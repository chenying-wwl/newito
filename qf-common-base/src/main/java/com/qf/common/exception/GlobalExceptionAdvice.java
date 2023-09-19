

package com.qf.common.exception;

import com.qf.common.bean.R;
import com.qf.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author : 千锋健哥
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * Global Exception
     *
     * @param exception Exception
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R globalException(Exception exception) {
        log.error("Global Exception Handler: {}", exception.getMessage(), exception);
        return R.fail(exception.getMessage());
    }

    /**
     * UnAuthorized Exception
     *
     * @param unAuthorizedException UnAuthorizedException
     * @return R
     */
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R unAuthorizedException(UnAuthorizedException unAuthorizedException) {
        log.warn("UnAuthorized Exception Handler: {}", unAuthorizedException.getMessage(), unAuthorizedException);
        return R.fail(unAuthorizedException.getMessage());
    }

    /**
     * Validation Exception
     *
     * @param exception MethodArgumentNotValidException
     * @return R
     */
    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public R methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        HashMap<String, String> map = new HashMap<>(16);
        List<FieldError> errorList = exception.getBindingResult().getFieldErrors();
        errorList.forEach(error -> {
            log.warn("Method Argument Not Valid Exception Handler: {}({})", error.getField(), error.getDefaultMessage());
            map.put(error.getField(), error.getDefaultMessage());
        });
        return R.fail(JsonUtil.toJsonString(map));
    }

}
