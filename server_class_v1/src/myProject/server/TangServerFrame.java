package myProject.server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Data;

import lombok.Data;

@Data

public class TangServerFrame extends JFrame {
	
	private TangServer mContext;
	
	private TangServerFrame frame;
	private TangServer server;
	private ServerSocket serverSocket;

	private JLabel tanghooruFrame;
	private JButton startButton;
	private JButton endButton;
	private JTextField text; // PORT NUM
	private JScrollPane userList;
	private JTextField listText;
	private JTextArea serverList;
	
	
	public TangServerFrame(TangServer mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		tanghooruFrame = new JLabel(new ImageIcon("img/TangHooruFrame1.png"));
		setTitle("탕탕 후루후루후루");
		setSize(800, 1000);
//		setContentPane(tanghooruFrame);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startButton = new JButton(new ImageIcon("img/serverStart.png"));

		startButton.setSize(150, 80);
		startButton.setLocation(150, 800);
		startButton.setBackground(new Color(0, 191, 99));
		startButton.setBorderPainted(false);
		startButton.setVisible(true);

		endButton = new JButton(new ImageIcon("img/serverEnded.png"));
		endButton.setSize(150, 80);
		endButton.setLocation(500, 800);
		endButton.setBackground(new Color(0, 191, 99));
		endButton.setBorderPainted(false);

		text = new JTextField(10);
		text.setSize(150, 30);
		text.setLocation(320, 700);

		serverList = new JTextArea();
		serverList.setBounds(200, 100, 400, 550);
		serverList.setEnabled(false);


	}

	public void setInitLayout() {

		tanghooruFrame.add(startButton);
		tanghooruFrame.add(endButton);
		tanghooruFrame.add(text);
		tanghooruFrame.add(serverList);

		add(tanghooruFrame);

		setVisible(true);

	}


	public void addEventListener() {
		

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == startButton && !text.getText().equals("")) {
					String port = text.getText();
					serverList.append(port  +" 로 연결하였습니다." + "\n");
					mContext.startServer();
				}
			}
//				if (e.getSource() == startButton && !text.getText().equals("")) {
//						serverList.setText(text.getText() + ": " + "PORT CONNECTED\n");
//						
//					
//				
//					
//				}
//			}
		});
		endButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == endButton) {
					System.out.println("44444444");
					

				}

			}
		});

	}


}
