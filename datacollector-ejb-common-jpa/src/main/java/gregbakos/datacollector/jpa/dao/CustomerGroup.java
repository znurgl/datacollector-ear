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
@Table(name = "T_CUSTOMER_GROUPS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"NAME", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class CustomerGroup extends EntityBase {
	private static final long serialVersionUID = 7307191615660078967L;

	@Column(nullable = false, length = 100)
	private String name;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	@Column(nullable = true)
	private Long listingOrder;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to CustomerGroup
	@ManyToOne
	private CustomerGroup parentGroup;

	// bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	// bi-directional many-to-one association to CustomerGroup
	@OneToMany(mappedBy = "parentGroup")
	private Set<CustomerGroup> customerGroups;

	// bi-directional many-to-one association to CustomerGrouping
	@OneToMany(mappedBy = "customerGroup")
	private Set<CustomerGrouping> customerGroupings;

	public Set<CustomerGrouping> getCustomerGroupings() {
		return customerGroupings;
	}

	public void setCustomerGroupings(Set<CustomerGrouping> customerGroupings) {
		this.customerGroupings = customerGroupings;
	}

	public Long getListingOrder() {
		return listingOrder;
	}

	public void setListingOrder(Long listingOrder) {
		this.listingOrder = listingOrder;
	}

	public CustomerGroup() {
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

	public CustomerGroup getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(CustomerGroup parentGroup) {
		this.parentGroup = parentGroup;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}

	public void setCustomerGroups(Set<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}
}