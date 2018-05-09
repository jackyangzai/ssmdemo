package com.wonder.web;

import com.wonder.entity.User;
import com.wonder.services.EmployeeService;
import com.wonder.util.JsonResult;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Resource
    private EmployeeService service;

    @RequestMapping("/list.do")
    public String list(ModelMap map){
        List<Map<String,Object>> list = service.findAllEmployee();
        for(Map<String,Object> m:list){
            System.out.println(m);
        }
        map.put("list",list);
        return "list-emp";
    }

    /**
     * 跳转到菜单页面
     * @param map 返回值
     * @param request 请求
     * @return 返回信息
     */
    @RequestMapping("/list-grid.do")
    public String ListGrid(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user",request.getParameter("user"));
        return "emplist";
    }

    /**
     * 登录方法
     * @param loginname 用户名
     * @param password 密码
     * @return 提示信息
     */
    @ResponseBody
    @RequestMapping(value = "/login.do")
    public Object Login(String loginname,String password,HttpServletRequest request,HttpServletResponse response){
        try {
            User user = service.Login(loginname,password);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser",user);
            return new JsonResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(e);
        }
    }

    /**
     * 查询药品信息
     * @param request 请求
     * @param response 返回
     * @return 药品信息
     */
    @ResponseBody
    @RequestMapping(value = "/getData.do",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String GetGridData(HttpServletRequest request, HttpServletResponse response) {
        //查询条件
        String key = request.getParameter("key");
        //分页查询条件
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap map = service.findAllUser(key,pageIndex,pageSize,sortField,sortOrder);
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.deepSerialize(map);
        System.out.println(json);
        return json;
    }

    /**
     * 保存用户信息
     * @param request 请求
     * @param response 返回
     */
    @RequestMapping(value = "/saveData.do",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    public void saveUsers(HttpServletRequest request,HttpServletResponse response){
        System.out.println("save");
        String json = request.getParameter("data");
        JSONDeserializer deserializer = new JSONDeserializer();
        ArrayList rows = (ArrayList)deserializer.deserialize(json);
        for(int i=0;i<rows.size();i++){
            HashMap row = (HashMap)rows.get(i);
            String state = row.get("_state")!=null?row.get("_state").toString():"";
            System.out.println("_state:"+state);
            if("added".equals(state)){
                //新增
                service.saveUserData(row);
            }else if(state.equals("removed") || state.equals("deleted")){
                //删除
                String LOGINNAME = (String) row.get("LOGINNAME");
                service.deleteUserData(LOGINNAME);
            }else if(state.equals("modified") || state.equals("")){
                //修改
                System.out.println("modified");
                service.updateUserData(row);
            }
        }
    }
}
