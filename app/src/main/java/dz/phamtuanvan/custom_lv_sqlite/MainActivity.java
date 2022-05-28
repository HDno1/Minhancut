package dz.phamtuanvan.custom_lv_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static EditText ed1,ed2,edId;
    DataBaseHelper dataBaseHelper;
    ListView ll;
    FloatingActionButton floatingActionButton;
    ArrayList<Student> arrayList;
    Button btnThem,btnXoa,btnCapNhat;
    MyAdapter myAdapter;
    public static int vitri;
    public static String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton2);

        ed1 = (EditText)findViewById(R.id.txt_Name);

        ll =  (ListView)findViewById(R.id.lv_student);

        dataBaseHelper = new DataBaseHelper(this);

        arrayList = new ArrayList<>();

        loadDataInListView();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        /*btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String phone = ed2.getText().toString();
                Boolean checkinsertData = dataBaseHelper.insertData(name,phone);
                if (checkinsertData == true){
                    Toast.makeText(MainActivity.this,"Insert Completed",Toast.LENGTH_SHORT).show();

                    loadDataInListView();
                }
                else {
                    Toast.makeText(MainActivity.this,"Insert Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });*/



        registerForContextMenu(ll);
        ll.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = arrayList.get(i);
                vitri = student.getId();
                 name1 = student.getName();
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(this,"Option 1",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                XacNhanXoa(vitri);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void loadDataInListView() {
        arrayList = dataBaseHelper.getaAllData();
        myAdapter = new MyAdapter(this, arrayList);
        ll.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    private void XacNhanXoa(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(name1+" wants to delete ?");
        builder.setTitle("Confirm");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Boolean checkDeleteData = dataBaseHelper.deleteData(position);
                if (checkDeleteData == true){
                    Toast.makeText(MainActivity.this,"Delete Completed",Toast.LENGTH_SHORT).show();

                    loadDataInListView();

                }
                else {
                    Toast.makeText(MainActivity.this,"Delete Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}