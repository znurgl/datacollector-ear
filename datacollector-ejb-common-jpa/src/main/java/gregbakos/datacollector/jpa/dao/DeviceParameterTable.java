package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_DEVICE_PARAMETER_TABLES", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"DEVICEPARAMETERTYPE_ID", "DEVICE_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class DeviceParameterTable extends EntityBase {
	private static final long serialVersionUID = -193983009753464591L;

	@Column(length = 1000)
	private String deviceParameterValue;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(nullable = false)
	private Device device;

	// bi-directional many-to-one association to DeviceParameterType
	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceParameterType deviceParameterType;

	public DeviceParameterTable() {
		super();
	}

	public String getDeviceParameterValue() {
		return deviceParameterValue;
	}

	public void setDeviceParameterValue(String deviceParameterValue) {
		this.deviceParameterValue = deviceParameterValue;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public DeviceParameterType getDeviceParameterType() {
		return deviceParameterType;
	}

	public void setDeviceParameterType(DeviceParameterType deviceParameterType) {
		this.deviceParameterType = deviceParameterType;
	}
}