package mx.infotec.dads.datapoolgenerator.service.impl;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.domain.DataType;
import mx.infotec.dads.datapoolgenerator.service.DataGeneratorService;
import mx.infotec.dads.datapoolgenerator.service.JavaFakerGeneratorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The implementation of DataGeneratorService interface
 * @author Roberto Villarejo Martínez
 *
 */
@Service
public class DataGeneratorServiceImpl implements DataGeneratorService {

    private final Logger log = LoggerFactory.getLogger(DataGeneratorServiceImpl.class);
    
    private JavaFakerGeneratorService fakerService;

	@Autowired
	public DataGeneratorServiceImpl(JavaFakerGeneratorService fakerService) {
		this.fakerService = fakerService;
	}

	@Override
	public void generate(DataColumn dataColumn, int size) {
		DataType dataType = dataColumn.getDataType();		
		dataColumn.setData(fakerService.generate(dataType, size));
		
	}

	@Override
	public String generate(DataType dataType) {
		return fakerService.generate(dataType);
	}

}
