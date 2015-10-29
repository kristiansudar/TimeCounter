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

    private void addTime(Date pStart, Date pEnd){
        // Brutto Arbeitszeit
        long diff = pEnd.getTime() - pStart.getTime();
        // Abzug der Pause - 50 Minuten in Millisekunden
        long ohnePause = 0;
        ohnePause = diff - 3000000;
        // Abzug der Tarifvereinbarung - 24 Minuten in Millisekunden
        long netto = 0;
        netto = ohnePause - 1440000;
        diff = netto;
        //Maximalarbeitszeit von 10 Std
        if (diff > 10){
            diff = 10;
        }
        lTimeCount = diff;
    }

    //TODo
    /*private Time getFinishTime(Time pStarttime){


    }*/

    private void finishTime(Date pStart){

    }

    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();
    TextView startTime;
    TextView endTime;
    TextView finishtime;

    public void calcFinishDate(String time) {
        SimpleDateFormat df = new java.text.SimpleDateFormat("HH:mm");
        try {

            Date tempTime = df.parse(time);
            //ToDo Calendar an 24h Format anpassen
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tempTime);
            calendar.add(Calendar.HOUR, 8);
            calendar.add(Calendar.MINUTE, 50);
            calendar.add(Calendar.MINUTE, 24);

            //Setzen des Textviews
            finishtime = (TextView) findViewById(R.id.tvFinishTimeOutput);
            finishtime.setText(calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE));
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
