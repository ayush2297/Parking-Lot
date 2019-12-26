import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void givenAParkingLot_ShouldReturn100() {
        ParkingLot parkingLot = new ParkingLot();
        int parkingSize = parkingLot.getParkingSize();
        Assert.assertEquals(100,parkingSize);
    }

    @Test
    public void givenAnEmptyParkingLot_ShouldReturn100AvailableSlots() {
        ParkingLot parkingLot = new ParkingLot();
        int slots = parkingLot.getAvailableSlots();
        Assert.assertEquals(100,slots);
    }

    @Test
    public void givenAnEmptyParkingLot_ShouldReturnFirstSlotWhenAskedToGiveASlot() {
        ParkingLot parkingLot = new ParkingLot();
        int slots = parkingLot.getNearestParkingSlot();
        Assert.assertEquals(1,slots);
    }

}

