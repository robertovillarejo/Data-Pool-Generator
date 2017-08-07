package mx.infotec.dads.datapoolgenerator.service.mapper;

import mx.infotec.dads.datapoolgenerator.domain.DataPoolRequest;
import mx.infotec.dads.datapoolgenerator.service.dto.DataPoolRequestDTO;

public class DataPoolRequestMapper {
		
	public static DataPoolRequest toDataPoolRequest(DataPoolRequestDTO requestDTO) {
		DataPoolRequest request = new DataPoolRequest();
		request.setName(requestDTO.getName());
		request.setSourceData(requestDTO.getSourceData());
		
		//Fill Columns Object
		request.getColumns().setRowsNumber(requestDTO.getHorizontal().getRowsNumber());
		request.getColumns().setDataColumns(DataColumnMapper.toDataColumn(requestDTO.getHorizontal().getDataTypes()));
		
		//Fill Repeat Object
		request.getRepeat().setTimes(requestDTO.getVertical().getRepeat());
		request.getRepeat().setUniqueValues(requestDTO.getVertical().isVerticalUnique());
		request.getRepeat().setDataColumns(DataColumnMapper.toDataColumn(requestDTO.getVertical().getDataTypes()));
		
		return request;
	}

}
