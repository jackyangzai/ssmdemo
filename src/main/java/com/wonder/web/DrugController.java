package com.wonder.web;

import com.wonder.services.DrugService;
import com.wonder.util.JsonResult;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/drug")
public class DrugController {
    @Resource
    private DrugService drugService;
    //跳转到药品维护页面
    @RequestMapping("/list.do")
    public String listDrug(){
        return "listdrug";
    }
    //跳转到出入库页面
    @RequestMapping("/toInOutStock.do")
    public String toInOutStock(HttpServletRequest request){
        System.out.println(request.getParameter("user"));
        return "in_out_stock";
    }

    /**
     * 查询药品信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/searchList.do",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    @ResponseBody
    public String searchDrugData(HttpServletRequest request, HttpServletResponse response){
        String key = request.getParameter("key");
//        System.out.println(key);
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap map = drugService.searchDrugData(key,pageIndex,pageSize,sortField,sortOrder);
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.deepSerialize(map);
//        System.out.println(json);
        return json;
    }

    /**
     * 查询一条药品信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchOne.do",produces = "text/html; charset=utf-8")
    public String searchOneData(HttpServletRequest request){
        String drug_name = request.getParameter("id");
        HashMap map = drugService.searchOneData(drug_name);
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.deepSerialize(map);
        return json;
    }

    /**
     * 保存药品数据
     * @param request
     * @param response
     */
    @RequestMapping(value = "/save.do",method = RequestMethod.POST)
    public void saveDrugData(HttpServletRequest request,HttpServletResponse response){
        System.out.println("save");
        String json = request.getParameter("data");
        String state = request.getParameter("action");
        JSONDeserializer deserializer = new JSONDeserializer();
        ArrayList rows = (ArrayList)deserializer.deserialize(json);
        HashMap row = (HashMap)rows.get(0);
        System.out.println(state);
        if("added".equals(state)){
            drugService.insertDrugData(row);
        }else if ("edit".equals(state)){
//            System.out.println("modified");
            drugService.updateDrug(row);
        }
    }

    /**
     * 删除选中的药品目录信息
     * @param request
     * @param response
     */
    @RequestMapping("/remove.do")
    public void removeDrugData(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("id");
        System.out.println(ids);
        String[] drug_nos = ids.split(",");
        for(int i=0;i<drug_nos.length;i++){
            String drug_no = drug_nos[i];
            drugService.deleteDrug(drug_no);
        }
    }

    /**
     * 库存查询
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stock.do",method = RequestMethod.POST)
    public String searchStock(HttpServletRequest request,HttpServletResponse response){
        ArrayList<Map> list = drugService.searchStock();
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.deepSerialize(list);
        return json;
    }
    //药品出入库
    @ResponseBody
    @RequestMapping(value = "/inOutDrug.do",method = RequestMethod.POST)
    public JsonResult inDrug(HttpServletRequest request){
        String json = request.getParameter("data");
//        System.out.println(json);
        String user = (String) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        System.out.println(user+"--"+action);
        JSONDeserializer deserializer = new JSONDeserializer();
        ArrayList rows = (ArrayList)deserializer.deserialize(json);
        HashMap row = (HashMap)rows.get(0);
        row.put("user",user);
        row.put("action",action);
        try {
            if("add".equals(action)){
                drugService.inDrug(row);
                return new JsonResult("success");
            }else {
                System.out.println(action);
                drugService.outDrug(row);
                return new JsonResult("success");
            }

        }catch (Exception e){
            return new JsonResult(e);
        }


    }
}
