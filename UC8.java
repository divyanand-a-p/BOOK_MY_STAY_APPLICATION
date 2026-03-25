import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    double cost;

    Reservation(String reservationId, String guestName, String roomType, double cost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.cost = cost;
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> list) {
        if (list.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : list) {
            System.out.println("ID: " + r.reservationId +
                    ", Guest: " + r.guestName +
                    ", Room: " + r.roomType +
                    ", Cost: ₹" + r.cost);
        }
    }

    public double calculateTotalRevenue(List<Reservation> list) {
        double total = 0;
        for (Reservation r : list) {
            total += r.cost;
        }
        return total;
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("R001", "Alice", "Deluxe", 3000));
        history.addReservation(new Reservation("R002", "Bob", "Suite", 5000));
        history.addReservation(new Reservation("R003", "Charlie", "Standard", 2000));

        BookingReportService reportService = new BookingReportService();

        List<Reservation> allBookings = history.getAllReservations();

        reportService.displayAllBookings(allBookings);

        double totalRevenue = reportService.calculateTotalRevenue(allBookings);

        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}