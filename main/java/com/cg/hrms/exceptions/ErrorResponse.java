package com.cg.hrms.exceptions;

import java.util.Objects;

public class ErrorResponse {

    private final int status;
    private final String message;
    private final long timestamp;

    private ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.timestamp = builder.timestamp;
    }

    // Getters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Builder Class
    public static class Builder {
        private int status;
        private String message;
        private long timestamp;

        public Builder() {
            this.timestamp = System.currentTimeMillis();
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponse build() {
            // Check that all required fields are set
            Objects.requireNonNull(status, "Status must not be null");
            Objects.requireNonNull(message, "Message must not be null");

            return new ErrorResponse(this);
        }
    }
}
