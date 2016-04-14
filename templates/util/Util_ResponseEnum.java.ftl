package ${basePackage}.util;

public enum ResponseEnum {
	ERROR_ADD(500,"服务器内部异常"),
	ERROR_404(404,"请求路径错误"),
	SUCCESS(200,"请求成功"),
	ERROR_NORECORD(0,"没有查询到符合条件的记录"),
	ERROR_NOLOGIN(-1,"未登录"),
	ERROR_NOPRIVILEGE(-2,"没有权限"),
	ERROR_FORM_VALIDATE(-3,"表单校验失败"),
	ERROR_RESUBMIT(-4,"表单重复提交"),
	ERROR_VALIDATE_CODE_OVERTIME(-5,"验证码过期"),
	ERROR_BUSY(-6,"服务器忙，请稍后再试"),
	ERROR_MAINTAIN(-7,"服务器正在维护中...");
	private int code;
	private String tips;
	
	private ResponseEnum(int code, String tips) {
		this.code = code;
		this.tips = tips;
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
}