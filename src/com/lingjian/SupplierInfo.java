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
 *供应商信息维护
 */
public class SupplierInfo extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JPanel panConpanents,deleteSupplier;
	private JLabel supplierId,supplierName,supplierAddr,supplierPhone,supplierIntroduc;
	private JTextField jtSupplierId,jtSupplierName,jtSupplierAddr,jtSupplierPhone,jtSupplierIntroduc;
	private JButton jb;
	@SuppressWarnings("rawtypes")
	public  Vector rowData, columnName; 
	/**
	 * 查询供应商信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void querySupplier(){
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

			String sql="select sup_id, sup_name, sup_addr, sup_phone, sup_introduc  from t_supplier";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			//添加列名
			columnName.add("供应商账号");
			columnName.add("供应商名称");
			columnName.add("供应商地址");
			columnName.add("供应商联系电话");
			columnName.add("供应商简介");

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
		this.setTitle("供应商信息查询");  
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
				int answer=JOptionPane.showConfirmDialog(SupplierInfo.this, "确认关闭？");
				if(answer==JOptionPane.OK_OPTION){
					SupplierInfo.this.dispose();
				}
			}
		});
	}

	/**
	 * 新增供应商信息
	 */
	public void insertSupplier(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("供应商新增");
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
			this.panConpanents.add(this.getSupplierId());
			this.panConpanents.add(this.getTxSupplierId());
			this.panConpanents.add(this.getSupplierName());
			this.panConpanents.add(this.getTxSupplierName());
			this.panConpanents.add(this.getSupplierAddr());
			this.panConpanents.add(this.getTxSupplierAddr());
			this.panConpanents.add(this.getSupplierPhone());
			this.panConpanents.add(this.getTxSupplierPhone());
			this.panConpanents.add(this.getSupplierIntroduc());
			this.panConpanents.add(this.getTxSupplierIntroduc());
			this.panConpanents.add(this.getBtnSubmit(flag));
		}

		return panConpanents;
	}
	//供应商账号文本
	public JLabel getSupplierId(){
		if(supplierId==null){
			this.supplierId=new JLabel("供应商账号:");
		}
		return supplierId;
	}
	//供应商账号输入框
	public JTextField getTxSupplierId() {
		if(jtSupplierId==null){
			jtSupplierId=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtSupplierId;
	}
	//供应商名称文本
	public JLabel getSupplierName(){
		if(supplierName==null){
			this.supplierName=new JLabel("供应商名称:");
		}
		return supplierName;
	}
	//供应商名称输入框
	public JTextField getTxSupplierName() {
		if(jtSupplierName==null){
			jtSupplierName=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtSupplierName;
	}
	//供应商地址文本
	public JLabel getSupplierAddr(){
		if(supplierAddr==null){
			this.supplierAddr=new JLabel("供应商地址:");
		}
		return supplierAddr;
	}
	//供应商地址输入框
	public JTextField getTxSupplierAddr() {
		if(jtSupplierAddr==null){
			jtSupplierAddr=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtSupplierAddr;
	}
	//供应商电话文本
	public JLabel getSupplierPhone(){
		if(supplierPhone==null){
			this.supplierPhone=new JLabel("供应商联系电话:");
		}
		return supplierPhone;
	}
	//供应商电话输入框
	public JTextField getTxSupplierPhone() {
		if(jtSupplierPhone==null){
			jtSupplierPhone=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtSupplierPhone;
	}
	//供应商电话文本
	public JLabel getSupplierIntroduc(){
		if(supplierIntroduc==null){
			this.supplierIntroduc=new JLabel("供应商简介:");
		}
		return supplierIntroduc;
	}
	//供应商电话输入框
	public JTextField getTxSupplierIntroduc() {
		if(jtSupplierIntroduc==null){
			jtSupplierIntroduc=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtSupplierIntroduc;
	}
	//确定按钮
	public JButton getBtnSubmit(final String flag) {
		if(jb==null){
			jb=new JButton("确定");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertSupplierInfo(flag);
				}
			});
		}
		return jb;
	}


	public void insertSupplierInfo(String flag){

		String supplierIdTxt=jtSupplierId.getText().trim();
		String jtSupplierNameTxt=jtSupplierName.getText().trim();
		String jtSupplierAddrTxt=jtSupplierAddr.getText().trim();
		String jtSupplierPhoneTxt=jtSupplierPhone.getText().trim();
		String jtSupplierIntroducTxt=jtSupplierIntroduc.getText().trim();



		if(null==supplierIdTxt||"".equals(supplierIdTxt)) {
			JOptionPane.showMessageDialog(SupplierInfo.this, "供应商账号不能为空！");
			jtSupplierId.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();

			String sqlQuery="select * from t_supplier where sup_id='"+supplierIdTxt+"'";
			rs=stat.executeQuery(sqlQuery);
			if("insert".equals(flag)){//新增供应商信息方法
				if(rs.next()){
					JOptionPane.showMessageDialog(SupplierInfo.this, "该供应商已存在，请重新输入");
					jtSupplierId.requestFocus();
					jtSupplierName.requestFocus();
					jtSupplierAddr.requestFocus();
					jtSupplierPhone.requestFocus();
					jtSupplierIntroduc.requestFocus();
					return;
				}else{
					String sqlInsert=" insert into t_supplier (sup_id,sup_name,sup_addr,sup_phone,sup_introduc) values ("
						+ " '"+supplierIdTxt+"','"+jtSupplierNameTxt+"','"+jtSupplierAddrTxt+"','"+jtSupplierPhoneTxt+"','"+jtSupplierIntroducTxt+"')";

					int ret=stat.executeUpdate(sqlInsert);
					if(ret==1){
						JOptionPane.showMessageDialog(SupplierInfo.this, "新增成功");
						SupplierInfo.this.dispose();
					}

				}
			}else if("update".equals(flag)){//更新供应商信息方法

				if((null==jtSupplierNameTxt||"".equals(jtSupplierNameTxt))&&(null==jtSupplierAddrTxt||"".equals(jtSupplierAddrTxt))
						&&(null==jtSupplierPhoneTxt||"".equals(jtSupplierPhoneTxt))&&(null==jtSupplierIntroducTxt||"".equals(jtSupplierIntroducTxt))){
					JOptionPane.showMessageDialog(SupplierInfo.this, "请输入待修改供应商信息");
					return;
				}


				if(rs.next()){
					StringBuffer sb=new StringBuffer("update t_supplier set");
					if(null!=jtSupplierNameTxt&&!"".equals(jtSupplierNameTxt)){
						sb.append(" sup_name='"+jtSupplierNameTxt+"'");
						if(null!=jtSupplierAddrTxt&&!"".equals(jtSupplierAddrTxt)){
							sb.append(",sup_addr='"+jtSupplierAddrTxt+"'");
						}
						if(null!=jtSupplierPhoneTxt&&!"".equals(jtSupplierPhoneTxt)){
							sb.append(",sup_phone='"+jtSupplierPhoneTxt+"'");
						}
						if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
							sb.append(",sup_introduc='"+jtSupplierIntroducTxt+"'");
						}
					}else{
						if(null!=jtSupplierAddrTxt&&!"".equals(jtSupplierAddrTxt)){
							sb.append(" sup_addr='"+jtSupplierAddrTxt+"'");
							if(null!=jtSupplierPhoneTxt&&!"".equals(jtSupplierPhoneTxt)){
								sb.append(",sup_phone='"+jtSupplierPhoneTxt+"'");
							}
							if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
								sb.append(",sup_introduc='"+jtSupplierIntroducTxt+"'");
							}
						}else{
							if(null!=jtSupplierPhoneTxt&&!"".equals(jtSupplierPhoneTxt)){
								sb.append(" sup_phone='"+jtSupplierPhoneTxt+"'");
								if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
									sb.append(",sup_introduc='"+jtSupplierIntroducTxt+"'");
								}
								
							}else{
								if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
									sb.append(" sup_introduc='"+jtSupplierIntroducTxt+"' ");
								}
							}
						}
					}
					sb.append(" where sup_id='"+supplierIdTxt+"'");
					int ret=stat.executeUpdate(sb.toString());
					if(ret==1){
						JOptionPane.showMessageDialog(SupplierInfo.this, "更新成功");
						SupplierInfo.this.dispose();
					}else{
						JOptionPane.showMessageDialog(SupplierInfo.this, "更新失败");
						return;
					}

				}else{
					JOptionPane.showMessageDialog(SupplierInfo.this, "该供应商不存在，请重新输入");
					jtSupplierId.requestFocus();
					jtSupplierName.requestFocus();
					jtSupplierAddr.requestFocus();
					jtSupplierPhone.requestFocus();
					jtSupplierIntroduc.requestFocus();
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
	 * 修改供应商信息
	 */
	public void updateSupplier(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("供应商更新");
		addConpant(flag);
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	public void addConpant(String flag) {
		this.setLayout(new FlowLayout());//流式布局
		this.add(this.getPanConpanents(flag));

	}
	/**
	 * 删除供应商信息
	 */
	public void deleteSupplier(){
		
		this.setSize(500,300);
		this.setTitle("供应商信息删除");
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
		if(deleteSupplier==null){
			this.deleteSupplier=new JPanel(new GridLayout(2,1,10,10));
			this.deleteSupplier.add(this.getSupplierId());
			this.deleteSupplier.add(this.getTxSupplierId());
			this.deleteSupplier.add(this.getBtnDelete());
		}
		return deleteSupplier;
	}
	//删除按钮
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("删除");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteSupplierInfo();
				}

			});
		}
		return jb;
	}
	/**
	 * 删除供应商信息主方法
	 */
	public void deleteSupplierInfo() {
		
		String supplierIdTxt=jtSupplierId.getText().trim();
		
		if(null==supplierIdTxt||"".equals(supplierIdTxt)) {
			JOptionPane.showMessageDialog(SupplierInfo.this, "供应商账号不能为空！");
			jtSupplierId.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
			//加载驱动
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//获取连接
				conn=DriverManager.getConnection(url);

				stat=conn.createStatement();

				String sql=" delete from t_supplier where sup_id='"+supplierIdTxt+"' ";
				int ret=stat.executeUpdate(sql);
				if(ret==1){
					JOptionPane.showMessageDialog(SupplierInfo.this, "删除成功");
					SupplierInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(SupplierInfo.this, "删除失败");
					jtSupplierId.requestFocus();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
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
