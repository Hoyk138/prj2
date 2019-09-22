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
	 * DBMS 테이블에서 조회한 도시락 리스트를 JTable에 설정
	 */
	//method의 접근 지정자를 잘 모르면 public으로 하라
	private void setPCList() {
		DefaultTableModel dtm = mpc.getDtmPC();

		// JTable의 레코드 초기화
		dtm.setRowCount(0);

		Object[] rowData = null;// JTable에 넣을 데이터

		// DBMS에서 조회
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<PCVO> listPC = aDAO.selectAllPC();
			if (listPC.isEmpty()) {// 조회된 PC가 없는 경우
				JOptionPane.showMessageDialog(mpc, "등록된 PC가 없습니다.");
			} // end if
			PCVO pcVO = null;// list의 방의 값을 저장하기 위한 변수
			for (int i = 0; i < listPC.size(); i++) {
				pcVO = listPC.get(i);
				// 조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
				rowData = new Object[3];
				// 배열에 값을 할당
				//{"PC번호","IP주소","다시 고친 날짜"}
//				rowData[0] = new Integer(i + 1);// auto boxing
				rowData[0] = pcVO.getPc_num();
				rowData[1] = pcVO.getIp_address();
				rowData[2] = pcVO.getInput_date();
				// dtm에 추가
				dtm.addRow(rowData);
			} // end for

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(mpc, "서비스가 원활하지 못한점 죄송합니다.");
//				sqle.printStackTrace();
		} // end catch

	}// setPCList

	private void renewPcIp(int pcNum, int renewFlag) {
		String msg="";
		String inputIP = null;
		boolean regiModiFlag = false;
		if (renewFlag == REGISTRATION || renewFlag == MODIFICATION) {
			switch (renewFlag) {
			    case REGISTRATION: msg = "등록"; break;
			    case MODIFICATION: msg = "수정"; break;
			}//switch case
			inputIP = JOptionPane.showInputDialog(mpc, "새로 "+msg+"할 IP를 입력하세요.\n예) 127.0.0.1");
			
			String[] options = {"예","아니요"};
			switch (JOptionPane.showOptionDialog(mpc, "입력하신 IP ["+inputIP+"]로 "+msg+" 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
			case JOptionPane.OK_OPTION:
				regiModiFlag = true;
		    }//switch case
		} else if (renewFlag == ELIMINATION) {
			msg = "삭제";
			regiModiFlag = true;
		}//else if
		
		if (regiModiFlag) {
//			System.out.println("pcNum, inputIP를 매개변수로 하는 update DAO method호출");
			AdminDAO aDAO = AdminDAO.getInstance();
			try {
				if (aDAO.updatePCIP(pcNum, inputIP, msg)) {
					JOptionPane.showMessageDialog(mpc, pcNum+"번 PC의 IP가 "+msg+" 되었습니다.\n프로그램을 다시 실행 해주세요");
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
			    case REGISTRATION: msg = "등록"; break;
			    case MODIFICATION: msg = "수정"; break;
			    case ELIMINATION: msg = "삭제"; break;
			}//switch case
			JOptionPane.showMessageDialog(mpc, "IP를 "+msg+"할 PC번호를 목록에서 선택 해주세요");
			return;
		} else {
			String msg = "";
			String ip = (String)mpc.getDtmPC().getValueAt(selectedRow, 1);
			switch (renewFlag) {
		        case REGISTRATION: msg = "새로운 IP를 "+(pcNum)+"번 PC에 등록 하시겠습니까?"; break;
		        case MODIFICATION: 
		        	if (ip==null || ip.isEmpty()) {
						JOptionPane.showMessageDialog(mpc, (pcNum)+"번 PC에는 수정할 IP가 없습니다.");
						return;
					} else {
						msg = (selectedRow+1)+"번 PC의 IP를 수정 하시겠습니까?"; break;
					}//if else
		        case ELIMINATION: 
		        	if (ip==null || ip.isEmpty()) {
						JOptionPane.showMessageDialog(mpc, (pcNum)+"번 PC에는 삭제할 IP가 없습니다.");
						return;
					} else {
						msg = (selectedRow+1)+"번 PC의 IP를 삭제 하시겠습니까?"; break;
					}//if else
			}//switch case
			String[] options = {"예","아니요"};
			switch (JOptionPane.showOptionDialog(mpc, msg, "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
		        case JOptionPane.OK_OPTION: renewPcIp(pcNum, renewFlag);
		    }//switch case
		}//if else

//		String[] options = {"예","아니요"};
//		if (ae.getSource() == mpc.getJbtnResiter()) {
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(mpc, "IP를 등록할 PC번호를\n목록에서 선택 해주세요");
//				return;
//			} else {
//				switch (JOptionPane.showOptionDialog(mpc, "새로운 IP를 "+(selectedRow+1)+"번 PC에 등록 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
//				    case JOptionPane.OK_OPTION: renewPcIp(REGISTRATION);
//				}//switch case
//			}// if else
//		}//end if
//		if (ae.getSource() == mpc.getJbtnUpdate()) {
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(mpc, "IP를 수정할 PC번호를\n목록에서 선택 해주세요");
//				return;
//			} else {
//				switch (JOptionPane.showOptionDialog(mpc, (selectedRow+1)+"번 PC의 IP를 새로 수정 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
//				    case JOptionPane.OK_OPTION: renewPcIp(MODIFICATION);
//				}//switch case
//			}// if else
//		}//end if
//		if (ae.getSource() == mpc.getJbtnDelete()) {
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(mpc, "IP를 삭제할 PC번호를\n목록에서 선택 해주세요");
//				return;
//			} else {
//				switch (JOptionPane.showOptionDialog(mpc, (selectedRow+1)+"번 PC의 IP를 삭제 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
//			    case JOptionPane.OK_OPTION: renewPcIp(ELIMINATION);
//			    }//switch case
//			}// if else
//		}//end if
	}//actionPerformed
	
}//class
