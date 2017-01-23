package spring.aop;

/**
 * Created by LiLi on 16/11/23.
 */

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * 回顾BlankDisc类，它有多个磁道tracks，
 * 现在想要统计每个磁道播放了几次
 * 由于统计和本身播放是不同的关注点，所以将记录播放次数放到切面中
 */
@Aspect //定义切面
public class TrackCounter {
    Map<Integer,Integer> trackCounts = new HashMap<Integer, Integer>();

    //定义切点，通知play()方法
    @Pointcut("execution(* spring.beanConfiguration1115.BlankDisc.playTrack(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber) {}

    @Before("trackPlayed(trackNumber)")
    public void trackCounts(int trackNumber) {
        int num = getPlayCount(trackNumber);
        trackCounts.put(trackNumber,num+1);
    }

    public int getPlayCount(int trackNumber) {
        return trackCounts.get(trackNumber) == null ? 0 : trackCounts.get(trackNumber);
    }
}
