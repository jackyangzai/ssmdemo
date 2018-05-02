package com.wonder.services.impl;

import com.wonder.dao.HistoryDao;
import com.wonder.services.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    @Resource
    private HistoryDao historyDao;

    @Override
    public HashMap findDrugHistory(String drug_no, int pageIndex, int pageSize, String sortField, String sortOrder) {
        if(drug_no == null) drug_no="";
        if(!StringUtils.isEmpty(sortField)){
            if(!"desc".equals(sortOrder)) sortOrder="asc";
        }else{
            sortField = "in_out_time";
            sortOrder = "desc";
        }
        HashMap map = new HashMap();
        map.put("drug_no",drug_no);
        map.put("sortField",sortField);
        map.put("sortOrder",sortOrder);
        ArrayList<Map> dataAll = historyDao.findDrugHistory(map);
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
        return result;
    }
}
