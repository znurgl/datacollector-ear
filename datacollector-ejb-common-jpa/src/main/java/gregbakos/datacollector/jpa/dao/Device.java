package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ExcludeSuperclassListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_DEVICES", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"SERIALNUMBER", "DEVICETYPE_ID", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
@ExcludeSuperclassListeners
public class Device extends EntityBase {
	private static final long serialVersionUID = 7032845882146655170L;

	@Column(nullable = false, length = 100, unique = true)
	private String serialNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar mountingDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar warrantyEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar nextChangeDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastSealingDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar nextSealingDate;

	@Column(length = 20)
	private String channelNumber;

	@Column(nullable = true)
	private Long parallelChannelCount;

	@Column(length = 20)
	private String actualIp;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastSuccessRead;

	@Column(nullable = true)
	private Double lastSeqNumProcessed;

	@Column(nullable = true)
	private Long maxDelayTime;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceType deviceType;

	// bi-directional many-to-one association to Device
	@ManyToOne
	private Device communicator;

	// bi-directional many-to-one association to Provision
	@ManyToOne
	private Provision provision;

	@Transient
	private Customer customer;

	// bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(nullable = false)
	private Location location;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "BERSTAT")
	@JoinColumn(nullable = false)
	private Code deviceStatus;

	// bi-directional many-to-one association to FrontServer
	@ManyToOne
	private FrontServer frontServer;

	@ManyToOne
	private Firmware firmware;

	// bi-directional many-to-one association to Activity
	@OneToMany(mappedBy = "device")
	private Set<Activity> activities;

	@OneToMany(mappedBy = "device")
	private Set<Alarm> alarms;

	@OneToMany(mappedBy = "communicator")
	private Set<Device> devices;

	// bi-directional many-to-one association to DeviceParameterTable
	@OneToMany(mappedBy = "device")
	private Set<DeviceParameterTable> deviceParameterTables;

	@OneToMany(mappedBy = "device")
	private Set<DeviceGrouping> deviceGroupings;

	@OneToMany(mappedBy = "device")
	private Set<Task> tasks1;

	@OneToMany(mappedBy = "communicator")
	private Set<Task> tasks2;

	@OneToMany(mappedBy = "communicator")
	private Set<CommEventLog> commEventLogs;

	@OneToMany(mappedBy = "device")
	private Set<ActivityEventLog> activityEventLogs;

	@OneToMany(mappedBy = "device")
	private Set<Condition> conditions;

	@OneToMany(mappedBy = "communicator")
	private Set<Condition> conditions2;

	@OneToMany(mappedBy = "device")
	private Set<DeviceValue> deviceValues;

	@OneToMany(mappedBy = "device")
	private Set<Picture> pictures;

	@OneToMany(mappedBy = "device")
	private Set<AlarmEvent> alarmEvents;

	public Set<CommEventLog> getCommEventLogs() {
		return commEventLogs;
	}

	public void setCommEventLogs(Set<CommEventLog> commEventLogs) {
		this.commEventLogs = commEventLogs;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public Set<Task> getTasks1() {
		return tasks1;
	}

	public void setTasks1(Set<Task> tasks1) {
		this.tasks1 = tasks1;
	}

	public Set<Task> getTasks2() {
		return tasks2;
	}

	public void setTasks2(Set<Task> tasks2) {
		this.tasks2 = tasks2;
	}

	public Set<DeviceGrouping> getDeviceGroupings() {
		return deviceGroupings;
	}

	public void setDeviceGroupings(Set<DeviceGrouping> deviceGroupings) {
		this.deviceGroupings = deviceGroupings;
	}

	public Set<DeviceAttribute> getDeviceAttributes() {
		return deviceAttributes;
	}

	public void setDeviceAttributes(Set<DeviceAttribute> deviceAttributes) {
		this.deviceAttributes = deviceAttributes;
	}

	@OneToMany(mappedBy = "device")
	private Set<DeviceAttribute> deviceAttributes;

	public Device() {
		super();
	}

	public Calendar getMountingDate() {
		return mountingDate;
	}

	public void setMountingDate(Calendar mountingDate) {
		this.mountingDate = mountingDate;
	}

	public Calendar getWarrantyEndDate() {
		return warrantyEndDate;
	}

	public void setWarrantyEndDate(Calendar warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}

	public Calendar getNextChangeDate() {
		return nextChangeDate;
	}

	public void setNextChangeDate(Calendar nextChangeDate) {
		this.nextChangeDate = nextChangeDate;
	}

	public Calendar getLastSealingDate() {
		return lastSealingDate;
	}

	public void setLastSealingDate(Calendar lastSealingDate) {
		this.lastSealingDate = lastSealingDate;
	}

	public Calendar getNextSealingDate() {
		return nextSealingDate;
	}

	public void setNextSealingDate(Calendar nextSealingDate) {
		this.nextSealingDate = nextSealingDate;
	}

	public String getActualIp() {
		return actualIp;
	}

	public void setActualIp(String actualIp) {
		this.actualIp = actualIp;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public boolean isCommunicator() {
		return "K".equals(getDeviceClassCode());
	}

	public boolean isEndDevice() {
		return "V".equals(getDeviceClassCode());
	}

	public String getDeviceClassCode() {
		Code deviceClass = getDeviceClass();
		if (deviceClass == null) {
			return null;
		} else {
			return deviceClass.getCode();
		}
	}

	public Code getDeviceClass() {
		if (getDeviceType() == null) {
			return null;
		} else {
			Code deviceClass = getDeviceType().getDeviceClass();
			if (deviceClass == null) {
				return null;
			} else {
				return deviceClass;
			}
		}
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Device getCommunicator() {
		return communicator;
	}

	public void setCommunicator(Device communicator) {
		this.communicator = communicator;
	}

	public Provision getProvision() {
		return provision;
	}

	public void setProvision(Provision provision) {
		this.provision = provision;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public FrontServer getFrontServer() {
		return frontServer;
	}

	public void setFrontServer(FrontServer frontServer) {
		this.frontServer = frontServer;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	// public Set<Consumption> getConsumptions1() {
	// return consumptions1;
	// }
	//
	// public void setConsumptions1(Set<Consumption> consumptions1) {
	// this.consumptions1 = consumptions1;
	// }
	//
	// public Set<Consumption> getConsumptions2() {
	// return consumptions2;
	// }
	//
	// public void setConsumptions2(Set<Consumption> consumptions2) {
	// this.consumptions2 = consumptions2;
	// }

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<DeviceParameterTable> getDeviceParameterTables() {
		return deviceParameterTables;
	}

	public void setDeviceParameterTables(
			Set<DeviceParameterTable> deviceParameterTables) {
		this.deviceParameterTables = deviceParameterTables;
	}

	public Long getParallelChannelCount() {
		return parallelChannelCount;
	}

	public void setParallelChannelCount(Long parallelChannelCount) {
		this.parallelChannelCount = parallelChannelCount;
	}

	public Code getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Code deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Calendar getLastSuccessRead() {
		return lastSuccessRead;
	}

	public void setLastSuccessRead(Calendar lastSuccessRead) {
		this.lastSuccessRead = lastSuccessRead;
	}

	public Long getMaxDelayTime() {
		return maxDelayTime;
	}

	public void setMaxDelayTime(Long maxDelayTime) {
		this.maxDelayTime = maxDelayTime;
	}

	public Firmware getFirmware() {
		return firmware;
	}

	public void setFirmware(Firmware firmware) {
		this.firmware = firmware;
	}

	public Set<ActivityEventLog> getActivityEventLogs() {
		return activityEventLogs;
	}

	public void setActivityEventLogs(Set<ActivityEventLog> activityEventLogs) {
		this.activityEventLogs = activityEventLogs;
	}

	// származtatott értékek

	public String getDisplayName() {
		return (deviceType == null ? "" : deviceType.getI18nName() + " - ")
				+ serialNumber;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}

	public Set<Condition> getConditions2() {
		return conditions2;
	}

	public void setConditions2(Set<Condition> conditions2) {
		this.conditions2 = conditions2;
	}

	public Set<DeviceValue> getDeviceValues() {
		return deviceValues;
	}

	public void setDeviceValues(Set<DeviceValue> deviceValues) {
		this.deviceValues = deviceValues;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Set<AlarmEvent> getAlarmEvents() {
		return alarmEvents;
	}

	public void setAlarmEvents(Set<AlarmEvent> alarmEvents) {
		this.alarmEvents = alarmEvents;
	}

	public void setLastSeqNumProcessed(Double lastSeqNumProcessed) {
		this.lastSeqNumProcessed = lastSeqNumProcessed;
	}

	public Double getLastSeqNumProcessed() {
		return lastSeqNumProcessed;
	}

	public void setAlarms(Set<Alarm> alarms) {
		this.alarms = alarms;
	}

	public Set<Alarm> getAlarms() {
		return alarms;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}