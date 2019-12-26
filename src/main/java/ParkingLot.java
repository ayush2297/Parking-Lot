import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public Car[] parkingSpace;
    Map<Availability, ArrayList> parkingAvailabilityStatus;

    public ParkingLot() {
        this.parkingSpace = new Car[100];
        this.setInitialParkingStatus();
    }

    public void setInitialParkingStatus() {
        this.parkingAvailabilityStatus = new HashMap<>();
        ArrayList<Integer> unoccupiedSlots = new ArrayList(100);
        ArrayList<Integer> occupiedSlots = new ArrayList();
        for (int i = 1; i <= 100; i++) {
            unoccupiedSlots.add(i);
        }
        this.parkingAvailabilityStatus.put(Availability.UNOCCUPIED, unoccupiedSlots);
        this.parkingAvailabilityStatus.put(Availability.OCCUPIED, occupiedSlots);
    }

    public int getParkingSize() {
        return this.parkingSpace.length;
    }

    public int getCountOfAvailableSlots() {
        return this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size();
    }

    public int getNearestParkingSlot() {
        int nearestAllottedSlot = (int) this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).remove(0);
        this.parkingAvailabilityStatus.get(Availability.OCCUPIED).add(nearestAllottedSlot);
        Collections.sort(this.parkingAvailabilityStatus.get(Availability.OCCUPIED));
        return nearestAllottedSlot;
    }

    public void parkTheCar(Car car) {
        int nearestParkingSlot = this.getNearestParkingSlot();
        this.parkingSpace[nearestParkingSlot-1]=car;
    }
}
