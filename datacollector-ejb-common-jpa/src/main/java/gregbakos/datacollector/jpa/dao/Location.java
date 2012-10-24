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

@Entity
@Table(name = "T_LOCATIONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Location extends EntityBase {
	private static final long serialVersionUID = 2078972286563431008L;

	@Column(length = 20)
	private String postalCode;

	@Column(length = 100)
	private String locality;

	@Column(length = 100)
	private String street;

	@Column(length = 20)
	private String streetNumber;

	@Column(length = 100)
	private String lotNumber;

	@Column(length = 20)
	private String stairCase;

	@Column(length = 20)
	private String storey;

	@Column(length = 20)
	private String door;

	@Column(length = 100)
	private String placement;

	@Column(precision = 12, scale = 6)
	private Double latitude;

	@Column(precision = 12, scale = 6)
	private Double longitude;

	// bi-directional many-to-one association to Provider
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Consumption
	@OneToMany(mappedBy = "location")
	private Set<Consumption> consumptions;

	// bi-directional many-to-one association to Customer
	@OneToMany(mappedBy = "location")
	private Set<Customer> customers;

	// bi-directional many-to-one association to CustomerGroup
	@OneToMany(mappedBy = "location")
	private Set<CustomerGroup> customerGroups;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "location")
	private Set<Device> devices;

	// bi-directional many-to-one association to Provision
	@OneToMany(mappedBy = "location")
	private Set<Provision> provisions;

	@OneToMany(mappedBy = "location")
	private Set<Picture> pictures;

	public Location() {
		super();
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getStairCase() {
		return stairCase;
	}

	public void setStairCase(String stairCase) {
		this.stairCase = stairCase;
	}

	public String getStorey() {
		return storey;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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

	public Set<Provision> getProvisions() {
		return provisions;
	}

	public void setProvisions(Set<Provision> provisions) {
		this.provisions = provisions;
	}

	public String getLocationString() {
		StringBuffer sb = new StringBuffer();
		sb.append(postalCode).append(" ");
		sb.append(locality).append(", ");
		sb.append(street).append(" ");
		sb.append(streetNumber);

		if (stairCase != null && stairCase.trim().length() > 0) {
			sb.append("/");
			sb.append(stairCase);
		}
		if (storey != null && storey.trim().length() > 0) {
			sb.append(" ");
			sb.append(storey);
			sb.append(".");
		}
		if ((door != null && door.trim().length() > 0)
				&& (storey != null && storey.trim().length() > 0)) {
			sb.append("/");
		}
		if (door != null && door.trim().length() > 0) {
			sb.append(door);
			sb.append(".");
		}

		return sb.toString();
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

}