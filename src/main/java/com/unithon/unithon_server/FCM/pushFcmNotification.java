package com.unithon.unithon_server.FCM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class pushFcmNotification extends Thread{
	private   HttpURLConnection conn;
	public final static String AUTH_KEY_FCM = "AAAAVMzORNY:APA91bFuHOmHQ6vQrXbKOXxi1xZ-7TRqVO_milGin2Djyz_3zZUgq0hy53_bAu4TqPR5tTHn1RGzS18MvIG-LZ7fKmE3WqtonnP2v_FlP_LIBcy4XDw_f9GQ-4QpQM8emVNZCStKRG1RTDmlYOXDh0CqroBJeP4X4g";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	private String message;
	private String token;
	
	
	 public pushFcmNotification(String message ,String token){
		this.message = message;
		this.token = token;
		
	 }  

	 public void run(){
		 try {
			pushFCMNotification(message,token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	public  void pushFCMNotification(String message ,String token) throws Exception {
//		String authKey = AUTH_KEY_FCM;
        

		conn = FCMconnect.connect();
	    conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
        conn.setRequestProperty("Content-Type", "application/json");
        	
        JsonObject json = new JsonObject();
        JsonObject info = new JsonObject();
        JsonObject jsonobj = new JsonObject();
        JsonArray jsonarr = new JsonArray();
        Gson gson = new Gson();
      
        info.addProperty("body", message); // Notification body

        json.add("data", info);
        json.addProperty("to", token); // deviceID
//        json.addProperty("to", userDeviceIdKey.trim()); // deviceID
         try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())){
//혹시나 한글 깨짐이 발생하면 
//try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다. 	
       		 wr.write(json.toString());
        	 wr.flush();
            
        }catch(Exception e){
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        
//        conn.disconnect();
//        System.out.println(output);
   	}
	

}