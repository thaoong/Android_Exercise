package com.nguyenthithao.practice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ThongTinCaNhanActivity extends AppCompatActivity {
    EditText edtHoTen, edtCMND, edtThongTinBoSung;
    RadioButton radTrungCap, radCaoDang, radDaiHoc;
    CheckBox chkDocBao, chkDocSach, chkDocCoding;
    Button btnGui;
    TextView txtThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        addViews();
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTen.getText().toString();
                String CMND = edtCMND.getText().toString();
                String bangcap = layBangCap();
                String docbao = "";
                String docsach = "";
                String doccoding = "";
                if (chkDocBao.isChecked()){
                    docbao = chkDocBao.getText().toString();
                }
                if (chkDocSach.isChecked()){
                    docsach = chkDocSach.getText().toString();
                }
                if (chkDocCoding.isChecked()){
                    doccoding = chkDocCoding.getText().toString();
                }
                String thongtinbosung = edtThongTinBoSung.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinCaNhanActivity.this);
                builder.setTitle("Thông tin cá nhân");
                builder.setMessage(
                        hoten + "\n"
                        + CMND + "\n"
                        + bangcap +"\n"
                        + docbao + "" + docsach + " " + doccoding + "\n"
                        + "------------------------------------------- \n"
                        + "Thông tin bổ sung: \n"
                        + thongtinbosung);
                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
//                txtThongTin.setText(
//                        "Họ tên: " + hoten + "\n"
//                        +"CMND: " + CMND + "\n"
//                        +"Bằng cấp: " + bangcap +"\n"
//                        +"Sở thích: " + docbao + "" + docsach + "" + doccoding + "\n"
//                        +thongtinbosung);
            }
        });
    }

    private String layBangCap() {
        if (radTrungCap.isChecked())
        {
            return radTrungCap.getText().toString();
        }
        else if (radCaoDang.isChecked())
        {
            return radCaoDang.getText().toString();
        }
        else if (radDaiHoc.isChecked()) {
            return radDaiHoc.getText().toString();
        }
        return "";
    }

    private void addViews() {
        edtHoTen=findViewById(R.id.edtHoTen);
        edtCMND=findViewById(R.id.edtCMND);
        edtThongTinBoSung=findViewById(R.id.edtThongTinBoSung);
        radTrungCap=findViewById(R.id.radTrungCap);
        radCaoDang=findViewById(R.id.radCaoDang);
        radDaiHoc=findViewById(R.id.radDaiHoc);
        chkDocBao=findViewById(R.id.chkDocBao);
        chkDocSach=findViewById(R.id.chkDocSach);
        chkDocCoding=findViewById(R.id.chkDocCoding);
        btnGui=findViewById(R.id.btnGui);
        txtThongTin=findViewById(R.id.txtThongTin);
    }
}