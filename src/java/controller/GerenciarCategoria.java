package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.CategoriaDAO;
public class GerenciarCategoria extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        
        Categoria c = new Categoria();
        
        try{
            CategoriaDAO cDAO = new CategoriaDAO();
            if(acao.equals("desativar")){
                c.setIdCategoria(Integer.parseInt(id));
                if(cDAO.desativarCategoria(c)){
                    mensagem="Categoria desativada!";
                }else{
                    mensagem ="erro na desativação de Categoria";
                }  
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='categoria.jsp';");
        out.println("</script>");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idCategoria = request.getParameter("idCategoria");
        String nome = request.getParameter("nome");
        String mensagem = "";
        
        Categoria c = new Categoria();
        try{
            CategoriaDAO cDAO = new CategoriaDAO();
            if(!idCategoria.isEmpty()){
                c.setIdCategoria(Integer.parseInt(idCategoria));
            }
            if( nome.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
                
            }else{       
                c.setNome(nome);
                if(cDAO.cadastrarCategoria(c)){
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="categoria já cadastrada!";
                }
            }
        }catch(Exception e){
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='categoria.jsp';");
        out.println("</script>");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
