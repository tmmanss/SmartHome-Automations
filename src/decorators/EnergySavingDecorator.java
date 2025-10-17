package decorators;

import devices.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    public EnergySavingDecorator(Device device) {
        super(device);
    }

    public void optimizePower() {
        System.out.println("⚡ " + getName() + " optimizing power consumption...");
    }

    @Override
    public void showInfo() {
        wrappedDevice.showInfo();
        System.out.println("♻️ Energy saving mode active");
    }
}
