package com.bangjwo.global.common.exception;

import com.chunsun.template.common.error.ErrorCode;

public class TemplateException extends BusinessException {
    public TemplateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
