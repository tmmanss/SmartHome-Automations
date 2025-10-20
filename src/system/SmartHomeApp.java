package system;

import devices.*;
import decorators.*;
import rooms.*;
import system.SmartHomeFacade;

import java.util.Scanner;

public class SmartHomeApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static House house;
    private static SmartHomeFacade facade;
    private static NavigationService navigationService;
    private static DeviceController deviceController;
    private static RoomManager roomManager;

    public static void main(String[] args) {
        initializeSystem();
        setupDevices();
        runApplication();
    }

    private static void initializeSystem() {
        house = new House();
        facade = new SmartHomeFacade(house);
        navigationService = new NavigationService(scanner);
        deviceController = new DeviceController(scanner);
        roomManager = new RoomManager(scanner, house);
    }

    private static void setupDevices() {
        // Hallway
        house.getRoom(RoomType.HALLWAY).addDevice(new ClapControlDecorator(new Light("Hallway Light")));
        house.getRoom(RoomType.HALLWAY).addDevice(new RemoteAccessDecorator(new MusicSystem("Hallway Music")));
        house.getRoom(RoomType.HALLWAY).addDevice(new Thermostat("Hallway Thermostat"));
        house.getRoom(RoomType.HALLWAY).addDevice(new SecurityCamera("Hallway Camera"));

        // Living Room
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new EnergySavingDecorator(new Light("Living Room Main Light")));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new ClapControlDecorator(new Light("Living Room Lamp")));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new RemoteAccessDecorator(new MusicSystem("Living Room Music")));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new Thermostat("Living Room Thermostat"));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new SecurityCamera("Living Room Camera"));
        house.getRoom(RoomType.LIVING_ROOM).addDevice(new Blind("Living Room Blinds"));

        // Bedroom
        house.getRoom(RoomType.BEDROOM).addDevice(new EnergySavingDecorator(new Light("Bedroom Light")));
        house.getRoom(RoomType.BEDROOM).addDevice(new RemoteAccessDecorator(new MusicSystem("Bedroom Music")));
        house.getRoom(RoomType.BEDROOM).addDevice(new Thermostat("Bedroom Thermostat"));
        house.getRoom(RoomType.BEDROOM).addDevice(new Blind("Bedroom Blinds"));

        // Bathroom
        house.getRoom(RoomType.BATHROOM).addDevice(new ClapControlDecorator(new Light("Bathroom Light")));
        house.getRoom(RoomType.BATHROOM).addDevice(new MusicSystem("Bathroom Music"));
        house.getRoom(RoomType.BATHROOM).addDevice(new Thermostat("Bathroom Thermostat"));

        // Kitchen
        house.getRoom(RoomType.KITCHEN).addDevice(new EnergySavingDecorator(new Light("Kitchen Ceiling Light")));
        house.getRoom(RoomType.KITCHEN).addDevice(new ClapControlDecorator(new Light("Kitchen Counter Light")));
        house.getRoom(RoomType.KITCHEN).addDevice(new RemoteAccessDecorator(new MusicSystem("Kitchen Music")));
        house.getRoom(RoomType.KITCHEN).addDevice(new Thermostat("Kitchen Thermostat"));
        house.getRoom(RoomType.KITCHEN).addDevice(new Blind("Kitchen Blinds"));
        house.getRoom(RoomType.KITCHEN).addDevice(new SecurityCamera("Kitchen Camera"));
    }

    private static void runApplication() {

        System.out.println("🏠 Oh, you arrived! Welcome Home! 🏠\n");
        facade.arrivedHome();

        RoomType currentRoom = RoomType.HALLWAY;
        System.out.println("📍 You are in: " + currentRoom + "\n");

        while (true) {
            showMainMenu(currentRoom);
            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> deviceController.viewAndControlDevices(house.getRoom(currentRoom));
                case "2" -> currentRoom = navigationService.chooseRoom();
                case "3" -> roomManager.addDeviceToRoom(house.getRoom(currentRoom));
                case "4" -> roomManager.removeDeviceFromRoom(house.getRoom(currentRoom));
                case "v" -> handleVoiceCommands();
                case "5" -> {
                    facade.leaveHome();
                    System.out.println("🏠 You left home. Goodbye! 👋");
                    return;
                }
                default -> System.out.println("❌ Invalid option, please try again.\n");
            }
        }
    }

    private static void showMainMenu(RoomType currentRoom) {
        System.out.println("=".repeat(40));
        System.out.println("🏠 SMART HOME CONTROL PANEL");
        System.out.println("📍 Current Room: " + currentRoom);
        System.out.println("=".repeat(40));
        System.out.println("""
            Choose an option:
            1. 📋 View and control devices in this room
            2. 🚪 Move to another room
            3. ➕ Add a device to this room
            4. ❌ Remove a device from this room
            v. 🗣️ Voice command / Scene
            5. 🏃 Leave home
        """);
        System.out.print("Enter your choice (1-5 or v). Tip: try 'v' then type 'dance mode': ");
    }

    private static void handleVoiceCommands() {
        System.out.println("\n🗣️ Say a command (e.g., 'dance mode', 'good night', 'energy saver'). Tip: use exact phrases.");
        String command = scanner.nextLine().trim().toLowerCase();
        switch (command) {
            case "dance mode" -> facade.activateDanceMode();
            case "good night" -> facade.activateGoodNightMode();
            case "energy saver", "energy saving" -> facade.activateEnergySavingMode();
            default -> System.out.println("❌ Unknown command.");
        }
        System.out.println();
    }
}