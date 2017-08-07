package mx.infotec.dads.datapoolgenerator.service.mapper;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class UnknownDataTypeRequestException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknownDataTypeRequestException(String message) {
		super(message);
	}
	
	public UnknownDataTypeRequestException(String message, Throwable cause){
		super(message, cause);
	}

}
