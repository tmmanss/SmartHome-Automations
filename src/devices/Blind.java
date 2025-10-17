package devices;

public class Blind extends BaseDevice {
    private boolean isOpen = false;

    public Blind(String name) {
        super(name);
    }

    public void open() {
        if (!broken) {
            isOpen = true;
            System.out.println("🪟 " + name + " opened.");
        }
    }

    public void close() {
        if (!broken) {
            isOpen = false;
            System.out.println("🪟 " + name + " closed.");
        }
    }

    @Override
    public void showInfo() {
        System.out.println("🪟 " + name + " — " + (isOpen ? "Open" : "Closed") + ", status: " + status);
    }
}
