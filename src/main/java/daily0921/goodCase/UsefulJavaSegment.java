package daily0921.goodCase;

import com.alibaba.dubbo.common.json.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LiLi on 16/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-beanConfig.xml"})
public class UsefulJavaSegment {
    //几个非常有用的java片段程序  http://www.phpxs.com/code/1001855/

    //1. 字符串与整形的相互转换
    @Test
    public void String2Integer() {
        //整形转字符串
        String a = String.valueOf(3);

        //字符串转整形
        Integer b = Integer.parseInt(a);
    }

    //2. 向文件末尾添加内容(未验证)
    public void addToFile() {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("", true));
            out.write("aString");
        } catch (IOException e) {
            // error processing code
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //3. 得到当前方法的名字
    @Test
    public void getCurrentMethodName() {
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(method);
        //得到调用改方法的名字
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Test
    public void test() {
        getCurrentMethodName();
    }

    //4. 转字符串到日期
    @Test
    public void String2Date() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date date = simpleDateFormat.parse("2016.12.11");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //转日期到指定字符串
        Date date = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = simpleDateFormat1.format(date);

        //
        Long millSeconds = date.getTime();

        /**joda dateTime**/
    }

    //5. 创建Json格式的数据
    @Test
    public void jsonData() {
        JSONObject json = new JSONObject();

        json.put("name","lily");
        json.put("ocuppation", "Java development");

        System.out.println(json.toString());
    }

    //6. 把Array转换成Map
    @Test
    public void Array2Map() {
        String[][] countries = { { "United States", "New York" }, { "United Kingdom", "London" },
                { "Netherland", "Amsterdam" }, { "Japan", "Tokyo" }, { "France", "Paris" } };

        Map countryCapitals = ArrayUtils.toMap(countries);//ArrayUtils估计已经有点老了

        System.out.println("Capital of Japan is " + countryCapitals.get("Japan"));
        System.out.println("Capital of France is " + countryCapitals.get("France"));
    }

    //7. 发送邮件
    @Test
    public void sendEmail() {
        boolean debug = true;

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.tqmall.com");
        props.put("mail.smtp.port", "25");

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        try {
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress("li.li@tqmall.com");
            msg.setFrom(addressFrom);
            String[] recipients = {"li.li@tqmall.com"};
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            // Optional : You can also set your custom headers in the Email if you Want
            msg.addHeader("MyHeaderName", "myHeaderValue");

            // Setting the Subject and Content Type
            msg.setSubject("liliTest");
            msg.setContent("hahhahha", "text/plain");
           /*
            // set attachment
            String fileName = "liliTest";
            String filePath = System.currentTimeMillis() + fileName;
            if (!TextUtils.isEmpty(affix))
            {
                BodyPart messageBodyPart= new MimeBodyPart();
                DataSource source = new FileDataSource(affix);
                //添加附件的内容
                //添加附件的标题
                messageBodyPart.setDataHandler(new DataHandler(source));
                //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                messageBodyPart.setFileName(MimeUtility.encodeText(affixName, "gbk", "B"));
                //将multipart对象放到message中
                multipart.addBodyPart(messageBodyPart);
            }
            */
            Transport.send(msg);
        }
        catch(MessagingException e) {
            e.getStackTrace();
        }

    }

    //8. FormatUtil

    //9. 删除List指定元素
    @Test
    public void removeElement() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {

        }

    }

    //10. Pattern
    @Test
    public void test10() {

        HashMap map = new HashMap();
        map.put(null,"lili");
        map.get(null);
    }




}
