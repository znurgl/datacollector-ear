package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.IptException;
import gregbakos.datacollector.auth.AuthUtil;
import gregbakos.datacollector.jpa.dao.EntityBase;
import gregbakos.datacollector.jpa.dao.Provider;
import gregbakos.datacollector.jpa.dao.User;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class ProviderCheckEntityListener implements Serializable {

	private static final long serialVersionUID = 8692824383297446634L;

	private static final Logger logger = Logger
			.getLogger(ProviderCheckEntityListener.class.getName());

	@PrePersist
	@PreRemove
	@PreUpdate
	public void providerCheck(Object entity) {
		boolean needCheck = false;
		int code = 0;
		try {

			User u = null;
			try {
				List<String> roles = AuthUtil.getCallersRoles();
				boolean isFactory = roles.contains("FACTORY");
				boolean isUser = roles.contains("ADMIN")
						|| roles.contains("USER");

				if (isFactory) {
					return;
				}

				if (isUser) {
					u = AuthUtil.getEjbCallerFelhasznalo();
					needCheck = true;
				} else {
					code = 3;
				}
			} catch (Exception e) {
				return;
			}
			if (needCheck) {
				code = performCheck(entity, u);
			}
		} catch (Throwable e) {
			code = 0;

			logger.severe("A ProviderCheckListener hibára futott: " + e);
			e.printStackTrace();
		}
		if (code == 1) {
			throw new IptException("foreignProvidersEntity");
		} else if (code == 2) {
			throw new IptException("unauthorizedProviderModification");
		} else if (code == 3) {
			throw new IptException("foreignProvidersNewEntity");
		}
	}

	private int performCheck(Object entity, User user) throws Exception {

		Class clazz = entity.getClass();
		Method method = null;
		try {
			method = clazz.getMethod("getProvider", new Class[] {});
		} catch (Exception e) {
			return 0;
		}

		if (method.getReturnType().equals(Provider.class)) {
			Provider prov = (Provider) method.invoke(entity, new Object[] {});

			Long id = null;
			if (entity instanceof EntityBase) {
				id = ((EntityBase) entity).getId();
			}

			if (id != null) {
				Provider oldProv = null;

				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("DatacollectorJPA");
				EntityManager em = emf.createEntityManager();
				Object oldEntity = em.find(clazz, id);
				if (oldEntity != null) {
					oldProv = (Provider) method.invoke(oldEntity,
							new Object[] {});
					if (oldProv != null) {
						if (!oldProv.getId().equals(user.getProvider().getId())) {
							return 1;
						}
					} else if (prov != null
							&& !prov.getId().equals(user.getProvider().getId())) {
						return 2;
					}
					return 0;
				}
			}
			if (prov != null
					&& !prov.getId().equals(user.getProvider().getId())) {
				return 3;
			}

		}

		return 0;
	}

}
