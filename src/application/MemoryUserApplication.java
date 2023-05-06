package application;

import java.io.Serializable;

public class MemoryUserApplication implements Serializable {
    private String account;//账号
    private String mailbox;//邮箱
    private String uname;//用户名
    private String sex;//性别
    private String birthday;//生日
    private String signature;//个性签名
    private String age;//年龄
    private String avatar;//头像
    public MemoryUserApplication() {
    }
    public MemoryUserApplication(String uname, String account, String sex, String mailbox, String birthday, String signature, String age,String avatar) {
        this.uname = uname;
        this.account = account;
        this.sex = sex;
        this.mailbox = mailbox;
        this.birthday = birthday;
        this.signature = signature;
        this.age = age;
        this.avatar=avatar;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getMailbox() {
        return mailbox;
    }
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
