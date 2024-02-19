package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarYear2LunarYearActivity extends AppCompatActivity
    implements View.OnClickListener
{
    EditText edtCalendarYear;
    Button btnLunarYear;
    TextView txtLunarYear;
    ImageView imgReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_year2_lunar_year);
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnLunarYear.setOnClickListener(this);
        imgReturn.setOnClickListener(this);
    }

    private void addViews() {
        edtCalendarYear = findViewById(R.id.edtCalendarYear);
        btnLunarYear = findViewById(R.id.btnLunarYear);
        txtLunarYear = findViewById(R.id.txtLunarYear);
        imgReturn = findViewById(R.id.imgReturn);
    }
    String convertCalendar2Lunar(int calendarYear)
    {
        String result="";
        String []arr_can={"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        String []arr_chi={"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi"};
        int can = calendarYear%10;
        String ten_can=arr_can[can];
        int chi = calendarYear%12;
        String ten_chi=arr_chi[chi];
        result=ten_can+" "+ten_chi;
        return result;
    }

    @Override
    public void onClick (View v){
        if (v.equals(btnLunarYear))
        {
            int calendarYear = Integer.parseInt(edtCalendarYear.getText().toString());
            String result=convertCalendar2Lunar(calendarYear);
            txtLunarYear.setText(result);
        }
        if (v.equals(imgReturn)) {
            finish();
            //return previous screen
        }
    }
}