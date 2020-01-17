//package com.rquest.mailtoolbackup;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//@Component
//public class GlobalExceptionHandler implements HandlerExceptionResolver {
//	
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//	
//    @ExceptionHandler(value = Exception.class)
//    public void jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//    	log.info("报错处理================");
//    }
//
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//			Exception ex) {
//		log.info("报错处理================");
//		return null;
//	}
//
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
//
