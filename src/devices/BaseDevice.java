package devices;

public abstract class BaseDevice implements Device {
    protected String name;
    protected DeviceStatus status = DeviceStatus.WORKING;
    protected boolean broken = false;

    public BaseDevice(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DeviceStatus getStatus() {
        return status;
    }

    @Override
    public boolean isBroken() {
        return broken;
    }

    @Override
    public void repair() {
        if (broken) {
            status = DeviceStatus.REPAIRING;
            System.out.println("🔧 Repairing " + name + "...");
            broken = false;
            status = DeviceStatus.WORKING;
            System.out.println("✅ " + name + " repaired successfully!");
        } else {
            System.out.println("ℹ️ " + name + " is already working fine.");
        }
    }

    @Override
    public void breakDown() {
        if (broken) {
            System.out.println("⚠️ " + name + " is already broken.");
            return;
        }
        broken = true;
        status = DeviceStatus.BROKEN;
        System.out.println("⚠️ " + name + " broke down!");
    }
}
