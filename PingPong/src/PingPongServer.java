import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class PingPongServer {

    public static void main(String[] args) {

        try (Socket socket = new ServerSocket(2000).accept()){
            int pong =receive(socket.getInputStream());
            while (pong > 0){
                send(socket.getOutputStream(),pong+1);
                pong = receive(socket.getInputStream());
            }

        } catch (Exception ex) {
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
