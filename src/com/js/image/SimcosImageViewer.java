package com.js.image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimcosImageViewer {

	public SimcosImageViewer() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String imagePath){
		try {
			BufferedImage img =ImageIO.read(new File(imagePath));
			ImageIcon icon = new ImageIcon(img);
			final JFrame frame = new JFrame("Simcos Image");
			frame.setSize(600,500);
			JLabel lbl = new JLabel();
			lbl.setIcon(icon);
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout());
			panel.add(lbl,BorderLayout.CENTER);
			frame.add(panel);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					frame.dispose();
				}
			});
			frame.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		SimcosImageViewer obj = new SimcosImageViewer();
		obj.running("D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\Simcos_Workspace\\170830\\SimcosData\\1350017788_CarGrey.png");
	}
}
