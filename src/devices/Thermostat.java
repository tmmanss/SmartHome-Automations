package devices;

public class Thermostat extends BaseDevice {
    private double temperature = 22.0;
    private Mode mode = Mode.AUTO;

    public Thermostat(String name) {
        super(name);
    }

    public static enum Mode {
        ECO("Eco Mode (Low Power)"),
        COMFORT("Comfort Mode"),
        NIGHT("Night Mode"),
        SUMMER("Summer Mode"),
        WINTER("Winter Mode"),
        AUTO("Auto Adjust");

        private final String label;

        Mode(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public void setTemperature(double temp) {
        if (!broken) {
            temperature = temp;
            System.out.println("🌡 " + name + " temperature set to " + temperature + "°C");
        } else {
            System.out.println("⚠️ " + name + " is broken!");
        }
    }

    public void setMode(Mode mode) {
        if (!broken) {
            this.mode = mode;
            System.out.println("🌡 " + name + " mode set to " + mode);
        }
    }

    @Override
    public void showInfo() {
        System.out.println("🌡 " + name + " — " + mode + ", " + temperature + "°C, status: " + status);
    }
}
