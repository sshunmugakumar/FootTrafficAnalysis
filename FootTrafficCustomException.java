
public class FootTrafficCustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2936172872124671629L;

	 public FootTrafficCustomException(String s) 
	    { 
	        super(s); 
	    } 
	 
	 public FootTrafficCustomException(String errorMessage, Throwable err) {
		    super(errorMessage, err);
		}
}
