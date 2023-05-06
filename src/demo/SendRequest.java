package demo;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * sendrequest 反馈类型为int
 * sendrequestString 反馈类型为String
 */
public class SendRequest {
    //创建客户端对象

    public static Object sendrequest(Object obj) throws Exception {
        Socket s = new Socket("127.0.0.1",7777);

        ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());

        oos.writeObject(obj);

        //接收反馈，能否注册
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
        obj=ois.readObject();

        ois.close();
        oos.close();
        //s.close();
        return obj;
    }
}
