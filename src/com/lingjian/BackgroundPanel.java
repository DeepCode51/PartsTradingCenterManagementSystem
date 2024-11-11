package com.lingjian;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		ImageIcon icon=new ImageIcon(BackgroundPanel.class.getResource("/images/background.jpg"));
		//	      参数起始位置0 0 缩放比例大小 400,192
		g.drawImage(icon.getImage(), 0, 0, 600,400,this);
	}

}
