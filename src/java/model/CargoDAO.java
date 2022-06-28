 package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CargoDAO extends DataBaseDAO{
    
    public CargoDAO() throws Exception{}
    
    public ArrayList<Cargo> getLista() throws Exception{
        ArrayList<Cargo> lista = new ArrayList<>();
        String sql = "SELECT * FROM cargo WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Cargo c = new Cargo();
            c.setIdCargo(rs.getInt("idCargo"));
            c.setNome(rs.getString("nome"));
            lista.add(c);
        }
        this.Desconectar();
        return lista;
    }  
    
    public boolean cadastrarCargo(Cargo c){
        try{
            String sql;
            this.Conectar();
            if(c.getIdCargo()== 0){
                sql="INSERT INTO cargo (nome) VALUES (?)";
            }else{
                sql="UPDATE cargo SET nome=? WHERE idCargo=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1,c.getNome());
            if(c.getIdCargo()>0){
                pstm.setInt(2,c.getIdCargo());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    
    public boolean desativarCargo(Cargo c){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE cargo SET ativo=1 WHERE idCargo=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,c.getIdCargo());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
}
