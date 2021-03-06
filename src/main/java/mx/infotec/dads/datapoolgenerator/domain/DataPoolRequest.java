package mx.infotec.dads.datapoolgenerator.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DataPoolRequest useful for generate a DataPool
 * @author Roberto Villarejo Martínez
 *
 */
public class DataPoolRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rowsNumber;
	
	private List<DataColumn> addDataTypes  = new ArrayList<>();
	
	private List<DataColumn> repeatDataTypes  = new ArrayList<>();
	
	private int repeatTimes;
	
	private Numerator enumerator;

	public int getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public List<DataColumn> getAddDataTypes() {
		return addDataTypes;
	}

	public void setAddDataTypes(List<DataColumn> addDataTypes) {
		this.addDataTypes = addDataTypes;
	}

	public List<DataColumn> getRepeatDataTypes() {
		return repeatDataTypes;
	}

	public void setRepeatDataTypes(List<DataColumn> repeatDataTypes) {
		this.repeatDataTypes = repeatDataTypes;
	}

	public int getRepeatTimes() {
		return repeatTimes;
	}

	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	public Numerator getEnumerator() {
		return enumerator;
	}

	public void setEnumerator(Numerator enumerator) {
		this.enumerator = enumerator;
	}

}
