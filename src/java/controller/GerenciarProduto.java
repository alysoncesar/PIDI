package controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.ProdutoDAO;
public class GerenciarProduto extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        
        Produto p = new Produto();
        
        try{
            ProdutoDAO pDAO = new ProdutoDAO();
            if(acao.equals("desativar")){
                p.setIdProduto(Integer.parseInt(id));
                if(pDAO.desativarProduto(p)){
                    mensagem="Produto desativada!";
                }else{
                    mensagem ="erro na desativação do Produto";
                }     
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='produto.jsp';");
        out.println("</script>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idProduto = request.getParameter("idProduto");
        String nome = request.getParameter("nome");
        String valor = request.getParameter("valor");
        String descricao = request.getParameter("descricao");
        String mensagem = "";
        
        Produto p = new Produto();
        try{
            ProdutoDAO pDAO = new ProdutoDAO();
            if(!idProduto.isEmpty()){
                p.setIdProduto(Integer.parseInt(idProduto));
            }
            if( nome.isEmpty()||valor.isEmpty()|| descricao.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                p.setNome(nome);
                p.setValor(Double.parseDouble(valor));
                p.setDescricao((descricao));
                                
                if(pDAO.cadastrarProduto(p)){
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Produto!";
                }
            }   
        }catch(Exception e){
            System.out.println(e);
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='produto.jsp';");
        out.println("</script>");
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
