package spring.aop;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.beanConfiguration1115.CompactDisc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LiLi on 16/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TrackCounterConfig.class)
public class TrackCounterTest {
    @Autowired
    private CompactDisc cd;

    @Autowired
    TrackCounter counter;

    @Test
    public void testTrackCounter() {
        cd.playTrack(0);
        cd.playTrack(0);
        cd.playTrack(1);
        cd.playTrack(1);
        cd.playTrack(1);
        cd.playTrack(2);
        cd.playTrack(3);
        cd.playTrack(3);

        Assert.assertEquals(2,counter.getPlayCount(0));
        Assert.assertEquals(3,counter.getPlayCount(1));
        Assert.assertEquals(1,counter.getPlayCount(2));
        Assert.assertEquals(2,counter.getPlayCount(3));
    }

    @Test
    public void test() {
        Integer[] a = {1,2,3,4,5,6};
        List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(a));
        Integer[] b = {2,5,6};
        List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(b));
        l1.removeAll(l2);
        Lists.newArrayList();
        System.out.println(l1);
    }

}
