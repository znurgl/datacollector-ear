package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
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
@Table(name = "T_DEVICE_GROUPS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"NAME", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class DeviceGroup extends EntityBase {
	private static final long serialVersionUID = 1201242638876878748L;

	public Set<DeviceGrouping> getDeviceGroupings() {
		return deviceGroupings;
	}

	public void setDeviceGroupings(Set<DeviceGrouping> deviceGroupings) {
		this.deviceGroupings = deviceGroupings;
	}

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = true)
	private Long listingOrder;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean measurable;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to DeviceGroup
	@ManyToOne
	private DeviceGroup parentGroup;

	// bi-directional many-to-one association to Activity
	@OneToMany(mappedBy = "deviceGroup")
	private Set<Activity> activities;

	// bi-directional many-to-one association to DeviceGroup
	@OneToMany(mappedBy = "parentGroup")
	private List<DeviceGroup> deviceGroups;

	@OneToMany(mappedBy = "deviceGroup")
	private Set<DeviceGrouping> deviceGroupings;

	public DeviceGroup() {
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

	public DeviceGroup getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(DeviceGroup parentGroup) {
		this.parentGroup = parentGroup;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public List<DeviceGroup> getDeviceGroups() {
		return deviceGroups;
	}

	public void setDeviceGroups(List<DeviceGroup> deviceGroups) {
		this.deviceGroups = deviceGroups;
	}

	public Long getListingOrder() {
		return listingOrder;
	}

	public void setListingOrder(Long listingOrder) {
		this.listingOrder = listingOrder;
	}

	public Boolean getMeasurable() {
		return measurable;
	}

	public void setMeasurable(Boolean measurable) {
		this.measurable = measurable;
	}

	// származtatott értékek

	public String getDisplayName() {
		return (provider == null ? "" : provider.getI18nName() + " - ")
				+ i18nName;
	}
}