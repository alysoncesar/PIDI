package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Comanda;
import model.ComandaDAO;
import model.Fornecedor;
import model.Produto;
import model.ProdutoDAO;
import model.Usuario;
import model.UsuarioDAO;
public class GerenciarComanda extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        String data = request.getParameter("data");
        
        Comanda c = new Comanda();
        
        try{
            ComandaDAO cDAO = new ComandaDAO();
            if(acao.equals("desativar")){
                c.setIdComanda(Integer.parseInt(id));
                if(cDAO.desativarComanda(c)){
                    mensagem="Comanda desativada!";
                }else{
                    mensagem ="erro na desativação da comanda";
                }    
                
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='comanda.jsp';");
        out.println("</script>");
    }
/*idProduto nome preco quantidadeMin	quantidadeMax quantidadeAtual idCategoria idFornecedor */    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idComanda = request.getParameter("idComanda");
        String idProduto = request.getParameter("idProduto");
        String quantidade = request.getParameter("quantidade");
        String idUsuario = request.getParameter("idUsuario");
        String data = request.getParameter("data");
        String mensagem = "";
        
        Comanda c = new Comanda();
        try{
            ComandaDAO cDAO = new ComandaDAO();
            if(!idComanda.isEmpty()){
                c.setIdComanda(Integer.parseInt(idComanda));
            }
            if(idProduto.isEmpty()|| idUsuario.isEmpty()|| quantidade.isEmpty() ||data.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                 
                Double valor = cDAO.buscarPreco(Integer.parseInt(idProduto));
                Double valorFinal = valor * Double.parseDouble(quantidade);
                c.setValor(valorFinal);
                
                c.setQuantidadeProduto(Integer.parseInt(quantidade));
                
                Produto p = new Produto();
                p.setIdProduto(Integer.parseInt(idProduto));
                c.setProduto(p);
                
                Usuario u = new Usuario();
                u.setIdUsuario(Integer.parseInt(idUsuario)); 
                c.setUsuario(u);
                
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");                      
                java.sql.Date dataFinal = new java.sql.Date(fmt.parse(data).getTime());  
                c.setData(dataFinal);
                
                if(cDAO.cadastrarComanda(c)){
                    
                                        
                    UsuarioDAO uDAO = new UsuarioDAO();
                    uDAO.AtualizarComandaAberta(u);
                    
                     
                    ProdutoDAO pDAO = new ProdutoDAO();
                    pDAO.AtualizarContadorSaida(p);
                    
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Comanda!";
                }
            }   
        }catch(Exception e){
            System.out.println(e);
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='comanda.jsp';");
        out.println("</script>");
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
