package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.EmailListener;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_USERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "USERNAME" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class, EmailListener.class })
public class User extends EntityBase {
	private static final long serialVersionUID = 2420645733822000756L;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String userName;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String userPassword;

	@Transient
	private String confirmUserPassword;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean valid;

	@Column(length = 20)
	private String namePrefix;

	@Basic(optional = false)
	@Column(length = 200, nullable = false)
	private String firstName;

	@Basic(optional = false)
	@Column(length = 200, nullable = false)
	private String lastName;

	@Column(length = 20)
	private String namePostfix;

	@Column(length = 200)
	private String position;

	@Basic(optional = false)
	@Column(length = 200, nullable = false)
	private String emailAddress;

	@Column(length = 1000, name = "COMMENT_")
	private String comment;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	// @PrivateOwned
	private Set<UserRole> userRoles;

	@ManyToOne(targetEntity = I18nSupportedLanguage.class, optional = true)
	private I18nSupportedLanguage userLanguage;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastSuccessLoginTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastBadLoginTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastPasswordDate;

	@Column(nullable = false)
	private Long badLoginCount = 0L;

	@Column(nullable = false)
	private Long locked = 0L;

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setConfirmUserPassword(String confirmUserPassword) {
		this.confirmUserPassword = confirmUserPassword;
	}

	public String getConfirmUserPassword() {
		if (confirmUserPassword == null) {
			return userPassword;
		}
		return confirmUserPassword;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNamePostfix() {
		return namePostfix;
	}

	public void setNamePostfix(String namePostfix) {
		this.namePostfix = namePostfix;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public I18nSupportedLanguage getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(I18nSupportedLanguage userLanguage) {
		this.userLanguage = userLanguage;
	}

	public void setLastSuccessLoginTime(Calendar lastSuccessLoginTime) {
		this.lastSuccessLoginTime = lastSuccessLoginTime;
	}

	public Calendar getLastSuccessLoginTime() {
		return lastSuccessLoginTime;
	}

	public void setLastBadLoginTime(Calendar lastBadLoginTime) {
		this.lastBadLoginTime = lastBadLoginTime;
	}

	public Calendar getLastBadLoginTime() {
		return lastBadLoginTime;
	}

	public void setBadLoginCount(Long badLoginCount) {
		this.badLoginCount = badLoginCount;
	}

	public Long getBadLoginCount() {
		return badLoginCount;
	}

	public void setLocked(Long locked) {
		this.locked = locked;
	}

	public Long getLocked() {
		return locked;
	}

	public void setLastPasswordDate(Calendar lastPasswordDate) {
		this.lastPasswordDate = lastPasswordDate;
	}

	public Calendar getLastPasswordDate() {
		return lastPasswordDate;
	}
}