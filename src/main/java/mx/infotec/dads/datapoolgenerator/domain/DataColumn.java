package mx.infotec.dads.datapoolgenerator.domain;

import java.util.List;

public class DataColumn {
	
	private String header;
	
	private List<String> data;
	
	private DataType type;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public DataType getDataType() {
		return type;
	}

	public void setDataType(DataType dataType) {
		this.type = dataType;
	}

}
 