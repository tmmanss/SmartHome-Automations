package system;

import devices.*;
import decorators.*;
import rooms.Room;

import java.util.Scanner;

public class DeviceController {
    private final Scanner scanner;

    public DeviceController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void viewAndControlDevices(Room room) {
        while (true) {
            showDevices(room);
            System.out.println("\nOptions:");
            System.out.println("1. 🔧 Control a device");
            System.out.println("2. 🔄 Refresh status");
            System.out.println("3. ↩️ Back to main menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> controlDevice(room);
                case "2" -> { }
                case "3" -> { return; }
                default -> System.out.println("❌ Invalid choice, try again.\n");
            }
        }
    }

    private void showDevices(Room room) {
        System.out.println("=".repeat(50));
        System.out.println("📋 DEVICES IN " + room.getType());
        System.out.println("=".repeat(50));

        var devices = room.getDevices();
        if (devices.isEmpty()) {
            System.out.println("❌ No devices in this room.");
            return;
        }

        for (int i = 0; i < devices.size(); i++) {
            System.out.print((i + 1) + ". ");
            devices.get(i).showInfo();
        }
    }

    private void controlDevice(Room room) {
        var devices = room.getDevices();

        System.out.print("Enter device number to control: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < devices.size()) {
            Device device = devices.get(index);
                controlDeviceMenu(device);
            } else {
                System.out.println("❌ Invalid device number.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number.\n");
        }
    }

    private void controlDeviceMenu(Device device) {
        while (true) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("🔧 CONTROLLING: " + device.getName());
            System.out.println("=".repeat(40));
            device.showInfo();

            showDeviceControlOptions(device);
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                return;
            }

            handleDeviceControl(device, choice);
        }
    }

    private void showDeviceControlOptions(Device device) {
        System.out.println("\nControl Options:");

        Device base = (device instanceof DeviceDecorator) ? DeviceDecorator.unwrap(device) : device;

        if (base instanceof Light) {
            System.out.println("1. 💡 Turn On");
            System.out.println("2. ⚫ Turn Off");
            System.out.println("3. 🎨 Change Light Mode");
            System.out.println("4. ⚡ Break Device");
            System.out.println("5. 🛠️ Repair Device");
        } else if (base instanceof MusicSystem) {
            System.out.println("1. 🎵 Play Music");
            System.out.println("2. ⏹️ Stop Music");
            System.out.println("3. 🔊 Change Volume");
            System.out.println("4. ⚡ Break Device");
            System.out.println("5. 🛠️ Repair Device");
        } else if (base instanceof Thermostat) {
            System.out.println("1. 🔥 Increase Temperature");
            System.out.println("2. ❄️ Decrease Temperature");
            System.out.println("3. 🌡️ Set Specific Temperature");
            System.out.println("4. 🔄 Change Mode");
            System.out.println("5. ⚡ Break Device");
            System.out.println("6. 🛠️ Repair Device");
        } else if (base instanceof SecurityCamera) {
            System.out.println("1. 📹 Enable Camera");
            System.out.println("2. ⚫ Disable Camera");
            System.out.println("3. ⚡ Break Device");
            System.out.println("4. 🛠️ Repair Device");
        } else if (base instanceof Blind) {
            System.out.println("1. ⬆️ Open Blinds");
            System.out.println("2. ⬇️ Close Blinds");
            System.out.println("3. ⚡ Break Device");
            System.out.println("4. 🛠️ Repair Device");
        }

        if (device instanceof ClapControlDecorator) {
            System.out.println("7. 👏 Use Clap Control (tip: type 'clap clap')");
        }
        if (device instanceof RemoteAccessDecorator) {
            System.out.println("8. 🌍 Send Remote Command (tip: enter any text command)");
        }
        if (device instanceof EnergySavingDecorator) {
            System.out.println("9. ♻️ Optimize Power");
        }

        System.out.println("0. ↩️ Back to device list");
    }

    private void handleDeviceControl(Device device, String choice) {
        try {
            DeviceControlHandler.handleControl(device, choice, scanner);
        } catch (Exception e) {
            System.out.println("❌ Error controlling device: " + e.getMessage());
        }
    }
}