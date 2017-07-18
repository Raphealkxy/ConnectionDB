package com.timmy.main;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.timmy.util.DBUtil;

public class main {

	public static void main(String[] args) {
		try {
			 ServerSocket serverSocket=new ServerSocket(8888);//绑定端口号
	            System.out.println("***服务器已经打开***");//提示信息
	            Socket socket=null;//
	            Integer count=0;
	            socket=serverSocket.accept();//这个socket用来读取接受的数据
	     
	            DataInputStream inputStream = null;  
                DataOutputStream outputStream = null;  
                String strInputstream =""; 
                
                inputStream =new DataInputStream(socket.getInputStream());                   
                ByteArrayOutputStream baos = new ByteArrayOutputStream();  
                byte[] by = new byte[2048];  
                int n;  
                while((n=inputStream.read(by))!=-1){  
                    baos.write(by,0,n);           
                }  
                strInputstream = new String(baos.toByteArray()); 
                socket.shutdownInput();  
                baos.close();  

	    		
	    			JSONObject jsonObject=JSONObject.fromObject(strInputstream);
	    			String username=jsonObject.optString("username");
	    			String password=jsonObject.optString("password");
	    			System.out.println(jsonObject.toString());
	    		
	    		

	    	
			Connection connection=DBUtil.connect();
			String sql="insert into usertable values(?,?)";
			
			PreparedStatement ptmt=connection.prepareStatement(sql);
			ptmt.setString(1, username);
			ptmt.setString(2, password);
            ptmt.execute();
            
            
            //服务器响应客户端
            int port =socket.getPort();
            System.out.println(port);
    	 
    			Socket socket1=new Socket("localhost",port);
    			OutputStream os = socket1.getOutputStream();
    			PrintWriter pw =new PrintWriter(os);
    			pw.write("数据已录入数据库");
    			pw.flush();
    			socket1.shutdownOutput();
    			pw.close();
    			os.close();
    			socket1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

}
	}
