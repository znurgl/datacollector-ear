package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_TASKS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Task extends EntityBase {
	private static final long serialVersionUID = -4304634412293735117L;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastRunTime;

	@Column(length = 4000)
	private String parameters;

	private Integer repeatCounter;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to TaskType
	@ManyToOne
	@JoinColumn(nullable = false)
	private TaskType taskType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "FELSTAT")
	// Érvényes értékek: CodeGroup.code="FELSTAT"
	@JoinColumn(nullable = false)
	private Code taskStatus;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Activity activity;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Device communicator;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Device device;

	/**
	 * A feladat létrehozásának időpontja.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationTime;

	/**
	 * A feladatból legyártott JMS üzenet elévülésének időpontja (eddig kell
	 * várakozni a válaszra)
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar messageExpiration;

	/**
	 * A feladat lejárati időpontja. Maximum eddig lehet próbálkozni a
	 * kiadással. <BR>
	 * A lejárati idő, ismétlődő tevékenység esetén kissebb kell legyen mint a
	 * következő Task kezdési időpontja.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar taskExpiration;

	/**
	 * A rádiófrekvenciás koncentrátorokon nyilvátartott azonosító
	 */
	private Long rfTaskId;

	public Code getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Code taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Device getCommunicator() {
		return communicator;
	}

	public void setCommunicator(Device communicator) {
		this.communicator = communicator;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Task() {
		super();
	}

	public Calendar getLastRunTime() {
		return lastRunTime;
	}

	public Date getLastRunTimeDate() {
		return lastRunTime == null ? null : lastRunTime.getTime();
	}

	public void setLastRunTime(Calendar lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Integer getRepeatCounter() {
		return repeatCounter;
	}

	public void setRepeatCounter(Integer repeatCounter) {
		this.repeatCounter = repeatCounter;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public Calendar getMessageExpiration() {
		return messageExpiration;
	}

	public void setMessageExpiration(Calendar messageExpiration) {
		this.messageExpiration = messageExpiration;
	}

	public Calendar getTaskExpiration() {
		return taskExpiration;
	}

	public void setTaskExpiration(Calendar taskExpiration) {
		this.taskExpiration = taskExpiration;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}

	public Date getCreationTimeDate() {
		return creationTime == null ? null : creationTime.getTime();
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public void setRfTaskId(Long rfTaskId) {
		this.rfTaskId = rfTaskId;
	}

	public Long getRfTaskId() {
		return rfTaskId;
	}

}