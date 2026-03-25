import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {

    private static final List<String> validRoomTypes = Arrays.asList("Standard", "Deluxe", "Suite");

    public static void validate(String roomType, int availableRooms) throws InvalidBookingException {

        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available for booking.");
        }
    }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        String roomType = "Premium";
        int availableRooms = 0;

        try {

            BookingValidator.validate(roomType, availableRooms);

            System.out.println("Booking successful.");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());
        }

        System.out.println("System running safely.");
    }
}