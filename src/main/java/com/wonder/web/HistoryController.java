package com.wonder.web;

import com.wonder.services.HistoryService;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Resource
    private HistoryService historyService;

    @RequestMapping("/history.do")
    public String toHistory(){
        return "history";
    }

    @ResponseBody
    @RequestMapping(value = "/findHistory.do",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String findDrugHistory(HttpServletRequest request, HttpServletResponse response){
        String drug_no = request.getParameter("drug_no");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap map = historyService.findDrugHistory(drug_no,pageIndex,pageSize,sortField,sortOrder);
        JSONSerializer serializer = new JSONSerializer();
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
        String json = serializer.deepSerialize(map);
        return json;
    }
}
