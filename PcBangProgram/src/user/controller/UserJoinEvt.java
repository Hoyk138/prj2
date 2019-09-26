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
					JOptionPane.showMessageDialog(uj, "사용가능한 아이디 입니다");
					uj.getJpfPass().requestFocus(); 
				}else {
					uj.getJtfId().setText("");
					JOptionPane.showMessageDialog(uj, "이미 존재하는 아이디 입니다");
					uj.getJtfId().requestFocus(); 
				}//end else
			}else {
				JOptionPane.showMessageDialog(uj, "아이디를 입력해주세요");
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
						JOptionPane.showMessageDialog(uj, "비밀번호가 일치하지 않습니다");
						uj.getJpfPass().setText("");
						uj.getJpfPassComfirm().setText("");
						uj.getJpfPass().requestFocus();
						return;
					}
						
					
					//입력받은 아이디와 비밀번호확인질문, 답을 VO에 저장
					UserJoinVO ujVO=new UserJoinVO(joinId, cipherPass, joinName, joinPhone, joinQuestion, joinAnswer);
					//DB를 조회하기 위해 DAO객체를 얻기
					UserDAO uDAO=UserDAO.getInstance();
					
					
						try {
							temp2=uDAO.overlapPhone(joinPhone);
							
								if(temp2.isEmpty()) {
//									JOptionPane.showMessageDialog(uj, "사용가능한 아이디 입니다");
					
						try {
								
							uDAO.insertMember(ujVO);
							JOptionPane.showMessageDialog(uj, "회원가입이 완료되었습니다");
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
							JOptionPane.showMessageDialog(uj, "회원가입에 실패했습니다.");
							
					}//end catch
						
								}else {
									JOptionPane.showMessageDialog(uj, "이미 회원가입된 전화번호 입니다");
									uj.getJtfPhone2().setText("");
									uj.getJtfPhone3().setText("");
									
								}//end else
					
						} catch (SQLException e) {
							e.printStackTrace();
							
						}//end catch

				}else {
					JOptionPane.showMessageDialog(uj, "빈칸을 모두 입력해주세요");
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
	
		
		if (ae.getSource()==uj.getJtfId()) { //ID 입력받기
			if(!uj.getJtfId().getText().equals("")) { //ID가 비어있는지 판단
				uj.getJbtnOverlap().requestFocus(); //값이 존재한다면 커서를 아이디 중복확인으로 이동
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
				JOptionPane.showMessageDialog(uj, "아이디 중복확인을 해주세요");
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
						JOptionPane.showMessageDialog(uj, "전화번호는 숫자로만 입력해주세요");
						uj.getJtfPhone2().requestFocus();
						return;
					}//end catch
					
					uj.getJtfPhone3().requestFocus();
					
				} else {
//					uj.getJtfPhone2().setText("");
					JOptionPane.showMessageDialog(uj, "3자리혹은 4자리 번호를 입력해주세요");
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
						JOptionPane.showMessageDialog(uj, "전화번호는 숫자로만 입력해주세요");
						uj.getJtfPhone3().requestFocus();
						return;
					}//end catch
//					uj.getJtfAnswer().requestFocus();
					uj.getJcbQuestion().requestFocus();
				} else {
//					uj.getJtfPhone3().setText("");
					JOptionPane.showMessageDialog(uj, "4자리 번호를 입력해주세요");
					uj.getJtfPhone3().requestFocus();
					return;
				} // end else
			} // end if
		} // end if

	}//focusLost
	
	
	
	
}//class




























