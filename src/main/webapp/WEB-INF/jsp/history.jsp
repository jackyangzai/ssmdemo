<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/28
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String drug_no = (String)request.getParameter("drug_no");
    System.out.println(drug_no);
%>
<html>
<head>
    <title>库存历史</title>
    <script type="text/javascript">
        var drug_no = <%=drug_no%>
//        alert(drug_no);
    </script>
    <script src="../../scripts/jquery-1.4.3.js" type="text/javascript"></script>
    <!--MiniUI-->
    <link href="../../scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <script src="../../scripts/miniui/miniui.js" type="text/javascript"></script>
    <script src="../../scripts/boot.js" type="text/javascript"></script>
</head>
<body>

<div id="datagrid1" class="mini-datagrid" style="width:1000px;height:280px;"  allowCellEdit="true" allowCellSelect="true" multiSelect="true"
     editNextOnEnterKey="true"  url = "../history/findHistory.do" pageSize="10">
    <div property="columns">
        <div type="checkcolumn"></div>
        <div field="DRUG_NO" width="100" allowSort="true" headerAlign="center">药品编号</div>
        <div field="DRUG_NAME" width="100" allowSort="true" headerAlign="center">药品名称</div>
        <div field="FLAG" width="100" allowSort="true" headerAlign="center" renderer="onStatusRenderer">出入库状态</div>
        <div field="CHANGED_QTY" width="100" allowSort="true" headerAlign="center">出入库数量</div>
        <div field="IN_OUT_TIME" width="120" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">出入库时间</div>
        <div field="LOGINNAME" allowResize="false" width="120" headerAlign="center" allowSort="true">出入库员工帐号</div>
        <div field="USERNAME" width="100" allowSort="true" headerAlign="center">出入库员工登录名</div>
        <div type="comboboxcolumn"  field="ROLE" width="80"  align="center" headerAlign="center" renderer="onRoleRenderer">出入库员角色</div>
        <div type="comboboxcolumn" field="STATUS" width="80" headerAlign="center" renderer="onUserStatusRenderer">出入库员状态</div>

    </div>
</div>
<script type="text/javascript">
    var Roles = [{ id: 1, text: '管理员' }, { id: 2, text: '药库人员'}];
    var Status = [{ id:'Y',text:'启用'},{id:'N',text:'停用'}];
    var inOutRoles = [{ id: 'remove', text: '入库' }, { id: 'add', text: '出库'}];
    mini.parse();
    var grid = mini.get("datagrid1");
    grid.load({ drug_no: drug_no });


function onStatusRenderer(e) {
    if(e.value=='remove') return '出库';
    else return '入库';
}

function onRoleRenderer(e) {
    if(e.value=='1') return '管理者';
    else return '药库管理者';
}
function onUserStatusRenderer(e) {
    if(e.value=='Y') return '启用';
    else  return '停用';
}
</script>
</body>
</html>
