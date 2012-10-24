package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CONSUMPTION_SERIES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class ConsumptionSeries extends EntityBase {
	private static final long serialVersionUID = 4088093390213126580L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar startingTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar endingTime;

	@Lob()
	@Column(nullable = false)
	private String measuredSerial;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;

	// bi-directional many-to-one association to Provision
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provision provision;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ERTEKTIPUS")
	@JoinColumn(nullable = false)
	private Code valueType;

	public ConsumptionSeries() {
		super();
	}

	public Calendar getStartingTime() {
		return startingTime;
	}

	public Code getValueType() {
		return valueType;
	}

	public void setValueType(Code valueType) {
		this.valueType = valueType;
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

	public String getMeasuredSerial() {
		return measuredSerial;
	}

	public void setMeasuredSerial(String measuredSerial) {
		this.measuredSerial = measuredSerial;
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

	public Provision getProvision() {
		return provision;
	}

	public void setProvision(Provision provision) {
		this.provision = provision;
	}

	public String getMeasuredTime() {
		if (this.getMeasuredSerial() != null) {
			String[] splitArray = this.getMeasuredSerial().split(";");
			if (splitArray.length > 0) {
				return splitArray[0];
			}
		}
		return "";
	}

	public String getMeasuredValue() {
		if (this.getMeasuredSerial() != null) {
			String[] splitArray = this.getMeasuredSerial().split(";");
			if (splitArray.length > 1) {
				return splitArray[1];
			}
		}
		return "";
	}
}