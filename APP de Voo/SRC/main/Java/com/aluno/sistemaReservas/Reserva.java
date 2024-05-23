package SRC.main.Java.com.aluno.sistemaReservas;

public class Reserva {
    private Passageiro passageiro;
    private Voo voo;
    private int numeroAssento;

    public Reserva(Passageiro passageiro, Voo voo, int numeroAssento) {
        this.passageiro = passageiro;
        this.voo = voo;
        this.numeroAssento = numeroAssento;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public int getNumeroAssento() {
        return numeroAssento;
    }
}

