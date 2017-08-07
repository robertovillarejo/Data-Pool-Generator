package mx.infotec.dads.datapoolgenerator.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.service.dto.DataColumnDTO;


public class DataColumnMapper {
	
	public static DataColumn toDataColumn(DataColumnDTO dataColumnDTO) {
		DataColumn dataColumn = new DataColumn();
		dataColumn.setDataType(DataTypeMapper.toDataType(dataColumnDTO.getType()));
		dataColumn.setHeader(dataColumnDTO.getTitle());
		return dataColumn;
	}
	
	public static DataColumnDTO toDataColumnDTO(DataColumn dataColumn) {
		DataColumnDTO dataColumnDTO = new DataColumnDTO();
		dataColumnDTO.setTitle(dataColumn.getHeader());
		dataColumnDTO.setType(dataColumn.getDataType().toString());
		return dataColumnDTO;
	}
	
	public static List<DataColumn> toDataColumn(List<DataColumnDTO> dataColumnsDTO) {
		List<DataColumn> dataColumns = new ArrayList<>();
		for (DataColumnDTO dataColumnDTO : dataColumnsDTO) {
			dataColumns.add(toDataColumn(dataColumnDTO));
		}
		return dataColumns;
	}
	
	
}
