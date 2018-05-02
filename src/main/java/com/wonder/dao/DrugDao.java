package com.wonder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DrugDao {
    //查询药品信息
    ArrayList<Map> searchDrugData(HashMap map);
    HashMap searchOneData(String drug_name);
    //保存药品信息
    void insertDrugData(HashMap hashMap);
    //修改信息
    void updateDrugData(HashMap map);
    //删除药品信息
    void deleteDrug(String drug_no);
    //库存查询
    ArrayList<Map> searchStock();
    //药品入库
    void inDrug(HashMap map);
    //药品出库
    void outDrug(HashMap map);
    //出入库记录保存
    void insertHistory(String user,String action,String changeQty,String drug_no);
}
