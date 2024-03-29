package admin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import admin.DAO.AdminDAO;
import admin.VO.OrderedItemAndQuantityVO;
import admin.VO.UsePCVO;
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
	}//mouseEntered

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == pcs) {
			pcs.setBackground(pcs.getBackgrounColor());	
		}//end if
	}//mouseExited
	
	private AdminChat ac = null;
	private void checkChat() {
//		new AdminChat(this);
		//배경색으로 채팅 유무를 판단.
		if (pcs.getBackgrounColor()==Color.RED) {
			pcs.getAc().setVisible(true);
//			if (ac == null) {
//				ac = new AdminChat(pcs,this);
//				System.out.println("ac: "+ac);
//			}//end if
//			AdminChat.getInstance(pcs);
//			pcs.setBackgrounColor(Color.LIGHT_GRAY);
//			pcs.setBackground(pcs.getBackgrounColor());
		} else {
			JOptionPane.showMessageDialog(pcs, "요청 받은 채팅이 없습니다.");
		}//if else
	}//checkChat
	
	private void checkOrder() {
		if (pcs.getBackgrounColor() == Color.GREEN) {
//			System.out.println("PC" + pcs.getPcNum() + "번 주문 확인");
			// DB에 접근하여 해당 pcnum의 최신 주문 내용을 조회
			//jlblPCStateArr[USER_ID].setText("사용자 ID: ");
			String lblTxtUserID = pcs.getJlblPCStateArr()[0].getText();
			String userID = lblTxtUserID.substring(lblTxtUserID.indexOf(":")+2);
//			System.out.println(userID);
			
			UsePCVO upcVO = new UsePCVO(userID, pcs.getPcNum());
			
			AdminDAO aDAO = AdminDAO.getInstance();
			
			List<OrderedItemAndQuantityVO> list = null;
			try {
				list = aDAO.selectJustOrder(upcVO);
				OrderedItemAndQuantityVO oiaqVO = null;
				StringBuilder orderItem = null;
				String category = null;
				StringBuilder orderList = new StringBuilder("주문 받은 상품은\n\n입니다.");
				for (int i = 0; i < list.size(); i++) {
					oiaqVO = list.get(i);
					
					orderItem = new StringBuilder("[]   개\n\n");
					switch (oiaqVO.getCategory()) {
					case "F": category = "식사"; break;
					case "S": category = "스낵"; break;
					case "D": category = "음료"; break;
					}
					orderItem.insert(orderItem.indexOf("[")+1, category);
					orderItem.insert(orderItem.indexOf("]")+2, oiaqVO.getName());
					orderItem.insert(orderItem.lastIndexOf("개")-1, oiaqVO.getQuantity());
					
					orderList.insert(orderList.indexOf("입"), orderItem.toString());
				}//end for
				
				JOptionPane.showMessageDialog(pcs, orderList.toString());
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				JOptionPane.showMessageDialog(pcs, "주문 조회에 실패하였습니다.\n[주문 관리] 패널을 확인 해주세요");
			}//try catch
			
			
			//조회한 주문 내역을 화면에 출력 
			pcs.setBackgrounColor(Color.LIGHT_GRAY);
			pcs.setBackground(pcs.getBackgrounColor());
		} else {
			JOptionPane.showMessageDialog(pcs, "요청 받은 주문이 없습니다.");
		} //if else
	}//checkOrder
	
	private void paymentAndClose() {
		System.out.println("PC" + pcs.getPcNum() + "번 결제 및 사용 종료");
		try {
			String[] options = { "예", "아니요" };
			switch (JOptionPane.showOptionDialog(pcs, pcs.getPcNum() + " PC의 사용을 종료하고 결제 하시겠습니까?", "확인",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
			case JOptionPane.OK_OPTION:
				pcs.getDos().writeUTF("/종료");
			}// switch case
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // try catch
	}//paymentAndClose

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pcs.getJmiChat()) {
			checkChat();
		}//end if
		if (ae.getSource() == pcs.getJmiOrder()) {
			checkOrder();
		}//end if
		if (ae.getSource() == pcs.getJmiClose()) {
			paymentAndClose();
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
