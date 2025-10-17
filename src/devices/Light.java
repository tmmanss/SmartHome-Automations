package devices;

public class Light extends BaseDevice {
    private boolean isOn = false;
    private Mode mode = Mode.NEUTRAL;

    public Light(String name) {
        super(name);
    }

    public static enum Mode {
        COLD("Cold White"),
        WARM("Warm White"),
        NEUTRAL("Neutral White"),
        DIMMED("Dimmed"),
        DISCO("Disco Mode");

        private final String label;

        Mode(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public void turnOn() {
        if (!broken) {
            isOn = true;
            System.out.println("💡 " + name + " is turned ON (" + mode + ")");
        } else {
            System.out.println("⚠️ " + name + " is broken!");
        }
    }

    public void turnOff() {
        if (!broken) {
            isOn = false;
            System.out.println("💡 " + name + " is turned OFF");
        }
    }

    public void setMode(Mode mode) {
        if (!broken) {
            this.mode = mode;
            System.out.println("💡 " + name + " mode set to " + mode);
        } else {
            System.out.println("⚠️ Can't change mode, " + name + " is broken!");
        }
    }

    @Override
    public void showInfo() {
        System.out.println("💡 " + name + " — " + (isOn ? "ON" : "OFF") + ", mode: " + mode + ", status: " + status);
    }
}
