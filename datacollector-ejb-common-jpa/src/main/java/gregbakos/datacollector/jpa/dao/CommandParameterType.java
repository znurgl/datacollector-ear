package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_COMMAND_PARAMETER_TYPES", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "COMMANDTYPE_ID", "LISTINGORDER",
				"PROVIDER_ID" }),
		@UniqueConstraint(columnNames = { "COMMANDTYPE_ID", "CODE",
				"PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class CommandParameterType extends EntityBase {
	private static final long serialVersionUID = 2650589574546505061L;

	@Column(nullable = false)
	private Integer listingOrder;

	@Column(nullable = false, length = 100)
	private String code;

	@Column(length = 4000)
	private String description;

	private Integer dataLength;

	@Column(name = "FORMAT_STRING", length = 20)
	private String formatString;

	@Column(length = 100)
	private String defaultValue;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to CommandType
	@ManyToOne
	@JoinColumn(nullable = false)
	private CommandType commandType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "PARTIP")
	@JoinColumn(nullable = false)
	private Code dataType;

	public Code getDataType() {
		return dataType;
	}

	public void setDataType(Code dataType) {
		this.dataType = dataType;
	}

	public CommandParameterType() {
		super();
	}

	public Integer getListingOrder() {
		return listingOrder;
	}

	public void setListingOrder(Integer listingOrder) {
		this.listingOrder = listingOrder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}
}