<div>
    <table id="table_id" class="display">
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
<script>
    $(document).ready(function () {
        $('#table_id').DataTable({
            ajax: {
                url: "/getQuest",
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
                    html += "<div class='layui-btn-group'><button type = 'button' class='layui-btn'Onclick=alertBiref(" + questBiref + ")>详情</button ><button type='button' class='layui-btn'Onclick=questReceive(" + id + ")>领取</button>"
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
                "sEmptyTable": "当前没有可以领取的任务",
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
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
    });
    function alertBiref(questBiref) {
        layer.open({
            title: '任务详情'
            , content: questBiref
        });
    }
    function questReceive(questId) {
        layer.confirm('是否确定领取任务?', {
            icon: 3,
            title: '提示',
            btn: ['确定', '取消'],
            btn1: function (index, layero) {
                var data = { "questId": questId };
                $.ajax({
                    url: "/questReceive",
                    type: "Post",
                    dataType: 'json',
                    data: data,
                    ContentType: "application/x-www-form-urlencoded",
                    success: function (data) {
                        if (data.code == "200") {
                            layer.alert(data.data,function(index){
                                $("#main_content").load("/menu/questReceive");
                                layer.close(index);
                            })
                        }
                        else {
                            layer.alert(data.data, function (index) {
                                location.reload();
                            });
                        }
                    },
                    error: function (data) {
                        layer.alert("系统出现错误");
                    }
                });
                layer.close(index);
            }
        });
    }

</script>