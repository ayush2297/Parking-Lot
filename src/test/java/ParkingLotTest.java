import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

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

    @Test
    public void given2CarsWithSameDetails_ShouldReturnEqual() {
        Car car = new Car("MH 01 0001","BMW","Black");
        Car expectedCar = new Car("MH 01 0001","BMW","Black");
        Assert.assertEquals(expectedCar,car);
    }

    @Test
    public void givenParkingTime2PMAndUnParkingTime5PM_ShouldReturnParkingTimeAs3Hrs() {
        Car car = new Car("MH 01 0001","BMW","Black");
        car.setParkingTime(LocalDateTime.of(2019,12,26,14,0,0));
        long timeElapsed = car.getTotalParkedTime(LocalDateTime.of(2019,12,26,17,0,0));
        Assert.assertEquals(3,timeElapsed);
    }

}


