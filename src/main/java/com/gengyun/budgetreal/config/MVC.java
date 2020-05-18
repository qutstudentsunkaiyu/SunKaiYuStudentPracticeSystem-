package com.gengyun.budgetreal.config;

import com.gengyun.budgetreal.interrector.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class MVC extends WebMvcConfigurerAdapter {

	@Autowired private RequestMappingHandlerAdapter handlerAdapter;

	@PostConstruct
	public void init() {
		//		ConfigurableWebBindingInitializer initializer =
		//				(ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
		//		if (initializer.getConversionService() != null) {
		//			GenericConversionService genericConversionService =
		//					(GenericConversionService) initializer.getConversionService();
		//			genericConversionService.addConverter(new StringToDateConverter());
		//		}
	}

	/**
	 * CORS过滤器
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> swaggerAPIFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
				new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		FilterRegistrationBean<CorsFilter> bean =
				new FilterRegistrationBean<>(new CorsFilter(urlBasedCorsConfigurationSource));
		bean.setOrder(0);
		return bean;
	}

	/**
	 * CORS跨域设置,不起作用,interector
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//        registry.addMapping("/**").allowedOrigins("*")
		//        			.allowedHeaders("*")
		//                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
		//                .allowCredentials(true).maxAge(3600);
	}

	//	@Autowired PromotionInterceptor promotionInterceptor;
	//	@Autowired MemberInterceptor memberInterceptor;
	
	@Autowired
	LoginInterceptor loginInterceptor;

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/user/register","/user/login", "/tquestionItemSelect/import/**", "/tquestionItemWrite/import/**","/error/**", "/static/**");
	}

	/**
	 * JSON序列化 用于将controller返回的实体类转换成json串
	 */
//	@Bean
//	public FastJsonHttpMessageConverter jsonConverter() {
//
//		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//
//		converter.setFeatures(SerializerFeature.WriteNullBooleanAsFalse,
//				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
//				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringKeyAsString,
//				SerializerFeature.DisableCircularReferenceDetect);
//
//		return converter;
//	}

	/**
	 * 静态资源路径配置
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if (!registry.hasMappingForPattern("/static/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
		super.addResourceHandlers(registry);
	}

}
