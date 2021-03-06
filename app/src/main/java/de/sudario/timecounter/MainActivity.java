package de.sudario.timecounter;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Date tStartTime = null;
    private Date tEndTime = null;
    private long lTimeCount = 0;

    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();
    TextView startTime;
    TextView endTime;
    TextView finishtime;
    TextView maxFinishTime;
    //ToDo Kapselung:
    //calcFinishDate
    //calcMaxFinishDate
    //SetTextview
    //ToDo Berechnung des Zeitsaldos
    private void calcFinishDate(String time) {
        SimpleDateFormat df = new java.text.SimpleDateFormat("HH:mm");
        try {

            Date tempTime = df.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tempTime);
            calendar.add(Calendar.HOUR_OF_DAY, 8);
            calendar.add(Calendar.MINUTE, 50);
            calendar.add(Calendar.MINUTE, 24);

            //Setzen des Textviews
            finishtime = (TextView) findViewById(R.id.tvFinishTimeOutput);
            finishtime.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE) + " Uhr");

            //maximale Arbeitszeit
            maxFinishTime = (TextView) findViewById(R.id.tvMaxFinishTimeOutput);
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            maxFinishTime.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE) + " Uhr");


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startTime = ( TextView) findViewById(R.id.tvStartTimeOutput);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, onTimeSetListenerStart, calendarStart.get(Calendar.HOUR_OF_DAY), calendarStart.get(Calendar.MINUTE), true).show();
            }
        });

        endTime = ( TextView) findViewById(R.id.tvEndTimeOutput);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, onTimeSetListenerEnd, calendarEnd.get(Calendar.HOUR_OF_DAY), calendarEnd.get(Calendar.MINUTE), true).show();
            }
        });


    }
    TimePickerDialog.OnTimeSetListener onTimeSetListenerStart = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            startTime.setText(hourOfDay+":"+minute);
            //SBerechnen des Feierabends + Setzen des Textviews
            calcFinishDate(hourOfDay+":"+minute);
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSetListenerEnd = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            endTime.setText(hourOfDay+":"+minute);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
