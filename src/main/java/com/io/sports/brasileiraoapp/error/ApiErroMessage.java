package com.io.sports.brasileiraoapp.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
public class ApiErroMessage implements Serializable {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public Instant getTimestamp() {
        return timestamp;
    }

    public ApiErroMessage setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ApiErroMessage setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public ApiErroMessage setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErroMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiErroMessage setPath(String path) {
        this.path = path;
        return this;
    }
}
