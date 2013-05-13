/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContactCenterCastrol.java
 *
 * Created on Feb 15, 2010, 9:41:37 AM
 */

package cc_orangetv;

import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Event.*;
import java.sql.*;
import javax.sun.database.JavaConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.lang.Boolean;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.net.*;
import javax.swing.JFileChooser;

import jxl.*;
import jxl.write.*;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.UIManager.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jsm
 */
public class ContactCenterOrangeTv extends javax.swing.JInternalFrame {

    public static int counter=0,c=0,m=0,sm=0,fx=0,tt=0,x=0;
    public static boolean inshow=false,outshow=false,ticshow=false,outbound=false,asshow=false;
    public static String s,loid,callid;
    public static boolean teleOn=false, uploOn=false, uploOn1=false, brcaOn=false, raction=false;
    long elapsed;
    
    // TCP Components
   public static ServerSocket hostServer = null;
   public static ServerSocket hostServer1 = null;
   public static Socket sockettele = null;
   public static Socket socketbroad = null;
   public static Socket socketupload = null;
   public static Socket socketupload1 = null;
   public static BufferedReader intele = null;
   public static BufferedReader inbroad = null;
   public static BufferedReader inupload = null;
   public static BufferedReader inupload1 = null;
   public static PrintWriter outtele = null;
   public static PrintWriter outbroad = null;
   public static PrintWriter outupload = null;
   public static PrintWriter outupload1 = null;
   
   // Connection atate info
   public static String IPtele = "localhost";
   public static int porttele = 6023;
   public static String IPbroad = "192.168.0.45";
   public static int portbroad = 23;
  // public static int connectionStatus = DISCONNECTED;
   public static boolean isHost = true;
   //public static String statusString = statusMessages[connectionStatus];
   public static StringBuffer toAppend = new StringBuffer("");
   public static StringBuffer toSend = new StringBuffer("");

    /** Creates new form ContactCenterCastrol */
    public static String in[]=new String[40];
    public static String ou[]=new String[17];
    public static String in2[]=new String[15];
    public static String in3[]=new String[15];
    public static String ou1[]=new String[15];
    public static String ou3[]=new String[15];
    public static String tic[]=new String[30];
    public static String reptic[]=new String[100];
    public static String repcal[]=new String[40];
    public static String repsms[]=new String[20];
    public static String repmail[]=new String[20];
    public static String repfax[]=new String[20];
    public static String act[]=new String[7];
    public static String hoin[]=new String[20];
    public static String hoou[]=new String[20];
    public static String perfin[]=new String[20];
    public static String perfou[]=new String[20];
    public static String dayin[]=new String[20];
    public static String dayou[]=new String[20];
    String date;
    String oldtext;
    String scroltext;
    String newtext;
    String pass=null;
    String user=null;
    public static String pabx;
    public static String in_ext;
    public static String out_ext;
    public static String lt;
    public static String ld;
    public static int v;
    Timer broad;
    Timer inbo;
    Timer receiv;
    Timer Scrol;
    public static Timer msg;
    private int usrlvl;
    public static String msn[]=new String[6];
    public static String msu[]=new String[6];
    private String msgidin;
    private String msgidou;
    private String tu;

    public ContactCenterOrangeTv() {
        
        initComponents();
        conn=Log.conn;jconn=Log.jconn;
        
        setSize(1020,750);
        usrlvl();
        if(usrlvl==0){
            jtab.setEnabledAt(6, false);
        }
        currentdate();
        tblin.setModel(tabin);
        tblout.setModel(tabou);
        tbltic.setModel(tabtic);
        tblcus.setModel(tabticconf);
        tblact.setModel(tabact);
        tblmin.setModel(tabmin);
        tblmou.setModel(tabmou);
        tblsin.setModel(tabsin);
        tblsou.setModel(tabsou);
        tblfin.setModel(tabfin);
        tblfou.setModel(tabfou);
        tblreptic.setModel(tabreptic);
        tblrepcal.setModel(tabrepcal);
        tblrepsms.setModel(tabrepsms);
        tblrepmail.setModel(tabrepmail);
        tblrepfax.setModel(tabrepfax);
        tblmsin.setModel(tabmsin);
        tblmsou.setModel(tabmsou);
        tblhourin.setModel(tabhoin);
        tblhourout.setModel(tabhoou);
        tbldailyin.setModel(tabdayin);
        tbldailyout.setModel(tabdayou);
        tblperformin.setModel(tabperfin);
        tblperformout.setModel(tabperfou);
        tbin(tblin,new int []{130,100,70,85,85  ,85,60,120,70,120       ,75,120,70,120,90
                ,120,120,120,90,550     ,100,155,0});
        tbin(tblout,new int []{130,110,100,100,110,130,110,110});
//        tbin(tblcus,new int []{250,200,170,120,120,100,500,500,120,80,500,120,120,0});
        tbin(tblcus,new int []{100,115,350,200,100  ,300,120,250,100});
        tbin(tblmin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblmou,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblsin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblsou,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblfin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblfou,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblreptic,new int []{85,85,120,85,100  ,175,175,150,170,170    ,170,170,175,500,140
                                    ,140,145,150,250,500    ,500,500,100,100    ,120,150,150,100,100
                                    ,100,150,100,100,100    ,150,150,100,500,50,60,50,60,50,60,50,60,150,150,150,150,150,150,300,120,120,120,120,100,100,150,100,100,300,500,85,85,90,100,150,85,85,90,100,85,85,90,85,100,90,85,150,125,150,85});
        tbin(tblrepcal,new int []{100,100,110,95,120        ,110,110,110,95,95      
                                    ,95,130,110,105,110     ,105,110,105,90,110
                                    ,110,105,110,105    ,120,110    ,500,500,120,100        ,85,90,110  ,175,85,110,160});
        tbin(tblticconf,new int []{100,115,100,350,200,100,300,120,250,100,170,120,120,120});        
        tbin(tbltic,new int []{100,100,100,100,120      ,150,300,200,200,150
                ,250,200,150,250,120        ,250,500,500,-1});
        tbin(tblrepsms,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblrepmail,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblrepfax,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblhourin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100,100});
        tbin(tblhourout,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tbldailyin,new int []{100,100,100,100,100
                ,100,100,100,100,100,100,100
                ,100,100,100});
        tbin(tbldailyout,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblperformin,new int []{100,100,100,100,100,100
                ,100,100,100,100,100,100,100
                ,100,100,100});
        tbin(tblperformout,new int []{100,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});  
        optm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dtdt;
        try {
            dtdt = sdf.parse(ld.substring(8, 10) + "/" + ld.substring(5, 7) + "/" + ld.substring(0, 4));
            dctic1.setDate(dtdt);            dctic2.setDate(dtdt);            dctic3.setDate(dtdt);            dctic4.setDate(dtdt);
            dctic5.setDate(dtdt);            dctic6.setDate(dtdt);            dctic7.setDate(dtdt);            dctic8.setDate(dtdt);
            dccal1.setDate(dtdt);            dccal2.setDate(dtdt);            dcfax1.setDate(dtdt);            dcfax2.setDate(dtdt);
            dcmail1.setDate(dtdt);            dcmail2.setDate(dtdt);            dcsms1.setDate(dtdt);            dcsms2.setDate(dtdt);
            dtmi.setDate(dtdt);            dtmi1.setDate(dtdt);            dtmo.setDate(dtdt);            dtmo1.setDate(dtdt);
            dtsi.setDate(dtdt);            dtsi1.setDate(dtdt);            dtso.setDate(dtdt);            dtso1.setDate(dtdt);
            dtfi.setDate(dtdt);            dtfi1.setDate(dtdt);            dtfo.setDate(dtdt);            dtfo1.setDate(dtdt);
            dtmsi.setDate(dtdt);            dtmsi1.setDate(dtdt);            dtmso.setDate(dtdt);            dtmso1.setDate(dtdt);
            dthi.setDate(dtdt);            dtho.setDate(dtdt);            dtdi.setDate(dtdt);            dtdi1.setDate(dtdt);
            dtdo.setDate(dtdt);            dtdo1.setDate(dtdt);            dtpi.setDate(dtdt);            dtpi1.setDate(dtdt);
            dtpo.setDate(dtdt);            dtpo1.setDate(dtdt);
        } catch (ParseException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }

        showDept();showCaltype();
        tabelin();
        tabelou();
        tabelmsin();
        tabelmsou();
        tabeltic();
//        tabelticconf();
        tabelact();
        tabelmin();
        tabelmou();
        call();
        sms();
        mail();
        fax();
        new Timer(1000, dating).start();        
        inbo=new Timer(1000, activ);
        receiv=new Timer(10, testing);
        Scrol=new Timer(40, tiscrol);
        msg=new Timer(1000, inbound);

