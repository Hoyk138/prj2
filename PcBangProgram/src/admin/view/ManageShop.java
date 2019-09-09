package admin.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ManageShop extends JPanel implements MouseListener, ActionListener{
	
	private JPopupMenu jpm;
	private JMenuItem[] jmiChatArr = new JMenuItem[20];
	private JMenuItem[] jmiOrderArr = new JMenuItem[20];
	private JMenuItem[] jmiCloseArr = new JMenuItem[20];
	
	private JLabel[] jlblPCStateArr = new JLabel[4];
	
	private JPanel[] jpPCStateArr;
	
	public ManageShop(MainView mv) {
		setBorder(new TitledBorder("매장"));
		
		setLayout(new GridLayout(4, 5));
		jpPCStateArr = new JPanel[20];
		for (int PCNum = 1; PCNum <= 20; PCNum++) {
			add(jpPCStateArr[PCNum-1] = jpPCState(PCNum));
			jpPCStateArr[PCNum-1].addMouseListener(this);
		}//end for

	}//ManageShop
	
	public JPanel jpPCState(int PCNum) {
		JPanel jpPCState = new JPanel();
		
		//팝업 메뉴 생성
		jpm = new JPopupMenu();
		//팝업메뉴에 들어갈 메뉴 아이템 생성
//		jmiChat = new JMenuItem("채팅확인");
//		jmiOrder = new JMenuItem("주문확인");
//		jmiClose = new JMenuItem("결제 및 사용 종료");
		jmiChatArr[PCNum-1] = new JMenuItem("채팅확인");
		jmiOrderArr[PCNum-1] = new JMenuItem("주문확인");
		jmiCloseArr[PCNum-1] = new JMenuItem("결제 및 사용 종료");
		//팝업메뉴에 메뉴 아이템 등록
		jpm.add(jmiChatArr[PCNum-1]);
		jpm.add(jmiOrderArr[PCNum-1]);
		jpm.add(jmiCloseArr[PCNum-1]);
		//테이블에 팝업메뉴 배치
		jpPCState.setComponentPopupMenu(jpm);
		
		//배치
		jpPCState.setLayout(null);
		for (int i = 0; i < jlblPCStateArr.length; i++) {
			jlblPCStateArr[i] = new JLabel();
			jlblPCStateArr[i].setText(String.valueOf(i+1)+"번 라벨");
			jlblPCStateArr[i].setBounds(25, 25+25*i, 100, 25);
			jpPCState.add(jlblPCStateArr[i]);
		}//end for
		
		jmiChatArr[PCNum-1].addActionListener(this);
		jmiOrderArr[PCNum-1].addActionListener(this);
		jmiCloseArr[PCNum-1].addActionListener(this);
		
		jpPCState.setBorder(new TitledBorder(PCNum+"번"));
		jpPCState.setBounds(0, 0, 150, 150);
		jpPCState.setVisible(true);
		
		return jpPCState;
	}//jpPCState
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent me) {
		for (int i = 0; i < jpPCStateArr.length; i++) {
			if (me.getSource() == jpPCStateArr[i]) {
				jpPCStateArr[i].setBackground(Color.DARK_GRAY);
			}//end if
		}
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent me) {
		for (int i = 0; i < jpPCStateArr.length; i++) {
			if (me.getSource() == jpPCStateArr[i]) {
				jpPCStateArr[i].setBackground(Color.LIGHT_GRAY);	
			}//end if
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		for (int i = 0; i < 20; i++) {
			if (ae.getSource() == jmiChatArr[i]) {
				new AdminChat();
			}
			if (ae.getSource() == jmiOrderArr[i]) {
				System.out.println("PC-"+(i+1)+"번 주문 확인");
			}
			if (ae.getSource() == jmiCloseArr[i]) {
				System.out.println("PC-"+(i+1)+"번 결제 및 사용 종료");
			}
		}
	}

	public JPanel[] getJpPCStateArr() {
		return jpPCStateArr;
	}
	
}
