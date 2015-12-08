import java.util.ArrayList;
import java.util.List;

/**
 * Created by s-gheldd on 12/7/15.
 */
public class MainAirport {

    public static void main(String[] args) {
        final RealRunway MUC = new RealRunway();
        final List<Thread> planeThreads = new ArrayList<>();
        final List<RealPlane> planes = new ArrayList<>();
        for (int i = 0; i < 20 ; i++){
            final RealPlane plane = new RealPlane(MUC);
            planeThreads.add(new Thread(plane));
            planes.add(plane);
        }

        planeThreads.stream().forEach(Thread::start);

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        planes.stream().forEach(RealPlane::kill);
    }
}
