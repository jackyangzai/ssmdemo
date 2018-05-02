package com.wonder.services.impl;

import com.wonder.dao.DrugDao;
import com.wonder.services.DrugService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("drguService")
public class DrugServiceImpl implements DrugService {

    @Resource
    private DrugDao drugDao;

    public HashMap searchDrugData(String key, int pageIndex, int pageSize, String sortField, String sortOrder) {
        if(key == null) key="";
        if(!StringUtils.isEmpty(sortField)){
            if(!"desc".equals(sortOrder)) sortOrder="asc";
        }else{
            sortField = "drug_name";
            sortOrder = "desc";
        }
        HashMap hashMap =  new HashMap();
        hashMap.put("key",key);
        hashMap.put("sortField",sortField);
        hashMap.put("sortOrder",sortOrder);
        ArrayList<Map> drugDataAll = drugDao.searchDrugData(hashMap);
//        System.out.println(drugDataAll.size());
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;

        for (int i = 0; i < drugDataAll.size(); i++)
        {
            HashMap record = (HashMap)drugDataAll.get(i);
            if (record == null) continue;
            if (start <= i && i < end)
            {
                data.add(record);
            }
        }

        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("data", data);
        result.put("total", drugDataAll.size());

//        System.out.println(result);
        return result;
    }


    public HashMap searchOneData(String drug_name) {
        HashMap map = drugDao.searchOneData(drug_name);
        return map;
    }


    public void updateDrug(HashMap map) {
        drugDao.updateDrugData(map);
    }


    public void deleteDrug(String drug_no) {
        drugDao.deleteDrug(drug_no);
    }


    public void insertDrugData(HashMap map) {
        drugDao.insertDrugData(map);
    }

    public ArrayList<Map> searchStock() {
        return drugDao.searchStock();
    }


    public void inDrug(HashMap map) {
        drugDao.inDrug(map);
        System.out.println(map);
        drugDao.insertHistory((String)map.get("user"),(String)map.get("action"),(String)map.get("DRUG_QTY_CHANGE"),(String)map.get("DRUG_NO"));
    }


    public void outDrug(HashMap map) {
        drugDao.outDrug(map);
        drugDao.insertHistory((String)map.get("user"),(String)map.get("action"),(String)map.get("DRUG_QTY_CHANGE"),(String)map.get("DRUG_NO"));
    }
}
