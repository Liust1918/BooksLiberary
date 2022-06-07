package com.liust.bookmanage.MyStatusCode;


public enum MyHttpState {

    Successful_Run(2000,"运行成功"),
    Login_fail(5001,"登录错误"),
    Login_Successful(2001,"登录成功"),
    Value_Null(5000,"数据为空");

    private final Integer status;
    private final String msg;

    MyHttpState(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getCode() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
