package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_RESULTS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Result extends EntityBase {

	private static final long serialVersionUID = 5398934194036000979L;

	@Lob()
	private String measured;

	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	@Column(nullable = false)
	private java.util.Calendar resultDate;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Task task;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "RESULTSTAT")
	// Érvényes értékek: CodeGroup.code="RESULTSTAT"
	@JoinColumn(nullable = false)
	private Code resultStatus;

	public Result() {
		super();
	}

	public String getMeasured() {
		return measured;
	}

	public void setMeasured(String measured) {
		this.measured = measured;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getManipulateString() {
		String result = getMeasured();
		result = result.replace('{', ' ');
		result = result.replace('}', ' ');
		result = result.replace(':', '=');
		result = result.replace('"', ' ');
		result = result.replace('\\', ' ');

		result = result.substring(17);

		return result;
	}

	public Code getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Code resultStatus) {
		this.resultStatus = resultStatus;
	}

	public java.util.Calendar getResultDate() {
		return resultDate;
	}

	public void setResultDate(java.util.Calendar resultDate) {
		this.resultDate = resultDate;
	}

}
