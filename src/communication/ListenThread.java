package src.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListenThread extends Thread  {
    BufferedReader in;

    ICommunication master;
    boolean isRunning = true;
    public ListenThread(BufferedReader in, ICommunication master) {
        this.in = in;

        this.master = master;
    }

    public void run(){
        String msg;

        while (isRunning) {

            try {
                msg = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            master.notify(msg);



        }
        System.out.println("Stopped");
    }
}
