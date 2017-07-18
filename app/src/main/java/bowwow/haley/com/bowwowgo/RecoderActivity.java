package bowwow.haley.com.bowwowgo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecoderActivity extends Activity {

    // 핸들러를 사용해서 스톱워치 구현하기 -> 바보네 ㅠㅠ

    private final String TAG = "**RecoderActivity**";

    // 오늘 날짜 텍뷰
    private TextView todayTv;

    // 기록 시작 버튼
    private ImageButton startBt;

    // 기록 중지 버튼
    private ImageButton stopBt;

    // TimeClass -> 오늘 날짜 출력
    private TodayClass today;

    // 타임 출력하는 텍스트 뷰
    private TextView timeRecoder;

    // 스톱워치의 상태를 위한 상수
    private final static int IDLE = 0;
    private final static int RUNNING = 1;
    private final static int PAUSE = 2;

    private int mStatus = 0; // 초기 상태는 IDLE
    private long mBaseTime;
    private long mPauseTime;
    //  int mSplitCount;

    private String resultTime;
    private String startDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoder);

        Log.v(TAG, "onCreate()");

        this.todayTv = (TextView)findViewById(R.id.today_tv);
        this.startBt = (ImageButton)findViewById(R.id.start_bt);
        this.stopBt = (ImageButton)findViewById(R.id.stop_bt);
        this.timeRecoder = (TextView)findViewById(R.id.time_recoder);

        // 오늘 날짜 셋팅
        today = new TodayClass();
        todayTv.setText(today.getToday());

    } // end of onCreat();

    // 스톱워치를 위해 핸들러 생성
    Handler mTimer = new Handler() {


        // 핸들러 - 기본적으로 handleMessage에서 처리
        @Override
        public void handleMessage(Message msg) {

            Log.v(TAG, "handleMessage(Message msg)");

            // 텍스트 뷰 수정
            timeRecoder.setText(getMyTime());

            // 메세지를 보내기
            mTimer.sendEmptyMessage(0); // 0 은 메세지 구분을 위해


        }


    };// end of Handler mTimer = new Handler()


    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy()");

        mTimer.removeMessages(0); // 메세지를 지워서 메모리릭 방지
        super.onDestroy();
    }

    public void onClick(View v) {
        switch(v.getId()) {
            // 기록 최초 시작 버튼 클릭
            case R.id.start_bt: {
                Log.v(TAG, "click start button");
                switch(mStatus) {
                    // 초기 상태
                    case IDLE: {

                        // 현재 값 세팅
                        mBaseTime = SystemClock.elapsedRealtime();
                        Log.v(TAG, "mBaseTime : " + mBaseTime);
                        // 핸들러로 메세지 보냄 & 호출
                        mTimer.sendEmptyMessage(0);
                        // start 버튼을 일시정지 표시로 변경
                        startBt.setImageResource(R.drawable.ic_pause_black_24dp);
                        Log.v(TAG, "startBt -> pauseBt");
                        // 상태를 RUNNING으로 변경
                        mStatus = RUNNING;

                        // 최초 시작 시간 기록
                        today = new TodayClass();
                        startDate = today.getTodayTime();

                        Log.v(TAG, "startDate : " + startDate);

                        break;
                    }
                    // 버튼이 실행 상태 이면
                    case RUNNING: {
                        // 핸들러 메세지 삭제 -> 핸들러 중지
                        mTimer.removeMessages(0);

                        // 멈춘 시간 파악
                        mPauseTime = SystemClock.elapsedRealtime();
                        Log.v(TAG, "mPauseTime : " + mPauseTime);

                        // 여기에 뭔가 애니메이션을 줄 수는 없을까??


                        // 현재 일시 중지 상태인 버튼 이미지 -> 스타트로 변경
                        startBt.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        // 현재 gone 상태인 완전 정지 버튼 visible 상태로 변경
                        stopBt.setVisibility(View.VISIBLE);

                        // 상태를 멈춤 상태로 표시
                        mStatus = PAUSE;

                        break;
                    }
                    // 상태가 멈춤 상태 일때
                    case PAUSE: {

                        // 현재 값 가져옴
                        long now = SystemClock.elapsedRealtime();
                        Log.v(TAG, "case Pause, now : " + now);

                        // 베이스타임 = 베이스타임 + (now - mPauseTime)
                        // 잠깐 스톱워치를 멈췄다가 다시 시작하면 기분점이 변하니까
                        mBaseTime += (now - mPauseTime);
                        Log.v(TAG, "case Pause, mBaseTime : " + mBaseTime);

                        mTimer.sendEmptyMessage(0);

                        // 멈춤 상태일 때 재시작 버튼을 누르면
                        // startBt -> 그림 pause로 변경
                        startBt.setImageResource(R.drawable.ic_pause_black_24dp);
                        // stopBt -> gone 상태로 변경
                        stopBt.setVisibility(View.GONE);

                        mStatus = RUNNING;
                        break;

                    }
                } // switch(mStatus)
                break;
            } // case R.id.startBt :
            // 완전 정지 버튼 클릭
            case R.id.stop_bt: {
                Log.v(TAG, "click stop button");
                switch(mStatus) {

                    case PAUSE: {

                        // 얘는 한가지 상태밖에 없음 PAUSE 상태에서 누르는 것 밖에 없음
                        // 정지 버튼 클릭하면 -> 산책 기록하는 페이지로 이동

                        // timerecoder 값 가져오기
                        resultTime = timeRecoder.getText().toString();
                        Log.v(TAG, "resultTime : " + resultTime);
                        Log.v(TAG, "startDate : " + startDate);

                        // 핸들러 삭제
                        mTimer.removeMessages(0);

                      /*  try {

                            Thread.sleep(1000);

                        } catch(Exception e) {

                            e.printStackTrace();


                        }*/
                        // stop 버튼 gone으로 변경
                        stopBt.setVisibility(View.GONE);

                        mStatus = IDLE;

                        // 인텐트로 최종 결과 값 보내기
                        ////////////////////////// 시작 날짜 & 기록 시간 값 다음 페이지로 이동하기


                        // 잠깐... 기록 페이지로 갔다가 다시 시간 재고 싶어서 뒤로 돌아오면 어떻게 되는거징
                        // 다시 처음부터 기록이 되는건데...

                        break;
                    } //  case PAUSE :

                } // switch(mStatus)
                break;
            } // case R.id.stop_bt:


        } //  switch(v.getId())


    } // end of onClick

    private String getMyTime() {
        long now = SystemClock.elapsedRealtime();
        long time = now - mBaseTime; // 현재 시간과 지난 시간을 빼서 시간 구함
        // 포맷을 바꿔서 리턴

        long hour = time / 1000 / 60;
        long minute = (time / 1000) % 60;
        long sec = (time % 1000) / 10;


        String strTime =
                String.format("%02d:%02d:%02d", hour, minute, sec);

        return strTime;
    }

    /*
    private TextView timeRecode;
    private TextView todayTv;
    private TimeClass today;
    private long startTime;
    private long pauseTime;
    private long resultTime;
    private String strResultTime;
    private final String TAG = "**RecoderActivity**";
    private ImageButton startBt;
    private ImageButton pauseBt;
    private LinearLayout layoutForBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate()");
        setContentView(R.layout.activity_recoder);
        timeRecode = (TextView)findViewById(R.id.time_recode);
        todayTv = (TextView)findViewById(R.id.today_tv);
        startBt = (ImageButton)findViewById(R.id.start_bt);
        layoutForBt = (LinearLayout)findViewById(R.id.layout_for_bt);
        pauseBt = (ImageButton)findViewById(R.id.pause_bt);

        // 오늘 날짜로 지정
        today = new TimeClass();
        todayTv.setText(today.getToday());


    }


    // 1. 기록시작 버튼을 누르면 현재 날짜(현재 시간&날짜 변수1) & 시 분 초 기록(시작 시간 변수1) -> 추후에 산책 기록 보여줄 때 출력
    // 2. 기록 일시 중지 버튼 누르면 중지 시분초(중지 시간 변수1) - 시작 시 분 초 => 결과에 저장(결과 변수1) // 여기서 저장했으니까 시작 & 중지 변수 초기화
    // 3. 다시 기록 시작 버튼 누르면 다시 누른 시 분 초 기록(시작 시간 변수 1)에 저장
    // 4. 기록 일시 중지 버튼 누르면 // 중지 시간변수 1에 저장 -> 결과 변수 = 결과변수 + (중지시간변수1-시작시간변수1)
    // 5. 완전 중지 버튼 누르면 최종 결과 변수 = 총 산책 시간

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start_bt: {
                Log.v(TAG, "click Start button");
                startTime = today.getMillis();
                Log.v(TAG, "startTime : " + startTime);
                startBt.setVisibility(View.INVISIBLE);
                // layoutForBt.setVisibility(View.VISIBLE);
                pauseBt.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.pause_bt: {
                Log.v(TAG, "click Pause button");
                pauseTime = today.getMillis();
                Log.v(TAG, "pauseTime : " + pauseTime);
                resultTime = resultTime + (pauseTime - startTime);
                Log.v(TAG, "temp resultTime : " + resultTime);
                Log.v(TAG, "String Time : " + today.getTime(resultTime));
                pauseBt.setVisibility(View.INVISIBLE);
                layoutForBt.setVisibility(View.VISIBLE);
// 뭔가 아니네...스레드 이용해서 올려야 할까????
                break;
            }
            case R.id.restart_bt: {
                Log.v(TAG, "click Restart Button");

                break;
            }
            case R.id.stop_bt: {
                Log.v(TAG, "click Stop button");


                break;
            }
        }
    }*/
}
