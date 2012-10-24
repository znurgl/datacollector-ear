package gregbakos.datacollector;


public class IptException extends RuntimeException{

	private final String reason;
	
	public IptException(String reason, Throwable cause) {
		super(reason, cause);
		this.reason = reason;
	}

	public IptException(String reason) {
		super(reason);
		this.reason = reason;
	}

	public String getReason() {
		return reason == null ? null : (reason);
	}

}

