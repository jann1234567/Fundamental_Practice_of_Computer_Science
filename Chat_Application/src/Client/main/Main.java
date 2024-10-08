/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client.main;

import Client.event.PublicEvent;
import Client.swing.ComponentResizer;
import DAOs.DatabaseManager;
import DAOs.UserDAO;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.Socket;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main extends javax.swing.JFrame {
    
    
    private Animator animatorLogin;
    private boolean signIn;
    private static String localUser;
    List<String> Usernames = new ArrayList<>();
    private  Socket localSocket;

    public Main() {
        System.out.println("runing init component login");
        initComponentsLogin();
        System.out.println("runing init component home");
        initComponentHome();
        System.out.println("runing init component init");
        init();
        setupWindowListener();  //detect is application closed

        getContentPane().setBackground(new Color(245,245,245));
        TimingTarget targetLogin= new TimingTargetAdapter(){
            public void timingEvent(float fraction) {
            background1.setAnimate(fraction);
            }
            
            public void end(){
                panelLogin.setVisible(false);
                background1.setShowPaint(true);
                panelBody.setVisible(true);
            }
        };
        animatorLogin = new Animator(500,targetLogin);
        animatorLogin.setResolution(0);
    }

    private void setupWindowListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {  //run when user close the application
                userOffline();
            }
        });
    }

    private void userOffline() {
        try {
            if (localSocket != null && !localSocket.isClosed()) {
                DataOutputStream out = new DataOutputStream(localSocket.getOutputStream());
                out.writeUTF(Main.getLocalUser()); // Send the username 
                out.writeUTF("OFFLINE"); // pass user status
                out.flush();
                localSocket.close(); // Close the socket
                System.out.println(Main.getLocalUser() + " disconnected from server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/Client/icon/icon.png")).getImage());
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMinimumSize(new Dimension(800, 500));
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));
        home.setVisible(true);
        //initEvent(); 
    }

    private void initEvent() {
        home.setVisible(true);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponentsLogin() {

        background1 = new Client.swing.Background();
        panelLogin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmdSignIn = new Client.swing.Button();
        txtUser = new Client.swing.TextField();
        txtPort = new Client.swing.PortField();
        txtServerIp = new Client.swing.ServerIpField();
        panelBody = new Client.swing.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background1.setLayout(new java.awt.CardLayout());

        panelLogin.setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/icon/icon.png"))); // NOI18N

        cmdSignIn.setBackground(new java.awt.Color(157, 153, 255));
        cmdSignIn.setForeground(new java.awt.Color(255, 255, 255));
        cmdSignIn.setText("Sign In");
        cmdSignIn.setEffectColor(new java.awt.Color(200, 198, 255));
        cmdSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSignInActionPerformed(evt);
            }
        });

        txtUser.setBackground(new java.awt.Color(245, 245, 245));
        txtUser.setLabelText("User Name");
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        txtPort.setBackground(new java.awt.Color(245, 245, 245));
        txtPort.setLabelText("Port");
        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortActionPerformed(evt);
            }
        });

        txtServerIp.setBackground(new java.awt.Color(245, 245, 245));
        txtServerIp.setLabelText("Server's Ip");
        txtServerIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServerIpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(44, 44, 44)))
                    .addComponent(txtServerIp, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(cmdSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtServerIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        background1.add(panelLogin, "card2");

        panelBody.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                panelBodyPropertyChange(evt);
            }
        });

        

        background1.add(panelBody, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentHome() {
        home = new Client.form.Home();
        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, 1242, Short.MAX_VALUE)
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBodyLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

    }
    

    private void enableLogin(boolean action) {
        txtUser.setEditable(action);
        txtPort.setEditable(action);
        cmdSignIn.setEnabled(action);
    }


  
    private void cmdSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSignInActionPerformed
        if (!animatorLogin.isRunning()) {
            signIn = true;
            String user = txtUser.getText().trim();
            String port = txtPort.getText();
            String serverip= txtServerIp.getText();
            boolean action = true;
            if (user.equals("")) {
                txtUser.setHelperText("Please input user name");
                txtUser.grabFocus();
                action = false;
            }
            if (port.equals("")) {
                txtPort.setHelperText("Please input port");
                if (action) {
                    txtPort.grabFocus();
                }
                action = false;
            }
            if (serverip.equals("")) {
                txtServerIp.setHelperText("Please input server's ip");
                if (action) {
                    txtServerIp.grabFocus();
                }
                action = false;
            }
            if (action) {
                setLocalUser(user);
                System.out.println("local user: " + Main.localUser);    //store local username 
                animatorLogin.start();
                enableLogin(false);
                
                try {                    
                    System.out.println("Connecting to Server");
                    Socket socket = new Socket(serverip, Integer.parseInt(port));   //connect to server
                    localSocket = socket;
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
                    out.writeUTF(user); //pass username to server
                    out.writeUTF("ONLINE"); //pass user status to server 
                    out.flush(); 
                    System.out.println("Connected to server");
                    
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
                home = new Client.form.Home();
            }
        }
           // TODO add your handling code here:
    }//GEN-LAST:event_cmdSignInActionPerformed

    public void setLocalUser(String username) {
        System.out.println("set local user");
        Main.localUser = username;
    }
    public static String getLocalUser() {
        return Main.localUser;
    }


    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortActionPerformed
        
    }//GEN-LAST:event_txtPortActionPerformed

    private void panelBodyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_panelBodyPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBodyPropertyChange

    private void txtServerIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServerIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServerIpActionPerformed
   
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Client.swing.Background background1;
    private Client.swing.Button cmdSignIn;
    private Client.form.Home home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private Client.swing.PanelTransparent panelBody;
    private javax.swing.JPanel panelLogin;
    private Client.swing.PortField txtPort;
    private Client.swing.ServerIpField txtServerIp;
    private Client.swing.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
