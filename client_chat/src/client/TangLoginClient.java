package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TangLoginClient extends JFrame {

	private TangClient mContext;

	private JLabel loginFrame;
	private JTextField portNum;
	private JTextField ipNum;
	private JTextField id;
	private JTabbedPane list;
	private JButton goButton;

	public TangLoginClient(TangClient mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		loginFrame = new JLabel(new ImageIcon("img/TangLogin.png"));

		setTitle("Login");
		setSize(400, 520);
		setContentPane(loginFrame);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ipNum = new JTextField();
		ipNum.setBounds(145, 100, 100, 20);

		portNum = new JTextField();
		portNum.setBounds(145, 200, 100, 20);

		id = new JTextField();
		id.setBounds(145, 300, 100, 20);

		goButton = new JButton();
		goButton.setBounds(145, 350, 50, 30);

	}

	public void setInitLayout() {
		loginFrame.add(portNum);
		loginFrame.add(ipNum);
		loginFrame.add(id);
		loginFrame.add(goButton);
		
//		goButton.setEnabled(false);

		setVisible(true);

	}

	public void addEventListener() {

//		goButton.addActionListener(event -> {
//			if(!portNum.getText().equals("") && !ipNum.getText().equals("") && !id.getText().equals("")) {
//				goButton.setEnabled(true);
//			}
//		});

		goButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == goButton) {
					try {
						mContext.setClientName(id.getText());
						mContext.startClient();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 

			}
		});

//		portNum.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(portNum.getText().equals("8000")) {
//					try {
//						mContext.startClient();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
//		});

//		goButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(e.getSource() == goButton && portNum.getText().equals("8000")) {
//					try {
//						System.out.println("실행 흐름");
//						mContext.startClient();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				} else {
//					goButton.setEnabled(false);
//				}
//				
//			}
//		});

	}

}
