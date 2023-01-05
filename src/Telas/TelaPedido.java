/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dal.ModuloConexaoJavastar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


        

public class TelaPedido extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    private String tipo; 
    
    public TelaPedido() {
        initComponents();
        
        conexao = ModuloConexaoJavastar.conector();
    }

     // Método responsável pela pesquisa do cliente que será vinculado a OS

        private void pesquisarCliente(){

                
         /* Método responsável pela emissão de uma Ordem de Serviço*/
        String sql = "select idcli as ID, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";

        try {

        pst = conexao.prepareStatement(sql);
        pst.setString(1,txtCliPesquisar.getText() + "%");
        rs = pst.executeQuery();
        tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e);

        }

        }
        
        
         
        private void emitirOs(){

        String sql ="insert into tbpedidos(idcli,nome,Produto,descricao,TipoServico,Quantidade,valorTotal) values(?,?,?,?,?,?,?)";
//cboPedProdutos.setSelectedItem
        try {
        pst=conexao.prepareStatement(sql);
        pst.setString(1,txtCliIdPesquisar.getText());
        pst.setString(2,txtCliPesquisar.getText());
        pst.setString(3,cboPedProdutos.getSelectedItem().toString());
        pst.setString(4,txtPedDescricao.getText());
        pst.setString(5,tipo);
        pst.setString(6,txtPedQuantidade.getText());
        pst.setString(7,txtPedValorTotal.getText());
        


        int adicionado = pst.executeUpdate();

        if(adicionado >0){
        JOptionPane.showMessageDialog(null,"Ordem de Servico emitida com sucesso");
        txtCliIdPesquisar.setText(null);
        txtCliPesquisar.setText(null);

        txtPedDescricao.setText(null);
        txtPedValorTotal.setText(null);
        }

        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        }

        }
        
        private void setarServico(){
            String sql ="select * from tbpedidos where protocolo=?";
        try {
            switch(tipo = rs.getString(7)){
                case "Entrega":
                    rbtPedEntrega.doClick();
                break;
                case "Viagem":
                    rbtPedViagem.doClick();
                break;
                case "Mesa":
                    rbtPedMesa.doClick();
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        // Tem Que transformar isso em outra coisa
        /*
        private void setarServico(){
            String sql ="select * from tbpedidos where protocolo=?";
        try {
            switch(tipo = rs.getString(7)){
                case "Entrega":
                    rbtPedEntrega.doClick();
                break;
                case "Viagem":
                    rbtPedViagem.doClick();
                break;
                case "Mesa":
                    rbtPedMesa.doClick();
                break;
                
                
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        */
        private void pesquisar(){
        
        
            
            
        String sql ="select * from tbpedidos where protocolo=?";
        
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtPedProtocolo.getText());
            rs=pst.executeQuery();
                      
            if (rs.next()) {
             txtPedProtocolo.setText(rs.getString(1));
             txtCliIdPesquisar.setText(rs.getString(3));
             txtCliPesquisar.setText(rs.getString(4));
             
             txtPedDescricao.setText(rs.getString(6));
             switch(tipo = rs.getString(7)){
                
                 case "entrega":
                    rbtPedEntrega.doClick();
                break;
                case "Viagem":
                    rbtPedViagem.doClick();
                break;
                case "Mesa":
                    rbtPedMesa.doClick();
                break;
            }
             txtPedQuantidade.setText(rs.getString(8));
             cboPedProdutos.setSelectedItem(rs.getString(5));
             
             
             
             
             
              
            } else {
             JOptionPane.showMessageDialog(null,"Pedido não cadastrado");
             txtPedProtocolo.setText(null);
             txtPedDescricao.setText(null);
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            }
          }
        
        private void editar(){
        String sql ="update tbpedidos set protocolo= ?, Quantidade=?, ValorTotal=? where protocolo=?";
        try {
         pst=conexao.prepareStatement(sql);
         pst.setString(1,txtPedProtocolo.getText());
         pst.setString(2,txtPedQuantidade.getText());
         pst.setString(3,txtPedValorTotal.getText());
         pst.setString(4,txtPedProtocolo.getText());

        int adicionado = pst.executeUpdate();
        if(adicionado >0){
        JOptionPane.showMessageDialog(null,"Pedido alterado com sucesso");
         txtPedProtocolo.setText(null);
         txtPedDescricao.setText(null);
         txtPedQuantidade.setText(null);
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
       }
      }
        
        private void deletar(){
            
         int confirma=JOptionPane.showConfirmDialog(null, "Tem Certeza Que Deseja Remover Este Usuário?"
            ,"Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
         String sql="delete from tbpedidos where protocolo=?";
        try {
         pst=conexao.prepareStatement(sql);
         pst.setString(1,txtPedProtocolo.getText());
        int apagado = pst.executeUpdate();
        if (apagado>0) {
         JOptionPane.showMessageDialog(null,"Pedido removido com sucesso");
        }
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
       }
      }
     }

          private void setarProdutos(){
              
                String sql ="select * from tbprodutos where produto=?"; 
                
                String prod = cboPedProdutos.getSelectedItem().toString();
                              
                try{
                    pst=conexao.prepareStatement(sql);
                    pst.setString(1,prod);
                    rs=pst.executeQuery();
                    if (rs.next()){
                        cboPedProdutos.setSelectedItem(rs.getString(2));
                        txtPedDescricao.setText(rs.getString(3));
                        txtPedValorUnidade.setText(rs.getString(5));
                        txtPedQuantidade.setText(null);
                    } else {
                        JOptionPane.showMessageDialog(null,"Pedido não cadastrado");
                        txtPedProtocolo.setText(null);
                        txtPedDescricao.setText(null);
                        txtPedQuantidade.setText(null);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e);
                }
                
            }
                
        // Método responsável por setar o ID do cliente na OS

        private void setarIdCli() {
        int setar = tblClientes.getSelectedRow();
        txtCliIdPesquisar.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        }
        private void setarNomeCli(){
            int setar = tblClientes.getSelectedRow();
        txtCliPesquisar.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        }
        
        private void semTexto(){
            txtPedProtocolo.setEditable(false);
        }
        
        private void calculoPreco(){
            float resultado = Float.parseFloat(txtPedValorUnidade.getText()) * Float.parseFloat(txtPedQuantidade.getText());
            txtPedValorTotal.setText(String.valueOf(resultado));
        }
        
        private void teste(){
        String sql ="select * from tbprodutos where produto=?";
         
        String prod = cboPedProdutos.getSelectedItem().toString();
        try{
           
        cboPedProdutos.setSelectedItem(rs.getString(5));
        }catch(Exception e){
        }
         
        }
            
        
        
    
        
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        txtPedDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCliIdPesquisar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        rbtPedEntrega = new javax.swing.JRadioButton();
        rbtPedViagem = new javax.swing.JRadioButton();
        rbtPedMesa = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPedValorUnidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPedQuantidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPedValorTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnPedExcluir = new javax.swing.JButton();
        btnPedAdicionar = new javax.swing.JButton();
        btnPedEditar = new javax.swing.JButton();
        btnPedPesquisar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPedProtocolo = new javax.swing.JTextField();
        cboPedProdutos = new javax.swing.JComboBox<>();
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

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Produto");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, 30));
        getContentPane().add(txtPedDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 510, -1));

        jLabel1.setText("Descrição");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCliPesquisar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel7.setText("Cliente");

        txtCliIdPesquisar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel6.setText("ID");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(txtCliIdPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliPesquisar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCliIdPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 410, 160));

        buttonGroup1.add(rbtPedEntrega);
        rbtPedEntrega.setText("Entrega");
        rbtPedEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtPedEntregaActionPerformed(evt);
            }
        });
        getContentPane().add(rbtPedEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 90, -1));

        buttonGroup1.add(rbtPedViagem);
        rbtPedViagem.setText("Viagem");
        rbtPedViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtPedViagemActionPerformed(evt);
            }
        });
        getContentPane().add(rbtPedViagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

        buttonGroup1.add(rbtPedMesa);
        rbtPedMesa.setText("Mesa");
        rbtPedMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtPedMesaActionPerformed(evt);
            }
        });
        getContentPane().add(rbtPedMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        jLabel2.setText("Tipo De Serviço:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel3.setText("Valor Uni.");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 30));

        txtPedValorUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPedValorUnidadeKeyReleased(evt);
            }
        });
        getContentPane().add(txtPedValorUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 80, -1));

        jLabel4.setText("Quantidade");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, 30));

        txtPedQuantidade.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtPedQuantidadeComponentAdded(evt);
            }
        });
        txtPedQuantidade.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtPedQuantidadeInputMethodTextChanged(evt);
            }
        });
        txtPedQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPedQuantidadeActionPerformed(evt);
            }
        });
        txtPedQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPedQuantidadeKeyReleased(evt);
            }
        });
        getContentPane().add(txtPedQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 100, -1));

        jLabel8.setText("Valor Total");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, 30));

        txtPedValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPedValorTotalActionPerformed(evt);
            }
        });
        getContentPane().add(txtPedValorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 150, -1));

        btnPedExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ExcluirPedidos.png"))); // NOI18N
        btnPedExcluir.setPreferredSize(new java.awt.Dimension(126, 112));
        btnPedExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedExcluirActionPerformed(evt);
            }
        });

        btnPedAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/AdicionarPedido.png"))); // NOI18N
        btnPedAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedAdicionarActionPerformed(evt);
            }
        });

        btnPedEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/EditarPedidos.png"))); // NOI18N
        btnPedEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedEditarActionPerformed(evt);
            }
        });

        btnPedPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PesquisarPedidos.png"))); // NOI18N
        btnPedPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedPesquisarActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Adicionar");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Pesquisar");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Editar");
        jLabel11.setToolTipText("");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Excluir");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPedExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPedExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPedAdicionar)
                    .addComponent(btnPedEditar)
                    .addComponent(btnPedPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 620, 140));

        jLabel13.setText("Protocolo");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 30));

        txtPedProtocolo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPedProtocoloMousePressed(evt);
            }
        });
        getContentPane().add(txtPedProtocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 70, -1));

        cboPedProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expresso", "Café Com Leite", "Cappuccino" }));
        cboPedProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboPedProdutosMouseClicked(evt);
            }
        });
        cboPedProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPedProdutosActionPerformed(evt);
            }
        });
        getContentPane().add(cboPedProdutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 250, -1));

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

        setSize(new java.awt.Dimension(636, 528));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtPedViagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPedViagemActionPerformed
        // TODO add your handling code here:
        tipo="Viagem";
    }//GEN-LAST:event_rbtPedViagemActionPerformed

    private void rbtPedMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPedMesaActionPerformed
        // TODO add your handling code here:
        tipo="Mesa";
    }//GEN-LAST:event_rbtPedMesaActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        setarIdCli();
        setarNomeCli();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtCliPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCliPesquisarKeyPressed

    private void btnPedAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedAdicionarActionPerformed
        // TODO add your handling code here:
        emitirOs();
    }//GEN-LAST:event_btnPedAdicionarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisarCliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void txtPedProtocoloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPedProtocoloMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPedProtocoloMousePressed

    private void txtPedValorUnidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedValorUnidadeKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPedValorUnidadeKeyReleased

    private void txtPedQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedQuantidadeKeyReleased
        // TODO add your handling code here:
        calculoPreco();
    }//GEN-LAST:event_txtPedQuantidadeKeyReleased

    private void btnPedPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedPesquisarActionPerformed
        // TODO add your handling code here:
        
        pesquisar();
        
        teste();
        calculoPreco();
    }//GEN-LAST:event_btnPedPesquisarActionPerformed

    private void cboPedProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPedProdutosActionPerformed
        // TODO add your handling code here:
        setarProdutos();
    }//GEN-LAST:event_cboPedProdutosActionPerformed

    private void cboPedProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboPedProdutosMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboPedProdutosMouseClicked

    private void rbtPedEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPedEntregaActionPerformed

        tipo="entrega";
    }//GEN-LAST:event_rbtPedEntregaActionPerformed

    private void btnPedEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedEditarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_btnPedEditarActionPerformed

    private void btnPedExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedExcluirActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnPedExcluirActionPerformed

    private void txtPedQuantidadeComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtPedQuantidadeComponentAdded
        // TODO add your handling code here:
        calculoPreco();
    }//GEN-LAST:event_txtPedQuantidadeComponentAdded

    private void txtPedQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPedQuantidadeActionPerformed
        // TODO add your handling code here:
        calculoPreco();
    }//GEN-LAST:event_txtPedQuantidadeActionPerformed

    private void txtPedQuantidadeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtPedQuantidadeInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPedQuantidadeInputMethodTextChanged

    private void txtPedValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPedValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPedValorTotalActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPedAdicionar;
    private javax.swing.JButton btnPedEditar;
    private javax.swing.JButton btnPedExcluir;
    private javax.swing.JButton btnPedPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboPedProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem menAjuSob;
    private javax.swing.JMenuItem menCadCli;
    private javax.swing.JMenuItem menCadProd;
    private javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenuItem menOpSair;
    private javax.swing.JMenuItem menRelSer;
    private javax.swing.JRadioButton rbtPedEntrega;
    private javax.swing.JRadioButton rbtPedMesa;
    private javax.swing.JRadioButton rbtPedViagem;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliIdPesquisar;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtPedDescricao;
    private javax.swing.JTextField txtPedProtocolo;
    private javax.swing.JTextField txtPedQuantidade;
    private javax.swing.JTextField txtPedValorTotal;
    private javax.swing.JTextField txtPedValorUnidade;
    // End of variables declaration//GEN-END:variables
}
