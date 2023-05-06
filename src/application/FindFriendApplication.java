package application;

import java.io.Serializable;

public class FindFriendApplication implements Serializable {
    private String Account;

    public FindFriendApplication() {
    }

    public FindFriendApplication(String account) {
        Account = account;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
