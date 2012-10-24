package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_DEVICE_ATTRIBUTES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DeviceAttribute extends EntityBase {
	private static final long serialVersionUID = -3064671951961314700L;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Code getDeviceAttribType() {
		return deviceAttribType;
	}

	public void setDeviceAttribType(Code deviceAttribType) {
		this.deviceAttribType = deviceAttribType;
	}

	@Column(length = 300)
	private String DeviceAttrib;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Device device;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "DEVATTRIB")
	@JoinColumn(nullable = false)
	private Code deviceAttribType;

	public DeviceAttribute() {
		super();
	}

	public String getDeviceAttrib() {
		return this.DeviceAttrib;
	}

	public void setDeviceAttrib(String DeviceAttrib) {
		this.DeviceAttrib = DeviceAttrib;
	}

}
