package gregbakos.datacollector.jpa.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_USER_ROLES", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"USER_ID", "ROLE_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserRole extends EntityBase {
	private static final long serialVersionUID = 6665420984761880542L;

	// bi-directional many-to-one association to User
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private User user;

	// bi-directional many-to-one association to Role
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Role role;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String userName;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String roleCode;

	public UserRole() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!this.getClass().isInstance(o)) {
			return false;
		} else if (getId() == null || ((UserRole) o).getId() == null) {
			UserRole ur = (UserRole) o;
			if (getRoleCode().equals(ur.getRoleCode())
					&& getUserName().equals(ur.getUserName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return getId().equals(((EntityBase) o).getId());
		}
	}

}