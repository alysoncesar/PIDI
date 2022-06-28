
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InsumoDAO extends DataBaseDAO {

    public InsumoDAO() throws Exception {
    }
    public ArrayList<Insumo> getLista() throws Exception{
        ArrayList<Insumo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Insumo WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Insumo i = new Insumo();
            i.setIdInsumo(rs.getInt("idInsumo"));
            i.setNome(rs.getString("nome"));
            i.setPreco(rs.getDouble("preco"));
            i.setQuantidadeAtual(rs.getInt("quantidadeAtual"));
            i.setQuantidadeMinima(rs.getInt("quantidadeMinima"));
            
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("idCategoria"));                    
            i.setCategoria(categoria);
            
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
            i.setFornecedor(fornecedor);
            lista.add(i);
            
        }
        this.Desconectar();
        return lista;
    }
    
    
    
    public ArrayList<Insumo> getListaEntradaSaida() throws Exception{
        ArrayList<Insumo> listaEntradaSaida = new ArrayList<>();
        String sql = "SELECT * FROM Insumo WHERE ativo = 0";
        this.Conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Insumo i = new Insumo();
            i.setIdInsumo(rs.getInt("idInsumo"));
            i.setNome(rs.getString("nome"));
            i.setContadorInsumoEntrada(rs.getInt("ContadorInsumoEntrada"));
            i.setContadorInsumoSaida(rs.getInt("ContadorInsumoSaida"));
            listaEntradaSaida.add(i);
            
        }
        this.Desconectar();
        return listaEntradaSaida;
    }
    
    
     public boolean cadastrarInsumo(Insumo i){
        try{
            String sql;
            this.Conectar();
            if(i.getIdInsumo()== 0){
                sql="INSERT INTO Insumo (nome,preco,quantidadeMinima,quantidadeAtual,"
                        + "idCategoria, idFornecedor) VALUES (?,?,?,?,?,?)";
            }else{               
                sql="UPDATE Insumo SET nome=?,preco=?,quantidadeMinima=?,quantidadeAtual=?,"
                        + "idCategoria=?,idFornecedor=? WHERE idInsumo=?";
            }    
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1,i.getNome());
                pstm.setDouble(2,i.getPreco());
                pstm.setInt(3,i.getQuantidadeMinima());
                pstm.setInt(4,i.getQuantidadeAtual());                   
                pstm.setInt(5, i.getCategoria().getIdCategoria());
                pstm.setInt(6, i.getFornecedor().getIdFornecedor());
            if(i.getIdInsumo()>0){
                pstm.setInt(7,i.getIdInsumo());
            }    
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;            
        }
    }
     
     public boolean desativarInsumo(Insumo i){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE Insumo SET ativo=1 WHERE idInsumo=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,i.getIdInsumo());
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean atualizarQuantidadeEntrada(int codInsumo,int quantidade){
        try{
            String sql;
            this.Conectar();
            sql =" UPDATE Insumo SET quantidadeAtual = quantidadeAtual + ? WHERE IdInsumo=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,quantidade);
            pstm.setInt(2,codInsumo);
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }    
              
        
    public boolean atualizarQuantidadeSaida(int codInsumo,int quantidade){
        try{
            String sql;
            this.Conectar();
            sql =" UPDATE Insumo SET quantidadeAtual = quantidadeAtual - ? WHERE IdInsumo=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,quantidade);
            pstm.setInt(2,codInsumo);
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean checarQuantidade(Integer quantAtual,Integer quantMinima){
        if(quantAtual < quantMinima){
            return true;
        }
        return false;
    }
    
    public Double buscarPreco(int idInsumo){
        Double preco,quantidade,precoDividido;
        Double precoFinal = 0.0;
        try{           
        String sql = "SELECT itementrada.valor,itementrada.quantidade FROM itementrada,insumo "
                + "WHERE itementrada.idInsumo = insumo.idInsumo AND itementrada.ativo = 0 AND insumo.idInsumo = ?;";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idInsumo);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            preco= rs.getDouble("valor");
            quantidade = rs.getDouble("quantidade");
            precoDividido = preco/quantidade;
            System.out.println(precoDividido);
            
            if(precoDividido >= precoFinal){
                precoFinal = precoDividido;
            }
            
        }        
        
        return precoFinal;
        }catch(Exception e){
            System.out.println(e);
            return precoFinal;
        }
    }
    
    public boolean AtualizarPrecoInsumo(int codInsumo,double preco){
        try{
            String sql;
            this.Conectar();
            sql =" UPDATE Insumo SET preco = ? WHERE IdInsumo=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDouble(1,preco);
            pstm.setInt(2,codInsumo);
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Double CalcularValorUnico(int idInsumo){
        Double preco = 0.0;
        try{           
        String sql = "SELECT preco FROM insumo WHERE idInsumo=? ";    
        this.Conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idInsumo);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            preco= rs.getDouble("preco"); 
        }        
        return preco;
        }catch(Exception e){
            System.out.println(e);
            return preco;
        }
    }
    
    public boolean AtualizarSaidaProduto(int insumo){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE insumo SET ContadorInsumoSaida = ContadorInsumoSaida + 1 WHERE idInsumo=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,insumo);
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
     
      public Boolean AtualizarEntradaProduto(int insumo){
        try{
            String sql;
            this.Conectar();
            sql="UPDATE insumo SET ContadorInsumoEntrada = ContadorInsumoEntrada + 1 WHERE idInsumo=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,insumo);
            pstm.execute();
            this.Desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
}