        if(Log.version!=Log.Loc){
            if(!Log.data[0].equals("herfan")){
                connect();
                connecttele();
                connectuploder();
            }
        }        

//        System.out.print("\nusrlvl = "+usrlvl);
//        if(usrlvl!=0){
        usr();
        showDistributor();showcategory();
        btnrelease.setEnabled(true);
//        }
//        showCust();
    }

        private cccs_ticket Tic;
    public ContactCenterOrangeTv(cccs_ticket tic){
        this();
        this.Tic=tic;
    }

    public static cccs_login Log;
    public ContactCenterOrangeTv(cccs_login log){
        this();
        this.Log=log;
    }

    private cccs_InBoundCall Inc;
    public ContactCenterOrangeTv(cccs_InBoundCall inc){
        this();
        this.Inc=inc;
    }

    private cccs_OutBound Obc;
    public ContactCenterOrangeTv(cccs_OutBound obc){
        this();
        this.Obc=obc;
    }
    
    private cccs_History Hic;
    public ContactCenterOrangeTv(cccs_History hic){
        this();
        this.Hic=hic;
    }

    public cccs_Sms_income Sin;
    public ContactCenterOrangeTv(cccs_Sms_income sin){
        this();
        this.Sin=sin;
    }
    public cccs_Email_incoming Ein;
    public ContactCenterOrangeTv(cccs_Email_incoming ein){
        this();
        this.Ein=ein;
    }
    public cccs_Fax_incoming Fin;
    public ContactCenterOrangeTv(cccs_Fax_incoming fin){
        this();
        this.Fin=fin;
    }
    public cccs_Asdept Asd;
    public ContactCenterOrangeTv(cccs_Asdept asd){
        this();
        this.Asd=asd;
    }
    public cccs_Mssg Misg;
    public ContactCenterOrangeTv(cccs_Mssg misg){
        this();
        this.Misg=misg;
    }
    
    public static void tbin(javax.swing.JTable tb, int lebar[]){
        tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        int kolom=tb.getColumnCount();
        for (int i=0;i<kolom;i++){
            javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
//            tbc.setHeaderRenderer(tb.setAlignmentX(JTable.CENTER_ALIGNMENT));
            tb.setAlignmentY(tb.CENTER_ALIGNMENT);
            tb.setRowHeight(18);
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","User","Shift","Line number","Call status"        ,"Duration","Inquiry","Inquiry Detail","Request","Request Detail"
                        ,"Complaint","Complaint Detail","Suggestion","Suggestion Detail","Blank Call"       ,"Caller number","Caller Name","Ticket No.","Call Type","Comment"
                        ,"Wrong Number","Cust Company","Log ID"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false       ,false,false,false,false,false
                            ,false,false,false,false,false      ,false,false,false,false,false
                            ,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static void tabelin(){
        tabin.setRowCount(0);
        x=0;
             try{
                Date dt5 =dctic5.getDate();
                Date dt6 =dctic6.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cal3 = sdf.format(dt5);
                cal4 = sdf.format(dt6);
//              sql="select a.log_time, a.username, a.shift, a.line_number, a._callstatus, a.duration, a._complaint, a._blankcall, a.caller_number, a.caller_name, b.ticket_no, b._status from log_phone a, tickets b where a.log_date = '"+ld+"'";
              sql="select log_phone.*, " +
                      "_callstatus.data as cllstt, " +
//                      "substring(callback_time from 1 for 10) as cb_date, " +
//                      "substring(callback_time from 12 for 8) as cb_time, " +
                      "shift.data as dshift, " +
                      "tickets.ticket_no as notic " +
                      "from log_phone " +
                      "join _callstatus on log_phone._callstatus=_callstatus.code " +
                      "join shift on log_phone.shift=shift.code " +
                      "left join tickets on log_phone.ticket_id=tickets.ticket_id " +
                      "where log_date between '"+cal3+"' and '"+cal4+"' and _direction=0 ";
                condition="";
            if(!cbagenin.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenin.getSelectedItem()+"%'";
            }
            if(!cbcalstatin.getSelectedItem().equals("--")){
                if(cbcalstatin.getSelectedItem().equals("PHANTOM")){
                    condition=condition+" and _callstatus = '-1'";
                }else{
                    condition=condition+" and _callstatus = '"+cbcalstatin.getSelectedIndex()+"'";
                }
            }

            sql=sql+condition+" order by log_id";
              rs=jconn.SQLExecuteRS(sql, conn);
              System.out.println(sql);

            while(rs.next()){
//                System.out.print("\nisi id ="+rs.getString(1));
                in[x]=rs.getString(2)+" "+rs.getString(3);x++;
                in[x]=rs.getString(4);x++;
                in[x]=rs.getString("dshift");x++;
                in[x]=rs.getString(7);x++;
                in[x]=rs.getString("cllstt");x++;

                in[x]=rs.getString("duration");x++;
                in[x]=rs.getString("_inquiry");x++;
                in[x]=rs.getString("_inquiry_detail");x++;
                in[x]=rs.getString("_inquirytransfer");x++;
                in[x]=rs.getString("_inquirytransfer_detail");x++;

                in[x]=rs.getString("_complaint");x++;
                in[x]=rs.getString("_complaint_detail");x++;
                in[x]=rs.getString("_suggestion");x++;
                in[x]=rs.getString("_suggestion_detail");x++;
                in[x]=rs.getString("_blankcall");x++;

                in[x]=rs.getString("caller_number");x++;
                in[x]=rs.getString("caller_name");x++;
                in[x]=rs.getString("notic");x++;//logid
                in[x]=rs.getString("caller_type");x++;//call_type
                in[x]=rs.getString("comment");x++;//comment

                in[x]=rs.getString("_wrongnumber");x++;//wromg_num
                in[x]=rs.getString("cust_name");x++;//call_back
                in[x]=rs.getString("log_id");x++;//call_back
                tabin.addRow(in);
                x=0;
            }
              lblcalincount.setText(String.valueOf(tabin.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }


    public static javax.swing.table.DefaultTableModel getDefaultTabelout(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","User","Shift","Line number","Duration","Phone number","Ticket No","Status"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelou(){
        x=0;
        tabou.setRowCount(0);
        try{
            Date dt7 =dctic7.getDate();
            Date dt8 =dctic8.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal5 = sdf.format(dt7);
            cal6 = sdf.format(dt8);
             int row=0;
              sql="select log_phone.*" +
                      ", tickets.ticket_no as notic" +
                      ", _ticketstatus.data as status" +
                      ", shift.data as dshift" +
                      " from log_phone" +
                      " left join tickets on log_phone.ticket_id=tickets.ticket_id"+
                      " left join _ticketstatus on tickets._status=_ticketstatus.code"+
                      " join shift on log_phone.shift=shift.code" +
                      " where log_date between '"+cal5+"' and '"+cal6+"' and _direction=1";
                  condition="";
            if(!cbagenou.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenou.getSelectedItem()+"%'";
            }
            sql=sql+condition+" order by log_id";
              rs=jconn.SQLExecuteRS(sql, conn);
//              System.out.println(sql);
            while(rs.next()){
                ou[x]=rs.getString("log_time");x++;
                ou[x]=rs.getString("username");x++;
                ou[x]=rs.getString("dshift");x++;
                ou[x]=rs.getString("line_number");x++;
                ou[x]=rs.getString("duration");x++;
                ou[x]=rs.getString("phone_number");x++;
                ou[x]=rs.getString("notic");x++;
                ou[x]=rs.getString("status");x++;
                tabou.addRow(ou);
                x=0;
            }
              lblcaloutcount.setText(String.valueOf(tabou.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabeltic(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Ticket No.","Priority","Source","Type","Status"
                        ,"Open By","Product","Department","Assign Dept.","Assign User"
                        ,"Category","Bussiness Line","Bikepoint Name","Cust Name","Cust Phone number"
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
    public static void tabeltic(){
        x=0;
        tabtic.setRowCount(0);
        try{
            Date dt3 =dctic3.getDate();
            Date dt4 =dctic4.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tic3 = sdf.format(dt3);
            tic4 = sdf.format(dt4);
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
                      + " where ticket_id is not null";
              condition="";
            if(cktgl.isSelected()==true){
                if(!dctic4.getDate().equals("")){
                    condition=condition+" and open_date between '"+tic3+"' and '"+tic4+"'";
                }else{
                    condition=condition+" and open_date= '"+tic3+"'";
                    System.out.print(condition);
                }
            }
            if(!txtticno1.getText().equals("")){
                condition=condition+" and ticket_no like '%"+txtticno1.getText()+"%'";
            }
            if(!cbdept.getSelectedItem().equals("--")){
                condition=condition+" and tickets.dept_id = '"+cbdept.getSelectedIndex()+"'";
            }
            if(!cbticstatus.getSelectedItem().equals("--")){
                if(!cbticstatus.getSelectedItem().equals("CANCEL")){
                    condition=condition+" and _status = '"+cbticstatus.getSelectedIndex()+"'";
                }else{
                    condition=condition+" and _status = '-1'";
                }
            }
            if(cksubmit.isSelected()==true){
                condition=condition+" and _submitted=0";
            }
            if(!txtcus.getText().equals("")){
                condition=condition+" and cust_name like '%"+txtcus.getText()+"%'";
            }
            if(!cbcate.getSelectedItem().equals("--")){
                condition=condition+" and category = '"+cbcate.getSelectedItem()+"'";
            }
            sql=sql+condition+" order by ticket_no";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.print("\nserach tic "+sql);

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
                tic[x]=rs.getString("bikepoint_name");x++;
                tic[x]=rs.getString("cust_name");x++;
                tic[x]=rs.getString("cust_phone");x++;

                tic[x]=rs.getString("pic_name");x++;
                tic[x]=rs.getString("details");x++;
                tic[x]=rs.getString("solution");x++;
                tic[x]=rs.getString("ticket_id");x++;
                tabtic.addRow(tic);
                x=0;
            }
            lblticcount.setText(String.valueOf(tabtic.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelsin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","From","Status","Messages","Process By","Id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }

    private void tabelsin() {
          try{
              tabsin.setRowCount(0);
              int x=0;
              Date dt1 =dtsi.getDate();
              Date dt2 =dtsi1.getDate();
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              sin = sdf.format(dt1);
              sin1= sdf.format(dt2);
              sql="select log_sms.*, rcvd_status.data as stt, " +
                      "tickets.ticket_no as notic " +
                      "from log_sms " +
                      "join rcvd_status on log_sms._status=rcvd_status.code " +
                      "left join tickets on log_sms.ticket_id=tickets.ticket_id " +
                      "where sms_date between '"+sin+"' and '"+sin1+"' and _direction=0 order by sms_id";
              rs=jconn.SQLExecuteRS(sql, conn);
              System.out.println(sql);

            while(rs.next()){
                in2[x]=rs.getString("sms_date")+" "+rs.getString("sms_time");x++;
                in2[x]=rs.getString("sms_from");x++;
                in2[x]=rs.getString("stt");x++;
                in2[x]=rs.getString("sms_text");x++;
                in2[x]=rs.getString("username");x++;
                in2[x]=rs.getString("sms_id");x++;
                in2[x]=rs.getString("cust_name");x++;
                tabsin.addRow(in2);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelsou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","To","Status","Messages","Sent By","No. Ticket"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelsou(){
        try{
           tabsou.setRowCount(0);
           int x=0;
           Date dt1 =dtso.getDate();
           Date dt2 =dtso1.getDate();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           sou = sdf.format(dt1);
           sou1= sdf.format(dt2);
           sql="select log_sms.*, send_status.data as stt, " +
                   "tickets.ticket_no as notic " +
                   "from log_sms " +
                   "join send_status on log_sms._status=send_status.code " +
                   "left join tickets on log_sms.ticket_id=tickets.ticket_id " +
                   "where sms_date between '"+sou+"' and '"+sou1+"' and _direction=1 order by sms_id";
           rs=jconn.SQLExecuteRS(sql, conn);
           System.out.println(sql);

           while(rs.next()){
               ou1[x]=rs.getString("sms_date")+" "+rs.getString("sms_time");x++;
               ou1[x]=rs.getString("sms_to");x++;
               ou1[x]=rs.getString("stt");x++;
               ou1[x]=rs.getString("sms_text");x++;
               ou1[x]=rs.getString("username");x++;
               ou1[x]=rs.getString("notic");x++;
               tabsou.addRow(ou1);x=0;
           }
       }catch(Exception exc){
           System.err.println(exc.getMessage());
       }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelmin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","From","Subject","Status","username","Text","id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    String min,min1,mou,mou1,sin,sin1,sou,sou1,fin,fin1,fou,fou1;
    public static String msin,msin1,msou,msou1;
    private void tabelmin() {
        try{
            tabmin.setRowCount(0);
            int x=0;
            Date dt1 =dtmi.getDate();
            Date dt2 =dtmi1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            min = sdf.format(dt1);
            min1= sdf.format(dt2);
            sql="select log_mail.*, " +
                    "rcvd_status.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_mail " +
                    "join rcvd_status on log_mail.status=rcvd_status.code " +
                    "left join tickets on log_mail.ticket_id=tickets.ticket_id " +
                    "where mail_date between '"+min+"' and '"+min1+"' and direction=0 order by mail_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                in2[x]=rs.getString("mail_date");x++;
                in2[x]=rs.getString("mail_time");x++;
                in2[x]=rs.getString("mail_from");x++;
                in2[x]=rs.getString("mail_subject");x++;
                in2[x]=rs.getString("stt");x++;
                in2[x]=rs.getString("username");x++;
                in2[x]=rs.getString("mail_text");x++;
                in2[x]=rs.getString("mail_id");x++;
                in2[x]=rs.getString("cust_name");x++;
                tabmin.addRow(in2);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelmout(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","To","Username","Subject","Ticket no","Status","Text","CC","id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelmou() {
        try{
            tabmou.setRowCount(0);
            int x=0;
            Date dt1 =dtmo.getDate();
            Date dt2 =dtmo1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            mou = sdf.format(dt1);
            mou1= sdf.format(dt2);
            sql="select log_mail.*, send_status.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_mail " +
                    "join send_status on log_mail.status=send_status.code " +
                    "left join tickets on log_mail.ticket_id=tickets.ticket_id " +
                    "where mail_date between '"+mou+"' and '"+mou1+"' and direction=1 order by mail_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                ou1[x]=rs.getString("mail_date");x++;
                ou1[x]=rs.getString("mail_time");x++;
                ou1[x]=rs.getString("mail_to");x++;
                ou1[x]=rs.getString("username");x++;
                ou1[x]=rs.getString("mail_subject");x++;
                ou1[x]=rs.getString("notic");x++;
                ou1[x]=rs.getString("stt");x++;
                ou1[x]=rs.getString("mail_text");x++;
                ou1[x]=rs.getString("mail_cc");x++;
                ou1[x]=rs.getString("mail_id");x++;
                ou1[x]=rs.getString("cust_name");x++;
                tabmou.addRow(ou1);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelfin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","Document name","Status","Process By","Id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelfin() {
        try{
            tabfin.setRowCount(0);
            int x=0;
            Date dt1 =dtfi.getDate();
            Date dt2 =dtfi1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            fin = sdf.format(dt1);
            fin1= sdf1.format(dt2);
            sql="select log_fax.*, rcvd_status.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_fax " +
                    "join rcvd_status on log_fax._status=rcvd_status.code " +
                    "left join tickets on log_fax.ticket_id=tickets.ticket_id " +
                    "where rcvd_time between '"+fin+"' and '"+fin1+"' and _direction=0 order by fax_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                in3[x]=rs.getString("rcvd_time");x++;
                in3[x]=rs.getString("doc_name");x++;
                in3[x]=rs.getString("stt");x++;
                in3[x]=rs.getString("username");x++;
                in3[x]=rs.getString("fax_id");x++;
                in3[x]=rs.getString("cust_name");x++;
                tabfin.addRow(in3);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelfou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","To","Document name","Status","Sent By","No. Ticket"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelfou(){
        try{
            tabfou.setRowCount(0);
            int row=0;
            Date dt1 =dtfo.getDate();
            Date dt2 =dtfo1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            fou = sdf.format(dt1);
            fou1= sdf1.format(dt2);
            sql="select log_fax.*, _faxstatus.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_fax " +
                    "join _faxstatus on log_fax._status=_faxstatus.code " +
                    "left join tickets on log_fax.ticket_id=tickets.ticket_id " +
                    "where sent_time between '"+fou+"' and '"+fou1+"' and _direction=1 order by fax_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                ou3[0]=rs.getString("sent_time");
                ou3[1]=rs.getString("recipient");
                ou3[2]=rs.getString("doc_name");
                ou3[3]=rs.getString("stt");
                ou3[4]=rs.getString("username");
                ou3[5]=rs.getString("notic");
                tabfou.addRow(ou3);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }


    public static javax.swing.table.DefaultTableModel getDefaultTabelreptic(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Ticket No.","Priority","Type","Status","Open By"
                        ,"Department","Assign Dept.","Assign User","Category","Product Type"
                        ,"Guaranty Number","Cust Name","Cust Phone number","Cust Address","Cust Fax"    ,"PIC Name","PIC Phone","PIC Mobile","PIC Email","PIC Address"
                        ,"Details","Solution","Open Date","Open time"      ,"Open duration","Open username","Open unit","Process date","Process time"
                        ,"Process Duration","Process Username","Close Date","Close Time"        ,"Close Duration","Close Username"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false       ,false,false,false,false,false
                            ,false,false,false,false,false      ,false,false,false,false,false
                            ,false,false,false,false,false      ,false,false,false,false,false
                            ,false,false,false,false,false      ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelreptic(){
        int x=0;
        tabreptic.setRowCount(0);
        Date dt1 =dctic1.getDate();
        Date dt2 =dctic2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tic1 = sdf.format(dt1);
        tic2= sdf.format(dt2);
        try{
            int row=0;
            sql="select *, " +
                    "a.dept_name as dept," +
                    "b.dept_name as asdept," +
                    "c.dept_name as opdept," +
                    " _ticketstatus.data as ticstat, " +
                    "_ticketpriority.data as prior, " +
                    "case confirmed " +
                    "when 0 then 'Belum Konfirmasi' " +
                    "when 1 then 'Sedang Konfirmasi' " +
                    "when 2 then 'Sudah Konfirmasi' " +
                    "end as confirmd, " +
                    "case confirm_by " +
                    "when 0 then 'CONTACT CENTER' " +
                    "when 1 then 'CSO' " +
                    "end as cnfrm " +
                    "from tickets" +
                    " join _department a on tickets.dept_id=a.dept_id" +
                    " join _department b on tickets.assign_dept=b.dept_id" +
                    " join _department c on tickets.open_dept=c.dept_id" +
                    " join _ticketstatus on tickets._status=_ticketstatus.code" +
                    " join _ticketpriority on tickets._priority=_ticketpriority.code" +
                    " where open_date between '"+tic1+"' and '"+tic2+"' ";
            condition="";
            if(!txtuser.getText().equals("")){
                condition=condition+" and user like '%"+txtuser.getText()+"%'";
            }
            if(!txtcategory.getText().equals("")){
                condition=condition+" and category_name like '%"+txtcategory.getText()+"%'";
            }
            if(!txtdriver.getText().equals("")){
                condition=condition+" and driver_name like '%"+txtdriver.getText()+"%'";
            }
            if(!txtcustomer.getText().equals("")){
                condition=condition+" and cust_name like '%"+txtcustomer.getText()+"%'";
            }
            if(!txtticno.getText().equals("")){
                condition=condition+" and ticket_no like '%"+txtticno.getText()+"%'";
            }

            sql=sql+condition+" order by ticket_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                reptic[x]=rs.getString("ticket_no");x++;
                reptic[x]=rs.getString("prior");x++;
                reptic[x]=rs.getString("_type");x++;
                reptic[x]=rs.getString("ticstat");x++;
                reptic[x]=rs.getString("open_username");x++;

                reptic[x]=rs.getString("opdept");x++;
                reptic[x]=rs.getString("asdept");x++;
                reptic[x]=rs.getString("assign_username");x++;
                reptic[x]=rs.getString("category");x++;
                reptic[x]=rs.getString("_type");x++;

                reptic[x]=rs.getString("guaranty_no");x++;
                reptic[x]=rs.getString("cust_name");x++;
                reptic[x]=rs.getString("cust_phone");x++;
                reptic[x]=rs.getString("cust_address");x++;
                reptic[x]=rs.getString("cust_fax");x++;

                reptic[x]=rs.getString("pic_name");x++;
                reptic[x]=rs.getString("pic_phone");x++;
                reptic[x]=rs.getString("pic_mobile");x++;
                reptic[x]=rs.getString("pic_email");x++;
                reptic[x]=rs.getString("pic_address");x++;

                reptic[x]=rs.getString("details");x++;
                reptic[x]=rs.getString("solution");x++;
                reptic[x]=rs.getString("open_date");x++;
                reptic[x]=rs.getString("open_time");x++;

                reptic[x]=rs.getString("open_duration");x++;
                reptic[x]=rs.getString("open_username");x++;
                reptic[x]=rs.getString("opdept");x++;
                reptic[x]=rs.getString("process_date");x++;
                reptic[x]=rs.getString("process_time");x++;

                reptic[x]=rs.getString("process_duration");x++;
                reptic[x]=rs.getString("process_username");x++;
                reptic[x]=rs.getString("close_date");x++;
                reptic[x]=rs.getString("close_time");x++;

                reptic[x]=rs.getString("close_duration");x++;
                reptic[x]=rs.getString("close_username");x++;
                tabreptic.addRow(reptic);
                x=0;
                row+=1;
            }if(row==0){
                JOptionPane.showMessageDialog(null,"Ticket with number ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" doesn't exsist");
            }
            lblrepticcount.setText(String.valueOf(tabreptic.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelrepcal(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"log_date","log_time","username","shift","host_addr"     ,"line_number","_direction","_callstatus","duration","abandon"
                        ,"wait","Speed of answer","ACW","Inquiry","Inquiry Detail"      ,"Request","Request Detail","Complaint","Complaint Detail","blankcall"
                        ,"Others","Others Detail","_wrongnumber","caller_number"        ,"caller_type","caller_name"                        ,"comment","filename","phone_number","ticket_no"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }

    private void tabelrepcal(){
        x=0;
        tabrepcal.setRowCount(0);
        Date dt1 =dccal1.getDate();
        Date dt2 =dccal2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal1 = sdf.format(dt1);
        cal2= sdf.format(dt2);
        try{
            int row=0;
            sql="select log_phone.*, " +
                    "_callstatus.data as cllstatus, " +
                    "shift.data as dshift, " +
                    "_direction.data as ddir, " +
                    "tickets.ticket_no as notic " +
                    "from log_phone " +
                    "left join _callstatus on log_phone._callstatus=_callstatus.code " +
                    "left join tickets on log_phone.ticket_id=tickets.ticket_id " +
                    "join shift on log_phone.shift=shift.code " +
                    "join _direction on log_phone._direction=_direction.code " +
                    "where log_date between '"+cal1+"' and '"+cal2+"' ";
            condition="";
            if(!cbagenirepcal.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenirepcal.getSelectedItem()+"%'";
            }
            if(!cbcaldir.getSelectedItem().equals("--")){
                condition=condition+" and _direction = '"+cbcaldir.getSelectedIndex()+"'";
            }
            if(!cbcaltyperepcal.getSelectedItem().equals("--")){
                condition=condition+" and caller_type like '%"+cbcaltyperepcal.getSelectedItem()+"%'";
            }
            if(!cbcalstat.getSelectedItem().equals("--")){
                if(cbcalstat.getSelectedItem().equals("PHANTOM")){
                    condition=condition+" and _callstatus = '-1'";
                }else{
                    condition=condition+" and _callstatus = '"+cbcalstat.getSelectedIndex()+"'";
                }
            }

            sql=sql+condition+" order by log_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);

            while(rs.next()){
//                repcal[0]=rs.getString(1);
                repcal[x]=rs.getString("log_date");x++;
                repcal[x]=rs.getString("log_time");x++;
                repcal[x]=rs.getString("username");x++;
                repcal[x]=rs.getString("dshift");x++;
                repcal[x]=rs.getString(6);x++;

                repcal[x]=rs.getString(7);x++;
                repcal[x]=rs.getString("ddir");x++;
                repcal[x]=rs.getString("cllstatus");x++;
                repcal[x]=rs.getString(10);x++;
                repcal[x]=rs.getString(11);x++;

                repcal[x]=rs.getString(12);x++;
                repcal[x]=rs.getString(13);x++;
                repcal[x]=rs.getString(14);x++;
                repcal[x]=rs.getString(15);x++;
                repcal[x]=rs.getString(16);x++;

                repcal[x]=rs.getString(17);x++;
                repcal[x]=rs.getString(18);x++;
                repcal[x]=rs.getString(19);x++;
                repcal[x]=rs.getString(20);x++;
                repcal[x]=rs.getString(21);x++;

                repcal[x]=rs.getString(22);x++;
                repcal[x]=rs.getString(23);x++;
                repcal[x]=rs.getString(24);x++;
                repcal[x]=rs.getString(25);x++;
                
                repcal[x]=rs.getString(26);x++;                
                repcal[x]=rs.getString(27);x++;
                
                repcal[x]=rs.getString(28);x++;
                repcal[x]=rs.getString(29);x++;
                repcal[x]=rs.getString(36);x++;
                repcal[x]=rs.getString("notic");x++;
                tabrepcal.addRow(repcal);
                row+=1;
                x=0;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Ticket with number cccs_ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" doesn't exsist");
            }
            lblrepcalcount.setText(String.valueOf(tabrepcal.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
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

        jdp = new javax.swing.JDesktopPane();
        lbldate = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btncall = new javax.swing.JButton();
        btnsms = new javax.swing.JButton();
        btnmail = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        lbluser = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnoutbound = new javax.swing.JButton();
        lblpas = new javax.swing.JLabel();
        lblactivity = new javax.swing.JLabel();
        btnready = new javax.swing.JButton();
        cbdirection = new javax.swing.JComboBox();
        lblshift = new javax.swing.JLabel();
        lblshift1 = new javax.swing.JLabel();
        txtcalnoti = new javax.swing.JTextField();
        txtfaxnoti = new javax.swing.JTextField();
        txtsmsnoti = new javax.swing.JTextField();
        txtmailnoti = new javax.swing.JTextField();
        btnfax = new javax.swing.JButton();
        pnlscroll = new javax.swing.JPanel();
        lblscroll = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jtab = new javax.swing.JTabbedPane();
        pnlinbon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblin = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        btninsrch = new javax.swing.JButton();
        dctic5 = new com.toedter.calendar.JDateChooser();
        dctic6 = new com.toedter.calendar.JDateChooser();
        jLabel58 = new javax.swing.JLabel();
        cbcalstatin = new javax.swing.JComboBox();
        cbagenin = new javax.swing.JComboBox();
        lblcalincount = new javax.swing.JLabel();
        lblrepticcount12 = new javax.swing.JLabel();
        pnlou = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblout = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblticconf = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        dctic7 = new com.toedter.calendar.JDateChooser();
        dctic8 = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnoutsrch = new javax.swing.JButton();
        cbagenou = new javax.swing.JComboBox();
        lblcaloutcount = new javax.swing.JLabel();
        lblrepticcount11 = new javax.swing.JLabel();
        pnlDist = new javax.swing.JPanel();
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
        pnlact = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblact = new javax.swing.JTable();
        btnrelease = new javax.swing.JButton();
        cbagenrelease = new javax.swing.JComboBox();
        panelsms = new javax.swing.JTabbedPane();
        pninbox = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblsin = new javax.swing.JTable();
        dtsi = new com.toedter.calendar.JDateChooser();
        dtsi1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtimsg2 = new javax.swing.JTextArea();
        txtfrom2 = new javax.swing.JTextField();
        btnsmsinsrch = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        cbcust = new javax.swing.JComboBox();
        btncussavesms = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        txtnoticsms = new javax.swing.JTextField();
        btnsmsCALL = new javax.swing.JButton();
        pnoutbox = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblsou = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        dtso = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dtso1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtimsg1 = new javax.swing.JTextArea();
        txtfrom1 = new javax.swing.JTextField();
        btnsmsoutsrch = new javax.swing.JButton();
        panelmail = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblmin = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtfrom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtisu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtimsg = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        dtmi = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        dtmi1 = new com.toedter.calendar.JDateChooser();
        btnmailinsrch = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        cbcust1 = new javax.swing.JComboBox();
        btncussaveEmail = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        txtnoticmail = new javax.swing.JTextField();
        btnAttachment = new javax.swing.JButton();
        scpCcList1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        btnEmailCALL = new javax.swing.JButton();
        btnEmailCALL1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblmou = new javax.swing.JTable();
        txtoto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtocc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtosu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtomsg = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtidti = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        dtmo = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        dtmo1 = new com.toedter.calendar.JDateChooser();
        btnmailoutsrch = new javax.swing.JButton();
        scpCcList2 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        btnAttachment1 = new javax.swing.JButton();
        tabbpanereport = new javax.swing.JTabbedPane();
        pnlrep1 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblrepcal = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnrepcal = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        dccal1 = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        dccal2 = new com.toedter.calendar.JDateChooser();
        cbcaldir = new javax.swing.JComboBox();
        cbcalstat = new javax.swing.JComboBox();
        cbagenirepcal = new javax.swing.JComboBox();
        cbcaltyperepcal = new javax.swing.JComboBox();
        btnexportcall = new javax.swing.JButton();
        lblrepcalcount = new javax.swing.JLabel();
        lblrepticcount3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane39 = new javax.swing.JScrollPane();
        tblhourin = new javax.swing.JTable();
        jLabel78 = new javax.swing.JLabel();
        dthi = new com.toedter.calendar.JDateChooser();
        btnhi = new javax.swing.JButton();
        btnexportcall1 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane40 = new javax.swing.JScrollPane();
        tblhourout = new javax.swing.JTable();
        dtho = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        btnho = new javax.swing.JButton();
        btnexportcall2 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane43 = new javax.swing.JScrollPane();
        tbldailyin = new javax.swing.JTable();
        jLabel82 = new javax.swing.JLabel();
        dtdi = new com.toedter.calendar.JDateChooser();
        btndi = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        dtdi1 = new com.toedter.calendar.JDateChooser();
        btnexportcall3 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane44 = new javax.swing.JScrollPane();
        tbldailyout = new javax.swing.JTable();
        dtdo = new com.toedter.calendar.JDateChooser();
        jLabel83 = new javax.swing.JLabel();
        btndo = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        dtdo1 = new com.toedter.calendar.JDateChooser();
        btnexportcall4 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane41 = new javax.swing.JScrollPane();
        tblperformin = new javax.swing.JTable();
        dtpi = new com.toedter.calendar.JDateChooser();
        btnpi1 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        dtpi1 = new com.toedter.calendar.JDateChooser();
        jLabel88 = new javax.swing.JLabel();
        btnexportcall5 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane42 = new javax.swing.JScrollPane();
        tblperformout = new javax.swing.JTable();
        dtpo = new com.toedter.calendar.JDateChooser();
        btnpo1 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        dtpo1 = new com.toedter.calendar.JDateChooser();
        jLabel89 = new javax.swing.JLabel();
        btnexportcall6 = new javax.swing.JButton();
        pnlrep2 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblrepsms = new javax.swing.JTable();
        txtsmsstat = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnrepsms = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        dcsms1 = new com.toedter.calendar.JDateChooser();
        jLabel44 = new javax.swing.JLabel();
        dcsms2 = new com.toedter.calendar.JDateChooser();
        txtsmsticid = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cbdirrepsms = new javax.swing.JComboBox();
        cbagenirepcal1 = new javax.swing.JComboBox();
        btnexportsms = new javax.swing.JButton();
        lblrepsmscount = new javax.swing.JLabel();
        lblrepticcount5 = new javax.swing.JLabel();
        pnlrep3 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblrepmail = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtmailticid = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnrepmail = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtmailsub = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        dcmail1 = new com.toedter.calendar.JDateChooser();
        jLabel46 = new javax.swing.JLabel();
        dcmail2 = new com.toedter.calendar.JDateChooser();
        cbdirmail = new javax.swing.JComboBox();
        cbagenrepmail = new javax.swing.JComboBox();
        btnexportmail = new javax.swing.JButton();
        lblrepmailcount = new javax.swing.JLabel();
        lblrepticcount7 = new javax.swing.JLabel();
        pnlinf = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pninbox1 = new javax.swing.JPanel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tblmsin = new javax.swing.JTable();
        dtmsi = new com.toedter.calendar.JDateChooser();
        dtmsi1 = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane34 = new javax.swing.JScrollPane();
        txtimsg3 = new javax.swing.JTextArea();
        btnreplymsg = new javax.swing.JButton();
        btndelmsg = new javax.swing.JButton();
        pnoutbox1 = new javax.swing.JPanel();
        jScrollPane35 = new javax.swing.JScrollPane();
        tblmsou = new javax.swing.JTable();
        jLabel71 = new javax.swing.JLabel();
        dtmso = new com.toedter.calendar.JDateChooser();
        jLabel72 = new javax.swing.JLabel();
        dtmso1 = new com.toedter.calendar.JDateChooser();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane36 = new javax.swing.JScrollPane();
        txtimsg4 = new javax.swing.JTextArea();
        btncomposemsg = new javax.swing.JButton();
        btndelmsg1 = new javax.swing.JButton();
        panelfax = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        tblfin = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        dtfi = new com.toedter.calendar.JDateChooser();
        jLabel69 = new javax.swing.JLabel();
        dtfi1 = new com.toedter.calendar.JDateChooser();
        btnfinsrch = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        lblview = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        cbcust2 = new javax.swing.JComboBox();
        btncussaveFax = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        txtnoticfax = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tblfou = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();
        dtfo = new com.toedter.calendar.JDateChooser();
        jLabel76 = new javax.swing.JLabel();
        dtfo1 = new com.toedter.calendar.JDateChooser();
        btnfoutsrch = new javax.swing.JButton();
        jScrollPane31 = new javax.swing.JScrollPane();
        lblview1 = new javax.swing.JLabel();
        pnlrep4 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblrepfax = new javax.swing.JTable();
        txtfaxfinm = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnrepfax = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        dcfax1 = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        dcfax2 = new com.toedter.calendar.JDateChooser();
        cbstatusrepfax = new javax.swing.JComboBox();
        btnexportmail1 = new javax.swing.JButton();
        cbagenirepfax = new javax.swing.JComboBox();
        jLabel64 = new javax.swing.JLabel();
        cbdirfax = new javax.swing.JComboBox();
        lblrepfaxcount = new javax.swing.JLabel();
        lblrepticcount9 = new javax.swing.JLabel();
        pnltic = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltic = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        dctic3 = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        btnticsrch = new javax.swing.JButton();
        dctic4 = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        btnsenddept = new javax.swing.JButton();
        cktgl = new javax.swing.JCheckBox();
        jLabel73 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtsolution = new javax.swing.JTextArea();
        jScrollPane38 = new javax.swing.JScrollPane();
        txtdetail = new javax.swing.JTextArea();
        lblticcount = new javax.swing.JLabel();
        lblrepticcount10 = new javax.swing.JLabel();
        jScrollPane47 = new javax.swing.JScrollPane();
        jPanel25 = new javax.swing.JPanel();
        txtticno1 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cbticstatus = new javax.swing.JComboBox();
        cbdept = new javax.swing.JComboBox();
        cksubmit = new javax.swing.JCheckBox();
        jLabel53 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txtcus = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        cbcate = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblvrf = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        lblcaloutcount1 = new javax.swing.JLabel();
        lblrepticcount13 = new javax.swing.JLabel();
        pnlrep = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblreptic = new javax.swing.JTable();
        txtcategory = new javax.swing.JTextField();
        txtdriver = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcustomer = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnreptic = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        dctic1 = new com.toedter.calendar.JDateChooser();
        dctic2 = new com.toedter.calendar.JDateChooser();
        txtticno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnexporttic = new javax.swing.JButton();
        lblrepticcount = new javax.swing.JLabel();
        lblrepticcount1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setTitle("DISTRIBUTOR ORANGE TV");
        getContentPane().setLayout(null);

        jdp.setBackground(new java.awt.Color(255, 255, 255));

        lbldate.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbldate.setForeground(new java.awt.Color(255, 255, 255));
        lbldate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldate.setText("Date Time");
        lbldate.setBounds(40, 660, 230, 30);
        jdp.add(lbldate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(255, 102, 0));
        jPanel1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel1.setLayout(null);

        btncall.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btncall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cal1.jpg"))); // NOI18N
        btncall.setBorder(null);
        btncall.setEnabled(false);
        btncall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncallActionPerformed(evt);
            }
        });
        jPanel1.add(btncall);
        btncall.setBounds(370, 20, 80, 80);

        btnsms.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sm.jpg"))); // NOI18N
        btnsms.setBorder(null);
        btnsms.setEnabled(false);
        btnsms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsActionPerformed(evt);
            }
        });
        jPanel1.add(btnsms);
        btnsms.setBounds(460, 20, 80, 80);

        btnmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mail.jpg"))); // NOI18N
        btnmail.setBorder(null);
        btnmail.setEnabled(false);
        btnmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailActionPerformed(evt);
            }
        });
        jPanel1.add(btnmail);
        btnmail.setBounds(550, 20, 80, 80);

        btnlogout.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117830_public.png"))); // NOI18N
        btnlogout.setToolTipText("LOG OUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnlogout);
        btnlogout.setBounds(920, 20, 50, 40);

        lbluser.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lbluser.setForeground(new java.awt.Color(255, 102, 51));
        lbluser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbluser.setText("Username");
        jPanel1.add(lbluser);
        lbluser.setBounds(810, 20, 100, 40);

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo1.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(10, 10, 200, 100);

        btnoutbound.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnoutbound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/out.jpg"))); // NOI18N
        btnoutbound.setToolTipText("OutBound");
        btnoutbound.setEnabled(false);
        btnoutbound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoutboundActionPerformed(evt);
            }
        });
        jPanel1.add(btnoutbound);
        btnoutbound.setBounds(640, 20, 70, 0);

        lblpas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblpas.setText("jLabel1");
        lblpas.setEnabled(false);
        lblpas.setRequestFocusEnabled(false);
        jPanel1.add(lblpas);
        lblpas.setBounds(110, 70, 40, 0);

        lblactivity.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblactivity.setForeground(new java.awt.Color(255, 102, 51));
        lblactivity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblactivity.setText("Disconnected");
        jPanel1.add(lblactivity);
        lblactivity.setBounds(270, 20, 90, 20);

        btnready.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnready.setText("Ready");
        btnready.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreadyMouseClicked(evt);
            }
        });
        btnready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreadyActionPerformed(evt);
            }
        });
        jPanel1.add(btnready);
        btnready.setBounds(270, 60, 90, 25);

        cbdirection.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbdirection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND" }));
        jPanel1.add(cbdirection);
        cbdirection.setBounds(270, 40, 90, 23);

        lblshift.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblshift.setEnabled(false);
        lblshift.setRequestFocusEnabled(false);
        jPanel1.add(lblshift);
        lblshift.setBounds(110, 70, 0, 0);

        lblshift1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblshift1.setEnabled(false);
        lblshift1.setRequestFocusEnabled(false);
        jPanel1.add(lblshift1);
        lblshift1.setBounds(110, 70, 0, 0);

        txtcalnoti.setEditable(false);
        txtcalnoti.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtcalnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtcalnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcalnoti.setBorder(null);
        txtcalnoti.setOpaque(false);
        txtcalnoti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcalnotiMouseClicked(evt);
            }
        });
        jPanel1.add(txtcalnoti);
        txtcalnoti.setBounds(760, 70, 30, 0);

        txtfaxnoti.setEditable(false);
        txtfaxnoti.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtfaxnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtfaxnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfaxnoti.setBorder(null);
        txtfaxnoti.setOpaque(false);
        jPanel1.add(txtfaxnoti);
        txtfaxnoti.setBounds(650, 100, 60, 0);

        txtsmsnoti.setEditable(false);
        txtsmsnoti.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtsmsnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtsmsnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsmsnoti.setBorder(null);
        txtsmsnoti.setOpaque(false);
        jPanel1.add(txtsmsnoti);
        txtsmsnoti.setBounds(470, 100, 60, 20);

        txtmailnoti.setEditable(false);
        txtmailnoti.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtmailnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtmailnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmailnoti.setBorder(null);
        txtmailnoti.setOpaque(false);
        txtmailnoti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmailnotiActionPerformed(evt);
            }
        });
        jPanel1.add(txtmailnoti);
        txtmailnoti.setBounds(560, 100, 60, 20);

        btnfax.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnfax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fax.jpg"))); // NOI18N
        btnfax.setBorder(null);
        btnfax.setEnabled(false);
        btnfax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfaxActionPerformed(evt);
            }
        });
        jPanel1.add(btnfax);
        btnfax.setBounds(640, 20, 80, 0);

        pnlscroll.setBackground(new java.awt.Color(255, 255, 255));
        pnlscroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlscrollMouseClicked(evt);
            }
        });
        pnlscroll.setLayout(null);

        lblscroll.setForeground(new java.awt.Color(255, 0, 0));
        pnlscroll.add(lblscroll);
        lblscroll.setBounds(710, 0, 0, 20);

        jPanel1.add(pnlscroll);
        pnlscroll.setBounds(220, 120, 750, 20);

        jPanel1.setBounds(10, 20, 990, 140);
        jdp.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(null);

        jtab.setBackground(new java.awt.Color(255, 255, 255));
        jtab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jtab.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        pnlinbon.setBackground(new java.awt.Color(255, 255, 255));
        pnlinbon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlinbon.setLayout(null);

        tblin.setAutoCreateRowSorter(true);
        tblin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblin.setDoubleBuffered(true);
        tblin.setFillsViewportHeight(true);
        tblin.setMaximumSize(new java.awt.Dimension(2147483647, 72));
        tblin.setRowHeight(20);
        tblin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblinMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblin);

        pnlinbon.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 950, 390);

        jLabel55.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel55.setText("Open From");
        pnlinbon.add(jLabel55);
        jLabel55.setBounds(10, 10, 80, 10);

        jLabel56.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel56.setText("Until");
        pnlinbon.add(jLabel56);
        jLabel56.setBounds(130, 10, 50, 10);

        jLabel57.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel57.setText("Agen");
        pnlinbon.add(jLabel57);
        jLabel57.setBounds(260, 10, 50, 10);

        btninsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btninsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btninsrch.setText("Search By");
        btninsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsrchActionPerformed(evt);
            }
        });
        pnlinbon.add(btninsrch);
        btninsrch.setBounds(500, 20, 130, 24);

        dctic5.setDateFormatString("dd/MM/yyyy");
        pnlinbon.add(dctic5);
        dctic5.setBounds(10, 20, 120, 24);

        dctic6.setDateFormatString("dd/MM/yyyy");
        pnlinbon.add(dctic6);
        dctic6.setBounds(130, 20, 120, 24);

        jLabel58.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel58.setText("Call Status");
        pnlinbon.add(jLabel58);
        jLabel58.setBounds(370, 10, 100, 10);

        cbcalstatin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbcalstatin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ABANDON", "ANSWERED", "PHANTOM", "--" }));
        cbcalstatin.setSelectedIndex(3);
        pnlinbon.add(cbcalstatin);
        cbcalstatin.setBounds(370, 20, 100, 24);

        cbagenin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlinbon.add(cbagenin);
        cbagenin.setBounds(260, 20, 100, 24);

        lblcalincount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlinbon.add(lblcalincount);
        lblcalincount.setBounds(880, 0, 40, 10);

        lblrepticcount12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount12.setText("list");
        pnlinbon.add(lblrepticcount12);
        lblrepticcount12.setBounds(920, 0, 40, 10);

        jtab.addTab("InBound", pnlinbon);

        pnlou.setBackground(new java.awt.Color(255, 255, 255));
        pnlou.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlou.setLayout(null);

        tblout.setAutoCreateRowSorter(true);
        tblout.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblout.setRowHeight(20);
        tblout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbloutMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblout);

        pnlou.add(jScrollPane12);
        jScrollPane12.setBounds(10, 40, 950, 390);

        tblticconf.setAutoCreateRowSorter(true);
        tblticconf.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblticconf.setModel(new javax.swing.table.DefaultTableModel(
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
        tblticconf.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblticconf.setRowHeight(20);
        tblticconf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblticconfMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tblticconf);

        pnlou.add(jScrollPane17);
        jScrollPane17.setBounds(10, 430, 950, 0);

        jLabel59.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel59.setText("Open From");
        pnlou.add(jLabel59);
        jLabel59.setBounds(10, 10, 100, 10);

        dctic7.setDateFormatString("dd/MM/yyyy");
        pnlou.add(dctic7);
        dctic7.setBounds(10, 20, 120, 24);

        dctic8.setDateFormatString("dd/MM/yyyy");
        pnlou.add(dctic8);
        dctic8.setBounds(130, 20, 120, 24);

        jLabel60.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel60.setText("Until");
        pnlou.add(jLabel60);
        jLabel60.setBounds(130, 10, 100, 10);

        jLabel61.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel61.setText("Agen");
        pnlou.add(jLabel61);
        jLabel61.setBounds(260, 10, 100, 10);

        btnoutsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnoutsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnoutsrch.setText("Search By");
        btnoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoutsrchActionPerformed(evt);
            }
        });
        pnlou.add(btnoutsrch);
        btnoutsrch.setBounds(370, 20, 120, 24);

        cbagenou.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenou.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlou.add(cbagenou);
        cbagenou.setBounds(260, 20, 100, 24);

        lblcaloutcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlou.add(lblcaloutcount);
        lblcaloutcount.setBounds(880, 0, 40, 10);

        lblrepticcount11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount11.setText("list");
        pnlou.add(lblrepticcount11);
        lblrepticcount11.setBounds(920, 0, 40, 10);

        jtab.addTab("OutBound", pnlou);

        pnlDist.setBackground(new java.awt.Color(255, 255, 255));
        pnlDist.setLayout(null);

        btnoutsrch1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnoutsrch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnoutsrch1.setText("Search");
        btnoutsrch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoutsrch1ActionPerformed(evt);
            }
        });
        pnlDist.add(btnoutsrch1);
        btnoutsrch1.setBounds(520, 20, 99, 24);

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

        pnlDist.add(jScrollPane22);
        jScrollPane22.setBounds(10, 40, 950, 360);

        cbDist.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbDist.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlDist.add(cbDist);
        cbDist.setBounds(10, 20, 160, 0);

        jLabel97.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel97.setText("Address");
        pnlDist.add(jLabel97);
        jLabel97.setBounds(330, 10, 160, 10);

        lblcaloutcount2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlDist.add(lblcaloutcount2);
        lblcaloutcount2.setBounds(850, 30, 40, 10);

        lblrepticcount14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount14.setText("list");
        pnlDist.add(lblrepticcount14);
        lblrepticcount14.setBounds(890, 30, 40, 10);

        jLabel98.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel98.setText("Distributor");
        pnlDist.add(jLabel98);
        jLabel98.setBounds(10, 10, 160, 0);

        jLabel99.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel99.setText("Dealer");
        pnlDist.add(jLabel99);
        jLabel99.setBounds(10, 10, 160, 10);

        jLabel100.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel100.setText("Contact Person");
        pnlDist.add(jLabel100);
        jLabel100.setBounds(170, 10, 160, 10);

        txtBpName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlDist.add(txtBpName);
        txtBpName.setBounds(10, 20, 160, 24);

        txtOwner.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlDist.add(txtOwner);
        txtOwner.setBounds(170, 20, 160, 24);

        txtBpAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlDist.add(txtBpAdd);
        txtBpAdd.setBounds(330, 20, 160, 24);

        jtab.addTab("Contact", pnlDist);

        pnlact.setBackground(new java.awt.Color(255, 255, 255));
        pnlact.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlact.setLayout(null);

        tblact.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Level", "Activity", "Info", "Loggin", "Host address", "Line number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblact.setRowHeight(20);
        tblact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblactMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblactMouseExited(evt);
            }
        });
        jScrollPane5.setViewportView(tblact);

        pnlact.add(jScrollPane5);
        jScrollPane5.setBounds(10, 30, 950, 400);

        btnrelease.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnrelease.setText("Release");
        btnrelease.setEnabled(false);
        btnrelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreleaseActionPerformed(evt);
            }
        });
        pnlact.add(btnrelease);
        btnrelease.setBounds(860, 10, 100, 24);

        cbagenrelease.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenrelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbagenreleaseActionPerformed(evt);
            }
        });
        pnlact.add(cbagenrelease);
        cbagenrelease.setBounds(690, 10, 170, 24);

        jtab.addTab("Activity Monitoring", pnlact);

        panelsms.setBackground(new java.awt.Color(255, 255, 255));
        panelsms.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        panelsms.setOpaque(true);

        pninbox.setBackground(new java.awt.Color(255, 255, 255));
        pninbox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pninbox.setLayout(null);

        tblsin.setAutoCreateRowSorter(true);
        tblsin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblsin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sender", "Read", "Replied", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsin.setRowHeight(20);
        tblsin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsinMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblsin);

        pninbox.add(jScrollPane6);
        jScrollPane6.setBounds(10, 40, 930, 180);

        dtsi.setDateFormatString("dd/MM/yyyy");
        pninbox.add(dtsi);
        dtsi.setBounds(10, 20, 120, 24);

        dtsi1.setDateFormatString("dd/MM/yyyy");
        pninbox.add(dtsi1);
        dtsi1.setBounds(130, 20, 120, 24);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("From :");
        pninbox.add(jLabel1);
        jLabel1.setBounds(10, 10, 100, 10);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Until :");
        pninbox.add(jLabel2);
        jLabel2.setBounds(130, 10, 100, 10);

        jLabel47.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel47.setText("Sender");
        pninbox.add(jLabel47);
        jLabel47.setBounds(10, 240, 100, 20);

        jLabel49.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel49.setText("messages :");
        pninbox.add(jLabel49);
        jLabel49.setBounds(10, 260, 100, 20);

        txtimsg2.setColumns(20);
        txtimsg2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtimsg2.setLineWrap(true);
        txtimsg2.setRows(5);
        jScrollPane19.setViewportView(txtimsg2);

        pninbox.add(jScrollPane19);
        jScrollPane19.setBounds(110, 260, 550, 91);

        txtfrom2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pninbox.add(txtfrom2);
        txtfrom2.setBounds(110, 240, 250, 24);

        btnsmsinsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsmsinsrch.setText("Search");
        btnsmsinsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsinsrchActionPerformed(evt);
            }
        });
        pninbox.add(btnsmsinsrch);
        btnsmsinsrch.setBounds(260, 20, 90, 24);

        jLabel80.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Cust. Company");
        pninbox.add(jLabel80);
        jLabel80.setBounds(360, 240, 100, 0);

        cbcust.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cbcust.setMaximumRowCount(9);
        cbcust.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcustActionPerformed(evt);
            }
        });
        pninbox.add(cbcust);
        cbcust.setBounds(460, 240, 200, 0);

        btncussavesms.setFont(btncussavesms.getFont().deriveFont(btncussavesms.getFont().getStyle() | java.awt.Font.BOLD));
        btncussavesms.setText("Save");
        btncussavesms.setEnabled(false);
        btncussavesms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncussavesmsActionPerformed(evt);
            }
        });
        pninbox.add(btncussavesms);
        btncussavesms.setBounds(720, 240, 80, 0);

        jLabel91.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel91.setText("Ticket No");
        pninbox.add(jLabel91);
        jLabel91.setBounds(660, 260, 100, 0);

        txtnoticsms.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        pninbox.add(txtnoticsms);
        txtnoticsms.setBounds(660, 280, 140, 0);

        btnsmsCALL.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnsmsCALL.setText("CALL");
        btnsmsCALL.setEnabled(false);
        btnsmsCALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsCALLActionPerformed(evt);
            }
        });
        pninbox.add(btnsmsCALL);
        btnsmsCALL.setBounds(370, 240, 70, 24);

        panelsms.addTab("InBox", pninbox);

        pnoutbox.setBackground(new java.awt.Color(255, 255, 255));
        pnoutbox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnoutbox.setLayout(null);

        tblsou.setAutoCreateRowSorter(true);
        tblsou.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblsou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sent Time", "Send by", "Recipient", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsou.setRowHeight(20);
        tblsou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsouMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblsou);

        pnoutbox.add(jScrollPane7);
        jScrollPane7.setBounds(10, 40, 930, 180);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("From :");
        pnoutbox.add(jLabel3);
        jLabel3.setBounds(10, 10, 100, 10);

        dtso.setDateFormatString("dd/MM/yyyy");
        pnoutbox.add(dtso);
        dtso.setBounds(10, 20, 120, 24);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Until :");
        pnoutbox.add(jLabel4);
        jLabel4.setBounds(130, 10, 100, 10);

        dtso1.setDateFormatString("dd/MM/yyyy");
        pnoutbox.add(dtso1);
        dtso1.setBounds(130, 20, 120, 24);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setText("Recipient");
        pnoutbox.add(jLabel8);
        jLabel8.setBounds(10, 240, 100, 20);

        jLabel35.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel35.setText("messages :");
        pnoutbox.add(jLabel35);
        jLabel35.setBounds(10, 260, 100, 20);

        txtimsg1.setColumns(20);
        txtimsg1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtimsg1.setLineWrap(true);
        txtimsg1.setRows(5);
        jScrollPane18.setViewportView(txtimsg1);

        pnoutbox.add(jScrollPane18);
        jScrollPane18.setBounds(110, 260, 510, 86);

        txtfrom1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnoutbox.add(txtfrom1);
        txtfrom1.setBounds(110, 240, 250, 24);

        btnsmsoutsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsmsoutsrch.setText("Search");
        btnsmsoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsoutsrchActionPerformed(evt);
            }
        });
        pnoutbox.add(btnsmsoutsrch);
        btnsmsoutsrch.setBounds(260, 20, 90, 24);

        panelsms.addTab("OutBox", pnoutbox);

        jtab.addTab("Sms", panelsms);

        panelmail.setBackground(new java.awt.Color(255, 255, 255));
        panelmail.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        panelmail.setOpaque(true);
        panelmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelmailMouseClicked(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel9.setLayout(null);

        tblmin.setAutoCreateRowSorter(true);
        tblmin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "From", "Subject", "Date", "Read", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmin.setRowHeight(20);
        tblmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblminMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblmin);

        jPanel9.add(jScrollPane8);
        jScrollPane8.setBounds(10, 40, 950, 180);

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("From :");
        jPanel9.add(jLabel5);
        jLabel5.setBounds(10, 10, 100, 10);

        txtfrom.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel9.add(txtfrom);
        txtfrom.setBounds(110, 240, 300, 24);

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setText("Subject :");
        jPanel9.add(jLabel11);
        jLabel11.setBounds(10, 260, 100, 20);

        txtisu.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtisu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtisuActionPerformed(evt);
            }
        });
        jPanel9.add(txtisu);
        txtisu.setBounds(110, 260, 700, 24);

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel12.setText("messages :");
        jPanel9.add(jLabel12);
        jLabel12.setBounds(10, 280, 100, 20);

        txtimsg.setColumns(20);
        txtimsg.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtimsg.setLineWrap(true);
        txtimsg.setRows(5);
        txtimsg.setAutoscrolls(false);
        jScrollPane9.setViewportView(txtimsg);

        jPanel9.add(jScrollPane9);
        jScrollPane9.setBounds(110, 280, 700, 110);

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel18.setText("From :");
        jPanel9.add(jLabel18);
        jLabel18.setBounds(10, 240, 100, 20);

        dtmi.setDateFormatString("dd/MM/yyyy");
        jPanel9.add(dtmi);
        dtmi.setBounds(10, 20, 120, 24);

        jLabel48.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel48.setText("Until :");
        jPanel9.add(jLabel48);
        jLabel48.setBounds(130, 10, 100, 10);

        dtmi1.setDateFormatString("dd/MM/yyyy");
        jPanel9.add(dtmi1);
        dtmi1.setBounds(130, 20, 120, 24);

        btnmailinsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnmailinsrch.setText("Search");
        btnmailinsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailinsrchActionPerformed(evt);
            }
        });
        jPanel9.add(btnmailinsrch);
        btnmailinsrch.setBounds(260, 20, 90, 24);

        jLabel81.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Cust. Company");
        jPanel9.add(jLabel81);
        jLabel81.setBounds(410, 240, 100, 0);

        cbcust1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cbcust1.setMaximumRowCount(9);
        cbcust1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcust1ActionPerformed(evt);
            }
        });
        jPanel9.add(cbcust1);
        cbcust1.setBounds(510, 240, 200, 0);

        btncussaveEmail.setFont(btncussaveEmail.getFont().deriveFont(btncussaveEmail.getFont().getStyle() | java.awt.Font.BOLD));
        btncussaveEmail.setText("Save");
        btncussaveEmail.setEnabled(false);
        btncussaveEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncussaveEmailActionPerformed(evt);
            }
        });
        jPanel9.add(btncussaveEmail);
        btncussaveEmail.setBounds(730, 240, 80, 0);

        jLabel92.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel92.setText("Ticket No");
        jPanel9.add(jLabel92);
        jLabel92.setBounds(810, 220, 100, 0);

        txtnoticmail.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jPanel9.add(txtnoticmail);
        txtnoticmail.setBounds(810, 240, 150, 0);

        btnAttachment.setFont(btnAttachment.getFont().deriveFont(btnAttachment.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnAttachment.setText("Download");
        btnAttachment.setEnabled(false);
        btnAttachment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachmentActionPerformed(evt);
            }
        });
        jPanel9.add(btnAttachment);
        btnAttachment.setBounds(860, 260, 100, 0);

        jList2.setFont(jList2.getFont().deriveFont((float)11));
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList2MouseReleased(evt);
            }
        });
        scpCcList1.setViewportView(jList2);

        jPanel9.add(scpCcList1);
        scpCcList1.setBounds(810, 280, 150, 0);

        btnEmailCALL.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnEmailCALL.setText("CALL");
        btnEmailCALL.setEnabled(false);
        btnEmailCALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailCALLActionPerformed(evt);
            }
        });
        jPanel9.add(btnEmailCALL);
        btnEmailCALL.setBounds(420, 240, 70, 24);

        btnEmailCALL1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnEmailCALL1.setText("REPLY");
        btnEmailCALL1.setEnabled(false);
        btnEmailCALL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailCALL1ActionPerformed(evt);
            }
        });
        jPanel9.add(btnEmailCALL1);
        btnEmailCALL1.setBounds(490, 240, 70, 24);

        panelmail.addTab("InBox", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel10.setLayout(null);

        tblmou.setAutoCreateRowSorter(true);
        tblmou.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblmou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "To", "Subject", "Date", "Cc", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmou.setRowHeight(20);
        tblmou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmouMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblmou);

        jPanel10.add(jScrollPane10);
        jScrollPane10.setBounds(10, 40, 950, 180);

        txtoto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel10.add(txtoto);
        txtoto.setBounds(110, 240, 300, 24);

        jLabel13.setBackground(new java.awt.Color(255, 255, 204));
        jLabel13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel13.setText("To :");
        jPanel10.add(jLabel13);
        jLabel13.setBounds(10, 240, 100, 20);

        txtocc.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel10.add(txtocc);
        txtocc.setBounds(510, 240, 300, 24);

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cc :");
        jPanel10.add(jLabel14);
        jLabel14.setBounds(410, 240, 100, 20);

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel15.setText("Subject :");
        jPanel10.add(jLabel15);
        jLabel15.setBounds(10, 260, 100, 20);

        txtosu.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel10.add(txtosu);
        txtosu.setBounds(110, 260, 700, 24);

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel16.setText("messages :");
        jPanel10.add(jLabel16);
        jLabel16.setBounds(10, 280, 100, 20);

        txtomsg.setColumns(20);
        txtomsg.setEditable(false);
        txtomsg.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtomsg.setLineWrap(true);
        txtomsg.setRows(5);
        txtomsg.setAutoscrolls(false);
        jScrollPane11.setViewportView(txtomsg);

        jPanel10.add(jScrollPane11);
        jScrollPane11.setBounds(110, 280, 700, 110);

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Ticket Id. :");
        jPanel10.add(jLabel17);
        jLabel17.setBounds(210, 220, 100, 0);

        txtidti.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jPanel10.add(txtidti);
        txtidti.setBounds(310, 220, 100, 0);

        jLabel50.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel50.setText("From :");
        jPanel10.add(jLabel50);
        jLabel50.setBounds(10, 10, 100, 10);

        dtmo.setDateFormatString("dd/MM/yyyy");
        jPanel10.add(dtmo);
        dtmo.setBounds(10, 20, 120, 24);

        jLabel51.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel51.setText("Until :");
        jPanel10.add(jLabel51);
        jLabel51.setBounds(110, 10, 100, 10);

        dtmo1.setDateFormatString("dd/MM/yyyy");
        jPanel10.add(dtmo1);
        dtmo1.setBounds(130, 20, 120, 24);

        btnmailoutsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnmailoutsrch.setText("Search");
        btnmailoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailoutsrchActionPerformed(evt);
            }
        });
        jPanel10.add(btnmailoutsrch);
        btnmailoutsrch.setBounds(260, 20, 90, 24);

        jList3.setFont(jList3.getFont().deriveFont((float)11));
        jList3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList3MouseReleased(evt);
            }
        });
        scpCcList2.setViewportView(jList3);

        jPanel10.add(scpCcList2);
        scpCcList2.setBounds(810, 280, 150, 0);

        btnAttachment1.setFont(btnAttachment1.getFont().deriveFont(btnAttachment1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnAttachment1.setText("Download");
        btnAttachment1.setEnabled(false);
        btnAttachment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachment1ActionPerformed(evt);
            }
        });
        jPanel10.add(btnAttachment1);
        btnAttachment1.setBounds(860, 260, 100, 0);

        panelmail.addTab("OutBox", jPanel10);

        jtab.addTab("Email", panelmail);

        tabbpanereport.setBackground(new java.awt.Color(255, 255, 255));
        tabbpanereport.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabbpanereport.setOpaque(true);

        pnlrep1.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep1.setLayout(null);

        tblrepcal.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblrepcal.setModel(new javax.swing.table.DefaultTableModel(
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
        tblrepcal.setRowHeight(20);
        jScrollPane13.setViewportView(tblrepcal);

        pnlrep1.add(jScrollPane13);
        jScrollPane13.setBounds(10, 40, 950, 340);

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel21.setText("Call Status");
        pnlrep1.add(jLabel21);
        jLabel21.setBounds(260, 10, 100, 10);

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel22.setText("Direction");
        pnlrep1.add(jLabel22);
        jLabel22.setBounds(360, 10, 100, 10);

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel23.setText("Caller Type");
        pnlrep1.add(jLabel23);
        jLabel23.setBounds(460, 10, 100, 10);

        btnrepcal.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnrepcal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepcal.setText("Search Call");
        btnrepcal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepcalActionPerformed(evt);
            }
        });
        pnlrep1.add(btnrepcal);
        btnrepcal.setBounds(670, 20, 130, 24);

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel24.setText("Agen");
        pnlrep1.add(jLabel24);
        jLabel24.setBounds(560, 10, 100, 10);

        jLabel39.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel39.setText("Open From");
        pnlrep1.add(jLabel39);
        jLabel39.setBounds(10, 10, 100, 10);

        dccal1.setDateFormatString("dd/MM/yyyy");
        pnlrep1.add(dccal1);
        dccal1.setBounds(10, 20, 120, 24);

        jLabel40.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel40.setText("Until");
        pnlrep1.add(jLabel40);
        jLabel40.setBounds(130, 10, 100, 10);

        dccal2.setDateFormatString("dd/MM/yyyy");
        pnlrep1.add(dccal2);
        dccal2.setBounds(130, 20, 120, 24);

        cbcaldir.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbcaldir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbcaldir.setSelectedIndex(2);
        pnlrep1.add(cbcaldir);
        cbcaldir.setBounds(360, 20, 100, 24);

        cbcalstat.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbcalstat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ABANDON", "ANSWERED", "PHANTOM", "--" }));
        cbcalstat.setSelectedIndex(3);
        cbcalstat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcalstatActionPerformed(evt);
            }
        });
        pnlrep1.add(cbcalstat);
        cbcalstat.setBounds(260, 20, 100, 24);

        cbagenirepcal.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenirepcal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david" }));
        pnlrep1.add(cbagenirepcal);
        cbagenirepcal.setBounds(560, 20, 100, 24);

        cbcaltyperepcal.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbcaltyperepcal.setMaximumRowCount(10);
        cbcaltyperepcal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other", "--" }));
        cbcaltyperepcal.setSelectedIndex(10);
        pnlrep1.add(cbcaltyperepcal);
        cbcaltyperepcal.setBounds(460, 20, 100, 24);

        btnexportcall.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall.setText("Export");
        btnexportcall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcallActionPerformed(evt);
            }
        });
        pnlrep1.add(btnexportcall);
        btnexportcall.setBounds(10, 380, 90, 20);

        lblrepcalcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep1.add(lblrepcalcount);
        lblrepcalcount.setBounds(880, 0, 40, 10);

        lblrepticcount3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount3.setText("list");
        pnlrep1.add(lblrepticcount3);
        lblrepticcount3.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("Calls", pnlrep1);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(null);

        tblhourin.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane39.setViewportView(tblhourin);

        jPanel17.add(jScrollPane39);
        jScrollPane39.setBounds(10, 40, 940, 310);

        jLabel78.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel78.setText("Date");
        jPanel17.add(jLabel78);
        jLabel78.setBounds(10, 10, 100, 10);

        dthi.setDateFormatString("dd/MM/yyyy");
        jPanel17.add(dthi);
        dthi.setBounds(10, 20, 120, 24);

        btnhi.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnhi.setText("Refresh");
        btnhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhiActionPerformed(evt);
            }
        });
        jPanel17.add(btnhi);
        btnhi.setBounds(150, 20, 115, 24);

        btnexportcall1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall1.setText("Export");
        btnexportcall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnexportcall1);
        btnexportcall1.setBounds(10, 350, 90, 20);

        jTabbedPane1.addTab("Inbound", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(null);

        tblhourout.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane40.setViewportView(tblhourout);

        jPanel18.add(jScrollPane40);
        jScrollPane40.setBounds(10, 40, 940, 310);

        dtho.setDateFormatString("dd/MM/yyyy");
        jPanel18.add(dtho);
        dtho.setBounds(10, 20, 120, 24);

        jLabel79.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel79.setText("Date");
        jPanel18.add(jLabel79);
        jLabel79.setBounds(10, 10, 100, 10);

        btnho.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnho.setText("Refresh");
        btnho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoActionPerformed(evt);
            }
        });
        jPanel18.add(btnho);
        btnho.setBounds(150, 20, 115, 24);

        btnexportcall2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall2.setText("Export");
        btnexportcall2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall2ActionPerformed(evt);
            }
        });
        jPanel18.add(btnexportcall2);
        btnexportcall2.setBounds(10, 350, 90, 20);

        jTabbedPane1.addTab("Outbound", jPanel18);

        tabbpanereport.addTab("Hourly Calls", jTabbedPane1);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(null);

        tbldailyin.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane43.setViewportView(tbldailyin);

        jPanel21.add(jScrollPane43);
        jScrollPane43.setBounds(10, 40, 940, 310);

        jLabel82.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel82.setText("From");
        jPanel21.add(jLabel82);
        jLabel82.setBounds(10, 10, 100, 10);

        dtdi.setDateFormatString("dd/MM/yyyy");
        jPanel21.add(dtdi);
        dtdi.setBounds(10, 20, 120, 24);

        btndi.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btndi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btndi.setText("Refresh");
        btndi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndiActionPerformed(evt);
            }
        });
        jPanel21.add(btndi);
        btndi.setBounds(270, 20, 115, 24);

        jLabel84.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel84.setText("Until");
        jPanel21.add(jLabel84);
        jLabel84.setBounds(140, 10, 100, 10);

        dtdi1.setDateFormatString("dd/MM/yyyy");
        jPanel21.add(dtdi1);
        dtdi1.setBounds(140, 20, 120, 24);

        btnexportcall3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall3.setText("Export");
        btnexportcall3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall3ActionPerformed(evt);
            }
        });
        jPanel21.add(btnexportcall3);
        btnexportcall3.setBounds(10, 350, 90, 20);

        jTabbedPane4.addTab("Inbound", jPanel21);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(null);

        tbldailyout.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane44.setViewportView(tbldailyout);

        jPanel22.add(jScrollPane44);
        jScrollPane44.setBounds(10, 40, 940, 310);

        dtdo.setDateFormatString("dd/MM/yyyy");
        jPanel22.add(dtdo);
        dtdo.setBounds(10, 20, 120, 24);

        jLabel83.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel83.setText("From");
        jPanel22.add(jLabel83);
        jLabel83.setBounds(10, 10, 100, 10);

        btndo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btndo.setText("Refresh");
        btndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndoActionPerformed(evt);
            }
        });
        jPanel22.add(btndo);
        btndo.setBounds(270, 20, 115, 24);

        jLabel85.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel85.setText("Until");
        jPanel22.add(jLabel85);
        jLabel85.setBounds(140, 10, 100, 10);

        dtdo1.setDateFormatString("dd/MM/yyyy");
        jPanel22.add(dtdo1);
        dtdo1.setBounds(140, 20, 120, 24);

        btnexportcall4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall4.setText("Export");
        btnexportcall4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall4ActionPerformed(evt);
            }
        });
        jPanel22.add(btnexportcall4);
        btnexportcall4.setBounds(10, 350, 90, 20);

        jTabbedPane4.addTab("Outbound", jPanel22);

        tabbpanereport.addTab("Daily Calls", jTabbedPane4);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(null);

        tblperformin.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane41.setViewportView(tblperformin);

        jPanel19.add(jScrollPane41);
        jScrollPane41.setBounds(10, 40, 940, 310);

        dtpi.setDateFormatString("dd/MM/yyyy");
        jPanel19.add(dtpi);
        dtpi.setBounds(10, 20, 120, 24);

        btnpi1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnpi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnpi1.setText("Refresh");
        btnpi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpi1ActionPerformed(evt);
            }
        });
        jPanel19.add(btnpi1);
        btnpi1.setBounds(270, 20, 115, 24);

        jLabel86.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel86.setText("Until");
        jPanel19.add(jLabel86);
        jLabel86.setBounds(140, 10, 100, 10);

        dtpi1.setDateFormatString("dd/MM/yyyy");
        jPanel19.add(dtpi1);
        dtpi1.setBounds(140, 20, 120, 24);

        jLabel88.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel88.setText("From");
        jPanel19.add(jLabel88);
        jLabel88.setBounds(10, 10, 100, 10);

        btnexportcall5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall5.setText("Export");
        btnexportcall5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall5ActionPerformed(evt);
            }
        });
        jPanel19.add(btnexportcall5);
        btnexportcall5.setBounds(10, 350, 90, 20);

        jTabbedPane3.addTab("Inbound", jPanel19);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(null);

        tblperformout.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane42.setViewportView(tblperformout);

        jPanel20.add(jScrollPane42);
        jScrollPane42.setBounds(10, 40, 940, 310);

        dtpo.setDateFormatString("dd/MM/yyyy");
        jPanel20.add(dtpo);
        dtpo.setBounds(10, 20, 120, 24);

        btnpo1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnpo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnpo1.setText("Refresh");
        btnpo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpo1ActionPerformed(evt);
            }
        });
        jPanel20.add(btnpo1);
        btnpo1.setBounds(270, 20, 115, 24);

        jLabel87.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel87.setText("Until");
        jPanel20.add(jLabel87);
        jLabel87.setBounds(140, 10, 100, 10);

        dtpo1.setDateFormatString("dd/MM/yyyy");
        jPanel20.add(dtpo1);
        dtpo1.setBounds(140, 20, 120, 24);

        jLabel89.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel89.setText("From");
        jPanel20.add(jLabel89);
        jLabel89.setBounds(10, 10, 100, 10);

        btnexportcall6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportcall6.setText("Export");
        btnexportcall6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall6ActionPerformed(evt);
            }
        });
        jPanel20.add(btnexportcall6);
        btnexportcall6.setBounds(10, 350, 90, 20);

        jTabbedPane3.addTab("Outbound", jPanel20);

        tabbpanereport.addTab("Performance", jTabbedPane3);

        pnlrep2.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep2.setLayout(null);

        tblrepsms.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblrepsms.setModel(new javax.swing.table.DefaultTableModel(
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
        tblrepsms.setRowHeight(20);
        jScrollPane14.setViewportView(tblrepsms);

        pnlrep2.add(jScrollPane14);
        jScrollPane14.setBounds(10, 40, 950, 340);

        txtsmsstat.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep2.add(txtsmsstat);
        txtsmsstat.setBounds(260, 20, 100, 24);

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel25.setText("Status");
        pnlrep2.add(jLabel25);
        jLabel25.setBounds(260, 10, 100, 10);

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel26.setText("Direction");
        pnlrep2.add(jLabel26);
        jLabel26.setBounds(360, 10, 100, 10);

        btnrepsms.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnrepsms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepsms.setText("Search Messages");
        btnrepsms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepsmsActionPerformed(evt);
            }
        });
        pnlrep2.add(btnrepsms);
        btnrepsms.setBounds(570, 20, 170, 24);

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel28.setText("Agen");
        pnlrep2.add(jLabel28);
        jLabel28.setBounds(460, 10, 100, 10);

        jLabel43.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel43.setText("Open From");
        pnlrep2.add(jLabel43);
        jLabel43.setBounds(10, 10, 100, 10);

        dcsms1.setDateFormatString("dd/MM/yyyy");
        pnlrep2.add(dcsms1);
        dcsms1.setBounds(10, 20, 120, 24);

        jLabel44.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel44.setText("Until");
        pnlrep2.add(jLabel44);
        jLabel44.setBounds(130, 10, 100, 10);

        dcsms2.setDateFormatString("dd/MM/yyyy");
        pnlrep2.add(dcsms2);
        dcsms2.setBounds(130, 20, 120, 24);

        txtsmsticid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep2.add(txtsmsticid);
        txtsmsticid.setBounds(460, 20, 100, 0);

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel27.setText("Ticket No");
        pnlrep2.add(jLabel27);
        jLabel27.setBounds(460, 10, 100, 0);

        cbdirrepsms.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbdirrepsms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbdirrepsms.setSelectedIndex(2);
        pnlrep2.add(cbdirrepsms);
        cbdirrepsms.setBounds(360, 20, 100, 24);

        cbagenirepcal1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenirepcal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlrep2.add(cbagenirepcal1);
        cbagenirepcal1.setBounds(460, 20, 100, 24);

        btnexportsms.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportsms.setText("Export");
        btnexportsms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportsmsActionPerformed(evt);
            }
        });
        pnlrep2.add(btnexportsms);
        btnexportsms.setBounds(10, 380, 90, 20);

        lblrepsmscount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep2.add(lblrepsmscount);
        lblrepsmscount.setBounds(880, 0, 40, 10);

        lblrepticcount5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount5.setText("list");
        pnlrep2.add(lblrepticcount5);
        lblrepticcount5.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("SMS", pnlrep2);

        pnlrep3.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep3.setLayout(null);

        tblrepmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblrepmail.setModel(new javax.swing.table.DefaultTableModel(
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
        tblrepmail.setRowHeight(20);
        jScrollPane15.setViewportView(tblrepmail);

        pnlrep3.add(jScrollPane15);
        jScrollPane15.setBounds(10, 40, 950, 340);

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel29.setText("Direction");
        pnlrep3.add(jLabel29);
        jLabel29.setBounds(360, 10, 100, 10);

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel30.setText("Agen");
        pnlrep3.add(jLabel30);
        jLabel30.setBounds(460, 10, 100, 10);

        txtmailticid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep3.add(txtmailticid);
        txtmailticid.setBounds(460, 20, 100, 0);

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel31.setText("Ticket no");
        pnlrep3.add(jLabel31);
        jLabel31.setBounds(460, 10, 100, 0);

        btnrepmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnrepmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepmail.setText("Search Mail");
        btnrepmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepmailActionPerformed(evt);
            }
        });
        pnlrep3.add(btnrepmail);
        btnrepmail.setBounds(570, 20, 140, 24);

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel32.setText("Mail subject");
        pnlrep3.add(jLabel32);
        jLabel32.setBounds(260, 10, 100, 10);

        txtmailsub.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep3.add(txtmailsub);
        txtmailsub.setBounds(260, 20, 100, 24);

        jLabel45.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel45.setText("Open From");
        pnlrep3.add(jLabel45);
        jLabel45.setBounds(10, 10, 100, 10);

        dcmail1.setDateFormatString("dd/MM/yyyy");
        pnlrep3.add(dcmail1);
        dcmail1.setBounds(10, 20, 120, 24);

        jLabel46.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel46.setText("Until");
        pnlrep3.add(jLabel46);
        jLabel46.setBounds(130, 10, 100, 10);

        dcmail2.setDateFormatString("dd/MM/yyyy");
        pnlrep3.add(dcmail2);
        dcmail2.setBounds(130, 20, 120, 24);

        cbdirmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbdirmail.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbdirmail.setSelectedIndex(2);
        pnlrep3.add(cbdirmail);
        cbdirmail.setBounds(360, 20, 100, 24);

        cbagenrepmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenrepmail.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlrep3.add(cbagenrepmail);
        cbagenrepmail.setBounds(460, 20, 100, 24);

        btnexportmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportmail.setText("Export");
        btnexportmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportmailActionPerformed(evt);
            }
        });
        pnlrep3.add(btnexportmail);
        btnexportmail.setBounds(10, 380, 90, 20);

        lblrepmailcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep3.add(lblrepmailcount);
        lblrepmailcount.setBounds(880, 0, 40, 10);

        lblrepticcount7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount7.setText("list");
        pnlrep3.add(lblrepticcount7);
        lblrepticcount7.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("Email", pnlrep3);

        jtab.addTab("Report", tabbpanereport);

        pnlinf.setBackground(new java.awt.Color(255, 255, 255));
        pnlinf.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(5);
        jTextArea1.setBorder(null);
        jTextArea1.setOpaque(false);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(0, 0, 970, 410);

        pnlinf.addTab("ORange", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(null);

        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setTabSize(5);
        jTextArea2.setBorder(null);
        jTextArea2.setOpaque(false);
        jScrollPane20.setViewportView(jTextArea2);

        jPanel4.add(jScrollPane20);
        jScrollPane20.setBounds(0, 0, 970, 430);

        pnlinf.addTab("TV", jPanel4);

        jtab.addTab("Information", pnlinf);

        jPanel2.add(jtab);
        jtab.setBounds(6, 8, 980, 470);

        jPanel2.setBounds(10, 170, 990, 480);
        jdp.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jdp);
        jdp.setBounds(0, 0, 1010, 723);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTabbedPane2.setOpaque(true);

        pninbox1.setBackground(new java.awt.Color(255, 255, 255));
        pninbox1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pninbox1.setLayout(null);

        tblmsin.setAutoCreateRowSorter(true);
        tblmsin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblmsin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sender", "Read", "Replied", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmsin.setRowHeight(20);
        tblmsin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmsinMouseClicked(evt);
            }
        });
        jScrollPane33.setViewportView(tblmsin);

        pninbox1.add(jScrollPane33);
        jScrollPane33.setBounds(10, 40, 930, 180);

        dtmsi.setDateFormatString("dd/MM/yyyy");
        dtmsi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmsiPropertyChange(evt);
            }
        });
        pninbox1.add(dtmsi);
        dtmsi.setBounds(10, 20, 120, 24);

        dtmsi1.setDateFormatString("dd/MM/yyyy");
        dtmsi1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmsi1PropertyChange(evt);
            }
        });
        pninbox1.add(dtmsi1);
        dtmsi1.setBounds(130, 20, 120, 24);

        jLabel66.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel66.setText("From :");
        pninbox1.add(jLabel66);
        jLabel66.setBounds(10, 10, 100, 10);

        jLabel67.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel67.setText("Until :");
        pninbox1.add(jLabel67);
        jLabel67.setBounds(130, 10, 100, 10);

        jLabel70.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel70.setText("messages :");
        pninbox1.add(jLabel70);
        jLabel70.setBounds(10, 230, 100, 20);

        txtimsg3.setColumns(20);
        txtimsg3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtimsg3.setLineWrap(true);
        txtimsg3.setRows(5);
        jScrollPane34.setViewportView(txtimsg3);

        pninbox1.add(jScrollPane34);
        jScrollPane34.setBounds(110, 230, 830, 91);

        btnreplymsg.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnreplymsg.setText("Reply");
        btnreplymsg.setEnabled(false);
        btnreplymsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreplymsgActionPerformed(evt);
            }
        });
        pninbox1.add(btnreplymsg);
        btnreplymsg.setBounds(260, 20, 80, 24);

        btndelmsg.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btndelmsg.setText("Delete");
        btndelmsg.setEnabled(false);
        btndelmsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelmsgActionPerformed(evt);
            }
        });
        pninbox1.add(btndelmsg);
        btndelmsg.setBounds(360, 20, 80, 24);

        jTabbedPane2.addTab("InBox", pninbox1);

        pnoutbox1.setBackground(new java.awt.Color(255, 255, 255));
        pnoutbox1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnoutbox1.setLayout(null);

        tblmsou.setAutoCreateRowSorter(true);
        tblmsou.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblmsou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sent Time", "Send by", "Recipient", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmsou.setRowHeight(20);
        tblmsou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmsouMouseClicked(evt);
            }
        });
        jScrollPane35.setViewportView(tblmsou);

        pnoutbox1.add(jScrollPane35);
        jScrollPane35.setBounds(10, 40, 930, 180);

        jLabel71.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel71.setText("From :");
        pnoutbox1.add(jLabel71);
        jLabel71.setBounds(10, 10, 100, 10);

        dtmso.setDateFormatString("dd/MM/yyyy");
        dtmso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmsoPropertyChange(evt);
            }
        });
        pnoutbox1.add(dtmso);
        dtmso.setBounds(10, 20, 120, 24);

        jLabel72.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel72.setText("Until :");
        pnoutbox1.add(jLabel72);
        jLabel72.setBounds(130, 10, 100, 10);

        dtmso1.setDateFormatString("dd/MM/yyyy");
        dtmso1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmso1PropertyChange(evt);
            }
        });
        pnoutbox1.add(dtmso1);
        dtmso1.setBounds(130, 20, 120, 24);

        jLabel74.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel74.setText("messages :");
        pnoutbox1.add(jLabel74);
        jLabel74.setBounds(10, 230, 100, 20);

        txtimsg4.setColumns(20);
        txtimsg4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txtimsg4.setLineWrap(true);
        txtimsg4.setRows(5);
        jScrollPane36.setViewportView(txtimsg4);

        pnoutbox1.add(jScrollPane36);
        jScrollPane36.setBounds(110, 230, 830, 90);

        btncomposemsg.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btncomposemsg.setText("Compose");
        btncomposemsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomposemsgActionPerformed(evt);
            }
        });
        pnoutbox1.add(btncomposemsg);
        btncomposemsg.setBounds(360, 20, 85, 24);

        btndelmsg1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btndelmsg1.setText("Delete");
        btndelmsg1.setEnabled(false);
        btndelmsg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelmsg1ActionPerformed(evt);
            }
        });
        pnoutbox1.add(btndelmsg1);
        btndelmsg1.setBounds(260, 20, 80, 24);

        jTabbedPane2.addTab("OutBox", pnoutbox1);

        jTabbedPane5.addTab("Messaging", jTabbedPane2);

        panelfax.setBackground(new java.awt.Color(255, 255, 255));
        panelfax.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        panelfax.setOpaque(true);
        panelfax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelfaxMouseClicked(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel14.setLayout(null);

        tblfin.setAutoCreateRowSorter(true);
        tblfin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblfin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "From", "Subject", "Date", "Read", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblfin.setRowHeight(20);
        tblfin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblfinMouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(tblfin);

        jPanel14.add(jScrollPane28);
        jScrollPane28.setBounds(10, 40, 950, 140);

        jLabel65.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel65.setText("From :");
        jPanel14.add(jLabel65);
        jLabel65.setBounds(10, 10, 100, 10);

        dtfi.setDateFormatString("dd/MM/yyyy");
        jPanel14.add(dtfi);
        dtfi.setBounds(10, 20, 120, 24);

        jLabel69.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel69.setText("Until :");
        jPanel14.add(jLabel69);
        jLabel69.setBounds(130, 10, 100, 10);

        dtfi1.setDateFormatString("dd/MM/yyyy");
        jPanel14.add(dtfi1);
        dtfi1.setBounds(130, 20, 120, 24);

        btnfinsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnfinsrch.setText("Search");
        btnfinsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinsrchActionPerformed(evt);
            }
        });
        jPanel14.add(btnfinsrch);
        btnfinsrch.setBounds(260, 20, 90, 24);

        lblview.setBackground(new java.awt.Color(204, 204, 255));
        lblview.setOpaque(true);
        jScrollPane29.setViewportView(lblview);

        jPanel14.add(jScrollPane29);
        jScrollPane29.setBounds(10, 180, 950, 220);

        jLabel90.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Cust. Company");
        jPanel14.add(jLabel90);
        jLabel90.setBounds(670, 10, 90, 10);

        cbcust2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cbcust2.setMaximumRowCount(9);
        cbcust2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcust2ActionPerformed(evt);
            }
        });
        jPanel14.add(cbcust2);
        cbcust2.setBounds(670, 20, 200, 24);

        btncussaveFax.setFont(btncussaveFax.getFont().deriveFont(btncussaveFax.getFont().getStyle() | java.awt.Font.BOLD));
        btncussaveFax.setText("Save");
        btncussaveFax.setEnabled(false);
        btncussaveFax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncussaveFaxActionPerformed(evt);
            }
        });
        jPanel14.add(btncussaveFax);
        btncussaveFax.setBounds(880, 20, 80, 24);

        jLabel93.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("Ticket No");
        jPanel14.add(jLabel93);
        jLabel93.setBounds(530, 10, 60, 10);

        txtnoticfax.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jPanel14.add(txtnoticfax);
        txtnoticfax.setBounds(530, 20, 140, 24);

        panelfax.addTab("InBox", jPanel14);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel15.setLayout(null);

        tblfou.setAutoCreateRowSorter(true);
        tblfou.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblfou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "To", "Subject", "Date", "Cc", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblfou.setRowHeight(20);
        tblfou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblfouMouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tblfou);

        jPanel15.add(jScrollPane30);
        jScrollPane30.setBounds(10, 40, 950, 140);

        jLabel75.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel75.setText("From :");
        jPanel15.add(jLabel75);
        jLabel75.setBounds(10, 10, 100, 10);

        dtfo.setDateFormatString("dd/MM/yyyy");
        jPanel15.add(dtfo);
        dtfo.setBounds(10, 20, 120, 24);

        jLabel76.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel76.setText("Until :");
        jPanel15.add(jLabel76);
        jLabel76.setBounds(130, 10, 100, 10);

        dtfo1.setDateFormatString("dd/MM/yyyy");
        jPanel15.add(dtfo1);
        dtfo1.setBounds(130, 20, 120, 24);

        btnfoutsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnfoutsrch.setText("Search");
        btnfoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfoutsrchActionPerformed(evt);
            }
        });
        jPanel15.add(btnfoutsrch);
        btnfoutsrch.setBounds(260, 20, 90, 24);

        lblview1.setBackground(new java.awt.Color(204, 204, 255));
        lblview1.setOpaque(true);
        jScrollPane31.setViewportView(lblview1);

        jPanel15.add(jScrollPane31);
        jScrollPane31.setBounds(10, 180, 950, 220);

        panelfax.addTab("OutBox", jPanel15);

        jTabbedPane5.addTab("Fax", panelfax);

        pnlrep4.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep4.setLayout(null);

        tblrepfax.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblrepfax.setModel(new javax.swing.table.DefaultTableModel(
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
        tblrepfax.setRowHeight(20);
        jScrollPane16.setViewportView(tblrepfax);

        pnlrep4.add(jScrollPane16);
        jScrollPane16.setBounds(10, 40, 950, 340);

        txtfaxfinm.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep4.add(txtfaxfinm);
        txtfaxfinm.setBounds(460, 20, 100, 24);

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel33.setText("Status");
        pnlrep4.add(jLabel33);
        jLabel33.setBounds(360, 10, 100, 10);

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel34.setText("File name");
        pnlrep4.add(jLabel34);
        jLabel34.setBounds(460, 10, 100, 10);

        btnrepfax.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnrepfax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepfax.setText("Search Fax");
        btnrepfax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepfaxActionPerformed(evt);
            }
        });
        pnlrep4.add(btnrepfax);
        btnrepfax.setBounds(670, 20, 140, 24);

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel36.setText("Username");
        pnlrep4.add(jLabel36);
        jLabel36.setBounds(560, 10, 100, 10);

        jLabel41.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel41.setText("Open From");
        pnlrep4.add(jLabel41);
        jLabel41.setBounds(10, 10, 100, 10);

        dcfax1.setDateFormatString("dd/MM/yyyy");
        pnlrep4.add(dcfax1);
        dcfax1.setBounds(10, 20, 120, 24);

        jLabel42.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel42.setText("Until");
        pnlrep4.add(jLabel42);
        jLabel42.setBounds(130, 10, 100, 10);

        dcfax2.setDateFormatString("dd/MM/yyyy");
        pnlrep4.add(dcfax2);
        dcfax2.setBounds(130, 20, 120, 24);

        cbstatusrepfax.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep4.add(cbstatusrepfax);
        cbstatusrepfax.setBounds(360, 20, 100, 24);

        btnexportmail1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexportmail1.setText("Export");
        btnexportmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportmail1ActionPerformed(evt);
            }
        });
        pnlrep4.add(btnexportmail1);
        btnexportmail1.setBounds(10, 380, 90, 20);

        cbagenirepfax.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbagenirepfax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlrep4.add(cbagenirepfax);
        cbagenirepfax.setBounds(560, 20, 100, 24);

        jLabel64.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel64.setText("Direction");
        pnlrep4.add(jLabel64);
        jLabel64.setBounds(260, 10, 100, 10);

        cbdirfax.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbdirfax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbdirfax.setSelectedIndex(2);
        pnlrep4.add(cbdirfax);
        cbdirfax.setBounds(260, 20, 100, 24);

        lblrepfaxcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep4.add(lblrepfaxcount);
        lblrepfaxcount.setBounds(880, 0, 40, 10);

        lblrepticcount9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount9.setText("list");
        pnlrep4.add(lblrepticcount9);
        lblrepticcount9.setBounds(920, 0, 40, 10);

        jTabbedPane5.addTab("Fax", pnlrep4);

        pnltic.setBackground(new java.awt.Color(255, 255, 255));
        pnltic.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnltic.setLayout(null);

        tbltic.setAutoCreateRowSorter(true);
        tbltic.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tbltic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltic.setAlignmentX(1.0F);
        tbltic.setAlignmentY(1.0F);
        tbltic.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbltic.setRowHeight(20);
        tbltic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblticMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltic);

        pnltic.add(jScrollPane3);
        jScrollPane3.setBounds(10, 60, 950, 260);

        jButton6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton6.setText("Open Ticket");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnltic.add(jButton6);
        jButton6.setBounds(850, 410, 110, 25);

        dctic3.setDateFormatString("dd/MM/yyyy");
        pnltic.add(dctic3);
        dctic3.setBounds(10, 20, 120, 24);

        jLabel52.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel52.setText("Open From");
        pnltic.add(jLabel52);
        jLabel52.setBounds(30, 10, 80, 10);

        btnticsrch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnticsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnticsrch.setText("Search By");
        btnticsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnticsrchActionPerformed(evt);
            }
        });
        pnltic.add(btnticsrch);
        btnticsrch.setBounds(840, 20, 120, 24);

        dctic4.setDateFormatString("dd/MM/yyyy");
        pnltic.add(dctic4);
        dctic4.setBounds(130, 20, 120, 24);

        jLabel54.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel54.setText("Until");
        pnltic.add(jLabel54);
        jLabel54.setBounds(130, 10, 27, 10);

        btnsenddept.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsenddept.setText("Send to Dept.");
        btnsenddept.setEnabled(false);
        btnsenddept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsenddeptActionPerformed(evt);
            }
        });
        pnltic.add(btnsenddept);
        btnsenddept.setBounds(10, 410, 130, 24);

        cktgl.setBackground(new java.awt.Color(255, 255, 204));
        cktgl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cktgl.setSelected(true);
        cktgl.setOpaque(false);
        cktgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cktglActionPerformed(evt);
            }
        });
        pnltic.add(cktgl);
        cktgl.setBounds(10, 0, 20, 20);

        jLabel73.setText("Solution :");
        pnltic.add(jLabel73);
        jLabel73.setBounds(490, 320, 60, 10);

        jLabel77.setText("Details :");
        pnltic.add(jLabel77);
        jLabel77.setBounds(10, 320, 60, 10);

        txtsolution.setColumns(20);
        txtsolution.setEditable(false);
        txtsolution.setLineWrap(true);
        txtsolution.setRows(5);
        jScrollPane37.setViewportView(txtsolution);

        pnltic.add(jScrollPane37);
        jScrollPane37.setBounds(490, 330, 470, 80);

        txtdetail.setColumns(20);
        txtdetail.setEditable(false);
        txtdetail.setLineWrap(true);
        txtdetail.setRows(5);
        jScrollPane38.setViewportView(txtdetail);

        pnltic.add(jScrollPane38);
        jScrollPane38.setBounds(10, 330, 470, 80);

        lblticcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnltic.add(lblticcount);
        lblticcount.setBounds(880, 0, 40, 10);

        lblrepticcount10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount10.setText("list");
        pnltic.add(lblrepticcount10);
        lblrepticcount10.setBounds(920, 0, 40, 10);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setPreferredSize(new java.awt.Dimension(1200, 60));
        jPanel25.setLayout(null);

        txtticno1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel25.add(txtticno1);
        txtticno1.setBounds(10, 20, 80, 24);

        jLabel68.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel68.setText("Status");
        jPanel25.add(jLabel68);
        jLabel68.setBounds(350, 10, 100, 10);

        jLabel63.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel63.setText("Dept.");
        jPanel25.add(jLabel63);
        jLabel63.setBounds(90, 10, 100, 10);

        cbticstatus.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbticstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPEN", "PROCESS", "PRE-CLOSED", "CLOSED", "--" }));
        cbticstatus.setSelectedIndex(4);
        cbticstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbticstatusActionPerformed(evt);
            }
        });
        jPanel25.add(cbticstatus);
        cbticstatus.setBounds(350, 20, 100, 24);

        cbdept.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbdept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPT. CALL CENTER", "DEPT. KENDARAAN KEBON JERUK", "DEPT. DRIVER", "DEPT. MARKETING", " ", "DEPT. KENDARAAN LUAR KOTA", "--" }));
        cbdept.setSelectedIndex(6);
        jPanel25.add(cbdept);
        cbdept.setBounds(90, 20, 260, 24);

        cksubmit.setBackground(new java.awt.Color(255, 255, 204));
        cksubmit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cksubmit.setText("Not Submitted");
        cksubmit.setOpaque(false);
        cksubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cksubmitActionPerformed(evt);
            }
        });
        jPanel25.add(cksubmit);
        cksubmit.setBounds(710, 20, 120, 20);

        jLabel53.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel53.setText("No Ticket");
        jPanel25.add(jLabel53);
        jLabel53.setBounds(10, 10, 80, 10);

        jLabel94.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel94.setText("Customer");
        jPanel25.add(jLabel94);
        jLabel94.setBounds(450, 10, 80, 10);

        txtcus.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel25.add(txtcus);
        txtcus.setBounds(450, 20, 110, 24);

        jLabel96.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel96.setText("Category");
        jPanel25.add(jLabel96);
        jLabel96.setBounds(560, 10, 100, 10);

        cbcate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbcate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPT. CALL CENTER", "DEPT. KENDARAAN KEBON JERUK", "DEPT. DRIVER", "DEPT. MARKETING", " ", "DEPT. KENDARAAN LUAR KOTA", "--" }));
        cbcate.setSelectedIndex(6);
        cbcate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcateActionPerformed(evt);
            }
        });
        jPanel25.add(cbcate);
        cbcate.setBounds(560, 20, 150, 24);

        jScrollPane47.setViewportView(jPanel25);

        pnltic.add(jScrollPane47);
        jScrollPane47.setBounds(250, 0, 590, 63);

        jTabbedPane5.addTab("Ticket", pnltic);

        jPanel6.setLayout(null);

        tblvrf.setAutoCreateRowSorter(true);
        tblvrf.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblvrf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblvrf.setRowHeight(20);
        tblvrf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblvrfMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tblvrf);

        jPanel6.add(jScrollPane21);
        jScrollPane21.setBounds(10, 20, 950, 370);

        jLabel62.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel62.setText("Bike Point to Verified");
        jPanel6.add(jLabel62);
        jLabel62.setBounds(10, 10, 440, 10);

        lblcaloutcount1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel6.add(lblcaloutcount1);
        lblcaloutcount1.setBounds(880, 10, 40, 10);

        lblrepticcount13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount13.setText("list");
        jPanel6.add(lblrepticcount13);
        lblrepticcount13.setBounds(920, 10, 40, 10);

        jTabbedPane5.addTab("Verification", jPanel6);

        pnlrep.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep.setLayout(null);

        tblreptic.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblreptic.setModel(new javax.swing.table.DefaultTableModel(
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
        tblreptic.setRowHeight(20);
        tblreptic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblrepticMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblreptic);

        pnlrep.add(jScrollPane4);
        jScrollPane4.setBounds(10, 40, 950, 340);

        txtcategory.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep.add(txtcategory);
        txtcategory.setBounds(560, 20, 100, 24);

        txtdriver.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep.add(txtdriver);
        txtdriver.setBounds(460, 20, 100, 24);

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Driver");
        pnlrep.add(jLabel7);
        jLabel7.setBounds(460, 10, 100, 10);

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Category");
        pnlrep.add(jLabel6);
        jLabel6.setBounds(560, 10, 100, 10);

        txtcustomer.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep.add(txtcustomer);
        txtcustomer.setBounds(360, 20, 100, 24);

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel19.setText("Customer");
        pnlrep.add(jLabel19);
        jLabel19.setBounds(360, 10, 100, 10);

        btnreptic.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnreptic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnreptic.setText("Search Ticket");
        btnreptic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepticActionPerformed(evt);
            }
        });
        pnlrep.add(btnreptic);
        btnreptic.setBounds(770, 20, 150, 24);

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel20.setText("User");
        pnlrep.add(jLabel20);
        jLabel20.setBounds(260, 10, 100, 10);

        txtuser.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep.add(txtuser);
        txtuser.setBounds(260, 20, 100, 24);

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel37.setText("Open From");
        pnlrep.add(jLabel37);
        jLabel37.setBounds(10, 10, 100, 10);

        jLabel38.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel38.setText("Until");
        pnlrep.add(jLabel38);
        jLabel38.setBounds(130, 10, 100, 10);

        dctic1.setDateFormatString("dd/MM/yyyy");
        pnlrep.add(dctic1);
        dctic1.setBounds(10, 20, 120, 24);

        dctic2.setDateFormatString("dd/MM/yyyy");
        pnlrep.add(dctic2);
        dctic2.setBounds(130, 20, 120, 24);

        txtticno.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        pnlrep.add(txtticno);
        txtticno.setBounds(660, 20, 100, 24);

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("No Ticket");
        pnlrep.add(jLabel9);
        jLabel9.setBounds(660, 10, 100, 10);

        btnexporttic.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnexporttic.setText("Export");
        btnexporttic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportticActionPerformed(evt);
            }
        });
        pnlrep.add(btnexporttic);
        btnexporttic.setBounds(10, 380, 90, 20);

        lblrepticcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep.add(lblrepticcount);
        lblrepticcount.setBounds(880, 0, 40, 10);

        lblrepticcount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount1.setText("list");
        pnlrep.add(lblrepticcount1);
        lblrepticcount1.setBounds(920, 0, 40, 10);

        jTabbedPane5.addTab("Tickets", pnlrep);

        getContentPane().add(jTabbedPane5);
        jTabbedPane5.setBounds(455, 322, 100, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfaxActionPerformed
        // TODO add your handling code here:
        int z=0;
         try {
            sql = "select * from log_fax " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
            rs = jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                z++;
            }
            if (z!=0){
                cccs_Fax_incoming fax = new cccs_Fax_incoming();
                fax.setVisible(true);
                Fin.open();

            }else{
                sql="update log_fax set " +
                        "_status=1, " +
                        "username='"+lbluser.getText()+"' " +
                        "where _direction=0 " +
                        "and _status=0 " +
                        "order by rcvd_time limit 1";
                jconn.SQLExecute(sql, conn);
                // cek rebutan
                sql1="select * from log_fax " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql1, conn);
                z=0;
                while (rs.next()) {
                    z++;
                }
                if(z!=0){
                    s = "FAX|UPDATE\r\n";
                    kirimBroad();
                    cccs_Fax_incoming fax = new cccs_Fax_incoming();
                    fax.setVisible(true);
                    Fin.open();
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnfaxActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (ticshow==false){
            cccs_ticket tic = new cccs_ticket();
            tic.setVisible(true);

            Tic.newtic=true;
            ticshow=true;
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    @SuppressWarnings("static-access")
    private void btncallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncallActionPerformed
        // TODO add your handling code here:
        btncall.setEnabled(false);
        lblactivity.setText("On line");
        btncall.setDebugGraphicsOptions(v);
        s = "PICKUP\r\n";
        kirimTele();
//          System.out.print("\nyang ini d PICKUP isi string s = "+ s);
          stop();
          delay();

          sql="update log_phone set delay='"+elapsed+"', _callstatus=1 where log_id='"+loid+"'";
          jconn.SQLExecute(sql, conn);
//                      System.out.print(sql);

          sql3="update user_account set _activity=3, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
          jconn.SQLExecute(sql3, conn);
            
        cccs_InBoundCall inc = new cccs_InBoundCall();
        inc.setVisible(true);
        Inc.hangup=false;
        Inc.cek();        
    }//GEN-LAST:event_btncallActionPerformed

    private void btnsmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsActionPerformed
         // TODO add your handling code here:
        int z=0;
         try {
            sql = "select * from log_sms " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
            rs = jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                z++;
            }
            if (z!=0){
                cccs_Sms_income sms = new cccs_Sms_income();
                sms.setVisible(true);
                Sin.open();

            }else{
                sql="update log_sms set " +
                        "_status=1, " +
                        "username='"+lbluser.getText()+"' " +
                        "where _direction=0 " +
                        "and _status=0 " +
                        "order by sms_date, sms_time limit 1";
                jconn.SQLExecute(sql, conn);
                // cek rebutan
                sql1="select * from log_sms " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql1, conn);
                z=0;
                while (rs.next()) {
                    z++;
                }
                if(z!=0){
                    s = "SMS|UPDATE\r\n";
                    kirimBroad();
                    cccs_Sms_income sms = new cccs_Sms_income();
                    sms.setVisible(true);
                    Sin.open();
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsmsActionPerformed

    private void btnmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailActionPerformed
        // TODO add your handling code here:
        int z=0;
         try {
            sql = "select * from log_mail " +
                    "where direction=0 " +
                    "and status=1 " +
                    "and username='"+ lbluser.getText()+"'";
            rs = jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                z++;
            }
            if (z!=0){
                cccs_Email_incoming mail = new cccs_Email_incoming();
                mail.setVisible(true);
                Ein.open();

            }else{
                sql="update log_mail set " +
                        "status=1, " +
                        "username='"+lbluser.getText()+"' " +
                        "where direction=0 " +
                        "and status=0 " +
                        "order by mail_date, mail_time limit 1";
                jconn.SQLExecute(sql, conn);
                // cek rebutan
                sql1="select * from log_mail " +
                    "where direction=0 " +
                    "and status=1 " +
                    "and username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql1, conn);
                z=0;
                while (rs.next()) {
                    z++;
                }
                if(z!=0){
                    s = "MAIL|UPDATE\r\n";
                    kirimBroad();
                    cccs_Email_incoming mail = new cccs_Email_incoming();
                    mail.setVisible(true);
                    Ein.open();
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnmailActionPerformed

    private void tblticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblticMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            btnsenddept.setEnabled(true);
            if (tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Details"))==null){
                txtdetail.setText("");
            }else{
                txtdetail.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Details")));
            }
            if (tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Solution"))==null){
                txtsolution.setText("");
            }else{
                txtsolution.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Solution")));
            }
            
        }
        if(evt.getClickCount()==2){
            if(ticshow==false){                
    //            System.out.print("debugging");
                cccs_ticket tic = new cccs_ticket();
                tic.setVisible(true);
                Tic.newtic=false;

                Tic.txtnotic.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(), 0));
//                Tic.ass=((String)tbltic.getValueAt(tbltic.getSelectedRow(),3));
                Tic.id=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(),tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket Id")));

                Tic.klik();
                ticshow=true;
            }

        }
    }//GEN-LAST:event_tblticMouseClicked

    private void tblminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblminMouseClicked
        // TODO add your handling code here:
        int row=0;
        row=tblmin.getSelectedRow();
        if(row>=0){
            txtfrom.setText(tabmin.getValueAt(row,2).toString());
            txtisu.setText(tabmin.getValueAt(row,3).toString());
            txtimsg.setText(tabmin.getValueAt(row,6).toString());
            mailid=tabmin.getValueAt(row,7).toString();
            DefaultListModel listModel = new DefaultListModel();
            jList2.setModel(listModel);
            try{
                jList2.setModel(listModel);
                sql1="select filename from mail_attachment where mail_id='"+mailid+"'";
                rs1=jconn.SQLExecuteRS(sql1,conn);
                while(rs1.next()){
                    listModel.addElement(rs1.getString("filename").toString());
                }
            }catch(Exception e){
                System.out.println(e);
            }
//            cuscom1=tabmin.getValueAt(row,8).toString();
//            cbcust1.setSelectedItem(cuscom1);
            btnEmailCALL.setEnabled(true);
            btnEmailCALL1.setEnabled(true);
        }else{
            txtfrom.setText("");
            txtisu.setText("");
            txtimsg.setText("");
            cbcust1.setSelectedIndex(-1);
            btnEmailCALL.setEnabled(false);
            btnEmailCALL1.setEnabled(false);
        }
}//GEN-LAST:event_tblminMouseClicked

    private void tblmouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmouMouseClicked
        // TODO add your handling code here:
            int row=0;
            row=tblmou.getSelectedRow();
            txtoto.setText(tabmou.getValueAt(row,2).toString());
            txtosu.setText(tabmou.getValueAt(row,4).toString());
            txtomsg.setText(tabmou.getValueAt(row,7).toString());
            if (tabmou.getValueAt(row,8)!=null){
                txtocc.setText(tabmou.getValueAt(row,8).toString());
            }
            if (tabmou.getValueAt(row,5)!=null){
                txtidti.setText(tabmou.getValueAt(row,5).toString());
            }
            mailid=tabmou.getValueAt(row,9).toString();
            DefaultListModel listModel1 = new DefaultListModel();
            jList3.setModel(listModel1);
            try{
                jList3.setModel(listModel1);
                sql1="select filename from mail_attachment where mail_id='"+mailid+"'";
                rs1=jconn.SQLExecuteRS(sql1,conn);
                while(rs1.next()){
                    listModel1.addElement(rs1.getString("filename").toString());
                }
            }catch(Exception e){
                System.out.println(e);
            }
}//GEN-LAST:event_tblmouMouseClicked

    private void panelmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelmailMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_panelmailMouseClicked

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        // TODO add your handling code here:
        int i=JOptionPane.showConfirmDialog(null, "Really want to Exit..?","Exit",JOptionPane.YES_NO_OPTION);
        if (i==JOptionPane.YES_OPTION){
        String input=JOptionPane.showInputDialog("Fill the reason");
            sql="update user_account set _status=0, _activity=0, host_addr='"+Log.hostadd+"',logout_time='"+ld+"',info='"+input+"'where password = md5('"+lblpas.getText()+"') and username= '" +lbluser.getText()+ "' limit 1";
            jconn.SQLExecute(sql, conn);            
//            System.out.println(sql);
            s = "CLOSE\r\n";
              kirimTele();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(cccs_InBoundCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectuploder();
            s = "CLOSE\r\n";
            kirimUplo();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(cccs_InBoundCall.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            connectuploder1();
//            s = "CLOSE\r\n";
//            kirimUplo1();

              cleanUptele();              cleanUpupload();
              cleanUpupload1();              cleanbroad();
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            System.exit(0);            
        }
}//GEN-LAST:event_btnlogoutActionPerformed

    private void btnoutboundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoutboundActionPerformed
        // TODO add your handling code here:
        if(outbound==true){
            cccs_OutBound out = new cccs_OutBound();
            out.setVisible(true);
        }else{
            s = "REGISTER|"+pabx+"|"+out_ext+"|"+out_ext+"\r\n";
              kirimTele();
              int z=0;
             try {
                 //cek gantung
                sql = "select * from tickets " +
                        "where confirmed=1 " +
                        "and confirm_username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql, conn);
                System.out.print("\ncek gantung : "+sql);
                while (rs.next()) {
                    z++;
                }
                System.out.print("\ncek counter : "+z);
                if (z!=0){
                    cccs_OutBound out = new cccs_OutBound();
                    out.setVisible(true);
//                    Obc.open();

                }else{
                    sql="update tickets set " +
                            "confirmed=1, " +
                            "confirm_username='"+lbluser.getText()+"' " +
                            "where _status=2 and confirm=1 and confirm_by=0 and confirm_username is null and confirmed=0 " +
                            "order by close_date, close_time limit 1";
                    jconn.SQLExecute(sql, conn);
                     // cek rebutan
                    sql1="select * from tickets " +
                        "where confirmed=1 " +
                        "and confirm_username='"+ lbluser.getText()+"'";
                    rs = jconn.SQLExecuteRS(sql1, conn);
                    z=0;
                    while (rs.next()) {
                        z++;
                    }
                    if(z!=0){
                        s = "TICKET|CONFIRMED\r\n";
                        kirimBroad();
                        cccs_OutBound out = new cccs_OutBound();
                        out.setVisible(true);
//                        Obc.open();
                    }
                }


            } catch (SQLException ex) {
                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        if (c==0){
//
//        }else{
//            c--;
//            txtcalnoti.setText(String.valueOf(c));
//        }
        
}//GEN-LAST:event_btnoutboundActionPerformed

    private void btnreadyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreadyMouseClicked
        // TODO add your handling code here:            
            
//            System.out.print(s);
    }//GEN-LAST:event_btnreadyMouseClicked

public static String clbk=null;
public static String cldt=null;
String tic0;
    private void tblinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblinMouseClicked
        // TODO add your handling code here:
        if(tabin.getRowCount()!=0){
            if(evt.getClickCount()==2&&lblactivity.getText().equals("Disconnected")){
                if(inshow==false){
                    cccs_InBoundCall inc = new cccs_InBoundCall();
                    inc.setVisible(true);
                    Inc.loid=null;

                    Inc.ckblank.setEnabled(false);
                    Inc.txtlogid.setText((String)tblin.getValueAt(tblin.getSelectedRow(),tblin.getTableHeader().getColumnModel().getColumnIndex("Log ID")));
                    Inc.loid=String.valueOf((String)tblin.getValueAt(tblin.getSelectedRow(),tblin.getTableHeader().getColumnModel().getColumnIndex("Log ID")));
                    Inc.hangup=true;
                    Inc.cek();
                    inshow=true;
                }else{
                    JOptionPane.showMessageDialog(null,"Close the last form before");
                }
            }else if(evt.getClickCount()==2&&!lblactivity.getText().equals("Disconnected")&&cbdirection.getSelectedIndex()==1){
                cccs_OutBound out = new cccs_OutBound();
                out.setVisible(true);
                
                out.txtdial.setText((String)tblin.getValueAt(tblin.getSelectedRow(),tblin.getTableHeader().getColumnModel().getColumnIndex("Caller number")));
            }
        }
    }//GEN-LAST:event_tblinMouseClicked

    private void tbloutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbloutMouseClicked
        // TODO add your handling code here:
//        if(evt.getClickCount()==1){
//            tabou.setRowCount(0);
//            tabelou();
//        }
//        if(evt.getClickCount()==2){
//           cccs_OutBound obc = new cccs_OutBound();
//           obc.setVisible(true);

//           Obc.t
//        }
}//GEN-LAST:event_tbloutMouseClicked

    private static String tic1,tic2,tic3,tic4,cal1,cal2,cal3,cal4,cal5,cal6,sms1,sms2,mail1,mail2,fax1,fax2;
    private void btnrepticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepticActionPerformed
        // TODO add your handling code here:
        tabelreptic();
}//GEN-LAST:event_btnrepticActionPerformed

    private void btnrepcalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepcalActionPerformed
        // TODO add your handling code here:
        tabelrepcal();
}//GEN-LAST:event_btnrepcalActionPerformed

    private void btnrepsmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepsmsActionPerformed
        // TODO add your handling code here:
        tabrepsms.setRowCount(0);
        Date dt1 =dcsms1.getDate();
        Date dt2 =dcsms2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sms1 = sdf.format(dt1);
        sms2= sdf.format(dt2);
        try{
            int row=0;            
             sql="select log_sms.* , " +
                     "_direction.data as dir," +
                     " tickets.ticket_no as notic" +
                     ",case log_sms._direction" +
                     " when 0 then (select rcvd_status.data from rcvd_status where code=log_sms._status) " +
                     " when 1 then (select send_status.data from send_status where code=log_sms._status)" +
                     " end stt" +
                     " from log_sms" +
                     " join _direction on log_sms._direction=_direction.code" +
                     " left join tickets on log_sms.ticket_id=tickets.ticket_id" +
                     " where sms_date between '"+sms1+"' and '"+sms2+"'";
            condition="";
            if(!cbagenirepcal1.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenirepcal1.getSelectedItem()+"%'";
            }
            if(!cbdirrepsms.getSelectedItem().equals("--")){
                condition=condition+" and _direction = '"+cbdirrepsms.getSelectedIndex()+"'";
            }
            if(!txtsmsstat.getText().equals("")){
                condition=condition+" and _status like '%"+txtsmsstat.getText()+"%'";
            }
            if(!txtsmsticid.getText().equals("")){
                condition=condition+" and ticket_no = '"+txtsmsticid.getText()+"'";
            }

            sql=sql+condition+" order by sms_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);

            while(rs.next()){
                repsms[0]=rs.getString(1);
                repsms[1]=rs.getString(2);
                repsms[2]=rs.getString(3);
                repsms[3]=rs.getString(4);
                repsms[4]=rs.getString("stt");
                repsms[5]=rs.getString("dir");
                repsms[6]=rs.getString(8);
                repsms[7]=rs.getString(9);
                repsms[8]=rs.getString(10);
                repsms[9]=rs.getString("notic");
                repsms[10]=rs.getString(13);
                repsms[11]=rs.getString("direction_type");
                repsms[12]=rs.getString("cust_name");
                tabrepsms.addRow(repsms);
                row+=1;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Sms with number cccs_ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" dosen't exsist");
            }
            lblrepsmscount.setText(String.valueOf(tabrepsms.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepsmsActionPerformed

    private void btnrepmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepmailActionPerformed
        // TODO add your handling code here:
        tabrepmail.setRowCount(0);
        Date dt1 =dcmail1.getDate();
        Date dt2 =dcmail2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mail1 = sdf.format(dt1);
        mail2= sdf.format(dt2);
        try{
            int row=0;
            
                sql="select log_mail.*, _direction.data as dir" +
                        ", tickets.ticket_no as notic" +
                        ",case log_mail.direction" +
                        " when 0 then (select rcvd_status.data from rcvd_status where code=log_mail.status) " +
                        " when 1 then (select send_status.data from send_status where code=log_mail.status)" +
                        " end stt" +
                        " from log_mail" +
                        " join _direction on log_mail.direction=_direction.code" +
                        " left join tickets on log_mail.ticket_id=tickets.ticket_id" +
                        " where mail_id is not null and mail_date between '"+mail1+"' and '"+mail2+"'";
            condition="";
            if(!cbagenrepmail.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenrepmail.getSelectedItem()+"%'";
            }
            if(!cbdirmail.getSelectedItem().equals("--")){
                condition=condition+" and direction = '"+cbdirmail.getSelectedIndex()+"'";
            }
            if(!txtmailsub.getText().equals("")){
                condition=condition+" and mail_subject like '%"+txtmailsub.getText()+"%'";
            }
            if(!txtmailticid.getText().equals("")){
                condition=condition+" and ticket_no = '"+txtmailticid.getText()+"'";
            }

            sql=sql+condition+" GROUP by mail_id order by mail_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                repmail[0]=rs.getString(1);
                repmail[1]=rs.getString(2);
                repmail[2]=rs.getString(3);
                repmail[3]=rs.getString(4);
                repmail[4]=rs.getString(5);
                repmail[5]=rs.getString(6);
                repmail[6]=rs.getString(7);
                repmail[7]=rs.getString(8);
                repmail[8]=rs.getString(9);
                repmail[9]=rs.getString(10);
                repmail[10]=rs.getString("stt");
                repmail[11]=rs.getString("notic");
                repmail[12]=rs.getString("dir");
                repmail[13]=rs.getString("direction_type");
                repmail[14]=rs.getString("cust_name");
                tabrepmail.addRow(repmail);
                row+=1;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Mail with number subject "+txtmailsub.getText()+", username "+txtmailusr.getText()+", with direction "+txtmaildir.getText()+", with ticekt id "+txtmailticid.getText()+" dosen't exsist");
            }
            lblrepmailcount.setText(String.valueOf(tabrepmail.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepmailActionPerformed

    private void btnrepfaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepfaxActionPerformed
        // TODO add your handling code here:
        tabrepfax.setRowCount(0);
        Date dt1 =dcfax1.getDate();
        Date dt2 =dcfax2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        fax1 = sdf.format(dt1);
        fax2= sdf1.format(dt2);
        sql3="update log_fax set sent_time=null where sent_time like '0000%'";
        jconn.SQLExecute(sql3,conn);
        try{
            int row=0;
            sql="select log_fax.* , " +
                     "_direction.data as dir," +
                     " tickets.ticket_no as notic" +
                     ",case log_fax._direction" +
                     " when 0 then (select rcvd_status.data from rcvd_status where code=log_fax._status) " +
                     " when 1 then (select send_status.data from send_status where code=log_fax._status)" +
                     " end stt" +
                     " from log_fax" +
                     " join _direction on log_fax._direction=_direction.code" +
                     " left join tickets on log_fax.ticket_id=tickets.ticket_id" +
                     " where (sent_time between '"+fax1+"' and '"+fax2+"' or rcvd_time between '"+fax1+"' and '"+fax2+"')";
            condition="";
            if(!cbagenirepfax.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenirepfax.getSelectedItem()+"%'";
            }
            if(!cbdirfax.getSelectedItem().equals("--")){
                condition=condition+" and _direction = '"+cbdirfax.getSelectedIndex()+"'";
            }
            if(cbstatusrepfax.getSelectedIndex()!=-1){
                condition=condition+" and _status like '%"+cbstatusrepfax.getSelectedIndex()+"%'";
            }
            if(!txtfaxfinm.getText().equals("")){
                condition=condition+" and doc_name like '%"+txtfaxfinm.getText()+"%'";
            }

            sql=sql+condition+" order by fax_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                repfax[0]=rs.getString(1);
                repfax[1]=rs.getString(2);
                repfax[2]=rs.getString(3);
                repfax[3]=rs.getString(4);
                repfax[4]=rs.getString(5);
                repfax[5]=rs.getString(6);
                repfax[6]=rs.getString(7);
                repfax[7]=rs.getString("stt");
                repfax[8]=rs.getString("dir");
                repfax[9]=rs.getString("notic");
                repfax[10]=rs.getString("cust_name");
                tabrepfax.addRow(repfax);
                row+=1;
            }if(row==0){
                JOptionPane.showMessageDialog(null,"Data Fax doesn't exsist");
            }
            lblrepfaxcount.setText(String.valueOf(tabrepfax.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepfaxActionPerformed

    private void tblrepticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblrepticMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            cccs_History hic = new cccs_History();
            hic.setVisible(true);

            Hic.no=Integer.parseInt((String)tblreptic.getValueAt(tblreptic.getSelectedRow(),0));
            Hic.klik2();
        }
    }//GEN-LAST:event_tblrepticMouseClicked

    private void txtcalnotiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcalnotiMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtcalnotiMouseClicked

    private void tblticconfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblticconfMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==2){
            cccs_OutBound out = new cccs_OutBound();
            out.setVisible(true);

            Obc.txtnotic.setText((String)tblticconf.getValueAt(tblticconf.getSelectedRow(), 0));
            Obc.txtnotic1.setText((String)tblticconf.getValueAt(tblticconf.getSelectedRow(), 12));
            
            Obc.klik2();
        }
}//GEN-LAST:event_tblticconfMouseClicked

    private void tblactMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblactMouseEntered
        // TODO add your handling code here:
        inbo.start();
    }//GEN-LAST:event_tblactMouseEntered

    private void tblactMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblactMouseExited
        // TODO add your handling code here:
        inbo.stop();
    }//GEN-LAST:event_tblactMouseExited

    private void txtisuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtisuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtisuActionPerformed

    private void btnsmsinsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsinsrchActionPerformed
        // TODO add your handling code here:
        tabelsin();
}//GEN-LAST:event_btnsmsinsrchActionPerformed

    private void btnsmsoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsoutsrchActionPerformed
        // TODO add your handling code here:
        tabelsou();
}//GEN-LAST:event_btnsmsoutsrchActionPerformed

    private void btnmailinsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailinsrchActionPerformed
        // TODO add your handling code here:
        tabelmin();
}//GEN-LAST:event_btnmailinsrchActionPerformed

    private void btnmailoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailoutsrchActionPerformed
        // TODO add your handling code here:
        tabelmou();
}//GEN-LAST:event_btnmailoutsrchActionPerformed

    private void btnexportticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportticActionPerformed
        // TODO add your handling code here:
        tabex=tabreptic;
        createexcel();
}//GEN-LAST:event_btnexportticActionPerformed
public static String cuscom,cuscom1,cuscom2,smsid,mailid,faxid;
    private void tblsinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsinMouseClicked
        // TODO add your handling code here:
        int row=0;
        row=tblsin.getSelectedRow();
        if(row>=0){
            txtfrom2.setText(tblsin.getValueAt(row,1).toString());
            txtimsg2.setText(tblsin.getValueAt(row,3).toString());
            smsid=tblsin.getValueAt(row,5).toString();
            cuscom=tblsin.getValueAt(row,6).toString();
            cbcust.setSelectedItem(cuscom);
            btnsmsCALL.setEnabled(true);
        }else{
            txtfrom2.setText("");
            txtimsg2.setText("");
            cbcust.setSelectedIndex(-1);
            btnsmsCALL.setEnabled(false);
        }
    }//GEN-LAST:event_tblsinMouseClicked

    private void tblsouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsouMouseClicked
        // TODO add your handling code here:
        int row=0;
        row=tblsou.getSelectedRow();
        if(row>=0){
            txtfrom1.setText(tblsou.getValueAt(row,1).toString());
            txtimsg1.setText(tblsou.getValueAt(row,3).toString());
        }else{
            txtfrom1.setText("");
            txtimsg1.setText("");
        }
    }//GEN-LAST:event_tblsouMouseClicked
    
    private void btnticsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnticsrchActionPerformed
        // TODO add your handling code here:
        tabeltic();
}//GEN-LAST:event_btnticsrchActionPerformed


    private void btninsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsrchActionPerformed
        // TODO add your handling code here:
        tabelin();
}//GEN-LAST:event_btninsrchActionPerformed
     
    private void btnoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoutsrchActionPerformed
        // TODO add your handling code here:
        tabelou();
}//GEN-LAST:event_btnoutsrchActionPerformed

    private void btnexportcallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcallActionPerformed
        // TODO add your handling code here:
        tabex=tabrepcal;
        createexcel();
}//GEN-LAST:event_btnexportcallActionPerformed

    private void btnexportsmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportsmsActionPerformed
        // TODO add your handling code here:
        tabex=tabrepsms;
        createexcel();
}//GEN-LAST:event_btnexportsmsActionPerformed

    private void btnexportmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportmailActionPerformed
        // TODO add your handling code here:
        tabex=tabrepmail;
        createexcel();
}//GEN-LAST:event_btnexportmailActionPerformed

    private void btnsenddeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsenddeptActionPerformed
        // TODO add your handling code here:
        if (asshow==false){
            cccs_Asdept asd = new cccs_Asdept();
            asd.setVisible(true);

            Asd.id=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(),tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket Id")));
            Asd.notic=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(), 0));

            btnsenddept.setEnabled(false);
            asshow=true;
        }        
}//GEN-LAST:event_btnsenddeptActionPerformed

    private void cktglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cktglActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cktglActionPerformed

    private void txtmailnotiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmailnotiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmailnotiActionPerformed

    private void cbcalstatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcalstatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcalstatActionPerformed

    private void btnexportmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportmail1ActionPerformed
        // TODO add your handling code here:
        tabex=tabrepfax;
        createexcel();
    }//GEN-LAST:event_btnexportmail1ActionPerformed

    private void tblfinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblfinMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            int row=0;
            row=tblfin.getSelectedRow();
            lblview.setIcon(new ImageIcon("Z:/localhost/inbox/"+tabfin.getValueAt(row,1).toString()+""));
            faxid=tabfin.getValueAt(row,4).toString();
            cuscom2=tabfin.getValueAt(row,5).toString();
        }

        if(evt.getClickCount()==2){
//            Tampil1();
        }
}//GEN-LAST:event_tblfinMouseClicked

    private void btnfinsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinsrchActionPerformed
        // TODO add your handling code here:
        tabelfin();
}//GEN-LAST:event_btnfinsrchActionPerformed

    private void tblfouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblfouMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            int row=0;
            row=tblfou.getSelectedRow();
            lblview.setIcon(new ImageIcon("Z:/localhost/outbox/"+tabfou.getValueAt(row,2).toString()+""));
        }

        if(evt.getClickCount()==2){
//            Tampil1();
        }
}//GEN-LAST:event_tblfouMouseClicked

    private void btnfoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfoutsrchActionPerformed
        // TODO add your handling code here:
        tabelfou();
}//GEN-LAST:event_btnfoutsrchActionPerformed

    private void panelfaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelfaxMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_panelfaxMouseClicked

    private void btnreleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreleaseActionPerformed
        // TODO add your handling code here:
        sql="update user_account set _activity=0 where username= '" +cbagenrelease.getSelectedItem()+ "' limit 1";
        jconn.SQLExecute(sql, conn);
        JOptionPane.showMessageDialog(null, "AGEN "+cbagenrelease.getSelectedItem()+" HAS BEEN RELEASED", "SETTING",JOptionPane.WARNING_MESSAGE);
}//GEN-LAST:event_btnreleaseActionPerformed

    private void cbagenreleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbagenreleaseActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbagenreleaseActionPerformed

    private void tblmsinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmsinMouseClicked
        // TODO add your handling code here:
       if(tblmsin.getSelectedRow()>=0){
            btnreplymsg.setEnabled(true);
            btndelmsg.setEnabled(true);
            msgidin=(String)tblmsin.getValueAt(tblmsin.getSelectedRow(), 5);
            if (tblmsin.getValueAt(tblmsin.getSelectedRow(), 4)==null){
                txtimsg3.setText("");
            }else{
                txtimsg3.setText((String)tblmsin.getValueAt(tblmsin.getSelectedRow(), 4));
            }
            tu = (String)tblmsin.getValueAt(tblmsin.getSelectedRow(), 2);
            if(tblmsin.getValueAt(tblmsin.getSelectedRow(), 3).equals("0")){
                sql="update msg_inbox set _read = 1 where msg_id='"+msgidin+"'";
                jconn.SQLExecute(sql, conn);
                tabelmsin();
                tt--;
            }
        }
}//GEN-LAST:event_tblmsinMouseClicked

    private void btnreplymsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreplymsgActionPerformed
        // TODO add your handling code here:
        cccs_Mssg mssg = new cccs_Mssg();
        mssg.setVisible(true);

        Misg.txttu.setText(tu);
}//GEN-LAST:event_btnreplymsgActionPerformed

    private void tblmsouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmsouMouseClicked
        // TODO add your handling code here:
        if(tblmsou.getSelectedRow()>=0){
            btndelmsg1.setEnabled(true);
            msgidou=(String)tblmsou.getValueAt(tblmsou.getSelectedRow(), 4);
            if (tblmsou.getValueAt(tblmsou.getSelectedRow(), 3)==null){
                txtimsg4.setText("");
            }else{
                txtimsg4.setText((String)tblmsou.getValueAt(tblmsou.getSelectedRow(), 3));
            }
        }
}//GEN-LAST:event_tblmsouMouseClicked

    private void btncomposemsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomposemsgActionPerformed
        // TODO add your handling code here:
        cccs_Mssg mssg = new cccs_Mssg();
        mssg.setVisible(true);
}//GEN-LAST:event_btncomposemsgActionPerformed

    private void btndelmsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelmsgActionPerformed
        // TODO add your handling code here:
        sql="update msg_inbox set _erased = 1 where msg_id='"+msgidin+"'";
        jconn.SQLExecute(sql, conn);
}//GEN-LAST:event_btndelmsgActionPerformed

    private void btndelmsg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelmsg1ActionPerformed
        // TODO add your handling code here:
        sql="update msg_outbox set _erased = 1 where msg_id='"+msgidou+"'";
        jconn.SQLExecute(sql, conn);
    }//GEN-LAST:event_btndelmsg1ActionPerformed

    private void cbticstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbticstatusActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbticstatusActionPerformed
