
package model;


public class Cargo {
   private int idCargo;
   private String nome;

   public Cargo(){}
   
    public Cargo(int idCargo, String nome) {
        this.idCargo = idCargo;
        this.nome = nome;
    }
   
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String toString() {
        return "Cargo{" + "nome=" + nome + '}';
    }
   
}
