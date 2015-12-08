import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 GET /colorpage/r/g/b HTTP/1.x
 Weitere, nicht-leere Textzeilen...
 [Leerzeile]
 */
public class FarbServer implements Runnable {
    private final static String responseHeader = "HTTP/1.0 200 OK";
    private final String responseStart = "<html><body bgcolor=\"#";
    private final static String responseEnd = "\"></body></html>";

    private final static String badRequest = "HTTP/1.0 404 NOT FOUND";


    private final Socket socket;

    FarbServer(final Socket socket) {
        //System.out.println("server created");
        this.socket = socket;
    }


    @Override
    public void run() {
        try (PrintWriter to = new PrintWriter(socket.getOutputStream());
             BufferedReader from = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            final String request = from.readLine();
            if (checkRequest(request)) {
                //while(from.readLine()!=null){;}
                final String[] arguments = request.split(" ")[1].split("/");
                final int red = Integer.parseInt(arguments[2]);
                final int green = Integer.parseInt(arguments[3]);
                final int blue = Integer.parseInt(arguments[4]);
                if (rightSize(red) && rightSize(green) && rightSize(blue)) {
                    to.println(responseHeader);
                    to.println("");
                    to.print(responseStart);
                    to.print(String.format("%02X", red) + String.format("%02X", green) + String.format("%02X", blue));
                    to.println(responseEnd);
                    to.flush();
                } else {
                    to.println(badRequest);
                    to.println("");
                    to.flush();
                }

            } else {
                to.println(badRequest);
                to.println("");
                to.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private boolean checkRequest(final String request) {
        return request.matches("GET /colorpage/\\d{1,3}/\\d{1,3}/\\d{1,3} HTTP/1\\.\\d+");
    }

    private boolean rightSize(int colour) {

        return colour >= 0 && colour < 256;

    }
}
