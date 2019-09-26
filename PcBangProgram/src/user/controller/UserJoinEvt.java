package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.DAO.UserDAO;
import user.VO.UserJoinVO;
import user.view.UserJoin;

public class UserJoinEvt implements ActionListener, FocusListener {
	
	private UserJoin uj;
	
	public UserJoinEvt(UserJoin uj) {
		this.uj=uj;
	}//UserJoinEvt
	
	private void confirmId() {

	String joinId=uj.getJtfId().getText().trim();
	String temp="";
	
	UserDAO uDAO=UserDAO.getInstance();
	
		try {
			temp=uDAO.overlapId(joinId);
			
			if(!joinId.equals("")) {
				if(temp.isEmpty()) {
					JOptionPane.showMessageDialog(uj, "��밡���� ���̵� �Դϴ�");
					uj.getJpfPass().requestFocus(); 
				}else {
					uj.getJtfId().setText("");
					JOptionPane.showMessageDialog(uj, "�̹� �����ϴ� ���̵� �Դϴ�");
					uj.getJtfId().requestFocus(); 
				}//end else
			}else {
				JOptionPane.showMessageDialog(uj, "���̵� �Է����ּ���");
				uj.getJtfId().requestFocus();
			}//end else
	
		} catch (SQLException e) {
			e.printStackTrace();
			
		}//end catch
		
	}//confirmId
	
	private void addMember() {
		
		String joinId=uj.getJtfId().getText().trim();
		String joinPw= new String(uj.getJpfPass().getPassword()).trim();
		String joinPwConfirm= new String(uj.getJpfPassComfirm().getPassword()).trim();
		String cipherPass="";
		
			try {
				DataEncrypt de=new DataEncrypt("1111111111111111");
				cipherPass=de.encryption(joinPw);
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			} catch (GeneralSecurityException gse) {
				gse.printStackTrace();
			} // end catch
			
		
		String joinName=uj.getJtfName().getText().trim();
			StringBuilder sbNum=new StringBuilder();
			sbNum.append(uj.getJcbNum().getSelectedItem()).append(uj.getJtfPhone2().getText().trim()).append(uj.getJtfPhone3().getText().trim());
		String num2=uj.getJtfPhone2().getText().trim();
		String num3=uj.getJtfPhone3().getText().trim();
		String joinPhone=sbNum.toString();
			StringBuilder sbQuestion=new StringBuilder();
			sbQuestion.append(uj.getJcbQuestion().getSelectedItem());
		String joinQuestion=sbQuestion.toString();
		String joinAnswer=uj.getJtfAnswer().getText().trim();
		String temp2="";
		
			if (!joinId.equals("") && !cipherPass.equals("") && !joinPwConfirm.equals("")  && !joinName.equals("") && !num2.equals("")&& !num3.equals("") && !joinQuestion.equals("") && !joinAnswer.equals("")) {
				
					if (!joinPw.equals(joinPwConfirm)) {
						JOptionPane.showMessageDialog(uj, "��й�ȣ�� ��ġ���� �ʽ��ϴ�");
						uj.getJpfPass().setText("");
						uj.getJpfPassComfirm().setText("");
						uj.getJpfPass().requestFocus();
						return;
					}
						
					
					//�Է¹��� ���̵�� ��й�ȣȮ������, ���� VO�� ����
					UserJoinVO ujVO=new UserJoinVO(joinId, cipherPass, joinName, joinPhone, joinQuestion, joinAnswer);
					//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
					UserDAO uDAO=UserDAO.getInstance();
					
					
						try {
							temp2=uDAO.overlapPhone(joinPhone);
							
								if(temp2.isEmpty()) {
//									JOptionPane.showMessageDialog(uj, "��밡���� ���̵� �Դϴ�");
					
						try {
								
							uDAO.insertMember(ujVO);
							JOptionPane.showMessageDialog(uj, "ȸ�������� �Ϸ�Ǿ����ϴ�");
							uj.dispose();
							
						} catch (SQLException e) {
							
							e.printStackTrace();
		//					uj.getJtfId().setText("");
		//					uj.getJpfPass().setText("");
		//					uj.getJpfPassComfirm().setText("");
		//					uj.getJtfName().setText("");
		//					uj.getJcbNum().setSelectedIndex(0);         
		//					uj.getJtfPhone2().setText("");
		//					uj.getJtfPhone3().setText("");
		//					uj.getJcbQuestion().setSelectedIndex(0);
		//					uj.getJtfAnswer().setText("");
							JOptionPane.showMessageDialog(uj, "ȸ�����Կ� �����߽��ϴ�.");
							
					}//end catch
						
								}else {
									JOptionPane.showMessageDialog(uj, "�̹� ȸ�����Ե� ��ȭ��ȣ �Դϴ�");
									uj.getJtfPhone2().setText("");
									uj.getJtfPhone3().setText("");
									
								}//end else
					
						} catch (SQLException e) {
							e.printStackTrace();
							
						}//end catch

				}else {
					JOptionPane.showMessageDialog(uj, "��ĭ�� ��� �Է����ּ���");
			}//end else

	}//addMember
	
