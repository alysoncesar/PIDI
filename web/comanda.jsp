<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="model.ComandaDAO"%>
<%@page import="model.Comanda"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="menuLateral.jsp" %>
<%@include file="controleLogin.jsp" %>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Comanda - Inventory Click</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
  <link rel="shortcut icon" href="dist/img/InventoryClick.png" type="image/x-icon">
  
    <script type="text/javascript">
            function confirmarDesativacao(id){
                if(confirm("deseja mesmo desativar a comanda de número:"+id+"?")){
                    location.href='gerenciarcomanda.do?acao=desativar&id='+id;
                }
            }       
            function puxarDados(comanda,idProduto,idUsuario,idQuantidade,data){
                document.getElementById('idComandaCadastro').value = comanda;
                document.getElementById('idProdutoCadastro').value = idProduto;
                document.getElementById('idDataCadastro').value = data;
                document.getElementById('idQuantidadeCadastro').value = idQuantidade;
                document.getElementById('idUsuarioCadastro').value = idUsuario;
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
          Comandas
          <small>Gerenciamento de Comandas do sistema</small>
        </h1>
        <ol class="breadcrumb">
          <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
          <li class="active">Comanda</li>
        </ol>
      </section>

      <!-- Main content -->
      <section class="content container-fluid">

        <div class="row">
          <div class="col-md-8">

            <div class="box">
              <div class="box-header">
                <h3 class="box-title">Lista de Comandas</h3>
              </div>
              <!-- /.box-header -->
              <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th>Número</th>
                      <th>valor</th>
                      <th>idProduto</th>     
                      <th>QuantidadeProduto</th> 
                      <th>idUsuario</th>    
                      <th>data</th>    
                      <th>Ações</th>     
                    </tr>
                    <jsp:useBean class="model.ComandaDAO" id="cDAOLista"/>
                    <c:forEach var="c" items="${cDAOLista.lista}">
                        <tr>
                            <tr>
                        <td>${c.idComanda}</td>
                        <td>R$${c.valor}</td>
                        <td>${c.produto.idProduto}</td>                   
                        <td>${c.quantidadeProduto}</td> 
                        <td>${c.usuario.idUsuario}</td>                         
                        <td>${c.data}</td>                         
                        <td>
                            <button  onclick="puxarDados(${c.idComanda},${c.produto.idProduto},${c.usuario.idUsuario},${c.quantidadeProduto},${c.data})"class="btn btn-primary btn-edit btn-xs btn-flat">Editar</button>
                            <button  onclick="confirmarDesativacao(${c.idComanda})" class="btn btn-danger btn-delete btn-xs btn-flat">Excluir</button>
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
                    <p>Comanda</p>
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
                <h3 class="box-title">Novo Usuário</h3>
              </div>
              <!-- /.box-header -->
              <!-- form start -->
              <form action="gerenciarcomanda.do" method="POST" >
                <div class="box-body">
                  <div class="form-group">
                    <label>Comanda</label>                    
                    <select name ="idComanda" id="idComandaCadastro" onchange="exibir_ocultar(this)" class="form-control">
                      <option selected="selected" value="">Selecione uma Comanda</option>
                      <jsp:useBean class="model.ComandaDAO" id="cDAOCadastrar"/>
                      <c:forEach var="c" items="${cDAOCadastrar.lista}">
                            <option value="${c.idComanda}">${c.idComanda}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Quantidade Do Produto</label>
                    <input required type="text" class="form-control" id="idQuantidadeCadastro"
                      placeholder="Digite a quantidade" name="quantidade">
                  </div>  
                   <div class="form-group">
                    <label>Produto</label>                    
                    <select name ="idProduto" id="idProdutoCadastro" onchange="exibir_ocultar(this)" class="form-control">
                      <option selected="selected" value="">Selecione um Produto</option>
                      <jsp:useBean class="model.ProdutoDAO" id="pDAOCadastrar"/>
                      <c:forEach var="p" items="${pDAOCadastrar.lista}">
                            <option value="${p.idProduto}">${p.idProduto}-${p.nome}</option>
                      </c:forEach>
                    </select>
                  </div>    
                  <div class="form-group">
                    <label>Usuários</label>                    
                    <select name ="idUsuario" id="idUsuarioCadastro" onchange="exibir_ocultar(this)" class="form-control">
                      <option selected="selected" value="">Selecione um Usuario</option>
                      <jsp:useBean class="model.UsuarioDAO" id="uDAOCadastrar"/>
                      <c:forEach var="u" items="${uDAOCadastrar.lista}">
                            <option value="${u.idUsuario}">${u.idUsuario}-${u.nome}</option>
                      </c:forEach>
                    </select>
                  </div>    
                      <div class="form-group">
                    <label for="exampleInputEmail1">Data de entrada</label>
                    <input required type="date" class="form-control" id="idDataCadastro"
                      placeholder="Digite a data" name="data">
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