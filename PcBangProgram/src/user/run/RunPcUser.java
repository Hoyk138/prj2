package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import kr.co.sist.util.img.ImageResize;
import user.view.UserLogin;

public class RunPcUser {
	
	public RunPcUser(String adminIP) {
		new UserLogin(adminIP);
	}//RunPcUser
	
//	public String getIp() {
//		InetAddress local; 
//		String ip=null;
//		
//		try { 
//			local = InetAddress.getLocalHost(); 
//			ip = local.getHostAddress(); 
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace(); 
//		}//end catch
//		return ip;
//	}//getIp
	
	public void sendImage(String adminIP) throws UnknownHostException, IOException {
		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		 
		try {
			//2. 소켓생성 (소켓을 열어서 서버에 연결) 
//			client = new Socket("localhost", 5000);
//			client = new Socket("211.63.89.130", 5000);
//			client = new Socket("211.63.89.132", 5000);
//			client = new Socket("211.63.89.133", 5000);
//			client = new Socket("211.63.89.134", 5000);
//			client = new Socket("211.63.89.142", 5000);
			System.out.println(adminIP);
			client = new Socket(adminIP, 5000);
			//4. 데이터를 주고 받을 스트림 연결
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());


			// c:/dev/pcbang/user/img/item
			// 모든 파일의 목록을 CSV로 만들기
			File file = new File("c:/dev/pcbang/user/img/item");
			
			if(!file.exists()) {
				file.mkdir();
			}//end if
			
			File[] fileArr = file.listFiles();
			StringBuilder csvImgFile = new StringBuilder();
			for (int i = 0; i < fileArr.length; i++) {
				if (!fileArr[i].getName().startsWith("rs_")) {
					if (i != 0) {
						csvImgFile.append(",");
					} // end if
					csvImgFile.append(fileArr[i].getName());
//			System.out.println(fileArr[i].getName());
				} // end if
			} // end for
//		System.out.println(csvImgFile.toString());

//		String[] temp = file.list();
//		StringBuilder csvImgFile2 = new StringBuilder();
//		for (String string : temp) {
////			System.out.println(string);
//			csvImgFile2.append(string).append(",");
//		}//end for
//		System.out.println(csvImgFile2.toString());
			
			System.out.println("서버로 보낼 파일 리스트: "+csvImgFile.toString());

			// 5. 서버로 파일리스트 CSV Data 보내기
			dos.writeUTF(csvImgFile.toString());//문자열을 스트림 기록
			dos.flush();//스트림의 내용을 목적지(소켓)으로 분출
			
			//9. 서버에서 없는 파일의 갯수를 보낸 것을 받아 그 횟수만큼 반복시킨다. 
			int fileCnt = dis.readInt();
			System.out.println("서버에서 알려 준 '받아야 할 파일 갯수': "+fileCnt);

			long readCnt = 0;
			String recieveFileName = "";
			byte[] readData= new byte[120];
			int readSize = 0;
			for (int i = 0; i < fileCnt; i++) {
				System.out.println("----------"); 
				readCnt = 0;
				System.out.println("클라이언트(파일의 횟수 초기화): " + readCnt);
				//클러스터 bomb
				//플래그를 주고 받음으로서 서버와 클라이언트가 헛돌지 않게 해줍니다. 
				dos.writeUTF("Y");//블로킹 메서드
				dos.flush();
				
				//code block: 서버에서 응답이 오기 전까지 대기
				//10. 읽어들일 파일의 횟수 받기
				readCnt = dis.readLong();
				System.out.println("클라이언트(파일의 횟수): " + readCnt);
				//12. 받아서 생성할 파일명을 받기 
				recieveFileName = dis.readUTF();
				System.out.println("클라이언트(파일명): " + recieveFileName);
				//14. 파일생성(같은 이름이 있다면 덮어 씌우겠다.)
				fos = new FileOutputStream("c:/dev/pcbang/user/img/item/"+recieveFileName);
				while(readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0, readSize);
					readCnt--;
				}//end while
				fos.flush();
				fos.close();
				//14.thumbnail 파일 생성
				System.out.println("thumbnail 파일 생성:"+recieveFileName); 
				try {
					ImageResize.resizeImage("c:/dev/pcbang/user/img/item/"+recieveFileName, 160, 130);
				} catch (NullPointerException npe) {
					npe.printStackTrace();
					System.out.println(recieveFileName);
				}
				System.out.println("----------"); 
				
			}//end for
			
		} finally {
			if(dis != null) { dis.close(); }//end if
			if(dos != null) { dos.close(); }//end if
			if(client != null) { client.close(); }//end if
		}//end finally
		
	}//sendImage
	

	public static void main(String[] args) {
		boolean flag = false;
		String adminIP = "";
//		String[] options = {"예","아니요"};
		RunPcUser rpu = null;
		do {
			//adminIP = JOptionPane.showInputDialog("관리자 PC의 IP를 입력 해주세요\n예)127.0.01");
			adminIP = JOptionPane.showInputDialog("관리자 PC의 IP를 입력 해주세요\n예)211.63.89.130");
			if (adminIP==null) {
				adminIP="";
			}//end if
			//switch (JOptionPane.showOptionDialog(null, adminIP+"로 연결 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
			switch (JOptionPane.showConfirmDialog(null, "입력하신 IP ["+adminIP+"]로 연결 하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				rpu = new RunPcUser(adminIP);
				flag = true;
				try {
					rpu.sendImage(adminIP);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
				break;
			case JOptionPane.CANCEL_OPTION:
				flag = true;
				break;
			case JOptionPane.CLOSED_OPTION:
				flag = true;
				break;
			}//switch case
			
		} while (!flag);
		
	}//main
	
}//class

