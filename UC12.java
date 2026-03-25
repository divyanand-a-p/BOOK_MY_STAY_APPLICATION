import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }
}

class PersistenceService {

    public static void saveData(List<Reservation> bookings, RoomInventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(bookings);
            oos.writeObject(inventory);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static Object[] loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            List<Reservation> bookings = (List<Reservation>) ois.readObject();
            RoomInventory inventory = (RoomInventory) ois.readObject();
            System.out.println("Data loaded successfully.");
            return new Object[]{bookings, inventory};
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        List<Reservation> bookings;
        RoomInventory inventory;

        Object[] data = PersistenceService.loadData();

        if (data != null) {
            bookings = (List<Reservation>) data[0];
            inventory = (RoomInventory) data[1];
        } else {
            bookings = new ArrayList<>();
            inventory = new RoomInventory();
        }

        bookings.add(new Reservation("R001", "Alice", "Deluxe"));
        bookings.add(new Reservation("R002", "Bob", "Standard"));

        System.out.println("Current Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r.reservationId + " - " + r.guestName + " - " + r.roomType);
        }

        System.out.println("Inventory:");
        for (String key : inventory.inventory.keySet()) {
            System.out.println(key + ": " + inventory.inventory.get(key));
        }

        PersistenceService.saveData(bookings, inventory);
    }
}