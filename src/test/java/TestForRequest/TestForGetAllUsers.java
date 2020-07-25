package TestForRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TestForGetAllUsers {
    public static final String REST_SERVICE_URI = "http://localhost:8080/SFMES_HT/api/Supplier";

    private static void listAllCustomerUsers() {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> pageData = new HashMap<>();
        pageData.put("PageSize", 10);
        pageData.put("PageIndex", 2);
        System.out.println(pageData);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(pageData);
        ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI + "/GetTByCondition", HttpMethod.POST, httpEntity, Object.class);

        System.out.println(response.getBody());
    }

    private static void getDataById() {
        RestTemplate restTemplate = new RestTemplate();
		 /*
	    Map<String,Object> pageData=new HashMap<>();
	   
	    pageData.put("PageSize", 10);
	    pageData.put("PageNumber", 2);
	    System.out.println(pageData);
	    */
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(null);
        ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI + "/GetTById/1", HttpMethod.GET, httpEntity, Object.class);

        System.out.println(response.getBody());
    }

    private static void getInitDataById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
	 /*
    Map<String,Object> pageData=new HashMap<>();
   
    pageData.put("PageSize", 10);
    pageData.put("PageNumber", 2);
    System.out.println(pageData);
    */
        String paraString = "/GetEditInitialize/";
        paraString = paraString + String.valueOf(id);
        System.out.println(paraString);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(null);
        ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI + paraString, HttpMethod.GET, httpEntity, Object.class);

        System.out.println(response.getBody());

    }

    public static void deleteData() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> arrayList = new HashMap<>();
        int[] str = {1, 2, 3};
        arrayList.put("List", str);
        System.out.println(arrayList);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(arrayList);
        ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI + "/Delete", HttpMethod.POST, httpEntity, Object.class);

        System.out.println(response.getBody());
    }

    public static void main(String args[]) {
        System.out.println("此接口测试的内容为获取所有的CustomerUser的信息");
        listAllCustomerUsers();
        System.out.println("此接口根据用户所给Id号找到相应数据");
        getDataById();
        System.out.println("此接口为测试用户根据Id号找到数据的更改初始化数据");
        getInitDataById(1);
        System.out.println("此接口为根据指定Id删除相应数据库表的记录到的函数");
        deleteData();


    }
}

