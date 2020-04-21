<fieldset class="layui-elem-field layui-field-title">
    <legend>任务上传与详情查看</legend>
  </fieldset>
<div>
    <table id="table_id_quest_upload" class="display">
        <thead>
            <tr>
                <th>任务编号</th>
                <th>任务名称</th>
                <th>任务酬劳</th>
                <th>任务发布者</th>
                <th>任务发布日期</th>
                <th>操作</th>
                <th hidden>任务详情</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<fieldset class="layui-elem-field layui-field-title">
    <legend>任务完成情况查看</legend>
  </fieldset>
  <div>
    <table id="table_id_quest_info" class="display">
        <thead>
            <tr>
                <th>任务编号</th>
                <th>稿件字数</th>
                <th>完成情况</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
  </div>
<script>
    function alertBiref(questBiref) {
        layer.open({
            title: '任务详情'
            , content: questBiref
        });
    }
    function fileUpload(id){
        window.id=id;
        console.log(window.id);
    }
    layui.use('upload', function () {
            var upload = layui.upload;
            //执行实例
            upload.render({
                elem: '.layui-btn-normal' //绑定元素
                , url: '/file/upload' //上传接口
                , accept: 'file'
                , exts: 'doc|docx'
                , acceptMime: '.docx,.doc'
                , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                    this.data.id = window.id;
                    layer.load(); //上传loading
                }
                , done: function (res, index, upload) {
                    layer.closeAll('loading'); //关闭loading
                }
                , error: function (index, upload) {
                    layer.closeAll('loading'); //关闭loading
                }
            });
        });

    $(document).ready(function () {
        $('#table_id_quest_upload').DataTable({
            ajax: {
                url: "/getQuestReceive",
                type: "GET",
                "dataType": "json",
            },
            "columns": [
                { "data": "id" },
                { "data": "questName" },
                { "data": "questReward" },
                { "data": "questOrder" },
                { "data": "createTime" },
                { "data": "questBiref" }
            ],
            "columnDefs": [{
                "targets": 5,
                "render": function (data, type, row) {
                    var questBiref = '"' + row.questBiref + '"';
                    var id = '"' + row.id + '"';
                    var html = "";
                    if(row.receiveTime==null){
                      html += "<div class='layui-btn-group'><button type = 'button' class='layui-btn'Onclick=alertBiref(" + questBiref + ")>详情</button><button type='button' class='layui-btn layui-btn-normal' Onclick=fileUpload(" + id + ")> <i class='layui-icon'>&#xe67c;</i>上传文件</button>"
                    }
                    else{
                       html += "<div class='layui-btn-group'><button type = 'button' class='layui-btn'Onclick=alertBiref(" + questBiref + ")>详情</button><button type='button' class='layui-btn layui-btn-disabled' > <i class='layui-icon'>&#xe67c;</i>已经上传</button>"
                    }
                    return html;
                }
            }],
            "oLanguage": {  //将英文信息转换未中文
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "  首页  ",
                    "sPrevious": "  上一页  ",
                    "sNext": "   下一页  ",
                    "sLast": "  末页  "
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
    });
</script>