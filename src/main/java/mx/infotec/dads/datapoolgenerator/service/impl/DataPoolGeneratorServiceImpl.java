package mx.infotec.dads.datapoolgenerator.service.impl;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import mx.infotec.dads.datapoolgenerator.service.DataPoolGeneratorService;
import mx.infotec.dads.datapoolgenerator.service.JavaFakerGeneratorService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The implementation of DataPoolGeneratorService interface
 * @author Roberto Villarejo Martínez
 *
 */
@Service
public class DataPoolGeneratorServiceImpl implements DataPoolGeneratorService {

	private final Logger log = LoggerFactory.getLogger(DataPoolGeneratorServiceImpl.class);

	private JavaFakerGeneratorService fakerService;

	@Autowired
	public DataPoolGeneratorServiceImpl(JavaFakerGeneratorService fakerService) {
		this.fakerService = fakerService;
	}

	@Override
	public DataPool generate(DataPool dataPool) {
		addColumns(dataPool);
		repeatData(dataPool);
		return dataPool;
	}

	private void repeatData(DataPool dataPool) {

		int rowsNumber;
		if (dataPool.getData() == null || dataPool.getData().isEmpty()) rowsNumber = dataPool.getRequest().getColumns().getRowsNumber();
		else rowsNumber = dataPool.getData().get(0).getData().size();

		int size = dataPool.getRequest().getRepeat().getTimes() * rowsNumber;
		if (!dataPool.getRequest().getRepeat().isUniqueValues()) {	//Add Columns first and then repeat whole data
			
			dataPool.getData().addAll(generate(dataPool.getRequest().getRepeat().getDataColumns(), rowsNumber));
			repeatData(dataPool, dataPool.getRequest().getRepeat().getTimes());
			
		} else {	//Repeat the data, generate the columns with unique values and then add these to data
			
			repeatData(dataPool, dataPool.getRequest().getRepeat().getTimes());
			dataPool.getData().addAll(generate(dataPool.getRequest().getRepeat().getDataColumns(), size));
		}		
	}

	private void repeatData(DataPool dataPool, int n) {

		for (DataColumn dataColumn : dataPool.getData()) {
			List<String> data = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				data.addAll(dataColumn.getData());
			}
			dataColumn.setData(data);
		}

	}

	private void addColumns(DataPool dataPool) {
		int rowsNumber;
		if (dataPool.getData() == null || dataPool.getData().isEmpty()) rowsNumber = dataPool.getRequest().getColumns().getRowsNumber();
		else rowsNumber = dataPool.getData().get(0).getData().size();
		dataPool.getData().addAll(generate(dataPool.getRequest().getColumns().getDataColumns(), rowsNumber));
	}
	
	private List<DataColumn> generate(List<DataColumn> dataColumns, int n) {
		List<DataColumn> result = new ArrayList<>();
		for (DataColumn dataColumn : dataColumns) {
			result.add(new DataColumn(dataColumn.getHeader(), dataColumn.getType(), fakerService.generate(dataColumn.getType(), n)));
		}
		return result;
	}

}
