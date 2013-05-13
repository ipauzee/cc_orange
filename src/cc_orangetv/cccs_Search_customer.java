/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Search_ticket.java
 *
 * Created on Feb 23, 2010, 4:31:41 PM
 */

package cc_orangetv;


import javax.sun.database.JavaConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author jsm
 */
public class cccs_Search_customer extends javax.swing.JFrame {
    public static String tic1[]=new String[21];
    public static String tic[]=new String[21];
    public static int x=0, Form=0;
    public static String IdCust=null, condition;
    /** Creates new form Search_ticket */
    public cccs_Search_customer() {
        initComponents();
        tblcus.setModel(tabcus);
        tbcus(tblcus,new int []{10,250,200,170,120,120,100,500,500,120,80,500,120,120});
        showDistributor();
    }

    public static ContactCenterOrangeTv CCanj;
    public cccs_Search_customer(ContactCenterOrangeTv ccanj){
        this();
        this.CCanj=ccanj;
    }
    private cccs_ticket Tic;
    public cccs_Search_customer(cccs_ticket tic){
        this();
        this.Tic=tic;
    }
    public static cccs_InBoundCall Inc;
    public cccs_Search_customer(cccs_InBoundCall inc){
        this();
        this.Inc=inc;
    }
    public static cccs_OutBound Obc;
    public cccs_Search_customer(cccs_OutBound obc){
        this();
        this.Obc=obc;
    }
    public static cccs_Sms_income Sms;
    public cccs_Search_customer(cccs_Sms_income sms){
        this();
        this.Sms=sms;
    }
    public static cccs_SMS Sms1;
    public cccs_Search_customer(cccs_SMS sms1){
        this();
        this.Sms1=sms1;
    }
    public static cccs_Email_incoming Mail;
    public cccs_Search_customer(cccs_Email_incoming mail){
        this();
        this.Mail=mail;
    }

      public static javax.swing.table.DefaultTableModel getDefaultTabelticconf(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Id","Distributor","Bike Point Name","Owner","Outlet Phone"      ,"Owner Phone","Birth Date","Original Address","Address","Area"
                        ,"Zip","Note","Agent","Status"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false       ,false,false,false,false,false      
                        ,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }    
    public static javax.swing.table.DefaultTableModel tabcus=getDefaultTabelticconf();
    
    public static void tabelcus(){
        tabcus.setRowCount(0);
        x=0;
             try{
                 sql="select bikepoints.*" +
                      ", distributors.distributor_name as dist" +
                      " from bikepoints" +
                      " left join distributors on bikepoints.distributor_id=distributors.distributor_id" +
                      " where bikepoint_id is not null ";
                 condition="";
                 if(!cbDist.getSelectedItem().equals("--")){
                     condition=condition+" and distributor_name = '"+cbDist.getSelectedItem()+"'";
                 }
                 if(!txtBpName.getText().equals("")){
                     condition=condition+" and bikepoint_name like '%"+txtBpName.getText()+"%'";
                 }
                 if(!txtOwner.getText().equals("")){
                     condition=condition+" and owner like '%"+txtOwner.getText()+"%'";
                 }
                 if(!txtBpAdd.getText().equals("")){
                     condition=condition+" and address like '%"+txtBpAdd.getText()+"%'";
                 }
                 if(!txtPhoneNo.getText().equals("")){
                     condition=condition+" and (outlet_phone like '%"+txtPhoneNo.getText()+"%' or owner_phone like '%"+txtPhoneNo.getText()+"%')";
                 }
                 sql=sql+condition+" order by bikepoint_name";
                 System.out.println("bikepoint : "+sql);
              rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);

            while(rs.next()){
                tic1[x]=rs.getString("bikepoint_id");x++;
                tic1[x]=rs.getString("dist");x++;
                tic1[x]=rs.getString("bikepoint_name");x++;
                tic1[x]=rs.getString("owner");x++;
                tic1[x]=rs.getString("outlet_phone");x++;
                tic1[x]=rs.getString("owner_phone");x++;
                tic1[x]=rs.getString("birth_date");x++;
                tic1[x]=rs.getString("original_address");x++;
                tic1[x]=rs.getString("address");x++;
                tic1[x]=rs.getString("area");x++;
                tic1[x]=rs.getString("zip");x++;
                tic1[x]=rs.getString("note");x++;
                tic1[x]=("");x++;//tic[11]=rs.getString("driver_name");
                tic1[x]=("");x++;//tic[12]=rs.getString("driver_phone");
                tabcus.addRow(tic1);
                x=0;
            }
            lblcaloutcount2.setText(String.valueOf(tabcus.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }  

    private void tbcus(javax.swing.JTable tb, int lebar[]){
        tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
        int kolom=tb.getColumnCount();
        for (int i=0;i<kolom;i++){
            javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(18);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btnoutsrch1 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblcus = new javax.swing.JTable();
        cbDist = new javax.swing.JComboBox();
        jLabel97 = new javax.swing.JLabel();
        lblcaloutcount2 = new javax.swing.JLabel();
        lblrepticcount14 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        txtBpName = new javax.swing.JTextField();
        txtOwner = new javax.swing.JTextField();
        txtBpAdd = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        txtPhoneNo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltic = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Customer");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer"));
        jPanel5.setLayout(null);

        btnoutsrch1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnoutsrch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnoutsrch1.setText("Search");
        btnoutsrch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoutsrch1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnoutsrch1);
        btnoutsrch1.setBounds(820, 40, 99, 24);

        tblcus.setAutoCreateRowSorter(true);
        tblcus.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblcus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Confirm Username", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblcus.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblcus.setRowHeight(20);
        tblcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcusMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tblcus);

