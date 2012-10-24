package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_DEVICE_TYPES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class DeviceType extends EntityBase {

	private static final long serialVersionUID = 8866526336822755587L;

	@Column(nullable = false, length = 20)
	private String code;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = true)
	private Long maxParallelChannel;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	@Column(nullable = true, name = "COMMENT_", length = 4000)
	private String comment;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean radioFrequency;

	@Transient
	@TranslatableAttribute(attribute = "comment")
	private String i18nComment;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to ProvisionType
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProvisionType provisionType;

	// bi-directional many-to-one association to Protocol
	@ManyToOne
	@JoinColumn(nullable = false)
	private Protocol protocol;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "BERJELL")
	// Érvényes értékek: CodeGroup.code="BERJELL"
	@JoinColumn(nullable = false)
	private Code deviceClass;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "deviceType")
	private Set<Device> devices;

	// bi-directional many-to-one association to TaskType
	@OneToMany(mappedBy = "communicatorType")
	private Set<TaskType> taskTypes1;

	// bi-directional many-to-one association to TaskType
	@OneToMany(mappedBy = "deviceType")
	private Set<TaskType> taskTypes2;

	// bi-directional many-to-one association to TaskType
	@OneToMany(mappedBy = "communicatorType")
	private Set<Firmware> firmwares;

	@OneToMany(mappedBy = "deviceType")
	private Set<DeviceTypeParameter> deviceTypeParameters;

	@OneToMany(mappedBy = "deviceType")
	private Set<DeviceTypeAlarm> deviceTypeAlarms;

	@OneToMany(mappedBy = "deviceType")
	private Set<Picture> pictures;

	public Code getDeviceClass() {
		return deviceClass;
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

	public void setDeviceClass(Code deviceClass) {
		this.deviceClass = deviceClass;
	}

	public Set<Firmware> getFirmwares() {
		return firmwares;
	}

	public void setFirmwares(Set<Firmware> firmwares) {
		this.firmwares = firmwares;
	}

	public Set<DeviceTypeParameter> getDeviceTypeParameters() {
		return deviceTypeParameters;
	}

	public void setDeviceTypeParameters(
			Set<DeviceTypeParameter> deviceTypeParameters) {
		this.deviceTypeParameters = deviceTypeParameters;
	}

	public DeviceType() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getI18nName() {
		return i18nName;
	}

	public void setI18nName(String i18nName) {
		this.i18nName = i18nName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getI18nComment() {
		return i18nComment;
	}

	public void setI18nComment(String i18nComment) {
		this.i18nComment = i18nComment;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public ProvisionType getProvisionType() {
		return provisionType;
	}

	public void setProvisionType(ProvisionType provisionType) {
		this.provisionType = provisionType;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<TaskType> getTaskTypes1() {
		return taskTypes1;
	}

	public void setTaskTypes1(Set<TaskType> taskTypes1) {
		this.taskTypes1 = taskTypes1;
	}

	public Set<TaskType> getTaskTypes2() {
		return taskTypes2;
	}

	public void setTaskTypes2(Set<TaskType> taskTypes2) {
		this.taskTypes2 = taskTypes2;
	}

	public Long getMaxParallelChannel() {
		return maxParallelChannel;
	}

	public void setMaxParallelChannel(Long maxParallelChannel) {
		this.maxParallelChannel = maxParallelChannel;
	}

	public Set<DeviceTypeAlarm> getDeviceTypeAlarms() {
		return deviceTypeAlarms;
	}

	public void setDeviceTypeAlarms(Set<DeviceTypeAlarm> deviceTypeAlarms) {
		this.deviceTypeAlarms = deviceTypeAlarms;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Boolean getRadioFrequency() {
		return radioFrequency;
	}

	public void setRadioFrequency(Boolean radioFrequency) {
		this.radioFrequency = radioFrequency;
	}

}