package com.app.shiroweb.vo;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午10:55
 * describe: 返回给前端的数据封装
 **/
public class ResultVO {
    private String code;
    private String message;
    private boolean result;
    private Object data;

    public static ResultVO success(String code,String message, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setResult(true);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(String code,String message, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setResult(false);
        resultVO.setData(data);
        return resultVO;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
