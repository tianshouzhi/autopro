package ${basePackage}.web.inteceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ${basePackage}.util.JsonUtil;
import ${basePackage}.util.ResponseEnum;
import ${basePackage}.util.ResponseWrapper;
import ${basePackage}.util.ValidateResult;
import ${basePackage}.util.ValidateResult.ErrorParameter;

/**
 * 方法参数校验拦截器，如果参数类上有注解@Valid，则对该参数进行校验
 * @author tianshouzhi
 */
public class MethodValidateInteceptor  extends HandlerInterceptorAdapter{
	
	@Autowired
	private Validator validator;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		MethodParameter[] methodParameters=null;
		ValidateResult validateResult=null;
		List<ErrorParameter> errorParameters=null;
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod=(HandlerMethod) handler;
			methodParameters = handlerMethod.getMethodParameters();
			validateResult=new ValidateResult();
			errorParameters=new ArrayList<ValidateResult.ErrorParameter>();

			//如果当前方法参数列表不为空
			if(methodParameters!=null){
				//依次校验每一个参数,将参数校验结果封装到validateResult中
				for (MethodParameter methodParameter : methodParameters) {
					Class<?> parameterType = methodParameter.getParameterType();
					Object target = parameterType.newInstance();
					Map parameterMap = request.getParameterMap();
					BeanUtils.copyProperties(target, parameterMap);
					Set<ConstraintViolation<Object>> currentValidateResult = validator.validate(target);
					if(currentValidateResult!=null&&!currentValidateResult.isEmpty()){
						for (ConstraintViolation<Object> constraintViolation : currentValidateResult) {
							//当前校验的参数名
							String parameterName=constraintViolation.getPropertyPath().toString();
							//当前校验的参数值
							Object invalidValue = constraintViolation.getInvalidValue();
							//错误提示信息
							String errorMsg = constraintViolation.getMessage();
							
							ValidateResult.ErrorParameter errorParameter =validateResult.new  ErrorParameter(parameterName, errorMsg, invalidValue);
							errorParameters.add(errorParameter);
						}
					}
				}
			}
			
		}
		//判断是否有错误信息
		//如果集合为空，说明校验通过
		boolean invokeMethod=false;
		if(errorParameters==null||errorParameters.isEmpty()){
			invokeMethod= true;
		}else{
			String requestUrl=request.getRequestURL().toString();
			validateResult.setErrorParameters(errorParameters);
			validateResult.setInterfaceUrl(requestUrl);
			response.getWriter().write(JsonUtil.toJson(new ResponseWrapper(ResponseEnum.ERROR_FORM_VALIDATE.getCode(), ResponseEnum.ERROR_FORM_VALIDATE.getTips(), validateResult)));
		}
		return invokeMethod;
	}
	
}
