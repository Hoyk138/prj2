package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.FindIdVO;
import user.view.FindId;

public class FindIdEvt implements ActionListener {

	private FindId fi;
	
	public FindIdEvt (FindId fi) {
		this.fi=fi;
	}
	
	private void findId() {
		
		if(!fi.getJtfName().getText().equals("") && !fi.getJtfPhone2().getText().equals("") && !fi.getJtfPhone3().getText().equals("")) {
			
			String idName=fi.getJtfName().getText();
			StringBuilder sb=new StringBuilder();
			sb.append(fi.getJcbNum().getSelectedItem()).append(fi.getJtfPhone2().getText()).append(fi.getJtfPhone3().getText());
			String idPhone=sb.toString();
			
			//�Է¹��� �̸��� ��ȭ��ȣ�� VO�� ����
			FindIdVO fiVO=new FindIdVO(idName, idPhone);
			//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
			UserDAO uDAO=UserDAO.getInstance();
			
			try {
				String id=uDAO.selectId(fiVO);
				
				if(!id.isEmpty()) {
					JOptionPane.showMessageDialog(fi, "ȸ������ ���̵�� "+id+" �Դϴ�"); 
					fi.dispose();
					
				}else {
					fi.getJtfName().setText(""); //�̸� �ʱ�ȭ
					fi.getJtfPhone2().setText(""); //��ȭ��ȣ �ʱ�ȭ
					fi.getJtfPhone3().setText(""); //��ȭ��ȣ �ʱ�ȭ
					fi.getJcbNum().setSelectedIndex(0);
					JOptionPane.showMessageDialog(fi, "��ġ�ϴ� ȸ�������� �����ϴ�");
					fi.getJtfName().requestFocus(); //Ŀ���� �̸�ĭ�� ��ġ
					
				}//end else
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//catch
			}else {
				JOptionPane.showMessageDialog(fi, "������ ��� �Է����ּ���");  
			}//end else
		
	}//findId
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==fi.getJtfName()) { //�̸� �Է¹ޱ�
			if(!fi.getJtfName().getText().equals("")) { //�̸��� ����ִ��� �Ǵ�
				fi.getJtfPhone2().requestFocus(); //���� �����Ѵٸ� Ŀ���� ��ȭ��ȣ�߰��ڸ��� �̵�
			}//end if
		}//end if
		
		if(ae.getSource()==fi.getJcbNum()) {
			fi.getJtfPhone2().requestFocus();
		}//end if
		
		if(ae.getSource()==fi.getJtfPhone2()) {
			if(!fi.getJtfPhone2().getText().equals("")) {

					
				fi.getJtfPhone3().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==fi.getJtfPhone3()) {
			if(!fi.getJtfPhone2().getText().equals("")) {
				fi.getJbtnFindID().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==fi.getJbtnFindID()) {
			findId();
		}//end if
		
	}//actionPerformed
	
}//class
