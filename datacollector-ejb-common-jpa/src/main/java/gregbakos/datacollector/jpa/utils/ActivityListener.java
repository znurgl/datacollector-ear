package gregbakos.datacollector.jpa.utils;

import gregbakos.datacollector.IptException;
import gregbakos.datacollector.jpa.dao.Activity;

import java.io.Serializable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class ActivityListener implements Serializable {

	private static final long serialVersionUID = 3126361231806553185L;
	
	@PrePersist
	@PreUpdate
	public void checkDates(Activity activity){
		
		//nem lehet nagyobb az endDate mint a startDate
		if( (activity.getEndingDate() != null && activity.getStartingDate() != null) 
			&& activity.getEndingDate().before( activity.getStartingDate() )){
			throw new IptException("endingDateBiggerThanStartingDate");
		}
		
	}

}
