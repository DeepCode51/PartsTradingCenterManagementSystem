package com.lingjian;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 交易管理
 */
public class TransactionInfo extends JFrame{


	private static final long serialVersionUID = 1L;
	private JPanel panConpanents;
	private JLabel customerNo,spareId;
	private JTextField jtCustomerNo,jtSpareId;
	private JButton jb;
	
	
	public void trans(){
		this.setSize(500,300);
		this.setTitle("交易信息维护");
		addConpants();
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	public void addConpants() {
		this.setLayout(new FlowLayout());//流式布局
		this.add(this.getPanConpanents());
	}
	private JPanel getPanConpanents() {
		if(this.panConpanents==null){
			this.panConpanents=new JPanel(new GridLayout(3,2,10,10));
			this.panConpanents.add(this.getCustomerNo());
			this.panConpanents.add(this.getTxcustomerNo());
			this.panConpanents.add(this.getSpareId());
			this.panConpanents.add(this.getJtSpareId());
			this.panConpanents.add(this.getBtnSubmit());
		}
		return panConpanents;
	}

	public JLabel getCustomerNo(){
		if(customerNo==null){
			customerNo=new JLabel("顾客号：");
		}
		return customerNo;
	}
	//对应顾客号号输入框
	public JTextField getTxcustomerNo(){
		if(jtCustomerNo==null){
			jtCustomerNo=new JTextField(16);//构造一个具有指定列数的新的空 TextField
		}
		return jtCustomerNo;
	}
	public JLabel getSpareId(){
		if(spareId==null){
			spareId=new JLabel("零件号：");
		}
		return spareId;
	}
	//对应交易员账号输入框
	public JTextField getJtSpareId(){
		if(jtSpareId==null){
			jtSpareId=new JTextField(16);//构造一个具有指定列数的新的空 TextField
		}
		return jtSpareId;
	}
	public JButton getBtnSubmit(){
		if(jb==null){
			jb=new JButton("确定");
			jb.setFont(new Font("楷书", Font.BOLD, 12));
			jb.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					doTrans();
				}
			});
		}
		return jb;
	}

	public void doTrans(){
		String customerIdTxt=jtCustomerNo.getText().trim();
		String spareIdTxt=jtSpareId.getText().trim();
		if(null==customerIdTxt||"".equals(customerIdTxt)) {
			JOptionPane.showMessageDialog(TransactionInfo.this, "顾客账号不能为空！");
			jtCustomerNo.requestFocus();
			return;
		}
		if(null==spareIdTxt||"".equals(spareIdTxt)) {
			JOptionPane.showMessageDialog(TransactionInfo.this, "零件号不能为空！");
			jtSpareId.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		Statement stat1 = null;
		Statement stat2 = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);
			stat=conn.createStatement();
			stat1=conn.createStatement();
			stat2=conn.createStatement();
			String sql02="select * from t_spare where spare_id='"+spareIdTxt+"'";
			rs=stat.executeQuery(sql02);
			if(rs.next()){
				System.out.println(rs.getString(1));
			}else{
				JOptionPane.showMessageDialog(TransactionInfo.this, "该零件信息不存在，请重新输入");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}
			rs.last();
			String sql01="select * from t_customer where customer_id='"+customerIdTxt+"'";
			rs1=stat1.executeQuery(sql01);
			if(rs1.next()){
				System.out.println(rs1.getString(1));
			}else{
				JOptionPane.showMessageDialog(TransactionInfo.this, "该顾客信息不存在，请重新输入");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}
			rs1.last();
			
			String sql03="select * from t_cus_spare where customer_id='"+customerIdTxt+"' and spare_id='"+spareIdTxt+"'";
			rs2=stat2.executeQuery(sql03);
			if(rs2.next()){
				JOptionPane.showMessageDialog(TransactionInfo.this, "该交易已存在，请重新输入");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}
			rs2.last();
			
			String sqlInsert="insert into t_cus_spare (customer_id,spare_id,number) values ('"+customerIdTxt+"','"+spareIdTxt+"',0)";
			int ret=stat.executeUpdate(sqlInsert);
			if(ret==1){
				JOptionPane.showMessageDialog(TransactionInfo.this, "新增成功");
				TransactionInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(TransactionInfo.this, "新增失败");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接资源
			if (rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs1!= null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs2!= null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat1 != null) {
				try {
					stat1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat2 != null) {
				try {
					stat2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