int i;
int InboundCall, AnsweredCall, AbandonCall, PhantomCall, BlankCall, PrankCall;
int Customer, Inquiry, InquiryTr, WrongNum, Complain;
int Non_customer, CustDriver, CustUser;
int Towing;
int CustPIC;
int CustOther;
int IntANJ;
int IntCC;
int IntCSO;
int IntDriver;
int IntOther;

String TotalInbWait;
String TotalInbDelay;
String TotalInbBusy;
String TotalInbCall;

String AvgInbCall;
String AvgInbBusy;
String ASA ;

int OutAnsweredCall    ;
int OutNotAnsweredCall ;
int OutCall         ;
String TotalOutCall    ;
String AvgOutCall      ;
    //OtherCall       := 0;
int BCustCall       ;
int BDealerCall     ;
int BTechCall       ;
int BHPMCall        ;
int BOtherCall      ;
int OutgoingSMS     ;
String lpad            ;
String hi,ho,dayin1,dayin2,dayou1,dayou2,perfin1,perfin2,perfou1,perfou2;
    private void btnhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhiActionPerformed
        // TODO add your handling code here:
        tabhoin.setRowCount(0);
        Date dt1 =dthi.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        hi = sdf.format(dt1);
        sql="delete from report_inbound";
        jconn.SQLExecute(sql, conn);
        for(i=0;i<=23;i++){
            InboundCall = 0;
            AnsweredCall    = 0;
            AbandonCall     = 0;
            BlankCall       = 0;
            PrankCall       = 0;
            Inquiry         = 0;
            InquiryTr         = 0;
            Complain        = 0;
            WrongNum        = 0;


            TotalInbWait    = "0";
            TotalInbDelay   = "0";
            TotalInbBusy    = "0";
            TotalInbCall    = "0";

            AvgInbCall      = "0";
            AvgInbBusy      = "0";
//            ASA             = "0";
            try{
                sql="select lpad("+i+",2,00)";
                rs=jconn.SQLExecuteRS(sql, conn);
                while(rs.next()){
                    lpad=String.valueOf(rs.getString(1));
                }
                sql1="select count(username) as total_inbound " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql1, conn);
                while(rs.next()){
                    InboundCall=Integer.parseInt(rs.getString("total_inbound"));
                }
                sql2="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql2, conn);
                while(rs.next()){
                    OutCall=Integer.parseInt(rs.getString("total_outbound"));
                }
                sql3="select count(username) as total_answered " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0 " +
                        "and _callstatus=1";
                rs=jconn.SQLExecuteRS(sql3, conn);
                while(rs.next()){
                    AnsweredCall=Integer.parseInt(rs.getString("total_answered"));
                }
                sql4="select count(username) as total_abandon " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0 " +
                        "and _callstatus=0";
                rs=jconn.SQLExecuteRS(sql4, conn);
                while(rs.next()){
                    AbandonCall=Integer.parseInt(rs.getString("total_abandon"));
                }
                sql5="select ifnull(sec_to_time(sum(time_to_sec(delay))),0) as total_delay " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql5, conn);
                while(rs.next()){
                    TotalInbDelay=String.valueOf(rs.getString("total_delay"));
                }
                sql6="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql6, conn);
                while(rs.next()){
                    TotalInbCall=String.valueOf(rs.getString("total_duration"));
                }
                sql7="select ifnull(sec_to_time(sum(time_to_sec(busy))),0) as total_busy " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql7, conn);
                while(rs.next()){
                    TotalInbBusy=String.valueOf(rs.getString("total_busy"));
                }
                sql8="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_inbound " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql8, conn);
                while(rs.next()){
                    AvgInbCall=String.valueOf(rs.getString("avg_inbound"));
                }
                sql9="select ifnull(sec_to_time(avg(time_to_sec(busy))),0) as avg_busy " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql9, conn);
                while(rs.next()){
                    AvgInbBusy=String.valueOf(rs.getString("avg_busy"));
                }
                sql10="select ifnull(sum(_inquiry),0) as inquiry " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql10, conn);
                while(rs.next()){
                    Inquiry=Integer.parseInt(rs.getString("inquiry"));
                }
                sql13="select ifnull(sum(_inquirytransfer),0) as inquiry1 " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql13, conn);
                while(rs.next()){
                    InquiryTr=Integer.parseInt(rs.getString("inquiry1"));
                }
                sql11="select ifnull(sum(_complaint),0) as complain " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql11, conn);
                while(rs.next()){
                    Complain=Integer.parseInt(rs.getString("complain"));
                }
                sql12="select ifnull(sum(_blankcall),0) as _blankcall " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql12, conn);
                while(rs.next()){
                    BlankCall=Integer.parseInt(rs.getString("_blankcall"));
                }
                sql14="select ifnull(sum(_suggestion),0) as _prankcall " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql14, conn);
                while(rs.next()){
                    PrankCall=Integer.parseInt(rs.getString("_prankcall"));
                }
                sql15="select ifnull(sum(_wrongnumber),0) as wrongnum " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql15, conn);
                while(rs.next()){
                    WrongNum=Integer.parseInt(rs.getString("wrongnum"));
                }

            }catch(Exception e){
                System.out.print(e);
            }
            sql13="insert into report_inbound(" +
                    "hour " +
                    ",inb_call_count " +
                    ",abandon_call " +
                    ",answered_call " +
                    ",total_inb_delay_time " +
                    ",total_inb_call_time " +
                    ",total_inb_busy_time " +
                    ",avg_inb_call " +
                    ",avg_inb_busy " +
                    ",blank_call " +
                    ",prank_call " +
                    ",inquiry " +
                    ",inquiry_trans " +
                    ",complain " +
                    ",wrong_num " +
                    ")values (" +
                    "'"+lpad+":00"+"' " +
                    ",'"+InboundCall+"'" +
                    ",'"+AbandonCall+"'" +
                    ",'"+AnsweredCall+"'" +
                    ",'"+TotalInbDelay+"'" +
                    ",'"+TotalInbCall+"'" +
                    ",'"+TotalInbBusy+"'" +
                    ",'"+AvgInbCall+"'" +
                    ",'"+AvgInbBusy+"'" +
                    ",'"+BlankCall+"'" +
                    ",'"+PrankCall+"'" +
                    ",'"+Inquiry+"'" +
                    ",'"+InquiryTr+"'" +
                    ",'"+Complain+"'" +
                    ",'"+WrongNum+"'" +
                    ")";
            jconn.SQLExecute(sql13, conn);
        }
        try{
              sql="select * from report_inbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  hoin[0]=rs.getString("hour");
                  hoin[1]=rs.getString("inb_call_count");
                  hoin[2]=rs.getString("answered_call");
                  hoin[3]=rs.getString("abandon_call");
                  hoin[4]=rs.getString("total_inb_call_time");
                  hoin[5]=rs.getString("avg_inb_call");
                  hoin[6]=rs.getString("total_inb_busy_time");
                  hoin[7]=rs.getString("avg_inb_busy");
                  hoin[8]=rs.getString("blank_call");
                  hoin[9]=rs.getString("prank_call");
                  hoin[10]=rs.getString("inquiry");
                  hoin[11]=rs.getString("inquiry_trans");
                  hoin[12]=rs.getString("complain");
                  hoin[13]=rs.getString("wrong_num");
                  tabhoin.addRow(hoin);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnhiActionPerformed

    private void btnhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoActionPerformed
        // TODO add your handling code here:
        tabhoou.setRowCount(0);
        Date dt1 =dtho.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ho = sdf.format(dt1);
        sql="delete from report_outbound";
        jconn.SQLExecute(sql, conn);
        for(i=0;i<=23;i++){

            OutCall         = 0;
            TotalOutCall    = "0";
            AvgOutCall      = "0";
            Customer        = 0;
            Non_customer    = 0;
            CustDriver      = 0;
            CustUser        = 0;
            CustPIC         = 0;
            CustOther       = 0;
            IntANJ          = 0;
            IntCC           = 0;
            IntCSO          = 0;
            IntDriver       = 0;
            IntOther        = 0;

//            ASA             = "0";

            try{
                sql="select lpad("+i+",2,00)";
                rs=jconn.SQLExecuteRS(sql, conn);
                while(rs.next()){
                    lpad=String.valueOf(rs.getString(1));
                }
                sql1="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql1, conn);
                while(rs.next()){
                    OutCall=Integer.parseInt(rs.getString("total_outbound"));
                }
                sql2="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql2, conn);
                while(rs.next()){
                    TotalOutCall=String.valueOf(rs.getString("total_duration"));
                }
                sql3="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_outbound " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql3, conn);
                while(rs.next()){
                    AvgOutCall=String.valueOf(rs.getString("avg_outbound"));
                }
                sql4="select count(caller_type) as customer " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Customer' ";
                rs=jconn.SQLExecuteRS(sql4, conn);
                while(rs.next()){
                    Customer=Integer.parseInt(rs.getString("customer"));
                }
                sql5="select count(caller_type) as noncust " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Non-customer' ";
                rs=jconn.SQLExecuteRS(sql5, conn);
                while(rs.next()){
                    Non_customer=Integer.parseInt(rs.getString("noncust"));
                }
//                sql6="select count(caller_type) as cdriver " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-Driver' ";
//                rs=jconn.SQLExecuteRS(sql6, conn);
//                while(rs.next()){
//                    CustDriver=Integer.parseInt(rs.getString("cdriver"));
//                }
//                sql7="select count(caller_type) as cuser " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-User' ";
//                rs=jconn.SQLExecuteRS(sql7, conn);
//                while(rs.next()){
//                    CustUser=Integer.parseInt(rs.getString("cuser"));
//                }
                sql8="select count(caller_type) as cpic " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='PIC' ";
                rs=jconn.SQLExecuteRS(sql8, conn);
                while(rs.next()){
                    CustPIC=Integer.parseInt(rs.getString("cpic"));
                }
//                sql9="select count(caller_type) as cother " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-Other' ";
//                rs=jconn.SQLExecuteRS(sql9, conn);
//                while(rs.next()){
//                    CustOther=Integer.parseInt(rs.getString("cother"));
//                }
//                sql10="select count(caller_type) as ianj " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type = 'Internal-ANJ' ";
//                rs=jconn.SQLExecuteRS(sql10, conn);
//                while(rs.next()){
//                    IntANJ=Integer.parseInt(rs.getString("ianj"));
//                }
//                sql11="select count(caller_type) as icc " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-CC' ";
//                rs=jconn.SQLExecuteRS(sql11, conn);
//                while(rs.next()){
//                    IntCC=Integer.parseInt(rs.getString("icc"));
//                }
//                sql12="select count(caller_type) as icso " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-CSO' ";
//                rs=jconn.SQLExecuteRS(sql12, conn);
//                while(rs.next()){
//                    IntCSO=Integer.parseInt(rs.getString("icso"));
//                }
//                sql13="select count(caller_type) as idriver " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-Driver' ";
//                rs=jconn.SQLExecuteRS(sql13, conn);
//                while(rs.next()){
//                    IntDriver=Integer.parseInt(rs.getString("idriver"));
//                }
//                sql14="select count(caller_type) as iother " +
//                        "from log_phone " +
//                        "where log_date='"+ho+"' " +
//                        "and log_time like '"+lpad+"%' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-Other' ";
//                rs=jconn.SQLExecuteRS(sql14, conn);
//                while(rs.next()){
//                    IntOther=Integer.parseInt(rs.getString("iother"));
//                }

            }catch(Exception e){
                System.out.print(e);
            }
            sql15="insert into report_outbound(" +
                    "hour " +
                    ",out_call_count " +
                    ",total_out_call_time " +
                    ",avg_out_call " +
                    ",customer_count " +
                    ",noncust_count " +
//                    ",custdriver_count " +
//                    ",custuser_count " +
                    ",custpic_count " +
//                    ",custother_count " +
//                    ",internalanj_count " +
//                    ",internalcc_count " +
//                    ",internalcso_count " +
//                    ",internaldriver_count " +
//                    ",internalother_count " +
                    ")values (" +
                    "'"+lpad+":00"+"' " +
                    ",'"+OutCall+"'" +
                    ",'"+TotalOutCall+"'" +
                    ",'"+AvgOutCall+"'" +
                    ",'"+Customer+"'" +
                    ",'"+Non_customer+"'" +
//                    ",'"+CustDriver+"'" +
//                    ",'"+CustUser+"'" +
                    ",'"+CustPIC+"'" +
//                    ",'"+CustOther+"'" +
//                    ",'"+IntANJ+"'" +
//                    ",'"+IntCC+"'" +
//                    ",'"+IntCSO+"'" +
//                    ",'"+IntDriver+"'" +
//                    ",'"+IntOther+"'" +
                    ")";
            jconn.SQLExecute(sql15, conn);
        }
        try{
              sql="select * from report_outbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  hoou[0]=rs.getString("hour");
                  hoou[1]=rs.getString("out_call_count");
                  hoou[2]=rs.getString("total_out_call_time");
                  hoou[3]=rs.getString("avg_out_call");
                  hoou[4]=rs.getString("customer_count");
                  hoou[5]=rs.getString("noncust_count");
//                  hoou[6]=rs.getString("custdriver_count");
//                  hoou[7]=rs.getString("custuser_count");
                  hoou[6]=rs.getString("custpic_count");
//                  hoou[9]=rs.getString("custother_count");
//                  hoou[10]=rs.getString("internalanj_count");
//                  hoou[11]=rs.getString("internalcc_count");
//                  hoou[12]=rs.getString("internalcso_count");
//                  hoou[13]=rs.getString("internaldriver_count");
//                  hoou[14]=rs.getString("internalother_count");
                  tabhoou.addRow(hoou);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnhoActionPerformed

    private void btnpi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpi1ActionPerformed
        // TODO add your handling code here:
        tabperfin.setRowCount(0);
        String tgl;
        String nama;
        Date dt1 =dtpi.getDate();
        Date dt2 =dtpi1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        perfin1 = sdf.format(dt1);
        perfin2 = sdf.format(dt2);
        sql="delete from report_inbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_inbound(date, username) " +
                "select distinct log_date, username from log_phone " +
                "where _direction=0 " +
                "and log_date between '"+perfin1+"' and '"+perfin2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date, username from report_inbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                nama=rs.getString(2);

                sql1="select count(username) as total_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    InboundCall=Integer.parseInt(rs1.getString("total_inbound"));
                }
                sql2="select count(username) as total_phantom " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0 " +
                        "and _callstatus=-1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    PhantomCall=Integer.parseInt(rs1.getString("total_phantom"));
                }
                sql3="select count(username) as total_answered " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0 " +
                        "and _callstatus=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AnsweredCall=Integer.parseInt(rs1.getString("total_answered"));
                }
                sql4="select count(username) as total_abandon " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0 " +
                        "and _callstatus=0";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    AbandonCall=Integer.parseInt(rs1.getString("total_abandon"));
                }
                sql5="select ifnull(sec_to_time(sum(time_to_sec(delay))),0) as total_delay " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    TotalInbDelay=String.valueOf(rs1.getString("total_delay"));
                }
                sql6="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql6, conn);
                while(rs1.next()){
                    TotalInbCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql7="select ifnull(sec_to_time(sum(time_to_sec(busy))),0) as total_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql7, conn);
                while(rs1.next()){
                    TotalInbBusy=String.valueOf(rs1.getString("total_busy"));
                }
                sql8="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    AvgInbCall=String.valueOf(rs1.getString("avg_inbound"));
                }
                sql9="select ifnull(sec_to_time(avg(time_to_sec(busy))),0) as avg_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql9, conn);
                while(rs1.next()){
                    AvgInbBusy=String.valueOf(rs1.getString("avg_busy"));
                }
                sql10="select ifnull(sum(_inquiry),0) as inquiry " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql10, conn);
                while(rs1.next()){
                    Inquiry=Integer.parseInt(rs1.getString("inquiry"));
                }
                sql14="select ifnull(sum(_inquirytransfer),0) as inquiry1 " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql14, conn);
                while(rs1.next()){
                    InquiryTr=Integer.parseInt(rs1.getString("inquiry1"));
                }
                sql11="select ifnull(sum(_complaint),0) as complain " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql11, conn);
                while(rs1.next()){
                    Complain=Integer.parseInt(rs1.getString("complain"));
                }
                sql12="select ifnull(sum(_blankcall),0) as _blankcall " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql12, conn);
                while(rs1.next()){
                    BlankCall=Integer.parseInt(rs1.getString("_blankcall"));
                }
                sql15="select ifnull(sum(_suggestion),0) as _prankcall " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql15, conn);
                while(rs1.next()){
                    PrankCall=Integer.parseInt(rs1.getString("_prankcall"));
                }
                sql1="select ifnull(sum(_wrongnumber),0) as wronum " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    WrongNum=Integer.parseInt(rs1.getString("wronum"));
                }

                sql13="update report_inbound set " +
                        "inb_call_count='"+InboundCall+"' " +
                        ",answered_call='"+AnsweredCall+"' " +
                        ",abandon_call='"+AbandonCall+"' " +
                        ",total_inb_call_time='"+TotalInbCall+"' " +
                        ",avg_inb_call='"+AvgInbCall+"' " +
                        ",total_inb_busy_time='"+TotalInbBusy+"' " +
                        ",avg_inb_busy='"+AvgInbBusy+"' " +
                        ",blank_call='"+BlankCall+"' " +
                        ",prank_call='"+PrankCall+"' " +
                        ",complain='"+Complain+"' " +
                        ",inquiry='"+Inquiry+"' " +
                        ",inquiry_trans='"+InquiryTr+"' " +
                        ",wrong_num='"+WrongNum+"' " +
                        "where date='"+tgl+"' and username='"+nama+"' ";
                jconn.SQLExecute(sql13, conn);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_inbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  perfin[0]=rs.getString("Date");
                  perfin[1]=rs.getString("username");
                  perfin[2]=rs.getString("inb_call_count");
                  perfin[3]=rs.getString("answered_call");
                  perfin[4]=rs.getString("abandon_call");
                  perfin[5]=rs.getString("total_inb_call_time");
                  perfin[6]=rs.getString("avg_inb_call");
                  perfin[7]=rs.getString("total_inb_busy_time");
                  perfin[8]=rs.getString("avg_inb_busy");
                  perfin[9]=rs.getString("blank_call");
                  perfin[10]=rs.getString("prank_call");
                  perfin[11]=rs.getString("complain");
                  perfin[12]=rs.getString("inquiry");
                  perfin[13]=rs.getString("inquiry_trans");
                  perfin[14]=rs.getString("wrong_num");
                  tabperfin.addRow(perfin);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnpi1ActionPerformed

    private void btnpo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpo1ActionPerformed
        // TODO add your handling code here:
        tabperfou.setRowCount(0);
        String tgl;
        String nama;
        Date dt1 =dtpo.getDate();
        Date dt2 =dtpo1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        perfou1 = sdf.format(dt1);
        perfou2 = sdf.format(dt2);
        sql="delete from report_outbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_outbound(date, username) " +
                "select distinct log_date, username from log_phone " +
                "where _direction=1 " +
                "and log_date between '"+perfou1+"' and '"+perfou2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date, username from report_outbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                nama=rs.getString(2);

                sql1="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    OutCall=Integer.parseInt(rs1.getString("total_outbound"));
                }
                sql2="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    TotalOutCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql3="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AvgOutCall=String.valueOf(rs1.getString("avg_outbound"));
                }
                sql4="select count(caller_type) as customer " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer' ";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    Customer=Integer.parseInt(rs1.getString("customer"));
                }
                sql5="select count(caller_type) as noncust " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Non-customer' ";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    Non_customer=Integer.parseInt(rs1.getString("noncust"));
                }
