package admin.fileServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileHelper extends Thread {

//	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private FileInputStream fis;
	private List<String> listSendFile;//접속자에게 보낼 파일
	private File temp1;

	public FileHelper(Socket client) throws IOException{
//		this.client = client;
		
		//4. 데이터를 주고 받을 스트림 연결
		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());
		
		String temp = "";
		File[] serverFile = null;
		
		//6. 클라이언트가 보내오는 파일의 목록 받기
//		String temp = "ad.png,bob.png,default.gif,m1_f1.png,maekjju.jpg,rs_bob.png,rs_m1_f1.png,rs_maekjju.jpg,rs_강사님 이게아니야!!2.png,rs_날두하고싶다.jpg,rs_우왕ㅋ.jpg,강사님 이게아니야!!2.png,날두하고싶다.jpg,우왕ㅋ.jpg";
		temp = dis.readUTF();//파일 목록 받기 
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img

		temp1 = new File("C:/dev/pcbang/admin/img/item");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();
		for (int i = 0; i < serverFile.length; i++) {
			if (!temp.contains(serverFile[i].getName()) 
					&& !serverFile[i].getName().startsWith("rs_")) {
				listSendFile.add(serverFile[i].getName());
			} // end if
		} // end for
//		System.out.println("없는 파일: "+listSendFile);
		
		//7. 전송할 파일의 갯수 보내기(클라리언트는 이 횟수로 반복시켜 읽는다.)
		dos.writeInt(listSendFile.size());
		dos.flush();
		
	}//FileHelper
	
	public void run() {
		byte[] readData = new byte[120];//파일에서 읽어들인 내용을 임시로 저장
		long sendCnt = 0;//전송할 readData의 갯수(파일을 몇 번 보낼 지 주고 받을 사인)
		int readSize = 0;//읽어들인 배열의 방의 갯수(크기)
		
		try {
			// 8. 전송할 파일 정보 얻기 (FileStream이 필요하다)
			for (int i = 0; i < listSendFile.size(); i++) {
				sendCnt = 0;
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));

				while ((readSize = fis.read(readData)) != -1) {// 512개의 방 중에 몇 개가 채워졌는가
					sendCnt++;// 전송할 횟 수(클라이언트에게 보낼 파일이 몇 개인지)
				} // end while
					// 포인터를 앞으로 보내려면 끊고 다시 붙여야 한다.
				fis.close();
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
				dis.readUTF();// 클라이언트로 부터 값이 들어 오면

				// 8. 전송할 횟수를 클라이언트에게 보낸다.
				System.out.println("전송할 횟수:"+sendCnt);
				dos.writeLong(sendCnt);
				dos.flush();
				// 11. 전송할 파일명을 보낸다.
				System.out.println("전송할 파일명:"+listSendFile.get(i));
				dos.writeUTF(listSendFile.get(i));
				dos.flush();

//			//13. 파일에서 읽어들여 클라이언트에 보낸다.
				while (sendCnt > 0) {
					readSize = fis.read(readData);
					dos.write(readData, 0, readSize);
					sendCnt--;
				} // end while
				dos.flush();

			} // end for
		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//end catch
	}//run
		
}//class