/**
 * 
 */
package ma.riaya.integration.util;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.internal.helper.Helper;
import org.eclipse.persistence.mappings.AggregateObjectMapping;
import org.eclipse.persistence.mappings.Association;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectMapMapping;
import org.eclipse.persistence.mappings.ObjectReferenceMapping;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.tools.schemaframework.IndexDefinition;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class NamingStartegy implements SessionCustomizer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.persistence.config.SessionCustomizer#customize(org.eclipse
	 * .persistence.sessions.Session)
	 */
	@Override
	public void customize(final Session session) throws Exception {
		for (final ClassDescriptor descriptor : session.getDescriptors().values()) {
            // Only change the table name for non-embedable entities with no
            // @Table already
            if (!descriptor.getTables().isEmpty() && descriptor.getAlias().equalsIgnoreCase(descriptor.getTableName())) {
                final String tableName = addUnderscores(Helper.getShortClassName(descriptor.getJavaClassName()));
                descriptor.setTableName(tableName);
                for (final IndexDefinition index : descriptor.getTables().get(0).getIndexes()) {
                    index.setTargetTable(tableName);
                }
            }
            for (final DatabaseMapping mapping : descriptor.getMappings()) {
                // Only change the column name for non-embedable entities with
                // no @Column already

                if (mapping instanceof AggregateObjectMapping) {
                    for (final Association association : ((AggregateObjectMapping) mapping).getAggregateToSourceFieldAssociations()) {
                        final DatabaseField field = (DatabaseField) association.getValue();
                        field.setName(addUnderscores(field.getName()));
                        
                        for (final DatabaseMapping attrMapping : session.getDescriptor(((AggregateObjectMapping) mapping).getReferenceClass()).getMappings()) {
                            if (attrMapping.getAttributeName().equalsIgnoreCase((String) association.getKey())) {
                                ((AggregateObjectMapping) mapping).addFieldTranslation(field, addUnderscores(attrMapping.getAttributeName()));
                                ((AggregateObjectMapping) mapping).getAggregateToSourceFields().remove(association.getKey());
                                break;
                            }
                        }
                    }
                } else if (mapping instanceof ObjectReferenceMapping) {
                    for (final DatabaseField foreignKey : ((ObjectReferenceMapping) mapping).getForeignKeyFields()) {
                        foreignKey.setName(addUnderscores(foreignKey.getName()));
                    }
                } else if (mapping instanceof DirectMapMapping) {
                    for (final DatabaseField referenceKey : ((DirectMapMapping) mapping).getReferenceKeyFields()) {
                        referenceKey.setName(addUnderscores(referenceKey.getName()));
                    }
                    for (final DatabaseField sourceKey : ((DirectMapMapping) mapping).getSourceKeyFields()) {
                        sourceKey.setName(addUnderscores(sourceKey.getName()));
                    }
                } else {
                    final DatabaseField field = mapping.getField();
                    if (field != null && !mapping.getAttributeName().isEmpty() && field.getName().equalsIgnoreCase(mapping.getAttributeName())) {
                        field.setName(addUnderscores(mapping.getAttributeName()));
                    }
                }
            }
        }
	}
	
	private static String addUnderscores(final String name) {
        final StringBuffer buf = new StringBuffer(name.replace('.', '_'));
        //buf.chars().filter(Character::isLowerCase);
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1))
					&& Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
        return buf.toString().toUpperCase();
    }
}
