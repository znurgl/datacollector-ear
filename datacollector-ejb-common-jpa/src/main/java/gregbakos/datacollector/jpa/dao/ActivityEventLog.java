package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_ACTIVITY_EVENT_LOGS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ActivityEventLog extends EntityBase {
	private static final long serialVersionUID = 2370228416439853391L;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar eventDate;

	@Column(length = 1000)
	private String errorMessage;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Activity activity;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Device device;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "ACTIVITYEVENT")
	@JoinColumn(nullable = false)
	private Code activityEvent;

	public ActivityEventLog() {
		super();
	}

	public Calendar getEventDate() {
		return eventDate;
	}

	public void setEventDate(Calendar eventDate) {
		this.eventDate = eventDate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Code getActivityEvent() {
		return activityEvent;
	}

	public void setActivityEvent(Code actEvent) {
		this.activityEvent = actEvent;
	}

}
