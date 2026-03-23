import java.util.*;

class ParkingSpot {
    String licensePlate;
    long entryTime;

    public ParkingSpot(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = System.currentTimeMillis();
    }
}

public class ParkingLotManager {

    private ParkingSpot[] table;
    private int size;

    public ParkingLotManager(int capacity) {
        table = new ParkingSpot[capacity];
        size = capacity;
    }

    // Hash function
    private int hash(String licensePlate) {
        return Math.abs(licensePlate.hashCode()) % size;
    }

    // Park vehicle using linear probing
    public int parkVehicle(String licensePlate) {

        int index = hash(licensePlate);
        int start = index;
        int probes = 0;

        while (table[index] != null) {
            index = (index + 1) % size;
            probes++;

            if (index == start) {
                System.out.println("Parking Full!");
                return -1;
            }
        }

        table[index] = new ParkingSpot(licensePlate);

        System.out.println("Vehicle " + licensePlate +
                " parked at spot " + index +
                " (probes: " + probes + ")");

        return index;
    }

    // Remove vehicle
    public void exitVehicle(String licensePlate) {

        int index = hash(licensePlate);
        int start = index;

        while (table[index] != null) {

            if (table[index].licensePlate.equals(licensePlate)) {

                long duration =
                        (System.currentTimeMillis() - table[index].entryTime) / 1000;

                table[index] = null;

                System.out.println("Vehicle " + licensePlate +
                        " exited. Duration: " + duration + " sec");

                return;
            }

            index = (index + 1) % size;

            if (index == start) break;
        }

        System.out.println("Vehicle not found!");
    }

    // Show occupancy
    public void getStatistics() {

        int occupied = 0;

        for (ParkingSpot spot : table) {
            if (spot != null) occupied++;
        }

        double occupancy = (occupied * 100.0) / size;

        System.out.println("Occupancy: " + occupancy + "%");
    }


    // Demo
    public static void main(String[] args) {

        ParkingLotManager parking = new ParkingLotManager(5);

        parking.parkVehicle("ABC123");
        parking.parkVehicle("XYZ999");
        parking.parkVehicle("ABC124");

        parking.getStatistics();

        parking.exitVehicle("ABC123");

        parking.getStatistics();
    }
}