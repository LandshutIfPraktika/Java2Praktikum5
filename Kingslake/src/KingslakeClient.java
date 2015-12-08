import java.io.*;
import java.net.Socket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class KingslakeClient {
    public static void main(String[] args) {

        if (args.length>0) {
            try (Socket socket = new Socket("localhost", 57777);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(),true)) {

                writer.println(args[0]);
                writer.flush();
                String answer;
                while (!(answer=reader.readLine()).equals("\0")){
                    System.out.println(answer);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
