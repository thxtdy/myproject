package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Data;

@Data


public class TangClientFrame extends JFrame {

	private JLabel tangChatFrame;
	private TangClient client;

	public JTextArea chatList;
	private JTextField text;
	
	// 기능 수행 버튼 (유저 목록, 방 목록, 방 만들기)
	private JButton userList;
	private JButton roomList;
	private JButton makeRoom;
	private JButton sendButton;
	// 위 버튼의 기능 실행 결과 출력
	private JLabel allUser;

	private TangClient mContext;
	
	public TangClientFrame(TangClient mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		tangChatFrame = new JLabel(new ImageIcon("Img/keroroFrame1.png"));
		setTitle( "반갑습니후루후루~ [ " +  mContext.id + " ]");
		setSize(400, 530);
		setContentPane(tangChatFrame);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 채팅 로그
		chatList = new JTextArea();
		chatList.setBounds(50, 90, 290, 300);

		// 채팅 입력란
		text = new JTextField(30);
		text.setBounds(80, 400, 200, 30);

		sendButton = new JButton();
		sendButton.setBounds(240, 400, 30, 20);
		
		userList = new JButton(new ImageIcon("Img/UserImage.png"));
		userList.setBounds(270, 50, 30, 35);
		userList.setBackground(new Color(0, 191, 99));
		userList.setBorderPainted(false);
		
		roomList = new JButton(new ImageIcon("Img/RoomImage.png"));
		roomList.setBounds(310, 50, 30, 35);
		roomList.setBackground(new Color(0, 191, 99));
		roomList.setBorderPainted(false);
		
		makeRoom = new JButton(new ImageIcon("Img/PlusImage.png"));
		makeRoom.setBounds(345, 50, 30, 35);
		makeRoom.setBackground(new Color(0, 191, 99));
		makeRoom.setBorderPainted(false);
		
		allUser = new JLabel(new ImageIcon("Img/TangFrame.png"));
		allUser.setBounds(230, 50, 140, 200);
		allUser.setVisible(false);

	}

	public void setInitLayout() {

		tangChatFrame.add(sendButton);
		tangChatFrame.add(chatList);
		tangChatFrame.add(text);
		
		add(userList);
		add(roomList);
		add(makeRoom);
		
		

		setVisible(true);
	}

	public void addEventListener() {

//		text.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (!text.getText().equals("")) {
//					String message = text.getText();
////					chatList.append("[ 이름이름 ]: "+message + "\n");
//					mContext.chatwriter(message);
//					chatList.append(message + "\n");
////					client.getWriter().print(message);
//
//				} else if (text.getText().equals("")) {
//					return;
//				}
//				text.setText(null);
//
//			}
//		});

		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String message = text.getText();
					mContext.chatwriter(message);
					text.setText(null);
				}
			}
		});
		
//		sendButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (e.getSource() == sendButton && !text.getText().equals("")) {
//					String message = text.getText();
//					chatList.append("[ 이름이름 ]: "+message + "\n");
//					
//				} else if (text.getText().equals("")) {
//					System.out.println("빈칸");
//					return;
//
//				}
//				text.setText(null);
//
//			}
//		});
//		userList.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//			
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				userList.setVisible(true);
//				
//				System.out.println("들어옴");
//			}
//		});
		
	}

//	public static void main(String[] args) {
//		TangClientFrame clientFrame = new TangClientFrame();
//		
//	}
//	
}
