<%@include file="mascara.jsp" %>
<head>
            <title>Login - Inventory Click</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="icon" type="image/png" href="images/icons/InventoryClick.png"/>
            <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
            <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
            <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
            <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
            <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
            <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
            <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
            <link rel="stylesheet" type="text/css" href="css/util.css">
            <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>	
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100 p-t-50 p-b-90">
                    <form action="gerenciarlogin.do" method="POST" class="login100-form validate-form flex-sb flex-w">
                        <span class="login100-form-title p-b-51">Login</span>
                            <div class="wrap-input100 validate-input m-b-16" data-validate = "CPF obrigatório">
                                <input class="input100" maxlength="14" onkeypress="formatar(this,'###.###.###-##')" id="login" type="text" name="cpf" placeholder="Cpf">
                                    <span class="focus-input100"></span>
                            </div>				

                            <div class="wrap-input100 validate-input m-b-16" data-validate = "Senha obrigatória">
                                    <input class="input100" id="password" type="password" name="senha" placeholder="Senha">
                                    <span class="focus-input100"></span>
                            </div>
                            <div class="container-login100-form-btn m-t-17">
                                <button type="submit" class="login100-form-btn" value="Log In">Login</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="dropDownSelect1"></div>	
            <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
            <script src="vendor/animsition/js/animsition.min.js"></script>
            <script src="vendor/bootstrap/js/popper.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
            <script src="vendor/select2/select2.min.js"></script>
            <script src="vendor/daterangepicker/moment.min.js"></script>
            <script src="vendor/daterangepicker/daterangepicker.js"></script>
            <script src="vendor/countdowntime/countdowntime.js"></script>
            <script src="js/main.js"></script>
    </body>