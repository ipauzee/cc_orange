/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * cccs_Mssg.java
 *
 * Created on Apr 29, 2010, 4:03:43 PM
 */

package cc_orangetv;

import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 *
 * @author jsm
 */
public class cccs_Mssg extends javax.swing.JFrame {
    private boolean hide;
    private String tu="";
    int index;
    private String opdt;
    private String optm;

    public static StringBuffer toSend = new StringBuffer("");

    /** Creates new form cccs_Mssg */
    public cccs_Mssg() {
        initComponents();
        jScrollPane2.setVisible(false);
        setLocation(400,200);
        setSize(330,370);
        getagent();
//        setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
        hide=true;
    }

    public static ContactCenterOrangeTv CCanj;
    public cccs_Mssg(ContactCenterOrangeTv ccanj){
        this();
        this.CCanj=ccanj;
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
        txttu = new javax.swing.JTextField();
        btnhide = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmsg = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compose new message");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        txttu.setEditable(false);
        txttu.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txttu.setText("<none>");
        txttu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttuCaretUpdate(evt);
            }
        });
        jPanel1.add(txttu);
        txttu.setBounds(20, 20, 260, 24);

        btnhide.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnhide.setText("jButton1");
        btnhide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhideActionPerformed(evt);
            }
        });
        jPanel1.add(btnhide);
        btnhide.setBounds(280, 20, 30, 22);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList1MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(20, 40, 290, 260);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("to :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 0, 100, 20);

        txtmsg.setColumns(20);
        txtmsg.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtmsg.setRows(5);
        txtmsg.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtmsgCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(txtmsg);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 290, 240);

        jButton1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(220, 310, 80, 22);

        jButton2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton2.setText("Send");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(140, 310, 80, 22);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("message :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 40, 100, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 330, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
//        try {
//            conn.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }//GEN-LAST:event_formWindowClosed

    private void txttuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttuCaretUpdate
        // TODO add your handling code here:
        if(!txttu.getText().equals("<None>") || !txtmsg.getText().equals("") && !txttu.getText().equals("<None>")){
            jButton2.setEnabled(true);
        }
}//GEN-LAST:event_txttuCaretUpdate

    private void btnhideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhideActionPerformed
        // TODO add your handling code here:
        if(jScrollPane2.isShowing()==true){
            jScrollPane2.setVisible(false);
        }else{
            jScrollPane2.setVisible(true);
        }
}//GEN-LAST:event_btnhideActionPerformed
String agent;
Object sel;int baris;int conter;
    private void jList1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseReleased
        // TODO add your handling code here:
        //        countagent=0;
        //        sel=null;
        agent="";
        int[] selectedIx = jList1.getSelectedIndices();
        for (int i=0; i<selectedIx.length; i++) {
            //            countagent++;
            sel = jList1.getModel().getElementAt(selectedIx[i]);
            if(agent.equals("")){
                agent=agent+(String.valueOf(sel));
            }else{
                agent=agent+","+(String.valueOf(sel));
            }
        }
        if(agent.equals("")){
            agent=agent+"<None>";
        }
        txttu.setText(String.valueOf(agent));
}//GEN-LAST:event_jList1MouseReleased

    private void txtmsgCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtmsgCaretUpdate
        // TODO add your handling code here:
        if(!txttu.getText().equals("<None>") || !txtmsg.getText().equals("") && !txttu.getText().equals("<None>")){
            jButton2.setEnabled(true);
        }
}//GEN-LAST:event_txtmsgCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(txttu.getText().equals("<none>")){
            JOptionPane.showMessageDialog(null, "Fill data first", "Messaging",JOptionPane.WARNING_MESSAGE);
        }else{
            opdt();
            optm();
            int p;
            int coma;
            String nm;
            tu = txttu.getText();
            //        coma = tu.
            index = tu.indexOf(',');
            System.out.print("\n index = "+index);
            p = tu.length();
            while (index != -1) {
                nm = tu.substring(0, index);
                tu = tu.substring(index + 1, p);
                sql="insert into msg_inbox (msg_date,msg_time,msg_from,msg_to,msg_text) values ('"+opdt+"','"+optm+"','"+CCanj.lbluser.getText()+"','"+nm+"','"+txtmsg.getText()+"')";
                CCanj.jconn.SQLExecute(sql, CCanj.conn);
                sql1="insert into msg_outbox (msg_date,msg_time,msg_from,msg_to,msg_text) values ('"+opdt+"','"+optm+"','"+CCanj.lbluser.getText()+"','"+nm+"','"+txtmsg.getText()+"')";
                CCanj.jconn.SQLExecute(sql1, CCanj.conn);
                index = tu.indexOf(',');
                p = tu.length();
                System.out.print("\n index dalam while = "+index);
                String s = "MSG|"+nm+"\r\n";
                sendString(s);
                CCanj.outbroad.print(toSend); CCanj.outbroad.flush();
                toSend.setLength(0);
                s=null;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
            System.out.print("\n index setelah while = "+index);
            if(index==-1){
                sql="insert into msg_inbox (msg_date,msg_time,msg_from,msg_to,msg_text) values ('"+opdt+"','"+optm+"','"+CCanj.lbluser.getText()+"','"+tu+"','"+txtmsg.getText()+"')";
                CCanj.jconn.SQLExecute(sql, CCanj.conn);
                sql1="insert into msg_outbox (msg_date,msg_time,msg_from,msg_to,msg_text) values ('"+opdt+"','"+optm+"','"+CCanj.lbluser.getText()+"','"+tu+"','"+txtmsg.getText()+"')";
                CCanj.jconn.SQLExecute(sql1, CCanj.conn);
                String s = "MSG|"+tu+"\r\n";
                sendString(s);
                CCanj.outbroad.print(toSend); CCanj.outbroad.flush();
                toSend.setLength(0);
                s=null;
                dispose();
                JOptionPane.showMessageDialog(null, "Messages sent", "Messaging",JOptionPane.WARNING_MESSAGE);
            }
        }
        dispose();
        CCanj.tabelmsin();
        CCanj.tabelmsou();
}//GEN-LAST:event_jButton2ActionPerformed

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
                new cccs_Mssg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhide;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtmsg;
    public static javax.swing.JTextField txttu;
    // End of variables declaration//GEN-END:variables

    private String sql;
    private String sql1;
    private ResultSet rs;
//    private JavaConnector jconn=new JavaConnector();
//    private Connection conn;

    private void opdt(){
          try{
              sql="select CURRENT_DATE";
              rs = CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
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
              rs = CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
              while(rs.next()){
                  optm=rs.getString(1);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
        }

    private void getagent(){
        DefaultListModel listModel = new DefaultListModel();
        jList1.setModel(listModel);
        try{
            int x=0;
            sql="select username from user_account";
//            condition="";
//            if(Log.level==0){
//                condition=condition+" where _level > 0 ";
//            }
//            sql=sql+condition;
            rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            while(rs.next()){
                listModel.addElement(rs.getString(1).toString());
            }
        } catch (Exception e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null,e.getMessage().toString(),"error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
