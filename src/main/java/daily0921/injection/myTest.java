package daily0921.injection;


import com.alibaba.dubbo.common.json.JSON;
import daily0921.containerAnnotation.Store;
import daily0921.mutiBean.BeanInvoker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by tqmall on 16/9/21.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class myTest /*extends UnitTestBase*/ {
    /**
     * Bean Scope singleton 针对同一个容器
     */
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-resource.xml");
        BeanScope beanScope = (BeanScope) context.getBean("beanScope");
        beanScope.say();

//        BeanScope beanScope1 = (BeanScope) context.getBean("beanScope");
//        beanScope1.say();

    }

    @Test
    public void test1_1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-resource.xml");
        BeanScope beanScope = (BeanScope) context.getBean("beanScope");
        beanScope.say();
    }

    @Test
    public void test2() {
        BeanFactory context = new ClassPathXmlApplicationContext("spring-resource.xml");
        InjectionService injectionService = (InjectionService) context.getBean("injectionServiceImpl");

        injectionService.save("hello");

    }

    /**
     * List 组装  & Map 组装
     * @Order(value=)  value 值小的先加载
     */
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-resource.xml");
        BeanInvoker beanInvoker = (BeanInvoker)context.getBean("beanInvoker");

        beanInvoker.say();
    }

    /**
     * @Configuration @Bean
     */
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-resource.xml");
        //@Bean默认和注释的方法名同名
        Store store = (Store)context.getBean("store");
        System.out.println(store.getClass().getName());



    }

    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-resource.xml");
        context.getBean("dataSourceManager");


    }

    /**
     * Interger Date String 转换
     */
    @Test
    public void temp() {
        SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = sdf.format(new Date());
//        System.out.println(dateString);

        try {
            Date date = sdf.parse("19-12-1223 17:13:11");
//            System.out.println(date);
        }
        catch (ParseException e) {

        }

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);

        // Create our Gregorian Calendar.
        GregorianCalendar cal = new GregorianCalendar();
        // Set the date and time of our calendar
        // to the system´s date and time
        cal.setTime(new Date());
        System.out.println("System Date: " + dateFormat.format(cal.getTime()));
        // Set the day of week to FRIDAY
        cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
        System.out.println("After Setting Day of Week to Friday: " +dateFormat.format(cal.getTime()));
        int friday13Counter = 0;
        while (friday13Counter <= 10) {

// Go to the next Friday by adding 7 days.
            cal.add(GregorianCalendar.DAY_OF_MONTH,7);
// If the day of month is 13 we have
// another Friday the 13th.
            if (cal.get(GregorianCalendar.DAY_OF_MONTH) == 13) { friday13Counter++;
                System.out.println(dateFormat.format(cal.getTime()));
            }
        }
//        System.out.println(System.currentTimeMillis());
//        System.out.println(new Date().getTime());
     }
    @Test
    public void test10() {
        System.out.println(Integer.MAX_VALUE);

        Long instime = 1422585105L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(instime*1000)));

        String dateStr = "2015-01-30 10:31:45.0";
        try {
            Date date = sdf.parse(dateStr);
            System.out.println(Integer.valueOf(String.valueOf(date.getTime()/1000)));
            System.out.println(Integer.parseInt(String.valueOf(date.getTime()/1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test11() {
        String i = "23*25*27";
        String[] list = i.split("\\*");
        BigDecimal mutiply = BigDecimal.valueOf(1);
        for (String s : list) {
            mutiply = new BigDecimal(s);
            System.out.println(s);
        }
        System.out.println(mutiply);

        String s = "9.86";
        Float f =  Float.valueOf(s);
        System.out.println(f);
        f =f*100.00000f;
        System.out.println(f);
        Long result = f.longValue();
        System.out.println(result);
    }

    //Json.parse()
    @Test
    public void test12() {
        String data = "10001,10002,10003,10004";
        try {
            JSON.parse(data, List.class);
//            System.out.println(ids);
        } catch (com.alibaba.dubbo.common.json.ParseException e) {
            e.printStackTrace();
        }
    }

}
