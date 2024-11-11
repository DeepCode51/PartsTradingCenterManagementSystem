package com.lingjian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * 注册界面
 */
public class Register extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel acctNo,name,passWord;
	private JPanel panConpanents;
	private JTextField jtAcctNo,jtName;
	private JPasswordField jxPassWord;
	private JButton register,login;

	public Register(){
		register();
	}
	//注册方法
	public void register(){
		this.setSize(500,300);
		this.setTitle("注册页面");
		addConpants();
		this.setLocationRelativeTo(null);//居中
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}

	//添加内容
	public void addConpants() {
		this.setLayout(new FlowLayout());//流式布局
		this.add(this.getPanConpanents());
	}
	private JPanel getPanConpanents() {
		if(this.panConpanents==null){
			this.panConpanents=new JPanel(new GridLayout(4,2,10,10));
			//第一行
			this.panConpanents.add(this.getAcctNo());
			this.panConpanents.add(this.getTxAcctNo());
			//第二行
			this.panConpanents.add(this.getNames());
			this.panConpanents.add(this.getJtName());
			//第三行
			this.panConpanents.add(this.getPassWord());
			this.panConpanents.add(this.getJxPassWord());
			//第四行
			this.panConpanents.add(this.getBtnRegister());
			this.panConpanents.add(this.getBtnLogin());
		}
		return panConpanents;
	}
	//对应交易员账号
	public JLabel getAcctNo(){
		if(acctNo==null){
			acctNo=new JLabel("交易员账号：",JLabel.CENTER);
		}
		acctNo.setHorizontalAlignment(SwingConstants.CENTER);
		return acctNo;
	}
	//对应交易员账号输入框
	public JTextField getTxAcctNo(){
		if(jtAcctNo==null){
			jtAcctNo=new JTextField(16);//构造一个具有指定列数的新的空 TextField
		}
		return jtAcctNo;
	}
	//对应交易员名称
	public JLabel getNames(){
		if(name==null){
			name=new JLabel("交易员名称：",JLabel.CENTER);
		}
		name.setHorizontalAlignment(SwingConstants.CENTER);
		return name;
	}
	//对应交易员名称输入框
	public JTextField getJtName(){
		if(jtName==null){
			jtName=new JTextField(16);//构造一个具有指定列数的新的空 TextField
		}
		return jtName;
	}
	//对应交易员密码
	public JLabel getPassWord(){
		if(passWord==null){
			passWord=new JLabel("交易员密码：",JLabel.CENTER);
		}
		passWord.setHorizontalAlignment(SwingConstants.CENTER);
		return passWord;
	}
	//对应交易员密码输入框
	public JPasswordField getJxPassWord(){
		if(jxPassWord==null){
			jxPassWord=new JPasswordField(16);//构造一个具有指定列数的新的空 JPasswordField
		}
		return jxPassWord;
	}
	public JButton getBtnRegister(){
		if(register==null){
			register=new JButton("注册");
			register.setFont(new Font("楷书", Font.BOLD, 12));
			register.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					doRegister();
				}
			});
		}
		return register;
	}
	public JButton getBtnLogin(){
		if(login==null){
			login=new JButton("登录");
			login.setFont(new Font("楷书", Font.BOLD, 12));
			login.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//关闭当前页面
					Register.this.dispose();
					//打开登录页面
					new LingJianLogin();
				}
			});
		}
		return login;
	}
	//注册主方法
	public void doRegister() {
		//获取交易员账号输入框内容
		String accountNo=jtAcctNo.getText().trim();
		if(null==accountNo||"".equals(accountNo)){
			JOptionPane.showMessageDialog(Register.this, "交易员账号不能为空！");
			jtAcctNo.requestFocus();
			return;
		}
		//获取交易员账号输入框内容
		String jyName=jtName.getText().trim();
		if(null==jyName||"".equals(jyName)){
			JOptionPane.showMessageDialog(Register.this, "交易员名称不能为空！");
			jtName.requestFocus();
			return;
		}
		//提取密码
		String password=String.valueOf(jxPassWord.getPassword());
		if(password==null||password.equals("")){
			JOptionPane.showMessageDialog(Register.this, "交易员密码不能为空");
			jxPassWord.requestFocus();
			return;
		}

		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Connection conn = null;
		Statement stat=null;
		ResultSet rs = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();		
			String sqlQuery="select * from t_trader where trader_id='"+accountNo+"' and trader_name='"+jyName+"'";
			//查询数据库中是否已存在此交易员，存在抛错，不存在可继续注册
			rs=stat.executeQuery(sqlQuery);
			if(rs.next()){
				JOptionPane.showMessageDialog(Register.this, "该交易员已存在，请重新注册");
				jtAcctNo.setText("");
				jtAcctNo.requestFocus();
				jtName.setText("");
				jtName.requestFocus();
				jxPassWord.setText("");
				jxPassWord.requestFocus();
				return;
			}else{
				String sqlInsert="insert into t_Trader (trader_id,trader_name,trader_password) values ('"+accountNo+"','"+jyName+"','"+password+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(Register.this, "注册成功，请登录");
				}else{
					JOptionPane.showMessageDialog(Register.this, "注册失败，请重新注册");
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

}
