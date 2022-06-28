
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO extends DataBaseDAO{

    public UsuarioDAO() throws Exception{
    }
    
    public ArrayList<Usuario> getLista() throws Exception{
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setEndereco(rs.getString("endereco"));
            u.setRg(rs.getString("rg"));
            u.setCpf(rs.getString("cpf"));            
            
            Cargo c = new Cargo();
            c.setIdCargo(rs.getInt("idCargo"));           
            
            u.setCargo(c);
            
            u.setSenha(rs.getString("senha"));                   
            lista.add(u);
        }        
        this.Desconectar();
        return lista;
    }
    
    public ArrayList<Usuario> getListaUsuarioComanda() throws Exception{
        ArrayList<Usuario> listaUsuarioComanda = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Usuario u = new Usuario();
            u.setNome(rs.getString("nome"));
            u.setContadorComanda(rs.getInt("ContadorComanda"));
            listaUsuarioComanda.add(u);
        }        
        this.Desconectar();
        return listaUsuarioComanda;
    }
    
    public boolean cadastrarUsuario(Usuario u){
        try{
            String sql;
            this.Conectar();
            if(u.getIdUsuario()== 0){
                sql="INSERT INTO usuario (nome,rg,cpf,idCargo,senha,endereco) VALUES (?,?,?,?,?,?)";
            }else{
                sql="UPDATE usuario SET nome=? ,rg=?,cpf=?,idCargo=?,senha=? ,endereco=? WHERE idUsuario=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1,u.getNome());                
                pstm.setString(2,(u.getRg()));
                pstm.setString(3,(u.getCpf()));
                pstm.setInt(4,u.getCargo().getIdCargo());
                pstm.setString(5, u.getSenha());
                pstm.setString(6,u.getEndereco());
                if(u.getIdUsuario()>0){
                pstm.setInt(7,u.getIdUsuario());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    
    public boolean desativarUsuario(Usuario u){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE usuario SET ativo=1 WHERE idUsuario=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,u.getIdUsuario());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean AtualizarComandaAberta(Usuario u){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE usuario SET ContadorComanda = ContadorComanda + 1 WHERE idUsuario=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,u.getIdUsuario());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean Logar(String cpf,String senha){
        boolean check = false;
        try{
            String sql;            
            this.Conectar();
            sql="SELECT nome FROM usuario WHERE cpf=? and senha=?";
            System.out.println(sql);
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cpf);
            pstm.setString(2, senha);
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                check = true;
            }
            
            return check;           
        }catch(Exception e){
            System.out.println(e);
            return check;
        }
    }
    
    public String buscarNome(String cpf){
        String nome="";
        try{           
        String sql = "SELECT nome FROM usuario WHERE cpf=? ";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,cpf);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            nome= rs.getString("nome"); 
        }        
        return nome;
        }catch(Exception e){
            System.out.println(e);
            return nome;
        }
    }
    
    
}
