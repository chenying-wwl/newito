

package com.qf.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 Empty 异常
 *
 * @author : 千锋健哥
 */
public class EmptyException extends RuntimeException {
    public EmptyException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}
