package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_LOGIN_LOGS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ProviderCheckEntityListener.class })
public class LoginLog extends EntityBase{
	private static final long serialVersionUID = -8501490982329168404L;

	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String userName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar loginTime;
	
	@Column(length = 100)
	private String ip;
	
	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String succeeded;
	
	public LoginLog() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Calendar getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Calendar loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSucceeded() {
		return succeeded;
	}

	public void setSucceeded(String succeeded) {
		this.succeeded = succeeded;
	}
   
	
}
