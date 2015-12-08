import java.io.*;
import java.net.Socket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class PingPongClient {

    public static void main(String[] args)  {
        try (Socket socket = new Socket("localhost", 2000)
        ) {
            int ping = 1;
            socket.getOutputStream().write(1);
            while (ping != 0) {
                ping = receive(socket.getInputStream());
                send(socket.getOutputStream(), ping + 1);
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
        int ping = inputStream.read();
        System.out.println("Ping: " + ping);
        return ping;
    }


}
