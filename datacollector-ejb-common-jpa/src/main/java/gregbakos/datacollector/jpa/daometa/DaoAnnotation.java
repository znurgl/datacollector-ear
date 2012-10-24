package gregbakos.datacollector.jpa.daometa;

import java.io.Serializable;
import java.util.HashMap;

public class DaoAnnotation extends HashMap<String, Object> implements
		Serializable {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

}
