package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Data;

@Data

public class TangClientFrame extends JFrame {

	private JLabel tangChatFrame;
	private JTextField text;
	private JTextArea chatList;
	private TangClient client;
	private JButton sendButton;

	private TangClient mContext;
	
	public TangClientFrame(TangClient mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		tangChatFrame = new JLabel(new ImageIcon("Img/TangLogin.png"));
		setTitle("탕챗");
		setSize(400, 520);
		setContentPane(tangChatFrame);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 채팅 로그
		chatList = new JTextArea();
		chatList.setBounds(50, 75, 290, 300);

		// 채팅 입력란
		text = new JTextField(30);
		text.setBounds(80, 400, 200, 30);

		sendButton = new JButton();
		sendButton.setBounds(240, 400, 30, 20);

	}

	public void setInitLayout() {

		tangChatFrame.add(sendButton);
		tangChatFrame.add(chatList);
		tangChatFrame.add(text);

		setVisible(true);
	}

	public void addEventListener() {

		text.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!text.getText().equals("")) {
					String message = text.getText();
					chatList.append("[ 이름이름 ]: "+message + "\n");
//					client.getWriter().print(message);

				} else if (text.getText().equals("")) {
					return;
				}
				text.setText(null);

			}
		});

		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sendButton && !text.getText().equals("")) {
					String message = text.getText();
//						chatList.setText(message);
					chatList.append("[ 이름이름 ]: "+message + "\n");
//						while(true) {
//							chatList.append(message);
//						}

//					client.getWriter().print(message);
				} else if (text.getText().equals("")) {
					System.out.println("빈칸");
					return;

				}
				text.setText(null);

			}
		});

	}

//	public static void main(String[] args) {
//		TangClientFrame clientFrame = new TangClientFrame();
//		
//	}
//	
}
