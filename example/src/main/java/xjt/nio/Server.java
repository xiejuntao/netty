package xjt.nio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server extends BaseIO{
    public static void main(String[] args){
        ServerSocketChannel serverSocketChannel;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("localhost",7777));
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                int n = selector.select();
                print("n="+n);
                if(n>0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                        }
                        if(selectionKey.isReadable()){
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                            int i = socketChannel.read(byteBuffer);
                            if(i!=-1){
                                String msg = new String(byteBuffer.array()).trim();
                                print("server received message: %s",msg);
                                String replyMsg = msg.toUpperCase();
                                socketChannel.write(ByteBuffer.wrap(replyMsg.getBytes()));
                                print("server reply upper message: %s",replyMsg);
                            }
                        }
                        iterator.remove();
                    }
                    continue;
                }
                //Set<SelectionKey>
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
