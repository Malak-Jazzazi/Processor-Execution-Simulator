public class Clock {
    private int currentTime;

    public Clock() {
        this.currentTime = 0;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void tick() {
        currentTime++;
    }
}