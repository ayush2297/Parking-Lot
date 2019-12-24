import org.junit.Assert;
import org.junit.Test;

public class ParkingLotManagerTest {

    @Test
    public void givenAnEmptyParkingLot_ShouldReturn100() {
        ParkingLot parkingLot = new ParkingLot();
        int parkingSize = parkingLot.getParkingSize();
        Assert.assertEquals(100,parkingSize);
    }

}
