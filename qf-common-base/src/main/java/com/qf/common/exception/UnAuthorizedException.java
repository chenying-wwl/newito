

package com.qf.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 未授权 异常
 *
 * @author : 千锋健哥
 */
public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}
