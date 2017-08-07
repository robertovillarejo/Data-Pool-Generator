package mx.infotec.dads.datapoolgenerator.service.impl;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import mx.infotec.dads.datapoolgenerator.domain.DataPoolRequest;
import mx.infotec.dads.datapoolgenerator.service.DataGeneratorService;
import mx.infotec.dads.datapoolgenerator.service.DataPoolGeneratorService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataPoolGeneratorServiceImpl implements DataPoolGeneratorService {

    private final Logger log = LoggerFactory.getLogger(DataPoolGeneratorServiceImpl.class);
    
private DataGeneratorService dataGenerator;
	
	@Autowired
	public DataPoolGeneratorServiceImpl(DataGeneratorService dataGenerator) {
		this.dataGenerator = dataGenerator;
	}

	@Override
	public DataPool generate(DataPoolRequest request) {
		DataPool dataPool = new DataPool();
		dataPool.setName(request.getName());
		copySourceData(request, dataPool);
		addColumns(request, dataPool);
		repeatData(request, dataPool);
		return dataPool;
	}
	
private void repeatData(DataPoolRequest request, DataPool dataPool) {
		
		int rowsNumber;
		if (request.getSourceData() == null || request.getSourceData().isEmpty()) rowsNumber = request.getColumns().getRowsNumber();
		else rowsNumber = request.getSourceData().get(0).getData().size();
		
		int size = request.getRepeat().getTimes() * rowsNumber;
		if (!request.getRepeat().isUniqueValues()) {	//Add Columns first and then repeat whole data
			dataPool.getColumns().addAll(dataGenerator.generate(request.getRepeat().getDataColumns(), rowsNumber));
			repeatData(dataPool, request.getRepeat().getTimes());
		} else {	//Repeat the data, generate the columns with unique values and then add these to data
			repeatData(dataPool, request.getRepeat().getTimes());
			dataPool.getColumns().addAll(dataGenerator.generate(request.getRepeat().getDataColumns(), size));
		}		
	}
	
	private void repeatData(DataPool dataPool, int n) {
		
		for (DataColumn dataColumn : dataPool.getColumns()) {
			List<String> data = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				data.addAll(dataColumn.getData());
			}
			dataColumn.setData(data);
		}
		
	}
		
	private void addColumns(DataPoolRequest request, DataPool dataPool) {
		int rowsNumber;
		if (request.getSourceData() == null) rowsNumber = request.getColumns().getRowsNumber();
		else rowsNumber = dataPool.getColumns().get(0).getData().size();
		
		dataGenerator.generate(request.getColumns().getDataColumns(), rowsNumber);
		dataPool.getColumns().addAll(request.getColumns().getDataColumns());
		
	}

	private void copySourceData(DataPoolRequest request, DataPool dataPool) {
		if (request.getSourceData() != null || !request.getSourceData().isEmpty()) {
			dataPool.setColumns(request.getSourceData());
		}		
	}

}
