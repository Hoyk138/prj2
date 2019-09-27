package admin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import admin.view.AdminChat;
import admin.view.PCState;

public class PCStateEvt extends MouseAdapter implements ActionListener {

	private PCState pcs;
	private int pcNum;
	
//	private Socket socket;
//	private DataInputStream dis;
//	private DataOutputStream dos;
	
	
	public PCStateEvt(PCState pcs) {
		this.pcs = pcs;
		pcNum = pcs.getPcNum();
		
	}//PCStateEvt
	
	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == pcs) {
			pcs.setBackground(new Color(0x0FE3A3));
		}//end if
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == pcs) {
			pcs.setBackground(pcs.getBackgrounColor());	
		}//end if
	}

	private AdminChat ac = null;
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pcs.getJmiChat()) {
//			new AdminChat(this);
			//배경색으로 채팅 유무를 판단.
			if (pcs.getBackgrounColor()==Color.RED) {
				pcs.getAc().setVisible(true);
//				if (ac == null) {
//					ac = new AdminChat(pcs,this);
//					System.out.println("ac: "+ac);
//				}//end if
//				AdminChat.getInstance(pcs);
//				pcs.setBackgrounColor(Color.LIGHT_GRAY);
//				pcs.setBackground(pcs.getBackgrounColor());
			} else {
				JOptionPane.showMessageDialog(pcs, "요청 받은 채팅이 없습니다.");
			}//else if
		}//end if
			if (ae.getSource() == pcs.getJmiOrder()) {
				if (pcs.getBackgrounColor()==Color.GREEN) {
					System.out.println("PC"+pcs.getPcNum()+"번 주문 확인");
					//db에 접근하여 해당 pcnum의 최신 주문 내용을 조회
					pcs.setBackgrounColor(Color.LIGHT_GRAY);
					pcs.setBackground(pcs.getBackgrounColor());	
				} else {
					JOptionPane.showMessageDialog(pcs, "요청 받은 주문이 없습니다.");
				}//else if
			}
			if (ae.getSource() == pcs.getJmiClose()) {
				System.out.println("PC"+pcs.getPcNum()+"번 결제 및 사용 종료");
				try {
					String[] options = {"예","아니요"};
					switch (JOptionPane.showOptionDialog(pcs, pcs.getPcNum()+" PC의 사용을 종료하고 결제 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
					case JOptionPane.OK_OPTION:
						pcs.getDos().writeUTF("/종료");
				    }//switch case
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}//try catch
			}//end if
	}//actionPerformed
	
	public int getPcNum() {
		return pcNum;
	}

//	public DataInputStream getDis() {
//		return dis;
//	}
//
//	public DataOutputStream getDos() {
//		return dos;
//	}

	public AdminChat getAc() {
		return ac;
	}

	public void setAc(AdminChat ac) {
		this.ac = ac;
	}
	
}//class
