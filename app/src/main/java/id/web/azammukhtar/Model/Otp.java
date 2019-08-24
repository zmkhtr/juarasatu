package id.web.azammukhtar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Otp {
    @SerializedName("msgId")
    @Expose
    private String msgId;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("maxAttempt")
    @Expose
    private String maxAttempt;
    @SerializedName("expireIn")
    @Expose
    private int expireIn;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMaxAttempt() {
        return maxAttempt;
    }

    public void setMaxAttempt(String maxAttempt) {
        this.maxAttempt = maxAttempt;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
