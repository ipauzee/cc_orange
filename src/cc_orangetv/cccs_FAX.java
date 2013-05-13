/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Fax.java
 *
 * Created on Apr 10, 2010, 12:16:59 PM
 */

package cc_orangetv;

import java.net.*;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sun.database.JavaConnector;
import javax.swing.ImageIcon;
import java.io.*;
import java.io.Writer;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.JOptionPane;
/**
 *
 * @author jsm
 */
public class cccs_FAX extends javax.swing.JFrame {
    String s;
    public static int z;
    public static int zz;
    public static int fid=0;
    private String usrlvl;
    private String opdt;
    private String optm;
    public static int statuss;
    public static int id;
    private String notic;
    public static boolean faxtic = false;


   public static ServerSocket hostServer = null;
   public static ServerSocket hostServer1 = null;
   public static Socket socket = null;
   public static Socket socket1 = null;
   public static BufferedReader in0 = null;
   public static BufferedReader in1 = null;
   public static PrintWriter out1 = null;

   // Connection atate info
   public static String IPtele = "localhost";
   public static int porttele = 601;
   public static String IPbroad = "192.168.0.48";
   public static int portbroad = 23;
  // public static int connectionStatus = DISCONNECTED;
   public static boolean isHost = true;
   public static StringBuffer toSend = new StringBuffer("");
   public static PrintWriter out = null;
    public static int status;
    public static int asdep;
    /** Creates new form Fax */
    public cccs_FAX() {
        initComponents();
//        conn=(Connection) jconn.ConnectToMySQL("localhost","cc_anj","cc_anj","123jaring456");
        conn=(Connection) jconn.ConnectToMySQL("192.168.0.32","cc_anj","cc_anj","123jaring456");
        setSize(1024,768);
    }

    public static ContactCenterOrangeTv CCanj;
    public cccs_FAX(ContactCenterOrangeTv ccanj){
        this();
        this.CCanj=ccanj;
    }
    public static cccs_Search_ticket Sct;
    public cccs_FAX(cccs_Search_ticket sct){
        this();
        this.Sct=sct;
    }
    private cccs_ticket Tic;
    public cccs_FAX(cccs_ticket tic){
        this();
        this.Tic=tic;
    }
    private cccs_Fax_incoming Fin;
    public cccs_FAX(cccs_Fax_incoming fin){
        this();
        this.Fin=fin;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmsg = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        txtcto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnotic = new javax.swing.JTextField();
        btnsrchcus = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        btnsend = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtdocname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FAX");
        setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);

