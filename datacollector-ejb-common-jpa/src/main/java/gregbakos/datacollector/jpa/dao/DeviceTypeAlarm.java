package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_DEVICE_TYPE_ALARMS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"DEVICETYPE_ID", "VALUETYPE_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DeviceTypeAlarm extends EntityBase {
	private static final long serialVersionUID = -6811336729607813101L;

	@Column(precision = 8, scale = 4)
	private Double timeBase;

	@Column(precision = 12, scale = 6)
	private Double lowAlarmLevel;

	@Column(precision = 12, scale = 6)
	private Double highAlarmLevel;

	@Column(precision = 12, scale = 6)
	private Double lowWarningLevel;

	@Column(precision = 12, scale = 6)
	private Double highWarningLevel;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "VALUETYPE")
	@JoinColumn(nullable = false)
	private Code valueType;

	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceType deviceType;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Code unit;

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceTypeAlarm() {
		super();
	}

	public Double getTimeBase() {
		return timeBase;
	}

	public void setTimeBase(Double timeBase) {
		this.timeBase = timeBase;
	}

	public Double getLowAlarmLevel() {
		return lowAlarmLevel;
	}

	public void setLowAlarmLevel(Double lowAlarmLevel) {
		this.lowAlarmLevel = lowAlarmLevel;
	}

	public Double getHighAlarmLevel() {
		return highAlarmLevel;
	}

	public void setHighAlarmLevel(Double highAlarmLevel) {
		this.highAlarmLevel = highAlarmLevel;
	}

	public Double getLowWarningLevel() {
		return lowWarningLevel;
	}

	public void setLowWarningLevel(Double lowWarningLevel) {
		this.lowWarningLevel = lowWarningLevel;
	}

	public Double getHighWarningLevel() {
		return highWarningLevel;
	}

	public void setHighWarningLevel(Double highWarningLevel) {
		this.highWarningLevel = highWarningLevel;
	}

	public Code getValueType() {
		return valueType;
	}

	public void setValueType(Code valueType) {
		this.valueType = valueType;
	}

	public Code getUnit() {
		return unit;
	}

	public void setUnit(Code unit) {
		this.unit = unit;
	}

	public String getAlarms() {
		return ((lowAlarmLevel == null) ? "-" : ("<" + lowAlarmLevel)) + ","
				+ ((highAlarmLevel == null) ? "-" : (">" + highAlarmLevel));
	}

	public String getWarnings() {
		return ((lowWarningLevel == null) ? "-" : ("<" + lowWarningLevel))
				+ ","
				+ ((highWarningLevel == null) ? "-" : (">" + highWarningLevel));
	}
}
