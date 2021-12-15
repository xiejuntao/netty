package xjt.socket;

import xjt.nio.BaseIO;

import java.io.*;
import java.net.Socket;

public class Client extends BaseIO {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9999);
        print("connected");
        String w = "a";
            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            socketWriter.write(w);
            socketWriter.flush();
            socket.shutdownOutput();
            print("write %s",w);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String rs = socketReader.readLine();
            print("read %s",rs);
    }
}
