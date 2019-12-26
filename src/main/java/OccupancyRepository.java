import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OccupancyRepository {
    Map<Availability, ArrayList> parkingAvailabilityStatus;

    public OccupancyRepository() {
        this.setInitialParkingStatus();
    }

    public void setInitialParkingStatus() {
        this.parkingAvailabilityStatus = new HashMap<>();
        ArrayList<Integer> unoccupiedSlots = new ArrayList(100);
        ArrayList<Car> occupiedSlots = new ArrayList();
        for (Integer i = 1; i <= 100; i++) {
            unoccupiedSlots.add(i);
        }
        this.parkingAvailabilityStatus.put(Availability.UNOCCUPIED, unoccupiedSlots);
        this.parkingAvailabilityStatus.put(Availability.OCCUPIED, occupiedSlots);
    }

    public void unParkUpdate(Car car) {
        this.parkingAvailabilityStatus.get(Availability.OCCUPIED).remove(car);
        this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).add(car.parkingSlot);
        Collections.sort(this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED));

    }

    public void parkUpdate(Car car) {
        this.parkingAvailabilityStatus.get(Availability.OCCUPIED).add(car);
    }


    public int getNearestParkingSlot() throws ParkingLotException {
        try {
            return (Integer) this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ParkingLotException("No parking space available!!", ParkingLotException.ExceptionType.PARKING_FULL);
        }
    }

    public int getAvailableSlots() {
        return this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size();
    }
}
