package mx.infotec.dads.datapoolgenerator.service.mapper;

public class DataTypeRequestException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataTypeRequestException(String message) {
		super(message);
	}
	
	public DataTypeRequestException(String message, Throwable cause){
		super(message, cause);
	}

}
