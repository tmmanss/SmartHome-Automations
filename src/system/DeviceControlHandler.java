package system;

import devices.*;
import decorators.*;
import java.util.Scanner;

public class DeviceControlHandler {

    public static void handleControl(Device device, String choice, Scanner scanner) {
        Device base = (device instanceof DeviceDecorator) ? DeviceDecorator.unwrap(device) : device;

        if (base instanceof Light light) {
            switch (choice) {
                case "1" -> light.turnOn();
                case "2" -> light.turnOff();
                case "3" -> changeLightMode(light, scanner);
                case "4" -> light.breakDown();
                case "5" -> light.repair();
            }
        } else if (base instanceof MusicSystem music) {
            switch (choice) {
                case "1" -> music.play();
                case "2" -> music.stop();
                case "3" -> {
                    System.out.print("Enter volume (0-100): ");
                    try {
                        int v = Integer.parseInt(scanner.nextLine());
                        music.setVolume(v);
                    } catch (NumberFormatException ignored) {}
                }
                case "4" -> music.breakDown();
                case "5" -> music.repair();
            }
        } else if (base instanceof Thermostat thermo) {
            switch (choice) {
                case "1" -> thermo.setTemperature(thermo.getTemperature() + 1);
                case "2" -> thermo.setTemperature(thermo.getTemperature() - 1);
                case "3" -> {
                    System.out.print("Enter temperature: ");
                    try {
                        double t = Double.parseDouble(scanner.nextLine());
                        thermo.setTemperature(t);
                    } catch (NumberFormatException ignored) {}
                }
                case "4" -> changeThermoMode(thermo, scanner);
                case "5" -> thermo.breakDown();
                case "6" -> thermo.repair();
            }
        } else if (base instanceof SecurityCamera cam) {
            switch (choice) {
                case "1" -> cam.enable();
                case "2" -> cam.disable();
                case "3" -> cam.breakDown();
                case "4" -> cam.repair();
            }
        } else if (base instanceof Blind blind) {
            switch (choice) {
                case "1" -> blind.open();
                case "2" -> blind.close();
                case "3" -> blind.breakDown();
                case "4" -> blind.repair();
            }
        }

        if (device instanceof ClapControlDecorator clap && choice.equals("7")) {
            clap.listenForClaps();
        } else if (device instanceof RemoteAccessDecorator remote && choice.equals("8")) {
            System.out.print("Enter remote command: ");
            String cmd = scanner.nextLine();
            remote.remoteControl(cmd);
        } else if (device instanceof EnergySavingDecorator saver && choice.equals("9")) {
            saver.optimizePower();
        }
    }

    private static void changeLightMode(Light light, Scanner scanner) {
        System.out.println("Select mode: 1) COLD 2) WARM 3) NEUTRAL 4) DIMMED 5) DISCO");
        String m = scanner.nextLine();
        switch (m) {
            case "1" -> light.setMode(Light.Mode.COLD);
            case "2" -> light.setMode(Light.Mode.WARM);
            case "3" -> light.setMode(Light.Mode.NEUTRAL);
            case "4" -> light.setMode(Light.Mode.DIMMED);
            case "5" -> light.setMode(Light.Mode.DISCO);
        }
    }

    private static void changeThermoMode(Thermostat t, Scanner scanner) {
        System.out.println("Select mode: 1) ECO 2) COMFORT 3) NIGHT 4) SUMMER 5) WINTER 6) AUTO");
        String m = scanner.nextLine();
        switch (m) {
            case "1" -> t.setMode(Thermostat.Mode.ECO);
            case "2" -> t.setMode(Thermostat.Mode.COMFORT);
            case "3" -> t.setMode(Thermostat.Mode.NIGHT);
            case "4" -> t.setMode(Thermostat.Mode.SUMMER);
            case "5" -> t.setMode(Thermostat.Mode.WINTER);
            case "6" -> t.setMode(Thermostat.Mode.AUTO);
        }
    }
}