
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Comanda;
import model.ComandaDAO;
import model.ItemSaidaDAO;

public class GerenciarRelatorio extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        HttpSession session = request.getSession(true);
        
        String acao = request.getParameter("acao");
        String data = request.getParameter("data");
        
        Comanda c = new Comanda();
        
        try{
            ComandaDAO cDAO = new ComandaDAO();
           
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");                      
                java.sql.Date dataFinal = new java.sql.Date(fmt.parse(data).getTime());                  
                c.setData(dataFinal);
                session.setAttribute("dataFinal",dataFinal);
                Double valorDiario = cDAO.buscarValorDiario(dataFinal);
                session.setAttribute("valorFinal",valorDiario);
                System.out.println(session.getAttribute("valorFinal"));
                
                int comandaDiaria = cDAO.comandaDiaria(dataFinal);
                session.setAttribute("comandaDiaria", comandaDiaria);
                
                ItemSaidaDAO iDAO = new ItemSaidaDAO();
                double valorTotalSaida = iDAO.buscarValorDiario(dataFinal);
                session.setAttribute("valorTotalSaida",valorTotalSaida);
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("location.href='relatorios.jsp';");
        /*out.println("document.getElementById('valorFinal').style.display =block;");*/
        out.println("document.getElementById('valorFinal').setAttribute('value',valor);");
         out.println("document.getElementById('idDataCadastro').setAttribute('value',dataFinal);");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
