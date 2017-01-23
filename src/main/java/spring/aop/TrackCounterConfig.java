package spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.beanConfiguration1115.BlankDisc;
import spring.beanConfiguration1115.CompactDisc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiLi on 16/11/23.
 */
@Configuration
@EnableAspectJAutoProxy //启用AspectJ自动代理
public class TrackCounterConfig {
    @Bean //CompactDisc Bean
    public CompactDisc sgtPeppers() {
        BlankDisc blankDisc = new BlankDisc();
        blankDisc.setArtist("Jay");
        blankDisc.setTitle("Seven Mile Aromatic");

        List<String> tracks = new ArrayList<String>();
        tracks.add("GrandMa");
        tracks.add("Lovely Woman");
        tracks.add("Seven Mile Aromatic");
        tracks.add("Run Aground");
        blankDisc.setTracks(tracks);

        return blankDisc;
    }

    @Bean //TrackCounter Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }
}
