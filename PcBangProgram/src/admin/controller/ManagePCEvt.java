package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import admin.DAO.AdminDAO;
import admin.VO.PCVO;
import admin.view.ManagePC;

public class ManagePCEvt implements ActionListener{

	private ManagePC mpc;
//	private ManageShop ms;
	
	private static final int REGISTRATION = 0;
	private static final int MODIFICATION = 1;
	private static final int ELIMINATION = 2;
	
//	public ManagePCEvt(ManagePC mpc, ManageShop ms) {
	public ManagePCEvt(ManagePC mpc) {
		this.mpc = mpc;
//		this.ms = ms;
		setPCList();
	}//ManageMemberEvt
	
	/**
	 * DBMS ���̺��� ��ȸ�� ���ö� ����Ʈ�� JTable�� ����
	 */
	//method�� ���� �����ڸ� �� �𸣸� public���� �϶�
	private void setPCList() {
		DefaultTableModel dtm = mpc.getDtmPC();

		// JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);

		Object[] rowData = null;// JTable�� ���� ������

		// DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<PCVO> listPC = aDAO.selectAllPC();
			if (listPC.isEmpty()) {// ��ȸ�� PC�� ���� ���
				JOptionPane.showMessageDialog(mpc, "��ϵ� PC�� �����ϴ�.");
			} // end if
			PCVO pcVO = null;// list�� ���� ���� �����ϱ� ���� ����
			for (int i = 0; i < listPC.size(); i++) {
				pcVO = listPC.get(i);
				// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData = new Object[3];
				// �迭�� ���� �Ҵ�
				//{"PC��ȣ","IP�ּ�","�ٽ� ��ģ ��¥"}
//				rowData[0] = new Integer(i + 1);// auto boxing
				rowData[0] = pcVO.getPc_num();
				rowData[1] = pcVO.getIp_address();
				rowData[2] = pcVO.getInput_date();
				// dtm�� �߰�
				dtm.addRow(rowData);
			} // end for

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(mpc, "���񽺰� ��Ȱ���� ������ �˼��մϴ�.");
//				sqle.printStackTrace();
		} // end catch

	}// setPCList

	private void renewPcIp(int pcNum, int renewFlag) {
		String msg="";
		String inputIP = null;
		boolean regiModiFlag = false;
		if (renewFlag == REGISTRATION || renewFlag == MODIFICATION) {
			switch (renewFlag) {
			    case REGISTRATION: msg = "���"; break;
			    case MODIFICATION: msg = "����"; break;
			}//switch case
			inputIP = JOptionPane.showInputDialog(mpc, "���� "+msg+"�� IP�� �Է��ϼ���.\n��) 127.0.0.1");
			
			String[] options = {"��","�ƴϿ�"};
			switch (JOptionPane.showOptionDialog(mpc, "�Է��Ͻ� IP ["+inputIP+"]�� "+msg+" �Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
			case JOptionPane.OK_OPTION:
				regiModiFlag = true;
		    }//switch case
		} else if (renewFlag == ELIMINATION) {
			msg = "����";
			regiModiFlag = true;
		}//else if
		
		if (regiModiFlag) {
//			System.out.println("pcNum, inputIP�� �Ű������� �ϴ� update DAO methodȣ��");
			AdminDAO aDAO = AdminDAO.getInstance();
			try {
				if (aDAO.updatePCIP(pcNum, inputIP, msg)) {
					JOptionPane.showMessageDialog(mpc, pcNum+"�� PC�� IP�� "+msg+" �Ǿ����ϴ�.\n���α׷��� �ٽ� ���� ���ּ���");
					setPCList();
//					ms.addJpPCStateArr();
				}//end if
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}//try catch
		}//end if
		
	}//renewPcIp
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		int renewFlag = 0;
		if (ae.getSource() == mpc.getJbtnResiter()) { renewFlag = REGISTRATION; } 
		else if (ae.getSource() == mpc.getJbtnUpdate()) { renewFlag = MODIFICATION; } 
		else if (ae.getSource() == mpc.getJbtnDelete()) { renewFlag = ELIMINATION; } 
		
		int selectedRow = mpc.getJtPC().getSelectedRow(); 
		int pcNum = selectedRow+1;
		if (selectedRow == -1) {
			String msg = "";
			switch (renewFlag) {
			    case REGISTRATION: msg = "���"; break;
			    case MODIFICATION: msg = "����"; break;
			    case ELIMINATION: msg = "����"; break;
			}//switch case
			JOptionPane.showMessageDialog(mpc, "IP�� "+msg+"�� PC��ȣ�� ��Ͽ��� ���� ���ּ���");
			return;
		} else {
			String msg = "";
			String ip = (String)mpc.getDtmPC().getValueAt(selectedRow, 1);
			switch (renewFlag) {
		        case REGISTRATION: msg = "���ο� IP�� "+(pcNum)+"�� PC�� ��� �Ͻðڽ��ϱ�?"; break;
		        case MODIFICATION: 
		        	if (ip==null || ip.isEmpty()) {
						JOptionPane.showMessageDialog(mpc, (pcNum)+"�� PC���� ������ IP�� �����ϴ�.");
						return;
					} else {
						msg = (selectedRow+1)+"�� PC�� IP�� ���� �Ͻðڽ��ϱ�?"; break;
					}//if else
		        case ELIMINATION: 
		        	if (ip==null || ip.isEmpty()) {
						JOptionPane.showMessageDialog(mpc, (pcNum)+"�� PC���� ������ IP�� �����ϴ�.");
						return;
					} else {
						msg = (selectedRow+1)+"�� PC�� IP�� ���� �Ͻðڽ��ϱ�?"; break;
					}//if else
			}//switch case
			String[] options = {"��","�ƴϿ�"};
			switch (JOptionPane.showOptionDialog(mpc, msg, "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
		        case JOptionPane.OK_OPTION: renewPcIp(pcNum, renewFlag);
		    }//switch case
		}//if else

//		String[] options = {"��","�ƴϿ�"};
//		if (ae.getSource() == mpc.getJbtnResiter()) {
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(mpc, "IP�� ����� PC��ȣ��\n��Ͽ��� ���� ���ּ���");
//				return;
//			} else {
//				switch (JOptionPane.showOptionDialog(mpc, "���ο� IP�� "+(selectedRow+1)+"�� PC�� ��� �Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
//				    case JOptionPane.OK_OPTION: renewPcIp(REGISTRATION);
//				}//switch case
//			}// if else
//		}//end if
//		if (ae.getSource() == mpc.getJbtnUpdate()) {
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(mpc, "IP�� ������ PC��ȣ��\n��Ͽ��� ���� ���ּ���");
//				return;
//			} else {
//				switch (JOptionPane.showOptionDialog(mpc, (selectedRow+1)+"�� PC�� IP�� ���� ���� �Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
//				    case JOptionPane.OK_OPTION: renewPcIp(MODIFICATION);
//				}//switch case
//			}// if else
//		}//end if
//		if (ae.getSource() == mpc.getJbtnDelete()) {
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(mpc, "IP�� ������ PC��ȣ��\n��Ͽ��� ���� ���ּ���");
//				return;
//			} else {
//				switch (JOptionPane.showOptionDialog(mpc, (selectedRow+1)+"�� PC�� IP�� ���� �Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
//			    case JOptionPane.OK_OPTION: renewPcIp(ELIMINATION);
//			    }//switch case
//			}// if else
//		}//end if
	}//actionPerformed
	
}//class
