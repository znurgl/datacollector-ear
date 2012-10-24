package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_TASK_TYPES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class TaskType extends EntityBase {
	private static final long serialVersionUID = -3466689047482777873L;

	@Column(nullable = false)
	private Boolean valid;

	@Column(length = 4000)
	private String description;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean multiRowAnswer;

	@Column(nullable = true, length = 100)
	private String answerObjectName;

	@Column(nullable = true, length = 100)
	private String answerObjectGet;

	@Column(nullable = true, length = 100)
	private String answerRowObjectName;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to ActivityType
	@ManyToOne
	@JoinColumn(nullable = false)
	private ActivityType activityType;

	// bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceType communicatorType;

	// bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(nullable = true)
	private DeviceType deviceType;

	// bi-directional many-to-one association to ControlScript
	@ManyToOne
	@JoinColumn(nullable = false)
	private ControlScript controlScript;

	// bi-directional many-to-one association to Task
	@OneToMany(mappedBy = "taskType")
	private Set<Task> tasks;

	// bi-directional many-to-one association to TaskParameterType
	@OneToMany(mappedBy = "taskType")
	private Set<TaskParameterType> taskParameterTypes;

	public TaskType() {
		super();
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public DeviceType getCommunicatorType() {
		return communicatorType;
	}

	public void setCommunicatorType(DeviceType communicatorType) {
		this.communicatorType = communicatorType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public ControlScript getControlScript() {
		return controlScript;
	}

	public void setControlScript(ControlScript controlScript) {
		this.controlScript = controlScript;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<TaskParameterType> getTaskParameterTypes() {
		return taskParameterTypes;
	}

	public void setTaskParameterTypes(Set<TaskParameterType> taskParameterTypes) {
		this.taskParameterTypes = taskParameterTypes;
	}

	public Boolean getMultiRowAnswer() {
		return multiRowAnswer;
	}

	public void setMultiRowAnswer(Boolean multiRowAnswer) {
		this.multiRowAnswer = multiRowAnswer;
	}

	public String getAnswerObjectName() {
		return answerObjectName;
	}

	public void setAnswerObjectName(String answerObjectName) {
		this.answerObjectName = answerObjectName;
	}

	public String getAnswerObjectGet() {
		return answerObjectGet;
	}

	public void setAnswerObjectGet(String answerObjectGet) {
		this.answerObjectGet = answerObjectGet;
	}

	public String getAnswerRowObjectName() {
		return answerRowObjectName;
	}

	public void setAnswerRowObjectName(String answerRowObjectName) {
		this.answerRowObjectName = answerRowObjectName;
	}

}