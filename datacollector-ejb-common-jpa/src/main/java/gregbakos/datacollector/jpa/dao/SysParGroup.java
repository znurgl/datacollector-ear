package gregbakos.datacollector.jpa.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_SYS_PAR_GROUPS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "NAME" }),
		@UniqueConstraint(columnNames = { "CODE" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SysParGroup extends EntityBase {
	private static final long serialVersionUID = -4132196160907384728L;

	@Column(nullable = false, length = 20)
	private String code;

	@Column(nullable = false, length = 300)
	private String name;

	@Column(length = 300)
	private String description;

	// bi-directional many-to-one association to SysParType
	@OneToMany(mappedBy = "sysParGroup")
	private Set<SysParType> sysParTypes;

	public SysParGroup() {
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

	public Set<SysParType> getSysParTypes() {
		return sysParTypes;
	}

	public void setSysParTypes(Set<SysParType> sysParTypes) {
		this.sysParTypes = sysParTypes;
	}
}