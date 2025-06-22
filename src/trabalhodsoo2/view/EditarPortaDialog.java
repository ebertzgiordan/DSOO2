package trabalhodsoo2.view;

import trabalhodsoo2.entity.Porta;
import trabalhodsoo2.dao.PortaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class EditarPortaDialog extends JDialog {

    private JTextField txtCodigo, txtLocalizacao, txtTipoCabo, txtUso, txtUsuarioFinal, txtObservacao;
    private JCheckBox chkOcupada;
    private JButton btnSalvar, btnCancelar, btnDeletar;
    private Porta porta;
    private PortaDAO portaDAO;
    private String tipo;

    public EditarPortaDialog(Window parent, Porta porta, String tipo) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.porta = porta;
        this.tipo = tipo;
        this.portaDAO = new PortaDAO();
        setTitle("Editar Porta");

        setLayout(new GridLayout(9, 2, 10, 5));

        add(new JLabel("Código:"));
        txtCodigo = new JTextField(porta.getCodigo());
        add(txtCodigo);

        add(new JLabel("Localização:"));
        txtLocalizacao = new JTextField(porta.getLocalizacao());
        add(txtLocalizacao);

        add(new JLabel("Tipo de Cabo:"));
        txtTipoCabo = new JTextField(porta.getTipoCabo());
        add(txtTipoCabo);

        add(new JLabel("Uso:"));
        txtUso = new JTextField(porta.getUso());
        add(txtUso);

        add(new JLabel("Usuário Final:"));
        txtUsuarioFinal = new JTextField(porta.getUsuarioFinal());
        add(txtUsuarioFinal);

        add(new JLabel("Observação:"));
        txtObservacao = new JTextField(porta.getObservacao());
        add(txtObservacao);

        add(new JLabel("Ocupada:"));
        chkOcupada = new JCheckBox("Sim", porta.isOcupada());
        add(chkOcupada);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        btnDeletar = new JButton("Excluir");

        add(btnSalvar);
        add(btnCancelar);
        add(btnDeletar);

        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());
        btnDeletar.addActionListener(e -> excluir());

        pack();
        setLocationRelativeTo(parent);
    }

    private void salvar() {
        porta.setCodigo(txtCodigo.getText());
        porta.setLocalizacao(txtLocalizacao.getText());
        porta.setTipoCabo(txtTipoCabo.getText());
        porta.setUso(txtUso.getText());
        porta.setUsuarioFinal(txtUsuarioFinal.getText());
        porta.setObservacao(txtObservacao.getText());
        porta.setOcupada(chkOcupada.isSelected());

        portaDAO.atualizar(porta, tipo);
        JOptionPane.showMessageDialog(this, "Porta atualizada com sucesso!");
        dispose();
    }

    private void excluir() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta porta?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            portaDAO.excluir(porta.getId(), tipo);
            JOptionPane.showMessageDialog(this, "Porta excluída com sucesso!");
            dispose();
        }
    }
}
