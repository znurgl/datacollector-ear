package gregbakos.datacollector.jpa.annotation;

import gregbakos.datacollector.jpa.dao.Activity;
import gregbakos.datacollector.jpa.dao.ActivityEventLog;
import gregbakos.datacollector.jpa.dao.ActivityType;
import gregbakos.datacollector.jpa.dao.Alarm;
import gregbakos.datacollector.jpa.dao.AlarmDescription;
import gregbakos.datacollector.jpa.dao.AlarmEvent;
import gregbakos.datacollector.jpa.dao.AuditLog;
import gregbakos.datacollector.jpa.dao.CameraLocation;
import gregbakos.datacollector.jpa.dao.Code;
import gregbakos.datacollector.jpa.dao.CodeGroup;
import gregbakos.datacollector.jpa.dao.CommEventLog;
import gregbakos.datacollector.jpa.dao.CommandParameterType;
import gregbakos.datacollector.jpa.dao.CommandType;
import gregbakos.datacollector.jpa.dao.Condition;
import gregbakos.datacollector.jpa.dao.Consumption;
import gregbakos.datacollector.jpa.dao.ConsumptionSeries;
import gregbakos.datacollector.jpa.dao.Contract;
import gregbakos.datacollector.jpa.dao.ControlScript;
import gregbakos.datacollector.jpa.dao.Customer;
import gregbakos.datacollector.jpa.dao.CustomerAttribute;
import gregbakos.datacollector.jpa.dao.CustomerGroup;
import gregbakos.datacollector.jpa.dao.CustomerGrouping;
import gregbakos.datacollector.jpa.dao.Device;
import gregbakos.datacollector.jpa.dao.DeviceAttribute;
import gregbakos.datacollector.jpa.dao.DeviceGroup;
import gregbakos.datacollector.jpa.dao.DeviceGrouping;
import gregbakos.datacollector.jpa.dao.DeviceParameterTable;
import gregbakos.datacollector.jpa.dao.DeviceParameterType;
import gregbakos.datacollector.jpa.dao.DeviceType;
import gregbakos.datacollector.jpa.dao.DeviceTypeAlarm;
import gregbakos.datacollector.jpa.dao.DeviceTypeParameter;
import gregbakos.datacollector.jpa.dao.DeviceValue;
import gregbakos.datacollector.jpa.dao.EntityBase;
import gregbakos.datacollector.jpa.dao.Firmware;
import gregbakos.datacollector.jpa.dao.FrontServer;
import gregbakos.datacollector.jpa.dao.I18nSupportedLanguage;
import gregbakos.datacollector.jpa.dao.I18nTranslatedEntities;
import gregbakos.datacollector.jpa.dao.I18nTranslation;
import gregbakos.datacollector.jpa.dao.Location;
import gregbakos.datacollector.jpa.dao.Picture;
import gregbakos.datacollector.jpa.dao.Protocol;
import gregbakos.datacollector.jpa.dao.Provider;
import gregbakos.datacollector.jpa.dao.Provision;
import gregbakos.datacollector.jpa.dao.ProvisionType;
import gregbakos.datacollector.jpa.dao.ProvisionTypeValues;
import gregbakos.datacollector.jpa.dao.RateItem;
import gregbakos.datacollector.jpa.dao.RateTable;
import gregbakos.datacollector.jpa.dao.Result;
import gregbakos.datacollector.jpa.dao.Role;
import gregbakos.datacollector.jpa.dao.SysPar;
import gregbakos.datacollector.jpa.dao.SysParGroup;
import gregbakos.datacollector.jpa.dao.SysParType;
import gregbakos.datacollector.jpa.dao.Task;
import gregbakos.datacollector.jpa.dao.TaskParameterType;
import gregbakos.datacollector.jpa.dao.TaskType;
import gregbakos.datacollector.jpa.dao.User;
import gregbakos.datacollector.jpa.dao.UserRole;
import gregbakos.datacollector.jpa.daometa.DaoAnnotation;
import gregbakos.datacollector.jpa.daometa.DaoClass;
import gregbakos.datacollector.jpa.daometa.DaoField;
import gregbakos.datacollector.jpa.daometa.DaoSchema;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

