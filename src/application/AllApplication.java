package application;

import java.io.Serializable;

public class AllApplication<T> implements Serializable {
    private String flag;
    private T data;

    public AllApplication() {
    }

    public AllApplication(String flag, T data) {
        this.flag = flag;
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
