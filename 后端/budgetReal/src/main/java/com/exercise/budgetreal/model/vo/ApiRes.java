package com.exercise.budgetreal.model.vo;


import com.alibaba.fastjson.JSON;
import java.util.HashMap;

public class ApiRes extends HashMap<String,Object> {


  public static ApiRes success(String code ,String msg){
      ApiRes apiRes = new ApiRes();
      apiRes.put("code",code);
      apiRes.put("msg",msg);
      return apiRes;
  }

    public static ApiRes fail(String code ,String msg){
        ApiRes apiRes = new ApiRes();
        apiRes.put("code",code);
        apiRes.put("msg",msg);
        return apiRes;
    }
    public static ApiRes successId(String code ,String msg,int id){
        ApiRes apiRes = new ApiRes();
        apiRes.put("code",code);
        apiRes.put("msg",msg);
        apiRes.put("id",id);
        return apiRes;
    }


    @Override
    public  String toString() {
        return JSON.toJSONString(this);
    }
}
