package trabalhodsoo2.view;

import java.awt.Window;
import trabalhodsoo2.dao.PatchPanelDAO;
import trabalhodsoo2.dao.PortaDAO;
import trabalhodsoo2.dao.RackDAO;
import trabalhodsoo2.entity.PatchPanel;
import trabalhodsoo2.entity.Porta;
import trabalhodsoo2.entity.Rack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Editar_Portas extends javax.swing.JInternalFrame {

    private DefaultTableModel modeloTabela;

    public Editar_Portas() {
        initComponents();
        configurarTabela();
        configurarEventos();
        carregarRacks();
    }

    private void configurarTabela() {
        modeloTabela = new DefaultTableModel();
        jTable1.setModel(modeloTabela);
    }

    private void configurarEventos() {
        jComboBox1.addActionListener(e -> atualizarPatchPanels());
        jComboBox2.addActionListener(e -> atualizarPatchPanels());
        jComboBox3.addActionListener(e -> carregarPortas());
    }

    private void carregarRacks() {
        List<Rack> racks = new RackDAO().listar();
        jComboBox1.removeAllItems();
        for (Rack r : racks) {
            jComboBox1.addItem(r);
        }
    }

    private void atualizarPatchPanels() {
        Rack rack = (Rack) jComboBox1.getSelectedItem();
        String tipo = (String) jComboBox2.getSelectedItem();
        if (rack == null || tipo == null) {
            return;
        }

        List<PatchPanel> patches = new PatchPanelDAO().listarPorRackETipo(rack.getId(), tipo);
        jComboBox3.removeAllItems();
        for (PatchPanel p : patches) {
            jComboBox3.addItem(p);
        }
    }

    private void carregarPortas() {
        PatchPanel patch = (PatchPanel) jComboBox3.getSelectedItem();
        String tipo = (String) jComboBox2.getSelectedItem();
        if (patch == null || tipo == null) {
            return;
        }

        PortaDAO dao = new PortaDAO();
        List<Porta> portas = dao.listar(patch.getId(), tipo);

        if (tipo.equals("Lógico")) {
            modeloTabela.setColumnIdentifiers(new String[]{"ID", "Código", "Localização", "Ocupada", "Observação", "Tipo Cabo", "Uso", "Usuário Final"});
        } else {
            modeloTabela.setColumnIdentifiers(new String[]{"ID", "Código", "Localização", "Ocupada", "Observação", "Ramal", "Cor Cabo", "Usuário Final"});
        }

        modeloTabela.setRowCount(0);
        for (Porta p : portas) {
            if (tipo.equals("Lógico")) {
                modeloTabela.addRow(new Object[]{p.getId(), p.getCodigo(), p.getLocalizacao(), p.isOcupada(), p.getObservacao(), p.getTipoCabo(), p.getUso(), p.getUsuarioFinal()});
            } else {
                modeloTabela.addRow(new Object[]{p.getId(), p.getCodigo(), p.getLocalizacao(), p.isOcupada(), p.getObservacao(), p.getRamal(), p.getCorCabo(), p.getUsuarioFinal()});
            }
        }
    }

    private void editarPorta() {
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma porta para editar.");
            return;
        }

        int id = (Integer) modeloTabela.getValueAt(linha, 0);
        String tipo = (String) jComboBox2.getSelectedItem();

        PortaDAO dao = new PortaDAO();
        Porta portaSelecionada = dao.buscarPorId(id, tipo);

        if (portaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Erro ao recuperar dados da porta.");
            return;
        }

        Window parent = SwingUtilities.getWindowAncestor(this);
        EditarPortaDialog dialog = new EditarPortaDialog(parent, portaSelecionada, tipo);
        dialog.setVisible(true);

        // Após fechar o diálogo, atualiza a tabela
        carregarPortas();
    }

    // A partir daqui é o initComponents gerado pelo NetBeans
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listar/Editar Portas");

        jLabel1.setText("Listar/Editar Portas");

        jLabel2.setText("Selecione o Rack:");

        jLabel3.setText("Tipo de Porta:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Lógico", "Telefone"}));

        jLabel4.setText("Selecione o Patch Panel:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        jScrollPane1.setViewportView(jTable1);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(evt -> editarPorta());

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(evt -> dispose());

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(evt -> carregarPortas());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(144, 144, 144)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(btnEditar)
                                                .addGap(50, 50, 50)
                                                .addComponent(btnAtualizar)
                                                .addGap(50, 50, 50)
                                                .addComponent(btnFechar)))
                                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnEditar)
                                        .addComponent(btnAtualizar)
                                        .addComponent(btnFechar))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    // Declarações de variáveis (gerado pelo NetBeans)
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JComboBox<Rack> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<PatchPanel> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
