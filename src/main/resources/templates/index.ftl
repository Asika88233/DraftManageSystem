<!doctype html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <title>稿件管理系统——大厅</title>
  <link rel="stylesheet" href="/adminlte/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/layui/css/layui.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/adminlte/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="/adminlte/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="/adminlte/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/adminlte/css/skins/skin-black.css">
  <link rel="stylesheet" href="/DataTables-1.10.15/media/css/jquery.dataTables.css">
  <!-- jQuery 3 -->
  <script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
  <!-- Bootstrap 3.3.7 -->
  <script src="/adminlte/bootstrap/js/bootstrap.min.js"></script>
  <!-- AdminLTE App -->
  <script src="/adminlte/js/adminlte.min.js"></script>
  <script src="/adminlte/js/fastclick.js"></script>
  <script src="/adminlte/js/app.min.js"></script>
  <script src="/adminlte/js/icheck.min.js"></script>
  <script src="/layui/layui.js"></script>
  <script src="/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
  <script type="text/javascript" src="/js/controller.js"></script>
</head>
 <script>
    function logout() {
      if (confirm("是否确认注销？")) {
        $.ajax({
          type: "GET",//传输方式
          url: "/index/loginout",//action路径
          success: function (msg) {
            alert("注销成功！");
            location.reload();
          }
        });
      }
    }
  </script>
<body class="skin-black sidebar-mini">
  <div class="wrapper">
    <header class="main-header">
      <!-- Logo -->
      <a href="javascript://" class="logo">
        <span class="logo-lg"><b>稿件管理系统</b></span>
      </a>
      <!-- Header Navbar: style can be found in header.less -->
      <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="javascript://" class="sidebar-toggle" data-toggle="offcanvas" role="button">
          <span class="sr-only">切换导航</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
            <li><a href="javascript://" data-toggle="control-sidebar" onclick="openMenu('personinfo')">${userName}</a>
            </li>
            <li>
              <a href="javascript://" title="注销" onclick="logout()" data-toggle="control-sidebar"><i
                  class="fa fa-gears"></i><span>注销</span></a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
          <li class="treeview">
            <#list menuArray as menuJson>
              <a href="javascript://" menu_id="${menuJson.id}">
                <i class="${menuJson.icon}"></i> <span>${menuJson.name}</span>
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <#list menuJson.subMenus as subMenuArray>
                  <li>
                    <a href="javascript://" onclick="openMenu('${subMenuArray.id}')" menu_id="${subMenuArray.id}"><i
                        class="${subMenuArray.icon}"></i> ${subMenuArray.name}</a>
                  </li>
                </#list>
              </ul>
            </#list>
          </li>

        </ul>
      </section>
      <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" style="min-height: 946px;">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1 id="menu_title">

        </h1>
      </section>

      <!-- Main content -->
      <section class="content">
        <div class="row">
          <div class="col-md-12">
            <!-- /.box -->
            <div id="main_content">
              <h1></h1>
            </div>
          </div>
          <!-- /.col-->
        </div>
        <!-- ./row -->
      </section>
      <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
      <div class="pull-right hidden-xs">
        <b>Version</b> 2.3.7
      </div>
      <strong>Copyright © 2019 All rights reserved.</strong>
    </footer>
  </div>
</body>

</html>