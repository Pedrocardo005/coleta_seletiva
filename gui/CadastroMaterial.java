package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import utils.FabricaConexao;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        armazenar.addActionListener(new EventoSalvar());
        voltar = new JButton("Voltar");
        voltar.addActionListener(new EventoVoltar());

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

    public void salvarMaterialNoBanco(){
        JSONObject objetoJson = new JSONObject();

        objetoJson.put("tipo", caixaTipo.getSelectedItem());
        objetoJson.put("peso", numPeso.getValue());
        objetoJson.put("cubagem", numCubagem.getValue());

        Connection conexao = FabricaConexao.getInstance();

        try{
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO material(material) VALUES('" +objetoJson.toJSONString()+ "')");
            ps.execute(); // executar o sql no banco de dados
            JOptionPane.showMessageDialog(this, "Material cadastrado com sucesso!", "Inserção no Banco",JOptionPane.INFORMATION_MESSAGE);
            dispose(); // fechar esta janela de Cadastro
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar material no banco!", "Inserção no Banco",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class EventoSalvar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            salvarMaterialNoBanco();
        }

    }

    private class EventoVoltar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            
        }

    }
}
