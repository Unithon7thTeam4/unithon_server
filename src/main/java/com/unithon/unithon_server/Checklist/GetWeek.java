package com.unithon.unithon_server.Checklist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetWeek {

    public String[] weekCalendar(String yyyymmdd) throws Exception{

        Calendar cal = Calendar.getInstance();
        int toYear = 0;
        int toMonth = 0;
        int toDay = 0;
        if(yyyymmdd == null || yyyymmdd.equals("")){   //파라메타값이 없을경우 오늘날짜
            toYear = cal.get(cal.YEAR);
            toMonth = cal.get(cal.MONTH)+1;
            toDay = cal.get(cal.DAY_OF_MONTH);

            int yoil = cal.get(cal.DAY_OF_WEEK); //요일나오게하기(숫자로)

            if(yoil != 1){   //해당요일이 일요일이 아닌경우
                yoil = yoil-2;
            }else{           //해당요일이 일요일인경우
                yoil = 7;
            }
            cal.set(toYear, toMonth-1, toDay-yoil);  //해당주월요일로 세팅
        }else{
            String d[] = yyyymmdd.split("-");
            int yy =Integer.parseInt(d[0]);
            int mm =Integer.parseInt(d[1])-1;
            int dd =Integer.parseInt(d[2]);
            cal.set(yy, mm,dd);
        }
        String[] arrYMD = new String[7];

        int inYear = cal.get(cal.YEAR);
        int inMonth = cal.get(cal.MONTH);
        int inDay = cal.get(cal.DAY_OF_MONTH);
        int yoil = cal.get(cal.DAY_OF_WEEK); //요일나오게하기(숫자로)
        if(yoil != 1){   //해당요일이 일요일이 아닌경우
            yoil = yoil-2;
        }else{           //해당요일이 일요일인경우
            yoil = 7;
        }
        inDay = inDay-yoil;
        for(int i = 0; i < 7;i++){
            cal.set(inYear, inMonth, inDay+i-1);  //
            String y = Integer.toString(cal.get(cal.YEAR));
            String m = Integer.toString(cal.get(cal.MONTH)+1);
            String d = Integer.toString(cal.get(cal.DAY_OF_MONTH));
            if(m.length() == 1) m = "0" + m;
            if(d.length() == 1) d = "0" + d;

            arrYMD[i] = y+"-"+m +"-"+d;

        }

        return arrYMD;
    }


    public String getYesterday(String yyyymmdd) throws Exception {

        String thisDay = yyyymmdd;
        //하루 증가된 날짜 계산을 위해서
        String yesterday = thisDay.replaceAll("-","");
        int thisDayMoreInt = Integer.parseInt(yesterday);
        thisDayMoreInt = thisDayMoreInt-1;
        yesterday = String.valueOf(thisDayMoreInt);

        SimpleDateFormat sdfmt = new SimpleDateFormat("yyyyMMdd");
        Date date = sdfmt.parse(yesterday);

        yesterday = new java.text.SimpleDateFormat ("yyyy-MM-dd").format(date);

        return yesterday;

    }
}
