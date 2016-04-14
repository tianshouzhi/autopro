package ${basePackage}.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数校验结果
 * @author tianshouzhi
 *
 */
public class ValidateResult {
	/**接口Url*/
	private String interfaceUrl;
	/**
	 * 错误信息集合List
	 */
	private List<ErrorParameter> errorParameters=new ArrayList<ValidateResult.ErrorParameter>();
	
	public ValidateResult() {
		super();
	}


	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}


	public ValidateResult(String interfaceUrl,
			List<ErrorParameter> errorParameters) {
		super();
		this.interfaceUrl = interfaceUrl;
		this.errorParameters = errorParameters;
	}


	public List<ErrorParameter> getErrorParameters() {
		return errorParameters;
	}


	public void setErrorParameters(List<ErrorParameter> errorParameters) {
		this.errorParameters = errorParameters;
	}


	public class ErrorParameter{
		/**
		 * 错误参数的名称
		 */
		private String parameterName;
		/**
		 * 传递过来的错误的值
		 */
		private Object invalidValue;
		/**
		 * 错误提示信息
		 */
		private String errorMsg;
		public String getParameterName() {
			return parameterName;
		}
		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}
		public String getErrorMsg() {
			return errorMsg;
		}
		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}
		public Object getInvalidValue() {
			return invalidValue;
		}
		public void setInvalidValue(Object invalidValue) {
			this.invalidValue = invalidValue;
		}
		public ErrorParameter(String parameterName, String errorMsg,
				Object invalidValue) {
			super();
			this.parameterName = parameterName;
			this.errorMsg = errorMsg;
			this.invalidValue = invalidValue;
		}
		public ErrorParameter() {
			super();
		}
		
	}
}
