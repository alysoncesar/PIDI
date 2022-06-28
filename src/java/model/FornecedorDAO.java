package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FornecedorDAO extends DataBaseDAO{
    
    public FornecedorDAO()throws Exception{}
    
        
    public ArrayList<Fornecedor> getLista() throws Exception{
        ArrayList<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Fornecedor f = new Fornecedor();
            f.setIdFornecedor(rs.getInt("idFornecedor"));
            f.setNome(rs.getString("nome"));
            f.setCnpj(rs.getString("cnpj"));
            f.setEndereco(rs.getString("endereco"));
            f.setCep(rs.getString("cep"));
            lista.add(f);
        }
        this.Desconectar();
        return lista;
    }
    public boolean cadastrarFornecedor(Fornecedor f){

        try{
            String sql;
            this.Conectar();
            if(f.getIdFornecedor()== 0){
                sql="INSERT INTO fornecedor (nome,cnpj,endereco,cep) VALUES (?,?,?,?)";
            }else{
                sql="UPDATE fornecedor SET nome=?, cnpj=?,endereco=?, cep=? where idfornecedor = ?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1,f.getNome());
                pstm.setString(2,f.getCnpj());
                pstm.setString(3,f.getEndereco());
                pstm.setString(4,f.getCep());
            if(f.getIdFornecedor()>0){
                pstm.setInt(5,f.getIdFornecedor());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    
    public boolean desativarFornecedor(Fornecedor f){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE fornecedor SET ativo=1 WHERE idFornecedor=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,f.getIdFornecedor());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
