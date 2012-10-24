package gregbakos.datacollector.jpa.dao;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_I18N_SUPPORTED_LANGUAGES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class I18nSupportedLanguage extends EntityBase {
	private static final long serialVersionUID = -8759833572543894955L;

	@Basic(optional = false)
	@Column(length = 10, nullable = false, unique = true)
	private String languageCode;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String languageName;

	@OneToMany(mappedBy = "userLanguage")
	private Set<User> users;

	public I18nSupportedLanguage() {
		super();
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return languageName;
	}

}
