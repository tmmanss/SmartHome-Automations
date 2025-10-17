package devices;

public class MusicSystem extends BaseDevice {
    private boolean playing = false;
    private int volume = 50;

    public MusicSystem(String name) {
        super(name);
    }

    public void play() {
        if (!broken) {
            playing = true;
            System.out.println("🎶 " + name + " is now playing music.");
        }
    }

    public void stop() {
        playing = false;
        System.out.println("🎵 " + name + " stopped playing.");
    }

    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(volume, 100));
        System.out.println("🔊 " + name + " volume set to " + this.volume);
    }

    @Override
    public void showInfo() {
        System.out.println("🎵 " + name + " — " + (playing ? "Playing" : "Stopped") + ", volume: " + volume + ", status: " + status);
    }
}
