package com.unithon.unithon_server.FCM;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FCMconnect {
    private static HttpURLConnection conn;

    public final static String AUTH_KEY_FCM = "AAAAVMzORNY:APA91bFuHOmHQ6vQrXbKOXxi1xZ-7TRqVO_milGin2Djyz_3zZUgq0hy53_bAu4TqPR5tTHn1RGzS18MvIG-LZ7fKmE3WqtonnP2v_FlP_LIBcy4XDw_f9GQ-4QpQM8emVNZCStKRG1RTDmlYOXDh0CqroBJeP4X4g";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public static HttpURLConnection connect() throws Exception{
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url;
        try {
            url = new URL(FMCurl);
            conn = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }

}