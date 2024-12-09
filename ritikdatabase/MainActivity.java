package com.example.ritikdatabase;//package com.example.ritikdatabase;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn,idBtnUpdateCourse,btndelete;
    private databasehelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        idBtnUpdateCourse = findViewById(R.id.idBtnUpdateCourse);
        btndelete = findViewById(R.id.BtnDelete);

        // creating a new dbhandler class
        // and passing our context to it.
        databasehelper = new databasehelper(MainActivity.this);

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                databasehelper.addNewCourse(courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), courseTracksEdt.getText().toString(), courseDurationEdt.getText().toString());
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
//                    Intent intent =new Intent(MainActivity.this, MainActivity2.class);
//                    startActivity(intent);
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                databasehelper.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");

                idBtnUpdateCourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databasehelper.updateCourse(courseName,courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), courseTracksEdt.getText().toString(), courseDurationEdt.getText().toString());
                        Toast.makeText(MainActivity.this,"course update",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                });
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calling a method to delete our course.
                        databasehelper.deleteCourse(courseName);
                        Toast.makeText(MainActivity.this, "Deleted the course", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(i);
                    }
                });

            }


        });
//        idBtnUpdateCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(context, UpdateCourseActivity.class);
//
//                // below we are passing all our values.
//                i.putExtra("name", modal.getCourseName());
//                i.putExtra("description", modal.getCourseDescription());
//                i.putExtra("duration", modal.getCourseDuration());
//                i.putExtra("tracks", modal.getCourseTracks());
//
//                // starting our activity.
//                context.startActivity(i);
//
//            }
//        });
//
        }


}