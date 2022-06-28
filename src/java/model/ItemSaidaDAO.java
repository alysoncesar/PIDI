package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class ItemSaidaDAO extends DataBaseDAO{

    public ItemSaidaDAO() throws Exception {
    }
    
    public ArrayList<ItemSaida> getLista() throws Exception{
        ArrayList<ItemSaida> lista = new ArrayList<>();
        String sql = "SELECT * FROM itemSaida WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            ItemSaida is = new ItemSaida();
            is.setIdItemSaida(rs.getInt("idItemSaida"));
            is.setData(rs.getDate("data"));
            is.setValor(rs.getDouble("valor"));
            is.setQuantidade(rs.getInt("quantidade"));
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            is.setUsuario(usuario);
            
            Insumo insumo = new Insumo();
            insumo.setIdInsumo(rs.getInt("idInsumo"));
            is.setInsumo(insumo);
            
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("idProduto"));
            is.setProduto(produto);
            
            lista.add(is);
        } 
        this.Desconectar();
        return lista;
        
    }
    public ArrayList<ItemSaida> getListaValorTotalSaida() throws Exception{
        ArrayList<ItemSaida> listaValorTotalSaida = new ArrayList<>();
        String sql = "SELECT SUM(valor) FROM itemSaida WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            ItemSaida is = new ItemSaida();
            is.setValor(rs.getDouble("SUM(valor)"));
            listaValorTotalSaida.add(is);
        } 
        this.Desconectar();
        return listaValorTotalSaida;
        
    }
    public boolean cadastrarItemSaida(ItemSaida is){
        try{
            String sql;
            this.Conectar();
            if(is.getIdItemSaida()== 0){
                sql="INSERT INTO itemSaida (data, valor, quantidade,idInsumo, idUsuario,idProduto)"
                        + " VALUES (?,?,?,?,?,?)";
            }else{
                sql="UPDATE itemSaida SET data=?,valor=?,quantidade=?,idInsumo=?,idUsuario=?,idProduto =?"
                        + " WHERE idItemSaida=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setDate(1,is.getData());
                pstm.setDouble(2,is.getValor());
                pstm.setDouble(3,is.getQuantidade());
                pstm.setInt(4, is.getInsumo().getIdInsumo());
                pstm.setInt(5, is.getUsuario().getIdUsuario());
                pstm.setInt(6, is.getProduto().getIdProduto());
            if(is.getIdItemSaida()>0){
                pstm.setInt(7,is.getIdItemSaida());
            }    
            pstm.execute();
            
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
        
    public boolean desativarItemSaida(ItemSaida is){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE itemSaida SET ativo=1 WHERE idItemSaida=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,is.getIdItemSaida());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Double buscarValorDiario(Date data){
        Double preco = 0.0;
        try{           
        String sql = "SELECT SUM(valor) FROM itemSaida WHERE data = ? AND ativo = 0";    
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
}
