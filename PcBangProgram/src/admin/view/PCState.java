package admin.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PCState extends JPanel {
	
	private int pcNum;
	
	private JPopupMenu jpm;
	private JMenuItem jmiChat;
	private JMenuItem jmiOrder;
	private JMenuItem jmiClose;
	
	private JLabel[] jlblPCStateArr = new JLabel[5]; 
	
	public PCState(int pcNum) {
		this.pcNum = pcNum;
		
		setLayout(new GridLayout(5, 1));
		for (int i = 0; i < jlblPCStateArr.length; i++) {
			jlblPCStateArr[i]= new JLabel((i+1)+"번 라벨");
			add(jlblPCStateArr[i]);
		}//end for
		
		//팝업 메뉴 등록
		jpm = new JPopupMenu();
	    
		jmiChat = new JMenuItem("채팅확인");
	    jmiOrder = new JMenuItem("주문확인");
    	jmiClose = new JMenuItem("결제 및 사용 종료");
    	jpm.add(jmiChat);
    	jpm.add(jmiOrder);
    	jpm.add(jmiClose);
    	
    	setComponentPopupMenu(jpm);
    	
    	//이벤트 등록
    	PCStateEvt pcse = new PCStateEvt(this);
    	this.addMouseListener(pcse);
    	jmiChat.addActionListener(pcse);
    	jmiOrder.addActionListener(pcse);
    	jmiClose.addActionListener(pcse);
		
		setBorder(new TitledBorder(pcNum+"번"));
		setVisible(true);
		
	}//PCState
	
	public int getPcNum() {
		return pcNum;
	}

	public JMenuItem getJmiChat() {
		return jmiChat;
	}

	public JMenuItem getJmiOrder() {
		return jmiOrder;
	}

	public JMenuItem getJmiClose() {
		return jmiClose;
	}
	
}
