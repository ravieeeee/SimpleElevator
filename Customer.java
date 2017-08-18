import java.util.Random;


public class Customer {
    private int curFloor;
    private int dstFloor;
    private boolean inElevator = false;
    private boolean finished;


    public int getCurFloor() {
        return curFloor;
    }

    public int getDstFloor() {
        return dstFloor;
    }

    public void setFloor(int buildingFloor) {
        while(true) {
            Random random = new Random();
            curFloor = random.nextInt(buildingFloor+1);
            dstFloor = random.nextInt(buildingFloor+1);

            if (dstFloor != curFloor && curFloor > 0 && dstFloor > 0) {
                break;
            }
        }
    }


    public boolean isInElevator() {
        return inElevator;
    }

    public void setInElevator(boolean inElevator) {
        this.inElevator = inElevator;
    }



    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finshed) {
        this.finished = finshed;
    }

}
