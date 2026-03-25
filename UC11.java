import java.util.*;

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public synchronized String allocateRoom(String roomType) {
        int count = inventory.getOrDefault(roomType, 0);

        if (count > 0) {
            int roomNumber = count;
            inventory.put(roomType, count - 1);
            return roomType + "-" + roomNumber;
        }
        return null;
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class BookingTask implements Runnable {
    private String guestName;
    private String roomType;
    private RoomInventory inventory;

    public BookingTask(String guestName, String roomType, RoomInventory inventory) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.inventory = inventory;
    }

    public void run() {
        String roomId = inventory.allocateRoom(roomType);

        if (roomId != null) {
            System.out.println("Booking confirmed for Guest: " + guestName + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for Guest: " + guestName + " (No rooms available)");
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new Thread(new BookingTask("Abhi", "Single", inventory));
        Thread t2 = new Thread(new BookingTask("Vanmathi", "Double", inventory));
        Thread t3 = new Thread(new BookingTask("Kural", "Suite", inventory));
        Thread t4 = new Thread(new BookingTask("Subha", "Single", inventory));

        System.out.println("Concurrent Booking Simulation\n");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.displayInventory();
    }
}