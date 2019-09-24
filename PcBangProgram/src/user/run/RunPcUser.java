package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import user.view.UserLogin;

public class RunPcUser {
	
	public RunPcUser() {
		new UserLogin();
	}//RunPcUser
	
	public String getIp() {
		InetAddress local; 
		String ip=null;
		
		try { 
			local = InetAddress.getLocalHost(); 
			ip = local.getHostAddress(); 
		} catch (UnknownHostException e1) {
			e1.printStackTrace(); 
		}//end catch
		return ip;
	}//getIp
	
//	public void sendImage() throws UnknownHostException, IOException {
//		//1.서버소켓 열기
//		Socket client=null;
//		DataOutputStream dos=null;
//		DataInputStream dis=null;
//		FileOutputStream fos=null;
//		
//		try {
//			//2.소켓 생성 : 각 컴퓨터 ip를 서버로 연결
//			client=new Socket(getIp(),5000); /////////////
//			//데이터를 주고받을 스트림 연결
//			dos=new DataOutputStream(client.getOutputStream()); //보내기
//			dis=new DataInputStream(client.getInputStream()); //들이기
//			
//			//[ 경로 ] 모든 파일의 목록을 CSV만들기
//			File file=new File("C:/dev/workspace/Team22/src/user/images"); //파일 생성
//			File[] temp=file.listFiles(); //파일의 list를 배열에 담기
//			
//			StringBuilder csvFile=new StringBuilder();
//			for(int i=0;i<temp.length;i++) {
//				if(i!=0) {
//					csvFile.append(",");
//				}//end if
//				csvFile.append(temp[i].getName());
//			}//end for
//			
//			//서버로 파일리스트 CSV data 보내기 ////? 
//			dos.writeUTF(csvFile.toString()); //문자열을 스트림 기록
//			dos.flush(); //스트림의 내용을 목적지(소켓)로 분출
//			
//			//서버에서 없는 파일의 갯수를 보낸것을 받아 그 횟수만큼 반복시킴
//			int fileCnt=dis.readInt();
//			int readCnt=0;
//			String fileName="";
//			
//			byte[] readData=new byte[512]; //
//			int readSize=0;
//			
//			for(int i=0; i<fileCnt;i++) {
//	//			dos.wirteUTF(); //서버로 전달할 값?
//				//읽어들일 파일의 회수 받기
//				readCnt=dis.readInt();
//				//받아서 생성할 파일명 받기
//				fileName=dis.readUTF();
//				//파일생성
//				fos=new FileOutputStream("C:/dev/workspace/Team22/src/user/images/"+fileName);
//				
//				while(readCnt>0) {
//					readSize=dis.read(readData);
//					fos.write(readData,0,readSize); ////////?
//					readCnt--;
//				}//end while
//				fos.flush();
//				//14. thumbnail 파일 생성
//	//			ImageResize.resizeImage("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName, 100, 80);
//			}//end for
//		}finally {
//			if(dis!=null) {dis.close();}//end if
//			if(dos!=null) {dos.close();}//end if
//			if(client!=null) {dos.close();}//end if
//		}//end finally
//		
//	}//sendImage
	

	public static void main(String[] args) {
		
		RunPcUser rpu=new RunPcUser();
//		try {
//			pcu.sendImage();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}//end catch
		
		
//		new UserLogin();
	}//main
	
	
	
}//class
