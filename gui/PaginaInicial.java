package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class PaginaInicial extends JFrame{

    private JButton btnSubcoleta;
    private JButton btnRelatorio;
    private JButton btnCadastrar;
    
    public PaginaInicial() {
        super("Coleta seletiva de lixo");

        btnSubcoleta = new JButton("Registrar uma subcoleta");
        btnSubcoleta.addActionListener(new EventoRegSubcoleta());
        btnRelatorio = new JButton("Relatório");
        btnRelatorio.addActionListener(new EventoRelatorio());
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new EventoCadastroMaterial());

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

    public class EventoRegSubcoleta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            RegSubcoleta JanelaRegistrar = new RegSubcoleta();
        }

    }

    public class EventoRelatorio implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Relatorio relatorio = new Relatorio();
            
        }
        
    }

    public class EventoCadastroMaterial implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            CadastroMaterial cadastroMaterial = new CadastroMaterial();    
        }
    }
}