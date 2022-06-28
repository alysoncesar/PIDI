package model;

public class Usuario {
    private int idUsuario,contadorComanda;
    private Cargo cargo;
    private String nome,endereco,rg,cpf,senha;

    public Usuario() {
    }

    public Usuario(int idUsuario, Cargo cargo, String nome, String endereco, String rg, String cpf, String senha, int contadorComanda) {
        this.idUsuario = idUsuario;
        this.cargo = cargo;
        this.nome = nome;
        this.endereco = endereco;
        this.rg = rg;
        this.cpf = cpf;
        this.senha = senha;
        this.contadorComanda = contadorComanda;
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getContadorComanda() {
        return contadorComanda;
    }

    public void setContadorComanda(int contadorComanda) {
        this.contadorComanda = contadorComanda;
    }

    public String toString() {
        return "Usuario{" + "nome=" + nome + '}';
    }

    
    
    
    
}
