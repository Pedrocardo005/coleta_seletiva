package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entidades.Material;

public class MaterialTableModel extends AbstractTableModel{

    String[] colunas = {"Material", "Peso (Kg)", "Cubagem m³"};

    private ArrayList<Material> listaMateriais;

    public MaterialTableModel(ArrayList<Material> a) {
        listaMateriais = a;
    }

    public Material getMaterial(int linha) {
        return listaMateriais.get(linha);
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return colunas.length;
    }

    @Override
    public int getRowCount() {

        return listaMateriais.size();
    }

    @Override
    public String getColumnName(int indice) {
        return colunas[indice];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Material material = listaMateriais.get(rowIndex);

        if (columnIndex == 0){
            return material.getTipo();
        }else if(columnIndex == 1){
            return material.getPeso();
        }else if(columnIndex == 2){
            return material.getCubagem();
        }
        
        return null;
    }
}
