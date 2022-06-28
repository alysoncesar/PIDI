package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import model.UsuarioDAO;

public class GerenciarLogin extends HttpServlet {

    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String mensagem = "";
        HttpSession session = request.getSession(true);
        
        Usuario u = new Usuario();
        try{
            UsuarioDAO uDAO = new UsuarioDAO();            
            if(cpf.isEmpty() || senha.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                u.setCpf(cpf);
                u.setSenha(senha);
                if(uDAO.Logar(cpf,senha)){
                    session.setAttribute("cpf",cpf);
                    session.setAttribute("nome",uDAO.buscarNome(cpf));
                    System.out.println("nome:"+session.getAttribute("nome"));
                    mensagem ="logado com sucesso";
                    response.sendRedirect("fornecedor.jsp");
                }else{
                    mensagem ="cpf ou senha incorreto!";
                }
            }
        }catch(Exception e){
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='index.jsp';");
        out.println("</script>");
    }       
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
