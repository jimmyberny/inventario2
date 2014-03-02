package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.datas.DatosGeneral;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.modelo.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Enrique
 */
public class Login extends javax.swing.JPanel implements Vista {

	private static final Logger log = LoggerFactory.getLogger(Login.class);
	private Aplicacion app;
	private DatosGeneral dGeneral;

	public Login() {
		initComponents();

		jpLogin.setVisible(false);
		KeyListener kl = new EnterKeyListener();
		
		jtfUsuario.addKeyListener(kl);
		jpfContrasena.addKeyListener(kl);
	}

	@Override
	public void inicializar(Aplicacion app) throws InventarioException {
		this.app = app;

		dGeneral = (DatosGeneral) app.getDatos(InventarioApp.AD_GENERAL);
	}

	@Override
	public JComponent getVista() {
		return this;
	}

	@Override
	public String getTitulo() {
		return "Inicio";
	}

	@Override
	public void activar() throws InventarioException {
		// Nada nuevo
	}

	@Override
	public boolean ocultar() {
		return app.getUsuario() != null;
	}

	private void ingresar() {
		try {
			String usuario = jtfUsuario.getText();
			String contra = new String(jpfContrasena.getPassword());
			if (usuario.trim().isEmpty() || contra.isEmpty()) {
				log.info("Faltan datos");
				return;
			}
			Usuario usr = dGeneral.getUsuario(usuario, contra);
			if (usr != null) {
				app.ingresar(usr);
			} else {
				new AppMensaje("Usuario no encontrado").mostrar(this);
			}
		} catch (InventarioException ex) {
			new AppMensaje(ex).mostrar(this);
		}
	}

	private class EnterKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_ENTER) {
				ingresar();
			}
		}

	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jpfContrasena = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jbIngresar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Usuario");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 28));

        jtfUsuario.setPreferredSize(new java.awt.Dimension(160, 26));

        jpfContrasena.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Contraseña");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 28));

        jbIngresar.setText("Ingresar");
        jbIngresar.setPreferredSize(new java.awt.Dimension(120, 28));
        jbIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIngresarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.setPreferredSize(new java.awt.Dimension(120, 28));
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpLoginLayout = new javax.swing.GroupLayout(jpLogin);
        jpLogin.setLayout(jpLoginLayout);
        jpLoginLayout.setHorizontalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addContainerGap(574, Short.MAX_VALUE)
                .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLoginLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLoginLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLoginLayout.createSequentialGroup()
                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpLoginLayout.setVerticalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jpLogin, java.awt.BorderLayout.PAGE_END);

        lblLogo.setBackground(java.awt.Color.white);
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/BP_whistlingpig.png"))); // NOI18N
        lblLogo.setOpaque(true);
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });
        add(lblLogo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
		if (!jpLogin.isVisible()) {
			jpLogin.setVisible(true);
			jtfUsuario.requestFocus(); // Que el cursos se ubique en esta caja
			this.validate();
		}
    }//GEN-LAST:event_lblLogoMouseClicked

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
		jpLogin.setVisible(false);
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIngresarActionPerformed
		ingresar();
    }//GEN-LAST:event_jbIngresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbIngresar;
    private javax.swing.JPanel jpLogin;
    private javax.swing.JPasswordField jpfContrasena;
    private javax.swing.JTextField jtfUsuario;
    private javax.swing.JLabel lblLogo;
    // End of variables declaration//GEN-END:variables

}
