package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.IptException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

public class DeleteConstraintCheckEntityListener implements Serializable {

	private static final long serialVersionUID = -3957251960519222703L;

	private static final Logger logger = Logger
			.getLogger(DeleteConstraintCheckEntityListener.class.getName());

	private static final String REASON_PREFIX = "constraint";

	@PreRemove
	public void checkChildren(Object entity) {
		Class<?> childType = null;

		try {
			Class<?> clazz = entity.getClass();

			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields) {
				OneToMany annotation = field.getAnnotation(OneToMany.class);
				if (annotation != null) {
					Method getField = clazz.getMethod(
							"get"
									+ Character.toUpperCase(field.getName()
											.charAt(0))
									+ field.getName().substring(1),
							new Class[] {});

					Collection<?> collection = (Collection<?>) getField.invoke(
							entity, new Object[] {});
					if (!collection.isEmpty()) {
						childType = collection.iterator().next().getClass();
						break;
					}
				}
			}
		} catch (Throwable t) {
			logger.severe("Hiba történt fizikai törlés engedélyezésének ellenőrzése közben.");
			t.printStackTrace();
		}
		if (childType != null) {
			String entityName = entity
					.getClass()
					.getName()
					.substring(entity.getClass().getName().lastIndexOf('.') + 1);
			String childName = childType.getName().substring(
					childType.getName().lastIndexOf('.') + 1);
			String reason = REASON_PREFIX + "." + "" + entityName + "-"
					+ childName;
			throw new IptException(reason);
		}
	}
}
