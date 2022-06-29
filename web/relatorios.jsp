<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="model.UsuarioDAO"%>
<%@page import="model.Usuario"%>
<%@page import="model.ComandaDAO"%>
<%@page import="model.Comanda"%>
<%@page import="model.ItemSaida"%>
<%@page import="model.ItemSaidaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="menuLateral.jsp" %>
<%@include file="controleLogin.jsp" %>
<html>

<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Relatório - Inventory Click</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
  <link rel="shortcut icon" href="dist/img/InventoryClick.png" type="image/x-icon">
  
   <script type="text/javascript">
            function buscarValor(){
                data = document.getElementById("idDataCadastro").value;
                location.href='gerenciarrelatorio.do?acao=buscar&data='+data;
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
          Relatório
          <small>Relatórios do sistema</small>
        </h1>
        <ol class="breadcrumb">
          <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
          <li class="active">Relatórios</li>
        </ol>
      </section>

       <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                    <tr >
                        <th>ValorTotal das comandas por Dia</th>
                        <th>
                            <div>                               
                                <div>
                                    <input required type="date" value="${dataFinal}" style="max-width: 150px;margin-left: -810px" 
                                        id="idDataCadastro" placeholder="Digite a data" name="data">  
                                    <button type="submit" onclick="buscarValor()"class="btn btn-success">Buscar</button>
                                </div>                                 
                            </div> 
                        </th>
                    </tr>
                    <td><input id="valorFinal" style="border:none;" disabled value="R$${valorFinal}"></td>   
                  </thead>
                  <tbody id="table-users">
                  </tbody>
                </table>
              </div>
       <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                    <tr >
                        <th>ValorTotal das comandas</th>                        
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                       <td>
                            <div>
                            <jsp:useBean class="model.ComandaDAO" id="cDAO"/>
                                <c:forEach var="c" items="${cDAO.listaValorTotalComandas}">
                                    <tr>
                                        <td id="valorComanda">R$${c.valor}</td> 
                                    </tr>
                                </c:forEach>                                 
                            </div>
                        </td> 
                        <td>
                            <th>ValorTotal das Saidas diario</th>
                            <jsp:useBean class="model.ItemSaidaDAO" id="isDAO"/>
                                <c:forEach var="is" items="${isDAO.listaValorTotalSaida}">
                                    <tr>
                                        <td><input id="valorTotalSaida" style="border:none;" disabled value="R$${valorTotalSaida}"></td>   
                                    </tr>
                            </c:forEach>
                        </td>
                    </tr>
                    
                  <th>balanço final SAIDA | Comanda</th>
                    <c:forEach var="c" items="${cDAO.listaValorTotalComandas}">
                    <c:forEach var="is" items="${isDAO.listaValorTotalSaida}">
                  <td><input id="valorBalanco" style="border:none;"  "disabled>R$${valorFinal - valorTotalSaida }</td>
                    </c:forEach>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
                              
        <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                  <br>
                  <br>
                  <br>
                  <th>quantidade comanda abertas</th>
                            <tr>
                        <td><input id="comandaDiaria" style="border:none;" disabled value="${comandaDiaria}"></td>
                        
                        
                        </tr>
                  </thead>
                  <tbody id="table-users">
                  </tbody>
                </table>
              </div>
                    <br>
                    <br>
                    <br>
            <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                  <br>
                  <br>
                  <br>
                  <th>Produto/quantidade comanda/ Valor Total Produto</th>
                    <tr>
                      <th>Nome produto</th>
                      <th>Contador Saidas</th>
                      <th>Valor Final</th>
                      
                    </tr>
                    <jsp:useBean class="model.ProdutoDAO" id="pDAOLista"/>
                    <c:forEach var="p" items="${pDAOLista.listaContadorSaida}">
                        <tr>
                            <tr>
                        <td>${p.nome}</td>
                        <td>${p.contadorSaida}</td>
                        <td>RS${p.valor}</td>
                        
                        
                        </tr>
                    </c:forEach>
                  </thead>
                  <tbody id="table-users">
                  </tbody>
                </table>
              </div>   
                    
              <div class="box-body no-padding">
                <table class="table table-striped">
                  <thead>
                  <br>
                  <br>
                  <br>
                  <th>nome/quantidade comanda</th>
                    <tr>
                      <th>Nome Insumo</th>
                      <th>Contador Entrada</th>
                      <th>Contador Saidas</th>                      
                    </tr>
                    <jsp:useBean class="model.InsumoDAO" id="iDAOLista"/>
                    <c:forEach var="i" items="${iDAOLista.listaEntradaSaida}">
                        <tr>
                        <tr>
                        <td>${i.nome}</td>
                        <td>${i.contadorInsumoEntrada}</td>
                        <td>${i.contadorInsumoSaida}</td>
                        </tr>
                    </c:forEach>
                  </thead>
                  <tbody id="table-users">
                  </tbody>
                </table>
              </div>      
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