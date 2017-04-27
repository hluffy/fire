package cn.com.welleplus.fire.exception;
/**
 * 页面请求pageIndex和pageSizre转换异常
 * @author Lu
 *
 */
public class PageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PageException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PageException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PageException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
