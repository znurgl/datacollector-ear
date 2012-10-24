package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_PICTURES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Picture extends EntityBase implements Serializable {
	private static final long serialVersionUID = 8540949733974156947L;

	@Lob()
	private byte[] picture;

	// bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(nullable = true)
	private DeviceType deviceType;

	// bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(nullable = true)
	private Device device;

	// bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(nullable = true)
	private Location location;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "PICTYPE")
	@JoinColumn(nullable = false)
	private Code pictureType;

	public Picture() {
		super();
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Code getPictureType() {
		return pictureType;
	}

	public void setPictureType(Code pictureType) {
		this.pictureType = pictureType;
	}

}
