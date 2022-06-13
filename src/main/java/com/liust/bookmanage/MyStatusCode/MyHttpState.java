package com.liust.bookmanage.MyStatusCode;


public enum MyHttpState {

    Successful_Run(2000, "运行成功"),
    Fail_Run(5000, "运行失败"),
    Login_fail(5001, "登录错误"),
    Login_Successful(2001, "登录成功"),
    Register_fail(5002, "注册失败"),
    Register_Successful(2002, "注册成功"),
    Update_fail(5003, "修改数据失败"),
    Drop_content(5004, "缺少数据"),
    Error_Content(5005, "输入数据不符合"),
    ;


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
