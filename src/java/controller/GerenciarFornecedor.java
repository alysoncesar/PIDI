package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Fornecedor;
import model.FornecedorDAO;
public class GerenciarFornecedor extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");
        
        Fornecedor f = new Fornecedor();
        
        try{
            FornecedorDAO fDAO = new FornecedorDAO();
            if(acao.equals("desativar")){
                f.setIdFornecedor(Integer.parseInt(id));
                if(fDAO.desativarFornecedor(f)){
                    mensagem="Fornecedor desativado!";
                }else{
                    mensagem ="erro na desativação de Fornecedor";
                }   
            }
           
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='fornecedor.jsp';");
        out.println("</script>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idFornecedor = request.getParameter("idFornecedor");
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String cep = request.getParameter("cep");  
        String mensagem = "";
        
        Fornecedor f = new Fornecedor();
        try{
            FornecedorDAO fDAO = new FornecedorDAO();
            if(!idFornecedor.isEmpty()){
                f.setIdFornecedor(Integer.parseInt(idFornecedor));
            }
            if( nome.isEmpty()||cnpj.isEmpty()|| endereco.isEmpty()|| cep.isEmpty()){
                mensagem="campo obrigatório deve ser preenchido";
            }else{       
                f.setNome(nome);
                f.setCnpj(cnpj);
                f.setEndereco(endereco);
                f.setCep(cep);
                
                if(fDAO.cadastrarFornecedor(f)){
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
        out.println("location.href='fornecedor.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
