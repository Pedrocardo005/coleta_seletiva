package gui;

import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Material;
import utils.FabricaConexao;

public class Relatorio extends JFrame{

    private JButton btnTelaInicial;
    private JTable tableMateriais;
    private ArrayList<Material> listaMateriais;
    private MaterialTableModel modelMateriais;
    // Achar um elemento que contenha uma informação estática.

    public Relatorio() {
        super("Relatório");

        btnTelaInicial = new JButton("Tela inicial");
        btnTelaInicial.addActionListener(new EventoTelaInicial());

        listaMateriais = new ArrayList<Material>();
        modelMateriais = new MaterialTableModel(listaMateriais);
        buscarDados();

        tableMateriais = new JTable(modelMateriais);
        tableMateriais.setPreferredScrollableViewportSize(new Dimension(500, 200));
        tableMateriais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(tableMateriais), BorderLayout.NORTH);
        panel.add(btnTelaInicial, BorderLayout.LINE_START);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(600, 300);
        pack();
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

                // coloca os valores obtidos dentro do objeto material
                material.setTipo(tipo);
                material.setPeso(peso);
                material.setCubagem(cubagem);

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

    private class EventoTelaInicial implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
