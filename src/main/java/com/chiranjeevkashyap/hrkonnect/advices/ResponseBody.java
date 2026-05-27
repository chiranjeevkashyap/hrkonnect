package com.chiranjeevkashyap.hrkonnect.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBody<T> {
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private T data;
    private ResponseError error;

    public ResponseBody() {
        this.timestamp = LocalDateTime.now();
    }

    public ResponseBody(ResponseError error) {
        this();
        this.error = error;
    }

    public ResponseBody(T data) {
        this();
        this.data = data;
    }
}
