package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.Set;

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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_PROVISION_TYPES", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"NAME", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class ProvisionType extends EntityBase {
	private static final long serialVersionUID = -2038626265157922876L;

	@Column(nullable = false, length = 100)
	private String name;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Consumption
	@OneToMany(mappedBy = "provisionType")
	private Set<Consumption> consumptions;

	// bi-directional many-to-one association to DeviceType
	@OneToMany(mappedBy = "provisionType")
	private Set<DeviceType> deviceTypes;

	// bi-directional many-to-one association to Provision
	@OneToMany(mappedBy = "provisionType")
	private Set<Provision> provisions;

	// bi-directional many-to-one association to RateTable
	@OneToMany(mappedBy = "provisionType")
	private Set<RateTable> rateTables;

	// bi-directional many-to-one association to RateTable
	@OneToMany(mappedBy = "provisionType")
	private Set<ProvisionTypeValues> provisionTypeValues;

	public ProvisionType() {
		super();
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<Consumption> getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(Set<Consumption> consumptions) {
		this.consumptions = consumptions;
	}

	public Set<DeviceType> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(Set<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public Set<Provision> getProvisions() {
		return provisions;
	}

	public void setProvisions(Set<Provision> provisions) {
		this.provisions = provisions;
	}

	public Set<RateTable> getRateTables() {
		return rateTables;
	}

	public void setRateTables(Set<RateTable> rateTables) {
		this.rateTables = rateTables;
	}

	public Set<ProvisionTypeValues> getProvisionTypeValues() {
		return provisionTypeValues;
	}

	public void setProvisionTypeValues(
			Set<ProvisionTypeValues> provisionTypeValues) {
		this.provisionTypeValues = provisionTypeValues;
	}
}