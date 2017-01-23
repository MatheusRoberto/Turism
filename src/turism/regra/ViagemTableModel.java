/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.regra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import turism.modelo.Viagem;

/**
 *
 * @author matheus
 */
public class ViagemTableModel extends AbstractTableModel{
    
    /*Formato de data*/
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private static final long serialVersionUID = 1L;

    /* Lista de Viagem que representam as linhas. */
    private final List<Viagem> linhas;

    /* Array de Strings com o nome das colunas. */
    private final String[] colunas = new String[]{
        "Cidade Origem: ", "Cidade Destino: ", "Data de ida: ", "Data de volta:"};

    public ViagemTableModel() {
        this.linhas = new ArrayList<>();
    }

    public ViagemTableModel(List<Viagem> viagens) {
        linhas = new ArrayList<>(viagens);
    }

        /* Retorna a quantidade de colunas. */
    @Override
    public int getColumnCount() {
        // EstÃ¡ retornando o tamanho do array "colunas".  
        return colunas.length;
    }

    /* Retorna a quantidade de linhas. */
    @Override
    public int getRowCount() {
        // Retorna o tamanho da lista de Cliente.  
        return linhas.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Retorna o conteÃºdo do Array que possui o nome das colunas  
        return colunas[columnIndex];
    } 
   
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Viagem viagem = linhas.get(rowIndex);

        // Retorna o campo referente a coluna especificada.  
        // Aqui é feito um switch para verificar qual é a coluna  
        // e retornar o campo adequado. As colunas sãoas mesmas  
        // que foram especificadas no array "colunas".  
        switch (columnIndex) {

            // Seguindo o exemplo: "Tipo","Data de Cadastro", "Nome", "Idade"};  
            case 0:
                return viagem.getIdorigem().getNome();
            case 1:
                return viagem.getIddestino().getNome();
            case 2:
                return df.format(viagem.getDataida());
            case 3:
                return df.format(viagem.getDatavolta());
            default:
                // Isto não deveria acontecer...  
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
//modifica na linha e coluna especificada  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Viagem viagem = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        switch (columnIndex) { // Seta o valor do campo respectivo  
            case 0:
                viagem.getIdorigem().getNome();
            case 1:
                viagem.getIddestino().getNome();
            case 2:
                viagem.getDataida();
            case 3:
                viagem.getDatavolta();
            default:
            // Isto não deveria acontecer...               
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //modifica na linha especificada  
    public void setValueAt(Viagem aValue, int rowIndex) {
        Viagem viagem = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        viagem.getIdorigem().getNome();
        viagem.getIddestino().getNome();
        viagem.getDataida();
        viagem.getDatavolta();

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);

    }   
   
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Viagem getViagem(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /* Adiciona um registro. */
    public void addItem(Viagem m) {
        // Adiciona o registro.  
        linhas.add(m);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Remove a linha especificada. */
    public void removeItem(int indiceLinha) {
        linhas.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    /* Adiciona uma lista de Cliente ao final dos registros. */
    public void addListaViagem(List<Viagem> viagens) {
        // Pega o tamanho antigo da tabela.  
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.  
        linhas.addAll(viagens);

        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {
        linhas.clear();

        fireTableDataChanged();
    }

    /* Verifica se este table model esta vazio. */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }
}
