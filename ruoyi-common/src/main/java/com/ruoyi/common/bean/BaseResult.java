package com.ruoyi.common.bean;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {
    public static final int FAILED = 500;
    public static final int SUCCESS = 200;
    public static final String SUCCESS_MSG = "操作成功!";
    public static final String FAILED_MSG = "操作失败!";

    /**
     * 操作状态码，200:成功，500:失败
     */
    protected int status;

    /**
     * 提示信息，{操作成功|操作失败}
     */
    protected String message;

    /**
     * 业务数据
     */
    protected T data;

    public static <T> BaseResult<T> newErrorResult() {
        return new BaseResult(500, "操作失败!");
    }

    public static <T> BaseResult<T> newErrorResult(String errorMsg) {
        return new BaseResult(500, errorMsg);
    }

    public static <T> BaseResult<T> newErrorResult(int errorCode, String errorMsg) {
        return new BaseResult(errorCode, errorMsg);
    }

    public boolean isSuccess() {
        return this.getStatus() == 200;
    }

    public boolean successAndDataNotNull() {
        return this.getStatus() == 200 && this.getData() != null;
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult(200, "操作成功!", data);
    }

    public static <T> BaseResult<T> success() {
        return new BaseResult(200, "操作成功!");
    }

    public BaseResult() {
        this.status = 200;
        this.message = "操作成功!";
    }

    public BaseResult(T data) {
        this.status = 200;
        this.message = "操作成功!";
        this.data = data;
    }

    public BaseResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
