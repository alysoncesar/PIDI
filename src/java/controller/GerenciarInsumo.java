package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Fornecedor;
import model.Insumo;
import model.InsumoDAO;
public class GerenciarInsumo extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        
        Insumo i = new Insumo();
        
        try{
            InsumoDAO iDAO = new InsumoDAO();
            if(acao.equals("desativar")){
                i.setIdInsumo(Integer.parseInt(id));
                if(iDAO.desativarInsumo(i)){
                    mensagem="Insumo desativado!";
                }else{
                    mensagem ="erro na desativação do Insumo";
                }     
            }
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='insumo.jsp';");
        out.println("</script>");
    }
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idInsumo = request.getParameter("idInsumo");
        String nome = request.getParameter("nome");
        String preco = request.getParameter("preco");
        String quantidadeMinima = request.getParameter("quantidadeMinima");      
        String quantidadeAtual = request.getParameter("quantidadeAtual");
        String idCategoria = request.getParameter("idCategoria");
        String idFornecedor = request.getParameter("idFornecedor");
        String mensagem = "";
        
        Insumo i = new Insumo();
        try{
            InsumoDAO iDAO = new InsumoDAO();
            if(!idInsumo.isEmpty()){
                i.setIdInsumo(Integer.parseInt(idInsumo));
            }
            if( nome.isEmpty()|| quantidadeMinima.isEmpty()||
              quantidadeAtual.isEmpty()|| idCategoria.isEmpty()||idFornecedor.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                i.setNome(nome);
                
                
                i.setPreco(0.0);               
                
                i.setQuantidadeMinima(Integer.parseInt(quantidadeMinima));
                i.setQuantidadeAtual(Integer.parseInt(quantidadeAtual));
                
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(Integer.parseInt(idCategoria));
                i.setCategoria(categoria);
                
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(Integer.parseInt(idFornecedor));
                i.setFornecedor(fornecedor);        
                
                if(iDAO.cadastrarInsumo(i)){
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Cargo!";
                }
            }   
        }catch(Exception e){
            System.out.println(e);
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='insumo.jsp';");
        out.println("</script>");
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
