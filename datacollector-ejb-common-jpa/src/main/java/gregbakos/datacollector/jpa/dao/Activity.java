package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ActivityListener;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_ACTIVITIES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class, ActivityListener.class })
public class Activity extends EntityBase {
	private static final long serialVersionUID = 7369467238594555899L;

	private Integer monthOfYear;

	private Integer dayOfMonth;

	private Integer hourOfDay;

	private Integer minuteOfHour;

	private Integer frequencyMinute;

	private Boolean continuousMeasuring;

	private Integer measuringCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startingDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endingDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastRunTime;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to ActivityType
	@ManyToOne
	@JoinColumn(nullable = false)
	private ActivityType activityType;

	// bi-directional many-to-one association to DeviceGroup
	@ManyToOne
	private DeviceGroup deviceGroup;

	// bi-directional many-to-one association to Device
	@ManyToOne
	private Device device;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "COMMEVENT")
	@JoinColumn(name = "STARTEREVENTTYPE_ID")
	private Code starterEventType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "TEVSTAT")
	@JoinColumn(nullable = true)
	private Code activityStatus;

	@OneToMany(mappedBy = "activity")
	private Set<Task> tasks;

	@OneToMany(mappedBy = "activity")
	private Set<ActivityEventLog> activityEventLogs;

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Code getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Code activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Activity() {
		super();
	}

	public Integer getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public Integer getMinuteOfHour() {
		return minuteOfHour;
	}

	public void setMinuteOfHour(Integer minuteOfHour) {
		this.minuteOfHour = minuteOfHour;
	}

	public Integer getFrequencyMinute() {
		return frequencyMinute;
	}

	public void setFrequencyMinute(Integer frequencyMinute) {
		this.frequencyMinute = frequencyMinute;
	}

	public Calendar getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Calendar startingDate) {
		this.startingDate = startingDate;
	}

	public Calendar getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Calendar endingDate) {
		this.endingDate = endingDate;
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

	public DeviceGroup getDeviceGroup() {
		return deviceGroup;
	}

	public void setDeviceGroup(DeviceGroup deviceGroup) {
		this.deviceGroup = deviceGroup;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Set<ActivityEventLog> getActivityEventLogs() {
		return activityEventLogs;
	}

	public void setActivityEventLogs(Set<ActivityEventLog> activityEventLogs) {
		this.activityEventLogs = activityEventLogs;
	}

	public Boolean getContinuousMeasuring() {
		return continuousMeasuring;
	}

	public void setContinuousMeasuring(Boolean continuousMeasuring) {
		this.continuousMeasuring = continuousMeasuring;
	}

	public Integer getMeasuringCount() {
		return measuringCount;
	}

	public void setMeasuringCount(Integer measuringCount) {
		this.measuringCount = measuringCount;
	}

	public void setStarterEventType(Code starterEventType) {
		this.starterEventType = starterEventType;
	}

	public Code getStarterEventType() {
		return starterEventType;
	}
}