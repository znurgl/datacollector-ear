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
@Table(name = "T_COMM_EVENT_LOGS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CommEventLog extends EntityBase {
	private static final long serialVersionUID = -1752515231983770195L;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar eventDate;

	@Column(length = 1000)
	private String errorMessage;

	@ManyToOne
	@JoinColumn(nullable = true)
	private FrontServer frontServer;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Device communicator;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "COMMEVENT")
	@JoinColumn(nullable = false)
	private Code commEvent;

	public CommEventLog() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public FrontServer getFrontServer() {
		return frontServer;
	}

	public void setFrontServer(FrontServer frontServer) {
		this.frontServer = frontServer;
	}

	public Device getCommunicator() {
		return communicator;
	}

	public void setCommunicator(Device communicator) {
		this.communicator = communicator;
	}

	public Code getCommEvent() {
		return commEvent;
	}

	public void setCommEvent(Code commEvent) {
		this.commEvent = commEvent;
	}

	public Calendar getEventDate() {
		return eventDate;
	}

	public void setEventDate(Calendar eventDate) {
		this.eventDate = eventDate;
	}

}
