package bowwow.haley.com.bowwowgo;

/**
 * Created by user on 2017-07-18.
 */


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * 오늘 날짜 저장하는 클래스 - 현재 시분초 기록 => 이 클래스에서 하기? 여기서 다 할까?
 */

public class TodayClass {

    // 고민...

    // 1. 기록시작 버튼을 누르면 현재 날짜(현재 시간&날짜 변수1) & 시 분 초 기록(시작 시간 변수1) -> 추후에 산책 기록 보여줄 때 출력
    // 2. 기록 일시 중지 버튼 누르면 중지 시분초(중지 시간 변수1) - 시작 시 분 초 => 결과에 저장(결과 변수1) // 여기서 저장했으니까 시작 & 중지 변수 초기화
    // 3. 다시 기록 시작 버튼 누르면 다시 누른 시 분 초 기록(시작 시간 변수 1)에 저장
    // 4. 기록 일시 중지 버튼 누르면 // 중지 시간변수 1에 저장 -> 결과 변수 = 결과변수 + (중지시간변수1-시작시간변수1)
    // 5. 완전 중지 버튼 누르면 최종 결과 변수 = 총 산책 시간


    // private Calendar calendar;
    private Date today;
    private SimpleDateFormat dateFormat;
  /*  private final int[] TIME_UNIT = { 3600, 60, 1 };
    private final String TIME_UNIT_NAME = ":";*/


    public String getToday() {

        //  calendar = Calendar.getInstance();
        today = new Date();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(today);

    }

    public String getTodayTime() {

        // calendar = Calendar.getInstance();
        today = new Date();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Log.v(TAG, "yyyy-MM-dd HH:mm:ss : " + dateFormat.format(today));
        return dateFormat.format(today);

    }
/*

    // 시간 차이를 구하기 위해 현재 시간을 1000분의 1초로 변환해서 반환하는 함수 -> 시간 기록 누를 때 이걸 저장
    public long getMillis() {

        calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 1000; // 현재 시간을 초로 반환

    }

    // 초로 변환한 시간을 24(시간) * 60(분) * 60(초) 단위로 바꿔주는 함수
    public String getTime(long millis) {

        String result = "";

        for(int i = 0; i < TIME_UNIT.length; ++i) {

            result += millis / TIME_UNIT[i] + TIME_UNIT_NAME;
            millis %= TIME_UNIT[i];
        }

        return result;
    }
*/


/*
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE); // == Calendar.DAY_OF_MONTH
    //  String strToday = year + "년 " + month + "월 " + day + "일";

    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    public String toAllString() {

        return year + "/" + month + "/" + day + " " +;
    }*/


}
