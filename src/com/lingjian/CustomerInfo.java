package com.lingjian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

/**
 *顾客信息管理
 */
public class CustomerInfo extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JButton jb;
	private JPanel panConpanents,deleteCustomer;
	private JLabel customerId,customerName,customerAddr,customerPhone;
	private JTextField jtCustomerId,jtCustomerName,jtCustomerAddr,jtCustomerPhone;
	@SuppressWarnings("rawtypes")
	public  Vector rowData, columnName; 
	/**
	 * 查询顾客信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void queryCustomer(){
		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		rowData = new Vector();
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();

			String sql="select * from t_customer";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			columnName.add("顾客账号");
			columnName.add("顾客名称");
			columnName.add("顾客联系电话");
			columnName.add("顾客地址");

			while (rs.next()) {
				Vector line1 = new Vector();
				for (int k = 1; k <= data.getColumnCount(); k++) {
					line1.add(rs.getString(data.getColumnName(k)));//这里是添加行数据
				}
				rowData.add(line1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接资源
			if (rs != null) {
				try {
					rs.close();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		jt = new JTable(rowData, columnName);  
		jsp = new JScrollPane(jt);  

		this.add(jsp);  
		this.setTitle("顾客信息查询");  
		this.setSize(600, 400);  //这是大小
		this.setLocation(300, 200);  //这是位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setResizable(true);  //尺寸是否可变
		this.setVisible(true);  //显示与隐藏
		//取消JFrame的关闭默认操作,默认关闭取消都会隐藏当前窗体，取消后自己设置
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer=JOptionPane.showConfirmDialog(CustomerInfo.this, "确认关闭？");
				if(answer==JOptionPane.OK_OPTION){
					CustomerInfo.this.dispose();
				}
			}
		});
	}
	/**
	 * 顾客信息新增
	 */
	public void insertCustomer(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("顾客信息新增");
		addConpants(flag);
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	public void updateCustomer(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("顾客信息更新");
		addConpants(flag);
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	public void addConpants(String flag) {
		this.setLayout(new FlowLayout());//流式布局
		this.add(this.getPanConpanents(flag));
	}

	public JPanel getPanConpanents(String flag) {
		if(panConpanents==null){
			this.panConpanents=new JPanel(new GridLayout(6,2,10,10));
			this.panConpanents.add(this.getCustomerId());
			this.panConpanents.add(this.getTxCustomerId());
			this.panConpanents.add(this.getCustomerName());
			this.panConpanents.add(this.getJtCustomerName());
			this.panConpanents.add(this.getCustomerAddr());
			this.panConpanents.add(this.getJtCustomerAddr());
			this.panConpanents.add(this.getCustomerPhone());
			this.panConpanents.add(this.getJtCustomerPhone());
			this.panConpanents.add(this.getBtnSubmit(flag));
		}

		return panConpanents;
	}
	private JLabel getCustomerId() {
		if(customerId==null){
			this.customerId=new JLabel("顾客账号:");
		}
		return customerId;
	}
	private JTextField getTxCustomerId() {
		if(jtCustomerId==null){
			jtCustomerId=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtCustomerId;
	}
	private JLabel getCustomerName() {
		if(customerName==null){
			this.customerName=new JLabel("顾客名称:");
		}
		return customerName;
	}
	private JTextField getJtCustomerName() {
		if(jtCustomerName==null){
			jtCustomerName=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtCustomerName;
	}
	private JLabel getCustomerAddr() {
		if(customerAddr==null){
			this.customerAddr=new JLabel("顾客地址:");
		}
		return customerAddr;
	}
	private JTextField getJtCustomerAddr() {
		if(jtCustomerAddr==null){
			jtCustomerAddr=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtCustomerAddr;
	}
	private JLabel getCustomerPhone() {
		if(customerPhone==null){
			this.customerPhone=new JLabel("顾客联系电话:");
		}
		return customerPhone;
	}
	private JTextField getJtCustomerPhone() {
		if(jtCustomerPhone==null){
			jtCustomerPhone=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtCustomerPhone;
	}
	private JButton getBtnSubmit(final String flag) {
		if(jb==null){
			jb=new JButton("确定");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doCustomerInfo(flag);
				}

			});
		}
		return jb;
	}
	/**
	 * 顾客信息操作主方法流程
	 * @param flag
	 */
	public void doCustomerInfo(String flag) {
		String jtCustomerIdTxt=jtCustomerId.getText().trim();
		String jtCustomerNameTxt=jtCustomerName.getText().trim();
		String jtCustomerAddrTxt=jtCustomerAddr.getText().trim();
		String jtCustomerPhoneTxt=jtCustomerPhone.getText().trim();
		
		if(null==jtCustomerIdTxt||"".equals(jtCustomerIdTxt)) {
			JOptionPane.showMessageDialog(CustomerInfo.this, "顾客账号不能为空！");
			jtCustomerId.requestFocus();
			return;
		}
		if(null==jtCustomerNameTxt||"".equals(jtCustomerNameTxt)) {
			JOptionPane.showMessageDialog(CustomerInfo.this, "顾客名称不能为空！");
			jtCustomerName.requestFocus();
			return;
		}
		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);
			stat=conn.createStatement();
			String sql01="select * from t_customer where customer_id='"+jtCustomerIdTxt+"'";
			rs=stat.executeQuery(sql01);

			if("insert".equals(flag)){
				if(rs.next()){
					JOptionPane.showMessageDialog(CustomerInfo.this, "该顾客已存在，请重新输入");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}
				String sqlInsert="insert into t_customer (customer_id,customer_name,customer_phone,customer_addr) values ('"+jtCustomerIdTxt+"','"+jtCustomerNameTxt+"','"+jtCustomerPhoneTxt+"','"+jtCustomerAddrTxt+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(CustomerInfo.this, "新增成功");
					CustomerInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(CustomerInfo.this, "新增失败");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}
			}
			if("update".equals(flag)){
				if(!rs.next()){
					JOptionPane.showMessageDialog(CustomerInfo.this, "该顾客不存在，请重新输入");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}
				String sqlUpdate="update t_customer set customer_name='"+jtCustomerNameTxt+"', customer_phone='"+jtCustomerPhoneTxt+"',customer_addr='"+jtCustomerAddrTxt+"'" +
						" where customer_id='"+jtCustomerIdTxt+"'";

				int result=stat.executeUpdate(sqlUpdate);
				if(result==1){
					JOptionPane.showMessageDialog(CustomerInfo.this, "更新成功");
					CustomerInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(CustomerInfo.this, "更新失败");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接资源
			if (rs != null) {
				try {
					rs.close();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 顾客信息删除
	 */
	public void deleteCustomer(){
		this.setSize(500,300);
		this.setTitle("顾客信息删除");
		addConpantDelete();
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	public void addConpantDelete() {
		this.setLayout(new FlowLayout());//流式布局
		this.add(this.getPanConpanents());
		
	}
	public JPanel getPanConpanents() {
		if(deleteCustomer==null){
			this.deleteCustomer=new JPanel(new GridLayout(2,2,10,10));
			this.deleteCustomer.add(this.getCustomerId());
			this.deleteCustomer.add(this.getTxCustomerId());
			this.deleteCustomer.add(this.getBtnDelete());
		}
		return deleteCustomer;
	}
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("删除");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCustomerInfo();
				}
			});
		}
		return jb;
	}
	/**
	 * 删除顾客信息主方法
	 */
	public void deleteCustomerInfo() {
		String jtCustomerIdTxt=jtCustomerId.getText().trim();
		if(null==jtCustomerIdTxt||"".equals(jtCustomerIdTxt)) {
			JOptionPane.showMessageDialog(CustomerInfo.this, "顾客账号不能为空！");
			jtCustomerId.requestFocus();
			return;
		}
		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();
			String sql01="select * from t_customer where customer_id='"+jtCustomerIdTxt+"'";
			rs=stat.executeQuery(sql01);
			if(!rs.next()){
				JOptionPane.showMessageDialog(CustomerInfo.this, "该顾客信息不存在，请重新输入");
				jtCustomerId.setText("");
				return;
			}
			String sql=" delete from t_customer where customer_id='"+jtCustomerIdTxt+"' ";
			int ret=stat.executeUpdate(sql);
			if(ret==1){
				JOptionPane.showMessageDialog(CustomerInfo.this, "删除成功");
				CustomerInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(CustomerInfo.this, "删除失败");
				jtCustomerId.requestFocus();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//关闭数据库连接资源
			if (stat != null) {
				try {
					stat.close();
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
