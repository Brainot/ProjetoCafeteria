/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.sql.*;
import model.Dal.ModuloConexaoJavastar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCadastroProdutos extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public TelaCadastroProdutos() {
        initComponents();
        
        conexao = ModuloConexaoJavastar.conector();
        
    }

   private void adicionar(){
        String sql ="insert into tbprodutos(produto,descricao,estoque,valor) values(?,?,?,?)";
        try {
         pst=conexao.prepareStatement(sql);
         
         pst.setString(1,txtProProduto.getText());
         pst.setString(2,txtProDescricao.getText());
         pst.setString(3,txtProEstoque.getText());
         pst.setString(4,txtProValor.getText());
        int adicionado = pst.executeUpdate();
        if(adicionado >0){
        JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso");
         txtProCod.setText(null);
         txtProProduto.setText(null);
         txtProDescricao.setText(null);
         txtProEstoque.setText(null);
         txtProValor.setText(null);
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
       }
      }
        
    private void pesquisar(){
        
        String sql ="select * from tbprodutos where cod=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtProCod.getText());
            rs=pst.executeQuery();
            if (rs.next()) {
             txtProProduto.setText(rs.getString(2));
             txtProDescricao.setText(rs.getString(3));
             txtProEstoque.setText(rs.getString(4));
             txtProValor.setText(rs.getString(5));
            } else {
             JOptionPane.showMessageDialog(null,"Produto não cadastrado");
             txtProProduto.setText(null);
             txtProDescricao.setText(null);
             txtProEstoque.setText(null);
             txtProValor.setText(null);
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            }
          }
             

        
        //MÉTODO ALTERAR DADOS DE USUÁRIO
        private void editar(){
        String sql ="update tbprodutos set produto=?, descricao=?, estoque=?, valor=? where cod=?";
        try {
         pst=conexao.prepareStatement(sql);
         pst.setString(1,txtProProduto.getText());
         pst.setString(2,txtProDescricao.getText());
         pst.setString(3,txtProEstoque.getText());
         pst.setString(4,txtProValor.getText());
         pst.setString(5,txtProCod.getText());
        int adicionado = pst.executeUpdate();
        if(adicionado >0){
        JOptionPane.showMessageDialog(null,"Produto alterado com sucesso");
         txtProCod.setText(null);
         txtProProduto.setText(null);
         txtProDescricao.setText(null);
         txtProEstoque.setText(null);
         txtProValor.setText(null);
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
       }
      }
        
                
        // MÉTODO DELETE (REMOÇÃO DE USUÁRIOS)
        private void deletar(){
            
         int confirma=JOptionPane.showConfirmDialog(null, "Tem Certeza Que Deseja Remover Este Usuário?"
            ,"Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
         String sql="delete from tbprodutos where cod=?";
        try {
         pst=conexao.prepareStatement(sql);
         pst.setString(1,txtProCod.getText());
        int apagado = pst.executeUpdate();
        if (apagado>0) {
         JOptionPane.showMessageDialog(null,"Produto removido com sucesso");
        }
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
       }
      }
     }
        
        private void pesquisarUsuario(){

                
         /* Método responsável pela emissão de uma Ordem de Serviço*/
        String sql = "select cod, produto, descricao, estoque, valor from tbprodutos where produto like ?";

        try {

        pst = conexao.prepareStatement(sql);
        pst.setString(1,txtProPesquisar.getText() + "%"); //% == qualquer coisa
        rs = pst.executeQuery();
        tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e);

        }

        }
        
        private void pesquisarId(){

                
         /* Método responsável pela emissão de uma Ordem de Serviço*/
        String sql = "select cod, produto, descricao, estoque, valor from tbprodutos where cod like ?";

        try {

        pst = conexao.prepareStatement(sql);
        pst.setString(1,txtProPesquisarCod.getText() + "%"); //% == qualquer coisa
        rs = pst.executeQuery();
        tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e);

        }

        }
        
           
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtProCod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProProduto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtProDescricao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtProEstoque = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtProValor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel = new javax.swing.JLabel();
        txtProPesquisar = new javax.swing.JTextField();
        txtProPesquisarCod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        menCadUsu = new javax.swing.JMenuItem();
        menCadCli = new javax.swing.JMenuItem();
        menCadProd = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menRelSer = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menAjuSob = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menOpSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Cod");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 30));
        getContentPane().add(txtProCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 50, -1));

        jLabel3.setText("Produto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, 30));
        getContentPane().add(txtProProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 340, -1));

        jLabel4.setText("Descrição");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 50));
        getContentPane().add(txtProDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 470, -1));

        jLabel5.setText("Estoque");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 30));
        getContentPane().add(txtProEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 200, -1));

        jLabel6.setText("Valor Uni.");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, 50));
        getContentPane().add(txtProValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 190, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/deletar1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar_1.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/atualizar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/adicionarCafe.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Adicionar");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Pesquisar");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Editar");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Deletar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 620, 140));

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cod", "Produto", "Descricao", "Estoque", "Valor"
            }
        ));
        jScrollPane1.setViewportView(tblProdutos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 230, 540, 90));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 530, 20));

        jLabel.setText("Pesquisar");
        getContentPane().add(jLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, 30));

        txtProPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProPesquisarKeyPressed(evt);
            }
        });
        getContentPane().add(txtProPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 380, -1));

        txtProPesquisarCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProPesquisarCodKeyPressed(evt);
            }
        });
        getContentPane().add(txtProPesquisarCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 50, -1));

        jLabel7.setText("Cod");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 30));

        jMenu3.setText("Cadastro");

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menCadUsu.setText("Usuario");
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        jMenu3.add(menCadUsu);

        menCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menCadCli.setText("Cliente");
        menCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadCliActionPerformed(evt);
            }
        });
        jMenu3.add(menCadCli);

        menCadProd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menCadProd.setText("Produto");
        menCadProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadProdActionPerformed(evt);
            }
        });
        jMenu3.add(menCadProd);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Pedidos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatorio");

        menRelSer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menRelSer.setText("Serviços");
        jMenu4.add(menRelSer);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ajuda");

        menAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menAjuSob.setText("Sobre");
        jMenu5.add(menAjuSob);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Opções");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Voltar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        menOpSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menOpSair.setText("Sair");
        menOpSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpSairActionPerformed(evt);
            }
        });
        jMenu6.add(menOpSair);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(636, 524));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtProPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProPesquisarKeyPressed
        // TODO add your handling code here:
        pesquisarUsuario();
    }//GEN-LAST:event_txtProPesquisarKeyPressed

    private void txtProPesquisarCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProPesquisarCodKeyPressed
        // TODO add your handling code here:
        pesquisarId();
    }//GEN-LAST:event_txtProPesquisarCodKeyPressed

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed

        TelaCadastroUsuario chamaUsu = new TelaCadastroUsuario();

        chamaUsu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadCliActionPerformed

        TelaCadastroCliente chamacli = new TelaCadastroCliente();

        chamacli.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menCadCliActionPerformed

    private void menCadProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadProdActionPerformed
        TelaCadastroProdutos chamaProd = new TelaCadastroProdutos();

        chamaProd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menCadProdActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        TelaPedido chamaPedido = new TelaPedido();

        chamaPedido.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:

        TelaPrincipal chamaPrincipal = new TelaPrincipal();

        chamaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menOpSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpSairActionPerformed
        // TODO add your handling code here:

        System.exit(0);
    }//GEN-LAST:event_menOpSairActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem menAjuSob;
    private javax.swing.JMenuItem menCadCli;
    private javax.swing.JMenuItem menCadProd;
    private javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenuItem menOpSair;
    private javax.swing.JMenuItem menRelSer;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtProCod;
    private javax.swing.JTextField txtProDescricao;
    private javax.swing.JTextField txtProEstoque;
    private javax.swing.JTextField txtProPesquisar;
    private javax.swing.JTextField txtProPesquisarCod;
    private javax.swing.JTextField txtProProduto;
    private javax.swing.JTextField txtProValor;
    // End of variables declaration//GEN-END:variables
}
