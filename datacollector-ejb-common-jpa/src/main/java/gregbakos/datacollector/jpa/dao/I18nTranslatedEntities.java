package gregbakos.datacollector.jpa.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_I18N_ENTITY")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class I18nTranslatedEntities extends EntityBase {
	private static final long serialVersionUID = -5782691154589771248L;

	@Basic(optional = false)
	@Column(unique = true, nullable = false)
	private String entityName;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Transient
	private String displayName = null;

	public String toString() {
		return displayName == null ? entityName : displayName;
	}
}
