
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDAO extends DataBaseDAO{

    public ProdutoDAO() throws Exception {
    }
    
    public ArrayList<Produto> getLista() throws Exception{
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Produto p = new Produto();
            p.setIdProduto(rs.getInt("idProduto"));
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("Descricao"));
            p.setValor(rs.getDouble("valor"));
            lista.add(p);
        }
        this.Desconectar();
        return lista;
    }
    
    public ArrayList<Produto> getListaContadorSaida() throws Exception{
        ArrayList<Produto> listaContadorSaida = new ArrayList<>();
        String sql = "SELECT nome,valor,contadorSaida FROM produto WHERE ativo = 0;";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Produto p = new Produto();
            p.setNome(rs.getString("nome"));
            p.setContadorSaida(rs.getInt("contadorSaida"));
            
            Double valorFinal = rs.getDouble("contadorSaida") * rs.getDouble ("valor");
            p.setValor(valorFinal);
            listaContadorSaida.add(p);
        }
        this.Desconectar();
        return listaContadorSaida;
    }
    
    public boolean AtualizarContadorSaida(Produto p){
        try{
            String sql;
            this.Conectar();
                sql="UPDATE produto SET contadorSaida = contadorSaida + 1 WHERE idProduto=?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1,p.getIdProduto());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    public boolean cadastrarProduto(Produto p){
        try{
            String sql;
            this.Conectar();
            if(p.getIdProduto()== 0){
                sql="INSERT INTO produto (nome,descricao,valor) VALUES (?,?,?)";
            }else{
                sql="UPDATE produto SET nome=?,descricao=?,valor=? WHERE idProduto=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1,p.getNome());
                 pstm.setString(2, p.getDescricao());
                pstm.setDouble(3,p.getValor());               
            if(p.getIdProduto()>0){
                pstm.setInt(4,p.getIdProduto());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
    
    public boolean desativarProduto(Produto p){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE produto SET ativo=1 WHERE idProduto =?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,p.getIdProduto());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }    
    }
}
