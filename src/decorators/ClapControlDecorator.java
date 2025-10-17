package decorators;

import devices.Device;

import java.util.Scanner;

public class ClapControlDecorator extends DeviceDecorator {
    private boolean isOn = false;

    public ClapControlDecorator(Device device) {
        super(device);
    }

    @Override
    public void showInfo() {
        wrappedDevice.showInfo();
        System.out.println("🎤 Clap control is enabled (clap twice to toggle)");
    }

    public void listenForClaps() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("👏 Clap twice to toggle " + getName() + " (enter 'clap clap'): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("clap clap")) {
            toggle();
        } else {
            System.out.println("❌ No valid clap detected.");
        }
    }

    private void toggle() {
        isOn = !isOn;
        System.out.println("👏 " + getName() + (isOn ? " turned ON" : " turned OFF") + " by clapping!");
    }
}
