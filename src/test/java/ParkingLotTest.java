import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ParkingLotTest {

    @Test
    public void givenAParkingLot_ShouldReturn100() {
        ParkingLot parkingLot = new ParkingLot();
        int parkingSize = parkingLot.getParkingSize();
        Assert.assertEquals(100, parkingSize);
    }

    @Test
    public void givenAnEmptyParkingLot_ShouldReturn100AvailableSlots() {
        ParkingLot parkingLot = new ParkingLot();
        int slots = parkingLot.getCountOfAvailableSlots();
        Assert.assertEquals(100, slots);
    }

    @Test
    public void givenAnEmptyParkingLot_ShouldReturnFirstSlotWhenAskedToGiveASlot() {
        try {
            ParkingLot parkingLot = new ParkingLot();
            int slots = parkingLot.getNearestParkingSlot();
            Assert.assertEquals(1, slots);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given2CarsWithSameDetails_ShouldReturnEqual() {
        Car car = new Car("MH 01 0001", "BMW", "Black");
        Car expectedCar = new Car("MH 01 0001", "BMW", "Black");
        Assert.assertEquals(expectedCar, car);
    }

    @Test
    public void givenParkingTime2PMAndUnParkingTime5PM_ShouldReturnParkingTimeAs3Hrs() {
        Car car = new Car("MH 01 0001", "BMW", "Black");
        car.setParkingTime(LocalDateTime.of(2019, 12, 26, 14, 0, 0));
        long timeElapsed = car.getTotalParkedTime(LocalDateTime.of(2019, 12, 26, 17, 0, 0));
        Assert.assertEquals(3, timeElapsed);
    }

    @Test
    public void givenACarForParkingInAnEmptyParkingLot_ShouldParkTheCarInFirstSlot() {
        Car car = new Car("MH 01 0001", "BMW", "Black");
        car.setParkingTime(LocalDateTime.of(2019, 12, 26, 14, 0, 0));
        try {
            ParkingLot parkingLot = new ParkingLot();
            parkingLot.parkTheCar(car);
            Assert.assertEquals(car, parkingLot.parkingSpace[0]);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given3CarForParkingInAnEmptyParkingLot_ShouldParkTheLastCarinThirdSlot() {
        Car car0 = new Car("MH 01 0001", "BMW", "Black");
        Car car1 = new Car("MH 01 0002", "BMW", "Black");
        Car car2 = new Car("MH 01 0003", "BMW", "Black");
        ParkingLot parkingLot = new ParkingLot();
        try {
            parkingLot.parkTheCar(car0);
            parkingLot.parkTheCar(car1);
            parkingLot.parkTheCar(car2);
            Assert.assertEquals(car2, parkingLot.parkingSpace[2]);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given1CarParkedInAParkingLot_WhenUnParked_ShouldReturn100ParkingAvailableSlots() {
        Car car0 = new Car("MH 01 0001", "BMW", "Black");
        ParkingLot parkingLot = new ParkingLot();
        try {
            parkingLot.parkTheCar(car0);
            parkingLot.unParkTheCar(car0);
            Assert.assertEquals(null, parkingLot.parkingSpace[0]);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given2ParkedCars_IfFirstCarIsUnParkedAndThirdCarIsAdded_ShouldReturn1AsParkingAvailableSlotForCar3() {
        Car car1 = new Car("MH 01 0001", "BMW", "Black");
        Car car2 = new Car("MH 01 0002", "BMW", "Black");
        ParkingLot parkingLot = new ParkingLot();
        try {
            parkingLot.parkTheCar(car1);
            parkingLot.parkTheCar(car2);
            parkingLot.unParkTheCar(car1);
            Car car3 = new Car("MH 01 0003", "BMW", "Black");
            parkingLot.parkTheCar(car3);
            Assert.assertEquals(car3, parkingLot.parkingSpace[0]);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingSpace_WhenFullyOccupied_ShouldThrowAnException() {
        Car car1 = new Car("MH 01 0001", "BMW", "Black");
        ParkingLot parkingLot = new ParkingLot();
        try {
            for (int i = 0; i < 100; i++) {
                parkingLot.parkTheCar(car1);
            }
            parkingLot.parkTheCar(car1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_FULL, e.type);
            Assert.assertTrue(parkingLot.isParkingFull);
        }
    }

    @Test
    public void givenFullyOccupiedParkingSpace_IfSpaceBecomesAvailable_ShouldBeAbleToParkTheCar() {
        Car car1 = new Car("MH 01 0001", "BMW", "Black");
        Car car2 = new Car("MH 01 0002", "BMW", "Black");
        ParkingLot parkingLot = new ParkingLot();
        try {
            for (int i = 0; i < 99; i++) {
                parkingLot.parkTheCar(car1);
            }
            parkingLot.parkTheCar(car2);
            parkingLot.parkTheCar(car1);
        } catch (ParkingLotException e) {
            parkingLot.unParkTheCar(car2);
            try {
                parkingLot.parkTheCar(car1);
            } catch (ParkingLotException ex) {
                ex.printStackTrace();
            }
            Assert.assertFalse(parkingLot.isParkingFull);
        }
    }
}



