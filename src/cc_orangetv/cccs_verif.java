/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * cccs_verif.java
 *
 * Created on Jun 27, 2012, 11:28:13 AM
 */
package cc_orangetv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author jsm
 */
public class cccs_verif extends javax.swing.JFrame {
    public static String IdCust,dob;

    /** Creates new form cccs_verif */
    public cccs_verif() {
        initComponents();
    }

    public static ContactCenterOrangeTv CCanj;
    public cccs_verif(ContactCenterOrangeTv ccanj){
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbDistributor = new javax.swing.JComboBox();
        txtBikePoint = new javax.swing.JTextField();
        txtOwner = new javax.swing.JTextField();
        txtBikePoint1 = new javax.swing.JTextField();
        txtBikePoint2 = new javax.swing.JTextField();
        txtOwnerPho = new javax.swing.JTextField();
        txtArea = new javax.swing.JTextField();
        txtOutletPho = new javax.swing.JTextField();
        txtZip = new javax.swing.JTextField();
        dtBirth = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAddressOri = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btncall = new javax.swing.JButton();
        btncall1 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(jLabel1.getFont().deriveFont((float)11));
        jLabel1.setText("Original Address");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 130, 100, 20);

        jLabel2.setFont(jLabel2.getFont().deriveFont((float)11));
        jLabel2.setText("Distributor");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 10, 100, 20);

        jLabel3.setFont(jLabel3.getFont().deriveFont((float)11));
        jLabel3.setText("Bike Point Name");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 30, 100, 20);

        jLabel4.setFont(jLabel4.getFont().deriveFont((float)11));
        jLabel4.setText("Owner");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 50, 100, 20);

        jLabel5.setFont(jLabel5.getFont().deriveFont((float)11));
        jLabel5.setText("Outlet Phone");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 70, 100, 20);

        jLabel6.setFont(jLabel6.getFont().deriveFont((float)11));
        jLabel6.setText("Owner Phone");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 90, 100, 20);

        jLabel7.setFont(jLabel7.getFont().deriveFont((float)11));
        jLabel7.setText("Address");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 190, 100, 20);

        jLabel8.setFont(jLabel8.getFont().deriveFont((float)11));
        jLabel8.setText("Area");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 270, 100, 20);

        jLabel9.setFont(jLabel9.getFont().deriveFont((float)11));
        jLabel9.setText("Zip");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 290, 100, 20);

        jLabel10.setFont(jLabel10.getFont().deriveFont((float)11));
        jLabel10.setText("Note");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(10, 320, 100, 20);

        jLabel13.setFont(jLabel13.getFont().deriveFont((float)11));
        jLabel13.setText("Brith Date");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(10, 110, 100, 20);

        cbDistributor.setFont(cbDistributor.getFont().deriveFont((float)11));
        jPanel1.add(cbDistributor);
        cbDistributor.setBounds(120, 10, 270, 24);

        txtBikePoint.setFont(txtBikePoint.getFont().deriveFont((float)11));
        jPanel1.add(txtBikePoint);
        txtBikePoint.setBounds(120, 30, 270, 24);

        txtOwner.setFont(txtOwner.getFont().deriveFont((float)11));
        jPanel1.add(txtOwner);
        txtOwner.setBounds(120, 50, 270, 24);
        jPanel1.add(txtBikePoint1);
        txtBikePoint1.setBounds(120, 30, 270, 24);
        jPanel1.add(txtBikePoint2);
        txtBikePoint2.setBounds(120, 30, 270, 24);

        txtOwnerPho.setFont(txtOwnerPho.getFont().deriveFont((float)11));
        jPanel1.add(txtOwnerPho);
        txtOwnerPho.setBounds(120, 90, 220, 24);

        txtArea.setFont(txtArea.getFont().deriveFont((float)11));
        jPanel1.add(txtArea);
        txtArea.setBounds(120, 270, 270, 24);

        txtOutletPho.setFont(txtOutletPho.getFont().deriveFont((float)11));
        jPanel1.add(txtOutletPho);
        txtOutletPho.setBounds(120, 70, 220, 24);

        txtZip.setFont(txtZip.getFont().deriveFont((float)11));
        jPanel1.add(txtZip);
        txtZip.setBounds(120, 290, 270, 24);

        dtBirth.setDateFormatString("ddMMyyyy");
        dtBirth.setFont(dtBirth.getFont().deriveFont((float)11));
        jPanel1.add(dtBirth);
        dtBirth.setBounds(120, 110, 120, 24);

        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtAddress.setLineWrap(true);
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(120, 200, 270, 70);

        txtAddressOri.setColumns(20);
        txtAddressOri.setEditable(false);
        txtAddressOri.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtAddressOri.setLineWrap(true);
        txtAddressOri.setRows(5);
        jScrollPane3.setViewportView(txtAddressOri);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(120, 130, 270, 70);

        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtNote.setLineWrap(true);
        txtNote.setRows(5);
        jScrollPane4.setViewportView(txtNote);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(120, 310, 270, 70);

        btncall.setFont(new java.awt.Font("Calibri", 1, 12));
        btncall.setForeground(new java.awt.Color(0, 153, 51));
        btncall.setText("Call");
        btncall.setEnabled(false);
        jPanel1.add(btncall);
        btncall.setBounds(340, 70, 50, 24);

        btncall1.setFont(new java.awt.Font("Calibri", 1, 12));
        btncall1.setForeground(new java.awt.Color(0, 153, 51));
        btncall1.setText("Call");
        btncall1.setEnabled(false);
        btncall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncall1ActionPerformed(evt);
            }
        });
        jPanel1.add(btncall1);
        btncall1.setBounds(340, 90, 50, 24);

        btnSave.setFont(new java.awt.Font("Calibri", 1, 12));
        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave);
        btnSave.setBounds(250, 380, 70, 24);

        btnCancel.setFont(new java.awt.Font("Calibri", 1, 12));
        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel);
        btnCancel.setBounds(320, 380, 65, 24);

        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 11));
        lblStatus.setText("Status");
        jPanel1.add(lblStatus);
        lblStatus.setBounds(120, 380, 100, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void btncall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncall1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncall1ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(cccs_verif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cccs_verif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cccs_verif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cccs_verif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new cccs_verif().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCancel;
    public static javax.swing.JButton btnSave;
    public static javax.swing.JButton btncall;
    public static javax.swing.JButton btncall1;
    public static javax.swing.JComboBox cbDistributor;
    public static com.toedter.calendar.JDateChooser dtBirth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblStatus;
    public static javax.swing.JTextArea txtAddress;
    public static javax.swing.JTextArea txtAddressOri;
    public static javax.swing.JTextField txtArea;
    public static javax.swing.JTextField txtBikePoint;
    public static javax.swing.JTextField txtBikePoint1;
    public static javax.swing.JTextField txtBikePoint2;
    public static javax.swing.JTextArea txtNote;
    public static javax.swing.JTextField txtOutletPho;
    public static javax.swing.JTextField txtOwner;
    public static javax.swing.JTextField txtOwnerPho;
    public static javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables
    public static String sql;
    public static ResultSet rs;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static Date dtdt;
            
    public static void open(){
        sql="select bikepoints.*" +
                ", distributors.distributor_name as dist" +
                " from bikepoints" +
                " left join distributors on bikepoints.distributor_id=distributors.distributor_id" +
                " where bikepoint_id='"+IdCust+"'";
        rs = CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
        try {
            while (rs.next()) {
                cbDistributor.addItem(rs.getString("dist"));
                txtBikePoint.setText(rs.getString("bikepoint_name"));
                txtOwner.setText(rs.getString("owner"));
                txtOutletPho.setText(rs.getString("outlet_phone"));
                txtOwnerPho.setText(rs.getString("owner_phone"));
                dob=rs.getString("birth_date");                
                txtAddressOri.setText(rs.getString("original_address"));
                txtAddress.setText(rs.getString("address"));
                txtArea.setText(rs.getString("area"));
                txtZip.setText(rs.getString("zip"));
                txtNote.setText(rs.getString("note"));
            }
            try {
                dtdt = sdf.parse(dob.substring(8, 10) + "/" + dob.substring(5, 7) + "/" + dob.substring(0, 4));
                dtBirth.setDate(dtdt);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}