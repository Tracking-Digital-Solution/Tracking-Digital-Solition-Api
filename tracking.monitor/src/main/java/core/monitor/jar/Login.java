package core.monitor.jar;

import core.monitor.repositorio.Ilooca;
import core.monitor.resources.ConexaoService;
import login.jar.Usuario;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Login extends javax.swing.JFrame implements Ilooca {

	Scanner leitor = new Scanner(System.in);
	ConexaoService conexao = new ConexaoService();
	JdbcTemplate con = conexao.getConexaoDoBanco();
	String email;
	String senha;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btn_entrar;
	private javax.swing.JTextField ipt_email;
	private javax.swing.JTextField ipt_senha;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	public Login() {
		initComponents();
	}

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
			java.util.logging.Logger.getLogger(Login.class
					.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class
					.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class
					.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class
					.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		ipt_senha = new javax.swing.JTextField();
		ipt_email = new javax.swing.JTextField();
		btn_entrar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("LOGIN");

		jLabel2.setText("Email");

		jLabel3.setText("Senha");

		ipt_senha.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ipt_senhaActionPerformed(evt);
			}
		});

		ipt_email.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ipt_emailActionPerformed(evt);
			}
		});

		btn_entrar.setText("ENTRAR");
		btn_entrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_entrarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(175, 175, 175)
												.addComponent(jLabel1))
										.addGroup(layout.createSequentialGroup()
												.addGap(29, 29, 29)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel3)
														.addComponent(jLabel2)
														.addComponent(ipt_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(ipt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(159, 159, 159)
												.addComponent(btn_entrar)))
								.addContainerGap(136, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addGap(40, 40, 40)
								.addComponent(jLabel2)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(ipt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(33, 33, 33)
								.addComponent(jLabel3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(ipt_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
								.addComponent(btn_entrar)
								.addGap(19, 19, 19))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void ipt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipt_emailActionPerformed
		// TODO add your handling code here:
		email = ipt_email.getText();
		System.out.println(email);

	}//GEN-LAST:event_ipt_emailActionPerformed

	private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
		// TODO add your handling code here:
		email = ipt_email.getText();
		senha = ipt_senha.getText();
		System.out.println(senha);
		System.out.println(email);

//        for (int i = 0;
//                i < usuariosDoBanco.size();
//                i++) {
//            if (email.equals(usuariosDoBanco.get(i).getEmail())
//                    && senha.equals(usuariosDoBanco.get(i).getSenha())) {
//                new Home().setVisible(true);
//            } else {
//                System.out.println("Usuário ou senha incorretos");
//                System.out.println(usuariosDoBanco.get(i).getSenha());
//
//            }
		if (validationLogin()) {
			new Home().setVisible(true);
			//System.out.println("foi");
		} else {
			System.out.println("Erro ao cadastrar Login");
		}
	}//GEN-LAST:event_btn_entrarActionPerformed

	private void ipt_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipt_senhaActionPerformed
		// TODO add your handling code here:
		senha = ipt_senha.getText();
	}//GEN-LAST:event_ipt_senhaActionPerformed

	private Boolean validationLogin() {

		try {
			con.queryForObject(String.format("select * from perfil where email = '%s' and senha = '%s'", email, senha),
					new BeanPropertyRowMapper<>(Usuario.class));
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return false;

		}

		return true;

	}

	@Override
	public String getIp() {
		return null;
	}
	// End of variables declaration//GEN-END:variables

}
