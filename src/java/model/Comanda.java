
package model;

import java.sql.Date;

public class Comanda {
    private int idComanda,quantidadeProduto;
    private Double valor;
    private Date data;
    private Produto produto;
    private Usuario usuario;

    public Comanda() {
    }

    public Comanda(int idComanda, Double valor, Produto produto, Usuario usuario, int quantidadeProduto, Date data) {
        this.idComanda = idComanda;
        this.valor = valor;
        this.produto = produto;
        this.usuario = usuario;
        this.data = data;
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String toString() {
        return "Comanda{" + "produto=" + produto + '}';
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    

    
    
}
