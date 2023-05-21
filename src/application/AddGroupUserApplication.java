package application;

import java.io.Serializable;

public class AddGroupUserApplication implements Serializable {
    private String group_id;
    private String newUser_id;

    public AddGroupUserApplication() {
    }

    public AddGroupUserApplication(String group_id, String newUser_id) {
        this.group_id = group_id;
        this.newUser_id = newUser_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getNewUser_id() {
        return newUser_id;
    }

    public void setNewUser_id(String newUser_id) {
        this.newUser_id = newUser_id;
    }
}
