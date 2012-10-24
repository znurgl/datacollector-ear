package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_PROVISION_TYPE_VALUES", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"VALUETYPE_ID", "PROVISIONTYPE_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class ProvisionTypeValues extends EntityBase {
	private static final long serialVersionUID = -4856621812519583253L;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean rated;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean sent;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean rolled;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProvisionType provisionType;

	// bi-directional many-to-one association to Code
	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "VALUETYPE")
	// Érvényes értékek: CodeGroup.code="VALUETYPE"
	@JoinColumn(nullable = false)
	private Code valueType;

	// bi-directional many-to-one association to Code
	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ALAPME")
	@JoinColumn(nullable = false)
	private Code unit;

	public ProvisionTypeValues() {
		super();
	}

	public Boolean getRated() {
		return rated;
	}

	public void setRated(Boolean rated) {
		this.rated = rated;
	}

	public Boolean getSent() {
		return sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

	public Boolean getRolled() {
		return rolled;
	}

	public void setRolled(Boolean rolled) {
		this.rolled = rolled;
	}

	public ProvisionType getProvisionType() {
		return provisionType;
	}

	public void setProvisionType(ProvisionType provisionType) {
		this.provisionType = provisionType;
	}

	public Code getValueType() {
		return valueType;
	}

	public void setValueType(Code valueType) {
		this.valueType = valueType;
	}

	public Code getUnit() {
		return unit;
	}

	public void setUnit(Code unit) {
		this.unit = unit;
	}

}