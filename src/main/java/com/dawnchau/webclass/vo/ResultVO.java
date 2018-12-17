package com.dawnchau.webclass.vo;

import lombok.Data;
import org.json.JSONObject;



@Data
public class ResultVO<T> {

    public String msg;

    public T data;

    public ResultVO() {
    }

    public ResultVO(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    /**
     * 以 JSON 字符串的形式返回回去
     * @return
     */
    public static String fillResultString(String msg, Object data){
        JSONObject jsonpObject = new JSONObject(){{
            put("msg",msg);
            put("data",data);
        }};
        return jsonpObject.toString();
    }

}
