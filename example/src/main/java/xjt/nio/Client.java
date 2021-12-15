package xjt.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client extends BaseIO{
    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 7777))) {
            while (true) {
                print("please write sth");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferedReader.readLine();
                ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());
                socketChannel.write(byteBuffer);
                print("send msg: %s",s);
                byteBuffer.clear();
                socketChannel.read(byteBuffer);
                print("received msg: %s",new String(byteBuffer.array()).trim());
            }
        }
    }
}
