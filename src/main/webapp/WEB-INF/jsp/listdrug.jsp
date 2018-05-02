<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/26
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药品目录维护</title>
    <script src="../../scripts/jquery-1.4.3.js" type="text/javascript"></script>
    <!--MiniUI-->
    <link href="../../scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <script src="../../scripts/miniui/miniui.js" type="text/javascript"></script>
    <script src="../../scripts/boot.js" type="text/javascript"></script>
</head>
<body>
<h1 >药品目录维护</h1>

<div style="width:1000px;">
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                    <a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
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
        <div field="DRUG_COM_NAME" width="120" headerAlign="center" allowSort="true">药品通用名</div>
        <div field="DRUG_CATEGORY" width="120" headerAlign="center" allowSort="true" renderer="onCategoryRenderer">药品分类</div>
        <div field="DRUG_SPEC" width="120" headerAlign="center" allowSort="true">药品规格</div>
        <div field="DRUG_PRO_ADDRESS" width="120" headerAlign="center" allowSort="true">药品产地</div>
        <div field="DRUG_STATUS" width="120" headerAlign="center" allowSort="true" renderer="onStatusRenderer">药品状态</div>
        <%--<div field="DRUG_QTY" width="120" headerAlign="center" allowSort="true">药品库存</div>--%>
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
    
    function add() {
        mini.open({
            url: "/html/drug.html",
            title: "新增药品目录", width: 600, height: 400,
                onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "new"};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
            });
    }


    function edit() {

        var length = grid.getSelecteds().length;
        var row = grid.getSelected();
        if (length==1) {
            mini.open({
                url: "/html/drug.html",
                title: "编辑药品信息", width: 600, height: 400,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.DRUG_NAME };
                    iframe.contentWindow.SetData(data);

                },
                ondestroy: function (action) {
                    grid.reload();
                }
            });
        } else if(length>1) {
            alert("只能编辑一条记录！");
        } else {
            alert("请选择一条记录");
        }

    }

    function remove() {
        var rows = grid.getSelecteds();
        if (rows.length > 0) {
            if (confirm("确定删除选中记录？")) {
                var ids = [];
                for (var i = 0, l = rows.length; i < l; i++) {
                    var r = rows[i];
                    ids.push(r.DRUG_NO);
                }
                var id = ids.join(',');
                grid.loading("操作中，请稍后......");
                $.ajax({
                    url: '../drug/remove.do?id='+id,
                    success: function (text) {
                        grid.reload();
                    },
                    error: function () {
                    }
                });
            }
        } else {
            alert("请选中一条记录");
        }
    }
    
    var category = [{ id: 1, text: '西药' }, { id: 2, text: '中药'},{ id: 3, text: '中成药'}];
    function onCategoryRenderer(e) {
        for (var i = 0, l = category.length; i < l; i++) {
            var g = category[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }
    function onStatusRenderer(e) {
        if(e.value=='Y') return '启用';
        else return '停用';
    }
</script>
</body>
</html>
