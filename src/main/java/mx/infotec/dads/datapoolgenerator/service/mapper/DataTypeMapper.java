package mx.infotec.dads.datapoolgenerator.service.mapper;

import mx.infotec.dads.datapoolgenerator.domain.DataType;

/**
 * The mapper for DataType
 * @author Roberto Villarejo Martínez
 *
 */
public class DataTypeMapper {
	
	private DataTypeMapper() {
		throw new IllegalStateException("DataTypeMapper Class");
	}
		
	public static DataType toDataType(String dataType) {
		DataType dataTypeENUM = null;
		
		switch (dataType) {
			
		case "email":
			dataTypeENUM = DataType.EMAIL;
			break;
						
		case "last_name":
			dataTypeENUM = DataType.LAST_NAME;
			break;
			
		case "lorem":
			dataTypeENUM = DataType.LOREM;
			break;
			
		case "name":
			dataTypeENUM = DataType.NAME;
			break;
			
		case "company":
			dataTypeENUM = DataType.COMPANY;

		default:
			break;
		}
		
		return dataTypeENUM;
	}

}
