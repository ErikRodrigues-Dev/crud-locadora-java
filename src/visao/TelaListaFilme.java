package visao;

import entidades.Filme;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.FilmeDAO;

public class TelaListaFilme extends javax.swing.JFrame {

      private List<Filme> listaFilmes;
      
    public TelaListaFilme() {
        initComponents();
        montarListaFilmes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabFilmes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusable(false);
        setResizable(false);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        tabFilmes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Categoria", "Preço", "N.º Dias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabFilmes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
       new TelaCadastroFilme(this).setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int linha = tabFilmes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para alterar!");
        } else {
            
            TelaCadastroFilme cadastro = new TelaCadastroFilme(this);
            cadastro.setFilme(listaFilmes.get(linha));// Pega o filme selecionado e envia para tela de cadastro
            cadastro.setVisible(true);
           
        }
        
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linha = tabFilmes.getSelectedRow();
        if (linha == -1){
            JOptionPane.showMessageDialog(this, "Selecione um filme para excluir!");
        } else {
            Filme filme = listaFilmes.get(linha);
            String mensagem = "Deseja realmente excluir o filme " +filme.getTitulo() + "?";
            int opcao = JOptionPane.showConfirmDialog(this, mensagem, "Confirme a exclusão", 
                    JOptionPane.YES_NO_OPTION);
            
            if (opcao == JOptionPane.YES_OPTION) {
              if (FilmeDAO.excluir(filme.getId())) {
                  montarListaFilmes(); //Atualizar a lista de jogos.
                  JOptionPane.showMessageDialog(this, "Filme excluido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o filme!");    
              }  
           }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    public void montarListaFilmes() {
          try {
              listaFilmes = FilmeDAO.listar();
              DefaultTableModel modelo = (DefaultTableModel)tabFilmes.getModel();
              modelo.setRowCount(0);
              for (Filme filme : listaFilmes) {
                  Object[] linha = {
                      filme.getTitulo(),
                      filme.getCategoria().getNome(),
                      filme.getPreco(),
                      filme.getNumroDias()
                          
                  };
                  modelo.addRow(linha); 
              } 
              
          }
          catch (SQLException ex) {
              Logger.getLogger(TelaListaFilme.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabFilmes;
    // End of variables declaration//GEN-END:variables
}
