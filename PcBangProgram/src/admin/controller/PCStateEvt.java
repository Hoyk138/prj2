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
		//�������� ä�� ������ �Ǵ�.
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
			JOptionPane.showMessageDialog(pcs, "��û ���� ä���� �����ϴ�.");
		}//if else
	}//checkChat
	
	private void checkOrder() {
		if (pcs.getBackgrounColor() == Color.GREEN) {
//			System.out.println("PC" + pcs.getPcNum() + "�� �ֹ� Ȯ��");
			// DB�� �����Ͽ� �ش� pcnum�� �ֽ� �ֹ� ������ ��ȸ
			//jlblPCStateArr[USER_ID].setText("����� ID: ");
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
				StringBuilder orderList = new StringBuilder("�ֹ� ���� ��ǰ��\n\n�Դϴ�.");
				for (int i = 0; i < list.size(); i++) {
					oiaqVO = list.get(i);
					
					orderItem = new StringBuilder("[]   ��\n\n");
					switch (oiaqVO.getCategory()) {
					case "F": category = "�Ļ�"; break;
					case "S": category = "����"; break;
					case "D": category = "����"; break;
					}
					orderItem.insert(orderItem.indexOf("[")+1, category);
					orderItem.insert(orderItem.indexOf("]")+2, oiaqVO.getName());
					orderItem.insert(orderItem.lastIndexOf("��")-1, oiaqVO.getQuantity());
					
					orderList.insert(orderList.indexOf("��"), orderItem.toString());
				}//end for
				
				JOptionPane.showMessageDialog(pcs, orderList.toString());
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				JOptionPane.showMessageDialog(pcs, "�ֹ� ��ȸ�� �����Ͽ����ϴ�.\n[�ֹ� ����] �г��� Ȯ�� ���ּ���");
			}//try catch
			
			
			//��ȸ�� �ֹ� ������ ȭ�鿡 ��� 
			pcs.setBackgrounColor(Color.LIGHT_GRAY);
			pcs.setBackground(pcs.getBackgrounColor());
		} else {
			JOptionPane.showMessageDialog(pcs, "��û ���� �ֹ��� �����ϴ�.");
		} //if else
	}//checkOrder
	
	private void paymentAndClose() {
		System.out.println("PC" + pcs.getPcNum() + "�� ���� �� ��� ����");
		try {
			String[] options = { "��", "�ƴϿ�" };
			switch (JOptionPane.showOptionDialog(pcs, pcs.getPcNum() + " PC�� ����� �����ϰ� ���� �Ͻðڽ��ϱ�?", "Ȯ��",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
			case JOptionPane.OK_OPTION:
				pcs.getDos().writeUTF("/����");
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
