package admin.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import admin.DAO.AdminDAO;
import admin.view.ManageShop;

public class ManageShopServer extends Thread {
	
	private ManageShop ms;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	private Thread thread;
	
//	private Map<Integer, PcStateReceiver> pcStateReciverMap = new HashMap<Integer, PcStateReceiver>(27);
	
	public ManageShopServer(ManageShop ms) {
		this.ms = ms;
		serverSocket = null;
		socket = null;

	}//ManageShop

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(9000);
			System.out.println("매장 관리자 서버가 열렸습니다.");
			
			String ip = null;
			AdminDAO aDAO = AdminDAO.getInstance();
			int pcNum = 0;
//			ClientReceiver clientReceiver = null;
			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress().getAddress());
				ip = socket.getInetAddress().getHostAddress();
				System.out.println(ip);
				//ip에 해당하는 PC번호를 DB에서 조회한 다음
				pcNum = aDAO.selectPCNum(ip);
//				pcNum = aDAO.selectPCNum("211.63.89.133");
				System.out.println(pcNum);
				//PC번호에 해당하는 PCState를 map에서 불러와 소켓을 주고
				ms.getPcStateMap().get(pcNum).setSocket(socket);
//				ms.getPcStateEvtMap().get(pcNum).setSocket(socket);
//				pcStateReciverMap.put(pcNum, new PcStateReceiver(socket));
				//thread를 실행한다.
//				thread = new Thread(ms.getPcStateMap().get(pcNum));
//				Thread thread = new Thread(ms.getPcStateEvtMap().get(pcNum));
//				thread.start();
//				pcStateReciverMap.get(pcNum).start();
//				clientReceiver = new ClientReceiver(socket);
//				map.put(ip, clientReceiver);
//				clientReceiver.start();
			}//end while
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException ioe1) {
			ioe1.printStackTrace();
			try {
				if (socket != null) { socket.close(); }//end if
				if (serverSocket != null) { serverSocket.close(); }//end if
			} catch (IOException ioe2) {
				ioe2.printStackTrace();
			}//end catch
		}//end catch
	}//run

	public Thread getThread() {
		return thread;
	}
	
}//class
