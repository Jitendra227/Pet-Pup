package com.jitendra.petpup.model.data;

public class BreedImages {
    String message;

    public BreedImages(String message, String status) {
        this.message = message;
        this.status = status;
    }

    String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
