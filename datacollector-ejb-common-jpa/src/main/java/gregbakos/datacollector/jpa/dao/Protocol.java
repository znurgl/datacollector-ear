package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_PROTOCOLS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "NAME", "PROVIDER_ID" }),
		@UniqueConstraint(columnNames = { "CODE", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Protocol extends EntityBase {
	private static final long serialVersionUID = -7455725793876557178L;

	@Column(nullable = false, length = 20)
	private String code;

	@Column(nullable = false, length = 100)
	private String name;

	@Lob()
	@Column(name = "COMMENT_")
	private String comment;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to CommandType
	@OneToMany(mappedBy = "protocol")
	private Set<CommandType> commandTypes;

	// bi-directional many-to-one association to DeviceType
	@OneToMany(mappedBy = "protocol")
	private Set<DeviceType> deviceTypes;

	public Protocol() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<CommandType> getCommandTypes() {
		return commandTypes;
	}

	public void setCommandTypes(Set<CommandType> commandTypes) {
		this.commandTypes = commandTypes;
	}

	public Set<DeviceType> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(Set<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}
}