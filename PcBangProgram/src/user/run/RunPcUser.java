package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import kr.co.sist.util.img.ImageResize;
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
	
	public void sendImage() throws UnknownHostException, IOException {
		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		
		try {
			//2. 소켓생성 (소켓을 열어서 서버에 연결) 
			client = new Socket("localhost", 5000);
//			client = new Socket("211.63.89.130", 5000);
//			client = new Socket("211.63.89.132", 5000);
//			client = new Socket("211.63.89.133", 5000);
//			client = new Socket("211.63.89.134", 5000);
			//4. 데이터를 주고 받을 스트림 연결
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());


			// C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img
			// 모든 파일의 목록을 CSV로 만들기
			File file = new File("C:/Users/owner/git/prj2/PcBangProgram/src/user/image");
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

			// 5. 서버로 파일리스트 CSV Data 보내기
			dos.writeUTF(csvImgFile.toString());//문자열을 스트림 기록
			dos.flush();//스트림의 내용을 목적지(소켓)으로 분출
			
			//9. 서버에서 없는 파일의 갯수를 보낸 것을 받아 그 횟수만큼 반복시킨다. 
			int fileCnt = dis.readInt();

			int readCnt = 0;
			String recieveFileName = "";
			byte[] readData= new byte[512];
			int readSize = 0;
			for (int i = 0; i < fileCnt; i++) {
				
//				
				//클러스터 bomb
				//플래그를 주고 받음으로서 서버와 클라이언트가 헛돌지 않게 해줍니다. 
				dos.writeUTF("Y");//블로킹 메서드
				
				//code block: 서버에서 응답이 오기 전까지 대기
				//10. 읽어들일 파일의 횟수 받기
				readCnt = dis.readInt();
//				System.out.println("클라이언트: " + readCnt);
				//12. 받아서 생성할 파일명을 받기 
				recieveFileName = dis.readUTF();
//				System.out.println("클라이언트: " + recieveFileName);
				//14. 파일생성(같은 이름이 있다면 덮어 씌우겠다.)
				fos = new FileOutputStream("C:/Users/owner/git/prj2/PcBangProgram/src/user/image/"+recieveFileName);
				while(readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0, readSize);
					readCnt--;
				}//end while
				fos.flush();
				fos.close();
				//14.thumbnail 파일 생성
				ImageResize.resizeImage("C:/Users/owner/git/prj2/PcBangProgram/src/user/image/"+recieveFileName, 100, 80);
				

			}//end for
			
		} finally {
			if(dis != null) { dis.close(); }//end if
			if(dos != null) { dos.close(); }//end if
			if(client != null) { client.close(); }//end if
		}//end finally
		
	}//sendImage
	

	public static void main(String[] args) {
		RunPcUser rpu=new RunPcUser();
		try {
			rpu.sendImage();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		
		
	}//main
	
	
	
}//class

