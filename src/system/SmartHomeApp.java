package system;

import devices.*;
import rooms.*;

import java.util.Scanner;

public class SmartHomeApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        House house = new House();
        SmartHomeFacade facade = new SmartHomeFacade(house);

        // Добавим базовые устройства
        house.getRoom(RoomType.HALLWAY).addDevice(new Light("Hallway Light"));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new SecurityCamera("Main Camera"));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new Blind("Living Room Blinds"));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new MusicSystem("Music System"));
        house.getRoom(RoomType.BEDROOM).addDevice(new Thermostat("Bedroom Thermostat"));

        // Автоматизация при входе
        facade.arrivedHome();

        RoomType currentRoom = RoomType.HALLWAY;

        while (true) {
            System.out.println("""
                === Smart Home Console ===
                You are in: %s
                Choose an option:
                1. View devices in this room
                2. Move to another room
                3. Add a device
                4. Remove a device
                5. Leave home
            """.formatted(currentRoom));

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            Room room = house.getRoom(currentRoom);

            switch (choice) {
                case "1" -> {
                    if (room.getDevices().isEmpty()) {
                        System.out.println("No devices in this room.\n");
                    } else {
                        for (Device d : room.getDevices()) {
                            d.showInfo();
                        }
                    }
                }
                case "2" -> {
                    currentRoom = chooseRoom();
                }
                case "3" -> {
                    addDeviceToRoom(room);
                }
                case "4" -> {
                    removeDeviceFromRoom(room);
                }
                case "5" -> {
                    facade.leaveHome();
                    System.out.println("🏠 You left home. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option, try again.\n");
            }
        }
    }

    private static RoomType chooseRoom() {
        System.out.println("""
            Choose a room:
            1. HALLWAY
            2. LIVING_ROOM
            3. BEDROOM
            4. KITCHEN
            5. BATHROOM
        """);
        System.out.print("Enter choice: ");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> RoomType.HALLWAY;
            case "2" -> RoomType.LIVING_ROOM;
            case "3" -> RoomType.BEDROOM;
            case "4" -> RoomType.KITCHEN;
            case "5" -> RoomType.BATHROOM;
            default -> RoomType.HALLWAY;
        };
    }

    private static void addDeviceToRoom(Room room) {
        System.out.println("""
            Which device to add?
            1. Light
            2. Blinds
            3. Camera
            4. Music System
            5. Thermostat
        """);
        System.out.print("Enter choice: ");
        String input = scanner.nextLine();

        Device newDevice = switch (input) {
            case "1" -> new Light(room.getType() + " Light");
            case "2" -> new Blind(room.getType() + " Blinds");
            case "3" -> new SecurityCamera(room.getType() + " Camera");
            case "4" -> new MusicSystem(room.getType() + " Music System");
            case "5" -> new Thermostat(room.getType() + " Thermostat");
            default -> null;
        };

        if (newDevice != null) {
            room.addDevice(newDevice);
        } else {
            System.out.println("Invalid choice.\n");
        }
    }

    private static void removeDeviceFromRoom(Room room) {
        if (room.getDevices().isEmpty()) {
            System.out.println("No devices to remove.\n");
            return;
        }

        System.out.println("Select a device to remove:");
        for (int i = 0; i < room.getDevices().size(); i++) {
            System.out.println((i + 1) + ". " + room.getDevices().get(i).getName());
        }
        System.out.print("Enter number: ");
        String input = scanner.nextLine();
        try {
            int index = Integer.parseInt(input) - 1;
            Device toRemove = room.getDevices().get(index);
            room.removeDevice(toRemove);
        } catch (Exception e) {
            System.out.println("Invalid selection.\n");
        }
    }
}
