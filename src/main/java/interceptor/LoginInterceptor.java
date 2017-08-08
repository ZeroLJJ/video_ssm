package interceptor;

import entity.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.ResourceLoaderUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;

/**
 * 登录拦截器
 * Created by Zero on 2017/4/13.
 */
public class LoginInterceptor implements HandlerInterceptor {
	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否是非公开地址（实际使用时将非公开地址配置配置文件中）
		ResourceLoaderUtil resourceLoaderUtil = ResourceLoaderUtil.getInstance();
		Properties prop = resourceLoaderUtil.getPropFromProperties("nonpublic.properties");
		Iterator<String> it=prop.stringPropertyNames().iterator();
		while(it.hasNext()){
			String key=it.next();
			if(url.indexOf(prop.getProperty(key))>=0){  //匹配到则说明是非公开地址,进行身份判断

				if(url.indexOf("manage")>=0){  //后台管理
					if(url.indexOf("login")>=0){  //登录页面放行
						return true;
					}else {
						//判断session
						HttpSession session  = request.getSession();
						//从session中取出管理员身份信息
						User admin = (User) session.getAttribute("admin");
						if(admin != null){
                            //验证身份权限，如果符合则放行
							if(admin.getUlevel().equals("0")||admin.getUlevel().equals("1")){
								return true;
							}else {    //权限不足
								response.setCharacterEncoding("UTF-8");
								response.setContentType("text/html;charset=UTF-8");
								PrintWriter out = response.getWriter();
								out.print("<script>alert('权限不足，请切换账号')</script>");
								out.print("<script>window.location.href='" + request.getContextPath() + "/adminLogin'</script>");
								out.close();
								return false;
							}
						}else {    //未登录
							response.setCharacterEncoding("UTF-8");
							response.setContentType("text/html;charset=UTF-8");
							PrintWriter out = response.getWriter();
							out.print("<script>alert('该地址需要登录认证，接下来转到登录页')</script>");
							out.print("<script>window.location.href='" + request.getContextPath() + "/adminLogin'</script>");
							out.close();
							return false;
						}
					}
				}else {    //非后台管理界面
					//判断session
					HttpSession session  = request.getSession();
					//从session中取出用户身份信息
					User user = (User) session.getAttribute("user");
					if(user != null){
						//身份存在，检查是否是登录后台，再进行权限验证
						return true;
					}else {
						//设置响应编码，避免中文乱码
						//不可以放在外层，会导致正常response输出的内容出问题
						response.setCharacterEncoding("UTF-8");
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.print("<script>alert('该地址需要登录认证，接下来转到登录页')</script>");
						out.print("<script>window.location.href='" + request.getContextPath() + "/login'</script>");
						out.close();
						return false;
//					//执行这里表示用户身份需要认证，跳转登陆页面
//					request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
					}
				}
			}
		}

		//return false表示拦截，不向下执行
		//return true表示放行
		return true;
	}

	//进入Handler方法之后，返回modelAndView之前执行
	//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	//执行Handler完成执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {

	}

}
