
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class CategoriaDAO extends DataBaseDAO{
    
    public CategoriaDAO()throws Exception{}
    
    public ArrayList<Categoria> getLista() throws Exception{
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Categoria c = new Categoria();
            c.setIdCategoria(rs.getInt("idCategoria"));
            c.setNome(rs.getString("nome"));
            lista.add(c);
        }
        this.Desconectar();
        return lista;
    }
    
    public boolean cadastrarCategoria(Categoria c){
        try{
            String sql;
            this.Conectar();
            if(c.getIdCategoria()== 0){
                sql="INSERT INTO categoria (nome) VALUES (?)";
            }else{
                sql="UPDATE categoria SET nome=? WHERE idCategoria=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1,c.getNome());
            if(c.getIdCategoria()>0){
                pstm.setInt(2,c.getIdCategoria());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    
    public boolean desativarCategoria(Categoria c){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE categoria SET ativo=1 WHERE idCategoria=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,c.getIdCategoria());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
