package com.jingjusi.mediaweb.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果集
 * @param <T>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer status;
    private String message;
    private T data;

    public CommonResult(Integer code,String message){

        this(code,message,null);

    }
}
