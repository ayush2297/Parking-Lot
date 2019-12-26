public class ParkingLotException extends Exception {

    public enum ExceptionType {
        PARKING_FULL
    }
    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
