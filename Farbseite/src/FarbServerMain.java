import java.net.ServerSocket;

/**
 * Created by s-gheldd on 12/8/15.
 */
public class FarbServerMain {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            while (true){
                new Thread(new FarbServer(serverSocket.accept())).start();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
