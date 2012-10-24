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
@Table(name = "T_RATE_ITEMS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class RateItem extends EntityBase {
	private static final long serialVersionUID = -1261381756974690774L;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startingDay;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endingDay;

	private Integer startingMinute;

	private Integer endingMinute;

	private Double lowerLimit;

	private Double upperLimit;

	@Column(nullable = false)
	private Double rate;

	@ManyToOne
	@JoinColumn(nullable = false)
	private RateTable rateTable;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "VALUETYPE")
	@JoinColumn(nullable = true)
	private Code valueType;

	public RateItem() {
		super();
	}

	public Calendar getStartingDay() {
		return startingDay;
	}

	public void setStartingDay(Calendar startingDay) {
		this.startingDay = startingDay;
	}

	public Calendar getEndingDay() {
		return endingDay;
	}

	public void setEndingDay(Calendar endingDay) {
		this.endingDay = endingDay;
	}

	public Integer getStartingMinute() {
		return startingMinute;
	}

	public void setStartingMinute(Integer startingMinute) {
		this.startingMinute = startingMinute;
	}

	public Integer getEndingMinute() {
		return endingMinute;
	}

	public void setEndingMinute(Integer endingMinute) {
		this.endingMinute = endingMinute;
	}

	public Double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	/*
	 * public Provider getProvider() { return provider; }
	 * 
	 * public void setProvider(Provider provider) { this.provider = provider; }
	 */
	public RateTable getRateTable() {
		return rateTable;
	}

	public void setRateTable(RateTable rateTable) {
		this.rateTable = rateTable;
	}

	public void setValueType(Code valueType) {
		this.valueType = valueType;
	}

	public Code getValueType() {
		return valueType;
	}
}