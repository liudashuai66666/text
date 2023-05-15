package application;

import java.io.Serializable;

public class ImageApplication implements Serializable {
    private int flag;//标记，1代表表情包，2代表图片
    private String imageName;
    private byte[] bytes;
    private String sender;//发送者
    private String receiver;//接收者

    public ImageApplication() {
    }

    public ImageApplication(int flag,String imageName, byte[] bytes, String sender, String receiver) {
        this.flag=flag;
        this.imageName = imageName;
        this.bytes = bytes;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
