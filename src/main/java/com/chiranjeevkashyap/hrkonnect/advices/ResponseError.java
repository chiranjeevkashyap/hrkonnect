package com.chiranjeevkashyap.hrkonnect.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ResponseError {
    private HttpStatus status;
    private String error;
    private List<String> errors;
}
