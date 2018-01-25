package lpdata.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class TokenInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("进入了token拦截器。");
		// System.out.println(invocation.getInvocationContext().getParameters());
		HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String headerToken = request.getHeader("token").toString();
		// System.out.println(headerToken);
		if (headerToken.equals("debug")) {
			System.out.println("token是debug，放行");
			return invocation.invoke();
		}

		JSONObject role = (JSONObject) session.getAttribute(headerToken);
		// System.out.println(role);

		if (role == null) {
			System.out.println("role是null，返回tokenError");
			return "tokenError";
		}

		String loginName = (String) role.get("login_name");
		String SessionToken = (String) session.getAttribute(loginName);
		System.out.println(headerToken);

		if (SessionToken == headerToken) {
			return invocation.invoke();
		} else {
			session.removeAttribute(headerToken);
			System.out.println("两个token是不相等，返回tokenError");
			return "tokenError";
		}

	}

}
