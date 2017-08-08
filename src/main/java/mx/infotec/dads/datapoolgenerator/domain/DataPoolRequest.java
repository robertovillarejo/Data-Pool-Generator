package mx.infotec.dads.datapoolgenerator.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A DataPoolRequest useful for generate a DataPool
 * @author Roberto Villarejo Martínez
 *
 */
@JsonPropertyOrder({"columns", "repeat"})
public class DataPoolRequest {
	
	@JsonProperty(value="columns")
	private Columns columns;
	
	@JsonProperty(value="repeat")
	private Repeat repeat;
	
	public DataPoolRequest() {
		this.columns = new Columns();
		this.repeat = new Repeat();
	}

	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}

	public Repeat getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}

	@JsonPropertyOrder({"rows_number", "data_types"})
	public class Columns {
		
		@JsonProperty(value="data_types")
		private List<DataColumn> dataColumns = new ArrayList<>();
		
		@JsonProperty(value="rows_number")
		private int rowsNumber;

		public List<DataColumn> getDataColumns() {
			return dataColumns;
		}

		public void setDataColumns(List<DataColumn> dataColumns) {
			this.dataColumns = dataColumns;
		}

		public int getRowsNumber() {
			return rowsNumber;
		}

		public void setRowsNumber(int rowsNumber) {
			this.rowsNumber = rowsNumber;
		}
	}
	
	@JsonPropertyOrder({"times", "unique", "data_types"})
	public class Repeat {
		
		@JsonProperty(value="data_types")
		private List<DataColumn> dataColumns = new ArrayList<>();
		
		@JsonProperty(value="times")
		private int times = 1;
		
		@JsonProperty(value="unique")
		private boolean uniqueValues = false;

		public List<DataColumn> getDataColumns() {
			return dataColumns;
		}

		public void setDataColumns(List<DataColumn> dataColumns) {
			this.dataColumns = dataColumns;
		}

		public int getTimes() {
			return times;
		}

		public void setTimes(int times) {
			this.times = times;
		}

		public boolean isUniqueValues() {
			return uniqueValues;
		}

		public void setUniqueValues(boolean uniqueValues) {
			this.uniqueValues = uniqueValues;
		}
		
	}

}
