package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.auth.AuthUtil;
import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PostLoad;
import javax.persistence.Query;

public class TranslatorEntityListener implements Serializable {

	private static final String DEFAULT_LANGUAGE_CODE = "hu";

	private static final long serialVersionUID = 8632783079172058210L;

	private static final Logger logger = Logger
			.getLogger(TranslatorEntityListener.class.getName());

	private Long defaultLanguageId = null;

	private static Long systemDefaultLanguageId = null;

	public TranslatorEntityListener() {

	}

	public TranslatorEntityListener(Long defaultLanguageId) {
		this.defaultLanguageId = defaultLanguageId;
	}

	@PostLoad
	public void translateAttributes(Object entity) {
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DatacollectorJPA");
			EntityManager em = emf.createEntityManager();
			List<TranslatorBean> translatorBeans = new ArrayList<TranslatorBean>();

			Class<?> clazz = entity.getClass();

			Method getIdMethod = clazz.getMethod("getId", new Class[] {});
			Long id = (Long) getIdMethod.invoke(entity, new Object[] {});

			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields) {
				TranslatableAttribute fa = field
						.getAnnotation(TranslatableAttribute.class);
				if (fa != null) {

					TranslatorBean tb = new TranslatorBean();

					Method getField = clazz.getMethod(
							"get"
									+ Character.toUpperCase(fa.attribute()
											.charAt(0))
									+ fa.attribute().substring(1),
							new Class[] {});
					tb.setTranslatableValue((String) getField.invoke(entity,
							new Object[] {}));
					tb.setTranslatableAttribute(fa.attribute());
					tb.setDestinationAttribute(field.getName());
					translatorBeans.add(tb);

				}
			}
			if (translatorBeans.size() > 0) {
				if (defaultLanguageId == null) {

					Long langId = null;

					try {
						langId = AuthUtil.getCurrentUsersLanguageId();
					} catch (Throwable t) {
						if (systemDefaultLanguageId == null) {
							try {
								Query q = em
										.createQuery("select l.id from I18nSupportedLanguage l where l.languageCode = :defaultLanguageCode");
								q.setParameter("defaultLanguageCode",
										DEFAULT_LANGUAGE_CODE);
								systemDefaultLanguageId = (Long) q
										.getSingleResult();
							} catch (Throwable e) {
								// TODO ???
								systemDefaultLanguageId = 78001l;
								logger.severe("Hiba az alapértelemezett nyelv id meghatározásakor! "
										+ e.getMessage());
							}
						}
						langId = systemDefaultLanguageId;
					}

					TranslatorUtil.translate(em, entity, id, translatorBeans,
							langId);
				} else {
					TranslatorUtil.translate(em, entity, id, translatorBeans,
							defaultLanguageId);
				}
			}

		} catch (Throwable t) {
			logger.severe("Hiba történt a " + entity.getClass().getName()
					+ " típusú entitás fordításakor!" + t);
		}
	}
}
