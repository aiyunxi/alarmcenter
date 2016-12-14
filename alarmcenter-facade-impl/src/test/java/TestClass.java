import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymatou.alarmcenter.facade.rest.model.SaveSingleFormRequest;
import com.ymatou.alarmcenter.infrastructure.config.CustomObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaoming on 2016/12/13.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new CustomObjectMapper();
        List<SaveSingleFormRequest> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SaveSingleFormRequest restRequest = new SaveSingleFormRequest();
            restRequest.setAppId("AppId" + i);
            restRequest.setErrorLevel("2");
            restRequest.setTitle("title" + i);
            restRequest.setAssemblyName("assembly" + i);
            restRequest.setExceptionName("exception" + i);
            restRequest.setMethodName("method" + i);
            restRequest.setAddTime("2016-12-05 10:57:10");
            list.add(restRequest);
        }
        String json = mapper.writeValueAsString(list);

        List<SaveSingleFormRequest> list1 = mapper.readValue(json, ArrayList.class);
    }
}
