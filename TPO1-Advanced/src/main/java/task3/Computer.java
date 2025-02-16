package task3;

import org.w3c.dom.events.EventException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Computer {
    private Size size;
    private Location location;

    public int batteryCharge;
    public boolean isFunctional;

    public Computer(Size size, Location location) {
        this.size = size;
        this.location = location;
        this.isFunctional = true;
        this.batteryCharge = new Random().nextInt(101);
    }

    public Computer(Size size, Location location, boolean functionality) {
        this.size = size;
        this.location = location;
        this.isFunctional = functionality;
        this.batteryCharge = new Random().nextInt(101);
    }

    public Computer(Size size, Location location, int batteryCharge, boolean isFunctional) {
        this.size = size;
        this.location = location;
        this.batteryCharge = validateBatteryCharge(batteryCharge);
        this.isFunctional = isFunctional;
    }

    public void chargeUp() {
        //TimeUnit.SECONDS.sleep(20);
        this.batteryCharge += 1;
        checkFunctionality();
    }

    public void chargeDown() {
        //TimeUnit.SECONDS.sleep(10);
        this.batteryCharge -= 1;
        checkFunctionality();
    }

//    public void chargeDown(int articleVolume) throws InterruptedException {
//        int chargeLoss = Math.max(1, articleVolume / 10);
//        this.batteryCharge -= chargeLoss;
//        checkFunctionality();
//    }


    public void chargeFull() {
        while (batteryCharge < 100){
            chargeUp();
        }
    }

    public boolean checkFunctionality() {
        if (batteryCharge == 0) {
            isFunctional = false;
        } else if (!isFunctional && batteryCharge > 0) {
           isFunctional = false;
        }  else {
            isFunctional = true;
        }

        return isFunctional;
    }

    public int getBatteryCharge() {
        return batteryCharge;
    }

    private int validateBatteryCharge(int batteryCharge){
        if (batteryCharge > 100){
            batteryCharge = 100;
        } else if (batteryCharge < 0){
            batteryCharge = 0;
        }

        return batteryCharge;
    }

    public void setBatteryCharge(int batteryCharge) {
        this.batteryCharge = validateBatteryCharge(batteryCharge);
    }

    public int work(int articleVolume) {
        int writtenVolume = 0;
        while (isFunctional) {
            chargeDown();
            writtenVolume++;
            if (writtenVolume >= articleVolume){
                break;
            }
        }

        return writtenVolume;
    }
}
