/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.regra;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import turism.modelo.Veiculoscontratados;

/**
 *
 * @author matheus
 */
public class VeiculosContratadosTableModel extends AbstractTableModel {

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);

    private static final long serialVersionUID = 1L;

    /* Lista de Estoque que representam as linhas. */
    private final List<Veiculoscontratados> linhas;

    /* Array de Strings com o nome das colunas. */
    private final String[] colunas = new String[]{
        "Empresa", "Veículo", "Valor"};

    /* Cria um Veiculos Contratados vazio. */
    public VeiculosContratadosTableModel() {
        linhas = new ArrayList<>();
    }

    /* Cria um ClienteTableModel carregado com
     * a lista de Cliente especificada. */
    public VeiculosContratadosTableModel(List<Veiculoscontratados> veiculoscontratados) {
        linhas = new ArrayList<>(veiculoscontratados);
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

    ;  
   
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    ;  
   
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculoscontratados veiculos = linhas.get(rowIndex);

        // Retorna o campo referente a coluna especificada.  
        // Aqui é feito um switch para verificar qual é a coluna  
        // e retornar o campo adequado. As colunas sãoas mesmas  
        // que foram especificadas no array "colunas".  
        switch (columnIndex) {

            // Seguindo o exemplo: "Tipo","Data de Cadastro", "Nome", "Idade"};  
            case 0:
                return veiculos.getVeiculo().getIdempresa().getNome();
            case 1:
                return veiculos.getVeiculo().getNome();
            case 2:
                return DINHEIRO_REAL.format(veiculos.getValor());
            default:
                // Isto não deveria acontecer...  
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
//modifica na linha e coluna especificada  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Veiculoscontratados veiculos = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        switch (columnIndex) { // Seta o valor do campo respectivo  
            case 0:
                //veiculos.getVeiculo().getIdempresa().getNome();
            case 1:
                //veiculos.getVeiculo().getNome();
            case 2:
                veiculos.getValor();
            default:
            // Isto não deveria acontecer...               
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //modifica na linha especificada  
    public void setValueAt(Veiculoscontratados aValue, int rowIndex) {
        Veiculoscontratados veiculos = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        //veiculos.getVeiculo().getIdempresa().getNome();
        //veiculos.getVeiculo().getNome();
        veiculos.getValor();

        //fireTableCellUpdated(rowIndex, 0);
        //fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        //fireTableCellUpdated(rowIndex, 3);

    }   
   
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Veiculoscontratados getVeiculosContratados(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /* Adiciona um registro. */
    public void addItem(Veiculoscontratados m) {
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
    public void addListaVeiculosContratados(List<Veiculoscontratados> veiculocontratados) {
        // Pega o tamanho antigo da tabela.  
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.  
        linhas.addAll(veiculocontratados);

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
