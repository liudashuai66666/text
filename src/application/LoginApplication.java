package application;

import java.io.Serializable;

public class LoginApplication implements Serializable {
    private String Account;
    private String password;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String Account) {
        this.Account = Account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginApplication() {
    }

    public LoginApplication(String Account, String password) {
        this.Account = Account;
        this.password = password;
    }
}
