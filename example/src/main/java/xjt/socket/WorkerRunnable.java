package xjt.socket;

import xjt.nio.BaseIO;

import java.io.*;
import java.net.Socket;

public class WorkerRunnable extends BaseIO implements Runnable{
    public Socket socket;
    public BufferedReader socketReader;
    public BufferedWriter socketWriter;
    public WorkerRunnable(Socket socket) throws IOException {
        this.socket = socket;
        this.socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    @Override
    public void run() {
        try {
            String str;
            while((str=this.socketReader.readLine())!=null){
                try {
                    print("read str: %s", str);
                    String outStr = str.toUpperCase();
                    this.socketWriter.write(outStr);
                    print("write outStr: %s", outStr);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
