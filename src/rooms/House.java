package rooms;

import java.util.EnumMap;
import java.util.Map;

public class House {
    private final Map<RoomType, Room> rooms = new EnumMap<>(RoomType.class);

    public House() {
        for (RoomType type : RoomType.values()) {
            rooms.put(type, new Room(type));
        }
    }

    public Room getRoom(RoomType type) {
        return rooms.get(type);
    }

    public Map<RoomType, Room> getRooms() {
        return rooms;
    }
}
