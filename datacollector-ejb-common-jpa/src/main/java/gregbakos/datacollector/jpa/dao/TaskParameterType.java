package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_TASK_PARAMETER_TYPES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class TaskParameterType extends EntityBase {
	private static final long serialVersionUID = 6640296421599681500L;

	@Column(nullable = false)
	private Integer listingOrder;

	@Column(nullable = false, length = 300)
	private String name;

	@Column(length = 20)
	private String formatString;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to TaskType
	@ManyToOne
	@JoinColumn(nullable = false)
	private TaskType taskType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "PARTIP")
	// Érvényes értékek: CodeGroup.code="PARTIP"
	@JoinColumn(nullable = false)
	private Code dataType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "PARIRANY")
	// Érvényes értékek: CodeGroup.code="PARIRANY"
	@JoinColumn(nullable = false)
	private Code inOutType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "VALUETYPE")
	// Érvényes értékek: CodeGroup.code="VALUETYPE"
	@JoinColumn(nullable = true)
	private Code outValueType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "CONDITIONTYPE")
	// Érvényes értékek: CodeGroup.code="CONDITIONTYPE"
	@JoinColumn(nullable = true)
	private Code outConditionType;

	/**
	 * Y: évszám karakter, ha nem négy karakter akkor hátulról: pl.: 2010 esetén
	 * YYY = 010; M: hónapszám karakter D: napszám karakter H: óraszám karakter
	 * 24 órás formátumban I: percszám karakter S: másodperc szám karakter
	 */
	@Column(nullable = true, length = 30)
	private String dateValuePart;

	@Column(nullable = true, length = 100)
	private String answerRowObjectGet;

	@Basic(optional = false)
	@Column(nullable = false)
	private Boolean differenceValue;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Code outValueUnit;

	public void setDataType(Code dataType) {
		this.dataType = dataType;
	}

	public TaskParameterType() {
		super();
	}

	public Integer getListingOrder() {
		return listingOrder;
	}

	public void setListingOrder(Integer listingOrder) {
		this.listingOrder = listingOrder;
	}

	public Code getDataType() {
		return dataType;
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

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public Code getInOutType() {
		return inOutType;
	}

	public void setInOutType(Code inOutType) {
		this.inOutType = inOutType;
	}

	public Code getOutValueType() {
		return outValueType;
	}

	public void setOutValueType(Code outValueType) {
		this.outValueType = outValueType;
	}

	public Code getOutValueUnit() {
		return outValueUnit;
	}

	public void setOutValueUnit(Code outValueUnit) {
		this.outValueUnit = outValueUnit;
	}

	public Code getOutConditionType() {
		return outConditionType;
	}

	public void setOutConditionType(Code outConditionType) {
		this.outConditionType = outConditionType;
	}

	public String getDateValuePart() {
		return dateValuePart;
	}

	public void setDateValuePart(String dateValuePart) {
		this.dateValuePart = dateValuePart;
	}

	public String getAnswerRowObjectGet() {
		return answerRowObjectGet;
	}

	public void setAnswerRowObjectGet(String answerRowObjectGet) {
		this.answerRowObjectGet = answerRowObjectGet;
	}

	public Boolean getDifferenceValue() {
		return differenceValue;
	}

	public void setDifferenceValue(Boolean differenceValue) {
		this.differenceValue = differenceValue;
	}

}