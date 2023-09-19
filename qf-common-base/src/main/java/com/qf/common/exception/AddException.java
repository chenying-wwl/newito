

package com.qf.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 新增数据 异常
 *
 * @author : 千锋健哥
 */
public class AddException extends RuntimeException {
    public AddException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}