//                sql6="select count(caller_type) as cdriver " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-Driver' ";
//                rs1=jconn.SQLExecuteRS(sql6, conn);
//                while(rs1.next()){
//                    CustDriver=Integer.parseInt(rs1.getString("cdriver"));
//                }
//                sql7="select count(caller_type) as cuser " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-User' ";
//                rs1=jconn.SQLExecuteRS(sql7, conn);
//                while(rs1.next()){
//                    CustUser=Integer.parseInt(rs1.getString("cuser"));
//                }
                sql8="select count(caller_type) as cpic " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='PIC' ";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    CustPIC=Integer.parseInt(rs1.getString("cpic"));
                }
//                sql9="select count(caller_type) as cother " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-Other' ";
//                rs1=jconn.SQLExecuteRS(sql9, conn);
//                while(rs1.next()){
//                    CustOther=Integer.parseInt(rs1.getString("cother"));
//                }
//                sql10="select count(caller_type) as ianj " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type = 'Internal-ANJ' ";
//                rs1=jconn.SQLExecuteRS(sql10, conn);
//                while(rs1.next()){
//                    IntANJ=Integer.parseInt(rs1.getString("ianj"));
//                }
//                sql11="select count(caller_type) as icc " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-CC' ";
//                rs1=jconn.SQLExecuteRS(sql11, conn);
//                while(rs1.next()){
//                    IntCC=Integer.parseInt(rs1.getString("icc"));
//                }
//                sql12="select count(caller_type) as icso " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-CSO' ";
//                rs1=jconn.SQLExecuteRS(sql12, conn);
//                while(rs1.next()){
//                    IntCSO=Integer.parseInt(rs1.getString("icso"));
//                }
//                sql13="select count(caller_type) as idriver " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-Driver' ";
//                rs1=jconn.SQLExecuteRS(sql13, conn);
//                while(rs1.next()){
//                    IntDriver=Integer.parseInt(rs1.getString("idriver"));
//                }
//                sql14="select count(caller_type) as iother " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and username='"+nama+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-Other' ";
//                rs1=jconn.SQLExecuteRS(sql14, conn);
//                while(rs1.next()){
//                    IntOther=Integer.parseInt(rs1.getString("iother"));
//                }

                sql15="update report_outbound set " +
                        "out_call_count='"+OutCall+"' " +
                        ",total_out_call_time='"+TotalOutCall+"' " +
                        ",avg_out_call='"+AvgOutCall+"' " +
                        ",customer_count='"+Customer+"' " +
                        ",noncust_count='"+Non_customer+"' " +
