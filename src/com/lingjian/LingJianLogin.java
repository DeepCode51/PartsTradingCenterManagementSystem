package com.lingjian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
/**
 * 登录界面
 */
public class LingJianLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel title,lblAccountNO,lblPassword;
	private JPanel panBg,panConpanents;
	private JTextField txtAccontNo;
	private JPasswordField txtPassword;
	private JButton btnLogin,btnRegister;

	public LingJianLogin(){
		init();
	}
	public static void main(String[] args) {
		new LingJianLogin();
	}

	//登陆、注册方法入口
	private void init(){
		this.setSize(600,400);
		this.setTitle("零件交易中心管理系统");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int sw=screenSize.width;
		int sh=screenSize.height;
		this.setLocation((sw-this.getWidth())/2,(sh-this.getHeight())/2);
		addConpants();
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
	}
	//添加内容
	private void addConpants() {
		this.setLayout(new GridLayout(2,1));
		this.add(this.getPanBg());
		this.add(this.getPanConpanents());

	}
	//背景图片和标题
	public JPanel getPanBg() {
		if(this.panBg==null){
			this.panBg=new BackgroundPanel();
			this.panBg.add(this.getTitles());
		}
		return panBg;
	}
	public JPanel getPanConpanents() {
		if(this.panConpanents==null){
			this.panConpanents=new JPanel();

			this.panConpanents.add(this.getLblAccountNO());
			this.panConpanents.add(this.getTxtAccontNo());
			this.panConpanents.add(this.getLblPassword());
			this.panConpanents.add(this.getTxtPassword());
			this.panConpanents.add(this.getBtnLogin());
			this.panConpanents.add(this.getBtnRegister());
		}
		return panConpanents;
	}


	public JLabel getTitles(){
		if(title==null){
			title=new JLabel("零件交易中心管理系统",JLabel.CENTER);
			title.setFont(new java.awt.Font("微软雅黑",1,30));
		}
		return title;
	}
	//交易员账号
	public JLabel getLblAccountNO() {
		if(lblAccountNO==null){
			lblAccountNO=new JLabel("交易员账号： ");
		}
		return lblAccountNO;
	}
	//交易员账号输入框
	public JTextField getTxtAccontNo() {
		if(txtAccontNo==null){
			txtAccontNo=new JTextField(20);//构造一个具有指定列数的新的空 TextField
		}
		return txtAccontNo;
	}
	//密码
	public JLabel getLblPassword() {
		if(lblPassword==null){
			lblPassword=new JLabel("密码：");
		}
		return lblPassword;
	}
	//密码输入框
	public JPasswordField getTxtPassword() {
		if(txtPassword==null){
			txtPassword=new JPasswordField(20);
			txtPassword.setEchoChar('*');
			txtPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						loginvalite();
					}
				}
			});
		}
		return txtPassword;
	}
	//注册按钮
	public JButton getBtnRegister() {
		if(btnRegister==null){
			btnRegister=new JButton("注册");
			btnRegister.setFont(new Font("楷书",Font.BOLD,14));
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//关闭当前页面
					LingJianLogin.this.dispose();
					//注册方法，打开注册页面
					register();
				}
			});
		}
		return btnRegister;
	}
	public void register() {
		Register rs=new Register();
		rs.register();
	}
	//登陆按钮
	public JButton getBtnLogin() {
		if(btnLogin==null){
			btnLogin=new JButton("登录");
			btnLogin.setFont(new Font("楷书",Font.BOLD,14));
			btnLogin.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					loginvalite();
				}
			});
		}
		return btnLogin;
	}

	//登陆验证主流程
	private void loginvalite() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		//mysql数据库连接url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 从文本框读取内容
		String accountNo=txtAccontNo.getText().trim();
		if(accountNo==null||accountNo.equals("")){
			JOptionPane.showMessageDialog(LingJianLogin.this, "账号不能为空！");
			txtAccontNo.requestFocus();
			return;
		}
		//提取密码
		String password=String.valueOf(txtPassword.getPassword());
		if(password==null||password.equals("")){
			JOptionPane.showMessageDialog(LingJianLogin.this, "密码不能为空");
			txtPassword.requestFocus();
			return;
		}
		try {
			//查询交易员表，验证登陆信息
			stat=conn.createStatement();		
			String sql="select * from t_trader where trader_id='"+accountNo+"' and trader_password='"+password+"'";
			rs=stat.executeQuery(sql);
			if(rs.next()){
				//关闭当前页面
				LingJianLogin.this.dispose();
				//打开系统主界面
				new LingJianMainPage();

			}else{
				JOptionPane.showMessageDialog(LingJianLogin.this, "账号或密码错误");
				txtPassword.setText("");
				txtPassword.requestFocus();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(LingJianLogin.this, "访问数据库失败！");
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
