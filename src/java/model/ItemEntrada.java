
package model;

import java.sql.Date;

public class ItemEntrada {
    private int idItemEntrada;
    private Double valor;
    private Date data;
    private int quantidade;
    private int fornecedor;
    private Insumo insumo;
    private Usuario usuario;

    public ItemEntrada() {
    }

    public ItemEntrada(int idItemEntrada, Double valor, Date data, int quantidade, Insumo insumo, Usuario usuario, int fornecedor) {
        this.idItemEntrada = idItemEntrada;
        this.valor = valor;
        this.data = data;
        this.quantidade = quantidade;
        this.insumo = insumo;
        this.usuario = usuario;
        this.fornecedor = fornecedor;
    }

    public ItemEntrada(Date data) {
        this.data = data;
    }
   

    public int getIdItemEntrada() {
        return idItemEntrada;
    }

    public void setIdItemEntrada(int idItemEntrada) {
        this.idItemEntrada = idItemEntrada;
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

    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "ItemEntrada{" + "produto=" + insumo + '}';
    }

    
    
    
    
    
}
