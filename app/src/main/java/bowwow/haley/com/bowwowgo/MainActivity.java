package bowwow.haley.com.bowwowgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView todayText;
    private String strToday;
    private TodayClass today;
    private final String TAG = "**MainActivity**";


    // LocalDate 사용하고 싶은뎅.. -> api 26부터... 그냥 Calandar 사용


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayText = (TextView)findViewById(R.id.today_tv);

        /*today = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        strToday = dateFormat.format(today);*/

        today = new TodayClass();
        strToday = today.getToday();

        todayText.setText(strToday);


    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.go_walk_bt:
                startActivity(new Intent(this, RecoderActivity.class));
                break;
        }
    }
}
