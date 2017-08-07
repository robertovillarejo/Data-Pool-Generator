package mx.infotec.dads.datapoolgenerator.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;

/**
 * The DTO for DataPoolRequest
 * @author Roberto Villarejo Martínez
 *
 */
public class DataPoolRequestDTO {
	
	@JsonProperty(value="name")
	private String name;
	
	@JsonProperty(value="source_data")
	private List<DataColumn> sourceData;
	
	@JsonProperty(value="columns")
	private Columns horizontal;
	
	@JsonProperty(value="repeat")
	private Repeat vertical;
	
	public Columns getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(Columns horizontal) {
		this.horizontal = horizontal;
	}
	public Repeat getVertical() {
		return vertical;
	}
	public void setVertical(Repeat vertical) {
		this.vertical = vertical;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DataColumn> getSourceData() {
		return sourceData;
	}
	public void setSourceData(List<DataColumn> dataPoolId) {
		this.sourceData = dataPoolId;
	}

	public class Columns {
		
		@JsonProperty(value="data_types")
		private List<DataColumnDTO> dataTypes;
		
		@JsonProperty(value="rows_number")
		private int rowsNumber = 1;
		
		public List<DataColumnDTO> getDataTypes() {
			return dataTypes;
		}
		public void setDataTypes(List<DataColumnDTO> dataTypes) {
			this.dataTypes = dataTypes;
		}
		public int getRowsNumber() {
			return rowsNumber;
		}
		public void setRowsNumber(int rowsNumber) {
			this.rowsNumber = rowsNumber;
		}
		
	}
	
	public class Repeat {
		
		@JsonProperty(value="data_types")
		private List<DataColumnDTO> dataTypes;
		
		@JsonProperty(value="times")
		private int times;
		
		@JsonProperty(value="unique")
		private boolean uniqueValues = false;
		
		public List<DataColumnDTO> getDataTypes() {
			return dataTypes;
		}
		public void setDataTypes(List<DataColumnDTO> dataTypes) {
			this.dataTypes = dataTypes;
		}
		public int getRepeat() {
			return times;
		}
		public void setRepeat(int repeat) {
			this.times = repeat;
		}
		public boolean isVerticalUnique() {
			return uniqueValues;
		}
		public void setVerticalUnique(boolean verticalUnique) {
			this.uniqueValues = verticalUnique;
		}
	
	}

}
