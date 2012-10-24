package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.IptException;
import gregbakos.datacollector.jpa.dao.EntityBase;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.JoinColumn;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

public class ConstraintListener implements Serializable {

	private static final long serialVersionUID = -7142833485613990494L;

	private static final Logger logger = Logger
			.getLogger(ConstraintListener.class.getName());

	@PrePersist
	@PreUpdate
	public void checkConstraints(Object object) {
		EntityBase entity = (EntityBase) object;
		Class<?> clazz = entity.getClass();
		UniqueConstraint[] uniqueConstraints = clazz.getAnnotation(Table.class)
				.uniqueConstraints();
		StringBuffer errorCode = new StringBuffer("constraint."
				+ clazz.getSimpleName());
		for (UniqueConstraint annotation : uniqueConstraints) {
			try {
				String[] columnNames = annotation.columnNames();
				StringBuffer sql = new StringBuffer("SELECT OBJECT(o) FROM "
						+ clazz.getSimpleName() + " o WHERE ");
				Map<String, Object> parameterMap = new TreeMap<String, Object>();
				for (String column : columnNames) {
					try {
						ColumnAndValue columnAndValue = getColumnAndValue(
								column, entity);
						errorCode.append("-" + column.toLowerCase());
						sql.append("o." + columnAndValue.getSqlColumnName()
								+ "=:" + column.toLowerCase() + " AND ");
						parameterMap.put(column.toLowerCase(),
								columnAndValue.getValue());
					} catch (NoSuchMethodException nsme) {
						nsme.printStackTrace();
						logger.warning("Hiba történt constraint ellenörzés közben ");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						logger.warning("Hiba történt constraint ellenörzés közben ");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						logger.warning("Hiba történt constraint ellenörzés közben ");
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						logger.warning("Hiba történt constraint ellenörzés közben ");
					}
				}
				if (columnNames.length > 0) {
					sql = new StringBuffer(sql.substring(0, sql.length() - 4));
				}
				checkConstraint(sql.toString(), parameterMap,
						errorCode.toString(), entity);
			} catch (NullPointerException npe) {
				npe.printStackTrace();
				logger.warning("Hiba történt constraint ellenörzés közben ");
			}
		}
		checkUniqueColumns(clazz, entity);
		System.out.println();
	}

	private void checkUniqueColumns(Class<?> clazz, EntityBase entity) {
		try {
			StringBuffer errorCode = new StringBuffer("constraint."
					+ clazz.getSimpleName());
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				StringBuffer sql = new StringBuffer("SELECT OBJECT(o) FROM "
						+ clazz.getSimpleName() + " o WHERE ");
				if (field.getAnnotation(Column.class) != null) {
					boolean unique = field.getAnnotation(Column.class).unique();
					if (unique) {
						ColumnAndValue columnAndValue = getColumnAndValue(
								field.getName(), entity);
						errorCode.append("-" + field.getName().toLowerCase());
						sql.append("o." + columnAndValue.getSqlColumnName()
								+ "=:" + field.getName().toLowerCase()
								+ " AND ");
						Map<String, Object> parameterMap = new TreeMap<String, Object>();
						parameterMap.put(field.getName().toLowerCase(),
								columnAndValue.getValue());
						sql = new StringBuffer(sql.substring(0,
								sql.length() - 4));
						checkConstraint(sql.toString(), parameterMap,
								errorCode.toString(), entity);
					}
				}
			}
		} catch (SecurityException se) {
			se.printStackTrace();
			logger.warning("Hiba történt constraint ellenörzés közben ");
		} catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();
			logger.warning("Hiba történt constraint ellenörzés közben ");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.warning("Hiba történt constraint ellenörzés közben ");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.warning("Hiba történt constraint ellenörzés közben ");
		}
	}

	private ColumnAndValue getColumnAndValue(String column, Object entity)
			throws NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		Method method;
		if ((method = getMethodWithAnotation(column, entity.getClass())) != null) {
			ColumnAndValue columnAndValue = new ColumnAndValue();
			Object value = method.invoke(entity, null);
			columnAndValue.setSqlColumnName(method.getName().substring(3, 4)
					.toLowerCase()
					+ method.getName().substring(4));
			columnAndValue.setValue(value);
			return columnAndValue;
		} else if (column.toLowerCase().contains("_")) {
			int linePosition = column.toLowerCase().indexOf("_");
			ColumnAndValue columnAndValue = getColumnAndValue(
					column.substring(0, linePosition), entity);
			Object value = columnAndValue.getValue();
			ColumnAndValue subColumnAndValue = getColumnAndValue(
					column.substring(linePosition + 1), value);
			subColumnAndValue.setSqlColumnName(columnAndValue
					.getSqlColumnName()
					+ "."
					+ subColumnAndValue.getSqlColumnName());
			return subColumnAndValue;
		} else {
			ColumnAndValue columnAndValue = new ColumnAndValue();
			Class<?> clazz = entity.getClass();
			String geterMethodName = "get"
					+ column.substring(0, 1).toUpperCase()
					+ column.substring(1).toLowerCase();
			method = getMethod(geterMethodName, clazz);
			Object value = method.invoke(entity, null);

			columnAndValue.setSqlColumnName(method.getName().substring(3, 4)
					.toLowerCase()
					+ method.getName().substring(4));
			columnAndValue.setValue(value);
			return columnAndValue;
		}
	}

	private Method getMethodWithAnotation(String methodName, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Column.class) != null) {
				String name = field.getAnnotation(Column.class).name();
				if (methodName.equalsIgnoreCase(name)) {
					return getMethod("get" + field.getName(), clazz);
				}
			} else if (field.getAnnotation(JoinColumn.class) != null) {
				String name = field.getAnnotation(JoinColumn.class).name();
				if (methodName.equalsIgnoreCase(name)) {
					return getMethod("get" + field.getName(), clazz);
				}
			}
		}
		return null;
	}

	private Method getMethod(String methodName, Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase(methodName)) {
				return method;
			}
		}
		throw new IptException("internal.method");
	}

	private void checkConstraint(String sql, Map<String, Object> parameterMap,
			String errorCode, EntityBase entity) {
		if (sql != null && parameterMap != null) {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("DatacollectorJPA");
			EntityManager em = emf.createEntityManager();
			Query query = em.createQuery(sql);
			Iterator<Entry<String, Object>> parameterIterator = parameterMap
					.entrySet().iterator();
			while (parameterIterator.hasNext()) {
				Entry<String, Object> parameter = parameterIterator.next();
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
			if (entity.getId() != null) {
				// update
				List resultList = query.getResultList();
				int s = resultList.size();
				for (Object object : resultList) {
					EntityBase ent = (EntityBase) object;
					if (ent.getId().equals(entity.getId())) {
						break;
					}
					throw new IptException(errorCode);
				}
			} else {
				// insert
				if (query.getResultList().size() > 0) {
					throw new IptException(errorCode);
				}
			}
		}
	}

	class ColumnAndValue {
		private String sqlColumnName;
		private Object value;

		public void setSqlColumnName(String sqlColumnName) {
			this.sqlColumnName = sqlColumnName;
		}

		public String getSqlColumnName() {
			return this.sqlColumnName;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Object getValue() {
			return this.value;
		}
	}
}
