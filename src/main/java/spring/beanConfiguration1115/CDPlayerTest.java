package spring.beanConfiguration1115;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by LiLi on 16/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=CDPlayerConfig.class)
@ContextConfiguration(locations = "classpath:spring-beanConfig.xml")
public class CDPlayerTest {

    @Rule
    public StandardOutputStreamLog log = new StandardOutputStreamLog();

//    @Autowired
//    @Qualifier("sgtPeppers")
//    private CompactDisc compactDisc;

    @Autowired
    private MediaPlayer mediaPlayer;

//    @Test
//    public void CDShouldNotNull() {
//        assertNotNull(compactDisc);
//    }

    @Test
    public void play() {
        mediaPlayer.play();
//        assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band By The Beatles\n", log.getLog());
    }

}
