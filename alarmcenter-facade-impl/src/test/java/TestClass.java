import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by zhangxiaoming on 2016/12/13.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
//        ObjectMapper mapper = new CustomObjectMapper();
//        List<SaveSingleFormRequest> list = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            SaveSingleFormRequest restRequest = new SaveSingleFormRequest();
//            restRequest.setAppId("AppId" + i);
//            restRequest.setErrorLevel("2");
//            restRequest.setTitle("title" + i);
//            restRequest.setAssemblyName("assembly" + i);
//            restRequest.setExceptionName("exception" + i);
//            restRequest.setMethodName("method" + i);
//            restRequest.setAddTime("2016-12-05 10:57:10");
//            list.add(restRequest);
//        }
//        String json = mapper.writeValueAsString(list);
//
//        List<SaveSingleFormRequest> list1 = mapper.readValue(json, ArrayList.class);
        //String str1="2016-12-16 02:28:11";
        //String str2 ="2016-09-20 16:17:22.693000";
        //DateTime dt = DateTime.parse(str2);
        //DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(str1);

    }

    @Test
    public void test2() {
        DateTime dt = new DateTime(2016, 12, 16, 16, 19, 31);
        Long time = dt.getMillis();
        System.out.println(time);
    }
}
