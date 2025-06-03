package com.edu.famBridge.dto;

public class Responsedto {
        private String message;
        private boolean success;

        public Responsedto(String message, boolean success) {
            this.message = message;
            this.success = success;
        }

        // Getters and Setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

