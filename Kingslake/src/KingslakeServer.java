import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class KingslakeServer {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private final int port = 57777;
    private final char decorator;


    public static void main(String[] args) {
        if (args.length>0){
            KingslakeServer kingslakeServer = new KingslakeServer(args[0].charAt(0));
            kingslakeServer.listenAndServe();
        }
    }

    KingslakeServer(char decorator) {
        this.decorator = decorator;
    }

    public void listenAndServe() {
        try (Socket socket = new ServerSocket(port).accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.socket = socket;
            this.reader = reader;
            this.writer = writer;

            String challenge = reader.readLine();
            while (!challenge.equals("\0")){
                writeAnswer(challenge);
                challenge = reader.readLine();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void writeAnswer(String challange) {
        String answer = this.decorator + challange + this.decorator;
        try {

            for (byte x :
                    challange.getBytes()) {
                writer.write(answer);
                writer.newLine();
                writer.flush();
                Thread.sleep(1000);
            }
            writer.write("\0");
            writer.newLine();
            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
