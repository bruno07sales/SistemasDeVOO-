package SRC.main.Java.com.aluno.sistemaReservas;


public class Passageiro {
    private String nome;
    private String numeroPassaporte;

    public Passageiro(String nome, String numeroPassaporte) {
        this.nome = nome;
        this.numeroPassaporte = numeroPassaporte;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroPassaporte() {
        return numeroPassaporte;
    }
}

