package com.example.sayed.firebasedatabase184;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    com.rengwuxian.materialedittext.MaterialEditText nameET;
    com.rengwuxian.materialedittext.MaterialEditText emailET;
    ListView dataList;
    DatabaseReference databaseReference;
    ArrayList<String>infoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        dataList = findViewById(R.id.dataListView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Person");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                infoList = new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()
                     ) {
                    Person person  = snapshot.getValue(Person.class);
                    infoList.add("Name: "+person.getPersonName()+" Email: "+person.getPersonEmail());
                }
                ArrayAdapter<String>adapter =  new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, infoList);
                dataList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void saveData(View view) {
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String databaseKey = databaseReference.push().getKey();
        Person person = new Person(name, email, databaseKey);
        try{
            databaseReference.child(databaseKey).setValue(person);
        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }
}
