package toolkind;

public class Friends {
    public String avatar;//头像
    public String uname;//用户名
    public Friends() {
    }

    public Friends(String avatar, String uname) {
        this.avatar = avatar;
        this.uname = uname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
