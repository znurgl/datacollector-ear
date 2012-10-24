package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.auth.AuthUtil;
import gregbakos.datacollector.jpa.codetable.CodetableUtil;
import gregbakos.datacollector.jpa.dao.AuditLog;
import gregbakos.datacollector.jpa.dao.Code;
import gregbakos.datacollector.jpa.dao.User;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Query;

public class LoggerEntityListener implements Serializable {

	private static final long serialVersionUID = 8632783079172058210L;

	private static final Logger logger = Logger
			.getLogger(LoggerEntityListener.class.getName());

	private static Code INSERT_EVENT_ID = null;
	private static Code UPDATE_EVENT_ID = null;

	@PostPersist
	public void logInsert(Object entity) {
		if (INSERT_EVENT_ID == null) {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DatacollectorJPA");
			EntityManager em = emf.createEntityManager();
			INSERT_EVENT_ID = CodetableUtil.getCodeByCodes(em, "AUDIT_EVENT",
					"INSERT");
		}
		writeAuditLog(entity, INSERT_EVENT_ID);
	}

	@PostUpdate
	public void logUpdate(Object entity) {
		if (UPDATE_EVENT_ID == null) {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DatacollectorJPA");
			EntityManager em = emf.createEntityManager();
			UPDATE_EVENT_ID = CodetableUtil.getCodeByCodes(em, "AUDIT_EVENT",
					"UPDATE");
		}
		writeAuditLog(entity, UPDATE_EVENT_ID);
	}

	@PostRemove
	public void clearAuditLog(Object entity) {
		try {
			Class<?> clazz = entity.getClass();
			String className = clazz.getName();
			Method getIdMethod = clazz.getMethod("getId", new Class[] {});
			Long entityId = (Long) getIdMethod.invoke(entity, new Object[] {});

			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DatacollectorJPA");
			EntityManager em = emf.createEntityManager();

			Query query = em.createNamedQuery(AuditLog.QUERY_DELETE);
			query.setParameter("entityName", className);
			query.setParameter("entityId", entityId);
			query.executeUpdate();
		} catch (Throwable e) {
			e.printStackTrace();
			logger.warning("Hiba törént AuditLog törlés közben");
		}
	}

	public void writeAuditLog(Object entity, Code code) {

		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DatacollectorJPA");
			EntityManager em = emf.createEntityManager();

			User user = AuthUtil.getEjbCallerFelhasznalo();

			Class<?> clazz = entity.getClass();
			Method getIdMethod = clazz.getMethod("getId", new Class[] {});
			Long entityId = (Long) getIdMethod.invoke(entity, new Object[] {});

			AuditLog logEntry = new AuditLog();
			logEntry.setLogEntityName(entity.getClass().getCanonicalName());
			logEntry.setLogEntityId(entityId);
			logEntry.setLogUser(user);
			logEntry.setLogTimestamp(GregorianCalendar.getInstance());
			logEntry.setOperationCode(code);
			em.persist(logEntry);

		} catch (Throwable e) {
			try {
				e.printStackTrace();
				logger.warning("Hiba törént az AuditLog írása közben");
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
		}
	}

}
