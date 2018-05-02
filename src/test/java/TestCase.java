import com.wonder.dao.DrugDao;
import com.wonder.dao.EmployeeDao;
import com.wonder.dao.HistoryDao;
import com.wonder.entity.Employee;
import com.wonder.entity.User;
import com.wonder.services.EmployeeService;
import com.wonder.services.impl.EmployeeServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
    ClassPathXmlApplicationContext cx;
    SqlSessionFactory factory;

    @Before
    public void init(){
        cx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//        factory = cx.getBean("sqlSessionFactory",SqlSessionFactory.class);
    }
    @Test
    public void test01(){
        System.out.println(factory);
        SqlSession session = factory.openSession();
        System.out.println(session);
        session.close();
    }

    @Test
    public void test02(){
        EmployeeDao dao = cx.getBean("employeeDao",EmployeeDao.class);
        System.out.println(dao);
        List<Map<String,Object>> list = dao.findAllEmployees();
        for(Map<String,Object> map:list){
            System.out.println(map);
        }
    }

    @Test
    public void test03(){
        EmployeeService service = cx.getBean("employeeService", EmployeeServiceImpl.class);
        System.out.println(service);
        List<Map<String,Object>> list = service.findAllEmployee();
        for(Map<String,Object> map:list){
            System.out.println(map);
        }
    }
    @Test
    public void test04(){
        EmployeeDao dao = cx.getBean("employeeDao",EmployeeDao.class);
        System.out.println(dao);
        User user = dao.findEmpByName("1100");
        System.out.println(user);
    }

    @Test
    public void test05(){
        EmployeeService service = cx.getBean("employeeService", EmployeeServiceImpl.class);
        System.out.println(service);
//        User user = service.Login("1100","123456");
//        System.out.println(user);
//        System.out.println(service.findAllUser());
//        service.deleteUserData(4444444);
    }
    @Test
    public void test06(){
        EmployeeDao dao = cx.getBean("employeeDao",EmployeeDao.class);
        HashMap map = new HashMap();
        map.put("key","1100");
        map.put("sortField","loginname");
        map.put("sortOrder","desc");
        ArrayList<Map> list = dao.findAllUser(map);
        System.out.println(list);
    }

    @Test
    public void test07(){
        EmployeeDao dao = cx.getBean("employeeDao",EmployeeDao.class);
//        HashMap map = new HashMap();
//        map.put("ID","666");
//        map.put("LOGINNAME","6666");
//        map.put("USERNAME","hahaha");
//        map.put("PASSWORD","00000");
//        map.put("ROLE","1");
//        map.put("STATUS","Y");
//        dao.saveUserData(map);
//          dao.deletUserData(666);
    }
    @Test
    public void test08(){
        DrugDao drugDao = cx.getBean("drugDao",DrugDao.class);
        HashMap hashMap = new HashMap();
//        hashMap.put("drug_no","853405");
//        hashMap.put("drug_name","枇杷露糖浆");
//        hashMap.put("drug_com_name","止咳糖浆");
//        hashMap.put("drug_category","2");
//        hashMap.put("drug_spec","瓶");
//        hashMap.put("drug_pro_addres","杭州");
//        hashMap.put("drug_status","Y");
//        hashMap.put("drug_qty",999);
//        drugDao.insertDrugData(hashMap);
//        hashMap.put("key","");
//        hashMap.put("sortField","drug_no");
//        hashMap.put("sortOrder","desc");
//        ArrayList<Map> list = drugDao.searchDrugData(hashMap);
//        for(Map map:list){
//            System.out.println(map);
//        }
        ArrayList<Map> list= drugDao.searchStock();
        System.out.println(list);
    }

    @Test
    public void test09(){
        HistoryDao historyDao = cx.getBean("historyDao",HistoryDao.class);
//        ArrayList<Map> list = historyDao.findDrugHistory("8181");
//        for(Map map:list){
//            System.out.println(map);
//        }
    }
}
