package mx.infotec.dads.datapoolgenerator.service;

import java.util.List;

import mx.infotec.dads.datapoolgenerator.domain.DataType;

public interface JavaFakerGeneratorService {

	public String generate(DataType dataType);
	
	public List<String> generate(DataType dataType, int n);
	
}
