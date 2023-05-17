package application;

import java.io.Serializable;

public class ChatData implements Serializable {
    String message;
    String sendTime;
    String sendUser;
    String receiver;
    String message_type;
    public ChatData() {
    }
    public ChatData(String message, String sendTime, String sendUser, String receiver, String message_type) {
        this.message = message;
        this.sendTime = sendTime;
        this.sendUser = sendUser;
        this.receiver = receiver;
        this.message_type = message_type;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }
}
