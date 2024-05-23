package SRC.main.Java.com.aluno.sistemaReservas;


public class Assento {
    private int numero;
    private boolean reservado;

    public Assento(int numero) {
        this.numero = numero;
        this.reservado = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isDisponivel() {
        return !reservado;
    }

    public void reservar() {
        if (!reservado) {
            reservado = true;
        }
    }

    public void cancelar() {
        if (reservado) {
            reservado = false;
        }
    }
}
