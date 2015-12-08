/**
 * Created by s-gheldd on 12/7/15.
 */
public class RealRunway extends Runway {
    private final int gates = 10;
    private int filled = 0;

    synchronized public boolean isFull(){
        return filled<gates;
    }

    @Override
    synchronized public void arrive(){
        filled++;
        super.arrive();
    }

    @Override
    synchronized public void depart(){
        filled--;
        this.notifyAll();
        super.depart();
    }
}
