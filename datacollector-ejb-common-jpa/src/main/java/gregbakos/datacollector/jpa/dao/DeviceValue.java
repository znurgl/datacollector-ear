package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.LoggerEntityListener;

import java.util.Calendar;

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
@Table(name = "T_DEVICE_VALUES")
@EntityListeners({ LoggerEntityListener.class })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DeviceValue extends EntityBase {
	private static final long serialVersionUID = 612935103149438677L;

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

	@Column(precision = 12, scale = 6)
	private Double measuredMultiplier;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar validFromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar validToDate;

	// bi-directional many-to-one association to Code
	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "VALUETYPE")
	@JoinColumn(nullable = false)
	private Code valueType;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Device device;

	// bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(nullable = false)
	private Code unit;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public DeviceValue() {
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

	public Double getMeasuredMultiplier() {
		return measuredMultiplier;
	}

	public void setMeasuredMultiplier(Double measuredMultiplier) {
		this.measuredMultiplier = measuredMultiplier;
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

	public void setValidFromDate(Calendar validFromDate) {
		this.validFromDate = validFromDate;
	}

	public Calendar getValidFromDate() {
		return validFromDate;
	}

	public void setValidToDate(Calendar validToDate) {
		this.validToDate = validToDate;
	}

	public Calendar getValidToDate() {
		return validToDate;
	}
}
