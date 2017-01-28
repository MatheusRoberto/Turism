/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.regra;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import turism.controle.VeiculoDAO;
import turism.modelo.Assento;
import turism.modelo.Veiculo;

/**
 *
 * @author matheus
 */
public class PassageiroTableModel extends AbstractTableModel{

    private static final long serialVersionUID = 1L;

    /* Lista de Depedentes que representam as linhas. */
    private final List<Assento> linhas;

    /* Array de Strings com o nome das colunas. */
    private final String[] colunas = new String[]{
        "Passageiro: ", "Veículo: ", "Assento: "};

    public PassageiroTableModel() {
        this.linhas = new ArrayList<>();
    }

    public PassageiroTableModel(List<Assento> passageiros) {
        linhas = new ArrayList<>(passageiros);
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
        Assento assento = linhas.get(rowIndex);
         VeiculoDAO vdao = new VeiculoDAO();
         Veiculo v = vdao.buscaVeiculo(assento.getAssentoPK().getIdveiculo());

        // Retorna o campo referente a coluna especificada.  
        // Aqui é feito um switch para verificar qual é a coluna  
        // e retornar o campo adequado. As colunas sãoas mesmas  
        // que foram especificadas no array "colunas".  
        switch (columnIndex) {

            // Seguindo o exemplo: "Tipo","Data de Cadastro", "Nome", "Idade"};  
            case 0:
                return assento.getNome();
            case 1:
                return v.getNome();
            case 2:
                return assento.getAssentoPK().getIdassento();
            default:
                // Isto não deveria acontecer...  
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
//modifica na linha e coluna especificada  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Assento assento = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        switch (columnIndex) { // Seta o valor do campo respectivo  
            case 0:
                assento.getNome();;
            case 1:
                assento.getAssentoPK().getIdassento();
            default:
            // Isto não deveria acontecer...               
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //modifica na linha especificada  
    public void setValueAt(Assento aValue, int rowIndex) {
        Assento assento = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        assento.getNome();
        assento.getAssentoPK().getIdassento();

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
    }   
   
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Assento getPassageiro(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /* Adiciona um registro. */
    public void addItem(Assento m) {
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
    public void addListaPassageiro(List<Assento> passageiros) {
        // Pega o tamanho antigo da tabela.  
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.  
        linhas.addAll(passageiros);

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
