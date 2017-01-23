package spring.beanConfiguration1115;

/**
 * Created by LiLi on 16/11/15.
 */
//@Component
public class CDPlayer implements MediaPlayer {

    private CompactDisc compactDisc;

/*


    */
/** 构造器注入 **//*

    @Autowired
    public CDPlayer(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }
*/

    /** Setter注入 **/
//    @Autowired
    public void setCompactDisc(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    @Override
    public void play() {
        compactDisc.play();
    }
}