	private boolean flag=false;
	private String inputId = "";
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String inputId=new String(uj.getJtfId().getText().trim());
		if (this.inputId.isEmpty()) {
			this.inputId=inputId;
		}else {
			if(!this.inputId.equals(inputId)) {
				flag=false;
				this.inputId=inputId;
			}//end if
		}//end else
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		String pass=new String(uj.getJpfPass().getPassword()).trim();
		String confirm=new String(uj.getJpfPassComfirm().getPassword()).trim();
	
		
		if (ae.getSource()==uj.getJtfId()) { //ID �Է¹ޱ�
			if(!uj.getJtfId().getText().equals("")) { //ID�� ����ִ��� �Ǵ�
				uj.getJbtnOverlap().requestFocus(); //���� �����Ѵٸ� Ŀ���� ���̵� �ߺ�Ȯ������ �̵�
			}//end if
			return;
		}//end if
		
		if(ae.getSource()==uj.getJbtnOverlap()) {
			flag=true;
			confirmId();
			uj.getJpfPass().requestFocus();
			return;
		}//end if
		
		if(ae.getSource()==uj.getJpfPass()) {
			if(pass.equals("")){   
				uj.getJpfPassComfirm().requestFocus();
			}//end if
			return; 
		}//end if
			
		if(ae.getSource()==uj.getJpfPassComfirm()) {
			if(confirm.equals("")){   
				uj.getJtfName().requestFocus();
			}//end if
		}//end if
		
		
		if(ae.getSource()==uj.getJtfName()) {
			if(!uj.getJtfName().getText().equals("")) {   
				uj.getJcbNum().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJcbNum()) {
			uj.getJtfPhone2().requestFocus();
		}//end if

		if(ae.getSource()==uj.getJtfPhone2()) {
			if(!uj.getJtfPhone2().getText().equals("")) {   
				uj.getJtfPhone3().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJtfPhone3()) {
			if(!uj.getJtfPhone3().getText().equals("")) {   
				uj.getJcbQuestion().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJcbQuestion()) {
			uj.getJtfAnswer().requestFocus();
		}//end if
		
		if(ae.getSource()==uj.getJtfAnswer()) {
			if(!uj.getJtfAnswer().getText().equals("")) {   
				uj.getJbtnJoin().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJbtnJoin()) {
			if (!flag && !uj.getJtfId().getText().equals("")) {
				JOptionPane.showMessageDialog(uj, "���̵� �ߺ�Ȯ���� ���ּ���");
				uj.getJbtnOverlap().requestFocus();
			}else{
				addMember();
			}//end else
		}//end if

}//actionPerformed
	
	@Override
	public void focusGained(FocusEvent fe) {
		
	}//focusGained

	@Override
	public void focusLost(FocusEvent fe) {
		

		if (fe.getSource() == uj.getJtfPhone2()) {
			
			if (!uj.getJtfPhone2().getText().equals("")) {
				
				if (uj.getJtfPhone2().getText().length() == 3 || uj.getJtfPhone2().getText().length() == 4) {
					
					try {
						Integer.parseInt(uj.getJtfPhone2().getText().trim());
						
					}catch(NumberFormatException nfe) {
//						uj.getJtfPhone2().setText("");
						JOptionPane.showMessageDialog(uj, "��ȭ��ȣ�� ���ڷθ� �Է����ּ���");
						uj.getJtfPhone2().requestFocus();
						return;
					}//end catch
					
					uj.getJtfPhone3().requestFocus();
					
				} else {
//					uj.getJtfPhone2().setText("");
					JOptionPane.showMessageDialog(uj, "3�ڸ�Ȥ�� 4�ڸ� ��ȣ�� �Է����ּ���");
					uj.getJtfPhone2().requestFocus();
					return;
				} // end else
			} // end if
		}//end if
		
		if (fe.getSource() == uj.getJtfPhone3()) {
			if (!uj.getJtfPhone3().getText().equals("")) {
				if (uj.getJtfPhone3().getText().length() == 4) {
					
					try {
						Integer.parseInt(uj.getJtfPhone3().getText().trim());
						
					}catch(NumberFormatException nfe) {
//						uj.getJtfPhone3().setText("");
						JOptionPane.showMessageDialog(uj, "��ȭ��ȣ�� ���ڷθ� �Է����ּ���");
						uj.getJtfPhone3().requestFocus();
						return;
					}//end catch
//					uj.getJtfAnswer().requestFocus();
					uj.getJcbQuestion().requestFocus();
				} else {
//					uj.getJtfPhone3().setText("");
					JOptionPane.showMessageDialog(uj, "4�ڸ� ��ȣ�� �Է����ּ���");
					uj.getJtfPhone3().requestFocus();
					return;
				} // end else
			} // end if
		} // end if

	}//focusLost
	
	
	
	
}//class




























