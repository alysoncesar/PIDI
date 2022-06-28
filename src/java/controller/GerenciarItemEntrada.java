package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Insumo;
import model.InsumoDAO;
import model.ItemEntrada;
import model.ItemEntradaDAO;
import model.Produto;
import model.Usuario;
public class GerenciarItemEntrada extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        
        ItemEntrada ie = new ItemEntrada();
        
        try{
            ItemEntradaDAO ieDAO = new ItemEntradaDAO();
            if(acao.equals("desativar")){
                ie.setIdItemEntrada(Integer.parseInt(id));
                if(ieDAO.desativarItemEntrada(ie)){
                    mensagem="Entrada desativada!";
                }else{
                    mensagem ="erro na desativação de Entrada";
                }   
            }
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='itemEntrada.jsp';");
        out.println("</script>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idItemEntrada = request.getParameter("idItemEntrada");
        String data = request.getParameter("data");
        String quantidade = request.getParameter("quantidade");
        String idInsumo = request.getParameter("idInsumo");        
        String idUsuario = request.getParameter("idUsuario");
        String valor = request.getParameter("valor");
        String mensagem = "";
        
        
        ItemEntrada ie = new ItemEntrada();
        try{
            ItemEntradaDAO ieDAO = new ItemEntradaDAO();
            if(!idItemEntrada.isEmpty()){
                ie.setIdItemEntrada(Integer.parseInt(idItemEntrada));
            }
            if( data.isEmpty()|| quantidade.isEmpty()|| idInsumo.isEmpty()||
              idUsuario.isEmpty() || valor.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");                      
                java.sql.Date dataFinal = new java.sql.Date(fmt.parse(data).getTime());                 
                ie.setData(dataFinal);    
                ie.setQuantidade(Integer.parseInt(quantidade));
                
                Insumo insumo = new Insumo();
                insumo.setIdInsumo(Integer.parseInt(idInsumo));
                ie.setInsumo(insumo);
                
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(idUsuario));                
                ie.setUsuario(usuario);   
                                
                /*InsumoDAO ins = new InsumoDAO();
                Double preco = ins.buscarPreco(insumo.getIdInsumo());
                int quantidadeEntrada = Integer.parseInt(quantidade);
                Double valorFinal = quantidadeEntrada * preco ;*/
                ie.setValor(Double.parseDouble(valor));
                                
                if(ieDAO.cadastrarItemEntrada(ie)){
                    InsumoDAO iDAO = new InsumoDAO();
                    iDAO.atualizarQuantidadeEntrada(Integer.parseInt(idInsumo),Integer.parseInt(quantidade));
                    iDAO.AtualizarEntradaProduto(Integer.parseInt(idInsumo));
                    iDAO.AtualizarPrecoInsumo(Integer.parseInt(idInsumo),iDAO.buscarPreco(Integer.parseInt(idInsumo)));
                    mensagem ="gravado com sucesso";
                }else{
                    mensagem ="Erro ao gravar Entrada!";
                }
            }   
        }catch(Exception e){
            out.print(e);
            System.out.println(e);
            mensagem="Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='itemEntrada.jsp';");
        out.println("</script>");
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
