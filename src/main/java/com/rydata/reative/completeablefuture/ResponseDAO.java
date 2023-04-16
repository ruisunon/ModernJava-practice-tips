package com.rydata.reative.completeablefuture;

public class ResponseDAO {
    private String email;
    private Boolean isSent;
    public ResponseDAO(){
    }

    public ResponseDAO(String email, Boolean isSent) {
        this.email = email;
        this.isSent = isSent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }
}
