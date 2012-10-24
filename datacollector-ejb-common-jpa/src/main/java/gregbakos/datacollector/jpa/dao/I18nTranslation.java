package gregbakos.datacollector.jpa.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_I18N_TRANSLATIONS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"TRANSLATED_ENTITY_ID", "ENTITY_ID", "I18NSUPPORTEDLANGUAGE_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class I18nTranslation extends EntityBase {
	private static final long serialVersionUID = -8572251393011273611L;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, name = "TRANSLATED_ENTITY_ID")
	private I18nTranslatedEntities entity;

	@Basic(optional = false)
	@Column(nullable = false, name = "ENTITY_ID")
	private Long entityId;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private I18nSupportedLanguage i18nSupportedLanguage;

	@Lob
	private String translations;

	public I18nTranslation() {
		super();
	}

	public I18nTranslatedEntities getEntity() {
		return entity;
	}

	public void setEntity(I18nTranslatedEntities entity) {
		this.entity = entity;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public I18nSupportedLanguage getI18nSupportedLanguage() {
		return i18nSupportedLanguage;
	}

	public void setI18nSupportedLanguage(
			I18nSupportedLanguage i18nSupportedLanguage) {
		this.i18nSupportedLanguage = i18nSupportedLanguage;
	}

	public String getTranslations() {
		return translations;
	}

	public void setTranslations(String translations) {
		this.translations = translations;
	}

}
