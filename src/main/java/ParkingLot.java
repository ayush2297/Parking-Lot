public class ParkingLot {
    private OccupancyRepository occupancyStatus;
    public Car[] parkingSpace;
    public boolean isParkingFull = false;

    public ParkingLot() {
        this.parkingSpace = new Car[100];
        this.occupancyStatus = new OccupancyRepository();
    }

    public int getParkingSize() {
        return this.parkingSpace.length;
    }

    public int getCountOfAvailableSlots() {
        return this.occupancyStatus.getAvailableSlots();
    }

    public void parkTheCar(Car car) throws ParkingLotException {
        this.isParkingSpaceAvailable();
        int nearestParkingSlot = this.getNearestParkingSlot();
        car.parkingSlot = nearestParkingSlot;
        this.occupancyStatus.parkUpdate(car);
        this.parkingSpace[nearestParkingSlot - 1] = car;
    }

    int getNearestParkingSlot() throws ParkingLotException {
        try {
            return this.occupancyStatus.getNearestParkingSlot();
        } catch (ParkingLotException e) {
            this.isParkingFull = true;
            throw e;
        }
    }

    public void unParkTheCar(Car car) {
        this.parkingSpace[car.parkingSlot - 1] = null;
        this.occupancyStatus.unParkUpdate(car);
        this.isParkingFull = false;
    }

    private void isParkingSpaceAvailable() throws ParkingLotException {
        if (isParkingFull) {
            throw new ParkingLotException("No parking space available!!", ParkingLotException.ExceptionType.PARKING_FULL);
        }
    }
}
