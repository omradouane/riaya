/**
 * 
 */
package ma.riaya.integration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.jpa.metamodel.ManagedTypeImpl;
import org.junit.Before;

import ma.riaya.integration.repos.query.QueryTool;
import ma.riaya.integration.util.Constants;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public abstract class AbstractTest {

	private static final Logger log = Logger.getLogger(AbstractTest.class);
	
	static {
		System.setProperty(Constants.PU_KEY, Constants.TEST_PU);
	}

	@Before
	public void initialize() {
		log.debug("initialize database");
		final EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory(Constants.TEST_PU);
		final EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// disable foreign keys
		enableDatabaseIntegrity(em, Boolean.FALSE);
		// reset the DIC_SEQUENCE table
		resetSequence(em);
		// truncate all tables
		truncateTables(em);
		// enable foreign keys
		enableDatabaseIntegrity(em, Boolean.TRUE);
		
		em.getTransaction().commit();
	}

	
	private void truncateTables(final EntityManager em) {
		em.getMetamodel().getEntities().parallelStream()
										.map(ManagedTypeImpl.class::cast)
										.map(ManagedTypeImpl::getDescriptor)
										.map(ClassDescriptor::getTableName)
										.map(QueryTool.DELETE_QUERY::concat)
										.map(em::createNativeQuery)
										.forEach(Query::executeUpdate);
	}
	
	private void resetSequence(final EntityManager em) {
		em.createNativeQuery(QueryTool.RESET_ALL_SEQUENCE_STRING).executeUpdate();
	}
	
	private void enableDatabaseIntegrity(final EntityManager em, final Boolean enabled){
		em.createNativeQuery(QueryTool.ALTER_DATABASE_INTEGRITY + enabled.toString().toUpperCase()).executeUpdate();
	}
}
