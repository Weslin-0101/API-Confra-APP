package com.confra.api.docs.schemas;

import com.confra.api.docs.util.ErrorMessageUtil;
import io.swagger.v3.oas.annotations.media.Schema;

public class InternalServerErrorSchema extends ErrorMessageUtil {
    public InternalServerErrorSchema(String code, String message) {
        super(code, message);
    }

    @Override
    @Schema(description = "Internal Server Error", example = "500")
    public String getCode() {
        return super.getCode();
    }
}
