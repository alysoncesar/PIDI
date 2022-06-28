<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="model.UsuarioDAO"%>
<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="menuLateral.jsp" %>
<%@include file="controleLogin.jsp" %>
<%@include file="mascara.jsp" %>
<html>

<head>s
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Usuários - Inventory Click</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
  <link rel="shortcut icon" href="dist/img/InventoryClick.png" type="image/x-icon">
  
    <script type="text/javascript">
            function confirmarDesativacao(id,nome){
                if(confirm("deseja mesmo desativar o usuario :"+nome+"?")){
                    location.href='gerenciarusuario.do?acao=desativar&id='+id;
                }
            } 
             function puxarDados(idUsuario,nome,endereco,rg,cpf,idCargo){
                document.getElementById('idUsuarioCadastro').value = idUsuario;
                document.getElementById('idNomeCadastro').value = nome;
                document.getElementById('idEnderecoCadastro').value = endereco;
                document.getElementById('idRgCadastro').value = rg;
                document.getElementById('idCpfCadastro').value = cpf;
                document.getElementById('idCargoCadastro').value = idCargo;
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
          Usuário
          <small>Gerenciamento de Usuário do sistema</small>
        </h1>
        <ol class="breadcrumb">
          <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
          <li class="active">Usuário</li>
        </ol>
      </section>

      <!-- Main content -->
      <section class="content container-fluid">

        <div class="row">
          <div class="col-md-8">

            <div class="box">
              <div class="box-header">
                <h3 class="box-title">Lista de Usuários</h3>
              </div>
              <!-- /.box-header -->
              <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th style="width: 10px">Id</th>
                      <th>Nome</th>
                      <th>Endereço</th>
                      <th>RG</th>
                      <th>CPF</th>        
                      <th>Cargo</th>        
                      <th>Ações</th>
                    </tr>
                    <jsp:useBean class="model.UsuarioDAO" id="uDAOLista"/>
                    <c:forEach var="u" items="${uDAOLista.lista}">
                        <tr>
                            <tr>
                        <td>${u.idUsuario}</td>
                        <td>${u.nome}</td>
                        <td>${u.endereco}</td>                    
                        <td>${u.rg}</td>  
                        <td>${u.cpf}</td>  
                        <td>${u.cargo.idCargo}</td>  
                        <td>
                            <button  onclick="puxarDados(${u.idUsuario},'${u.nome}','${u.endereco}','${u.rg}','${u.cpf}',${u.cargo.idCargo})"class="btn btn-primary btn-edit btn-xs btn-flat">Editar</button>
                            <button  onclick="confirmarDesativacao(${u.idUsuario},'${u.nome}')" class="btn btn-danger btn-delete btn-xs btn-flat">Excluir</button>
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
                    <p>Usuários</p>
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
              <form action="gerenciarusuario.do" method="POST" >
                <div class="box-body">
                  <div class="form-group">
                    <label>Usuários</label>                    
                    <select name ="idUsuario" id="idUsuarioCadastro" onchange="exibir_ocultar(this)" class="form-control">
                      <option selected="selected" value="">Selecione um Usuário</option>
                      <jsp:useBean class="model.UsuarioDAO" id="uDAOCadastrar"/>
                      <c:forEach var="u" items="${uDAOCadastrar.lista}">
                            <option value="${u.idUsuario}">${u.idUsuario}-${u.nome}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputName">Nome</label>
                    <input required type="text" class="form-control" id="idNomeCadastro"
                      placeholder="Digite o nome" name="nome">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Endereço</label>
                    <input required type="text" class="form-control" id="idEnderecoCadastro"
                      placeholder="Digite o endereço" name="endereco">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">RG</label>
                    <input required type="text" maxlength="9" onkeypress="formatar(this,'#.###.###')" class="form-control" id="idRgCadastro"
                      placeholder="Digite o RG" name="rg">
                  </div>   
                  <div class="form-group">
                    <label for="exampleInputPassword1">CPF</label>
                    <input required type="text" maxlength="14" onkeypress="formatar(this,'###.###.###-##')" class="form-control" id="idCpfCadastro"
                      placeholder="Digite o CPF" name="cpf">
                  </div>  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Senha</label>
                    <input required type="password" maxlength="10" class="form-control" id="exampleInputPassword"
                      placeholder="Digite a senha" name="senha">
                  </div>   
                  <div class="form-group">
                    <label>Cargo</label>                    
                    <select name ="idCargo" id="idCargoCadastro" onchange="exibir_ocultar(this)" class="form-control">
                      <option selected="selected" value="">Selecione um Cargo</option>
                      <jsp:useBean class="model.CargoDAO" id="cDAOCadastrar"/>
                      <c:forEach var="c" items="${cDAOCadastrar.lista}">
                            <option value="${c.idCargo}">${c.idCargo}-${c.nome}</option>
                      </c:forEach>
                    </select>
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