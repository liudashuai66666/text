package client.vo;

import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Scope;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;

public class User {
    public static String account;//账号
    public static String mailbox;//邮箱
    public static String sex;//性别
    public static String uname;//用户名
    public static String birthday;//生日
    public static String signature;//个性签名
    public static String age;//年龄
    public static String avatar;//头像
    public static Socket socket;//与服务端的连接
    public static int level;//当前群聊的身份
}