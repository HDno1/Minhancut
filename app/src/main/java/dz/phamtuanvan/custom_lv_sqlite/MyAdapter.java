package dz.phamtuanvan.custom_lv_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;

    ArrayList<Student> arrayList;

    public MyAdapter(Context context, ArrayList<Student> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_view,null);
            TextView txt_Name =(TextView) view.findViewById(R.id.txt_Name);
            TextView txt_Phone = (TextView) view.findViewById(R.id.txt_Phone);
            CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkbox);
            TextView txt_Id = (TextView)view.findViewById(R.id.txt_Id);
            
            
            
            Student student = arrayList.get(i);
            txt_Id.setText(String.valueOf(student.getId()));
            txt_Name.setText(student.getName());
            txt_Phone.setText(student.getPhone());

            /*checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    *//*MainActivity.edId.setText(String.valueOf(student.getId()));*//*
                    MainActivity.vitri = student.getId();
                    MainActivity.ed1.setText(student.getName());
                    MainActivity.ed2.setText(student.getPhone());
                }
            });*/

        return view;
    }
}
