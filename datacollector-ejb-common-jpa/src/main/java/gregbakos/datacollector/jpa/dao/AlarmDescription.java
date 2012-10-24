package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "T_ALARM_DESCRIPTIONS")
public class AlarmDescription extends EntityBase implements Serializable {
	private static final long serialVersionUID = 6854716820382926268L;

	@Column(nullable = false, length = 300)
	private String descriptionPath;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar descriptionDate;

	// bi-directional many-to-one association to AlarmEvent
	@ManyToOne
	@JoinColumn(nullable = false)
	private AlarmEvent alarmEvent;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ALARM_DESC_TYPE")
	@JoinColumn(nullable = false)
	private Code descriptionType;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "MIME_TYPE")
	@JoinColumn(nullable = false)
	private Code mimeType;

	@Transient
	private String fileSize;

	public AlarmDescription() {
		super();
	}

	public String getDescriptionPath() {
		return descriptionPath;
	}

	public void setDescriptionPath(String descriptionPath) {
		this.descriptionPath = descriptionPath;
	}

	public Calendar getDescriptionDate() {
		return descriptionDate;
	}

	public void setDescriptionDate(Calendar descriptionDate) {
		this.descriptionDate = descriptionDate;
	}

	public AlarmEvent getAlarmEvent() {
		return alarmEvent;
	}

	public void setAlarmEvent(AlarmEvent alarmEvent) {
		this.alarmEvent = alarmEvent;
	}

	public Code getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(Code descriptionType) {
		this.descriptionType = descriptionType;
	}

	public Code getMimeType() {
		return mimeType;
	}

	public void setMimeType(Code mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}
