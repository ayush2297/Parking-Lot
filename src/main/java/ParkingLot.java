import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car[] parkingSlots;
    Map<Availability, ArrayList> repository;

    public ParkingLot() {
        this.parkingSlots = new Car[100];
        this.setInitialParkingStatus();
    }

    public void setInitialParkingStatus() {
        this.repository = new HashMap<>();
        ArrayList unoccupiedSlots = new ArrayList(100);
        ArrayList occupiedSlots = new ArrayList();
        for (int i = 0; i < 100; i++) {
            unoccupiedSlots.add(i);
        }
        this.repository.put(Availability.UNOCCUPIED,unoccupiedSlots);
        this.repository.put(Availability.OCCUPIED,occupiedSlots);
    }

    public int getParkingSize()
    {
        return this.parkingSlots.length;
    }

    public int getAvailableSlots() {
        return this.repository.get(Availability.UNOCCUPIED).size();
    }
}
