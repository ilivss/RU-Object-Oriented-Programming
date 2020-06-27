package drinkersandbar;

public class Glass {

    private int volume = 0;

    public void fill(int cc) {
        volume = cc;
    }

    public void empty() {
        volume = 0;
    }

    public int getVolume() {
        return volume;
    }
}