public class DaoMetaBuilder {
	public static DaoSchema initSchemaMeta() {
		DaoSchema sch = new DaoSchema();
		HashMap<String, DaoClass> classes = sch.getClasses();
		
		initClass(classes, Activity.class);
		initClass(classes, ActivityEventLog.class);
		initClass(classes, ActivityType.class);
		initClass(classes, Alarm.class);
		initClass(classes, AlarmDescription.class);
		initClass(classes, AlarmEvent.class);
		initClass(classes, AuditLog.class);
		initClass(classes, CameraLocation.class);
		initClass(classes, Code.class);
		initClass(classes, CodeGroup.class);
		initClass(classes, CommandParameterType.class);
		initClass(classes, CommandType.class);
		initClass(classes, CommEventLog.class);
		initClass(classes, Condition.class);
		initClass(classes, Consumption.class);
		initClass(classes, ConsumptionSeries.class);
		initClass(classes, Contract.class);
		initClass(classes, ControlScript.class);
		initClass(classes, Customer.class);
		initClass(classes, CustomerAttribute.class);
		initClass(classes, CustomerGroup.class);
		initClass(classes, CustomerGrouping.class);
		initClass(classes, Device.class);
		initClass(classes, DeviceAttribute.class);
		initClass(classes, DeviceGroup.class);
		initClass(classes, DeviceGrouping.class);
		initClass(classes, DeviceParameterTable.class);
		initClass(classes, DeviceParameterType.class);
		initClass(classes, DeviceType.class);
		initClass(classes, DeviceTypeAlarm.class);
		initClass(classes, DeviceTypeParameter.class);
		initClass(classes, DeviceValue.class);
		initClass(classes, EntityBase.class);
		initClass(classes, Firmware.class);
		initClass(classes, FrontServer.class);
		initClass(classes, I18nSupportedLanguage.class);
		initClass(classes, I18nTranslatedEntities.class);
		initClass(classes, I18nTranslation.class);
		initClass(classes, Location.class);
		initClass(classes, Picture.class);
		initClass(classes, Protocol.class);
		initClass(classes, Provider.class);
		initClass(classes, Provision.class);
		initClass(classes, ProvisionType.class);
		initClass(classes, ProvisionTypeValues.class);
		initClass(classes, RateItem.class);
		initClass(classes, RateTable.class);
		initClass(classes, Result.class);
		initClass(classes, Role.class);
		initClass(classes, SysPar.class);
		initClass(classes, SysParGroup.class);
		initClass(classes, SysParType.class);
		initClass(classes, Task.class);
		initClass(classes, TaskParameterType.class);
		initClass(classes, TaskType.class);
		initClass(classes, User.class);
		initClass(classes, UserRole.class);
		return sch;
	}
	
	private static void initClass(HashMap<String, DaoClass> classes, Class cl) {
		DaoClass daoClass = new DaoClass();
		daoClass.setName(cl.getName());
		classes.put(cl.getName(), daoClass);
		Field[] fields = cl.getDeclaredFields();
		HashMap<String, DaoField> fl = daoClass.getFields();
		for (Field field : fields) {
			initField(fl, field);
		}
		
	}
	
	private static void initField(HashMap<String, DaoField> fields, Field field) {
		//System.out.println("-- FIELD "+field.getName());	
		DaoField daoField = new DaoField();
		daoField.setName(field.getName());
		fields.put(field.getName(), daoField);
		Annotation[] annot = field.getAnnotations();
		HashMap<String, DaoAnnotation> an = daoField.getAnnotations();
		for (Annotation annotation : annot) {
			initAnnotation(an, annotation);
		}
	}

	private static void initAnnotation(HashMap<String, DaoAnnotation> an, Annotation annotation) {
		//System.out.println("-- ANNOTATION "+annotation.annotationType().getName());	
		Class<? extends Annotation> annotationType = annotation.annotationType();
		
		if (annotationType.equals(Column.class) ||
			annotationType.equals(JoinColumn.class) ||
			annotationType.equals(CodeGroupReference.class)	) {
			DaoAnnotation daoAnnotation = new DaoAnnotation();
			daoAnnotation.setName(annotationType.getName());
			an.put(annotationType.getName(), daoAnnotation);
					
			//Field[] fields = annotationType.getFields();
			Method[] methods = annotationType.getMethods();
			
			try {
				for (Method method : methods) {
					if ((method.getReturnType().equals(String.class) || method.getReturnType().equals(Boolean.TYPE)) &&
						(method.getParameterTypes().length == 0 && !method.getName().equals("toString")&& !method.getName().equals("hashCode"))) {
						//System.out.println(method.getName());	
						Object ret = method.invoke(annotation);
						if (ret != null) {
							daoAnnotation.put(method.getName(), ret);
						}
					}
				}
				/*for (Field field : fields) {
					Object object;
					object = field.get(annotation);
					daoAnnotation.put(field.getName(), object);
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

}
