package com.wonder.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DrugService {
//    查询药品信息
    HashMap searchDrugData(String key,int pageIndex,int pageSize,String sortField,String sortOrder);
//    查询一条信息
    HashMap searchOneData(String drug_name);
    //修改药品信息
    void updateDrug(HashMap map);
    //删除药品信息
    void deleteDrug(String drug_no);
    //保存药品信息
    void insertDrugData(HashMap map);
    //查询库存
    ArrayList<Map> searchStock();
    //药品入库
    void inDrug(HashMap map);
    //药品出库
    void outDrug(HashMap map);
}
