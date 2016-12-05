package NTP;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JESUS_Server {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	/* PORT number that the JESUS Server listen on */
	private static final int PORT = 1945;
	/* Current Login ID list */
	private static HashSet<String> IDs = new HashSet<String>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		}
		finally {
			listener.close();
		}
	}
	
	private static class Handler extends Thread {
		
		private String id;
		private Socket socket;
		private BufferedReader in;
	    private PrintWriter out;
	    private ArrayList<Integer> answerList = new ArrayList<Integer>();
	    
		public Handler(Socket socket) {
			this.socket = socket;
		}
		
		public void run () {
			try {
				 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		         out = new PrintWriter(socket.getOutputStream(), true);
		         
		         while (true) {
		        	 int valid;
		        	 String inMsg = in.readLine();
		        	   /* Log-In */
		        	 if (inMsg.startsWith("LOGIN")) {
		        		String[] split = inMsg.split(" ");
		        		valid =  loginCheck(split[1], split[2]);
		        		if (valid >= -1)
		        			id = split[1];
		        		out.println("" + valid);
		        	 }/* Sign Up */
		        	 else if (inMsg.startsWith("SIGNUP")) {
		        		System.out.println(inMsg);
		        		String[] split = inMsg.split(" ");
		        		if (signUpHandler(split[1], split[2])) {
		        			System.out.println("SUCCESS");
		        			out.println("SUCCESS");
		        		}
		        		else {
		        			out.println("ALREADY");
		        		}
		        	 }/* Give Questions Given Level */
		        	 else if (inMsg.startsWith("LEVELCHOICE")) {
		        		 String[] split = inMsg.split(" ");
		        		 ArrayList<String> rs = giveChoiceLevel(Integer.parseInt(split[1]));
		        		 
		        		 for (String e : rs) {
		        			 out.println(e);
		        		 }out.println("END");
		        	 }/* Give a Chosen Question */
		        	 else if (inMsg.startsWith("QCHOICE")) {
		        		 String[] split = inMsg.split(" ");
		        		 String answer = null;
		        		 ArrayList<String> rs = giveChoiceQuestion(Integer.parseInt(split[1]));
		        		 
		        		 for (String e : rs) {
		        			 out.println(e);
		        		 }out.println("END");
		        		 /* Setting for Feedback */
		        		 answer = takeAnswer(Integer.parseInt(split[1]));
		        		 split = answer.split(" ");
		        		 for (int i = 0; i < split.length; ++ i) {
		        			 answerList.add(Integer.parseInt(split[i]));
		        		 }
		        	 }/* Check Submitted Answer */
		        	 else if (inMsg.startsWith("CHECKANS")) {
		        		 String[] split = inMsg.split(" ");
		        		 
		        		 if (checkSubmitAnswer(Integer.parseInt(split[1]), split[2]))
		        			 out.println("Correnct Answer");
		        		 else
		        			 out.print("Wrong Answer");
		        	 }/* Real-Time Feedback */
		        	 else if (inMsg.startsWith("FEEDBACK")) {
		        		 
		        	 }
		         }
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (id != null) {
                    IDs.remove(id);
                }
				try {
                    socket.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
			}
		}
	}
	
	public static int loginCheck(String id, String password) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		/* Check if current input id is log-in or not */
        synchronized (id) {
            if (!IDs.contains(id)) {
                IDs.add(id);
            }
            else
                return -4;//Current Input ID Already log-in
        }
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			String jdbcUrl = "jdbc:mysql://localhost/JESUS";//사용하는 데이터베이스명을 포함한 url
			String userId = "root";//사용자계정
			String userPass = "1234";//사용자 패스워드

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);//Connection 객체를 얻어냄

			stmt = conn.createStatement();//Statement 객체를 얻어냄

			rs = stmt.executeQuery("select PASSWORD, STATE from client where ID = '" + id + "'");
			if( rs.next() ) {
					int state = rs.getInt("STATE");
					System.out.println(state);
					if (password.equals(rs.getString("PASSWORD"))) {
						return state;//Login Success Return client's State
					} return -3;//Wrong Password Input
			}
			else {
				return -2;//Wrong ID input
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			stmt.close();
			conn.close();
		}
		return 0;
	}
	
	public static boolean signUpHandler (String id, String password) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			String jdbcUrl = "jdbc:mysql://localhost/JESUS";//사용하는 데이터베이스명을 포함한 url
			String userId = "root";//사용자계정
			String userPass = "1234";//사용자 패스워드

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);//Connection 객체를 얻어냄

			stmt = conn.createStatement();//Statement 객체를 얻어냄
			
			rs = stmt.executeQuery("select ID from client where ID = '" + id + "'");
			if( rs.next() ) { 
				return false;
			}
			else {
				stmt.executeUpdate("insert into CLIENT values ('" + id + "', '" + password + "', -1)");
				return true;
			}
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			stmt.close();
			conn.close();
		}
		return false;
	}
	public static ArrayList<String> giveChoiceLevel (int level) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			ArrayList<String> result = new ArrayList<String>();
			String jdbcUrl = "jdbc:mysql://localhost/JESUS";//사용하는 데이터베이스명을 포함한 url
			String userId = "root";//사용자계정
			String userPass = "1234";//사용자 패스워드

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);//Connection 객체를 얻어냄

			stmt = conn.createStatement();//Statement 객체를 얻어냄
			System.out.println("퀴리 부분");
			System.out.println(level);
			rs = stmt.executeQuery("select Q_NO, TITLE from question where LEVEL = " + level);
			
			while (rs.next()) {
				System.out.println("문제 출력");
				result.add(rs.getInt("Q_NO") +  " " + rs.getString("TITLE"));
			}
			return result;
			
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			stmt.close();
			conn.close();
		}
		return null;
	}
	public static ArrayList<String> giveChoiceQuestion (int qNo) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			ArrayList<String> result = new ArrayList<String>();
			String jdbcUrl = "jdbc:mysql://localhost/JESUS";//사용하는 데이터베이스명을 포함한 url
			String userId = "root";//사용자계정
			String userPass = "1234";//사용자 패스워드

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);//Connection 객체를 얻어냄

			stmt = conn.createStatement();//Statement 객체를 얻어냄
			
			rs = stmt.executeQuery("select INS_NO, INS_CONTENT from INSTRUCTION where Q_NO = " + qNo);
			while (rs.next()) {
				System.out.println("문제 출력");
				result.add(rs.getInt("INS_NO") +  " " + rs.getString("INS_CONTENT"));
			}
			return result;
			
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			stmt.close();
			conn.close();
		}
		return null;
	}
	public static boolean checkSubmitAnswer (int q_no, String submitAns) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			String jdbcUrl = "jdbc:mysql://localhost/JESUS";//사용하는 데이터베이스명을 포함한 url
			String userId = "root";//사용자계정
			String userPass = "1234";//사용자 패스워드

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);//Connection 객체를 얻어냄

			stmt = conn.createStatement();//Statement 객체를 얻어냄
			rs = stmt.executeQuery("select ANSWER from question where Q_NO = " + q_no);
			
			if (submitAns.equals(rs.getString("ANSWER")))
				return true;
			else
				return false;
			
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			stmt.close();
			conn.close();
		}
		return false;
	}
	public static String takeAnswer (int qNo) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로딩: DriverManager에 등록
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
		}
		
		try {
			String jdbcUrl = "jdbc:mysql://localhost/JESUS";//사용하는 데이터베이스명을 포함한 url
			String userId = "root";//사용자계정
			String userPass = "1234";//사용자 패스워드

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);//Connection 객체를 얻어냄

			stmt = conn.createStatement();//Statement 객체를 얻어냄
			rs = stmt.executeQuery("select ANSWER from question where Q_NO = " + qNo);
			
			return rs.getString("ANSWER");
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			stmt.close();
			conn.close();
		}
		return null;
	}
	public static void RealTimeFEEDBACK (int insNO) {
		
	}
}
