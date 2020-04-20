<head>

<body>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>个人信息一天只能修改一次</legend>
    </fieldset>
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 90px;">用户名称</label>
            <div class="layui-input-block">
                <input type="password" name="username" lay-verify="" autocomplete="off" placeholder="请输入原始密码"
                    class="layui-input" style="width: 400px;">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">验证日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd"
                        autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">稿件完成数</label>
            <div class="layui-input-block">
                <input type="text" name="username" lay-verify="" placeholder="" autocomplete="off"
                    class="layui-input" style="width: 400px;" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
    <script>
        layui.use(['form', 'laydate'], function () {
            var form = layui.form
                , layer = layui.layer
                , laydate = layui.laydate;
            form.render();
            laydate.render({
                elem: '#date'
            });
        });
    </script>
</body>