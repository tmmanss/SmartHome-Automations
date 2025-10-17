package rooms;

import devices.Device;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private final RoomType type;
    private final List<Device> devices = new ArrayList<>();

    public Room(RoomType type) {
        this.type = type;
    }

    public RoomType getType() {
        return type;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
        System.out.println(device.getClass().getSimpleName() + " added to " + type);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
        System.out.println(device.getClass().getSimpleName() + " removed from " + type);
    }
}
