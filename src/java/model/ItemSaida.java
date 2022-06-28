
package model;
import java.sql.Date;

public class ItemSaida {
    private int idItemSaida;
    private Double valor;
    private Date data;
    private int quantidade;
    private Insumo insumo;
    private Usuario usuario;
    private Produto produto;

    public ItemSaida() {
    }

    public ItemSaida(int idItemSaida, Double valor, Date data, int quantidade, 
            Insumo insumo, Usuario usuario,Produto produto) {
        this.idItemSaida = idItemSaida;
        this.valor = valor;
        this.data = data;
        this.quantidade = quantidade;
        this.insumo = insumo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ItemSaida(Date data) {
        this.data = data;
    }
   

    public int getIdItemSaida() {
        return idItemSaida;
    }

    public void setIdItemSaida(int idItemSaida) {
        this.idItemSaida = idItemSaida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ItemSaida{" + "insumo=" + insumo + '}';
    }
    
    
    
    
    
}
