package gregbakos.datacollector.jpa.dao;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_DEVICEGROUPINGS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DeviceGrouping extends EntityBase {
	public void setDeviceGroup(DeviceGroup deviceGroup) {
		this.deviceGroup = deviceGroup;
	}

	private static final long serialVersionUID = 6493312022007498385L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Device device;

	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceGroup deviceGroup;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public DeviceGroup getDeviceGroup() {
		return deviceGroup;
	}

	public DeviceGrouping() {
		super();
	}
}
