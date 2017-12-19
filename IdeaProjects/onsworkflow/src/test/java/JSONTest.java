import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.json.SpinJsonPathQuery;

import java.util.Iterator;

import static org.camunda.spin.Spin.JSON;


public class JSONTest {


    private String json = "{\"workflows\":[" +
            "{" +
            "\"appPath\":null," +
            "\"acl\":null," +
            "\"status\":\"SUCCEEDED\"," +
            "\"createdTime\":\"Tue, 25 Jul 2017 08:50:49 GMT\"," +
            "\"conf\":null," +
            "\"lastModTime\":\"Tue, 25 Jul 2017 14:17:41 GMT\"," +
            "\"run\":10," +
            "\"endTime\":\"Tue, 25 Jul 2017 14:17:41 GMT\"," +
            "\"externalId\":null," +
            "\"appName\":\"Insert_Record\"," +
            "\"id\":\"0000026-170713121000690-oozie-oozi-W\"," +
            "\"startTime\":\"Tue, 25 Jul 2017 14:17:17 GMT\"," +
            "\"parentId\":null," +
            "\"toString\":\"Workflow id[0000026-170713121000690-oozie-oozi-W] status[SUCCEEDED]\"," +
            "\"group\":null," +
            "\"consoleUrl\":\"http://quickstart.cloudera:11000/oozie?job=0000026-170713121000690-oozie-oozi-W\"," +
            "\"user\":\"cloudera\"," +
            "\"actions\": []" +
            "}," +
            "{" +
            "\"appPath\":null," +
            "\"acl\":null," +
            "\"status\":\"SUCCEEDED\"," +
            "\"createdTime\":\"Thu, 20 Jul 2017 11:36:20 GMT\"," +
            "\"conf\":null," +
            "\"lastModTime\":\"Thu, 20 Jul 2017 11:36:20 GMT\"," +
            "\"run\":10," +
            "\"endTime\":\"Thu, 20 Jul 2017 11:36:20 GMT\"," +
            "\"externalId\":null," +
            "\"appName\":\"WF_3\"," +
            "\"id\":\"0000023-170713121000690-oozie-oozi-W\"," +
            "\"startTime\":\"Thu, 20 Jul 2017 11:36:20 GMT\"," +
            "\"parentId\":null," +
            "\"toString\":\"Workflow id[0000023-170713121000690-oozie-oozi-W] status[SUCCEEDED]\"," +
            "\"group\":null," +
            "\"consoleUrl\":\"http://quickstart.cloudera:11000/oozie?job=0000023-170713121000690-oozie-oozi-W\"," +
            "\"user\":\"cloudera\"," +
            "\"actions\": []" +
            "}" +
            "]" +
            "}";

    private SpinList<SpinJsonNode> workflows = JSON(json).jsonPath("$.workflows").elementList();

    private String getStringElement(SpinJsonNode fsjpQuery, String elementTag)
    {
        return fsjpQuery.jsonPath("$." + elementTag).stringValue();
    }

    private void printElements(SpinJsonNode fsjpQuery)
    {
        System.out.println("\n" + getStringElement(fsjpQuery,"toString"));
        System.out.println("startTime  [" + getStringElement(fsjpQuery, "startTime") + "]");
        System.out.println("endTime    [" + getStringElement(fsjpQuery, "endTime") + "]\n");
    }

    private void run()
    {
        Iterator<SpinJsonNode> wfItr = workflows.iterator();

        while(wfItr.hasNext())
        {
            SpinJsonNode fsjpQuery = wfItr.next();

            printElements(fsjpQuery);

        }



    }

    public static void main(String[] args)
    {
        new JSONTest().run();
    }

}

