package decorators;

import devices.Device;

public class RemoteAccessDecorator extends DeviceDecorator {
    public RemoteAccessDecorator(Device device) {
        super(device);
    }

    public void remoteControl(String command) {
        System.out.println("🌍 Remote command '" + command + "' sent to " + getName());
    }

    @Override
    public void showInfo() {
        wrappedDevice.showInfo();
        System.out.println("🌐 Remote Access enabled");
    }
}
