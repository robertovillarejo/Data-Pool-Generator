package mx.infotec.dads.datapoolgenerator.domain;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Numerator {
	
	@JsonProperty("header")
	private String header;
	@JsonProperty("initialValue")
	private int initialValue;
	@JsonProperty("increment")
	private int increment = 1;
	@JsonProperty("template")
	private String template = PLACEHOLDER;
	
	private static final String PLACEHOLDER = "{n}";
	@Transient
	private int currentVal;
	
	public String getTemplate() {
		return template;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setTemplate(String template) {
		if ("".equals(template)) {
			this.template = PLACEHOLDER;
		} else {
			this.template = template;
		}
	}

	public String generate() {
		String data = template.replace("{n}", Integer.toString(currentVal));
		currentVal += increment;
		return data;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
		this.currentVal = initialValue;
	}

}
