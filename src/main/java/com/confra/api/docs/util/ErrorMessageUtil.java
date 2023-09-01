package com.confra.api.docs.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessageUtil {
    private String code;
    private String message;
}
