/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.regra;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.GroupLayout;
import javax.swing.table.AbstractTableModel;
import turism.modelo.Parcela;

/**
 *
 * @author matheus
 */
public class ParcelaTableModel extends AbstractTableModel {

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
    public static final DecimalFormat ITENS = new DecimalFormat("###,###,##0");

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private static final long serialVersionUID = 1L;

    /* Lista de Depedentes que representam as linhas. */
    private final List<Parcela> linhas;

    /* Array de Strings com o nome das colunas. */
    private final String[] colunas = new String[]{
        "Valor: ", "Data de Vencimento: ", "Data de Pagamento: ", "Paga: "};

    public ParcelaTableModel() {
        this.linhas = new ArrayList<>();
    }

    public ParcelaTableModel(List<Parcela> parcelas) {
        linhas = new ArrayList<>(parcelas);
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
        Parcela parcela = linhas.get(rowIndex);
        String pg;
        String pagamento;
        if (parcela.getPaga()) {
            pg = "Paga";
            pagamento = df.format(parcela.getDatapagamento());
        } else {
            pg = "Não";
            pagamento = "";
        }

        // Retorna o campo referente a coluna especificada.  
        // Aqui é feito um switch para verificar qual é a coluna  
        // e retornar o campo adequado. As colunas sãoas mesmas  
        // que foram especificadas no array "colunas".  
        switch (columnIndex) {

            // Seguindo o exemplo: "Tipo","Data de Cadastro", "Nome", "Idade"};  
            case 0:
                return DINHEIRO_REAL.format(parcela.getValor());
            case 1:
                return df.format(parcela.getDatavencimento());
            case 2:
                return pagamento;
            case 3:
                return pg;
            default:
                // Isto não deveria acontecer...  
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
//modifica na linha e coluna especificada  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Parcela parcela = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        switch (columnIndex) { // Seta o valor do campo respectivo  
            case 0:
                parcela.getValor();
            case 1:
                parcela.getDatavencimento();
            case 2:
                parcela.getDatapagamento();
            case 3:
                parcela.getPaga();
            default:
            // Isto não deveria acontecer...               
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //modifica na linha especificada  
    public void setValueAt(Parcela aValue, int rowIndex) {
        Parcela parcela = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        parcela.getValor();

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Parcela getParcela(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /* Adiciona um registro. */
    public void addItem(Parcela m) {
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
    public void addListaPArcela(List<Parcela> parcelas) {
        // Pega o tamanho antigo da tabela.  
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.  
        linhas.addAll(parcelas);

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
