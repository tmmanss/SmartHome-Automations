package system;

import devices.*;
import rooms.*;
import decorators.*;

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

        Room livingRoom = house.getRoom(RoomType.LIVING_ROOM);
        for (Device device : livingRoom.getDevices()) {
            if (device instanceof SecurityCamera cam) {
                cam.disable();
            }
        }

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

    public void activateDanceMode() {
        System.out.println("\n🕺 Activating Dance Mode...\n");

        for (Room room : house.getRooms().values()) {
            for (Device device : room.getDevices()) {
                Device base = (device instanceof DeviceDecorator) ? DeviceDecorator.unwrap(device) : device;
                if (base instanceof Light lights) {
                    lights.setMode(Light.Mode.DISCO);
                    lights.turnOn();
                } else if (base instanceof MusicSystem music) {
                    music.setVolume(100);
                    music.play();
                }
            }
        }
        System.out.println("🎉 Disco lights on and music at max!\n");
    }

    public void activateGoodNightMode() {
        System.out.println("\n🌙 Activating Good Night Mode...\n");
        for (Room room : house.getRooms().values()) {
            for (Device device : room.getDevices()) {
                Device base = (device instanceof DeviceDecorator) ? DeviceDecorator.unwrap(device) : device;
                if (base instanceof Light lights) {
                    lights.setMode(Light.Mode.DIMMED);
                    lights.turnOff();
                } else if (base instanceof MusicSystem music) {
                    music.stop();
                    music.setVolume(10);
                } else if (base instanceof Blind blinds) {
                    blinds.close();
                } else if (base instanceof Thermostat thermo) {
                    thermo.setMode(Thermostat.Mode.NIGHT);
                } else if (base instanceof SecurityCamera cam) {
                    cam.enable();
                }
            }
        }
        System.out.println("😴 Lights dimmed, blinds closed, cameras active, thermostat to night.\n");
    }

    public void activateEnergySavingMode() {
        System.out.println("\n♻️ Activating Energy Saving Mode...\n");
        for (Room room : house.getRooms().values()) {
            for (Device device : room.getDevices()) {
                if (device instanceof EnergySavingDecorator saver) {
                    saver.optimizePower();
                }
                Device base = (device instanceof DeviceDecorator) ? DeviceDecorator.unwrap(device) : device;
                if (base instanceof Light lights) {
                    lights.setMode(Light.Mode.DIMMED);
                } else if (base instanceof MusicSystem music) {
                    music.setVolume(20);
                } else if (base instanceof Thermostat thermo) {
                    thermo.setMode(Thermostat.Mode.ECO);
                }
            }
        }
        System.out.println("✅ Energy settings applied across the house.\n");
    }
}
