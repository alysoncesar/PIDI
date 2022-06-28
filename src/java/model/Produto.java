
package model;

public class Produto {
    private int idProduto,contadorSaida;
    private String nome,descricao;
    private Double valor;

    public Produto() {
    }

    public Produto(String nome) {
        this.nome = nome;
    }

    public Produto(int idProduto, String nome, String descricao, Double valor, int contadorSaida) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.contadorSaida = contadorSaida;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String toString() {
        return "Produto{" + "nome=" + nome + '}';
    }

    public int getContadorSaida() {
        return contadorSaida;
    }

    public void setContadorSaida(int contadorSaida) {
        this.contadorSaida = contadorSaida;
    }
    
    
}
