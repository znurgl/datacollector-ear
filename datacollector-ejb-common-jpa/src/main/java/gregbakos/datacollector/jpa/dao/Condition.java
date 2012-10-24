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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CONDITIONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Condition extends EntityBase {
	private static final long serialVersionUID = -2975977840989116996L;

	@Column(nullable = false)
	private Double measuredValue;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startingTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar endingTime;

	// bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(nullable = true)
	private Device device;

	// bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(nullable = true)
	private Device communicator;

	// bi-directional many-to-one association to Consumption
	@ManyToOne
	@JoinColumn(nullable = true)
	private Consumption consumption;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "CONDITIONTYPE")
	@JoinColumn(nullable = false)
	private Code conditionType;

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

	public Code getConditionType() {
		return conditionType;
	}

	public void setConditionType(Code conditionType) {
		this.conditionType = conditionType;
	}

	public Condition() {
		super();
	}

	public Consumption getConsumption() {
		return consumption;
	}

	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}

}
