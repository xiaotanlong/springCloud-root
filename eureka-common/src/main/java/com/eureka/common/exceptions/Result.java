package com.eureka.common.exceptions;



import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by hairong on 2018/3/14.
 */
public class Result implements Serializable {
    private boolean status=true;
    private String msg=null;
    private Map data;
    private List list;
    private Integer code;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static Result ResultSuccess(){
        Result result = new Result();
        result.setCode(001);
        result.setMsg("ok");
        return result;
    }
    public static Result ResultSuccessDataList(List list){
        Result result = new Result();
        result.setStatus(true);
        result.setCode(002);
        result.setMsg("ok");
        result.setList(list);
        return result;
    }

    /*public static Result ResultSuccessDataMap(Map map){
        Result result = new Result();
        result.setStatus(true);
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setMsg("ok");
        result.setData(map);
        return result;
    }*/
    public static Result ResultSuccessDataListAndMap(List list, Map map){
        Result result = new Result();
        result.setStatus(true);
        result.setCode(003);
        result.setMsg("ok");
        result.setData(map);
        result.setList(list);
        return result;
    }
    /*public static Result ResultError(CodeEnum c){
        Result result = new Result();
        result.setStatus(false);
        result.setMsg(c.getMsg());
        result.setCode(c.getCode());
        return result;
    }
    public static Result ResultJsonError(){
        Result result = new Result();
        result.setStatus(false);
        result.setMsg("Json格式错误");
        result.setCode(CodeEnum.PARMERO.getCode());
        return result;
    }*/
}
