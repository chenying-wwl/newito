package com.qf.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qf.common.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Response
 *
 * @author 千锋健哥
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "返回数据实体")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean ok = false;
    @Schema(description = "状态码")
    private int code = Code.OK.getCode();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "消息描述")
    private String message = CommonConstant.Response.ERROR;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "返回的数据")
    private T data;

    /**
     * 成功
     *
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> ok() {
        return new R().success();
    }

    /**
     * 成功 自定义提示信息
     *
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> ok(String message) {
        return new R().success(message);
    }

    /**
     * 成功 自定义 Code & 提示信息
     *
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> ok(Code code, String message) {
        return new R().success(code.getCode(), message);
    }

    /**
     * 成功 返回结果
     *
     * @param data 返回结果
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> ok(T data) {
        return new R(data).success();
    }

    /**
     * 成功 返回结果 & 自定义提示信息
     *
     * @param data 返回结果
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> ok(T data, String message) {
        return new R(data).success(message);
    }

    /**
     * 失败
     *
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> fail() {
        return new R().failure();
    }

    /**
     * 失败 自定义提示信息
     *
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> fail(String message) {
        return new R().failure(message);
    }

    /**
     * 失败 自定义 Code & 提示信息
     *
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> fail(Code code, String message) {
        return new R().failure(code.getCode(), message);
    }

    /**
     * 失败 返回结果
     *
     * @param data 返回结果
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> fail(T data) {
        return new R(data).failure();
    }

    /**
     * 失败 返回结果 & 自定义提示信息
     *
     * @param data 返回结果
     * @return Response
     */
    @SuppressWarnings("unchecked")
    public static <T> R<T> fail(T data, String message) {
        return new R(data).failure(message);
    }

    /**
     * 构造函数
     *
     * @param data 数据
     */
    private R(T data) {
        this.data = data;
    }

    /**
     * 成功
     *
     * @return Response
     */
    private R success() {
        this.ok = true;
        this.code = Code.OK.getCode();
        this.message = CommonConstant.Response.OK;
        return this;
    }

    /**
     * 成功 自定义提示信息
     *
     * @param message 成功提示信息
     * @return Response
     */
    private R success(String message) {
        this.ok = true;
        this.code = Code.OK.getCode();
        this.message = message;
        return this;
    }

    /**
     * 成功 自定义提示信息
     *
     * @param code    Code
     * @param message 成功提示信息
     * @return Response
     */
    private R success(int code, String message) {
        this.ok = true;
        this.code = code;
        this.message = message;
        return this;
    }

    /**
     * 失败
     *
     * @return Response
     */
    private R failure() {
        this.ok = false;
        this.code = Code.FAILURE.getCode();
        this.message = CommonConstant.Response.ERROR;
        return this;
    }

    /**
     * 失败 自定义提示信息
     *
     * @param message 错误提示信息
     * @return Response
     */
    private R failure(String message) {
        this.ok = false;
        this.code = Code.FAILURE.getCode();
        this.message = message;
        return this;
    }

    /**
     * 失败 自定义提示信息
     *
     * @param code    Code
     * @param message 错误提示信息
     * @return Response
     */
    private R failure(int code, String message) {
        this.ok = false;
        this.code = code;
        this.message = message;
        return this;
    }

    public enum Code {
        OK(200), FAILURE(500), NotFound(3404);

        @Getter
        private int code;

        Code(int code) {
            this.code = code;
        }
    }
}
