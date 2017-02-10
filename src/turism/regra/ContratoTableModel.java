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
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import turism.controle.AssentoDAO;
import turism.controle.HoteisContratadosDAO;
import turism.controle.HotelDAO;
import turism.controle.ParcelaDAO;
import turism.controle.VeiculoDAO;
import turism.controle.VeiculosContratadosDAO;
import turism.controle.ViagemDAO;
import turism.modelo.Assento;
import turism.modelo.AssentoPK;
import turism.modelo.Contrato;
import turism.modelo.Hoteiscontratados;
import turism.modelo.HoteiscontratadosPK;
import turism.modelo.Hotel;
import turism.modelo.Parcela;
import turism.modelo.Veiculo;
import turism.modelo.Veiculoscontratados;

/**
 *
 * @author matheus
 */
public class ContratoTableModel extends AbstractTableModel {

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
    public static final DecimalFormat ITENS = new DecimalFormat("###,###,##0");
    private static final long serialVersionUID = 1L;

    /* Lista de Depedentes que representam as linhas. */
    private final List<Contrato> linhas;

    /* Array de Strings com o nome das colunas. */
    private final String[] colunas = new String[]{
        "Cliente: ", "Pago: ", "Valor Pago: ", "Valor Total: "};

    public ContratoTableModel() {
        this.linhas = new ArrayList<>();
    }

    public ContratoTableModel(List<Contrato> passageiros) {
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
        Contrato contrato = linhas.get(rowIndex);

        // Retorna o campo referente a coluna especificada.  
        // Aqui é feito um switch para verificar qual é a coluna  
        // e retornar o campo adequado. As colunas sãoas mesmas  
        // que foram especificadas no array "colunas".  
        switch (columnIndex) {

            // Seguindo o exemplo: "Tipo","Data de Cadastro", "Nome", "Idade"};  
            case 0:
                return contrato.getIdcliente().getNome();
            case 1:
                return this.quitado(contrato);
            case 2:
                return this.valorPago(contrato);
            case 3:
                return DINHEIRO_REAL.format(this.valorTotal(contrato));
            default:
                // Isto não deveria acontecer...  
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
//modifica na linha e coluna especificada  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Contrato contrato = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        switch (columnIndex) { // Seta o valor do campo respectivo  
            case 0:
                contrato.getIdcliente().getNome();
            default:
            // Isto não deveria acontecer...               
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //modifica na linha especificada  
    public void setValueAt(Assento aValue, int rowIndex) {
        Contrato contrato = linhas.get(rowIndex); // Carrega o item da linha que deve ser modificado  

        contrato.getIdcliente().getNome();

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Contrato getContrato(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    /* Adiciona um registro. */
    public void addItem(Contrato m) {
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
    public void addListaContratos(List<Contrato> passageiros) {
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

    private String quitado(Contrato c) {
        if (c.getPaga()) {
            return "Pago";
        } else {
            return "Não";
        }
    }

    private String valorPago(Contrato c) {
        double total = 0;

        ParcelaDAO pDAO = new ParcelaDAO();

        ArrayList<Parcela> parcelas = new ArrayList<>();

        if (c.getIdforma().getIdformapagamento() == 1) {
            parcelas = pDAO.buscaParcelaViagemContrato(c.getIdviagem().getIdviagem(), c.getIdcontrato());
        } else if (c.getIdforma().getIdformapagamento() == 2) {
            return DINHEIRO_REAL.format(this.valorTotal(c));
        }

        if (!parcelas.isEmpty()) {
            for (Parcela parcela : parcelas) {
                if (parcela.getPaga()) {
                    total += parcela.getValor();
                }
            }
        }
        return DINHEIRO_REAL.format(total);
    }

    private double valorTotal(Contrato c) {
        double onibus = 0, hotel = 0;

        VeiculoDAO vDAO = new VeiculoDAO();
        AssentoDAO aDAO = new AssentoDAO();
        VeiculosContratadosDAO vcDAO = new VeiculosContratadosDAO();
        HoteisContratadosDAO hcDAO = new HoteisContratadosDAO();

        ArrayList<Hoteiscontratados> hoteiscontratados = hcDAO.buscaHoteisContratadosViagem(c.getIdviagem().getIdviagem());
        ArrayList<Veiculoscontratados> veiculoscontratados = vcDAO.buscaVeiculosContratadosViagem(c.getIdviagem().getIdviagem());

        ArrayList<AssentoPK> apk = aDAO.buscaAssentoContrato(c.getIdcontrato());

        if (!veiculoscontratados.isEmpty()) {
            for (Veiculoscontratados nextc : veiculoscontratados) {
                for (AssentoPK apks : apk) {
                    if (nextc.getVeiculo().getIdveiculo() == apks.getIdveiculo()) {
                        if (nextc.getValor() > onibus) {
                            onibus = nextc.getValor();
                        }
                    }
                }

            }
        }

        if (!hoteiscontratados.isEmpty()) {
            for (Hoteiscontratados hoteu : hoteiscontratados) {
                if (hoteu.getValor() > hotel) {
                    hotel = hoteu.getValor();
                }
            }
        }

        return (onibus + hotel) * c.getQuantidade();
    }
}
