package devices;

public class SecurityCamera extends BaseDevice {
    private boolean active = true;

    public SecurityCamera(String name) {
        super(name);
    }

    public void enable() {
        active = true;
        System.out.println("📹 " + name + " is active.");
    }

    public void disable() {
        active = false;
        System.out.println("📹 " + name + " is disabled.");
    }

    @Override
    public void showInfo() {
        System.out.println("📹 " + name + " — " + (active ? "Active" : "Disabled") + ", status: " + status);
    }
}
