package gregbakos.datacollector.auth;

import gregbakos.datacollector.IptException;
import gregbakos.datacollector.jpa.dao.Provider;
import gregbakos.datacollector.jpa.dao.User;

import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBContext;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;

public class AuthUtil {

	public static String getEjbCallerPrincipal() {
		String principalName = "SYSTEM";
		try {
			InitialContext ic = new InitialContext();
			EJBContext ctx = (EJBContext) ic.lookup("java:comp/EJBContext");
			if (!"ANONYMOUS".equals(ctx.getCallerPrincipal().getName())) {
				principalName = ctx.getCallerPrincipal().getName();
			}

		} catch (Exception e) {
			try {
				Subject subject = (Subject) PolicyContext
						.getContext("javax.security.auth.Subject.container");
				Principal principal;

				if (subject != null) {
					Iterator<Principal> principals = subject.getPrincipals()
							.iterator();

					while (principals.hasNext()) {
						principal = principals.next();
						if (!(principal instanceof Group)) {
							if (!"ANONYMOUS".equals(principal.getName())) {
								principalName = principal.getName();
							}
						}
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return principalName;
	}

	public static User getEjbCallerFelhasznalo() {
		String userName = getEjbCallerPrincipal();
		return getFelhasznalo(userName);
	}

	public static User getFelhasznalo(String userName) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DatacollectorJPA");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select OBJECT(u) from User u "
				+ "where u.userName = :userName");

		query.setParameter("userName", userName);
		return (User) query.getSingleResult();
	}

	public static Long getCurrentUsersLanguageId() {
		String userName = getEjbCallerPrincipal();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DatacollectorJPA");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select u.userLanguage.id from User u "
				+ "where u.userName = :userName");

		query.setParameter("userName", userName);
		return (Long) query.getSingleResult();
	}

	public static List<String> getCallersRoles() throws Exception {
		List<String> groups = new ArrayList<String>();
		Subject subject = (Subject) PolicyContext
				.getContext("javax.security.auth.Subject.container");
		Principal principal;

		if (subject != null) {
			Iterator<Principal> principals = subject.getPrincipals().iterator();

			while (principals.hasNext()) {
				principal = principals.next();
				if (principal instanceof Group) {
					groups.add(principal.getName());
				}
			}
		}
		return groups;
	}

	public static Provider getProviderFilter() {
		try {
			List<String> roles = AuthUtil.getCallersRoles();
			boolean isFactory = roles.contains("FACTORY");
			if (isFactory) {
				return null;
			}
			User u = AuthUtil.getEjbCallerFelhasznalo();
			return u.getProvider();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IptException("notAuthenticated", e);
		}
	}
}
