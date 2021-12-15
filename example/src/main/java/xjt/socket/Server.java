package xjt.socket;

import xjt.nio.BaseIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server extends BaseIO {
    public static void main(String[] args) throws IOException {
        Executor executor = Executors.newFixedThreadPool(100);
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            print("waiting accept");
            Socket socket = serverSocket.accept();
            print("accepted connection");
            executor.execute(new WorkerRunnable(socket));
        }
    }
}
