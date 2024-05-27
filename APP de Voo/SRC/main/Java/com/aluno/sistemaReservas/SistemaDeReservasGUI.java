package SRC.main.Java.com.aluno.sistemaReservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeReservasGUI {
    private SistemaDeReservaDeVoo sistema;

    public SistemaDeReservasGUI(SistemaDeReservaDeVoo sistema) {
        this.sistema = sistema;
    }

    public void criarJanela() {
        JFrame frame = new JFrame("Sistema de Reservas de Voos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelOrigem = new JLabel("Origem:");
        JTextField campoOrigem = new JTextField(20);
        JLabel labelDestino = new JLabel("Destino:");
        JTextField campoDestino = new JTextField(20);
        JLabel labelCompanhia = new JLabel("Companhia Aérea:");
        JTextField campoCompanhia = new JTextField(20);

        JButton botaoBuscar = new JButton("Buscar Voos");
        JTextArea areaResultado = new JTextArea(10, 50);
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);

        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origem = campoOrigem.getText();
                String destino = campoDestino.getText();
                String companhiaAerea = campoCompanhia.getText();
                List<Voo> voos = sistema.buscarVoos(origem, destino, companhiaAerea);
                areaResultado.setText("");
                for (Voo voo : voos) {
                    areaResultado.append("Voo: " + voo.getNumeroVoo() + ", Origem: " + voo.getOrigem() +
                            ", Destino: " + voo.getDestino() + ", Companhia: " + voo.getCompanhiaAerea() + "\n");
                }
            }
        });

        JLabel labelNumeroVoo = new JLabel("Número do Voo:");
        JTextField campoNumeroVoo = new JTextField(10);
        JLabel labelNomePassageiro = new JLabel("Nome do Passageiro:");
        JTextField campoNomePassageiro = new JTextField(20);
        JLabel labelPassaporte = new JLabel("Número do Passaporte:");
        JTextField campoPassaporte = new JTextField(20);
        JLabel labelNumeroAssento = new JLabel("Número do Assento:");
        JTextField campoNumeroAssento = new JTextField(5);

        JButton botaoReservar = new JButton("Fazer Reserva");
        botaoReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroVoo = campoNumeroVoo.getText();
                String nomePassageiro = campoNomePassageiro.getText();
                String numeroPassaporte = campoPassaporte.getText();
                int numeroAssento = Integer.parseInt(campoNumeroAssento.getText());

                Passageiro passageiro = new Passageiro(nomePassageiro, numeroPassaporte);
                Reserva reserva = sistema.fazerReserva(passageiro, numeroVoo, numeroAssento);
                if (reserva != null) {
                    JOptionPane.showMessageDialog(frame, "Reserva feita com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Falha ao fazer reserva. Verifique os dados e tente novamente.");
                }
            }
        });

        JButton botaoCancelar = new JButton("Cancelar Reserva");
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroVoo = campoNumeroVoo.getText();
                String nomePassageiro = campoNomePassageiro.getText();
                String numeroPassaporte = campoPassaporte.getText();
                int numeroAssento = Integer.parseInt(campoNumeroAssento.getText());

                Passageiro passageiro = new Passageiro(nomePassageiro, numeroPassaporte);
                boolean sucesso = sistema.cancelarReserva(passageiro, numeroVoo, numeroAssento);
                if (sucesso) {
                    JOptionPane.showMessageDialog(frame, "Reserva cancelada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Falha ao cancelar reserva. Verifique os dados e tente novamente.");
                }
            }
        });

        JButton botaoListarPassageiros = new JButton("Listar Passageiros");
        botaoListarPassageiros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroVoo = campoNumeroVoo.getText();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("passageiros.txt"))) {
                    for (Reserva reserva : sistema.getReservas()) {
                        if (reserva.getVoo().getNumeroVoo().equalsIgnoreCase(numeroVoo)) {
                            writer.write("Voo: " + reserva.getVoo().getNumeroVoo() +
                                    ", Origem: " + reserva.getVoo().getOrigem() +
                                    ", Destino: " + reserva.getVoo().getDestino() +
                                    ", Companhia: " + reserva.getVoo().getCompanhiaAerea() +
                                    ", Passageiro: " + reserva.getPassageiro().getNome() +
                                    ", Passaporte: " + reserva.getPassageiro().getNumeroPassaporte() +
                                    ", Assento: " + reserva.getNumeroAssento());
                            writer.newLine();
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Passageiros listados e salvos em passageiros.txt com sucesso!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao salvar a lista de passageiros.");
                }
            }
        });

        panel.add(labelOrigem);
        panel.add(campoOrigem);
        panel.add(labelDestino);
        panel.add(campoDestino);
        panel.add(labelCompanhia);
        panel.add(campoCompanhia);
        panel.add(botaoBuscar);
        panel.add(scrollPane);
        panel.add(labelNumeroVoo);
        panel.add(campoNumeroVoo);
        panel.add(labelNomePassageiro);
        panel.add(campoNomePassageiro);
        panel.add(labelPassaporte);
        panel.add(campoPassaporte);
        panel.add(labelNumeroAssento);
        panel.add(campoNumeroAssento);
        panel.add(botaoReservar);
        panel.add(botaoCancelar);
        panel.add(botaoListarPassageiros);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SistemaDeReservaDeVoo sistema = new SistemaDeReservaDeVoo();
        
        // Adicionando alguns voos de exemplo
        List<Assento> assentos = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            assentos.add(new Assento(i));
        }
        sistema.adicionarVoo(new Voo("V123", "São Paulo", "Rio de Janeiro", "LATAM", assentos));
        sistema.adicionarVoo(new Voo("V456", "São Paulo", "Salvador", "GOL", assentos));
        sistema.adicionarVoo(new Voo("V789", "São Paulo", "Fortaleza", "AZUL", assentos));

        SistemaDeReservasGUI gui = new SistemaDeReservasGUI(sistema);
        gui.criarJanela();
    }
}
