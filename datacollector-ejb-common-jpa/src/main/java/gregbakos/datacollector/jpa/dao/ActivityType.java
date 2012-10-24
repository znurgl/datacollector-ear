package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_ACTIVITY_TYPES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class ActivityType extends EntityBase {
	private static final long serialVersionUID = 3273788115415284754L;

	@Transient
	public static final String ACTIVITY_TYPE_FIRMWARE_UPDATE = "COM_FIRMWARE_UPDATE";

	@Transient
	public static final String ACTIVITY_TYPE_PARAMETER_GET = "COM_PARAMETER_GET";

	@Transient
	public static final String ACTIVITY_TYPE_PARAMETER_SET = "COM_PARAMETER_SET";

	@Column(nullable = false, length = 100)
	private String name;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	@Column(length = 4000)
	private String description;

	@Transient
	@TranslatableAttribute(attribute = "description")
	private String i18nDescription;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ACTIVITY_KIND")
	@JoinColumn(nullable = false)
	private Code activityKind;

	// bi-directional many-to-one association to Activity
	@OneToMany(mappedBy = "activityType")
	private Set<Activity> activities;

	// bi-directional many-to-one association to TaskType
	@OneToMany(mappedBy = "activityType")
	private Set<TaskType> taskTypes;

	public ActivityType() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getI18nName() {
		return i18nName;
	}

	public void setI18nName(String i18nName) {
		this.i18nName = i18nName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getI18nDescription() {
		return i18nDescription;
	}

	public void setI18nDescription(String i18nDescription) {
		this.i18nDescription = i18nDescription;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<TaskType> getTaskTypes() {
		return taskTypes;
	}

	public void setTaskTypes(Set<TaskType> taskTypes) {
		this.taskTypes = taskTypes;
	}

	public Code getActivityKind() {
		return activityKind;
	}

	public void setActivityKind(Code activityKind) {
		this.activityKind = activityKind;
	}

}