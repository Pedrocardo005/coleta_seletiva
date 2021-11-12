package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class CadastroMaterial extends JFrame{
    private JLabel labelTipo;
    private JComboBox caixaTipo;
    private JLabel labelPeso;
    private JSpinner numPeso;
    private JLabel labelCubagem;
    private JSpinner numCubagem;
    private JButton armazenar;
    private JButton voltar;
    
    public CadastroMaterial(){
        super("Cadastrar um material");

        labelTipo = new JLabel("Tipo: ");
        String [] tipos = {"Vidro", "Plástico", "Papel",
                            "Papelão", "Metal", "Madeira", "Orgânicos"};
        
        caixaTipo = new JComboBox<String>(tipos);

        labelPeso = new JLabel("Peso: ");
        numPeso = new JSpinner();
        labelCubagem = new JLabel("Cubagem: ");
        numCubagem = new JSpinner();

        armazenar = new JButton("Armazenar");
        voltar = new JButton("Voltar");

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx=1;
        constraints.weighty=1;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 5, 5, 10);

        constraints.gridx=0; // coluna 0
        constraints.gridy=0; // linha 0
        panel.add(labelTipo,constraints);
        constraints.gridx=1; // coluna 1
        constraints.gridy=0; // linha 0
        panel.add(caixaTipo, constraints);

        constraints.gridx=0; // coluna 0
        constraints.gridy=1; // linha 1
        panel.add(labelPeso, constraints);
        constraints.gridx=1; // coluna 1
        constraints.gridy=1; // linha 1
        panel.add(numPeso, constraints);

        constraints.gridx=0; // coluna 0
        constraints.gridy=2; // linha 2
        panel.add(labelCubagem, constraints);
        constraints.gridx=1; // coluna 1
        constraints.gridy=2; // linha 2
        panel.add(numCubagem, constraints);

        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=1;
        panel.add(voltar, constraints);

        constraints.gridx=1; // coluna 0
        constraints.gridy=4; // linha 4
        constraints.gridwidth=1; // ocupa 2 colunas
        panel.add(armazenar, constraints);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false); // impede o redimensionamento da janela
        setLocation(600,300);
        pack(); // define o tamanho da janela (menor possível para caber o conteúdo)
        setVisible(true);
    }
}
