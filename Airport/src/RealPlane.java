/**
 * Created by s-gheldd on 12/7/15.
 */
public class RealPlane extends Plane implements Runnable {

    private final RealRunway runway;
    private boolean killed = false;

    RealPlane(RealRunway runway){
        this.runway = runway;
    }

    public void kill(){
        this.killed = true;
        System.out.println("Plane "+ this.getNumber() + " crashed!");
    }


    @Override
    public void run() {
        while (!killed) {

            if (this.runway.isFull()) {
                try {
                    wait();
                } catch (Exception ex){}
            } else {
                synchronized (runway){
                    this.approachRunway();
                    this.landAndClearRunway();
                    this.runway.arrive();
                }
            }
            this.approachParkingPosition();
            this.park();
            this.readyForTakeoff();
            synchronized (runway){
                this.starting();
                this.takeoff();
                this.runway.depart();
            }
        }
    }
}
