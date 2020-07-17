<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/15
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>TreeTable</title>
    <link href="${pageContext.request.contextPath}/static/assets/layui/css/layui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/assets/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"
            type="text/javascript"></script>
    <style>
        /** 箭头未展开 */
        #table1 + .treeTable .treeTable-icon .layui-icon-triangle-d:before {
            content: "\e602";
        }

        /** 箭头展开 */
        #table1 + .treeTable .treeTable-icon.open .layui-icon-triangle-d:before {
            content: "\e61a";
        }

        /** 文件图标 */
        #table1 + .treeTable .treeTable-icon .layui-icon-file:before {
            content: "";
        }

        /** 文件夹未展开（默认有图标我改为空了） */
        #table1 + .treeTable .treeTable-icon .layui-icon-layer:before {
            content: "";
        }

        /** 文件夹展开（默认有图标我改为空了） */
        #table1 + .treeTable .treeTable-icon.open .layui-icon-layer:before {
            content: "";
        }
    </style>
</head>
<body>
<div class="layui-container layui-text">
    <br>
    <h1>
        Layui树形表格treetable -
    </h1>
    <br>
    <div class="layui-btn-group">
        <button class="layui-btn" id="btn-expand">全部展开</button>
        <button class="layui-btn" id="btn-fold">全部折叠</button>
        <button class="layui-btn" id="btn-refresh">刷新表格</button>
    </div>
    &nbsp;
    <%--    <div class="layui-btn-group">--%>
    <%--        <a class="layui-btn layui-btn-normal" href="menu.html">菜单管理</a>--%>
    <%--        <a class="layui-btn layui-btn-normal" href="test.html">深度测试</a>--%>
    <%--        <a class="layui-btn layui-btn-normal" href="test2.html">自定义图标</a>--%>
    <%--        <a class="layui-btn layui-btn-normal" href="test3.html">多表格</a>--%>
    <%--        <a class="layui-btn layui-btn-normal" href="test5.html">搜索功能</a>--%>
    <%--    </div>--%>
    &emsp;
    <%--    <a class="layui-btn layui-btn-primary" href="https://whvse.gitee.io/treetable/" target="_blank">BOM树形表格</a>--%>

    <table id="table1" class="layui-table" lay-filter="table1"></table>

    <div id="window" class="site-text" style="margin: 5%; display: none">
        <form class="layui-form" id="book" method="post" lay-filter="table1">
            <div class="layui-form-item">
                <label class="layui-form-label">name</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" lay-verify="title" autocomplete="off" placeholder="请输入name"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">id</label>
                <div class="layui-input-block">
                    <input type="text" id="id" name="id" lay-verify="title" autocomplete="off" placeholder="请输入id"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">parent_id</label>
                <div class="layui-input-block">
                    <input type="text" id="parent_id" name="parent_id" lay-verify="title" autocomplete="off"
                           placeholder="请输入parent_id" class="layui-input">
                </div>
            </div>


            <!--  <div class="layui-form-item">
               <div class="layui-input-block">
                 <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
               </div>
             </div> -->
        </form>
    </div>
</div>
<!-- 操作列 -->
<script type="text/html" id="oper-col">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <%--    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit" data-toggle="modal" data-target="#myModal">修改</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${pageContext.request.contextPath}/static/assets/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/static/module/'
    }).extend({
        treetable: 'treetable-lay/treetable'
    }).use(['layer', 'table', 'treetable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;
        var treetable = layui.treetable;

        // 渲染表格
        var renderTable = function () {
            layer.load(2);
            treetable.render({
                treeColIndex: 0,    //设置下拉箭头在第几列
                treeSpid: 0,       //最上级的父级id
                treeIdName: 'id',   //数据库id字段的名称(自己的id)
                treePidName: 'parent_id', //数据库pid字段的名称（父亲的id）
                treeDefaultClose: true, //是否默认折叠
                treeLinkage: false, //父级展开时是否自动展开所有子级
                elem: '#table1',    //表格的id
                url: '/office/getChildren',
                page: false,
                cols: [[    //列数和列名可根据需要更改
                    {field: 'name', title: 'name'},  //filed:json数据的key
                    {field: 'id', title: 'id'},
                    {field: 'parent_id', title: 'parent_id'},
                    {templet: '#oper-col', title: 'oper'}
                ]],
                done: function () {
                    layer.closeAll('loading');
                }
            });
        };

        renderTable();

        $('#btn-expand').click(function () {
            treetable.expandAll('#table1');
        });

        $('#btn-fold').click(function () {
            treetable.foldAll('#table1');
        });

        $('#btn-refresh').click(function () {
            renderTable();
        });

        //监听工具条
        table.on('tool(table1)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'del') {
                // layer.msg('删除' + data.id);
                layer.confirm('确定要删除吗？', function (index) {
                    // 删除行
                    obj.del();
                    $.ajax({
                        url: "/office/del",
                        type: "post",
                        data: {
                            "id": data.id,
                        },
                        datatype: "text",
                        success: function (result) {
                            layer.msg('删除成功');
                        },
                        error: function () {
                        }
                    });
                    // 关闭弹框
                    layer.close(index);
                })
            } else if (layEvent === 'edit') {
                layer.open({
                    type: 1     //layer提供的五种层类型0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    , skin: 'layui-layer-molv'
                    , area: ['380px', '270px']   //宽高，默认auto自动适应
                    , title: ['编辑信息', 'font-size:18px']
                    , btn: ['确定', '取消']      //信息框模式时，btn默认是一个确认按钮，其它层类型则默认不显示，加载层和tips层则无效。定义的第一个按钮回调的是yes函数，后面定义的都是调用的no
                    , shadeClose: true
                    , shade: 0 //遮罩透明度
                    , maxmin: true //允许全屏最小化
                    , content: $("#window")  //弹出层的内容路径，根据type获取
                    , success: function (layero, index) {
                        $('#id').val(data.id);
                        $('#name').val(data.name);
                        $('#parent_id').val(data.parent_id);
                    }, yes: function (index, layero) {
                        $.ajax({
                            url: "/office/update",
                            type: "post",
                            data: {
                                id: $('#id').val(),
                                name: $('#name').val(),
                                parent_id: $('#parent_id').val()
                            },
                            datatype: "text",
                            success: function (result) {
                                if (result > 0) {
                                    layer.alert('编辑成功', {icon: 1, title: '提示'}, function (i) {
                                        layer.close(i);
                                        layer.close(index);//关闭弹出层
                                        $("#book")[0].reset()//重置form
                                        window.parent.location.reload();
                                    })
                                    table.reload('table1', {//重载表格
                                        page: {
                                            curr: 1
                                        }
                                    })
                                }
                            },
                            error: function () {
                            }
                        });
                    }
                });
                // layer.msg('修改' + data.id);

            }
        });

    });

    function edit() {
        $.ajax({
            url: "/office/update",
            type: "post",
            data: {
                "id": data.id,
                "name": data.name,
            },
            datatype: "text",
            success: function (result) {
                alert("修改成功");
            },
            error: function () {
            }
        });
    }
</script>
</body>
</html>
