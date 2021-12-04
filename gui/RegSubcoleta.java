package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entidades.Material;
import utils.FabricaConexao;

public class RegSubcoleta extends JFrame{

    private JButton registrar;
    private JButton voltar;
    private JTable tableMateriais;
    private ArrayList<Material> listaMateriais;
    private MaterialTableModel modelMateriais;
    
    public RegSubcoleta() {
        super("Registrar uma subcoleta");
        
        registrar = new JButton("Registrar");
        voltar = new JButton("Voltar");
        voltar.addActionListener(new EventoVoltar());
        registrar.addActionListener(new EventoArmazenar());

        listaMateriais = new ArrayList<Material>();
        modelMateriais = new MaterialTableModel(listaMateriais);
        buscarDados();

        tableMateriais = new JTable(modelMateriais);
        tableMateriais.setPreferredScrollableViewportSize(new Dimension(500, 200));
        tableMateriais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        panel.add(new JScrollPane(tableMateriais), BorderLayout.NORTH);
        panel.add(voltar, BorderLayout.LINE_START);
        panel.add(registrar, BorderLayout.LINE_END);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // impede o redimensionamento da janela
        setLocation(600,300);
        pack(); // define o tamanho da janela (menor possível para caber o conteúdo)
        setVisible(true);
    }

    public void buscarDados() {
        listaMateriais.clear(); // limpa/zera todos os dados do ArrayList

        // buca as informações de cada aluno no Banco de dados
        try {
            // obter uma conexão com o banco de dados
            Connection conexao = FabricaConexao.getInstance();
            // prepara a consulta sql
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM material;");
            ResultSet rs = ps.executeQuery();

            // percorrer a lista de resultados (ResultSet)
            while (rs.next()) {
                // cria um novo objeto material
                Material material = new Material();
                // captura o JSon como texto puro
                String jsonMaterialString = rs.getString("material");
                // cria o conversor(parser) Json
                JSONParser parser = new JSONParser();
                // converte(parse) o campo material para um objeto json
                JSONObject jsonMaterial = (JSONObject) parser.parse(jsonMaterialString);

                // obtém cada um dos valores do JSON
                String tipo = (String) jsonMaterial.get("tipo");
                String bufferString = jsonMaterial.get("peso").toString();
                float peso = Float.parseFloat(bufferString); 
                bufferString = jsonMaterial.get("cubagem").toString();
                float cubagem = Float.parseFloat(bufferString);
                int idMaterial = Integer.parseInt(rs.getString("idmaterial"));

                // coloca os valores obtidos dentro do objeto material
                material.setTipo(tipo);
                material.setPeso(peso);
                material.setCubagem(cubagem);
                material.setIdMaterial(idMaterial);

                // coloca cada novo material dentro da lista
                listaMateriais.add(material);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL", "Listagem dos dados", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro no parser do JSON", "Listagem dos dados",
            JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro no cast do json", "Listagem dos dados",
            JOptionPane.ERROR_MESSAGE);
        }

    }

    public void removerMaterial(int idMaterial){
        Connection conexao = FabricaConexao.getInstance(); // obtém a instancia do banco de dados
        try{
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM material WHERE idmaterial = "+ idMaterial +";");
            ps.execute(); // executar o sql no banco de dados
            JOptionPane.showMessageDialog(this, "Material retirado com sucesso!", "Retirada do Banco",JOptionPane.INFORMATION_MESSAGE);
            dispose(); // fechar esta janela de Cadastro
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao retirar material do banco!", "Retirada do Banco",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class EventoVoltar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private class EventoArmazenar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            if (tableMateriais.getSelectedRow() != -1){
                
                int linhaSelecionada = tableMateriais.getSelectedRow();

                Material material = modelMateriais.getMaterial(linhaSelecionada);

                removerMaterial(material.getIdMaterial());
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar uma coleta!","Exibição", JOptionPane.WARNING_MESSAGE);
            }
            
        }

    }
}
