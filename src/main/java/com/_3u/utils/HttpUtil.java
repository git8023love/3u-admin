package com._3u.utils;

import com._3u.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Aspect
public class HttpUtil {
      private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

      private static ServletRequestAttributes attributes = null;

      @Pointcut("execution(public * com._3u.controller.*.*(..))")
      public void executeController(){

      }

      @Before("executeController()")
      public void doBeforeAdvice(JoinPoint joinPoint) {
            attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

      }

      public static HttpServletResponse getResponse() {
            return attributes.getResponse();
      }

      public static HttpServletRequest getRequest() {
            return attributes.getRequest();
      }

      public static HttpSession getHttpSession() {
            return attributes.getRequest().getSession();
      }

      /**
       * 得到当前的用户id
       *
       * @return
       */
      public static int getUserId() {
            // 从session里面取值
            User user = (User) getHttpSession().getAttribute(Constants.SESSION_USER);
            if (user != null) {
                  return user.getId();
            }
            return -1;
      }

      /**
       * 得到当前登录用户对象
       *
       * @return
       */
      public static User getUser() {
            return (User) getHttpSession().getAttribute(Constants.SESSION_USER);
      }

      /**
       * 得到用户登录名称
       *
       * @return
       */
      public static String getRealName() {
            // 从session里面取值
            User user = (User) getHttpSession().getAttribute(Constants.SESSION_USER);
            if (user != null) {
                  return user.getRealName();
            }
            return "";
      }

      /**
       * 设置响应头信息编码
       *
       * @param encoding
       *            编码名称
       */
      protected void setResponseEncoding(String encoding) {
            this.getResponse().setContentType("text/html;charset=" + encoding);
      }

      /**
       * 使用response输出响应文本
       *
       * @param data
       *            文本信息
       */
      protected void responseWrite(String data) {
            try {
                  setResponseEncoding("UTF-8");
                  this.getResponse().getWriter().write(data);
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
      public String getIpAddr(HttpServletRequest request) {
            String ip = request.getHeader("x-real-ip");

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                  ip = request.getHeader("Proxy-Client-IP");
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                  ip = request.getHeader("WL-Proxy-Client-IP");
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                  ip = request.getRemoteAddr();
            }
            return ip;
      }

}
