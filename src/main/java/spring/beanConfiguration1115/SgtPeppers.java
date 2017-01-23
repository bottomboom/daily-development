package spring.beanConfiguration1115;

/**
 * Created by LiLi on 16/11/15.
 */
//@Component
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";

    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " By " + artist);
    }

    @Override
    public void playTrack(int num) {

    }
}
