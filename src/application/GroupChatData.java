package application;

import java.io.Serializable;

public class GroupChatData implements Serializable {
    String groupId;//群聊id
    MemoryUserApplication sender;//发送者
    String sendTime;//发送时间
    String sendMessage;//发送的信息
    String messageType;//信息的种类

    public GroupChatData(String groupId, MemoryUserApplication sender, String sendTime, String sendMessage, String messageType) {
        this.groupId = groupId;
        this.sender = sender;
        this.sendTime = sendTime;
        this.sendMessage = sendMessage;
        this.messageType = messageType;
    }

    public GroupChatData() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public MemoryUserApplication getSender() {
        return sender;
    }

    public void setSender(MemoryUserApplication sender) {
        this.sender = sender;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
