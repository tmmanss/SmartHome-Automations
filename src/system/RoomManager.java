package system;

import devices.*;
import rooms.Room;
import rooms.House;

import java.util.Scanner;

public class RoomManager {
    private final Scanner scanner;
    private final House house;

    public RoomManager(Scanner scanner, House house) {
        this.scanner = scanner;
        this.house = house;
    }

    public void addDeviceToRoom(Room room) {
        System.out.println("""
            ➕ ADD NEW DEVICE:
            1. 💡 Light
            2. 🪟 Blinds
            3. 📹 Security Camera
            4. 🎵 Music System
            5. 🌡️ Thermostat
        """);
        System.out.print("Enter device type (1-5): ");
        String input = scanner.nextLine();

        Device newDevice = switch (input) {
            case "1" -> new Light(room.getType() + " Light");
            case "2" -> new Blind(room.getType() + " Blinds");
            case "3" -> new SecurityCamera(room.getType() + " Camera");
            case "4" -> new MusicSystem(room.getType() + " Music System");
            case "5" -> new Thermostat(room.getType() + " Thermostat");
            default -> {
                System.out.println("❌ Invalid choice.\n");
                yield null;
            }
        };

        if (newDevice != null) {
            room.addDevice(newDevice);
            System.out.println("✅ Device added successfully!\n");
        }
    }

    public void removeDeviceFromRoom(Room room) {
        if (room.getDevices().isEmpty()) {
            System.out.println("❌ No devices to remove.\n");
            return;
        }

        System.out.println("❌ SELECT DEVICE TO REMOVE:");
        for (int i = 0; i < room.getDevices().size(); i++) {
            System.out.println((i + 1) + ". " + room.getDevices().get(i).getName());
        }
        System.out.print("Enter device number: ");
        String input = scanner.nextLine();
        try {
            int index = Integer.parseInt(input) - 1;
            Device toRemove = room.getDevices().get(index);
            room.removeDevice(toRemove);
            System.out.println("✅ Device removed successfully!\n");
        } catch (Exception e) {
            System.out.println("❌ Invalid selection.\n");
        }
    }
}