/**
 * Created by s-gheldd on 12/7/15.
 */
public class Plane {
    private static int serial = 0;
    private final int number;

    Plane(){
        this.number=serial;
        serial++;
    }

    public int getNumber(){
        return this.number;
    }

    public void approachRunway(){
        System.out.println("Plane " + this.number + " approaching runway!");
    }

    public void landAndClearRunway(){
        System.out.println("Plane " + this.number + " has landed!");
    }

    public void approachParkingPosition(){
        System.out.println("Plane " + this.number + " approaches parking position!");
    }

    public void park(){
        final long parkTime = (long)(Math.random() * 101);
        System.out.println("Plane "+ this.number + " is parking for " + parkTime + " millis!");
        try {
            Thread.sleep(parkTime);
        } catch (Exception ex){}
    }

    public void readyForTakeoff(){
        System.out.println("Plane "+ this.number +" is ready for takeoff!");
    }

    public void starting(){
        System.out.println("Plane "+ this.number + " is starting!");
    }

    public void takeoff(){
        System.out.println("Plane " + this.number + " took off!");
    }

}