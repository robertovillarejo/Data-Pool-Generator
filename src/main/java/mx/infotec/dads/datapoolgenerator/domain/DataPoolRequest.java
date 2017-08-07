package mx.infotec.dads.datapoolgenerator.domain;

import java.util.List;

public class DataPoolRequest {
	
	private List<DataColumn> sourceData;
	
	private String name;
	
	private Columns columns;
	
	private Repeat repeat;
	
	public DataPoolRequest() {
		this.columns = new Columns();
		this.repeat = new Repeat();
	}
	
	public List<DataColumn> getSourceData() {
		return sourceData;
	}

	public void setSourceData(List<DataColumn> sourceData) {
		this.sourceData = sourceData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public class Columns {
		
		private List<DataColumn> dataColumns;
		
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
	
	public class Repeat {
		
		private List<DataColumn> dataColumns;
		
		private int times = 1;
		
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