//                        ",custdriver_count='"+CustDriver+"' " +
//                        ",custuser_count='"+CustUser+"' " +
                        ",custpic_count='"+CustPIC+"' " +
//                        ",custother_count='"+CustOther+"' " +
//                        ",internalanj_count='"+IntANJ+"' " +
//                        ",internalcc_count='"+IntCC+"' " +
//                        ",internalcso_count='"+IntCSO+"' " +
//                        ",internaldriver_count='"+IntDriver+"' " +
//                        ",internalother_count='"+IntOther+"' " +
                        "where date='"+tgl+"' and username='"+nama+"' ";
                jconn.SQLExecute(sql15, conn);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_outbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  perfou[0]=rs.getString("Date");
                  perfou[1]=rs.getString("username");
                  perfou[2]=rs.getString("out_call_count");
                  perfou[3]=rs.getString("total_out_call_time");
                  perfou[4]=rs.getString("avg_out_call");
                  perfou[5]=rs.getString("customer_count");
                  perfou[6]=rs.getString("noncust_count");
//                  perfou[7]=rs.getString("custdriver_count");
//                  perfou[8]=rs.getString("custuser_count");
                  perfou[7]=rs.getString("custpic_count");
//                  perfou[10]=rs.getString("custother_count");
//                  perfou[11]=rs.getString("internalanj_count");
//                  perfou[12]=rs.getString("internalcc_count");
//                  perfou[13]=rs.getString("internalcso_count");
//                  perfou[14]=rs.getString("internaldriver_count");
//                  perfou[15]=rs.getString("internalother_count");
                  tabperfou.addRow(perfou);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnpo1ActionPerformed

    private void btndiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndiActionPerformed
        // TODO add your handling code here:
        tabdayin.setRowCount(0);
        String tgl;
        Date dt1 =dtdi.getDate();
        Date dt2 =dtdi1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dayin1 = sdf.format(dt1);
        dayin2 = sdf.format(dt2);
        sql="delete from report_inbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_inbound(date) " +
                "select distinct log_date from log_phone " +
                "where _direction=0 " +
                "and log_date between '"+dayin1+"' and '"+dayin2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date from report_inbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                System.out.print("\n isi tgl = "+tgl);

                sql1="select count(username) as total_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    InboundCall=Integer.parseInt(rs1.getString("total_inbound"));
                }
                sql2="select count(username) as total_phantom " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0 " +
                        "and _callstatus=-1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    PhantomCall=Integer.parseInt(rs1.getString("total_phantom"));
                }
                sql3="select count(username) as total_answered " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0 " +
                        "and _callstatus=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AnsweredCall=Integer.parseInt(rs1.getString("total_answered"));
                }
                sql4="select count(username) as total_abandon " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0 " +
                        "and _callstatus=0";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    AbandonCall=Integer.parseInt(rs1.getString("total_abandon"));
                }
                sql5="select ifnull(sec_to_time(sum(time_to_sec(delay))),0) as total_delay " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    TotalInbDelay=String.valueOf(rs1.getString("total_delay"));
                }
                sql6="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql6, conn);
                while(rs1.next()){
                    TotalInbCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql7="select ifnull(sec_to_time(sum(time_to_sec(busy))),0) as total_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql7, conn);
                while(rs1.next()){
                    TotalInbBusy=String.valueOf(rs1.getString("total_busy"));
                }
                sql8="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    AvgInbCall=String.valueOf(rs1.getString("avg_inbound"));
                }
                sql9="select ifnull(sec_to_time(avg(time_to_sec(busy))),0) as avg_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql9, conn);
                while(rs1.next()){
                    AvgInbBusy=String.valueOf(rs1.getString("avg_busy"));
                }
                sql10="select ifnull(sum(_inquiry),0) as inquiry " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql10, conn);
                while(rs1.next()){
                    Inquiry=Integer.parseInt(rs1.getString("inquiry"));
                }
                sql14="select ifnull(sum(_inquirytransfer),0) as inquiry1 " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql14, conn);
                while(rs1.next()){
                    InquiryTr=Integer.parseInt(rs1.getString("inquiry1"));
                }
                sql11="select ifnull(sum(_complaint),0) as complain " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql11, conn);
                while(rs1.next()){
                    Complain=Integer.parseInt(rs1.getString("complain"));
                }
                sql12="select ifnull(sum(_blankcall),0) as _blankcall " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql12, conn);
                while(rs1.next()){
                    BlankCall=Integer.parseInt(rs1.getString("_blankcall"));
                }
                sql15="select ifnull(sum(_suggestion),0) as _prankcall " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql15, conn);
                while(rs1.next()){
                    PrankCall=Integer.parseInt(rs1.getString("_prankcall"));
                }
                sql1="select ifnull(sum(_wrongnumber),0) as wronum " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    WrongNum=Integer.parseInt(rs1.getString("wronum"));
                }

                sql13="update report_inbound set " +
                        "inb_call_count='"+InboundCall+"' " +
                        ",answered_call='"+AnsweredCall+"' " +
                        ",abandon_call='"+AbandonCall+"' " +
                        ",total_inb_call_time='"+TotalInbCall+"' " +
                        ",avg_inb_call='"+AvgInbCall+"' " +
                        ",total_inb_busy_time='"+TotalInbBusy+"' " +
                        ",avg_inb_busy='"+AvgInbBusy+"' " +
                        ",blank_call='"+BlankCall+"' " +
                        ",prank_call='"+PrankCall+"' " +
                        ",wrong_num='"+WrongNum+"' " +
                        ",complain='"+Complain+"' " +
                        ",inquiry='"+Inquiry+"' " +
                        ",inquiry_trans='"+InquiryTr+"' " +
                        "where date='"+tgl+"' ";
                jconn.SQLExecute(sql13, conn);
                System.out.print("\nisi sql13 = "+sql13);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_inbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  dayin[0]=rs.getString("Date");
                  dayin[1]=rs.getString("inb_call_count");
                  dayin[2]=rs.getString("answered_call");
                  dayin[3]=rs.getString("abandon_call");
                  dayin[4]=rs.getString("total_inb_call_time");
                  dayin[5]=rs.getString("avg_inb_call");
                  dayin[6]=rs.getString("total_inb_busy_time");
                  dayin[7]=rs.getString("avg_inb_busy");
                  dayin[8]=rs.getString("blank_call");
                  dayin[9]=rs.getString("prank_call");
                  dayin[10]=rs.getString("complain");
                  dayin[11]=rs.getString("inquiry");
                  dayin[13]=rs.getString("inquiry_trans");
                  dayin[14]=rs.getString("wrong_num");
