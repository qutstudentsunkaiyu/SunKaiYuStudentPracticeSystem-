package com.exercise.budgetreal.util;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApiResponse extends HashMap<String, Object> {

	private static final long serialVersionUID = -5162873083105976921L;

	public static ApiResponse fail() {
		ApiResponse apiResult = new ApiResponse();
		apiResult.put("success", false);
		return apiResult;
	}

	public static ApiResponse success() {
		ApiResponse apiResult = new ApiResponse();
		apiResult.put("success", true);
		return apiResult;
	}

	public ApiResponse and(String key, Object object) {
		this.put(key, object);
		return this;
	}

	public ApiResponse error(String msg) {
		this.put("msg", msg);
		return this;
	}

	public ApiResponse error(ApiError error) {
		this.put("msg", error.getMsg());
		this.put("code", error.getCode());
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static void main(String[] args) {

	}

	@SuppressWarnings("unchecked")
	public ApiResponse data(Map<String, Object> data) {
		if (this.containsKey("data")) {
			Map<String, Object> object = (Map<String, Object>) this.get("data");
			object.putAll(data);
		} else {
			this.put("data", data);
		}
		return this;
	}

	public ApiResponse and(Map<String, Object> map) {
		for (Entry<String, Object> entry : map.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public ApiResponse andModel(Object obj, Class<?> clazz) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor p : ps) {
			if (p.getName().equals("class")) {
				continue;
			}
			Method method = p.getReadMethod();
			try {
				Object value = method.invoke(obj);
				this.put(p.getName(), value);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return this;
	}

}
