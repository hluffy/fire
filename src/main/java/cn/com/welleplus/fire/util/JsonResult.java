package cn.com.welleplus.fire.util;
/**
 * ·µ»ØjsonÀàĞÍ
 * @author Lu
 *
 * @param <T>
 */
public class JsonResult<T> {


	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	private T data;
	private String message;
	
	public JsonResult() {
		super();
	}
	
	public JsonResult(T t) {
		state = SUCCESS;
		data = t;
		message = "";
	}
	
	public JsonResult(int state,T t,Throwable e) {
		this.state = state;
		this.data = t;
		this.message = e.getMessage();
	}
	
	public JsonResult(T t,Throwable e) {
		state = ERROR;
		data = t;
		message = e.getMessage();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "{\"state\":" + state + ", \"data\":" + data + ", \"message\":\"" + message + "\"}";
	}
	
}
