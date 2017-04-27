package cn.com.welleplus.fire.exception;
/**
 * 查询不到用户的异常
 * @author Lu
 *
 */
public class AdminNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AdminNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AdminNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AdminNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
