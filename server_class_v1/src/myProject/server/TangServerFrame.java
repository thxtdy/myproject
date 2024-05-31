package myProject.server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ServerSocket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Data;
@Data

public class TangServerFrame extends JFrame implements ActionListener {

	private TangServer mContext;

	private TangServerFrame frame;
	private ServerSocket serverSocket;

	private JLabel tanghooruFrame;
	private JButton startButton;
	private JButton endButton;
	private JTextField portText; // PORT NUM
	private JScrollPane userList;
	public JTextField listText;
	public JTextArea serverList;

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

		portText = new JTextField(10);
		portText.setSize(150, 30);
		portText.setLocation(320, 700);

		serverList = new JTextArea();
		serverList.setBounds(200, 100, 400, 550);
		serverList.setEnabled(false);

	}

	public void setInitLayout() {

		add(startButton);
		add(endButton);
		add(serverList);

		add(portText);
		add(tanghooruFrame);

		setVisible(true);

	}

	public void addEventListener() {

		startButton.addActionListener(this);
		endButton.addActionListener(this);

		portText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

				char keyChar = e.getKeyChar();
				if (!Character.isDigit(keyChar) && keyChar != KeyEvent.VK_BACK_SPACE) {

					e.consume(); // 입력 취소
				}

				String text = portText.getText();
				if (text.length() >= 5) {

					e.consume();

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				String text = portText.getText();
				if (text.isEmpty() || text.length() <= 3) {
					startButton.setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				String text = portText.getText();
				if(!text.isEmpty()) {
					if(text.length() >= 3) {
						startButton.setEnabled(true);
					} else {
						startButton.setEnabled(false);
					}
				}

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton)e.getSource();
		
		if(j.equals(startButton)) {
			mContext.startServer();
			startButton.setEnabled(false);
			portText.setEnabled(false);
		} 

	}

}
