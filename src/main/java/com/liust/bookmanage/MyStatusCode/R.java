package com.liust.bookmanage.MyStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class R  {
    private Integer status;
    private String msg;
    private Object data;

    public static R setResult(Object data,MyHttpState httpState){
        return new R(httpState.getCode(),httpState.getMsg(),data);
    }


    public static R valueIsOk(Object data){
        MyHttpState myHttpState =MyHttpState.Successful_Run;
        return new R(myHttpState.getCode(),myHttpState.getMsg(),data);
    }

}
