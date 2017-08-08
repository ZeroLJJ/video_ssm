package exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理器
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//handler就是处理器适配器要执行Handler对象（只有method）
		
//		解析出异常类型
//		如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
//		String message = null;
//		if(ex instanceof CustomException){
//			message = ((CustomException)ex).getMessage();
//		}else{
////			如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
//			message="未知错误";
//		}

		ModelAndView modelAndView = new ModelAndView();

		//上边代码变为
		CustomException customException = null;
		if(ex instanceof ExistVideoException){
			customException = (ExistVideoException)ex;
			//错误信息
			String message = customException.getMessage();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.print(message);
			out.close();
			return modelAndView;
		} else if(ex instanceof UserFrozenException){
			customException = (UserFrozenException)ex;
			//错误信息
			String message = customException.getMessage();
			PrintWriter out = null;
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print("alert("+message+")");
				//加..是为了返回根目录
				response.sendRedirect("../login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
			return modelAndView;
		} else{
			customException = new CustomException("未知错误");
			modelAndView.setViewName("error");
			//错误信息
			String message = customException.getMessage();

			//将错误信息传到页面
			modelAndView.addObject("message", message);

			return modelAndView;
		}

	}

}
