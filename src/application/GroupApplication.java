package application;

import java.io.Serializable;

public class GroupApplication implements Serializable {
    private String group_id;
    private int group_level;
    private String group_avatar;
    private String group_name;
    private String group_data;
    private String group_time;

    public GroupApplication() {
    }

    public GroupApplication(String group_id, int group_level, String group_avatar, String group_name, String group_data, String group_time) {
        this.group_id = group_id;
        this.group_level = group_level;
        this.group_avatar = group_avatar;
        this.group_name = group_name;
        this.group_data = group_data;
        this.group_time = group_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getGroup_level() {
        return group_level;
    }

    public void setGroup_level(int group_level) {
        this.group_level = group_level;
    }

    public String getGroup_avatar() {
        return group_avatar;
    }

    public void setGroup_avatar(String group_avatar) {
        this.group_avatar = group_avatar;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_data() {
        return group_data;
    }

    public void setGroup_data(String group_data) {
        this.group_data = group_data;
    }

    public String getGroup_time() {
        return group_time;
    }

    public void setGroup_time(String group_time) {
        this.group_time = group_time;
    }
}
