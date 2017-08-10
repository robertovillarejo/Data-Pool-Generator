package mx.infotec.dads.datapoolgenerator.service;

import java.io.File;
import java.io.IOException;


import mx.infotec.dads.datapoolgenerator.domain.DataPool;

public interface CsvGeneratorService {
	
	public File writeCsv(DataPool dataPool) throws IOException;

}
