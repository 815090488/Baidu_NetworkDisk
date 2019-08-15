package com.luyao.ExceptionEnum;

public enum ExceptionCode implements ICustomizeErrorCode {

    FILE_OUT_OF_RANGE_VIP(2000,"文件超出上传范围，请充会员"),
    SPACE_IS_NOT_ENOUGH(2001,"空间不足"),
    FILE_OUT_OF_RANGE_SUPVIP(2002,"文件超出上传范围，请充会员"),
    FILE_DOES_NOT_EXIST(2003,"文件不存在！");
    private Integer code;
    private String message;


    ExceptionCode(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
