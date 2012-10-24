package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.IptException;
import gregbakos.datacollector.jpa.dao.User;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EmailListener implements Serializable {

	@PrePersist
	@PreUpdate
	public void validateEmailAddress(User user) {
		if (user != null && user.getEmailAddress() != null) {
			Pattern pattern = Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(user.getEmailAddress());
			if (!matcher.matches()) {
				throw new IptException("emailAddressValidation");
			}
			return;
		}
		throw new IptException("emailAddressValidationError");
	}
}
