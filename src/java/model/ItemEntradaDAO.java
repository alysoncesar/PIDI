package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;


public class ItemEntradaDAO extends DataBaseDAO{
    
    public ItemEntradaDAO()throws Exception{}
    public ArrayList<ItemEntrada> getLista() throws Exception{
        ArrayList<ItemEntrada> lista = new ArrayList<>();
        /*"SELECT * FROM itemEntrada WHERE ativo = 0";*/
        String sql ="SELECT *\n" +
                    "FROM itementrada,insumo \n" +
                    "WHERE itementrada.idInsumo = insumo.idInsumo AND itementrada.ativo = 0;";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            ItemEntrada ie = new ItemEntrada();
            ie.setIdItemEntrada(rs.getInt("idItemEntrada"));
            ie.setData(rs.getDate("data"));
            ie.setValor(rs.getDouble("valor"));
            ie.setQuantidade(rs.getInt("quantidade"));
            
            ie.setFornecedor(rs.getInt("idFornecedor"));
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            ie.setUsuario(usuario);
            
            Insumo insumo = new Insumo();
            insumo.setIdInsumo(rs.getInt("idInsumo"));
            ie.setInsumo(insumo);
            
            lista.add(ie);
        } 
        this.Desconectar();
        return lista;
        
    }
    
    public boolean cadastrarItemEntrada(ItemEntrada ie){
        try{
            String sql;
            this.Conectar();
            if(ie.getIdItemEntrada()== 0){
                sql="INSERT INTO itementrada (data, valor, quantidade,idInsumo, idUsuario)"
                        + " VALUES (?,?,?,?,?)";
            }else{
                sql="UPDATE itementrada SET data=?,valor=?,quantidade=?,idInsumo=?,idUsuario=? WHERE idItemEntrada=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                
                pstm.setDate(1,ie.getData());
                pstm.setDouble(2,ie.getValor());
                pstm.setDouble(3,ie.getQuantidade());
                pstm.setInt(4, ie.getInsumo().getIdInsumo());
                pstm.setInt(5, ie.getUsuario().getIdUsuario());
            if(ie.getIdItemEntrada()>0){
                pstm.setInt(6,ie.getIdItemEntrada());
            }   
            pstm.execute();
                        
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    
    public boolean desativarItemEntrada(ItemEntrada ie){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE itemEntrada SET ativo=1 WHERE idItemEntrada=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,ie.getIdItemEntrada());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
}

