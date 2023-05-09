package client.tool;



import application.MemoryUserApplication;

import java.util.ArrayList;
import java.util.Iterator;

public class FriendListDelete {
    public static void delete(ArrayList<MemoryUserApplication> list, String account)
    {
        Iterator<MemoryUserApplication> it=list.iterator();

        while(it.hasNext())
        {
            MemoryUserApplication memoryUserApplication=it.next();
            if(memoryUserApplication.getAccount().equals(account))
            {
                it.remove();
            }
        }
    }
}
