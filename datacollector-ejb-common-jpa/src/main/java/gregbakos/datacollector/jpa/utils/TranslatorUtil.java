package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.jpa.dao.I18nTranslation;

import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TranslatorUtil {

	public static void translate(EntityManager em, Object entity, Long id,
			List<TranslatorBean> translatorBeans, Long languageId)
			throws Exception {

		Class<?> clazz = entity.getClass();
		try {
			Query query = em
					.createQuery("select OBJECT(i) from I18nTranslation i "
							+ "where i.entity.entityName = :className and i.entityId = :entityId "
							+ "and i.i18nSupportedLanguage.id = :languageId");

			/*
			 * Query query =
			 * em.createQuery("select OBJECT(i) from I18nTranslation i, User u "
			 * +
			 * "where u.userName = :userName and i.entityName = :className and i.entityId = :entityId "
			 * + "and i.i18nSupportedLanguage.id = languageId");
			 */
			query.setParameter("className", clazz.getName());
			query.setParameter("entityId", id);
			query.setParameter("languageId", languageId);

			I18nTranslation translation;

			try {
				translation = (I18nTranslation) query.getSingleResult();
			} catch (Exception ex) {
				return;
			}

			StringTokenizer st = new StringTokenizer(
					translation.getTranslations(), "=;");
			while (st.hasMoreTokens()) {
				String propName = st.nextToken();
				String value = "";
				if (st.hasMoreTokens()) {
					value = st.nextToken();
				}
				for (TranslatorBean tb : translatorBeans) {
					if (propName.equals(tb.getTranslatableAttribute())) {
						String setterName = "set"
								+ Character.toUpperCase(tb
										.getDestinationAttribute().charAt(0))
								+ tb.getDestinationAttribute().substring(1);
						clazz.getMethod(setterName,
								new Class[] { String.class }).invoke(entity,
								value);
						tb.setTranslated(true);
						break;
					}
				}
			}
		} finally {
			for (TranslatorBean tb : translatorBeans) {
				if (!tb.isTranslated()) {
					String setterName = "set"
							+ Character.toUpperCase(tb
									.getDestinationAttribute().charAt(0))
							+ tb.getDestinationAttribute().substring(1);
					clazz.getMethod(setterName, new Class[] { String.class })
							.invoke(entity, tb.getTranslatableValue());

				}
			}
		}

	}

}
