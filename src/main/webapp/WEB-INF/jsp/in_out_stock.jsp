<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/27
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//String user = request.getParameter("user");
String user = (String) session.getAttribute("user");
//    System.out.println(user);
%>
<html>
<head>
    <title>药品出入库</title>
    <script src="../../scripts/jquery-1.4.3.js" type="text/javascript"></script>
    <!--MiniUI-->
    <link href="../../scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <script src="../../scripts/miniui/miniui.js" type="text/javascript"></script>
    <script src="../../scripts/boot.js" type="text/javascript"></script>
    <script type="text/javascript">
        var user = <%=user%>;
//        alert(user);
    </script>
</head>
<body>
<h1>出入库</h1>
<div style="width:1000px;">
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="add()">入库</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">出库</a>
                    <a class="mini-button" iconCls="icon-search" onclick="history()">出入库记录</a>
                </td>
                <td style="white-space:nowrap;">
                    <input id="key" class="mini-textbox" emptyText="请输入药品名称" style="width:150px;" onenter="onKeyEnter"/>
                    <a class="mini-button" onclick="search()">查询</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="datagrid1" class="mini-datagrid" style="width:1000px;height:316px;" allowResize="true"
     url="../drug/searchList.do"  idField="id" multiSelect="true" pageSize="10"
>
    <div property="columns">
        <!--<div type="indexcolumn"></div>        -->
        <div type="checkcolumn" ></div>
        <div field="DRUG_NO" width="120" headerAlign="center" allowSort="true">药品编号</div>
        <div field="DRUG_NAME" width="120" headerAlign="center" allowSort="true">药品名称</div>
        <%--<div field="DRUG_COM_NAME" width="120" headerAlign="center" allowSort="true">药品通用名</div>--%>
        <%--<div field="DRUG_CATEGORY" width="120" headerAlign="center" allowSort="true" renderer="onCategoryRenderer">药品分类</div>--%>
        <div field="DRUG_SPEC" width="120" headerAlign="center" allowSort="true">药品规格</div>
        <%--<div field="DRUG_PRO_ADDRESS" width="120" headerAlign="center" allowSort="true">药品产地</div>--%>
        <div field="DRUG_STATUS" width="120" headerAlign="center" allowSort="true" renderer="onStatusRenderer">药品状态</div>
        <div field="DRUG_QTY" width="120" headerAlign="center" allowSort="true">药品库存</div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var grid = mini.get("datagrid1");
    grid.load();
    //查询
    function search() {
        var key = mini.get("key").getValue();
        grid.load({key:key});
    }
    function onStatusRenderer(e) {
        if(e.value=='Y') return '启用';
        else return '停用';
    }
    
    function add() {
        var length = grid.getSelecteds().length;

        if(length==1){
            var row = grid.getSelected();
            var status = row.DRUG_STATUS;
            if(status=='Y'){
                mini.open({
                    url: "/html/drug_in_out.html",
                    title: "出入库操作", width: 820, height: 300,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "add", id: row.DRUG_NAME };
                        iframe.contentWindow.SetData(data);

                    },
                    ondestroy: function (action) {
                        grid.reload();
                    }
                });
            } else {
                alert("此药品已经停用，无法进行入库操作");
            }
        }else {
            alert("请选择一种药品进行入库操作")
        }
    }
    
    function remove() {
        var length = grid.getSelecteds().length;

        if(length==1){
            var row = grid.getSelected();
            var status = row.DRUG_STATUS;
            if(status=='Y'){
                mini.open({
                    url: "/html/drug_in_out.html",
                    title: "出入库操作", width: 600, height: 400,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "remove", id: row.DRUG_NAME };
                        iframe.contentWindow.SetData(data);

                    },
                    ondestroy: function (action) {
                        grid.reload();
                    }
                });
            } else {
                alert("此药品已经停用，无法进行出库操作");
            }
        }else {
            alert("请选择一种药品进行出库操作")
        }
    }
    
    function history() {
        var length = grid.getSelecteds().length;
        if(length==1){
            var drug_no = grid.getSelected().DRUG_NO;
            mini.open(
                {
                    url:'../history/history.do?drug_no='+drug_no,
                    title:'出入库记录查询',width: 1000, height: 400,
//                    onload: function () {
//                        var iframe = this.getIFrameEl();
//                        var data = { drug_no: drug_no };
//                        iframe.contentWindow.SetData(data);
//
//                    },
//                    ondestroy: function (action) {
//                        grid.reload();
//                    }
                }
            );
        }else {
            alert("请选择一种药品进行查看")
        }
    }
</script>
</body>
</html>
