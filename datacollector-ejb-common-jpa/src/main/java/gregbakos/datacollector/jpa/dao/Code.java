package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_CODES", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"CODEGROUP_ID", "CODE" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class Code extends EntityBase {
	private static final long serialVersionUID = -4996724490387503563L;

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

	@Column(nullable = true)
	private Double numericValue;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private CodeGroup codeGroup;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Provider provider;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Code parentCode;

	public Code() {
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

	public CodeGroup getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(CodeGroup codeGroup) {
		this.codeGroup = codeGroup;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Double getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}

	public Code getParentCode() {
		return parentCode;
	}

	public void setParentCode(Code parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public String toString() {
		return getI18nCode() + " - " + getI18nName();
	}
}
