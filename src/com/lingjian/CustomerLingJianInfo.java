package com.lingjian;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *顾客零件信息维护 
 */
public class CustomerLingJianInfo extends JFrame{


	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JPanel panConpanents,deleteCustomerLj;
	private JLabel customerId,spareId,number;
	private JTextField jtCustomerId,jtSpareId,jtNumber;
	private JButton jb;
	public  Vector rowData, columnName; 
	/**
	 * 查询顾客零件信息
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

			String sql="select *  from t_cus_spare";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			columnName.add("顾客账号");
			columnName.add("零件号");
			columnName.add("需求量");

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
		this.setTitle("顾客零件信息查询");  
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
				int answer=JOptionPane.showConfirmDialog(CustomerLingJianInfo.this, "确认关闭？");
				if(answer==JOptionPane.OK_OPTION){
					CustomerLingJianInfo.this.dispose();
				}
			}
		});
	}

	/**
	 * 顾客零件信息新增
	 */
	public void insertCustomerLj(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("顾客零件信息新增");
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
			this.panConpanents.add(this.getSpareId());
			this.panConpanents.add(this.getJtSpareId());
			this.panConpanents.add(this.getNumber());
			this.panConpanents.add(this.getJtNumber());
			this.panConpanents.add(this.getBtnSubmit(flag));
		}

		return panConpanents;
	}
	//顾客账号文本
	public JLabel getCustomerId(){
		if(customerId==null){
			this.customerId=new JLabel("顾客账号:");
		}
		return customerId;
	}
	//顾客账号输入框
	public JTextField getTxCustomerId() {
		if(jtCustomerId==null){
			jtCustomerId=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtCustomerId;
	}
	//零件号文本
	public JLabel getSpareId(){
		if(spareId==null){
			this.spareId=new JLabel("零件号:");
		}
		return spareId;
	}
	//零件号输入框
	public JTextField getJtSpareId() {
		if(jtSpareId==null){
			jtSpareId=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtSpareId;
	}
	//需求量文本
	public JLabel getNumber(){
		if(number==null){
			this.number=new JLabel("需求量:");
		}
		return number;
	}
	//需求量输入框
	public JTextField getJtNumber() {
		if(jtNumber==null){
			jtNumber=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtNumber;
	}
	//确定按钮
	public JButton getBtnSubmit(final String flag) {
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
	 * 顾客零件信息操作主方法
	 * @param flag
	 */
	public void doCustomerInfo(String flag) {

		String customerIdTxt=jtCustomerId.getText().trim();
		String spareIdTxt=jtSpareId.getText().trim();
		String numberTxt=jtNumber.getText().trim();

		if(null==customerIdTxt||"".equals(customerIdTxt)) {
			JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "顾客账号不能为空！");
			jtCustomerId.requestFocus();
			return;
		}
		if(null==spareIdTxt||"".equals(spareIdTxt)) {
			JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "零件号不能为空！");
			jtSpareId.requestFocus();
			return;
		}
		if(null==numberTxt||"".equals(numberTxt)) {
			JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "需求量不能为空！");
			jtNumber.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		Statement stat1 = null;
		Statement stat2 = null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
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
			rs2=stat.executeQuery(sql02);
			if(rs2.next()){
				System.out.println(rs2.getString(1));
			}else{
				JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "该零件信息不存在，请重新输入");
				jtNumber.setText("");
				jtSpareId.setText("");
				jtCustomerId.setText("");
				return;
			}
			rs2.last();
			
			
			String sql03="select * from t_customer where customer_id='"+customerIdTxt+"'";
			rs3=stat2.executeQuery(sql03);
			if(rs3.next()){
				System.out.println(rs3.getString(1));
			}else{
				JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "该顾客不存在，请重新输入");
				jtNumber.setText("");
				jtSpareId.setText("");
				jtCustomerId.setText("");
				return;
			}
			rs3.last();
			
			String sql01="select * from t_cus_spare where customer_id='"+customerIdTxt+"' and spare_id='"+spareIdTxt+"'";
			rs1=stat.executeQuery(sql01);
			if("insert".equals(flag)){
				if(rs1.next()){
					JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "该顾客零件信息已存在，请重新输入");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtCustomerId.setText("");
					return;
				}
				rs1.last();
				String sqlInsert="insert into t_cus_spare (customer_id,spare_id,number) values ('"+customerIdTxt+"','"+spareIdTxt+"','"+numberTxt+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "新增成功");
					CustomerLingJianInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "新增失败");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtCustomerId.setText("");
					return;
				}
			}
			if("update".equals(flag)){
				if(rs1.next()){
					System.out.println(rs1.getString(1));
				}else{
					JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "该顾客零件信息不存在，请重新输入");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtCustomerId.setText("");
					return;
				}

				String sqlUpdate="update t_cus_spare set number='"+numberTxt+"' where customer_id='"+customerIdTxt+"' and spare_id='"+spareIdTxt+"'";

				int result=stat.executeUpdate(sqlUpdate);
				if(result==1){
					JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "更新成功");
					CustomerLingJianInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "更新失败");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtCustomerId.setText("");
					return;
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接资源
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
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
					stat1.close();
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
	 * 顾客零件信息修改 
	 */
	public void updateCustomer(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("顾客零件信息修改");
		addConpants(flag);
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	/**
	 * 顾客零件信息删除
	 */
	public void deleteCustomer(){
		this.setSize(500,300);
		this.setTitle("顾客零件信息删除");
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
		if(deleteCustomerLj==null){
			this.deleteCustomerLj=new JPanel(new GridLayout(3,2,10,10));
			this.deleteCustomerLj.add(this.getCustomerId());
			this.deleteCustomerLj.add(this.getTxCustomerId());
			this.deleteCustomerLj.add(this.getSpareId());
			this.deleteCustomerLj.add(this.getJtSpareId());
			this.deleteCustomerLj.add(this.getBtnDelete());
		}
		return deleteCustomerLj;
	}
	//删除按钮
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("删除");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCustomerInfoLj();
				}

			});
		}
		return jb;
	}
	/**
	 * 删除顾客零件信息主方法
	 */
	public void deleteCustomerInfoLj() {
		String customerIdTxt=jtCustomerId.getText().trim();
		String spareIdTxt=jtSpareId.getText().trim();
		if(null==customerIdTxt||"".equals(customerIdTxt)) {
			JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "顾客账号不能为空！");
			jtCustomerId.requestFocus();
			return;
		}
		if(null==spareIdTxt||"".equals(spareIdTxt)) {
			JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "零件号不能为空！");
			jtSpareId.requestFocus();
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
			String sql01="select * from t_cus_spare where customer_id='"+customerIdTxt+"' and spare_id='"+spareIdTxt+"'";
			String sql=" delete from t_cus_spare where customer_id='"+customerIdTxt+"' and spare_id='"+spareIdTxt+"' ";
			rs=stat.executeQuery(sql01);
			if(!rs.next()){
				JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "该顾客零件信息不存在，请重新输入");
				jtSpareId.setText("");
				jtCustomerId.setText("");
				return;
			}
			int ret=stat.executeUpdate(sql);
			if(ret==1){
				JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "删除成功");
				CustomerLingJianInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(CustomerLingJianInfo.this, "删除失败");
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
