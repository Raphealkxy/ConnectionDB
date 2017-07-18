package com.timmy.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("localhost",8888);//客户端socket，用来发送数据。
		OutputStream outputStream=socket.getOutputStream();
		//PrintWriter pWriter=new PrintWriter(outputStream);
		//创建json格式数据
		JSONObject jsonObject=new JSONObject();
		//填充json数据内容
		jsonObject.put("username", "kongxiangyue");
		jsonObject.put("password", "123456");
		//json转化成字符串
		String string=jsonObject.toString();
		//字符串转化byte数组
		byte[] bytes=string.getBytes();
		//发送json数据
		//pWriter.write(bytes);
		outputStream.write(bytes);
		outputStream.flush();//刷新，向服务器发送信息；
		//pWriter.
		//pWriter.flush();
		socket.shutdownOutput();
		
		//pWriter.close();
		outputStream.close();
		socket.close();
		
		
	    int port =socket.getLocalPort();
	    System.out.println(port);
	    
        ServerSocket serverSocket=new ServerSocket(port);
  
        Socket socket1=serverSocket.accept();
  
        InputStream is=socket1.getInputStream();
        InputStreamReader inr =new InputStreamReader(is);
        BufferedReader bReader=new BufferedReader(inr);
        String data = bReader.readLine();
        while(data!=null)
        {
        	System.out.println("数据是："+data);
        	data=bReader.readLine();
        }
        
        socket1.shutdownInput();
        bReader.close();
        inr.close();
        is.close();
        socket1.close();
	}
	
	}


