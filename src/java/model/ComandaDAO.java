
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ComandaDAO extends DataBaseDAO{

    public ComandaDAO() throws Exception {
    }
    
    public ArrayList<Comanda> getLista() throws Exception{
        ArrayList<Comanda> lista = new ArrayList<>();
        String sql = "SELECT * FROM comanda WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Comanda c = new Comanda();         
            c.setIdComanda(rs.getInt("numeroComanda"));
            c.setValor(rs.getDouble("valor"));
            c.setQuantidadeProduto(rs.getInt("quantidadeProduto"));
            
            Produto p = new Produto();
            p.setIdProduto(rs.getInt("idProduto"));
            c.setProduto(p);
            
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            c.setUsuario(u);
            
            c.setData(rs.getDate("data"));
            lista.add(c);
        }        
        this.Desconectar();
        return lista;
    }
     public ArrayList<Comanda> getListaValorTotalComandas() throws Exception{
        ArrayList<Comanda> listaValorTotal = new ArrayList<>();
        String sql = "SELECT SUM(valor) FROM comanda WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Comanda c = new Comanda();    
            c.setValor(rs.getDouble("SUM(valor)"));
            listaValorTotal.add(c);
        }        
        this.Desconectar();
        return listaValorTotal;
    }
             
    
    public boolean cadastrarComanda(Comanda c){
        try{
            String sql;
            this.Conectar();
            if(c.getIdComanda()== 0){
                sql="INSERT INTO comanda (valor,idProduto,idUsuario,quantidadeProduto,data) VALUES (?,?,?,?,?)";
            }else{
                sql="UPDATE comanda SET valor =? , idProduto = ?, idUsuario = ?, quantidadeProduto = ?, data = ? WHERE numeroComanda = ?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setDouble(1,(c.getValor()));
                pstm.setInt(2,(c.getProduto().getIdProduto()));
                pstm.setInt(3,(c.getUsuario().getIdUsuario()));
                pstm.setInt(4,(c.getQuantidadeProduto()));
                pstm.setDate(5,(c.getData()));
                if(c.getIdComanda()>0){
                pstm.setInt(6,c.getIdComanda());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    /*"SELECT SUM(quantidadeProduto) AS QTDE, idProduto FROM comanda WHERE data = '2022-06-28' GROUP BY idProduto;"*/
   
    public Double buscarPreco(int idInsumo){
        Double preco = 0.0;
        try{           
        String sql = "SELECT valor FROM produto WHERE idProduto = ? AND ativo = 0";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idInsumo);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            preco= rs.getDouble("valor");
        }        
        
        return preco;
        }catch(Exception e){
            System.out.println(e);
            return preco;
        }
    }
    
    public int comandaDiaria(Date data){
        int quantidade = 0;
        try{           
        String sql = "SELECT COUNT(*) FROM comanda WHERE data = ? AND ativo = 0;";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDate(1,data);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            quantidade = rs.getInt("COUNT(*)");
        }        
        
        return quantidade;
        }catch(Exception e){
            System.out.println(e);
            return quantidade;
        }
    }
    
    public int contadorDiario(Date data){
        int quantidade = 0;
        try{           
        String sql = "SELECT COUNT(*) FROM comanda WHERE data = ? AND ativo = 0;";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDate(1,data);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            quantidade = rs.getInt("COUNT(*)");
        }        
        
        return quantidade;
        }catch(Exception e){
            System.out.println(e);
            return quantidade;
        }
    }
    
    public Double buscarValorDiario(Date data){
        Double preco = 0.0;
        try{           
        String sql = "SELECT SUM(valor) FROM comanda WHERE data = ? AND ativo = 0";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setDate(1,data);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            preco= rs.getDouble("SUM(valor)");
        }        
        
        return preco;
        }catch(Exception e){
            System.out.println(e);
            return preco;
        }
    }
    
    public boolean desativarComanda(Comanda c){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE comanda SET ativo=1 WHERE numeroComanda=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,c.getIdComanda());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
