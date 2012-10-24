package gregbakos.datacollector.jpa.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_SYS_PAR_TYPES", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "NAME", "SYSPARGROUP_ID" }),
		@UniqueConstraint(columnNames = { "CODE", "SYSPARGROUP_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SysParType extends EntityBase {
	private static final long serialVersionUID = -7129810477687135868L;

	@Column(nullable = false, length = 100)
	private String code;

	@Column(nullable = false, length = 300)
	private String name;

	@Column(nullable = false, length = 300)
	private String description;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Code dataType;

	// bi-directional many-to-one association to SysParGroup
	@ManyToOne
	@JoinColumn(nullable = false)
	private SysParGroup sysParGroup;

	// bi-directional many-to-one association to SysPar
	@OneToMany(mappedBy = "sysParType")
	private Set<SysPar> sysPars;

	public SysParType() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Code getDataType() {
		return dataType;
	}

	public void setDataType(Code dataType) {
		this.dataType = dataType;
	}

	public SysParGroup getSysParGroup() {
		return sysParGroup;
	}

	public void setSysParGroup(SysParGroup sysParGroup) {
		this.sysParGroup = sysParGroup;
	}

	public Set<SysPar> getSysPars() {
		return sysPars;
	}

	public void setSysPars(Set<SysPar> sysPars) {
		this.sysPars = sysPars;
	}
}