        jPanel5.add(jScrollPane22);
        jScrollPane22.setBounds(20, 60, 990, 290);

        cbDist.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbDist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        cbDist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDistActionPerformed(evt);
            }
        });
        jPanel5.add(cbDist);
        cbDist.setBounds(20, 40, 160, 24);

        jLabel97.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel97.setText("Address");
        jPanel5.add(jLabel97);
        jLabel97.setBounds(500, 30, 160, 10);

        lblcaloutcount2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel5.add(lblcaloutcount2);
        lblcaloutcount2.setBounds(930, 50, 40, 10);

        lblrepticcount14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount14.setText("list");
        jPanel5.add(lblrepticcount14);
        lblrepticcount14.setBounds(970, 50, 40, 10);

        jLabel98.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel98.setText("Distributor");
        jPanel5.add(jLabel98);
        jLabel98.setBounds(20, 30, 160, 10);

        jLabel99.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel99.setText("Bikepoint Name");
        jPanel5.add(jLabel99);
        jLabel99.setBounds(180, 30, 160, 10);

        jLabel100.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel100.setText("Owner Name");
        jPanel5.add(jLabel100);
        jLabel100.setBounds(340, 30, 160, 10);

        txtBpName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel5.add(txtBpName);
        txtBpName.setBounds(180, 40, 160, 24);

        txtOwner.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel5.add(txtOwner);
        txtOwner.setBounds(340, 40, 160, 24);

        txtBpAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtBpAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBpAddActionPerformed(evt);
            }
        });
        jPanel5.add(txtBpAdd);
        txtBpAdd.setBounds(500, 40, 160, 24);

        jLabel101.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel101.setText("Phone Number");
        jPanel5.add(jLabel101);
        jLabel101.setBounds(660, 30, 160, 10);

        txtPhoneNo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtPhoneNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNoActionPerformed(evt);
            }
        });
        jPanel5.add(txtPhoneNo);
        txtPhoneNo.setBounds(660, 40, 160, 24);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ticket history"));
        jPanel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel4.setLayout(null);

        tbltic.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tbltic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltic.setRowHeight(18);
        tbltic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblticMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltic);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(18, 31, 990, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("static-access")
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here

    }//GEN-LAST:event_formWindowClosed

    private void btnoutsrch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoutsrch1ActionPerformed
        // TODO add your handling code here:
        tabelcus(); 
    }//GEN-LAST:event_btnoutsrch1ActionPerformed

    private void tblcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcusMouseClicked
        // TODO add your handling code here:
        if(tabcus.getRowCount()!=0){
            if(evt.getClickCount()==1){    
                IdCust=(String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Id"));
                tabeltic();                    
            }else if(evt.getClickCount() == 2){
                switch (Form) {
                    case 0:
                        break;
                    case 1:
                        Tic.txtIdCust.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Id")));
                        Tic.txtGuarantyNo.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Bike Point Name")));
                        Tic.txtCusNam.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Owner")));
                        Tic.txtCusPho.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Outlet Phone")));
                        Tic.txtcustadd.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Address")));
            //            Tic.loid=String.valueOf((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Log ID")));                        
                        break;
                    case 2:
                        Inc.IdCust=(String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Id"));
                        Inc.txtIdCust.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Id")));
                        Inc.tabelcall();
//                        Inc.tabeltic();
                        break;
                    case 3:
                        Sms.IdCust=IdCust;
                        Sms.txtIdCust.setText(IdCust);
                        break;
                    case 31:
                        Sms1.IdCust=IdCust;
                        Sms1.txtIdCust.setText(IdCust);
                        break;
                }
                dispose();
            }            
        }
    }//GEN-LAST:event_tblcusMouseClicked

        private void txtBpAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBpAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBpAddActionPerformed

    private void tblticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblticMouseClicked
        // TODO add your handling code here:
        if (tabtic.getRowCount() != 0) {
            if (evt.getClickCount() == 1) { 
                
            } else if (evt.getClickCount() == 2) {  
                switch (Form) {
                    case 0:
                        break;
                    case 1:
                        Tic.txtIdCust.setText(IdCust);
                        Tic.txtGuarantyNo.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Bike Point Name")));
                        Tic.txtCusNam.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Owner")));
                        Tic.txtCusPho.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Outlet Phone")));
                        Tic.txtcustadd.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Address")));
            //            Tic.loid=String.valueOf((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Log ID")));
                        break;
                    case 2:
                        Inc.IdCust=IdCust;
                        Inc.txtIdCust.setText(IdCust);
                        Inc.txtnotic.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(),tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket No.")));
                        Inc.ticid=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(),tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket Id")));
                        Inc.tabelcall();
//                        Inc.tabeltic();
                        break;
                    case 3:
                        Sms.IdCust=IdCust;
                        Sms.txtIdCust.setText(IdCust);
                        Sms.txtnotic.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(),tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket No.")));                        
                        break;
                    case 31:
                        Sms1.IdCust=IdCust;
                        Sms1.txtIdCust.setText(IdCust);
                        Sms1.txtnotic.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(),tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket No.")));                        
                        break;
                }
                dispose();
            }       
        }     
    }//GEN-LAST:event_tblticMouseClicked

    private void cbDistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDistActionPerformed

    private void txtPhoneNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNoActionPerformed

        public static javax.swing.table.DefaultTableModel getDefaultTicHist(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Ticket No.","Priority","Source","Type","Status"
                        ,"Open By","Product","Department","Assign Dept.","Assign User"
                        ,"Category","Bussiness Line","Guaranty Number","Cust Name","Cust Phone number"
                        ,"PIC Name","Details","Solution","Ticket Id"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel tabtic=getDefaultTicHist();
    public static void tabeltic(){
        try{
            x=0;
            sql="select tickets.*"
                      + ", a.dept_name as opdept "
                      + ", b.dept_name as asdept"
                      + ", _ticketstatus.data as stt"
                      + ", _ticketpriority.data as prior"
                      + ", c.data as source"
                      + ", d.data as bussline"
                      + " from tickets "
                      + " join _department a on tickets.dept_id=a.dept_id"
                      + " left join _department b on tickets.assign_dept=b.dept_id"
                      + " join _ticketstatus on tickets._status=_ticketstatus.code"
                      + " join _ticketpriority on tickets._priority=_ticketpriority.code"
                      + " join _callertype c on tickets.source_id=c.code"
                      + " join _bussinesline d on tickets.bussiness_line_id=d.code"
                      + " where bikepoint_id="+IdCust+" or cust_phone like '%"+txtPhoneNo.getText()+"%'";
            rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
//              System.out.println(sql);

            while(rs.next()){
                tic[x]=rs.getString("ticket_no");x++;
                tic[x]=rs.getString("prior");x++;
                tic[x]=rs.getString("source");x++;
                tic[x]=rs.getString("_type");x++;
                tic[x]=rs.getString("stt");x++;

                tic[x]=rs.getString("open_username");x++;
                tic[x]=rs.getString("product");x++;
                tic[x]=rs.getString("opdept");x++;
                tic[x]=rs.getString("asdept");x++;
                tic[x]=rs.getString("assign_username");x++;

                tic[x]=rs.getString("category");x++;
                tic[x]=rs.getString("bussline");x++;
                tic[x]=rs.getString("bikepoint_id");x++;
                tic[x]=rs.getString("cust_name");x++;
                tic[x]=rs.getString("cust_phone");x++;

                tic[x]=rs.getString("pic_name");x++;
                tic[x]=rs.getString("details");x++;
                tic[x]=rs.getString("solution");x++;
                tic[x]=rs.getString("ticket_id");x++;
                tabtic.addRow(tic);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
   
    /**
    * @param args the command line arguments
    */

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cccs_Search_customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnoutsrch1;
    public static javax.swing.JComboBox cbDist;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JLabel lblcaloutcount2;
    private javax.swing.JLabel lblrepticcount14;
    private javax.swing.JTable tblcus;
    private javax.swing.JTable tbltic;
    public static javax.swing.JTextField txtBpAdd;
    public static javax.swing.JTextField txtBpName;
    public static javax.swing.JTextField txtOwner;
    public static javax.swing.JTextField txtPhoneNo;
    // End of variables declaration//GEN-END:variables

    public static String sql;
    public static ResultSet rs;
//    private JavaConnector jconn=new JavaConnector();
//    private Connection conn;
    
    private void showDistributor()    {
         try{
             cbDist.removeAllItems();
             sql="select distributor_name from distributors where _deleted=0 ";
             rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
             while(rs.next()){
                 cbDist.addItem(rs.getString(1));
             }
             cbDist.addItem("--");
             cbDist.setSelectedItem("--");
         }catch(Exception e){
             System.out.println(e);
         }
     }
}
