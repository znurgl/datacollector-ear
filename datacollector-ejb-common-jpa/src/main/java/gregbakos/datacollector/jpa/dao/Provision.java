package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PROVISIONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Provision extends EntityBase {
	private static final long serialVersionUID = -7379315153263567360L;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Contract
	@ManyToOne
	@JoinColumn(nullable = false)
	private Contract contract;

	// bi-directional many-to-one association to ProvisionType
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProvisionType provisionType;

	// bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(nullable = false)
	private Location location;

	// bi-directional many-to-one association to RateTable
	@ManyToOne
	@JoinColumn(nullable = true)
	private RateTable rateTable;

	// bi-directional many-to-one association to Consumption
	@OneToMany(mappedBy = "provision")
	private Set<Consumption> consumptions;

	// bi-directional many-to-one association to ConsumptionSery
	@OneToMany(mappedBy = "provision")
	private Set<ConsumptionSeries> consumptionSeries;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "provision")
	private Set<Device> devices;

	public Provision() {
		super();
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public ProvisionType getProvisionType() {
		return provisionType;
	}

	public void setProvisionType(ProvisionType provisionType) {
		this.provisionType = provisionType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public RateTable getRateTable() {
		return rateTable;
	}

	public void setRateTable(RateTable rateTable) {
		this.rateTable = rateTable;
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

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public String getDisplay() {
		return getProvisionType().getI18nName() + " "
				+ getLocation().getLocationString();
	}
}