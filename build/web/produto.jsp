<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="model.ProdutoDAO"%>
<%@page import="model.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="menuLateral.jsp" %>
<%@include file="controleLogin.jsp" %>
<html>

<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Produtos - Inventory Click</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
  <link rel="shortcut icon" href="dist/img/InventoryClick.png" type="image/x-icon">
  
    <script type="text/javascript">
            function confirmarDesativacao(id,nome){
                if(confirm("deseja mesmo desativar o produto :"+nome+"?")){
                    location.href='gerenciarproduto.do?acao=desativar&id='+id;
                }
            }  
            
             function puxarDados(idProduto,nome,valor,descricao){
                document.getElementById('idProdutoCadastro').value = idProduto;
                document.getElementById('idNomeCadastro').value = nome;
                document.getElementById('idValorCadastro').value = valor;
                document.getElementById('idDescricaoCadastro').value = descricao;
            }
    </script>
  
  
  
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">

    <header class="main-header">
      <a href="fornecedor.jsp" class="logo">
        <span class="logo-mini">Inventory Click</span>
        <span class="logo-lg">Inventory Click</span>
      </a>

      <!-- Header Navbar -->
      <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
          <span class="sr-only">Toggle navigation</span>
        </a>
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
              <button ><a href="controleLogout.jsp">deslogar</a></button>
            <!-- User Account Menu -->
            <li class="dropdown user user-menu">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!-- The user image in the navbar-->
                <img src="dist/img/avatar5.png" class="user-image" alt="User Image">
                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                <span class="hidden-xs"><%=nome%></span>
              </a>
              <ul class="dropdown-menu">
                <!-- Menu Body -->                
                <!-- Menu Footer-->
                <li class="user-footer">
                    <div class="pull-right">
                        <a href="#" class="btn btn-default btn-flat">Sair</a>
                    </div>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>
          Produtos
          <small>Gerenciamento de Produtos do sistema</small>
        </h1>
        <ol class="breadcrumb">
          <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
          <li class="active">Produtos</li>
        </ol>
      </section>

      <!-- Main content -->
      <section class="content container-fluid">

        <div class="row">
          <div class="col-md-8">

            <div class="box">
              <div class="box-header">
                <h3 class="box-title">Lista de Produtos</h3>
              </div>
              <!-- /.box-header -->
              <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th style="width: 10px">Id</th>
                      <th>Nome</th>
                      <th>Valor</th>
                      <th>Descri??o</th>        
                      <th>A??es</th>
                    </tr>
                    <jsp:useBean class="model.ProdutoDAO" id="pDAOLista"/>
                    <c:forEach var="p" items="${pDAOLista.lista}">
                        <tr>
                            <tr>
                        <td>${p.idProduto}</td>
                        <td>${p.nome}</td>
                        <td>${p.valor}</td>                    
                        <td>${p.descricao}</td>  
                        <td>
                            <button  onclick="puxarDados(${p.idProduto},'${p.nome}',${p.valor},'${p.descricao}')"class="btn btn-primary btn-edit btn-xs btn-flat">Editar</button>
                            <button  onclick="confirmarDesativacao(${p.idProduto},'${p.nome}')" class="btn btn-danger btn-delete btn-xs btn-flat">Excluir</button>
                        </td>
                        </tr>
                    </c:forEach>
                  </thead>
                  <tbody id="table-users">
                  </tbody>
                </table>
              </div>
              <!-- /.box-body -->
            </div>

          </div>
          <div class="col-md-4">

            <div class="row">

              <div class="col-xs-6">
                <!-- small box -->
                <div class="small-box bg-green">
                  <div class="inner">
                    <h3 id="number-users">0</h3>                    
                    <p>Produtos</p>
                  </div>
                  <div class="icon">
                    <i class="ion ion-person-add"></i>
                  </div>
                </div>
              </div>
              <!-- ./col -->
            </div>

            <div class="box box-success" id="box-user-create">
              <div class="box-header with-border">
                <h3 class="box-title">Novo Produto</h3>
              </div>
              <!-- /.box-header -->
              <!-- form start -->
              <form action="gerenciarproduto.do" method="POST" >
                <div class="box-body">
                  <div class="form-group">
                    <label>Produtos</label>                    
                    <select name ="idProduto" id="idProdutoCadastro" onchange="exibir_ocultar(this)" class="form-control">
                      <option selected="selected" value="">Selecione um Produto</option>
                      <jsp:useBean class="model.ProdutoDAO" id="pDAOCadastrar"/>
                      <c:forEach var="p" items="${pDAOCadastrar.lista}">
                            <option value="${p.idProduto}">${p.idProduto}-${p.nome}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputName">Nome</label>
                    <input required type="text" class="form-control" id="idNomeCadastro"
                      placeholder="Digite o nome" name="nome">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Valor</label>
                    <input required type="text" class="form-control" id="idValorCadastro"
                      placeholder="Digite o valor" name="valor">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Descri??o</label>
                    <input required type="text" class="form-control" id="idDescricaoCadastro"
                      placeholder="Digite a descri??o" name="descricao">
                  </div>         
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                  <button type="submit" class="btn btn-success">Salvar</button>
                </div>
              </form>
            </div>
          </div>
        </div>

      </section>
      <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
      <!-- To the right -->
      <div class="pull-right hidden-xs">
        <a target="_blank" href="#">Inventory Click</a>
      </div>
      <!-- Default to the left -->
      Copyright &copy; Inventory click 2022
    </footer>

  </div>

  <script src="classes/Utils.js"></script>
  <script src="models/user.js"></script>
  <script src="controllers/userController.js"></script>
</body>

</html>