package mx.infotec.dads.datapoolgenerator.service;

import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import mx.infotec.dads.datapoolgenerator.domain.DataPoolRequest;

/**
 * The interface for DataPoolGeneratorService
 * @author Roberto Villarejo Martínez
 *
 */
public interface DataPoolGeneratorService {

	public DataPool generate(DataPoolRequest request);
	
}
