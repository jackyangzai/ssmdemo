package com.wonder.services;

import com.wonder.entity.Employee;
import com.wonder.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Map<String,Object>> findAllEmployee();
    //登录
    User Login (String loginname, String password)throws NameException,PasswordException;
    //查询用户
    HashMap<String,Object> findAllUser(String key, int pageIndex, int pageSize, String sortField, String sortOrder);
    //保存用户信息
    void saveUserData(HashMap map);
    //删除用户信息
    void deleteUserData(String loginname);
    //修改信息
    void updateUserData(HashMap map);
}
