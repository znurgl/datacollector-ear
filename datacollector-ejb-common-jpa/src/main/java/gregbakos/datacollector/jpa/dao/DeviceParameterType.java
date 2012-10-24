package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

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

@Entity
@Table(name = "T_DEVICE_PARAMETER_TYPES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class DeviceParameterType extends EntityBase {
	private static final long serialVersionUID = 6965971379322086652L;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(length = 20)
	private String formatString;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean readOnly;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "PARTIP")
	@JoinColumn(nullable = false)
	private Code dataType;

	// bi-directional many-to-one association to DeviceParameterTable
	@OneToMany(mappedBy = "deviceParameterType")
	private Set<DeviceParameterTable> deviceParameterTables;

	@OneToMany(mappedBy = "deviceParameterType")
	private Set<DeviceTypeParameter> deviceTypeParameters;

	public Code getDataType() {
		return dataType;
	}

	public void setDataType(Code dataType) {
		this.dataType = dataType;
	}

	public Set<DeviceTypeParameter> getDeviceTypeParameters() {
		return deviceTypeParameters;
	}

	public void setDeviceTypeParameters(
			Set<DeviceTypeParameter> deviceTypeParameters) {
		this.deviceTypeParameters = deviceTypeParameters;
	}

	public DeviceParameterType() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<DeviceParameterTable> getDeviceParameterTables() {
		return deviceParameterTables;
	}

	public void setDeviceParameterTables(
			Set<DeviceParameterTable> deviceParameterTables) {
		this.deviceParameterTables = deviceParameterTables;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

}