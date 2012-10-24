package gregbakos.datacollector.jpa.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_SYS_PARS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"FRONTSERVER_ID", "SYSPARTYPE_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SysPar extends EntityBase {
	private static final long serialVersionUID = -4844153801830853374L;

	@Column(name = "SYS_PAR_VALUE", length = 300)
	private String sysParValue;

	@ManyToOne
	@JoinColumn(nullable = true)
	private FrontServer frontServer;

	// bi-directional many-to-one association to SysParType
	@ManyToOne
	@JoinColumn(nullable = false)
	private SysParType sysParType;

	public SysPar() {
		super();
	}

	public String getSysParValue() {
		return sysParValue;
	}

	public void setSysParValue(String sysParValue) {
		this.sysParValue = sysParValue;
	}

	public FrontServer getFrontServer() {
		return frontServer;
	}

	public void setFrontServer(FrontServer frontServer) {
		this.frontServer = frontServer;
	}

	public SysParType getSysParType() {
		return sysParType;
	}

	public void setSysParType(SysParType sysParType) {
		this.sysParType = sysParType;
	}
}