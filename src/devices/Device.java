package devices;

public interface Device {
    String getName();
    DeviceStatus getStatus();
    boolean isBroken();
    void repair();
    void showInfo();
}
