package SRC.main.Java.com.aluno.sistemaReservas;

    import java.util.ArrayList;
    import java.util.List;
    
    public class SistemaDeReservaDeVoo {
        private List<Voo> voos;
        private List<Reserva> reservas;
    
        public SistemaDeReservaDeVoo() {
            voos = new ArrayList<>();
            reservas = new ArrayList<>();
        }
    
        public void adicionarVoo(Voo voo) {
            voos.add(voo);
        }
    
        public List<Voo> buscarVoos(String origem, String destino, String companhiaAerea) {
            List<Voo> resultados = new ArrayList<>();
            for (Voo voo : voos) {
                if (voo.getOrigem().equalsIgnoreCase(origem) &&
                    voo.getDestino().equalsIgnoreCase(destino) &&
                    voo.getCompanhiaAerea().equalsIgnoreCase(companhiaAerea)) {
                    resultados.add(voo);
                }
            }
            return resultados;
        }
    
        public Reserva fazerReserva(Passageiro passageiro, String numeroVoo, int numeroAssento) {
            for (Voo voo : voos) {
                if (voo.getNumeroVoo().equalsIgnoreCase(numeroVoo)) {
                    if (voo.reservarAssento(numeroAssento)) {
                        Reserva reserva = new Reserva(passageiro, voo, numeroAssento);
                        reservas.add(reserva);
                        return reserva;
                    }
                }
            }
            return null;
        }
    
        public boolean cancelarReserva(Passageiro passageiro, String numeroVoo, int numeroAssento) {
            for (Voo voo : voos) {
                if (voo.getNumeroVoo().equalsIgnoreCase(numeroVoo)) {
                    if (voo.cancelarReserva(numeroAssento)) {
                        Reserva reservaParaCancelar = null;
                        for (Reserva reserva : reservas) {
                            if (reserva.getPassageiro().equals(passageiro) &&
                                reserva.getVoo().equals(voo) &&
                                reserva.getNumeroAssento() == numeroAssento) {
                                reservaParaCancelar = reserva;
                                break;
                            }
                        }
                        if (reservaParaCancelar != null) {
                            reservas.remove(reservaParaCancelar);
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
    