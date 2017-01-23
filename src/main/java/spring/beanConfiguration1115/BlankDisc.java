package spring.beanConfiguration1115;

import java.util.List;

/**
 * Created by LiLi on 16/11/16.
 */
public class BlankDisc implements CompactDisc{
    private String title;

    private String artist;

    private List<String> tracks;
/*

    public BlankDisc(String title, String artist,List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }
*/

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }
    @Override
    public void play() {
        System.out.println("Playing " + title + " By " + artist);
//        for (String track : tracks) {
//            System.out.println("-Track: "+ track);
//        }
        for (int i = 0; i < tracks.size(); i++) {
            playTrack(i);
        }
    }

    public void playTrack(int num) {
        System.out.println("-Track" + num +" : " + tracks.get(num));
    }
}
