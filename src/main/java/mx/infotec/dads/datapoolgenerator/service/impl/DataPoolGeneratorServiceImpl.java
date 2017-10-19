package mx.infotec.dads.datapoolgenerator.service.impl;

import mx.infotec.dads.datapoolgenerator.domain.Numerator;
import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import mx.infotec.dads.datapoolgenerator.domain.DataType;
import mx.infotec.dads.datapoolgenerator.service.DataPoolGeneratorService;
import mx.infotec.dads.datapoolgenerator.service.JavaFakerGeneratorService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rits.cloning.Cloner;

/**
 * The implementation of DataPoolGeneratorService interface
 * @author Roberto Villarejo Martínez
 *
 */
@Service
public class DataPoolGeneratorServiceImpl implements DataPoolGeneratorService {

	private final Logger log = LoggerFactory.getLogger(DataPoolGeneratorServiceImpl.class);

	private JavaFakerGeneratorService fakerService;
	private Cloner cloner = new Cloner();

	@Autowired
	public DataPoolGeneratorServiceImpl(JavaFakerGeneratorService fakerService) {
		this.fakerService = fakerService;
	}

	@Override
	public void copyDataSource(DataPool dataPool) {
		List<DataColumn> listDataColumn = cloner.deepClone(dataPool.getSourceData());
		dataPool.getData().clear();
		dataPool.setData(listDataColumn);
	}

	@Override
	public void repeatData(DataPool dataPool) {
		// Repeat current data
		repeatData(dataPool, dataPool.getRepeatTimes());
		//Generate and add repeated data
		generateRepeatData(dataPool);

	}

	public void repeatData(DataPool dataPool, int n) {

		for (DataColumn dataColumn : dataPool.getData()) {
			List<String> data = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				data.addAll(dataColumn.getData());
			}
			dataColumn.setData(data);
		}

	}

	@Override
	public void addColumns(DataPool dataPool) {
		int rowsNumber = dataPool.getRequestOrSizeRowsNumber();
		dataPool.getData().addAll(generate(dataPool.getAddDataTypes(), rowsNumber));
	}

	private List<DataColumn> generate(List<DataColumn> dataColumns, int n) {
		List<DataColumn> result = new ArrayList<>();
		for (DataColumn dataColumn : dataColumns) {
			result.add(new DataColumn(dataColumn.getHeader(), dataColumn.getType(), fakerService.generate(dataColumn.getType(), n)));
		}
		return result;
	}

	private void generateRepeatData(DataPool dataPool) {
		int rowsNumber = dataPool.getRequestOrSizeRowsNumber();
		dataPool.getData().addAll(
				generateRepeat(
						dataPool.getRepeatDataTypes(), rowsNumber, dataPool.getRepeatTimes()
						)
				);

	}

	private List<DataColumn> generateRepeat(List<DataColumn> dataColumns, int n, int repeat) {
		List<DataColumn> result = new ArrayList<>();
		for (DataColumn dataColumn : dataColumns) {

			DataColumn dataCol = new DataColumn();
			dataCol.setHeader(dataColumn.getHeader());
			dataCol.setType(dataColumn.getType());

			for (int i = 0; i < repeat; i++) {
				List<String> data = new ArrayList<>();
				String oneData = fakerService.generate(dataColumn.getType());
				for (int j = 0; j < n; j++) {
					data.add(oneData);
				}
				dataCol.getData().addAll(data);
			}
			result.add(dataCol);
		}
		return result;
	}

	@Override
	public void enumerate(DataPool dataPool) {
		if (dataPool.getEnumerator() == null) return;
		int rowsNumber = dataPool.getRequestOrSizeRowsNumber();
		Numerator enumerator = dataPool.getEnumerator();
		DataColumn numeration = new DataColumn();
		numeration.setHeader(enumerator.getHeader());
		numeration.setType(DataType.AUTO_INCREMENT);
		int repeat = dataPool.getRepeatTimes();
		for (int i = 0; i < repeat; i++) {
			String number = enumerator.generate();
			for (int j = 0; j < rowsNumber; j++) {
				numeration.getData().add(number);
			}
		}
		dataPool.getData().add(numeration);
	}

}
