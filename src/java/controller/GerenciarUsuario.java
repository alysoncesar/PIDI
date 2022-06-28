package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.Usuario;
import model.UsuarioDAO;

public class GerenciarUsuario extends HttpServlet {
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        
        Usuario u = new Usuario();
        
        try{
            UsuarioDAO uDAO = new UsuarioDAO();
            if(acao.equals("desativar")){
                u.setIdUsuario(Integer.parseInt(id));
                if(uDAO.desativarUsuario(u)){
                    mensagem="Usuario desativada!";
                }else{
                    mensagem ="erro na desativação do Usuario";
                }   
            }
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='usuario.jsp';");
        out.println("</script>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*idUsuario nome endereco rg cpf idCargo senha numero*/
        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");        
        String idCargo = request.getParameter("idCargo");
        String senha = request.getParameter("senha");
        String mensagem = "";
        
        Usuario u = new Usuario();
            
        try{
            UsuarioDAO uDAO = new UsuarioDAO();
            if(!idUsuario.isEmpty()){
                u.setIdUsuario(Integer.parseInt(idUsuario));
            }
            if( nome.isEmpty()||endereco.isEmpty()||rg.isEmpty()|| cpf.isEmpty()||idCargo.isEmpty()|| senha.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                u.setNome(nome);
                u.setEndereco(endereco);
                u.setRg(rg);
                u.setCpf(cpf);
                u.setSenha(senha);
                
                Cargo cargo = new Cargo();
                cargo.setIdCargo(Integer.parseInt(idCargo));
                u.setCargo(cargo); 
                
                if(uDAO.cadastrarUsuario(u)){
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Usuário!";
                }
            }   
        }catch(Exception e){
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='usuario.jsp';");
        out.println("</script>");
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}