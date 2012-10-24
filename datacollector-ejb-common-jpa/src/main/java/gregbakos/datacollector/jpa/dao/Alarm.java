package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ALARMS")
@EntityListeners({ ProviderCheckEntityListener.class })
public class Alarm extends EntityBase implements Serializable {
	private static final long serialVersionUID = -8720329190863568099L;

	public static final Integer DEFAULT_ESCALATION_LEVEL = 0;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = true, length = 20)
	private String telNum;

	@Column(nullable = true, length = 20)
	private String excLevel;

	@Column(nullable = true, length = 1)
	private Boolean allDeviceEvents;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ALARM_TYPE")
	@JoinColumn(nullable = true)
	private Code alarmType;

	@ManyToOne
	@JoinColumn
	private User user;

	@ManyToOne
	@JoinColumn
	private Device device;

	public Alarm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getExcLevel() {
		return excLevel;
	}

	public void setExcLevel(String excLevel) {
		this.excLevel = excLevel;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Code getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Code alarmType) {
		this.alarmType = alarmType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Device getDevice() {
		return device;
	}

	public void setAllDeviceEvents(Boolean allDeviceEvents) {
		this.allDeviceEvents = allDeviceEvents;
	}

	public Boolean getAllDeviceEvents() {
		return allDeviceEvents;
	}
}
