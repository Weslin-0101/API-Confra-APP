package com.confra.api.docs.schemas;

import com.confra.api.docs.util.ErrorMessageUtil;
import io.swagger.v3.oas.annotations.media.Schema;

public class UnauthorizedSchema extends ErrorMessageUtil {
    public UnauthorizedSchema(String code, String message) {
        super(code, message);
    }

    @Override
    @Schema(description = "Unauthorized", example = "401")
    public String getCode() {
        return super.getCode();
    }
}
