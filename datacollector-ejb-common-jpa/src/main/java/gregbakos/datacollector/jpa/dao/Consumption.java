package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CONSUMPTIONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
@NamedQueries({
		@NamedQuery(name = Consumption.QUERY_LAST_CONSUMPTION, query = "select object(c) "
				+ "from Consumption c "
				+ "where c.device = :device and "
				+ "c.valueType = :valueType and "
				+ "c.endingTime < :toDate "
				+ "order by c.endingTime desc"),
		@NamedQuery(name = Consumption.QUERY_LAST_CONSUMPTION_IN_RANGE, query = "select object(c) "
				+ "from Consumption c "
				+ "where c.device = :device and "
				+ "c.valueType = :valueType and "
				+ "c.endingTime >= :fromDate and "
				+ "c.endingTime < :toDate "
				+ "order by c.endingTime desc"),
		@NamedQuery(name = Consumption.QUERY_NEXT_CONSUMPTION, query = "select object(c) "
				+ "from Consumption c "
				+ "where c.device = :device and "
				+ "c.valueType = :valueType and "
				+ "c.endingTime > :fromDate "
				+ "order by c.endingTime asc") })
public class Consumption extends EntityBase {
	private static final long serialVersionUID = 7890580702554710692L;

	public static final String QUERY_LAST_CONSUMPTION = "Consumption.lastConsumption";

	public static final String QUERY_NEXT_CONSUMPTION = "Consumption.nextConsumption";

	public static final String QUERY_LAST_CONSUMPTION_IN_RANGE = "Consumption.lastConsumptionInRange";

	@Column(nullable = false)
	private Double measuredValue;

	@Column(nullable = true)
	private Double differenceValue;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startingTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar endingTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar postingTime;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;

	// bi-directional many-to-one association to ProvisionType
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProvisionType provisionType;

	// bi-directional many-to-one association to Provision
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provision provision;

	// bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(nullable = false)
	private Location location;

	// bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(nullable = false)
	private Device device;

	// bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(nullable = false)
	private Device communicator;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ERTEKTIPUS")
	@JoinColumn(nullable = false)
	private Code valueType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ENTRY_REASON")
	@JoinColumn(nullable = true)
	private Code entryReason;

	@OneToMany(mappedBy = "consumption")
	private Set<Condition> conditions;

	public Consumption() {
		super();
	}

	public Code getValueType() {
		return valueType;
	}

	public void setValueType(Code valueType) {
		this.valueType = valueType;
	}

	public Double getMeasuredValue() {
		return measuredValue;
	}

	public void setMeasuredValue(Double measuredValue) {
		this.measuredValue = measuredValue;
	}

	public Calendar getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Calendar startingTime) {
		this.startingTime = startingTime;
	}

	public Calendar getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Calendar endingTime) {
		this.endingTime = endingTime;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ProvisionType getProvisionType() {
		return provisionType;
	}

	public void setProvisionType(ProvisionType provisionType) {
		this.provisionType = provisionType;
	}

	public Provision getProvision() {
		return provision;
	}

	public void setProvision(Provision provision) {
		this.provision = provision;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Device getCommunicator() {
		return communicator;
	}

	public void setCommunicator(Device communicator) {
		this.communicator = communicator;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}

	public Calendar getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Calendar postingTime) {
		this.postingTime = postingTime;
	}

	public Double getDifferenceValue() {
		return differenceValue;
	}

	public void setDifferenceValue(Double differenceValue) {
		this.differenceValue = differenceValue;
	}

	public Code getEntryReason() {
		return entryReason;
	}

	public void setEntryReason(Code entryReason) {
		this.entryReason = entryReason;
	}

}