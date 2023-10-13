package com.confra.api.docs.schemas;

import com.confra.api.docs.util.ErrorMessageUtil;
import io.swagger.v3.oas.annotations.media.Schema;

public class NotFoundSchema extends ErrorMessageUtil {
    public NotFoundSchema(String code, String message) {
        super(code, message);
    }

    @Override
    @Schema(example = "404", description = "Not Found")
    public String getCode() {
        return super.getCode();
    }
}
