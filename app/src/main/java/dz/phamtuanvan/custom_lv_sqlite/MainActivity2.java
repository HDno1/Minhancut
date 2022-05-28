package dz.phamtuanvan.custom_lv_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Button btnThem,btnBack,btnCapNhat;
    EditText ed1,ed2,ed3;DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnThem = findViewById(R.id.button);
        btnBack = findViewById(R.id.button2);
        ed1 = findViewById(R.id.edt_edt1);
        ed2 = findViewById(R.id.edt_edt2);
        ed3 = findViewById(R.id.edt_edt3);
        dataBaseHelper = new DataBaseHelper(this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ed1.getText().toString();
                String name = ed2.getText().toString();
                String phone = ed3.getText().toString();
                Boolean checkinsertData = dataBaseHelper.insertData(id,name,phone);
                if (checkinsertData == true){
                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity2.this,"Insert Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}