//                  dayin[13]=rs.getString("internaldriver_count");
//                  dayin[14]=rs.getString("internalother_count");
                  tabdayin.addRow(dayin);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }

}//GEN-LAST:event_btndiActionPerformed

    private void btndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndoActionPerformed
        // TODO add your handling code here:
        tabdayou.setRowCount(0);
        String tgl;
        Date dt1 =dtdo.getDate();
        Date dt2 =dtdo1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dayou1 = sdf.format(dt1);
        dayou2 = sdf.format(dt2);
        sql="delete from report_outbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_outbound(date) " +
                "select distinct log_date from log_phone " +
                "where _direction=1 " +
                "and log_date between '"+dayou1+"' and '"+dayou2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date from report_outbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                System.out.print("\n isi tgl = "+tgl);

                sql1="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    OutCall=Integer.parseInt(rs1.getString("total_outbound"));
                }
                sql2="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    TotalOutCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql3="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AvgOutCall=String.valueOf(rs1.getString("avg_outbound"));
                }
                sql4="select count(caller_type) as customer " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer' ";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    Customer=Integer.parseInt(rs1.getString("customer"));
                }
                sql5="select count(caller_type) as noncust " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Non-customer' ";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    Non_customer=Integer.parseInt(rs1.getString("noncust"));
                }
//                sql6="select count(caller_type) as cdriver " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-Driver' ";
//                rs1=jconn.SQLExecuteRS(sql6, conn);
//                while(rs1.next()){
//                    CustDriver=Integer.parseInt(rs1.getString("cdriver"));
//                }
//                sql7="select count(caller_type) as cuser " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-User' ";
//                rs1=jconn.SQLExecuteRS(sql7, conn);
//                while(rs1.next()){
//                    CustUser=Integer.parseInt(rs1.getString("cuser"));
//                }
                sql8="select count(caller_type) as cpic " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='PIC' ";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    CustPIC=Integer.parseInt(rs1.getString("cpic"));
                }
//                sql9="select count(caller_type) as cother " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Customer-Other' ";
//                rs1=jconn.SQLExecuteRS(sql9, conn);
//                while(rs1.next()){
//                    CustOther=Integer.parseInt(rs1.getString("cother"));
//                }
//                sql10="select count(caller_type) as ianj " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type = 'Internal-ANJ' ";
//                rs1=jconn.SQLExecuteRS(sql10, conn);
//                while(rs1.next()){
//                    IntANJ=Integer.parseInt(rs1.getString("ianj"));
//                }
//                sql11="select count(caller_type) as icc " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-CC' ";
//                rs1=jconn.SQLExecuteRS(sql11, conn);
//                while(rs1.next()){
//                    IntCC=Integer.parseInt(rs1.getString("icc"));
//                }
//                sql12="select count(caller_type) as icso " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-CSO' ";
//                rs1=jconn.SQLExecuteRS(sql12, conn);
//                while(rs1.next()){
//                    IntCSO=Integer.parseInt(rs1.getString("icso"));
//                }
//                sql13="select count(caller_type) as idriver " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-Driver' ";
//                rs1=jconn.SQLExecuteRS(sql13, conn);
//                while(rs1.next()){
//                    IntDriver=Integer.parseInt(rs1.getString("idriver"));
//                }
//                sql14="select count(caller_type) as iother " +
//                        "from log_phone " +
//                        "where log_date='"+tgl+"' " +
//                        "and _direction=1 " +
//                        "and caller_type='Internal-Other' ";
//                rs1=jconn.SQLExecuteRS(sql14, conn);
//                while(rs1.next()){
//                    IntOther=Integer.parseInt(rs1.getString("iother"));
//                }

                sql15="update report_outbound set " +
                        "out_call_count='"+OutCall+"' " +
                        ",total_out_call_time='"+TotalOutCall+"' " +
                        ",avg_out_call='"+AvgOutCall+"' " +
                        ",customer_count='"+Customer+"' " +
                        ",noncust_count='"+Non_customer+"' " +
//                        ",custdriver_count='"+CustDriver+"' " +
//                        ",custuser_count='"+CustUser+"' " +
                        ",custpic_count='"+CustPIC+"' " +
//                        ",custother_count='"+CustOther+"' " +
//                        ",internalanj_count='"+IntANJ+"' " +
//                        ",internalcc_count='"+IntCC+"' " +
//                        ",internalcso_count='"+IntCSO+"' " +
//                        ",internaldriver_count='"+IntDriver+"' " +
//                        ",internalother_count='"+IntOther+"' " +
                        "where date='"+tgl+"' ";
                jconn.SQLExecute(sql15, conn);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_outbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  dayou[0]=rs.getString("Date");
                  dayou[1]=rs.getString("out_call_count");
                  dayou[2]=rs.getString("total_out_call_time");
                  dayou[3]=rs.getString("avg_out_call");
                  dayou[4]=rs.getString("customer_count");
                  dayou[5]=rs.getString("noncust_count");
//                  dayou[6]=rs.getString("custdriver_count");
//                  dayou[7]=rs.getString("custuser_count");
                  dayou[6]=rs.getString("custpic_count");
//                  dayou[9]=rs.getString("custother_count");
//                  dayou[10]=rs.getString("internalanj_count");
//                  dayou[11]=rs.getString("internalcc_count");
//                  dayou[12]=rs.getString("internalcso_count");
//                  dayou[13]=rs.getString("internaldriver_count");
//                  dayou[14]=rs.getString("internalother_count");
                  tabdayou.addRow(dayou);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btndoActionPerformed

    private void cksubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cksubmitActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cksubmitActionPerformed

    private void pnlscrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlscrollMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            lblscroll.setText("");
            Scrol.stop();
        }
    }//GEN-LAST:event_pnlscrollMouseClicked

    private void btnexportcall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall1ActionPerformed
        // TODO add your handling code here:
        tabex=tabhoin;
        createexcel();
    }//GEN-LAST:event_btnexportcall1ActionPerformed

    private void btnexportcall2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall2ActionPerformed
        // TODO add your handling code here:
        tabex=tabhoou;
        createexcel();
    }//GEN-LAST:event_btnexportcall2ActionPerformed

    private void btnexportcall3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall3ActionPerformed
        // TODO add your handling code here:
        tabex=tabdayin;
        createexcel();
    }//GEN-LAST:event_btnexportcall3ActionPerformed

    private void btnexportcall4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall4ActionPerformed
        // TODO add your handling code here:
        tabex=tabdayou;
        createexcel();
    }//GEN-LAST:event_btnexportcall4ActionPerformed

    private void btnexportcall5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall5ActionPerformed
        // TODO add your handling code here:
        tabex=tabperfin;
        createexcel();
    }//GEN-LAST:event_btnexportcall5ActionPerformed

    private void btnexportcall6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall6ActionPerformed
        // TODO add your handling code here:
        tabex=tabperfou;
        createexcel();
    }//GEN-LAST:event_btnexportcall6ActionPerformed

    private void cbcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcustActionPerformed
        // TODO add your handling code here:
        if(cbcust.getSelectedIndex()==-1){
            btncussavesms.setEnabled(false);
        }else if(!cbcust.getSelectedItem().equals(cuscom)){
            btncussavesms.setEnabled(true);
        }else{
            btncussavesms.setEnabled(false);
        }
    }//GEN-LAST:event_cbcustActionPerformed

    private void cbcust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcust1ActionPerformed
        // TODO add your handling code here:
        if(cbcust1.getSelectedIndex()==-1){
            btncussaveEmail.setEnabled(false);
        }else if(!cbcust1.getSelectedItem().equals(cuscom1)){
            btncussaveEmail.setEnabled(true);
        }else{
            btncussaveEmail.setEnabled(false);
        }
    }//GEN-LAST:event_cbcust1ActionPerformed

    private void btncussavesmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncussavesmsActionPerformed
        // TODO add your handling code here:
        btncussavesms.setEnabled(false);
        sql="update log_sms set"
                + " ticket_no='"+txtnoticsms.getText()+"'"
