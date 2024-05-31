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

import lombok.Data;
@Data

public class TangLoginClient extends JFrame {

	private TangClient mContext;

	private JLabel loginFrame;
	private JTextField portNum;
	private JTextField ipNum;
	private JTextField inputId;
	private JTabbedPane list;
	private JButton goButton;
	
	
	private JLabel ipImage;
	private JLabel portImage;
	private JLabel nameImage;
	
	public TangLoginClient(TangClient mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		loginFrame = new JLabel(new ImageIcon("img/keroroFrame1.png"));

		setTitle("Login");
		setSize(400, 520);
		setContentPane(loginFrame);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ipNum = new JTextField();
		ipNum.setBounds(145, 100, 100, 20);

		portNum = new JTextField();
		portNum.setBounds(145, 200, 100, 20);

		inputId = new JTextField();
		inputId.setBounds(145, 300, 100, 20);

		goButton = new JButton();
		goButton.setBounds(145, 350, 50, 30);
		
		
		ipImage = new JLabel(new ImageIcon("Img/IPimage.png")); 
		ipImage.setBounds(70, 80, 40, 15);
		ipImage.setVisible(true);
		
		portImage = new JLabel(new ImageIcon("Img/Portimage.png"));
		
		nameImage = new JLabel(new ImageIcon("Img/NameImage.png"));

	}

	public void setInitLayout() {
		loginFrame.add(portNum);
		loginFrame.add(ipNum);
		loginFrame.add(inputId);
		loginFrame.add(goButton);
		loginFrame.add(ipImage);
		
//		goButton.setEnabled(false);

		setVisible(true);

	}

	public void addEventListener() {

		goButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == goButton) {
					try {
						mContext.id = inputId.getText().trim();
						mContext.startClient();
					} catch (Exception e1) {
						System.out.println("문제");
					}
				} 

			}
		});
		

	}
	public static void main(String[] args) {
		new TangLoginClient(null);
	}

}
