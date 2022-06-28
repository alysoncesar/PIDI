

package model;


public class Insumo {
    private int idInsumo,ContadorInsumoEntrada,ContadorInsumoSaida;
    private String nome;
    private Double preco;
    private Integer quantidadeMinima,quantidadeAtual;
    private Categoria categoria;
    private Fornecedor fornecedor;

    public Insumo() {
    }

    public Insumo(String nome) {
        this.nome = nome;
    }

    public Insumo(int idInsumo, String nome, Double preco, Integer quantidadeMinima, Integer quantidadeAtual, Categoria categoria, Fornecedor fornecedor) {
        this.idInsumo = idInsumo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeAtual = quantidadeAtual;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }
    
   

    public int getContadorInsumoEntrada() {
        return ContadorInsumoEntrada;
    }

    public void setContadorInsumoEntrada(int ContadorInsumoEntrada) {
        this.ContadorInsumoEntrada = ContadorInsumoEntrada;
    }

    public int getContadorInsumoSaida() {
        return ContadorInsumoSaida;
    }

    public void setContadorInsumoSaida(int ContadorInsumoSaida) {
        this.ContadorInsumoSaida = ContadorInsumoSaida;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        if(preco != null){
            return preco;
        }
        return 0.0;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
     
    public String toString() {
        return "Insumo{" + "nome=" + nome + '}';
    }
    
}