//                + ", cust_name='"+cbcust.getSelectedItem()+"'"
                + " where sms_id='"+smsid+"' limit 1";
        jconn.SQLExecute(sql, conn);
        tabelsin();
    }//GEN-LAST:event_btncussavesmsActionPerformed

    private void btncussaveEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncussaveEmailActionPerformed
        // TODO add your handling code here:
        btncussaveEmail.setEnabled(false);
        sql="update log_mail set"
                + " ticket_no='"+txtnoticmail.getText()+"'"
//                + ", cust_name='"+cbcust1.getSelectedItem()+"'"
                + " where mail_id='"+mailid+"' limit 1";
        jconn.SQLExecute(sql, conn);
        tabelmin();
    }//GEN-LAST:event_btncussaveEmailActionPerformed

    private void btncussaveFaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncussaveFaxActionPerformed
        // TODO add your handling code here:
        btncussaveFax.setEnabled(false);
        sql="update log_fax set"
                + " ticket_no='"+txtnoticfax.getText()+"'"
//                + ", cust_name='"+cbcust2.getSelectedItem()+"'"
                + " where fax_id='"+faxid+"' limit 1";
        jconn.SQLExecute(sql, conn);
        tabelfin();
    }//GEN-LAST:event_btncussaveFaxActionPerformed

    private void cbcust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcust2ActionPerformed
        // TODO add your handling code here:
        if(cbcust2.getSelectedIndex()==-1){
            btncussaveFax.setEnabled(false);
        }else if(!cbcust2.getSelectedItem().equals(cuscom2)){
            btncussaveFax.setEnabled(true);
        }else{
            btncussaveFax.setEnabled(false);
        }
    }//GEN-LAST:event_cbcust2ActionPerformed

    private void dtmsiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmsiPropertyChange
        // TODO add your handling code here:
        tabelmsin();
    }//GEN-LAST:event_dtmsiPropertyChange

    private void dtmsi1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmsi1PropertyChange
        // TODO add your handling code here:
        tabelmsin();
    }//GEN-LAST:event_dtmsi1PropertyChange

    private void dtmsoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmsoPropertyChange
        // TODO add your handling code here:
        tabelmsou();
    }//GEN-LAST:event_dtmsoPropertyChange

    private void dtmso1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmso1PropertyChange
        // TODO add your handling code here:
        tabelmsou();
    }//GEN-LAST:event_dtmso1PropertyChange
String nmfile, fullnmfile, nmfile1, fullnmfile1;
    private void btnAttachmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachmentActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser(att);
        chooser.setSelectedFile(new File(att));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            nmfile=(chooser.getSelectedFile().getName().toString());
            System.out.print("\nnamafile: " + nmfile + "\n");
            fullnmfile=(chooser.getSelectedFile().getAbsolutePath());
            //            s = "DOWNLOAD|EMAIL|"+nmfile+"|"+fullnmfile+"|localhost|cc_takaful\r\n";
            s="";
            s = "DOWNLOAD|EMAIL|0|"+att+"|"+fullnmfile+"|192.168.0.48|anj_mail\r\n";
//            s = "DOWNLOAD|EMAIL|0|"+att+"|"+fullnmfile+"|192.168.0.8|anj_mail\r\n";
            sendString(s);
            outupload.print(toSend);
            outupload.flush();
            toSend.setLength(0);
        }
}//GEN-LAST:event_btnAttachmentActionPerformed
String att,att1;
Object sel1,sel2;
    private void jList2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseReleased
        // TODO add your handling code here:
        att="";
        int selectedIx = jList2.getSelectedIndex();
        sel1 = jList2.getModel().getElementAt(selectedIx);
        att=String.valueOf(sel1);
        btnAttachment.setEnabled(true);
}//GEN-LAST:event_jList2MouseReleased

    private void jList3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MouseReleased
        // TODO add your handling code here:
        att1="";
        int selectedIx = jList3.getSelectedIndex();
        sel2 = jList3.getModel().getElementAt(selectedIx);
        att1=String.valueOf(sel2);
        btnAttachment1.setEnabled(true);
}//GEN-LAST:event_jList3MouseReleased

    private void btnAttachment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachment1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser(att1);
        chooser.setSelectedFile(new File(att1));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            nmfile1=(chooser.getSelectedFile().getName().toString());
            fullnmfile1=(chooser.getSelectedFile().getAbsolutePath());
            //            s = "DOWNLOAD|EMAIL|"+nmfile+"|"+fullnmfile+"|localhost|cc_takaful\r\n";
            s = "DOWNLOAD|EMAIL|1|"+att1+"|"+fullnmfile1+"|192.168.0.48|anj_mail\r\n";
            kirimUplo();
        }
}//GEN-LAST:event_btnAttachment1ActionPerformed

    private void cbcateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcateActionPerformed

    private void tblvrfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblvrfMouseClicked
        // TODO add your handling code here:
//        if(evt.getClickCount()==1){
//            tabou.setRowCount(0);
//            tabelou();
//        }
//        if(evt.getClickCount()==2){
//           cccs_OutBound obc = new cccs_OutBound();
//           obc.setVisible(true);

//           Obc.t
//        }
    }//GEN-LAST:event_tblvrfMouseClicked

    private void tblcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcusMouseClicked
        // TODO add your handling code here:
        if(tabticconf.getRowCount()!=0){
            if(evt.getClickCount()==2){
                cccs_verif vrcus= new cccs_verif();
                vrcus.setVisible(true);

//                vrcus.Form=1;
                vrcus.IdCust=((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Id")));
                vrcus.open();         
            }
        }
    }//GEN-LAST:event_tblcusMouseClicked

    private void btnoutsrch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoutsrch1ActionPerformed
        // TODO add your handling code here:
        tabelticconf();
    }//GEN-LAST:event_btnoutsrch1ActionPerformed

    private void btnreadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreadyActionPerformed
        // TODO add your handling code here:
        dataregis();
        if (counter==0){
            String info="";
            if(cbdirection.getSelectedItem().equals("INBOUND")){
                btnready.setText("Unregis");
                lblactivity.setText("Registered");
                cbdirection.setEnabled(false);
                btnlogout.setEnabled(false);
                s = "REGISTER|"+pabx+"|"+in_ext+"|"+in_ext+"\r\n";                  
                kirimTele();
                counter++;
                receiv.start();
                info="INBOUND";
            }else{
                btnready.setText("Unregis");
                lblactivity.setText("Registered");
                btnoutbound.setEnabled(true);
                cbdirection.setEnabled(false);
                btnlogout.setEnabled(false);
                s = "REGISTER|"+pabx+"|"+out_ext+"|"+out_ext+"\r\n";                  
                kirimTele();
                counter++;
                outbound=true;
                info="OUTBOUND";
            }
            sql3="update user_account set _activity=4, info='"+info+"' ,time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
            jconn.SQLExecute(sql3,conn);
        }else{
           if(cbdirection.getSelectedItem().equals("INBOUND")){
                btnready.setText("Ready");
                lblactivity.setText("Disconnected");
                btncall.setEnabled(false);
                cbdirection.setEnabled(true);
                btnlogout.setEnabled(true);
                s = "UNREGISTER|"+pabx+"|"+in_ext+"|"+in_ext+"\r\n";                  
                kirimTele();
                counter=0;
           }else{
                btnready.setText("Ready");
                lblactivity.setText("Disconnected");
                cbdirection.setEnabled(true);
                btnlogout.setEnabled(true);
                s = "UNREGISTER|"+pabx+"|"+out_ext+"|"+out_ext+"\r\n";                  
                kirimTele();
                counter=0;
                outbound=false;
                if(txtcalnoti.getText().equals("")){
                    btnoutbound.setEnabled(false);  
                }
           }
           sql3="update user_account set _activity=1, info='' ,time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
            jconn.SQLExecute(sql3,conn);
        }
    }//GEN-LAST:event_btnreadyActionPerformed

    private void btnsmsCALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsCALLActionPerformed
        // TODO add your handling code here:
        cccs_OutBound out = new cccs_OutBound();
        out.setVisible(true);

        out.txtdial.setText(txtfrom2.getText());
    }//GEN-LAST:event_btnsmsCALLActionPerformed

    private void btnEmailCALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailCALLActionPerformed
        // TODO add your handling code here:
        cccs_OutBound out = new cccs_OutBound();
        out.setVisible(true);
    }//GEN-LAST:event_btnEmailCALLActionPerformed

    private void btnEmailCALL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailCALL1ActionPerformed
        // TODO add your handling code here:
        cccs_EMail Ecom=new cccs_EMail();
        Ecom.setVisible(true);

        Ecom.txtcto.setText(txtfrom.getText());
