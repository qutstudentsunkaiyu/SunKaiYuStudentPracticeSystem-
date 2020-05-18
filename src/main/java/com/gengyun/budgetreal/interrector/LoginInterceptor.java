package com.exercise.budgetreal.interrector;

import com.alibaba.fastjson.JSON;


import com.exercise.budgetreal.entity.User;
import com.exercise.budgetreal.support.UserHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String header = request.getHeader("X-Token");
		if (StringUtils.isBlank(header)) {
			header = request.getParameter("token");
		}
//        HttpSession session = request.getSession();
        final String token = header;
		try {
			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			
			Object object = claims.get("user");
			System.out.println(JSON.toJSONString(object));
			User user = JSON.parseObject(JSON.toJSONString(object), User.class);

			UserHolder.set(user);
			
		} catch (final SignatureException e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

		
		UserHolder.set(null);
	}

}
