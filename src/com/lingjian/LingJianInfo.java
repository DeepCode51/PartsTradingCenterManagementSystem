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
 *零件信息管理
 */
public class LingJianInfo extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JButton jb;
	private JPanel panConpanents,deleteLj;
	private JLabel ljId,ljName,ljWeight,ljColor,ljIntroduc;
	private JTextField jtLjId,jtLjNameName,jtLjWeight,jtLjColor,jtLjIntroduc;
	@SuppressWarnings("rawtypes")
	public  Vector rowData, columnName; 
	/**
	 * 查询零件信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void queryLingJian(){
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

			String sql="select * from t_spare";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			columnName.add("零件号");
			columnName.add("零件名称");
			columnName.add("零件重量");
			columnName.add("零件颜色");
			columnName.add("零件介绍");

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
		this.setTitle("零件信息查询");  
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
				int answer=JOptionPane.showConfirmDialog(LingJianInfo.this, "确认关闭？");
				if(answer==JOptionPane.OK_OPTION){
					LingJianInfo.this.dispose();
				}
			}
		});
	}
	/**
	 * 零件信息新增
	 */
	public void insertLingJian(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("零件信息新增");
		addConpants(flag);
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	public void updateLingJian(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("零件信息更新");
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
			this.panConpanents.add(this.getLjId());
			this.panConpanents.add(this.getTxLjId());
			this.panConpanents.add(this.getLjName());
			this.panConpanents.add(this.getJtLjName());
			this.panConpanents.add(this.getLjWeight());
			this.panConpanents.add(this.getJtLjWeight());
			this.panConpanents.add(this.getLjColor());
			this.panConpanents.add(this.getJtLjColor());
			this.panConpanents.add(this.getLjIntroduc());
			this.panConpanents.add(this.getJtLjIntroduc());
			this.panConpanents.add(this.getBtnSubmit(flag));
		}

		return panConpanents;
	}
	private JLabel getLjId() {
		if(ljId==null){
			this.ljId=new JLabel("零件号:");
		}
		return ljId;
	}
	private JTextField getTxLjId() {
		if(jtLjId==null){
			jtLjId=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtLjId;
	}
	private JLabel getLjName() {
		if(ljName==null){
			this.ljName=new JLabel("零件名称:");
		}
		return ljName;
	}
	private JTextField getJtLjName() {
		if(jtLjNameName==null){
			jtLjNameName=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtLjNameName;
	}
	private JLabel getLjWeight() {
		if(ljWeight==null){
			this.ljWeight=new JLabel("零件 重量:");
		}
		return ljWeight;
	}
	private JTextField getJtLjWeight() {
		if(jtLjWeight==null){
			jtLjWeight=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtLjWeight;
	}
	private JLabel getLjColor() {
		if(ljColor==null){
			this.ljColor=new JLabel("零件颜色:");
		}
		return ljColor;
	}
	private JTextField getJtLjColor() {
		if(jtLjColor==null){
			jtLjColor=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtLjColor;
	}
	
	
	
	private JLabel getLjIntroduc() {
		if(ljIntroduc==null){
			this.ljIntroduc=new JLabel("零件介绍:");
		}
		return ljIntroduc;
	}
	private JTextField getJtLjIntroduc() {
		if(jtLjIntroduc==null){
			jtLjIntroduc=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return jtLjIntroduc;
	}
	
	private JButton getBtnSubmit(final String flag) {
		if(jb==null){
			jb=new JButton("确定");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doLingJianInfo(flag);
				}

			});
		}
		return jb;
	}
	/**
	 * 零件信息操作主方法流程
	 * @param flag
	 */
	public void doLingJianInfo(String flag) {
		String jtLingJianIdTxt=jtLjId.getText().trim();
		String jtLingJianNameTxt=jtLjNameName.getText().trim();
		String jtLingJianWeightTxt=jtLjWeight.getText().trim();
		String jtLingJianColorTxt=jtLjColor.getText().trim();
		String jtLingJianIntroducTxt=jtLjIntroduc.getText().trim();
		
		if(null==jtLingJianIdTxt||"".equals(jtLingJianIdTxt)) {
			JOptionPane.showMessageDialog(LingJianInfo.this, "零件账号不能为空！");
			jtLjId.requestFocus();
			return;
		}
		if(null==jtLingJianNameTxt||"".equals(jtLingJianNameTxt)) {
			JOptionPane.showMessageDialog(LingJianInfo.this, "零件名称不能为空！");
			jtLjNameName.requestFocus();
			return;
		}
		if(null==jtLingJianWeightTxt||"".equals(jtLingJianWeightTxt)) {
			JOptionPane.showMessageDialog(LingJianInfo.this, "零件重量不能为空！");
			jtLjWeight.requestFocus();
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
			String sql01="select * from t_spare where spare_id='"+jtLingJianIdTxt+"'";
			rs=stat.executeQuery(sql01);

			if("insert".equals(flag)){
				if(rs.next()){
					JOptionPane.showMessageDialog(LingJianInfo.this, "该零件已存在，请重新输入");
					jtLjId.setText("");
					jtLjNameName.setText("");
					jtLjWeight.setText("");
					jtLjColor.setText("");
					return;
				}
				String sqlInsert="insert into t_spare (spare_id,spare_name,spare_weight,spare_color,spare_introduc) values ('"+jtLingJianIdTxt+"','"+jtLingJianNameTxt+"','"+jtLingJianWeightTxt+"','"+jtLingJianColorTxt+"','"+jtLingJianIntroducTxt+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(LingJianInfo.this, "新增成功");
					LingJianInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(LingJianInfo.this, "新增失败");
					jtLjId.setText("");
					jtLjNameName.setText("");
					jtLjWeight.setText("");
					jtLjColor.setText("");
					return;
				}
			}
			if("update".equals(flag)){
				if(!rs.next()){
					JOptionPane.showMessageDialog(LingJianInfo.this, "该零件不存在，请重新输入");
					jtLjId.setText("");
					jtLjNameName.setText("");
					jtLjWeight.setText("");
					jtLjColor.setText("");
					return;
				}
				String sqlUpdate="update t_spare set spare_name='"+jtLingJianNameTxt+"', spare_color='"+jtLingJianColorTxt+"',spare_weight='"+jtLingJianWeightTxt+"',spare_introduc='"+jtLingJianIntroducTxt+"' " +
						" where spare_id='"+jtLingJianIdTxt+"'";

				int result=stat.executeUpdate(sqlUpdate);
				if(result==1){
					JOptionPane.showMessageDialog(LingJianInfo.this, "更新成功");
					LingJianInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(LingJianInfo.this, "更新失败");
					jtLjId.setText("");
					jtLjNameName.setText("");
					jtLjWeight.setText("");
					jtLjColor.setText("");
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
	 * 零件信息删除
	 */
	public void deleteLingJian(){
		this.setSize(500,300);
		this.setTitle("零件信息删除");
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
		if(deleteLj==null){
			this.deleteLj=new JPanel(new GridLayout(2,2,10,10));
			this.deleteLj.add(this.getLjId());
			this.deleteLj.add(this.getTxLjId());
			this.deleteLj.add(this.getBtnDelete());
		}
		return deleteLj;
	}
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("删除");
			jb.setFont(new Font("楷书",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteLingJianInfo();
				}
			});
		}
		return jb;
	}
	/**
	 * 删除零件信息主方法
	 */
	public void deleteLingJianInfo() {
		String jtLingJianIdTxt=jtLjId.getText().trim();
		if(null==jtLingJianIdTxt||"".equals(jtLingJianIdTxt)) {
			JOptionPane.showMessageDialog(LingJianInfo.this, "零件账号不能为空！");
			jtLjId.requestFocus();
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
			String sql01="select * from t_spare where spare_id='"+jtLingJianIdTxt+"'";
			rs=stat.executeQuery(sql01);
			if(!rs.next()){
				JOptionPane.showMessageDialog(LingJianInfo.this, "该零件信息不存在，请重新输入");
				jtLjId.setText("");
				return;
			}
			String sql=" delete from t_spare where spare_id='"+jtLingJianIdTxt+"' ";
			int ret=stat.executeUpdate(sql);
			if(ret==1){
				JOptionPane.showMessageDialog(LingJianInfo.this, "删除成功");
				LingJianInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(LingJianInfo.this, "删除失败");
				jtLjId.requestFocus();
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
