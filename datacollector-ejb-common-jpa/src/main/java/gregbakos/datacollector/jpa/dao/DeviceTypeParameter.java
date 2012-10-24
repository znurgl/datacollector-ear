package gregbakos.datacollector.jpa.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_DEVICE_TYPE_PARAMETERS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DeviceTypeParameter extends EntityBase {

	private static final long serialVersionUID = -7919858718836747228L;

	@Column(nullable = false)
	private Boolean mandatory;

	@Column(nullable = false)
	private Boolean radiofrequency;

	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceType deviceType;

	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceParameterType deviceParameterType;

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceParameterType getDeviceParameterType() {
		return deviceParameterType;
	}

	public void setDeviceParameterType(DeviceParameterType deviceParameterType) {
		this.deviceParameterType = deviceParameterType;
	}

	public DeviceTypeParameter() {
		super();
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Boolean getRadiofrequency() {
		return radiofrequency;
	}

	public void setRadiofrequency(Boolean radiofrequency) {
		this.radiofrequency = radiofrequency;
	}

}
