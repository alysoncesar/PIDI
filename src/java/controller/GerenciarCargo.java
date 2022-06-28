
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.CargoDAO;

public class GerenciarCargo extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        
        Cargo c = new Cargo();
        
        try{
            CargoDAO cDAO = new CargoDAO();
            if(acao.equals("desativar")){
                c.setIdCargo(Integer.parseInt(id));
                if(cDAO.desativarCargo(c)){
                    mensagem="cargo desativado!";
                }else{
                    mensagem ="cargo já cadastrado";
                } 
            }    
            }catch(Exception e){
            System.out.println(e);
            }
            out.println("<script type='text/javascript'>");
            out.println("alert('"+mensagem+"');");
            out.println("location.href='cargo.jsp';");
            out.println("</script>");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idCargo = request.getParameter("idCargo");
        String nome = request.getParameter("nome");       
        String mensagem = "";        
     
        Cargo c = new Cargo();
        try{
            CargoDAO cDAO = new CargoDAO();
            
            if(!idCargo.isEmpty()){
                c.setIdCargo(Integer.parseInt(idCargo));
            }
            if( nome.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                c.setNome(nome);
                if(cDAO.cadastrarCargo(c)){
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Cargo!";
                }
            }
        }catch(Exception e){
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='cargo.jsp';");
        out.println("</script>");
       
    }
    
    
    
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
