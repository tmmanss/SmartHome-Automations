package system;

import rooms.RoomType;
import java.util.Scanner;

public class NavigationService {
    private final Scanner scanner;

    public NavigationService(Scanner scanner) {
        this.scanner = scanner;
    }

    public RoomType chooseRoom() {
        System.out.println("""
            🚪 CHOOSE A ROOM TO MOVE TO:
            1. 🏠 HALLWAY
            2. 🛋️ LIVING ROOM
            3. 🛏️ BEDROOM
            4. 👨‍🍳 KITCHEN
            5. 🚽 BATHROOM
        """);
        System.out.print("Enter room choice (1-5): ");
        String input = scanner.nextLine();
        RoomType chosenRoom = switch (input) {
            case "1" -> RoomType.HALLWAY;
            case "2" -> RoomType.LIVING_ROOM;
            case "3" -> RoomType.BEDROOM;
            case "4" -> RoomType.KITCHEN;
            case "5" -> RoomType.BATHROOM;
            default -> {
                System.out.println("❌ Invalid choice, staying in current room.\n");
                yield RoomType.HALLWAY;
            }
        };
        System.out.println("📍 You moved to: " + chosenRoom + "\n");
        return chosenRoom;
    }
}