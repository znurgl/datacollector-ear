package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_CUSTOMERS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"CUSTOMERNUMBER", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Customer extends EntityBase {
	private static final long serialVersionUID = 1825998980363754480L;

	@Column(length = 100)
	private String customerNumber;

	@Column(nullable = false, length = 100)
	private String name;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(nullable = false)
	private Location location;

	// bi-directional many-to-one association to Consumption
	@OneToMany(mappedBy = "customer")
	private Set<Consumption> consumptions;

	// bi-directional many-to-one association to ConsumptionSery
	@OneToMany(mappedBy = "customer")
	private Set<ConsumptionSeries> consumptionSeries;

	// bi-directional many-to-one association to Contract
	@OneToMany(mappedBy = "customer")
	private Set<Contract> contracts;

	// bi-directional many-to-one association to CustomerGrouping
	@OneToMany(mappedBy = "customer")
	private Set<CustomerGrouping> customerGroupings;

	// bi-directional many-to-one association to CustomerGrouping
	@OneToMany(mappedBy = "customer")
	private Set<CustomerAttribute> customerAttributes;

	public Set<CustomerAttribute> getCustomerAttributes() {
		return customerAttributes;
	}

	public void setCustomerAttributes(Set<CustomerAttribute> customerAttributes) {
		this.customerAttributes = customerAttributes;
	}

	public Set<CustomerGrouping> getCustomerGroupings() {
		return customerGroupings;
	}

	public void setCustomerGroupings(Set<CustomerGrouping> customerGroupings) {
		this.customerGroupings = customerGroupings;
	}

	public Customer() {
		super();
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Consumption> getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(Set<Consumption> consumptions) {
		this.consumptions = consumptions;
	}

	public Set<ConsumptionSeries> getConsumptionSeries() {
		return consumptionSeries;
	}

	public void setConsumptionSeries(Set<ConsumptionSeries> consumptionSeries) {
		this.consumptionSeries = consumptionSeries;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}
}