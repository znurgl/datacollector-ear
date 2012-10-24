package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_FRONT_SERVERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FrontServer extends EntityBase {
	private static final long serialVersionUID = 8312387889859674020L;

	@Column(nullable = false, length = 20)
	private String frontIp;

	@Column(nullable = false)
	private Integer frontPort;

	@Column(nullable = false, length = 300)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastWatchDogDate;

	@Column(nullable = false)
	private Integer maxConnection;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "frontServer")
	private Set<Device> devices;

	// bi-directional many-to-one association to SysPar
	@OneToMany(mappedBy = "frontServer")
	private Set<SysPar> sysPars;

	// bi-directional many-to-one association to SysPar
	@OneToMany(mappedBy = "frontServer")
	private Set<CommEventLog> commEventLogs;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "FRONTSTAT")
	@JoinColumn(nullable = false)
	private Code frontStatus;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "FRONT_TYPE")
	@JoinColumn(nullable = false)
	private Code frontType;

	public Code getFrontType() {
		return frontType;
	}

	public void setFrontType(Code frontType) {
		this.frontType = frontType;
	}

	public FrontServer() {
		super();
	}

	public String getFrontIp() {
		return frontIp;
	}

	public void setFrontIp(String frontIp) {
		this.frontIp = frontIp;
	}

	public Integer getFrontPort() {
		return frontPort;
	}

	public void setFrontPort(Integer frontPort) {
		this.frontPort = frontPort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Code getFrontStatus() {
		return frontStatus;
	}

	public void setFrontStatus(Code frontStatus) {
		this.frontStatus = frontStatus;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<SysPar> getSysPars() {
		return sysPars;
	}

	public void setSysPars(Set<SysPar> sysPars) {
		this.sysPars = sysPars;
	}

	public Set<CommEventLog> getCommEventLogs() {
		return commEventLogs;
	}

	public void setCommEventLogs(Set<CommEventLog> commEventLogs) {
		this.commEventLogs = commEventLogs;
	}

	public Calendar getLastWatchDogDate() {
		return lastWatchDogDate;
	}

	public void setLastWatchDogDate(Calendar lastWatchDogDate) {
		this.lastWatchDogDate = lastWatchDogDate;
	}

	public Integer getMaxConnection() {
		return maxConnection;
	}

	public void setMaxConnection(Integer maxConnection) {
		this.maxConnection = maxConnection;
	}
}