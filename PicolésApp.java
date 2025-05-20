import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PicolésApp {

    // Definindo os sabores com seus respectivos estoques e preços
    private static final String[] SABORES = {"Maracujá", "Morango", "Goiaba", "Chocolate", "Coco", "Limão"};
    private static final int[] ESTOQUES = {40, 30, 25, 50, 35, 20};
    private static final double[] PRECO = {3.00, 3.50, 2.50, 4.00, 3.00, 2.00};
    private static final JTextField[] inputs = new JTextField[6];

    public static void main(String[] args) {
        // Configuração inicial da janela
        JFrame frame = new JFrame("Sistema de Picolés");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400); // Tamanho da janela
        frame.setLayout(new FlowLayout());

        // Exibindo o título
        JLabel title = new JLabel("Escolha seus Picolés");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(title);

        // Painel para exibir os sabores
        JPanel panelSabores = new JPanel();
        panelSabores.setLayout(new GridLayout(SABORES.length, 1));

        // Criar campos de entrada para cada sabor
        for (int i = 0; i < SABORES.length; i++) {
            JPanel saborPanel = new JPanel();
            saborPanel.setLayout(new FlowLayout());

            JLabel saborLabel = new JLabel(SABORES[i] + " - Estoque: " + ESTOQUES[i] + " - Preço: R$ " + PRECO[i]);
            JTextField inputField = new JTextField(5);
            inputField.setText("0");
            inputs[i] = inputField;
            saborPanel.add(saborLabel);
            saborPanel.add(inputField);

            panelSabores.add(saborPanel);
        }

        frame.add(panelSabores);

        // Botão para calcular o total
        JButton calcularButton = new JButton("Calcular Total");
        frame.add(calcularButton);

        // Exibição do total
        JLabel totalLabel = new JLabel("Total a pagar: R$ 0.00");
        frame.add(totalLabel);

        // Botão de pagamento
        JButton pagarButton = new JButton("Pagar");
        pagarButton.setEnabled(false); // Inicialmente desabilitado
        frame.add(pagarButton);

        // Ação para calcular o total
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                boolean erroEstoque = false;

                // Verificar a quantidade solicitada para cada sabor
                for (int i = 0; i < SABORES.length; i++) {
                    try {
                        int qtd = Integer.parseInt(inputs[i].getText());
                        if (qtd > ESTOQUES[i]) {
                            JOptionPane.showMessageDialog(frame, "Estoque insuficiente para " + SABORES[i]);
                            erroEstoque = true;
                            break;
                        }
                        total += qtd * PRECO[i];
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido para " + SABORES[i]);
                        erroEstoque = true;
                        break;
                    }
                }

                // Exibir o total e habilitar o botão de pagamento
                if (!erroEstoque) {
                    totalLabel.setText("Total a pagar: R$ " + String.format("%.2f", total));
                    pagarButton.setEnabled(true); // Habilita o botão de pagamento
                }
            }
        });

        // Ação para o pagamento
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Compra Finalizada! Obrigado!");
                System.exit(0); // Fecha a aplicação após o pagamento
            }
        });

        // Tornar a janela visível
        frame.setVisible(true);
    }
}
