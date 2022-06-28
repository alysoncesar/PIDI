package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Insumo;
import model.InsumoDAO;
import model.ItemSaida;
import model.ItemSaidaDAO;
import model.Produto;
import model.ProdutoDAO;
import model.Usuario;

public class GerenciarItemSaida extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        
        ItemSaida is = new ItemSaida();
        
        try{
            ItemSaidaDAO isDAO = new ItemSaidaDAO();
            if(acao.equals("desativar")){
                is.setIdItemSaida(Integer.parseInt(id));
                if(isDAO.desativarItemSaida(is)){
                    mensagem="Saida desativada!";
                }else{
                    mensagem ="erro na desativação de Saida";
                }   
            }
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='itemSaida.jsp';");
        out.println("</script>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* iditemEntrada dia valor quantidade	idProdutos idUsuario */
        PrintWriter out = response.getWriter();
        String idItemSaida = request.getParameter("idItemSaida");
        String data = request.getParameter("data");
        String quantidade = request.getParameter("quantidade");
        String idInsumo = request.getParameter("idInsumo");        
        String idUsuario = request.getParameter("idUsuario");
        String idProduto = request.getParameter("idProduto");
        String mensagem = "";
        
        ItemSaida is = new ItemSaida();
        try{
            ItemSaidaDAO isDAO = new ItemSaidaDAO();
            if(!idItemSaida.isEmpty()){
                is.setIdItemSaida(Integer.parseInt(idItemSaida));
            }
            if( data.isEmpty()|| quantidade.isEmpty()|| idInsumo.isEmpty()||
              idUsuario.isEmpty() || idProduto.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");                      
                java.sql.Date dataFinal = new java.sql.Date(fmt.parse(data).getTime());   
                is.setData(dataFinal);                
                is.setQuantidade(Integer.parseInt(quantidade));
                
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(idInsumo));
                is.setInsumo(insumo);
                
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(idUsuario));
                is.setUsuario(usuario);        
                
                Produto produto = new Produto();
                produto.setIdProduto(Integer.parseInt(idProduto));
                is.setProduto(produto);
                
                 InsumoDAO ins = new InsumoDAO();
                Double preco = ins.buscarPreco(insumo.getIdInsumo());
                int quantidadeEntrada = Integer.parseInt(quantidade);
                Double valorFinal = quantidadeEntrada * preco ;
                is.setValor(valorFinal);
                
                if(isDAO.cadastrarItemSaida(is)){
                    InsumoDAO i = new InsumoDAO();
                    i.atualizarQuantidadeSaida(Integer.parseInt(idInsumo),Integer.parseInt(quantidade));
                    i.AtualizarSaidaProduto(Integer.parseInt(idInsumo));
                                       
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Saída!";
                }
            }   
        }catch(Exception e){
            out.print(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='itemSaida.jsp';");
        out.println("</script>");
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
