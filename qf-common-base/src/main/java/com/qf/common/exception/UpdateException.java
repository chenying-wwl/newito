

package com.qf.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 修改数据 异常
 *
 * @author : 千锋健哥
 */
public class UpdateException extends RuntimeException {
    public UpdateException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}
