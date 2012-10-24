package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_COMMAND_TYPES", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "PROTOCOL_ID", "NAME", "PROVIDER_ID" }),
		@UniqueConstraint(columnNames = { "PROTOCOL_ID", "CODE", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class CommandType extends EntityBase {
	private static final long serialVersionUID = -2419702649990715642L;

	@Column(nullable = false, length = 20)
	private String code;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(length = 4000)
	private String description;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Protocol
	@ManyToOne
	@JoinColumn(nullable = false)
	private Protocol protocol;

	// bi-directional many-to-one association to CommandType
	@ManyToOne
	private CommandType commandType;

	// bi-directional many-to-one association to CommandParameterType
	@OneToMany(mappedBy = "commandType")
	@OrderBy("listingOrder ASC")
	private List<CommandParameterType> commandParameterTypes;

	// bi-directional many-to-one association to CommandType
	@OneToMany(mappedBy = "commandType")
	private Set<CommandType> commandTypes;

	public CommandType() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public List<CommandParameterType> getCommandParameterTypes() {
		return commandParameterTypes;
	}

	public void setCommandParameterTypes(
			List<CommandParameterType> commandParameterTypes) {
		this.commandParameterTypes = commandParameterTypes;
	}

	public Set<CommandType> getCommandTypes() {
		return commandTypes;
	}

	public void setCommandTypes(Set<CommandType> commandTypes) {
		this.commandTypes = commandTypes;
	}
}