package mx.infotec.dads.datapoolgenerator.service;

import java.util.List;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.domain.DataType;

/**
 * The interface for DataGeneratorService
 * @author Roberto Villarejo Martínez
 *
 */
public interface DataGeneratorService {

	/**
	 * Generate one data of dataType
	 * @param dataType
	 * @return
	 */
	public void generate(DataColumn dataColumn, int size);
	
	public default List<DataColumn> generate(List<DataColumn> columns, int size) {
		for (DataColumn dataColumn : columns) {
			generate(dataColumn, size);
		}
		return columns;
	}
	
	public String generate(DataType dataType);
	
}
