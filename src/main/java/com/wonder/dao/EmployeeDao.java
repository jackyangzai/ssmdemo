package com.wonder.dao;

import com.wonder.entity.Employee;
import com.wonder.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    List<Map<String,Object>> findAllEmployees();
    User findEmpByName(String loginname);
    //查询用户
    ArrayList<Map> findAllUser(HashMap map);
    //保存用户信息
    void saveUserData(HashMap map);
    //删除用户信息
    void deletUserData(String loginname);
    //修改用户信息
    void updateUserData(HashMap map);
}
