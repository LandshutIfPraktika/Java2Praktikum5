import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class PingPong {

    public static void  main(String[] args) {
        final boolean client = args.length>0 && args[0].equals("client");
        try (Socket socket = client ? new Socket("localhost",2000):new ServerSocket(2000).accept()){
            if (client){
                send(socket.getOutputStream(),1);
            }
            int ping_pong = 1;
            while (ping_pong != 0){
                ping_pong = receive(socket.getInputStream());
                send(socket.getOutputStream(),ping_pong+1);
            }


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }



    /**
     * Ein Byte auf den Stream schreiben.
     *
     * @param outputStream Stream, offen zum Schreiben.
     * @param number       Byte, das auf den Stream geschrieben wird.
     * @throws IOException Bad.
     */
    private static void send(final OutputStream outputStream, final int number) throws IOException {
        outputStream.write(number);
        outputStream.flush();
    }

    private static int receive(final InputStream inputStream) throws IOException {
        int pong = inputStream.read();
        System.out.println("Pong: " + pong);
        return pong;

    }
}
