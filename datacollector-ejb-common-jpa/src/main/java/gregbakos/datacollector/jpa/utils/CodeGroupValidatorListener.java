package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.dao.Code;
import gregbakos.datacollector.jpa.exception.InvalidCodeGroupException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Logger;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CodeGroupValidatorListener implements Serializable {

	private static final long serialVersionUID = 8632783079172058210L;

	private static final Logger logger = Logger
			.getLogger(CodeGroupValidatorListener.class.getName());

	@PrePersist
	public void validatePreInsert(Object entity) {
		validate(entity);
	}

	@PreUpdate
	public void validatePreUpdate(Object entity) {
		validate(entity);
	}

	private void validate(Object entity) {

		try {
			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields) {

				CodeGroupReference annotation = field
						.getAnnotation(CodeGroupReference.class);
				if (annotation != null && (field.get(entity) instanceof Code)) {

					Code code = (Code) field.get(entity);

					if (code != null) {
						if (!annotation.CodeGroupCode().equals(
								code.getCodeGroup().getCode())) {
							throw new InvalidCodeGroupException(
									"Nem megengedett Kódcsoport kód: "
											+ code.getCodeGroup().getCode()
											+ "; megengedett: "
											+ annotation.CodeGroupCode());
						}
					}
				}
			}

		} catch (Throwable t) {
			logger.severe("Hiba történt a " + entity.getClass().getName()
					+ " típusú entitás validálásakor!" + t);
		}

	}

}
