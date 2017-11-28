package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class ServerModel {
	
	private final String USER_INFO_DOCUMENT = "user_pass.txt";
	/* 1. Starting up: read users list. 
	 * User List format: user_name pss 
	 * 2. Listens on a given port, wait */
	ServerModel(){
		HashMap<String, String> userInfoMap = new HashMap<String, String>();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(USER_INFO_DOCUMENT));
			String line = br.readLine();
			while(line != null && !line.isEmpty()){
				Scanner s = new Scanner(line);
				String userName = s.next();
				String psw = s.next();
				userInfoMap.put(userName, psw);
				s.close();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		finally {
			if(br != null){
				try{
					br.close();
				} catch(IOException e){
					throw new RuntimeException(e);
				}
			}
		}
	}

}
