<%
            String cpf = (String) session.getAttribute("cpf");
            String nome = (String) session.getAttribute("nome");
            if(cpf == null){
                response.sendRedirect("index.jsp");
            }
        %>