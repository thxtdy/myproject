package Swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch05.MyThreadServer;

public class TangTangHooruHooruHooru extends JFrame{
	
	
	private JLabel tanghooruFrame;
	private JButton startButton;
	private JButton endButton;
	private JPanel chatArea;
	private JTextField text;
	private MyThreadServer myThreadServer;
	
	public TangTangHooruHooruHooru() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	public void initData() {
		tanghooruFrame = new JLabel(new ImageIcon("img/TangHooruFrame1.png"));
		setTitle("탕탕 후루후루후루");
		setSize(800, 1000);
		setContentPane(tanghooruFrame);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startButton = new JButton(new ImageIcon("img/serverStart.png"));
		
		startButton.setSize(150, 80);
		startButton.setLocation(150, 800);
		startButton.setBackground(new Color(0, 191, 99));
		startButton.setBorderPainted(false);
		
		endButton = new JButton(new ImageIcon("img/serverEnded.png"));
		
		endButton.setSize(150, 80);
		endButton.setLocation(500, 800);
		endButton.setBackground(new Color(0, 191, 99));
		endButton.setBorderPainted(false);
		
		chatArea = new JPanel();
		chatArea.setSize(500, 650);
		chatArea.setLocation(150, 100);
		chatArea.setBackground(new Color(128, 128, 255));
		
		text = new JTextField(10);
		
	}
	
	public void setInitLayout() {
		
		
		tanghooruFrame.add(startButton);
		tanghooruFrame.add(endButton);
		
		tanghooruFrame.add(chatArea);
		
		
//		tanghooruFrame.add(text);
		
		
		
		chatArea.add(text);
		
		setVisible(true);
		
		
	}

	
	public void addEventListener() {
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==startButton) {
					System.out.println("33333333");
					myThreadServer = new MyThreadServer();
					myThreadServer.run();
				}
				
			}
		});
	}
	
	//test
	public static void main(String[] args) {
		TangTangHooruHooruHooru tang = new TangTangHooruHooruHooru();
		
		
	}
}