        txtmsg.setColumns(20);
        txtmsg.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtmsg.setRows(5);
        jScrollPane1.setViewportView(txtmsg);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 1000, 680);

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel15.setText("To :");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(10, 10, 50, 20);

        txtcto.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtcto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtctoActionPerformed(evt);
            }
        });
        jPanel1.add(txtcto);
        txtcto.setBounds(60, 10, 300, 20);

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ticket No.");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(360, 10, 100, 20);

        txtnotic.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jPanel1.add(txtnotic);
        txtnotic.setBounds(460, 10, 100, 20);

        btnsrchcus.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btnsrchcus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnsrchcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsrchcusMouseClicked(evt);
            }
        });
        jPanel1.add(btnsrchcus);
        btnsrchcus.setBounds(560, 10, 20, 20);

        btnexit.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btnexit.setText("Cancel");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });
        jPanel1.add(btnexit);
        btnexit.setBounds(900, 710, 100, 20);

        btnsend.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btnsend.setText("SEND");
        btnsend.setEnabled(false);
        btnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendActionPerformed(evt);
            }
        });
        jPanel1.add(btnsend);
        btnsend.setBounds(790, 710, 100, 20);

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Doc Name");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(580, 10, 100, 20);

        txtdocname.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtdocname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdocnameActionPerformed(evt);
            }
        });
        jPanel1.add(txtdocname);
        txtdocname.setBounds(680, 10, 330, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1030, 740);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtctoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtctoActionPerformed

    private void btnsrchcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsrchcusMouseClicked
        // TODO add your handling code here:
        cccs_Search_ticket st = new cccs_Search_ticket();
        st.setVisible(true);
}//GEN-LAST:event_btnsrchcusMouseClicked

    private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsendActionPerformed
        try {
            // TODO add your handling code here:
            opdt();
            optm();
            stt();
            Writer writer = null;
            String text = txtmsg.getText();
            File file = new File("Z:/localhost/incoming/" + txtdocname.getText() + ".txt");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
            sql = "insert into log_fax (recipient,username,doc_name,_direction,ticket_id) values ('" + txtcto.getText() + "','" + CCanj.lbluser.getText() + "','" + txtdocname.getText()+".txt',1,'" + id + "')";
//        sql1="insert into log_mail (mail_from,mail_to,mail_subject,mail_text,ticket_id,direction,username) values ('contact@anjrent.com','"+txtcsomail.getText()+"','<Ticket>#"+txtcusnam.getText()+"#"+cbnoplat.getSelectedItem()+"#"+txtusr.getText()+"#"+cbcategory.getSelectedItem()+"#"+txtnotic.getText()+"','"+txtdetails.getText()+"\n\n"+txtsolution.getText()+" \n\n UPDATED','"+id+"',1,'"+CCanj.lbluser.getText()+"')";
            jconn.SQLExecute(sql, conn);
            connect();
            s = "FAX|OUTBOUND\r\n";
            sendString(s);
            out1.print(toSend);
            out1.flush();
            toSend.setLength(0);
            JOptionPane.showMessageDialog(null, "FAX SENT", "MAILING", JOptionPane.WARNING_MESSAGE);
            cleanUp();
            if (faxtic == true) {
                if (status == 0 && asdep == 0) {
                    sql = "update tickets set _status=1, process_date='" + opdt + "', process_time='" + optm + "', process_username='" + CCanj.lbluser.getText() + "' where ticket_id='" + Tic.id + "' and ticket_no='" + txtnotic.getText() + "'";
                    jconn.SQLExecute(sql, conn);
                    status = 1;
                }
                sql1 = "insert into log_tickets (log_date,log_time,username,user_level,ticket_id,_status,info,ticket_no) values ('" + opdt + "','" + optm + "','" + CCanj.lbluser.getText() + "','" + usrlvl + "','" + Tic.id + "','" + status + "','Outgoing Fax \nTo : " + txtcto.getText() + " \n\n " + txtmsg.getText() + "','" + txtnotic.getText() + "')";
                jconn.SQLExecute(sql1, conn);
                System.out.print(sql1);
            }
            faxtic = false;
        } catch (IOException ex) {
            Logger.getLogger(cccs_FAX.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnsendActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnexitActionPerformed

    private void txtdocnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdocnameActionPerformed
        // TODO add your handling code here:
        if(txtdocname.getText().equals("")){
            btnsend.setEnabled(false);
        }else{
            btnsend.setEnabled(true);
        }
}//GEN-LAST:event_txtdocnameActionPerformed

       private static void sendString(String s) {
         synchronized (toSend) {
             toSend.append(s + "\r\n");
      }
   }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cccs_FAX().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnsend;
    private javax.swing.JButton btnsrchcus;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField txtcto;
    public static javax.swing.JTextField txtdocname;
    private javax.swing.JTextArea txtmsg;
    public static javax.swing.JTextField txtnotic;
    // End of variables declaration//GEN-END:variables

    public static String sql;
    public static String sql1;
    public static ResultSet rs;
    public static JavaConnector jconn=new JavaConnector();
    public static Connection conn;


              private void connect()  {
            try {
               socket1 = new Socket(IPbroad, portbroad);
               in1 = new BufferedReader(new
               InputStreamReader(socket1.getInputStream()));
               out1 = new PrintWriter(socket1.getOutputStream(), true);
//               System.out.print(socket1);
            }
            // If error, clean up and output an error message
            catch (IOException e) {
            System.out.print("OooPss... Br0adcastEr didNt wOrk...");}
    }
                 private static void cleanUp() {
      try {
         if (hostServer != null) {
            hostServer.close();
            hostServer = null;
         }
      }
      catch (IOException e) { hostServer = null; }

      try {
         if (socket1 != null) {
            socket1.close();
            socket1 = null;
         }
      }
      catch (IOException e) { socket = null; }

      try {
         if (in0 != null) {
            in0.close();
            in0 = null;
         }
      }
      catch (IOException e) { in1 = null; }

      if (out1 != null) {
         out1.close();
         out1 = null;
      }
   }
                 private void opdt(){
          try{
              sql="select CURRENT_DATE";
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  opdt=rs.getString(1);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
    }

        private void optm(){
            try{
              sql="select CURRENT_TIME";
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  optm=rs.getString(1);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
        }
        private void usrlvl(){
                try{
                    sql="select _level from user_account where username='"+CCanj.lbluser.getText()+"'";
                    rs=jconn.SQLExecuteRS(sql, conn);
                    while(rs.next()){
                        usrlvl=rs.getString(1);
                    }
                }catch(Exception e){
                    System.out.print(e);
                }
            }
        private void stt(){
                try{
                    sql="select _status, assign_dept from tickets where ticket_id='"+Tic.id+"' and ticket_no='"+txtnotic.getText()+"'";
                    rs=jconn.SQLExecuteRS(sql, conn);
                    while(rs.next()){
                        status=Integer.parseInt(rs.getString(1));
                        asdep=Integer.parseInt(rs.getString(2));
                    }
                }catch(Exception e){
                    System.out.print(e);
                }
            }
}
