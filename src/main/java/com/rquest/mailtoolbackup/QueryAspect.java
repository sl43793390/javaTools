//package com.rquest.mailtoolbackup;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.stereotype.Component;
//
//@EnableAspectJAutoProxy(proxyTargetClass=true)  
//@Aspect
//@Component
//public class QueryAspect {
//
//	private static final Logger logger = LoggerFactory.getLogger(QueryAspect.class);
//	
//	
//	@Pointcut("execution( * com.rquest.riskmaster.component.*.*(..))")
//	private void query(){}
//
//    @Around(value = "query()")
//    public Object doArount(ProceedingJoinPoint pjp){
//    	logger.info("qiemianchenggong==========前=========");
//    	Object proceed = null;
//    	try {
//    		return pjp.proceed();
////			logger.info("qiemianchenggong==========后=========");
//		} catch (Throwable e) {
//			logger.info("qiemianchenggong==========异常=========");
//			e.printStackTrace();
//		}	
//    	
//			return proceed;
//    }	
//    
////	
////	@Before("query()")
////    public void doBefore(JoinPoint joinPoint) throws Throwable {
////		user = (User) VaadinSession.getCurrent().getAttribute("user");
////		if(Util.userTimes.keySet().contains(user.getUserId())){
////			Util.userTimes.put(user.getUserId(), Util.userTimes.get(user.getUserId())-1);
////			logger.info("now left query time is"+Util.userTimes.get(user.getUserId()));
////		}else{
////			Util.userTimes.put(user.getUserId(),Util.QUERY_TIMES);
////		}
////        // 接收到请求，记录请求内容
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
////        
////        // 记录下请求内容
////        logger.info("URL : " + request.getRequestURL().toString());
////        logger.info("HTTP_METHOD : " + request.getMethod());
////        logger.info("IP : " + request.getRemoteAddr());
////        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
////        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
////
////    }
////
////    @AfterReturning(returning = "ret", pointcut = "query()")
////    public void doAfterReturning(Object ret) throws Throwable {
////    	//当前登录用户是
////        logger.info("当前user"+VaadinSession.getCurrent().getAttribute("user"));
////        // 处理完请求，返回内容
////        logger.info("RESPONSE : " + ret);
////    }
//}
