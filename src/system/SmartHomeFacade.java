package system;

import devices.*;
import rooms.*;

public class SmartHomeFacade {
    private final House house;

    public SmartHomeFacade(House house) {
        this.house = house;
    }

    public void arrivedHome() {
        System.out.println("\n🏠 Arrived home — activating home automation...\n");

        Room hallway = house.getRoom(RoomType.HALLWAY);
        for (Device device : hallway.getDevices()) {
            if (device instanceof Light lights) {
                lights.turnOn();
            }
        }

        // Turn off cameras
        Room livingRoom = house.getRoom(RoomType.LIVING_ROOM);
        for (Device device : livingRoom.getDevices()) {
            if (device instanceof SecurityCamera cam) {
                cam.disable();
            }
        }

        // Set thermostat mode
        for (Room room : house.getRooms().values()) {
            for (Device device : room.getDevices()) {
                if (device instanceof Thermostat thermo) {
                    thermo.setMode(Thermostat.Mode.COMFORT);
                }
            }
        }

        System.out.println("✅ Home is ready! Lights on, temperature set, cameras disabled.\n");
    }

    public void leaveHome() {
        System.out.println("\n🚪 Leaving home — activating away mode...\n");

        for (Room room : house.getRooms().values()) {
            for (Device device : room.getDevices()) {
                if (device instanceof Light lights) {
                    lights.turnOff();
                } else if (device instanceof Blind blinds) {
                    blinds.close();
                } else if (device instanceof SecurityCamera cam) {
                    cam.enable();
                } else if (device instanceof Thermostat thermo) {
                    thermo.setMode(Thermostat.Mode.ECO);
                }
            }
        }

        System.out.println("🔒 All lights off, blinds closed, cameras active, thermostat set to eco mode.\n");
    }
}
