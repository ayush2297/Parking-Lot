import java.util.*;

public class ParkingLot {
    public Car[] parkingSpace;
    Map<Availability, ArrayList> parkingAvailabilityStatus;
    public boolean isParkingFull = false;

    public ParkingLot() {
        this.parkingSpace = new Car[100];
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

    public int getParkingSize() {
        return this.parkingSpace.length;
    }

    public int getCountOfAvailableSlots() {
        return this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size();
    }

    public int getNearestParkingSlot() throws ParkingLotException {
        try {
            return (Integer) this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).remove(0);
        } catch (IndexOutOfBoundsException e) {
            this.isParkingFull = true;
            throw new ParkingLotException("No parking space available!!", ParkingLotException.ExceptionType.PARKING_FULL);
        }
    }

    public void parkTheCar(Car car) throws ParkingLotException {
        this.isParkingSpaceAvailable();
        int nearestParkingSlot = this.getNearestParkingSlot();
        car.parkingSlot = nearestParkingSlot;
        this.parkingAvailabilityStatus.get(Availability.OCCUPIED).add(car);
        this.parkingSpace[nearestParkingSlot - 1] = car;
    }

    public void unParkTheCar(Car car) {
        this.parkingSpace[car.parkingSlot - 1] = null;
        this.parkingAvailabilityStatus.get(Availability.OCCUPIED).remove(car);
        this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).add(car.parkingSlot);
        Collections.sort(this.parkingAvailabilityStatus.get(Availability.UNOCCUPIED));
        this.isParkingFull = false;
    }

    private void isParkingSpaceAvailable() throws ParkingLotException {
        if (isParkingFull) {
            this.isParkingFull = true;
            throw new ParkingLotException("No parking space available!!", ParkingLotException.ExceptionType.PARKING_FULL);
        }
    }

}
