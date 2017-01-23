/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turism.gui;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import turism.controle.EmpresaDAO;
import turism.controle.VeiculoDAO;
import turism.modelo.Empresa;
import turism.modelo.Veiculo;
import turism.regra.VeiculosTableModel;

/**
 *
 * @author matheus
 */
public class TelaEmpresa extends javax.swing.JFrame {

    //Entidade e DAO
    Empresa empresa = new Empresa();
    EmpresaDAO eDAO = new EmpresaDAO();

    //Array
    ArrayList<Empresa> empresas = new ArrayList<>();
    ArrayList<Veiculo> veiculos = new ArrayList<>();
    
    //modelo Table
    VeiculosTableModel modelo;

    /**
     * Creates new form TelaEmpresa
     */
    public TelaEmpresa() {
        initComponents();
        jBusca.setVisible(false);
        modelo = new VeiculosTableModel();
        jtVeiculos.setModel(modelo);
        painelVeiculos.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        painelFuncoes = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        painelVeiculos = new javax.swing.JPanel();
        jSPVeiculos = new javax.swing.JScrollPane();
        jtVeiculos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeVeiculo = new javax.swing.JTextField();
        jCBTipoVeiculo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtLotacao = new javax.swing.JSpinner();
        btnRmVeiculo = new javax.swing.JButton();
        btnAdVeiculo = new javax.swing.JButton();
        jBusca = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        listBusca = new javax.swing.JList<>();
        txtBusca = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empresa e Veículos");

        labelNome.setText("Nome:");

        painelFuncoes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/turism/imagens/padrao/save_64.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/turism/imagens/padrao/search_64.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/turism/imagens/padrao/delete_64.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/turism/imagens/padrao/add_64.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelFuncoesLayout = new javax.swing.GroupLayout(painelFuncoes);
        painelFuncoes.setLayout(painelFuncoesLayout);
        painelFuncoesLayout.setHorizontalGroup(
            painelFuncoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFuncoesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdicionar))
            .addGroup(painelFuncoesLayout.createSequentialGroup()
                .addGroup(painelFuncoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addComponent(btnExcluir))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelFuncoesLayout.setVerticalGroup(
            painelFuncoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFuncoesLayout.createSequentialGroup()
                .addComponent(btnAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addContainerGap())
        );

        painelVeiculos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Veículo"));

        jtVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jSPVeiculos.setViewportView(jtVeiculos);

        jLabel1.setText("Nome:");

        jLabel2.setText("Tipo:");

        jCBTipoVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ônibus", "Van", "Micro Ônibus", "Carro" }));

        jLabel3.setText("Nº Lotação:");

        txtLotacao.setModel(new javax.swing.SpinnerNumberModel(50, null, null, 1));

        btnRmVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/turism/imagens/close_16.png"))); // NOI18N
        btnRmVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmVeiculoActionPerformed(evt);
            }
        });

        btnAdVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/turism/imagens/add_16.png"))); // NOI18N
        btnAdVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdVeiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelVeiculosLayout = new javax.swing.GroupLayout(painelVeiculos);
        painelVeiculos.setLayout(painelVeiculosLayout);
        painelVeiculosLayout.setHorizontalGroup(
            painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVeiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelVeiculosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(120, 120, 120))
                    .addGroup(painelVeiculosLayout.createSequentialGroup()
                        .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painelVeiculosLayout.createSequentialGroup()
                                .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLotacao))
                                .addGap(47, 47, 47)
                                .addComponent(btnAdVeiculo))
                            .addComponent(txtNomeVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelVeiculosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jCBTipoVeiculo, 0, 144, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(painelVeiculosLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnRmVeiculo)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jSPVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        painelVeiculosLayout.setVerticalGroup(
            painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVeiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNomeVeiculo)
                    .addComponent(jCBTipoVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRmVeiculo)
                    .addComponent(btnAdVeiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSPVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        jBusca.setClosable(true);
        jBusca.setVisible(true);

        listBusca.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listBusca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listBuscaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listBusca);

        txtBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCaretUpdate(evt);
            }
        });
        txtBusca.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtBuscaInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        javax.swing.GroupLayout jBuscaLayout = new javax.swing.GroupLayout(jBusca.getContentPane());
        jBusca.getContentPane().setLayout(jBuscaLayout);
        jBuscaLayout.setHorizontalGroup(
            jBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addComponent(txtBusca))
                .addContainerGap())
        );
        jBuscaLayout.setVerticalGroup(
            jBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBuscaLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(labelNome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(painelVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(painelFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(labelNome)
                .addGap(6, 6, 6)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        jBusca.setVisible(true);
        empresas = eDAO.buscaEmpresas(txtNome.getText());
        txtBusca.setText(txtNome.getText());
        listBusca.removeAll();
        DefaultListModel modelol = new DefaultListModel();
        empresas.forEach((next) -> {
            modelol.addElement(next.getNome());
        });
        listBusca.setModel(modelol);
        listBusca.repaint();
        this.chaveBackdroug(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void listBuscaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listBuscaMouseClicked
        // TODO add your handling code here:
        int indice = listBusca.getSelectedIndex();
        if (indice >= 0) {
            empresa = empresas.get(indice);
            veiculos = eDAO.carregaVeiculos(empresa);
            painelVeiculos.setVisible(true);
            modelo.limpar();
            modelo.addListaVeiculo(veiculos);
            txtNome.setText(empresa.getNome());
        }
        jBusca.setVisible(false);
        this.chaveBackdroug(true);
    }//GEN-LAST:event_listBuscaMouseClicked

    private void txtBuscaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtBuscaInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaInputMethodTextChanged

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        // TODO add your handling code here:
        empresas = eDAO.buscaEmpresas(txtBusca.getText());
        listBusca.removeAll();
        DefaultListModel modelol = new DefaultListModel();
        empresas.forEach((next) -> {
            modelol.addElement(next.getNome());
        });
        listBusca.setModel(modelol);
        listBusca.repaint();
    }//GEN-LAST:event_txtBuscaCaretUpdate

    private void btnRmVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRmVeiculoActionPerformed
        // TODO add your handling code here:
        int indice = jtVeiculos.getSelectedRow();
        Veiculo v = veiculos.get(indice);
        veiculos.remove(indice);
        VeiculoDAO vDAO = new VeiculoDAO();
        vDAO.apagar(v);
        modelo.limpar();
        modelo.addListaVeiculo(veiculos);
    }//GEN-LAST:event_btnRmVeiculoActionPerformed

    private void btnAdVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdVeiculoActionPerformed
        // TODO add your handling code here:
        int lotacao = Integer.valueOf(txtLotacao.getValue().toString());
        Veiculo v;
        if (!(txtNome.getText().equalsIgnoreCase("")) && !(lotacao <= 0)){
            v = new Veiculo();
            v.setIdempresa(empresa);
            v.setLotacao(lotacao);
            v.setNome(txtNomeVeiculo.getText());
            String tipo = (String) jCBTipoVeiculo.getSelectedItem();
            v.setTipo(tipo);
            if ((veiculos.contains(v))){
                JOptionPane.showMessageDialog(this, "Veículo já adicionado");
                System.out.println("V: "+ v +" Veiculos: "+veiculos);
            } else {
                VeiculoDAO vDAO = new VeiculoDAO();
                vDAO.adicionar(v);
                veiculos.add(v);
                modelo.addItem(v);
            }
        }
    }//GEN-LAST:event_btnAdVeiculoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        empresa = new Empresa();
        txtNome.setText("");
        txtNomeVeiculo.setText("");
        btnAdicionar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnBuscar.setEnabled(false);
        painelVeiculos.setVisible(false);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite nome da Empresa!");
        } else {
            if (empresa.getIdempresa() == null) {
                empresa.setNome(txtNome.getText());
                eDAO.adiciona(empresa);
            } else {
                empresa.setNome(txtNome.getText());
                eDAO.atualizar(empresa);
            }
            btnAdicionar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnSalvar.setEnabled(false);
            btnBuscar.setEnabled(true);
            painelVeiculos.setVisible(true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int opcao = -1;
        if(empresa.getIdempresa() == null){
            JOptionPane.showMessageDialog(this, "Busque uma Empresa primeiro!");
        }else{
            opcao = JOptionPane.showConfirmDialog(this, "Certeza que deseja exluir?");
        }
        
        if(opcao == JOptionPane.YES_OPTION){
            VeiculoDAO vDAO = new VeiculoDAO();
            veiculos.forEach((veiculo) -> {
                vDAO.apagar(veiculo);
            });
            eDAO.apagar(empresa);
            JOptionPane.showMessageDialog(this, "Empresa e Veiculos excluidos!");
            
            //Nova instancia
            eDAO = new EmpresaDAO();
            empresa = new Empresa();
            veiculos.clear();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdVeiculo;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRmVeiculo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JInternalFrame jBusca;
    private javax.swing.JComboBox<String> jCBTipoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jSPVeiculos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtVeiculos;
    private javax.swing.JLabel labelNome;
    private javax.swing.JList<String> listBusca;
    private javax.swing.JPanel painelFuncoes;
    private javax.swing.JPanel painelVeiculos;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JSpinner txtLotacao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeVeiculo;
    // End of variables declaration//GEN-END:variables

private void chaveBackdroug(boolean chave){
    txtNome.setVisible(chave);
    labelNome.setVisible(chave);
    painelFuncoes.setVisible(chave);
    painelVeiculos.setVisible(chave);
}
}