public class ParkingLot {
    private Car[] parkingSlots;

    public ParkingLot() {
        this.parkingSlots = new Car[100];
    }

    public int getParkingSize() {
        return this.parkingSlots.length;
    }
}
