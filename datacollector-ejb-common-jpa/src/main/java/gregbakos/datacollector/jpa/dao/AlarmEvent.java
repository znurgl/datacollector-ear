package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_ALARM_EVENTS")
public class AlarmEvent extends EntityBase implements Serializable {
	private static final long serialVersionUID = 6231165003278350689L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar eventDate;

	// Eszkalációs szint (0-9)
	@Column(nullable = true, name = "ESCALATION_LEVEL")
	private Integer escalationLevel;

	@Column(nullable = true)
	private Double measuredValue;

	@Column(length = 4000, name = "COMMENT_")
	private String comment;

	// bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(nullable = true)
	private Device device;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ALARM_TYPE")
	@JoinColumn(nullable = false)
	private Code alarmType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "VALUETYPE")
	@JoinColumn(nullable = true)
	private Code valueType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "EVENT_STAT")
	@JoinColumn(nullable = false)
	private Code eventStatus;

	@ManyToOne
	@JoinColumn(name = "ALARMEDUSER_ID")
	private User alarmedUser;

	@OneToMany(mappedBy = "alarmEvent")
	private Set<AlarmDescription> alarmDescriptions;

	public AlarmEvent() {
		super();
	}

	public Calendar getEventDate() {
		return eventDate;
	}

	public void setEventDate(Calendar eventDate) {
		this.eventDate = eventDate;
	}

	public Integer getEscalationLevel() {
		return escalationLevel;
	}

	public void setEscalationLevel(Integer escalationLevel) {
		this.escalationLevel = escalationLevel;
	}

	public Double getMeasuredValue() {
		return measuredValue;
	}

	public void setMeasuredValue(Double measuredValue) {
		this.measuredValue = measuredValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Code getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Code alarmType) {
		this.alarmType = alarmType;
	}

	public Code getValueType() {
		return valueType;
	}

	public void setValueType(Code valueType) {
		this.valueType = valueType;
	}

	public Code getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(Code eventStatus) {
		this.eventStatus = eventStatus;
	}

	public Set<AlarmDescription> getAlarmDescriptions() {
		return alarmDescriptions;
	}

	public void setAlarmDescriptions(Set<AlarmDescription> alarmDescriptions) {
		this.alarmDescriptions = alarmDescriptions;
	}

	public void setAlarmedUser(User alarmedUser) {
		this.alarmedUser = alarmedUser;
	}

	public User getAlarmedUser() {
		return alarmedUser;
	}

}
