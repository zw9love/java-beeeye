package lpdata.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class RequestInterceptor extends MethodFilterInterceptor {
	// private Object postData;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("进入了method拦截器。");
		// System.out.println(invocation.getInvocationContext().getParameters());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String methodName = ServletActionContext.getRequest().getMethod();
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		if (methodName == "POST")
			return invocation.invoke();
		else
			System.out.println("使用了get请求方式。");
		return "methodError";

		// // 前处理
		// System.out.println("RequestMethodInterceptor 的前处理!");
		// // 放行
		// String result = invocation.invoke();
		// // 后处理
		// System.out.println("RequestMethodInterceptor 的后处理!");
		// return result;
	}

}
