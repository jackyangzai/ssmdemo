package com.wonder.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface HistoryService {
    HashMap findDrugHistory(String drug_no, int pageIndex, int pageSize, String sortField, String sortOrder);
}
