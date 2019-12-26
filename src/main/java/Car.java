import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Car {

    public String carMake;
    public String numberPlate;
    public String carColor;
    public LocalDateTime parkingTime;

    public Car(String numberPlate, String carMake, String carColor) {
        this.numberPlate = numberPlate;
        this.carMake = carMake;
        this.carColor = carColor;
    }

    public void setParkingTime(LocalDateTime parkingTime) {
        this.parkingTime=parkingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carMake, car.carMake) &&
                Objects.equals(numberPlate, car.numberPlate) &&
                Objects.equals(carColor, car.carColor);
    }

    public long getTotalParkedTime(LocalDateTime unparkingTime) {
        Duration timeDifference = Duration.between(this.parkingTime,unparkingTime);
        return timeDifference.toHours();
    }
}
