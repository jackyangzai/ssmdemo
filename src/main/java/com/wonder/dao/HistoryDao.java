package com.wonder.dao;

import java.util.ArrayList;
import java.util.Map;

public interface HistoryDao {
    ArrayList<Map> findDrugHistory(Map map);
}
