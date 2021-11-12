package gui;

import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.*;

import entidades.Material;

public class Relatorio extends JFrame{

    private JButton btnGerarPdf;
    private JButton btnTelaInicial;
    private JTable tableMateriais;
    private ArrayList<Material> listaMateriais;
    private MaterialTableModel modelMateriais;
    // Achar um elemento que contenha uma informação estática.

    public Relatorio() {
        super("Relatório");

        btnGerarPdf = new JButton("Gerar PDF");
        btnTelaInicial = new JButton("Tela inicial");

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
        panel.add(btnGerarPdf, BorderLayout.LINE_END);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(600, 300);
        pack();
        setVisible(true);
    }

    public void buscarDados(){
        listaMateriais.clear();
        String [] tipos = {"Vidro", "Plástico", "Papel",
                            "Papelão", "Metal", "Madeira", "Orgânicos"};

        for (int x = 0; x < tipos.length; x++) {
            Material material = new Material();

            String tipo = tipos[x];
            float peso = x + 1;
            float cubagem = x + 3;

            material.setTipo(tipo);
            material.setPeso(peso);
            material.setCubagem(cubagem);

            listaMateriais.add(material);
        }
    }
}
