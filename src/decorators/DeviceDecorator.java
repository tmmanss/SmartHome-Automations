package decorators;

import devices.Device;
import devices.DeviceStatus;

public abstract class DeviceDecorator implements Device {
    protected final Device wrappedDevice;

    public DeviceDecorator(Device device) {
        this.wrappedDevice = device;
    }

    @Override
    public String getName() {
        return wrappedDevice.getName();
    }

    @Override
    public DeviceStatus getStatus() {
        return wrappedDevice.getStatus();
    }

    @Override
    public boolean isBroken() {
        return wrappedDevice.isBroken();
    }

    @Override
    public void repair() {
        wrappedDevice.repair();
    }

    public Device getWrappedDevice() {
        return wrappedDevice;
    }

    public static Device unwrap(Device device) {
        Device current = device;
        while (current instanceof DeviceDecorator) {
            current = ((DeviceDecorator) current).getWrappedDevice();
        }
        return current;
    }

    @Override
    public void showInfo() {
        wrappedDevice.showInfo();
    }

    @Override
    public void breakDown() {
        wrappedDevice.breakDown();
    }
}
