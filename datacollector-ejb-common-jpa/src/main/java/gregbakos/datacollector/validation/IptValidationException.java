package gregbakos.datacollector.validation;

public class IptValidationException extends RuntimeException{

	private static final long serialVersionUID = 9066098269275024146L;
	private final String reason;
	
	public IptValidationException(String reason, Throwable cause) {
		super(reason, cause);
		this.reason = reason;
	}

	public IptValidationException(String reason) {
		super(reason);
		this.reason = reason;
	}

	public String getReason() {
		return "validation."+reason;
	}

}
