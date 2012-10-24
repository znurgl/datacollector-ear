package gregbakos.datacollector.jpa.dao;

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
@Table(name = "T_CODE_GROUPS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class CodeGroup extends EntityBase {
	private static final long serialVersionUID = 918705056618524132L;

	@Basic(optional = false)
	@Column(length = 100, unique = true, nullable = false)
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

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = true)
	private Provider provider;

	// bi-directional many-to-one association to Code
	@OneToMany(mappedBy = "codeGroup")
	private Set<Code> codes;

	public CodeGroup() {
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

	public void setI18nComment(String i18nComment) {
		this.i18nComment = i18nComment;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<Code> getCodes() {
		return codes;
	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

}
