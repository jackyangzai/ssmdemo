package com.wonder.services.impl;

import com.wonder.dao.EmployeeDao;
import com.wonder.entity.User;
import com.wonder.services.EmployeeService;
import com.wonder.services.NameException;
import com.wonder.services.PasswordException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    public List<Map<String, Object>> findAllEmployee() {
        return employeeDao.findAllEmployees();
    }

    public User Login(String loginname, String password) throws NameException,PasswordException {
        if(loginname==null||loginname.trim().isEmpty()){
            throw new NameException("登录账号不能为空");
        }
        if(password==null||password.trim().isEmpty()){
            throw new PasswordException("密码不能为空");
        }
        User user = employeeDao.findEmpByName(loginname);
        if(user==null){
            throw new NameException("此用户不存在");
        }
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new PasswordException("密码错误");
    }

    public HashMap<String,Object> findAllUser(String key,int pageIndex,int pageSize,String sortField,String sortOrder){
        if(key == null) key="";
        if(!StringUtils.isEmpty(sortField)){
            if("desc".equals(sortOrder)==false) sortOrder="asc";
        }else{
            sortField = "loginname";
            sortOrder = "desc";
        }
        HashMap map = new HashMap();
        map.put("key",key);
        map.put("sortField",sortField);
        map.put("sortOrder",sortOrder);
        ArrayList<Map> dataAll = employeeDao.findAllUser(map);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;

        for (int i = 0, l = dataAll.size(); i < l; i++) {
            HashMap record = (HashMap)dataAll.get(i);
            if (record == null) continue;
            if (start <= i && i < end)
            {
                data.add(record);
            }
        }

        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
//        System.out.println(result);
        return result;
    }

    public void saveUserData(HashMap map){
        employeeDao.saveUserData(map);
    }

    public void deleteUserData(String loginname){
        employeeDao.deletUserData(loginname);
    }

    public void updateUserData(HashMap map){
        employeeDao.updateUserData(map);
    }
}