//        Ecom.txtccc.setText(txtcc.getText());
        Ecom.txtcsu.setText(txtisu.getText());
        Ecom.txtcmsg.setText("From : "+txtfrom.getText()
//                            +"\nCc : "+txtcc.getText()
                            +"\nSubject : "+txtisu.getText()
                            +"\n\n"+txtimsg.getText());
    }//GEN-LAST:event_btnEmailCALL1ActionPerformed

    private void createexcel(){
        int koltab=0;
        int counter=1;
        int kolom=0;
        int baris=0;
        int k;
        int b;

        JFileChooser chooser = new JFileChooser("");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try{

                //Membuat workbook baru dengan nama laporan.xls
                WritableWorkbook workBook = Workbook.createWorkbook(new File(chooser.getSelectedFile().getAbsolutePath()+".xls"));


                 //Membuat sheet dengan nama Sheet pertama
                 WritableSheet sheet = workBook.createSheet("First sheet ",0);
                 System.out.print("\n debug : pembuatan header");

            Label label;
            for(int q=0;q<tabex.getColumnCount();q++){
                label = new Label(q,0,(tabex.getColumnName(kolom).toString()));
                kolom++;
                sheet.addCell(label);
            }

            k=tabex.getColumnCount();
                k-=1;
            b=tabex.getRowCount();
    //            b+=1;

           while(counter<=b){
               if (koltab>=k){
                   koltab=0;
               }
               while(koltab<=k){
                   if(kolom>k){
                       kolom=0;
                   }
    //               System.out.print(tblreptic.getValueAt(baris, kolom));
                   if (tabex.getValueAt(baris, kolom)==null){
                       label = new Label(koltab,counter,"");
                   }else{
                       label = new Label(koltab,counter,(tabex.getValueAt(baris, kolom).toString()));
                   }
                   koltab++;
                   kolom++;

                   sheet.addCell(label);
               }
               counter++;
               baris++;
           }
            workBook.write();
            workBook.close();
            JOptionPane.showMessageDialog(null, "SUCCESSED EXPORTING TO EXCEL", "REPORTING",JOptionPane.INFORMATION_MESSAGE);


            } catch (WriteException ex) {
                System.out.print(ex);
            } catch (IOException ex) {
                System.out.print(ex);
            }
           catch (Exception ex){
               ex.printStackTrace();
           }
        }
    }
            
       
                
        public static javax.swing.table.DefaultTableModel getDefaultTabelrepsms(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Sms Id","Sms Date","Sms Time","Username","Status","Direction","Sms From","Sms To","Sms Text","Ticket Id",/*"ref_no","rpt_code",*/"Retry Count","Destination Type","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
        public static javax.swing.table.DefaultTableModel getDefaultTabelrepmail(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Mail Id","Mail Date","Mail Time","Mail From","Mail To","Mail Cc","Mail Subject","Mail Text","Mail Read","Username","Status","Ticket Id","Direction","Destination Type","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
        public static javax.swing.table.DefaultTableModel getDefaultTabelrepfax(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"fax_id","recipient","sender","username","doc_name","sent_time","rcvd_time","_status","_direction","ticket no","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
        public static javax.swing.table.DefaultTableModel getDefaultTabelact(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Username","Level","Activity","Info","Login","Host address","Line number"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }   
    
    public static javax.swing.table.DefaultTableModel getDefaultTabelmsin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","From","Read","Message",""}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelmsou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","Recipient","Message",""}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelhoin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Hour","Total Receive","Answered","Abandoned","Call Duration","Avg Call Duration"
                        ,"ACW Time","Avg ACW Time","Blank Call","Others"
                        ,"Inquiry","Request","Complain","Wrong Number"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelhoou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Hour","Outgoing Call","Outbound Call Time","Avg Outbound Call Time","Customer"
                        ,"Non-customer","Customer-PIC"
//                        ,"Customer-Driver","Customer-User","Customer-PIC","Customer-Other"
//                        ,"Internal-ANJ","Internal-CC","Internal-CSO","Internal-Driver","Internal-Other"
        }){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabeldayin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Total Receive","Answered","Abandoned"/*,"Phantom"*/,"Call Duration","Avg Call Duration"
                        ,"ACW Time","Avg ACW Time","Blank Call","Others"
                        ,"Complain","Inquiry","Request","Wrong Number"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                            ,false,false,false,false,false,false
                            ,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabeldayou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Outgoing Call","Outbound Call Time","Avg Outbound Call Time","Customer"
                        ,"Non-customer","Customer-PIC"
//                        ,"Customer-Driver","Customer-User","Customer-PIC","Customer-Other"
//                        ,"Internal-ANJ","Internal-CC","Internal-CSO","Internal-Driver","Internal-Other"
        }){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelperfin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Agent","Total Receive","Answered","Abandoned","Call Duration","Avg Call Duration"
                        ,"ACW Time","Avg ACW Time","Blank Call","Others"
                        ,"Complain","Inquiry","Request","wrong Number"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false
                            ,false,false,false,false,false,false
                            ,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelperfou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Agent","Outgoing Call","Outbound Call Time","Avg Outbound Call Time","Customer"
                        ,"Non-customer","Customer-PIC"
//                        ,"Customer-Driver","Customer-User","Customer-PIC","Customer-Other"
//                        ,"Internal-ANJ","Internal-CC","Internal-CSO","Internal-Driver","Internal-Other"
        }){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
       private static void appendToChatBox(String s) {
         synchronized (toAppend) {
             toAppend.append(s);
      }
   }
       private static void sendString(String s) {
         synchronized (toSend) {
             toSend.append(s + "\r\n");
      }
   }
       private static void sending(String pik) {
         synchronized (toSend) {
             toSend.append(pik + "\r\n");
      }
   }
       private static void angkat(String pick) {
         synchronized (toSend) {
             toSend.append(pick + "\r\n");
      }
   }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactCenterOrangeTv().setVisible(true);
            }
        });
    }

    public static javax.swing.table.DefaultTableModel tabex;
    public static javax.swing.table.DefaultTableModel tabin=getDefaultTabelin();
    public static javax.swing.table.DefaultTableModel tabou=getDefaultTabelout();
    public static javax.swing.table.DefaultTableModel tabtic=getDefaultTabeltic();
    public static javax.swing.table.DefaultTableModel tabreptic=getDefaultTabelreptic();
    public static javax.swing.table.DefaultTableModel tabrepcal=getDefaultTabelrepcal();
    public static javax.swing.table.DefaultTableModel tabrepsms=getDefaultTabelrepsms();
    public static javax.swing.table.DefaultTableModel tabrepmail=getDefaultTabelrepmail();
    public static javax.swing.table.DefaultTableModel tabrepfax=getDefaultTabelrepfax();
    public static javax.swing.table.DefaultTableModel tabact=getDefaultTabelact();
    public static javax.swing.table.DefaultTableModel tabmin=getDefaultTabelmin();
    public static javax.swing.table.DefaultTableModel tabmou=getDefaultTabelmout();
    public static javax.swing.table.DefaultTableModel tabsin=getDefaultTabelsin();
    public static javax.swing.table.DefaultTableModel tabsou=getDefaultTabelsou();
    public static javax.swing.table.DefaultTableModel tabfin=getDefaultTabelfin();
    public static javax.swing.table.DefaultTableModel tabfou=getDefaultTabelfou();
    public static javax.swing.table.DefaultTableModel tabmsin=getDefaultTabelmsin();
    public static javax.swing.table.DefaultTableModel tabmsou=getDefaultTabelmsou();
    public static javax.swing.table.DefaultTableModel tabhoin=getDefaultTabelhoin();
    public static javax.swing.table.DefaultTableModel tabhoou=getDefaultTabelhoou();
    public static javax.swing.table.DefaultTableModel tabdayin=getDefaultTabeldayin();
    public static javax.swing.table.DefaultTableModel tabdayou=getDefaultTabeldayou();
    public static javax.swing.table.DefaultTableModel tabperfin=getDefaultTabelperfin();
    public static javax.swing.table.DefaultTableModel tabperfou=getDefaultTabelperfou();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAttachment;
    public static javax.swing.JButton btnAttachment1;
    private javax.swing.JButton btnEmailCALL;
    private javax.swing.JButton btnEmailCALL1;
    public static javax.swing.JButton btncall;
    private javax.swing.JButton btncomposemsg;
    private javax.swing.JButton btncussaveEmail;
    private javax.swing.JButton btncussaveFax;
    private javax.swing.JButton btncussavesms;
    private javax.swing.JButton btndelmsg;
    private javax.swing.JButton btndelmsg1;
    private javax.swing.JButton btndi;
    private javax.swing.JButton btndo;
    private javax.swing.JButton btnexportcall;
    private javax.swing.JButton btnexportcall1;
    private javax.swing.JButton btnexportcall2;
    private javax.swing.JButton btnexportcall3;
    private javax.swing.JButton btnexportcall4;
    private javax.swing.JButton btnexportcall5;
    private javax.swing.JButton btnexportcall6;
    private javax.swing.JButton btnexportmail;
    private javax.swing.JButton btnexportmail1;
    private javax.swing.JButton btnexportsms;
    private javax.swing.JButton btnexporttic;
    public static javax.swing.JButton btnfax;
    private javax.swing.JButton btnfinsrch;
    private javax.swing.JButton btnfoutsrch;
    private javax.swing.JButton btnhi;
    private javax.swing.JButton btnho;
    private javax.swing.JButton btninsrch;
    public static javax.swing.JButton btnlogout;
    public static javax.swing.JButton btnmail;
    private javax.swing.JButton btnmailinsrch;
    private javax.swing.JButton btnmailoutsrch;
    public static javax.swing.JButton btnoutbound;
    private javax.swing.JButton btnoutsrch;
    private javax.swing.JButton btnoutsrch1;
    private javax.swing.JButton btnpi1;
    private javax.swing.JButton btnpo1;
    public static javax.swing.JButton btnready;
    private javax.swing.JButton btnrelease;
    private javax.swing.JButton btnrepcal;
    private javax.swing.JButton btnrepfax;
    private javax.swing.JButton btnreplymsg;
    private javax.swing.JButton btnrepmail;
    private javax.swing.JButton btnrepsms;
    private javax.swing.JButton btnreptic;
    public static javax.swing.JButton btnsenddept;
    public static javax.swing.JButton btnsms;
    private javax.swing.JButton btnsmsCALL;
    private javax.swing.JButton btnsmsinsrch;
    private javax.swing.JButton btnsmsoutsrch;
    private javax.swing.JButton btnticsrch;
    private javax.swing.JComboBox cbDist;
    public static javax.swing.JComboBox cbagenin;
    private javax.swing.JComboBox cbagenirepcal;
    private javax.swing.JComboBox cbagenirepcal1;
    private javax.swing.JComboBox cbagenirepfax;
    private javax.swing.JComboBox cbagenou;
    private javax.swing.JComboBox cbagenrelease;
    private javax.swing.JComboBox cbagenrepmail;
    private javax.swing.JComboBox cbcaldir;
    private javax.swing.JComboBox cbcalstat;
    public static javax.swing.JComboBox cbcalstatin;
    private javax.swing.JComboBox cbcaltyperepcal;
    public static javax.swing.JComboBox cbcate;
    public static javax.swing.JComboBox cbcust;
    public static javax.swing.JComboBox cbcust1;
    public static javax.swing.JComboBox cbcust2;
    public static javax.swing.JComboBox cbdept;
    public static javax.swing.JComboBox cbdirection;
    private javax.swing.JComboBox cbdirfax;
    private javax.swing.JComboBox cbdirmail;
    private javax.swing.JComboBox cbdirrepsms;
    private javax.swing.JComboBox cbstatusrepfax;
    public static javax.swing.JComboBox cbticstatus;
    public static javax.swing.JCheckBox cksubmit;
    public static javax.swing.JCheckBox cktgl;
    private com.toedter.calendar.JDateChooser dccal1;
    private com.toedter.calendar.JDateChooser dccal2;
    private com.toedter.calendar.JDateChooser dcfax1;
    private com.toedter.calendar.JDateChooser dcfax2;
    private com.toedter.calendar.JDateChooser dcmail1;
    private com.toedter.calendar.JDateChooser dcmail2;
    private com.toedter.calendar.JDateChooser dcsms1;
    private com.toedter.calendar.JDateChooser dcsms2;
    private com.toedter.calendar.JDateChooser dctic1;
    private com.toedter.calendar.JDateChooser dctic2;
    public static com.toedter.calendar.JDateChooser dctic3;
    public static com.toedter.calendar.JDateChooser dctic4;
    public static com.toedter.calendar.JDateChooser dctic5;
    public static com.toedter.calendar.JDateChooser dctic6;
    public static com.toedter.calendar.JDateChooser dctic7;
    public static com.toedter.calendar.JDateChooser dctic8;
    private com.toedter.calendar.JDateChooser dtdi;
    private com.toedter.calendar.JDateChooser dtdi1;
    private com.toedter.calendar.JDateChooser dtdo;
    private com.toedter.calendar.JDateChooser dtdo1;
    public static com.toedter.calendar.JDateChooser dtfi;
    public static com.toedter.calendar.JDateChooser dtfi1;
    public static com.toedter.calendar.JDateChooser dtfo;
    public static com.toedter.calendar.JDateChooser dtfo1;
    private com.toedter.calendar.JDateChooser dthi;
    private com.toedter.calendar.JDateChooser dtho;
    public static com.toedter.calendar.JDateChooser dtmi;
    public static com.toedter.calendar.JDateChooser dtmi1;
    public static com.toedter.calendar.JDateChooser dtmo;
    public static com.toedter.calendar.JDateChooser dtmo1;
    public static com.toedter.calendar.JDateChooser dtmsi;
    public static com.toedter.calendar.JDateChooser dtmsi1;
    public static com.toedter.calendar.JDateChooser dtmso;
    public static com.toedter.calendar.JDateChooser dtmso1;
    private com.toedter.calendar.JDateChooser dtpi;
    private com.toedter.calendar.JDateChooser dtpi1;
    private com.toedter.calendar.JDateChooser dtpo;
    private com.toedter.calendar.JDateChooser dtpo1;
    public static com.toedter.calendar.JDateChooser dtsi;
    public static com.toedter.calendar.JDateChooser dtsi1;
    public static com.toedter.calendar.JDateChooser dtso;
    public static com.toedter.calendar.JDateChooser dtso1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    public static javax.swing.JList jList2;
    public static javax.swing.JList jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JDesktopPane jdp;
    private javax.swing.JTabbedPane jtab;
    public static javax.swing.JLabel lblactivity;
    private static javax.swing.JLabel lblcalincount;
    private static javax.swing.JLabel lblcaloutcount;
    private static javax.swing.JLabel lblcaloutcount1;
    private static javax.swing.JLabel lblcaloutcount2;
    private javax.swing.JLabel lbldate;
    public static javax.swing.JLabel lblpas;
    private javax.swing.JLabel lblrepcalcount;
    private javax.swing.JLabel lblrepfaxcount;
    private javax.swing.JLabel lblrepmailcount;
    private javax.swing.JLabel lblrepsmscount;
    private javax.swing.JLabel lblrepticcount;
    private javax.swing.JLabel lblrepticcount1;
    private javax.swing.JLabel lblrepticcount10;
    private javax.swing.JLabel lblrepticcount11;
    private javax.swing.JLabel lblrepticcount12;
    private javax.swing.JLabel lblrepticcount13;
    private javax.swing.JLabel lblrepticcount14;
    private javax.swing.JLabel lblrepticcount3;
    private javax.swing.JLabel lblrepticcount5;
    private javax.swing.JLabel lblrepticcount7;
    private javax.swing.JLabel lblrepticcount9;
    private javax.swing.JLabel lblscroll;
    public static javax.swing.JLabel lblshift;
    public static javax.swing.JLabel lblshift1;
    private static javax.swing.JLabel lblticcount;
    public static javax.swing.JLabel lbluser;
    public static javax.swing.JLabel lblview;
    public static javax.swing.JLabel lblview1;
    private javax.swing.JTabbedPane panelfax;
    private javax.swing.JTabbedPane panelmail;
    private javax.swing.JTabbedPane panelsms;
    private javax.swing.JPanel pninbox;
    private javax.swing.JPanel pninbox1;
    private javax.swing.JPanel pnlDist;
    private javax.swing.JPanel pnlact;
    private javax.swing.JPanel pnlinbon;
    private javax.swing.JTabbedPane pnlinf;
    private javax.swing.JPanel pnlou;
    private javax.swing.JPanel pnlrep;
    private javax.swing.JPanel pnlrep1;
    private javax.swing.JPanel pnlrep2;
    private javax.swing.JPanel pnlrep3;
    private javax.swing.JPanel pnlrep4;
    private javax.swing.JPanel pnlscroll;
    private javax.swing.JPanel pnltic;
    private javax.swing.JPanel pnoutbox;
    private javax.swing.JPanel pnoutbox1;
    private javax.swing.JScrollPane scpCcList1;
    private javax.swing.JScrollPane scpCcList2;
    private javax.swing.JTabbedPane tabbpanereport;
    private javax.swing.JTable tblact;
    private javax.swing.JTable tblcus;
    private javax.swing.JTable tbldailyin;
    private javax.swing.JTable tbldailyout;
    private javax.swing.JTable tblfin;
    private javax.swing.JTable tblfou;
    private javax.swing.JTable tblhourin;
    private javax.swing.JTable tblhourout;
    public static javax.swing.JTable tblin;
    private javax.swing.JTable tblmin;
    private javax.swing.JTable tblmou;
    private javax.swing.JTable tblmsin;
    private javax.swing.JTable tblmsou;
    private javax.swing.JTable tblout;
    private javax.swing.JTable tblperformin;
    private javax.swing.JTable tblperformout;
    private javax.swing.JTable tblrepcal;
    private javax.swing.JTable tblrepfax;
    private javax.swing.JTable tblrepmail;
    private javax.swing.JTable tblrepsms;
    private javax.swing.JTable tblreptic;
    private javax.swing.JTable tblsin;
    private javax.swing.JTable tblsou;
    public static javax.swing.JTable tbltic;
    private javax.swing.JTable tblticconf;
    private javax.swing.JTable tblvrf;
    public static javax.swing.JTextField txtBpAdd;
    public static javax.swing.JTextField txtBpName;
    public static javax.swing.JTextField txtOwner;
    public static javax.swing.JTextField txtcalnoti;
    private javax.swing.JTextField txtcategory;
    public static javax.swing.JTextField txtcus;
    private javax.swing.JTextField txtcustomer;
    private javax.swing.JTextArea txtdetail;
    private javax.swing.JTextField txtdriver;
    private javax.swing.JTextField txtfaxfinm;
    public static javax.swing.JTextField txtfaxnoti;
    private javax.swing.JTextField txtfrom;
    private javax.swing.JTextField txtfrom1;
    private javax.swing.JTextField txtfrom2;
    private javax.swing.JTextField txtidti;
    private javax.swing.JTextArea txtimsg;
    private javax.swing.JTextArea txtimsg1;
    private javax.swing.JTextArea txtimsg2;
    private javax.swing.JTextArea txtimsg3;
    private javax.swing.JTextArea txtimsg4;
    private javax.swing.JTextField txtisu;
    public static javax.swing.JTextField txtmailnoti;
    private javax.swing.JTextField txtmailsub;
    private javax.swing.JTextField txtmailticid;
    public static javax.swing.JTextField txtnoticfax;
    public static javax.swing.JTextField txtnoticmail;
    public static javax.swing.JTextField txtnoticsms;
    private javax.swing.JTextField txtocc;
    private javax.swing.JTextArea txtomsg;
    private javax.swing.JTextField txtosu;
    private javax.swing.JTextField txtoto;
    public static javax.swing.JTextField txtsmsnoti;
    private javax.swing.JTextField txtsmsstat;
    private javax.swing.JTextField txtsmsticid;
    private javax.swing.JTextArea txtsolution;
    private javax.swing.JTextField txtticno;
    public static javax.swing.JTextField txtticno1;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables

    public static String sql;
    public static String sql1;
    public static String sql2;
    public static String sql3;
    public static String sql4;
    public static String sql5;
    public static String sql6;
    public static String sql7;
    public static String sql8;
    public static String sql9;
    public static String sql10;
    public static String sql11;
    public static String sql12;
    public static String sql13;
    public static String sql14;
    public static String sql15;
    public static String sqlid;
    public static String sqlld;
    public static String sqllt;
    public static String condition,endcondition;
    public static ResultSet rs;
    public static ResultSet rs1;
    public static JavaConnector jconn=new JavaConnector();
    public static Connection conn;
    private Timer dateTimer;
    //private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy, HH:mm:ss");
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    
    public static javax.swing.table.DefaultTableModel getDefaultTabelticconf(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"KODE","Dealer","Alamat","Kota/Kabupaten","Kode Pos"       ,"Propinsi","Contact Person","No Telp/HP","Fax"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false       ,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }    
    public static javax.swing.table.DefaultTableModel tabticconf=getDefaultTabelticconf();
    
    private void tabelticconf(){
        tabticconf.setRowCount(0);
             try{
                 sql="select * " +
                      " from contact" +
                      " where contact_id is not null ";
                 condition="";
//                 if(!cbDist.getSelectedItem().equals("--")){
//                     condition=condition+" and distributor_name  = '"+cbDist.getSelectedItem()+"'";
//                 }
                 if(!txtBpName.getText().equals("")){
                     condition=condition+" and dealer like '%"+txtBpName.getText()+"%'";
                 }
                 if(!txtOwner.getText().equals("")){
                     condition=condition+" and contact_person like '%"+txtOwner.getText()+"%'";
                 }
                 if(!txtBpAdd.getText().equals("")){
                     condition=condition+" and alamat like '%"+txtBpAdd.getText()+"%'";
                 }
                 sql=sql+condition+" order by dealer";
              rs=jconn.SQLExecuteRS(sql, conn);

            while(rs.next()){
                tic[0]=rs.getString(2);
                tic[1]=rs.getString(3);
                tic[2]=rs.getString(4);
                tic[3]=rs.getString(5);
                tic[4]=rs.getString(6);
                tic[5]=rs.getString(7);
                tic[6]=rs.getString(8);
                tic[7]=rs.getString(9);
                tic[8]=rs.getString(10);
                tabticconf.addRow(tic);
            }
            lblcaloutcount2.setText(String.valueOf(tabticconf.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }              

    private void tabelact(){
        tabact.setRowCount(0);
             try{
             int row=0;
              sql="select username, _userlevel.data, a.data, info, login_time, host_addr, line_number"
                      + " from user_account x "                      
                      + " join _activity a on x._activity=a.code"
                      + " join _userlevel on x._level=_userlevel.code"
                      + " where x._activity>=1  order by username";
              rs=jconn.SQLExecuteRS(sql, conn);
//              System.out.println(sql);

            while(rs.next()){
                act[0]=rs.getString(1);
                act[1]=rs.getString(2);
                act[2]=rs.getString(3);
                act[3]=rs.getString(4);
                act[4]=rs.getString(5);
                act[5]=rs.getString(6);
                act[6]=rs.getString(7);
                tabact.addRow(act);
            }

        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }    
    
    public static void tabelmsin() {
        try{
            tabmsin.setRowCount(0);
            int row=0;
            Date dt1 =dtmsi.getDate();
            Date dt2 =dtmsi1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            msin = sdf.format(dt1);
            msin1= sdf.format(dt2);
            sql="select * " +
                    "from msg_inbox " +
                    "where msg_date between '"+msin+"' and '"+msin1+"' and _erased=0 and msg_to = '"+lbluser.getText()+"' order by msg_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);

            while(rs.next()){
                msn[0]=rs.getString("msg_date");
                msn[1]=rs.getString("msg_time");
                msn[2]=rs.getString("msg_from");
                msn[3]=rs.getString("_read");
                msn[4]=rs.getString("msg_text");
                msn[5]=rs.getString("msg_id");
                tabmsin.addRow(msn);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static void tabelmsou()        {
        try{
            tabmsou.setRowCount(0);
            int row=0;
            Date dt1 =dtmso.getDate();
            Date dt2 =dtmso1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            msou = sdf.format(dt1);
            msou1= sdf.format(dt2);
            sql="select * " +
                    "from msg_outbox " +
                    "where msg_date between '"+msou+"' and '"+msou1+"' and _erased=0 and msg_from = '"+lbluser.getText()+"' order by msg_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);
            while(rs.next()){
                msu[0]=rs.getString("msg_date");
                msu[1]=rs.getString("msg_time");
                msu[2]=rs.getString("msg_to");
                msu[3]=rs.getString("msg_text");
                msu[4]=rs.getString("msg_id");
                tabmsou.addRow(msu);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    
    Action activ = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            tabelact();
        }
    };
           
    Action dating = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            ambilTgl();
        }
    };
    int pjg=750;
    Action tiscrol = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            pjg--;
            if(pjg<-tp){
                pjg=750;
            }
            lblscroll.setLocation(pjg,0);
//                if(lblscroll.LEFT > -lblscroll.WIDTH){
//                    lblscroll.setLocation(pjg,0);
//                }
        }
    };
    int tp;
    Action inbound = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {                
            try {
                String come;
                String[] tcp = new String[20];
                String conf;
                int p;
                int index;
                if (inbroad.ready()) {
                    come = inbroad.readLine();
//                    System.out.print("\nframe: " + come + "\n");
                    index = come.indexOf(':');
                    p = come.length();
                    if (index >= 0) {
//                        System.out.print("\n ada titik dua\n");
                        tcp[0] = come.substring(index + 1, p);
//                        System.out.print("\n tcp[0] (stlh buang :): " + tcp[0] + "\n");
                        if (tcp[0].length() != 0) {
                            index = tcp[0].indexOf('|');
                            p = tcp[0].length();
                            if (index != -1) {
                                tcp[1] = tcp[0].substring(0, index);
                                System.out.print("\n keyword " + tcp[1] + "\n");
                                tcp[2] = tcp[0].substring(index + 1, p);
                                System.out.print("\n String1 " + tcp[2] + "\n");
                                if (tcp[2].length() != 0) {
                                    index = tcp[2].indexOf('|');
                                    p = tcp[2].length();
                                    if (index != -1) {
                                        tcp[3] = tcp[2].substring(0, index);
                                        System.out.print("\nisi data1 " + tcp[3] + "\n");
                                        tcp[4] = tcp[2].substring(index + 1, p);
                                        System.out.print("\nisi String2 " + tcp[4] + "\n");
                                        if (tcp[4].length() != 0) {
                                            index = tcp[4].indexOf('|');
                                            p = tcp[4].length();
                                            if (index != -1) {
                                                tcp[5] = tcp[4].substring(0, index);
                                                System.out.print("\nisi data2 " + tcp[5] + "\n");
                                                tcp[6] = tcp[4].substring(index + 1, p);
                                                System.out.print("\nisi String3 " + tcp[6] + "\n");
                                                if (tcp[6].length() != 0) {
                                                    index = tcp[6].indexOf('|');
                                                    p = tcp[6].length();
                                                    if (index != -1) {
                                                        tcp[7] = tcp[6].substring(0, index);
                                                        System.out.print("\nisi data3 " + tcp[7] + "\n");
                                                        tcp[8] = tcp[6].substring(index + 1, p);
                                                        System.out.print("\nisi String4 " + tcp[8] + "\n");
                                                        if (tcp[8].length() != 0) {
                                                            index = tcp[8].indexOf('|');
                                                            p = tcp[8].length();
                                                            if (index != -1) {
                                                                tcp[9] = tcp[8].substring(0, index);
                                                                System.out.print("\nisi data4 " + tcp[9] + "\n");
                                                                tcp[10] = tcp[8].substring(index + 1, p);
                                                                System.out.print("\nisi String5 " + tcp[10] + "\n");
                                                            }else{
                                                                tcp[9] = tcp[8].substring(0, p);
                                                                System.out.print("\n data4: " + tcp[9] + "\n");
                                                            }
                                                        }
                                                    }else{
                                                        tcp[7] = tcp[6].substring(0, p);
                                                        System.out.print("\n data3: " + tcp[7] + "\n");
                                                    }
                                                }
                                            }else{
                                                tcp[5] = tcp[4].substring(0, p);
                                                System.out.print("\n data2: " + tcp[5] + "\n");
                                            }
                                        }
                                    }else{
                                        tcp[3] = tcp[2].substring(0, p);
                                        System.out.print("\n data1: " + tcp[3] + "\n");
                                    }
                                }
                            } else {
                                tcp[1] = tcp[0].substring(0, p);
                                System.out.print("\n keyword: " + tcp[1] + "\n");
                            }
                        } else {
                            System.out.print("\n ga da keyword\n");
                        }
                    } else {
                        System.out.print("\n ga da : ny\n");
                    }
                    if (tcp[0].length() != 0 && tcp[3] != null) {
                        if (tcp[1].equals("MSG")&&tcp[3].equals(lbluser.getText())) {
                            tt++;
                            oldtext = lblscroll.getText();
                            if(oldtext.equals("")){
                                lblscroll.setText("1 message received");
                                newtext = lblscroll.getText();
                                tp=newtext.length()*8;
                                lblscroll.setSize(tp, 20);
                                Scrol.start();
                            }else{
                                oldtext=oldtext.replaceAll("1 message received", "");
                                lblscroll.setText(tt+" messages received ,"+oldtext);
                                newtext = lblscroll.getText();
                                tp=newtext.length()*8;
                                lblscroll.setSize(tp, 20);
                            }
                        }
                    }
                    if (tcp[0].length() != 0 && tcp[3] != null) {
                        System.out.print("\n panjang tcp 3 = "+tcp[3].length());
                        if (tcp[1].equals("TICKET")||tcp[3].equals("CONFIRM")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from tickets where _status=2 and confirm=1 and confirm_by=0 and confirm_username is null and confirmed=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    c = Integer.parseInt(rs.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            btnoutbound.setEnabled(true);
                            txtcalnoti.setText(String.valueOf(c));
                            if(c==0){
                                btnoutbound.setEnabled(false);
                                txtcalnoti.setText("");
                            }else{
                                btnoutbound.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + c + " OUTBOUND CALL TO COMFIRM", "OUTBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("MAIL")||tcp[3].equals("INBOUND")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from log_mail where direction=0 and status=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    m = Integer.parseInt(rs.getString(1));
//                                    m++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            tabelticconf();
                            btnmail.setEnabled(true);
                            txtmailnoti.setText(String.valueOf(m));
                            if(m==0){
                                btnmail.setEnabled(false);
                                txtmailnoti.setText("");
                            }else{
                                btnmail.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + m + " INBOUND MAIL TO COMFIRM", "INBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("SMS")||tcp[3].equals("INBOUND")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from log_sms where _direction=0 and _status=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    sm = Integer.parseInt(rs.getString(1));
//                                    sm++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            tabelticconf();
                            txtsmsnoti.setText(String.valueOf(sm));
                            btnsms.setEnabled(true);
                            if(sm==0){
                                btnsms.setEnabled(false);
                                txtsmsnoti.setText("");
                            }else{
                                btnsms.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + sm + " INBOUND SMS ", "INBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("FAX")||tcp[3].equals("INBOUND")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from log_fax where _direction=0 and _status=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    fx = Integer.parseInt(rs.getString(1));
//                                    sm++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            tabelticconf();
                            txtfaxnoti.setText(String.valueOf(fx));
                            btnsms.setEnabled(true);
                            if(fx==0){
                                btnfax.setEnabled(false);
                                txtfaxnoti.setText("");
                            }else{
                                btnfax.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + sm + " INBOUND SMS ", "INBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("SMS")||tcp[3].equals("UPDATE")) {
                            sms();
                        }
                        if (tcp[1].equals("EMAIL")||tcp[3].equals("UPDATE")) {
                            mail();
                        }
                        if (tcp[1].equals("FAX")||tcp[3].equals("UPDATE")) {
                            fax();
                        }
//                        TICKET|ASSIGN|"+deptid+"|"+id+"\r\n            
                        if(tcp[5] != null ){
//                            if (tcp[1].equals("TICKET")&&tcp[3].equals("ASSIGN")&&tcp[5].equals("0")) {
                            if (tcp[1].equals("TICKET")){
                                if(tcp[3].equals("ASSIGN")&&tcp[5].equals("0")){
                                    oldtext = lblscroll.getText();
                                    if(oldtext.equals("")){
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Assigned to your department");
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                        Scrol.start();
                                    }else{
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Assigned to your department, "+oldtext);
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                    }
                                }else if(tcp[3].equals("UPDATE")&&tcp[5].equals("0")){
                                    oldtext = lblscroll.getText();
                                    if(oldtext.equals("")){
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Updated");
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                        Scrol.start();
                                    }else{
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Updated, "+oldtext);
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                    }
                                }
//                                tt++;
//                                JOptionPane.showMessageDialog(null, "YOU'VE GOT " + tt + " TICKET ", "TICKET CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    private void ambilTgl(){
        GregorianCalendar now=new GregorianCalendar();
        int tgl=now.get(now.DATE);
        int bln=now.get(now.MONTH)+1;
        int thn=now.get(now.YEAR);
        int h=now.get(now.HOUR);
        int m=now.get(now.MINUTE);
        int s=now.get(now.SECOND);
    
        lbldate.setText(String.valueOf(thn)+"-"+String.valueOf(bln)+"-"+String.valueOf(tgl)+"  "+String.valueOf(h)+":"+String.valueOf(m)+":"+String.valueOf(s));
    }
     
    public void dataregis(){
          try{
             int row=0;
              sql="select pabx_host, inbound_ext, outbound_ext from user_account where username='"+lbluser.getText()+"'";
              rs=jconn.SQLExecuteRS(sql, conn);
//              System.out.println(sql);

            while(rs.next()){
                pabx=rs.getString(1);
                in_ext=rs.getString(2);
                out_ext=rs.getString(3);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
        
         Action testing = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String ring;
                String come;
                String tcp[]=new String[20];
                String conf;
                int p;
                int index;
            try {
                if (intele.ready()) {
                    ring = intele.readLine();
                    if((ring.substring(0,7).equals("ABANDON") )) {
                        stop();
                        delay();
                        if(elapsed<=5){
                            sql="update log_phone set abandon='"+elapsed+"', _callstatus=-1 where log_id='"+loid+"'";
                            jconn.SQLExecute(sql, conn);
                        }else{
                            sql="update log_phone set abandon='"+elapsed+"', _callstatus=0 where log_id='"+loid+"'";
                            jconn.SQLExecute(sql, conn);
                        }
                        btncall.setEnabled(false);
                        btncall.setDebugGraphicsOptions(v);
                        JOptionPane.showMessageDialog(null, "CALL DISCONNECTED", "INCOMING CALL",JOptionPane.WARNING_MESSAGE);
                    }else if ((ring.substring(0,7).equals("RINGING") )) {
                            start();
                            p=ring.length();
                            callid=ring.substring(8,p);
                            show();
                            toFront();                            
//                            JOptionPane.showMessageDialog(null, callid +"\n"+"CALLING", "INCOMING CALL",JOptionPane.WARNING_MESSAGE);
//                            int i=JOptionPane.showConfirmDialog(null, callid +"\n"+"CALLING", "INCOMING CALL",JOptionPane.YES_NO_OPTION);
                            btncall.setEnabled(true);
//                            btncall.setBackground(Color.RED);
//                            btncall.setIcon(frameIcon);
                            System.out.print("udah nyampe testing"+ ring);
                            try{
                                sqllt="select CURRENT_TIME";
                                rs = jconn.SQLExecuteRS(sqllt, conn);
                                while(rs.next()){
                                    lt=rs.getString(1);
                                }
//                                System.out.println(lt);

                                sql3="update user_account set _activity=2, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
                                jconn.SQLExecute(sql3, conn);
                                btncall.setEnabled(true);
                                
                                  sql="insert into log_phone (log_date,log_time,username,shift,host_addr,line_number,_direction,_callstatus,caller_number) values ('"+ld+"','"+lt+"','"+lbluser.getText()+"','"+Log.cbshift.getSelectedIndex()+"','"+pabx+"','"+in_ext+"',0,0,'"+callid+"')";
                                  jconn.SQLExecute(sql, conn);
//                                  System.out.println(sql);
                                  btncall.setEnabled(true);

                                  sqlid="select distinct last_insert_id() from log_phone";
                                  rs=jconn.SQLExecuteRS(sqlid, conn);
                                  while (rs.next()) {
                                    loid = rs.getString(1);
                                  }
                                  btncall.setEnabled(true);
                            }catch(Exception exc){
                                System.err.println(exc.getMessage());
                            }
                        }else{
                            btncall.setEnabled(false);
                            btncall.setDebugGraphicsOptions(v);
                        }                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
            }
            }        
       };


       public void start() {
        this.startTime = System.currentTimeMillis();
    }

    
    public void stop() {
        this.stopTime = System.currentTimeMillis();
    }

    
    //elaspsed time in milliseconds
    private void currentdate() {
        try {
            sqlld = "select CURRENT_DATE";
            rs = jconn.SQLExecuteRS(sqlld, conn);
            while (rs.next()) {
                ld = rs.getString(1);
            }
            System.out.println(ld);
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //elaspsed time in seconds
    public long delay() {
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);

        //System.out.print(elapsed);
        }
        return elapsed;
    }

    public static void call(){
        try {
            sql="select count(*) from tickets where _status=2 and confirm=1 and confirm_by=0 and confirm_username is null and confirmed=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                c = Integer.parseInt(rs.getString(1));
            }
            txtcalnoti.setText(String.valueOf(c));
            if(c==0){
                btnoutbound.setEnabled(false);
                txtcalnoti.setText("");
            }else{
                btnoutbound.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void sms(){
        try {
            sql="select count(*) from log_sms where _direction=0 and _status=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                sm = Integer.parseInt(rs.getString(1));
            }
            if(sm==0){
                btnsms.setEnabled(false);
                txtsmsnoti.setText("");
            }else{
                btnsms.setEnabled(true);
                txtsmsnoti.setText(String.valueOf(sm));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void mail(){
        try {
            sql="select count(*) from log_mail where direction=0 and status=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                m = Integer.parseInt(rs.getString(1));
            }            
            if(m==0){
                btnmail.setEnabled(false);
                txtmailnoti.setText("");
            }else{
                btnmail.setEnabled(true);
                txtmailnoti.setText(String.valueOf(m));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void fax(){
        try {
            sql="select count(*) from log_fax where _direction=0 and _status=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                fx = Integer.parseInt(rs.getString(1));
            }            
            if(fx==0){
                btnfax.setEnabled(false);
                txtfaxnoti.setText("");
            }else{
                btnfax.setEnabled(true);
                txtfaxnoti.setText(String.valueOf(fx));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterOrangeTv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String optm, opdt;
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
    private void usr(){
        cbagenrelease.removeAllItems();
        cbagenin.removeAllItems();cbagenirepcal.removeAllItems();cbagenirepcal1.removeAllItems();
        cbagenirepfax.removeAllItems();cbagenou.removeAllItems();cbagenrepmail.removeAllItems();
        try{
            sql="select username from user_account" ;
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                cbagenrelease.addItem(rs.getString(1));
            }            
            sql1="select username from user_account where dept_id in (0,2) order by username" ;
            rs1=jconn.SQLExecuteRS(sql1, conn);
            while(rs1.next()){
                cbagenin.addItem(rs1.getString(1));
                cbagenirepcal.addItem(rs1.getString(1));
                cbagenirepcal1.addItem(rs1.getString(1));
                cbagenirepfax.addItem(rs1.getString(1));
                cbagenou.addItem(rs1.getString(1));
                cbagenrepmail.addItem(rs1.getString(1));
            }
            cbagenin.addItem("--");cbagenirepcal.addItem("--");cbagenirepcal1.addItem("--");
            cbagenirepfax.addItem("--");cbagenou.addItem("--");cbagenrepmail.addItem("--");
            cbagenin.setSelectedItem("--");cbagenirepcal.setSelectedItem("--");cbagenirepcal1.setSelectedItem("--");
            cbagenirepfax.setSelectedItem("--");cbagenou.setSelectedItem("--");cbagenrepmail.setSelectedItem("--");
        }catch(Exception e){
            System.out.print(e);
        }
    }
    private void usrlvl(){
        try{
            sql="select _level from user_account where username='"+Log.txtnm.getText()+"'";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                usrlvl=Integer.parseInt(rs.getString(1));
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }
    private void showCust(){
        try{
            cbcust.removeAllItems();cbcust1.removeAllItems();cbcust2.removeAllItems();
            cbcust.addItem("Others");cbcust1.addItem("Others");cbcust2.addItem("Others");

            sql="select distinct(cust_name) from customer_order order by cust_name";
            rs=jconn.SQLExecuteRS(sql,conn);
            while(rs.next()){
                cbcust.addItem(rs.getString(1));
                cbcust1.addItem(rs.getString(1));
                cbcust2.addItem(rs.getString(1));
            }            
            cbcust.setSelectedIndex(-1);cbcust1.setSelectedIndex(-1);cbcust2.setSelectedIndex(-1);
        }catch(Exception e){
            System.out.println(e);

        }
    }

    private void showDept(){
         try{
             cbdept.removeAllItems();
             sql="select dept_name from _department where _deleted=0";
             rs=jconn.SQLExecuteRS(sql,conn);
             while(rs.next()){
                 cbdept.addItem(rs.getString(1));
             }
             cbdept.addItem("--");
             cbdept.setSelectedItem("--");
         }catch(Exception e){
             System.out.println(e);
         }
     }

    private void showcategory()    {
         raction=false;
         try{
             cbcate.removeAllItems();
             sql="select category_name, sla from _ticketcategory where _deleted=0 ";
//             sql=sql+endcondition;
             rs=jconn.SQLExecuteRS(sql,conn);
             while(rs.next()){
                 cbcate.addItem(rs.getString(1));
             }
             endcondition="";
             cbcate.addItem("--");
             cbcate.setSelectedItem("--");
         }catch(Exception e){
             System.out.println(e);
         }
         raction=true;
     }
    private void showDistributor(){
         raction=false;
         try{
             cbDist.removeAllItems();
             sql="select distributor_name from distributors where _deleted=0 ";
             rs=jconn.SQLExecuteRS(sql,conn);
             while(rs.next()){
                 cbDist.addItem(rs.getString(1));
             }
             cbDist.addItem("--");
             cbDist.setSelectedItem("--");
         }catch(Exception e){
             System.out.println(e);
         }
         raction=true;
     }
    
    private void showCaltype(){
        try{
            cbcaltyperepcal.removeAllItems();

            sql="select data from _callertype";
            rs=jconn.SQLExecuteRS(sql,conn);
            while(rs.next()){
                cbcaltyperepcal.addItem(rs.getString(1));
            }
            cbcaltyperepcal.addItem("--");
            cbcaltyperepcal.setSelectedItem("--");
        }catch(Exception e){
            System.out.println(e);

        }
    }

    public static void connecttele()  {
         try {
             // If guest, try to connect to the server
             sockettele = new Socket(IPtele, porttele);
             intele = new BufferedReader(new
                     InputStreamReader(sockettele.getInputStream()));
             outtele = new PrintWriter(sockettele.getOutputStream(), true);
             //               System.out.print(socket);
             teleOn=true;
         }
         // If error, clean up and output an error message
         catch (IOException e) {
             int i=JOptionPane.showConfirmDialog(null,"Telephony did not work\n\nPlease activate your telephony application","Activate Telepohony",JOptionPane.YES_NO_OPTION);
             if (i==JOptionPane.YES_OPTION){
                 connecttele();
             }else{
                 teleOn=false;
                 sql="update user_account set _activity=0 where username= '" +Log.data[0]+ "' limit 1";
                 jconn.SQLExecute(sql, conn);
                 try {
                     Thread.sleep(5000);
                 } catch (InterruptedException ex) {
                     //                     Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 System.exit(0);
             }
         }
     }

    public static int bc=0;
    public static void connect()  {
        try {
            bc++;
            socketbroad = new Socket(IPbroad, portbroad);
            inbroad = new BufferedReader(new
                    InputStreamReader(socketbroad.getInputStream()));
            outbroad = new PrintWriter(socketbroad.getOutputStream(), true);
            msg.start();
            brcaOn=true;
//               System.out.print(socket1);
            }
            // If error, clean up and output an error message
            catch (IOException e) {
                cleanbroad();
                brcaOn=false;
                if(bc==1000||bc==1){
                    JOptionPane.showMessageDialog(null,"Broadcaster didnt work...");
                    connect();
                    if(bc==1000){
                        bc=0;
                    };
                }
                bc++;
            }
    }
    public static void connectuploder()  {
        try {
            socketupload = new Socket("localhost", 6024);
            inupload = new BufferedReader(new
                    InputStreamReader(socketupload.getInputStream()));
            outupload = new PrintWriter(socketupload.getOutputStream(), true);
            //               System.out.print(socket1);
            uploOn=true;
        }
        // If error, clean up and output an error message
        catch (IOException e) {
            int i=JOptionPane.showConfirmDialog(null,"Uploader did not work\n\nPlease activate your uploader application","Activate Uploader",JOptionPane.YES_NO_OPTION);
            if (i==JOptionPane.YES_OPTION){
                connectuploder();
            }else{
                uploOn=false;
                sql="update user_account set _activity=0 where username= '" +Log.data[0]+ "' limit 1";
                jconn.SQLExecute(sql, conn);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                        //                     Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
    }
    public static void connectuploder1()  {
        try {
            socketupload1 = new Socket("localhost", 6007);
            inupload1 = new BufferedReader(new
                    InputStreamReader(socketupload1.getInputStream()));
            outupload1 = new PrintWriter(socketupload1.getOutputStream(), true);
            //               System.out.print(socket1);
            uploOn1=true;
        }
        // If error, clean up and output an error message
        catch (IOException e) {
            int i=JOptionPane.showConfirmDialog(null,"Uploader did not work\n\nPlease activate your uploader application","Activate Uploader",JOptionPane.YES_NO_OPTION);
            if (i==JOptionPane.YES_OPTION){
                connectuploder();
            }else{
                uploOn1=false;
                sql="update user_account set _activity=0 where username= '" +Log.data[0]+ "' limit 1";
                jconn.SQLExecute(sql, conn);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                        //                     Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
    }
    public static void kirimTele(){
        if(teleOn==true){
            sendString(s);
            outtele.print(toSend); outtele.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    public static void kirimBroad(){
        if(brcaOn==true){
            sendString(s);
            outbroad.print(toSend); outbroad.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    public static void kirimUplo(){
        if(uploOn==true){
            sendString(s);
            outupload.print(toSend); outupload.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    public static void kirimUplo1(){
        if(uploOn1==true){
            sendString(s);
            outupload1.print(toSend); outupload1.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    private static void cleanUptele() {
        try {
            if (hostServer != null) {
                hostServer.close();
                hostServer = null;
            }
        }
        catch (IOException e) { hostServer = null; }

        try {
            if (sockettele != null) {
                sockettele.close();
                sockettele = null;
            }
        }
        catch (IOException e) { sockettele = null; }

        try {
            if (intele != null) {
                intele.close();
                intele = null;
            }
        }
        catch (IOException e) { intele = null; }

        if (outtele != null) {
            outtele.close();
            outtele = null;
        }
    }
    private static void cleanbroad() {
        try {
            if (hostServer1 != null) {
                hostServer1.close();
                hostServer1 = null;
            }
        }
        catch (IOException e) { hostServer1 = null; }

        try {
            if (socketbroad != null) {
                socketbroad.close();
                socketbroad = null;
            }
        }
        catch (IOException e) { socketbroad = null; }

        try {
            if (inbroad != null) {
                inbroad.close();
                inbroad = null;
            }
        }
        catch (IOException e) { inbroad = null; }

        if (outbroad != null) {
            outbroad.close();
            outbroad = null;
        }
    }
    private static void cleanUpupload() {

      try {
         if (socketupload != null) {
            socketupload.close();
            socketupload = null;
         }
      }
      catch (IOException e) { socketupload = null; }

      try {
         if (inupload != null) {
            inupload.close();
            inupload = null;
         }
      }
      catch (IOException e) { inupload = null; }

      if (outupload != null) {
         outupload.close();
         outupload = null;
      }
   }
    private static void cleanUpupload1() {

      try {
         if (socketupload1 != null) {
            socketupload1.close();
            socketupload1 = null;
         }
      }
      catch (IOException e) { socketupload1 = null; }

      try {
         if (inupload1 != null) {
            inupload1.close();
            inupload1 = null;
         }
      }
      catch (IOException e) { inupload1 = null; }

      if (outupload1 != null) {
         outupload1.close();
         outupload1 = null;
      }
   }
}

