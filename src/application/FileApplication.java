package application;

import java.io.Serializable;

public class FileApplication implements Serializable {
    private int flag;//标记，1代表表情包，2代表图片，3代表群聊表情包，4代表群聊图片
    private String fileName;//文件名
    private byte[] bytes;//文件内容
    private String sender;//发送者
    private String receiver;//接收者

    public FileApplication() {
    }

    public FileApplication(int flag, String fileName, byte[] bytes, String sender, String receiver) {
        this.flag = flag;
        this.fileName = fileName;
        this.bytes = bytes;
        this.sender = sender;
        this.receiver = receiver;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
