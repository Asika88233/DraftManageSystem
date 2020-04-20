<head>

<body>
  <fieldset class="layui-elem-field layui-field-title">
    <legend>密码不能为纯数字哦</legend>
  </fieldset>
  <form class="layui-form" action="">
    <div class="layui-form-item">
      <label class="layui-form-label" style="width: 90px;">原始密码</label>
      <div class="layui-input-block">
        <input type="password" name="originPassword" lay-verify="required" autocomplete="off" placeholder="请输入原始密码"
          class="layui-input" style="width: 400px;">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">新密码</label>
      <div class="layui-input-block">
        <input type="password" name="newPassword" lay-verify="required" lay-reqtext="请输入密码" placeholder="请输入新密码"
          autocomplete="off" class="layui-input" style="width: 400px;">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
    </div>

  </form>
  <script>
    layui.use('form', function () {
      var form = layui.form;
      form.render();
      form.on('submit(demo1)', function (data) {
        layer.confirm("确定修改密码吗？", {
          btn: ['确定', '取消'],
          btn1: function (index, layero) {
            debugger;
            $.ajax({
              url: "/passwordEdit",
              data: JSON.stringify(data.field),
              type: "Post",
              contentType: "application/json",
              success: function (data) {
                if (data.code == "200") {
                  layer.alert(data.data);
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
        })
        return false;
      });
    });
  </script>
</body>
</head>