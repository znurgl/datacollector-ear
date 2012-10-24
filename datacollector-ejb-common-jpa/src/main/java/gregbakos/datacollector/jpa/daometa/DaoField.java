package gregbakos.datacollector.jpa.daometa;

import java.io.Serializable;
import java.util.HashMap;

public class DaoField implements Serializable{
	private HashMap<String, DaoAnnotation> annotations = new HashMap<String, DaoAnnotation>();
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, DaoAnnotation> getAnnotations() {
		return annotations;
	}

}
