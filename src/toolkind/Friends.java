package toolkind;

import application.MemoryUserApplication;
import client.view.PersonalData;

public class Friends {
    private String flag;
    private MemoryUserApplication user;
    public Friends() {
    }

    public Friends(String flag, MemoryUserApplication user) {
        this.flag = flag;
        this.user = user;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public MemoryUserApplication getUser() {
        return user;
    }

    public void setUser(MemoryUserApplication user) {
        this.user = user;
    }
}
