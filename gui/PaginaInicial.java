package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class PaginaInicial extends JFrame{

    private JButton btnSubcoleta;
    private JButton btnRelatorio;
    private JButton btnCadastrar;
    
    public PaginaInicial() {
        super("Coleta seletiva de lixo");

        btnSubcoleta = new JButton("Registrar uma subcoleta");
        btnRelatorio = new JButton("Relatório");
        btnCadastrar = new JButton("Cadastrar");

        JPanel jPanel = new JPanel(new BorderLayout(10, 10));
        jPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        jPanel.add(btnSubcoleta, BorderLayout.NORTH);
        jPanel.add(btnRelatorio, BorderLayout.CENTER);
        jPanel.add(btnCadastrar, BorderLayout.SOUTH);
        add(jPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(600, 300);
        pack();
        setVisible(true);
    }
}
