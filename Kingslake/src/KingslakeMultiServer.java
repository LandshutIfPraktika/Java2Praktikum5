import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class KingslakeMultiServer implements Runnable {
    private static char decorator;
    private final static int port = 57777;

    private final Socket socket;


    public static void main(String[] args) {
        if (args.length > 0) {
            decorator = args[0].charAt(0);
            try (final ServerSocket serverSocket = new ServerSocket(port)) {
                while (true){
                    new Thread(new KingslakeMultiServer(serverSocket.accept())).start();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }

    KingslakeMultiServer(final Socket socket) {
        this.socket = socket;
        System.out.println("Server created");
    }

    @Override
    public void run() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            final String challenge = reader.readLine();
            final String answer = decorator + challenge + decorator;
            for (char x : challenge.toCharArray()) {
                writer.println(answer);
                writer.flush();
                Thread.sleep(1000);
            }
            writer.println("\0");
            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
