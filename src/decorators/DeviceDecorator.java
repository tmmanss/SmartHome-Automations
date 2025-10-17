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

    @Override
    public void showInfo() {
        wrappedDevice.showInfo();
    }
}
