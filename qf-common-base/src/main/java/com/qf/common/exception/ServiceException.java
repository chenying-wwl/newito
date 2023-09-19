

package com.qf.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 服务 异常
 *
 * @author : 千锋健哥
 */
public class ServiceException extends RuntimeException {
    public ServiceException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}
