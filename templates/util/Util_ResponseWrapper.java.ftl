package ${basePackage}.util;

/**
 * 返回结果封装
 */
public class ResponseWrapper {
	/**返回码，见RespouseEnum*/
	private int code;
	/**返回码，见RespouseEnum*/
	private String tips;
	/**用户指定*/
	private String details;
	/**用户指定*/
	private Object data;
	public ResponseWrapper(int code, String tips, Object data) {
		super();
		this.code = code;
		this.tips = tips;
		this.data = data;
	}
	public ResponseWrapper(int code, String tips, String details, Object data) {
		super();
		this.code = code;
		this.tips = tips;
		this.details = details;
		this.data = data;
	}
	public ResponseWrapper() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
