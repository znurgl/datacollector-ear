package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_PROVIDERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class })
public class Provider extends EntityBase {
	private static final long serialVersionUID = -4490129751030289528L;

	@Column(nullable = false, length = 100)
	private String name;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	// bi-directional many-to-one association to Location
	@OneToMany(mappedBy = "provider")
	private Set<Location> locations;

	// bi-directional many-to-one association to Activity
	@OneToMany(mappedBy = "provider")
	private Set<Activity> activities;

	// bi-directional many-to-one association to ActivityType
	@OneToMany(mappedBy = "provider")
	private Set<ActivityType> activityTypes;

	// bi-directional many-to-one association to Code
	@OneToMany(mappedBy = "provider")
	private Set<Code> codes;

	// bi-directional many-to-one association to CodeGroup
	@OneToMany(mappedBy = "provider")
	private Set<CodeGroup> codeGroups;

	// bi-directional many-to-one association to CommandParameterType
	@OneToMany(mappedBy = "provider")
	private Set<CommandParameterType> commandParameterTypes;

	// bi-directional many-to-one association to CommandType
	@OneToMany(mappedBy = "provider")
	private Set<CommandType> commandTypes;

	// bi-directional many-to-one association to Consumption
	@OneToMany(mappedBy = "provider")
	private Set<Consumption> consumptions;

	// bi-directional many-to-one association to ConsumptionSery
	@OneToMany(mappedBy = "provider")
	private Set<ConsumptionSeries> consumptionSeries;

	// bi-directional many-to-one association to Contract
	@OneToMany(mappedBy = "provider")
	private Set<Contract> contracts;

	// bi-directional many-to-one association to Customer
	@OneToMany(mappedBy = "provider")
	private Set<Customer> customers;

	// bi-directional many-to-one association to CustomerGroup
	@OneToMany(mappedBy = "provider")
	private Set<CustomerGroup> customerGroups;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "provider")
	private Set<Device> devices;

	// bi-directional many-to-one association to DeviceGroup
	@OneToMany(mappedBy = "provider")
	private Set<DeviceGroup> deviceGroups;

	// bi-directional many-to-one association to DeviceParameterTable
	@OneToMany(mappedBy = "provider")
	private Set<DeviceParameterTable> deviceParameterTables;

	// bi-directional many-to-one association to DeviceParameterType
	@OneToMany(mappedBy = "provider")
	private Set<DeviceParameterType> deviceParameterTypes;

	// bi-directional many-to-one association to DeviceType
	@OneToMany(mappedBy = "provider")
	private Set<DeviceType> deviceTypes;

	// bi-directional many-to-one association to Protocol
	@OneToMany(mappedBy = "provider")
	private Set<Protocol> protocols;

	// bi-directional many-to-one association to Provision
	@OneToMany(mappedBy = "provider")
	private Set<Provision> provisions;

	// bi-directional many-to-one association to ProvisionType
	@OneToMany(mappedBy = "provider")
	private Set<ProvisionType> provisionTypes;

	// bi-directional many-to-one association to RateTable
	@OneToMany(mappedBy = "provider")
	private Set<RateTable> rateTables;

	// bi-directional many-to-one association to Task
	@OneToMany(mappedBy = "provider")
	private Set<Task> tasks;

	// bi-directional many-to-one association to TaskParameterType
	@OneToMany(mappedBy = "provider")
	private Set<TaskParameterType> taskParameterTypes;

	// bi-directional many-to-one association to TaskType
	@OneToMany(mappedBy = "provider")
	private Set<TaskType> taskTypes;

	// bi-directional many-to-one association to User
	@OneToMany(mappedBy = "provider")
	private Set<User> users;

	// bi-directional many-to-one association to User
	@OneToMany(mappedBy = "provider")
	private Set<Alarm> alarms;

	public Provider() {
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

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<ActivityType> getActivityTypes() {
		return activityTypes;
	}

	public void setActivityTypes(Set<ActivityType> activityTypes) {
		this.activityTypes = activityTypes;
	}

	public Set<Code> getCodes() {
		return codes;
	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

	public Set<CodeGroup> getCodeGroups() {
		return codeGroups;
	}

	public void setCodeGroups(Set<CodeGroup> codeGroups) {
		this.codeGroups = codeGroups;
	}

	public Set<CommandParameterType> getCommandParameterTypes() {
		return commandParameterTypes;
	}

	public void setCommandParameterTypes(
			Set<CommandParameterType> commandParameterTypes) {
		this.commandParameterTypes = commandParameterTypes;
	}

	public Set<CommandType> getCommandTypes() {
		return commandTypes;
	}

	public void setCommandTypes(Set<CommandType> commandTypes) {
		this.commandTypes = commandTypes;
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

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Set<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}

	public void setCustomerGroups(Set<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<DeviceGroup> getDeviceGroups() {
		return deviceGroups;
	}

	public void setDeviceGroups(Set<DeviceGroup> deviceGroups) {
		this.deviceGroups = deviceGroups;
	}

	public Set<DeviceParameterTable> getDeviceParameterTables() {
		return deviceParameterTables;
	}

	public void setDeviceParameterTables(
			Set<DeviceParameterTable> deviceParameterTables) {
		this.deviceParameterTables = deviceParameterTables;
	}

	public Set<DeviceParameterType> getDeviceParameterTypes() {
		return deviceParameterTypes;
	}

	public void setDeviceParameterTypes(
			Set<DeviceParameterType> deviceParameterTypes) {
		this.deviceParameterTypes = deviceParameterTypes;
	}

	public Set<DeviceType> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(Set<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public Set<Protocol> getProtocols() {
		return protocols;
	}

	public void setProtocols(Set<Protocol> protocols) {
		this.protocols = protocols;
	}

	public Set<Provision> getProvisions() {
		return provisions;
	}

	public void setProvisions(Set<Provision> provisions) {
		this.provisions = provisions;
	}

	public Set<ProvisionType> getProvisionTypes() {
		return provisionTypes;
	}

	public void setProvisionTypes(Set<ProvisionType> provisionTypes) {
		this.provisionTypes = provisionTypes;
	}

	/*
	 * public Set<RateItem> getRateItems() { return rateItems; }
	 * 
	 * public void setRateItems(Set<RateItem> rateItems) { this.rateItems =
	 * rateItems; }
	 */
	public Set<RateTable> getRateTables() {
		return rateTables;
	}

	public void setRateTables(Set<RateTable> rateTables) {
		this.rateTables = rateTables;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<TaskParameterType> getTaskParameterTypes() {
		return taskParameterTypes;
	}

	public void setTaskParameterTypes(Set<TaskParameterType> taskParameterTypes) {
		this.taskParameterTypes = taskParameterTypes;
	}

	public Set<TaskType> getTaskTypes() {
		return taskTypes;
	}

	public void setTaskTypes(Set<TaskType> taskTypes) {
		this.taskTypes = taskTypes;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return getId() + " - " + getName();
	}

	public Set<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(Set<Alarm> alarms) {
		this.alarms = alarms;
	}

}