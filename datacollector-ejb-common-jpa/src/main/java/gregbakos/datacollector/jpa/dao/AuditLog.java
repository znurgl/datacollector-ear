package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_AUDIT_LOGS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = AuditLog.QUERY_DELETE, query = "delete from AuditLog a where a.logEntityName = :entityName and a.logEntityId = :entityId")
public class AuditLog extends EntityBase {
	private static final long serialVersionUID = -5803317676839996623L;

	public static final String QUERY_DELETE = "AuditLog.delete";

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "AUDIT_EVENT")
	@JoinColumn(nullable = false)
	private Code logOperationCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	@Column(nullable = false)
	private java.util.Calendar logTimestamp;

	@Basic(optional = false)
	@Column(length = 500, nullable = false)
	private String logEntityName;

	@Basic(optional = false)
	@Column(nullable = false, name = "LOG_ENTITY_ID")
	private Long logEntityId;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private User logUser;

	public AuditLog() {
		super();
	}

	public Code getLogOperationCode() {
		return logOperationCode;
	}

	public void setOperationCode(Code operationCode) {
		this.logOperationCode = operationCode;
	}

	public java.util.Calendar getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(java.util.Calendar logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogEntityName() {
		return logEntityName;
	}

	public void setLogEntityName(String entityName) {
		this.logEntityName = entityName;
	}

	public Long getLogEntityId() {
		return logEntityId;
	}

	public void setLogEntityId(Long logEntityId) {
		this.logEntityId = logEntityId;
	}

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

}
