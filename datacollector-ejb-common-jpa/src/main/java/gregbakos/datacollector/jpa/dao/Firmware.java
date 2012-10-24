package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_FIRMWARES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Firmware extends EntityBase {
	private static final long serialVersionUID = 6018594418695994216L;

	@Column(length = 20)
	private String firmwareVersion;

	@Column(nullable = true, length = 20)
	private String firmwareUrl;

	@Lob()
	private byte[] firmwareDataFlash;

	@Lob()
	private byte[] firmwareDataEEP;

	@Column(name = "COMMENT_", length = 1000)
	private String comment;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "FIRMWARETYPE")
	@JoinColumn(nullable = false)
	private Code firmwareType;

	@ManyToOne
	@JoinColumn(nullable = false)
	private DeviceType communicatorType;

	@OneToMany(mappedBy = "firmware")
	private Set<Device> devices;

	public DeviceType getCommunicatorType() {
		return communicatorType;
	}

	public void setCommunicatorType(DeviceType communicatorType) {
		this.communicatorType = communicatorType;
	}

	public Firmware() {
		super();
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getFirmwareUrl() {
		return firmwareUrl;
	}

	public void setFirmwareUrl(String firmwareUrl) {
		this.firmwareUrl = firmwareUrl;
	}

	public byte[] getFirmwareDataFlash() {
		return firmwareDataFlash;
	}

	public void setFirmwareDataFlash(byte[] firmwareDataFlash) {
		this.firmwareDataFlash = firmwareDataFlash;
	}

	public byte[] getFirmwareDataEEP() {
		return firmwareDataEEP;
	}

	public void setFirmwareDataEEP(byte[] firmwareDataEEP) {
		this.firmwareDataEEP = firmwareDataEEP;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public String getDisplayName() {
		return communicatorType.getI18nName() + "/" + firmwareVersion;
	}

	public Code getFirmwareType() {
		return firmwareType;
	}

	public void setFirmwareType(Code firmwareType) {
		this.firmwareType = firmwareType;
	}
}