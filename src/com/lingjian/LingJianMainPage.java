package com.lingjian;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *系统主界面/菜单界面
 */
public class LingJianMainPage extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel panBg;
	private JMenuBar mbar;
	private JMenu menuTrans, menuSupplier,menuCustomer,menuLingJian;
	private JMenuItem itemSupplierQuery,itemSupplierInsert,itemSupplierUpdate,itemSupplierDelete,
	itemSupplierLingJianQuery,itemSupplierLingJianInsert,itemSupplierLingJianUpdate,itemSupplierLingJianDelete
	,itemCustomerQuery,itemCustomerInsert,itemCustomerUpdate,itemCustomerDelete,
	itemCustomerLingJianQuery,itemCustomerLingJianInsert,itemCustomerLingJianUpdate,itemCustomerLingJianDelete,
	itemLingJianQuery,itemLingJianInsert,itemLingJianUpdate,itemLingJianDelete,
	itemTrans;

	//供应商管理菜单
	private JMenu getMenuSupplier() {
		if(menuSupplier==null){
			this.menuSupplier=new JMenu("供应商管理");
			this.menuSupplier.add(this.getItemSuplierQuery());
			this.menuSupplier.add(this.getItemSuplierInsert());
			this.menuSupplier.add(this.getItemSuplierUpdate());
			this.menuSupplier.add(this.getItemSuplierDelete());
			this.menuSupplier.add(this.getItemSuplierLingJianQuery());
			this.menuSupplier.add(this.getItemSuplierLingJianInsert());
			this.menuSupplier.add(this.getItemSuplierLingJianUpdate());
			this.menuSupplier.add(this.getItemSuplierLingJianDelete());
		}
		return menuSupplier;
	}
	//供应商信息查询菜单
	private JMenuItem getItemSuplierQuery() {
		if(itemSupplierQuery==null){
			this.itemSupplierQuery=new JMenuItem("供应商信息查询");
			this.itemSupplierQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商信息查询界面
					SupplierInfo sup=new SupplierInfo();
					sup.querySupplier();
				}
			});
		}
		return itemSupplierQuery;
	}
	//供应商信息新增菜单
	private JMenuItem getItemSuplierInsert() {
		if(itemSupplierInsert==null){
			this.itemSupplierInsert=new JMenuItem("供应商信息新增");
			this.itemSupplierInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商新增页面
					SupplierInfo sup=new SupplierInfo();
					sup.insertSupplier();
				}
			});
		}
		return itemSupplierInsert;
	}
	//供应商信息修改菜单
	private JMenuItem getItemSuplierUpdate() {
		if(itemSupplierUpdate==null){
			this.itemSupplierUpdate=new JMenuItem("供应商信息修改");
			this.itemSupplierUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商更新界面
					SupplierInfo sup=new SupplierInfo();
					sup.updateSupplier();
				}
			});
		}
		return itemSupplierUpdate;
	}
	//供应商信息删除菜单
	private JMenuItem getItemSuplierDelete() {
		if(itemSupplierDelete==null){
			this.itemSupplierDelete=new JMenuItem("供应商信息删除");
			this.itemSupplierDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商删除界面
					SupplierInfo sup=new SupplierInfo();
					sup.deleteSupplier();
				}
			});
		}
		return itemSupplierDelete;
	}
	//供应商零件信息查询菜单
	private JMenuItem getItemSuplierLingJianQuery() {
		if(itemSupplierLingJianQuery==null){
			this.itemSupplierLingJianQuery=new JMenuItem("供应商零件信息查询");
			this.itemSupplierLingJianQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商零件信息查询界面
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.querySupplierLj();
				}
			});
		}
		return itemSupplierLingJianQuery;
	}
	//供应商零件信息新增菜单
	private JMenuItem getItemSuplierLingJianInsert() {
		if(itemSupplierLingJianInsert==null){
			this.itemSupplierLingJianInsert=new JMenuItem("供应商零件信息新增");
			this.itemSupplierLingJianInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商零件信息新增界面
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.insertSupplierLj();
				}
			});
		}
		return itemSupplierLingJianInsert;
	}
	//供应商零件信息更新菜单
	private JMenuItem getItemSuplierLingJianUpdate() {
		if(itemSupplierLingJianUpdate==null){
			this.itemSupplierLingJianUpdate=new JMenuItem("供应商零件信息更新");
			this.itemSupplierLingJianUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商零件信息更新界面
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.UpdateSupplierLj();
				}
			});
		}
		return itemSupplierLingJianUpdate;
	}
	//供应商零件信息删除菜单
	private JMenuItem getItemSuplierLingJianDelete() {
		if(itemSupplierLingJianDelete==null){
			this.itemSupplierLingJianDelete=new JMenuItem("供应商零件信息删除");
			this.itemSupplierLingJianDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开供应商零件信息删除界面
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.deleteSupplierLj();
				}
			});
		}
		return itemSupplierLingJianDelete;
	}



	private JMenu getMenuCustomer() {
		if(menuCustomer==null){
			this.menuCustomer=new JMenu("顾客信息管理");
			this.menuCustomer.add(this.getCustomerQuery());
			this.menuCustomer.add(this.getCustomerInsert());
			this.menuCustomer.add(this.getCustomerUpdate());
			this.menuCustomer.add(this.getCustomerDelete());
			this.menuCustomer.add(this.getCoutomerLingJianQuery());
			this.menuCustomer.add(this.getCoutomerLingJianInsert());
			this.menuCustomer.add(this.getCoutomerLingJianUpdate());
			this.menuCustomer.add(this.getCoutomerLingJianDelete());
		}
		return menuCustomer;
	}
	//顾客信息查询菜单
	private JMenuItem getCustomerQuery() {
		if(itemCustomerQuery==null){
			this.itemCustomerQuery=new JMenuItem("顾客信息查询");
			this.itemCustomerQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客信息查询界面
					CustomerInfo sl=new CustomerInfo();
					sl.queryCustomer();
				}
			});
		}
		return itemCustomerQuery;
	}
	//顾客信息新增菜单
	private JMenuItem getCustomerInsert() {
		if(itemCustomerInsert==null){
			this.itemCustomerInsert=new JMenuItem("顾客信息新增");
			this.itemCustomerInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客信息新增界面
					CustomerInfo sl=new CustomerInfo();
					sl.insertCustomer();
				}
			});
		}
		return itemCustomerInsert;
	}
	//顾客信息更新菜单
	private JMenuItem getCustomerUpdate() {
		if(itemCustomerUpdate==null){
			this.itemCustomerUpdate=new JMenuItem("顾客信息更新");
			this.itemCustomerUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客信息更新界面
					CustomerInfo sl=new CustomerInfo();
					sl.updateCustomer();
				}
			});
		}
		return itemCustomerUpdate;
	}
	//顾客信息删除菜单
	private JMenuItem getCustomerDelete() {
		if(itemCustomerDelete==null){
			this.itemCustomerDelete=new JMenuItem("顾客信息删除");
			this.itemCustomerDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客信息删除界面
					CustomerInfo sl=new CustomerInfo();
					sl.deleteCustomer();
				}
			});
		}
		return itemCustomerDelete;
	}

	//顾客零件信息查询菜单
	private JMenuItem getCoutomerLingJianQuery() {
		if(itemCustomerLingJianQuery==null){
			this.itemCustomerLingJianQuery=new JMenuItem("顾客零件信息查询");
			this.itemCustomerLingJianQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客零件信息查询界面
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.queryCustomer();
				}
			});
		}
		return itemCustomerLingJianQuery;
	}
	//顾客零件信息新增菜单
	private JMenuItem getCoutomerLingJianInsert() {
		if(itemCustomerLingJianInsert==null){
			this.itemCustomerLingJianInsert=new JMenuItem("顾客零件信息新增");
			this.itemCustomerLingJianInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客零件信息新增界面
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.insertCustomerLj();
				}
			});
		}
		return itemCustomerLingJianInsert;
	}
	//顾客零件信息修改菜单
	private JMenuItem getCoutomerLingJianUpdate() {
		if(itemCustomerLingJianUpdate==null){
			this.itemCustomerLingJianUpdate=new JMenuItem("顾客零件信息修改");
			this.itemCustomerLingJianUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客零件信息修改界面
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.updateCustomer();
				}
			});
		}
		return itemCustomerLingJianUpdate;
	}
	//顾客零件信息删除菜单
	private JMenuItem getCoutomerLingJianDelete() {
		if(itemCustomerLingJianDelete==null){
			this.itemCustomerLingJianDelete=new JMenuItem("顾客零件信息删除");
			this.itemCustomerLingJianDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开顾客零件信息修改界面
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.deleteCustomer();
				}
			});
		}
		return itemCustomerLingJianDelete;
	}

	private JMenu getMenuLingJian() {
		if(menuLingJian==null){
			this.menuLingJian=new JMenu("零件信息管理");
			this.menuLingJian.add(this.getItemLingJianQuery());
			this.menuLingJian.add(this.getItemLingJianInsert());
			this.menuLingJian.add(this.getItemLingJianUpdate());
			this.menuLingJian.add(this.getItemLingJianDelete());
		}
		return menuLingJian;
	}
	//零件信息查询菜单
	private JMenuItem getItemLingJianQuery() {
		if(itemLingJianQuery==null){
			this.itemLingJianQuery=new JMenuItem("零件信息查询");
			this.itemLingJianQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开零件信息查询界面
					LingJianInfo lj=new LingJianInfo();
					lj.queryLingJian();
				}
			});
		}
		return itemLingJianQuery;
	}
	//零件信息新增菜单
	private JMenuItem getItemLingJianInsert() {
		if(itemLingJianInsert==null){
			this.itemLingJianInsert=new JMenuItem("零件信息新增");
			this.itemLingJianInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开零件信息新增界面
					LingJianInfo lj=new LingJianInfo();
					lj.insertLingJian();
				}
			});
		}
		return itemLingJianInsert;
	}
	//零件信息修改菜单
	private JMenuItem getItemLingJianUpdate() {
		if(itemLingJianUpdate==null){
			this.itemLingJianUpdate=new JMenuItem("零件信息修改");
			this.itemLingJianUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开零件信息修改界面
					LingJianInfo lj=new LingJianInfo();
					lj.updateLingJian();
				}
			});
		}
		return itemLingJianUpdate;
	}
	//零件信息删除菜单
	private JMenuItem getItemLingJianDelete() {
		if(itemLingJianDelete==null){
			this.itemLingJianDelete=new JMenuItem("零件信息删除");
			this.itemLingJianDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开零件信息删除界面
					LingJianInfo lj=new LingJianInfo();
					lj.deleteLingJian();
				}
			});
		}
		return itemLingJianDelete;
	}



	private JMenu getMenuTrans() {
		if(menuTrans==null){
			this.menuTrans=new JMenu("交易管理");
			this.menuTrans.add(this.getItemTrans());
		}
		return menuTrans;
	}
	//交易信息维护菜单
	private JMenuItem getItemTrans() {
		if(itemTrans==null){
			this.itemTrans=new JMenuItem("交易信息维护");
			this.itemTrans.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//打开交易信息维护界面
					TransactionInfo trans=new TransactionInfo();
					trans.trans();
				}
			});
		}
		return itemTrans;
	}
	private JMenuBar getMbar() {
		if(mbar==null){
			this.mbar=new JMenuBar();
			this.mbar.add(this.getMenuSupplier());
			this.mbar.add(this.getMenuCustomer());
			this.mbar.add(this.getMenuLingJian());
			this.mbar.add(this.getMenuTrans());

		}
		return mbar;
	}
	public LingJianMainPage(){
		init();
	}
	private void init() {
		this.setSize(600, 400);
		this.setTitle("零件交易中心管理系统");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int sw=screenSize.width;
		int sh=screenSize.height;
		this.setLocation((sw-this.getWidth())/2,(sh-this.getHeight())/2);
		addConpants();
		this.setResizable(false);//设置尺寸不可变
		this.setVisible(true);
		//取消JFrame的关闭默认操作,默认关闭取消都会隐藏当前窗体，取消后自己设置
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer=JOptionPane.showConfirmDialog(LingJianMainPage.this, "确认关闭？");
				if(answer==JOptionPane.OK_OPTION){
					LingJianMainPage.this.dispose();
				}
			}
		});

	}
	private void addConpants() {
		this.setLayout(new GridLayout(1,1));
		this.add(this.getPanBg());
		this.setJMenuBar(this.getMbar());;//①面板中加载菜单栏
	}
	public JPanel getPanBg() {
		if(this.panBg==null){
			this.panBg=new BackgroundPanel();
		}
		return panBg;
	}
}
