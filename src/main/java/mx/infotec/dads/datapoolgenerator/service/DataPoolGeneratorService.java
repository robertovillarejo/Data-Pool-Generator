package mx.infotec.dads.datapoolgenerator.service;

import mx.infotec.dads.datapoolgenerator.domain.DataPool;

/**
 * The interface for DataPoolGeneratorService
 * @author Roberto Villarejo Martínez
 *
 */
public interface DataPoolGeneratorService {

	public default DataPool generate(DataPool dataPool) {
		copyDataSource(dataPool);
		addColumns(dataPool);
		repeatData(dataPool);
		enumerate(dataPool);
		return dataPool;
	}

	public abstract void enumerate(DataPool dataPool);

	public abstract void repeatData(DataPool dataPool);

	public abstract void addColumns(DataPool dataPool);

	public abstract void copyDataSource(DataPool dataPool);
	
}
