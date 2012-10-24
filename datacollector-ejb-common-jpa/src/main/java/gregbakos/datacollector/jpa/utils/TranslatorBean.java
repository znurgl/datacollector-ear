package gregbakos.datacollector.jpa.utils;

import java.io.Serializable;

public class TranslatorBean implements Serializable {

	private static final long serialVersionUID = 3825400125350878531L;

	private String translatableAttribute;

	private String translatableValue;

	private String destinationAttribute;

	private boolean translated = false;

	public String getTranslatableAttribute() {
		return translatableAttribute;
	}

	public void setTranslatableAttribute(String translatableAttribute) {
		this.translatableAttribute = translatableAttribute;
	}

	public String getTranslatableValue() {
		return translatableValue;
	}

	public void setTranslatableValue(String translatableValue) {
		this.translatableValue = translatableValue;
	}

	public String getDestinationAttribute() {
		return destinationAttribute;
	}

	public void setDestinationAttribute(String destinationAttribute) {
		this.destinationAttribute = destinationAttribute;
	}

	public boolean isTranslated() {
		return translated;
	}

	public void setTranslated(boolean translated) {
		this.translated = translated;
	}
}