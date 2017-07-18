package bowwow.haley.com.bowwowgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileActivity extends Activity {

    private ArrayAdapter<Integer> monthAdapter;
    private Spinner monthSpinner;

    private String[] strMonthArray;
    private Integer[] intMonthArray;

    private ArrayAdapter<Integer> dayAdapter;
    private Spinner daySpinner;

    private String[] strDayArray;
    private Integer[] intDayArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 월
        monthSpinner = (Spinner)findViewById(R.id.birth_month_sp);
        // integer_array는 바로 arrayadapter에 안들어가는 듯
        strMonthArray = getResources().getStringArray(R.array.month_array);
        intMonthArray = new Integer[strMonthArray.length];

        for(int i = 0; i < strMonthArray.length; ++i) {
            intMonthArray[i] = Integer.parseInt(strMonthArray[i]);
        }

        monthAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                intMonthArray);

        //monthSpinner.setPrompt("월");
        monthSpinner.setAdapter(monthAdapter);

        // 일
        daySpinner = (Spinner)findViewById(R.id.birth_day_sp);
        strDayArray = getResources().getStringArray(R.array.day_array);
        intDayArray = new Integer[strDayArray.length];

        for(int i = 0; i < strDayArray.length; ++i) {
            intDayArray[i] = Integer.parseInt(strDayArray[i]);
        }

        dayAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                intDayArray);
        daySpinner.setAdapter(dayAdapter);


       /* intArray = getResources().getIntArray(R.array.month_array);

        Log.v("PrfileActiviy", intArray[1] + "");*/

        /*
        monthSpinner = (Spinner)findViewById(R.id.birth_month_sp);

        *//*monthAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.month_array,
                android.R.layout.simple_spinner_dropdown_item);
        *//*
        intArray = getResources().getIntArray(R.array.month_array);
        monthAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, intArray);


        monthSpinner.setAdapter(monthAdapter);

        daySpinner = (Spinner)findViewById(R.id.birth_day_sp);*/
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.save_profile_bt:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
