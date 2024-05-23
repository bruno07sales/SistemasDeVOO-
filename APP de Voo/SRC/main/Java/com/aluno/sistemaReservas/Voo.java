package SRC.main.Java.com.aluno.sistemaReservas;


import java.util.ArrayList;
import java.util.List;

public class Voo {
    private String numeroVoo;
    private String origem;
    private String destino;
    private String companhiaAerea;
    private List<Assento> assentos;

    public Voo(String numeroVoo, String origem, String destino, String companhiaAerea, List<Assento> assentos) {
        this.numeroVoo = numeroVoo;
        this.origem = origem;
        this.destino = destino;
        this.companhiaAerea = companhiaAerea;
        this.assentos = assentos;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public List<Assento> assentosDisponiveis() {
        List<Assento> disponiveis = new ArrayList<>();
        for (Assento assento : assentos) {
            if (assento.isDisponivel()) {
                disponiveis.add(assento);
            }
        }
        return disponiveis;
    }

    public boolean reservarAssento(int numero) {
        for (Assento assento : assentos) {
            if (assento.getNumero() == numero && assento.isDisponivel()) {
                assento.reservar();
                return true;
            }
        }
        return false;
    }

    public boolean cancelarReserva(int numero) {
        for (Assento assento : assentos) {
            if (assento.getNumero() == numero && !assento.isDisponivel()) {
                assento.cancelar();
                return true;
            }
        }
        return false;
    }
}
