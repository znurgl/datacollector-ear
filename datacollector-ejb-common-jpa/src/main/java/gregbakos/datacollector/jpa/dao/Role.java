package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_ROLES", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "NAME" }),
		@UniqueConstraint(columnNames = { "CODE" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class })
public class Role extends EntityBase {
	private static final long serialVersionUID = 3140465141999914379L;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String code;

	@Transient
	@TranslatableAttribute(attribute = "code")
	private String i18nCode;

	@Basic(optional = false)
	@Column(length = 300, nullable = false)
	private String name;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	@Column(length = 1000, name = "COMMENT_")
	private String comment;

	@Transient
	@TranslatableAttribute(attribute = "comment")
	private String i18nComment;

	@OneToMany(mappedBy = "role")
	private Set<UserRole> roleUsers;

	public Role() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getI18nCode() {
		return i18nCode;
	}

	public void setI18nCode(String i18nCode) {
		this.i18nCode = i18nCode;
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

	public void setI18nComment(String setI18nComment) {
		this.i18nComment = setI18nComment;
	}

	public Set<UserRole> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<UserRole> roleUsers) {
		this.roleUsers = roleUsers;
	}

}