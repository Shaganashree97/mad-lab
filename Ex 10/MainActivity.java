package com.example.crudnew;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etRollno, etName, etDept;
    Button bInsert, bUpdate, bRead, bDelete;
    SQLiteDatabase db;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etRollno = findViewById(R.id.et1);
        etName = findViewById(R.id.et2);
        etDept = findViewById(R.id.et3);
        bInsert = findViewById(R.id.b1);
        bUpdate = findViewById(R.id.b2);
        bRead = findViewById(R.id.b3);
        bDelete = findViewById(R.id.b4);

        // Initialize database helper
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    // Insert student details into the database
    public void onInsert(View view) {
        String rollno = etRollno.getText().toString();
        String name = etName.getText().toString();
        String dept = etDept.getText().toString();

        if (rollno.isEmpty() || name.isEmpty() || dept.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put("rollno", rollno);
            values.put("name", name);
            values.put("dept", dept);

            long result = db.insert("student", null, values);

            if (result != -1) {
                Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Insertion Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Update student details based on roll number
    public void onUpdate(View view) {
        String rollno = etRollno.getText().toString();
        String name = etName.getText().toString();
        String dept = etDept.getText().toString();

        if (rollno.isEmpty() || name.isEmpty() || dept.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("dept", dept);

            int rowsUpdated = db.update("student", values, "rollno = ?", new String[]{rollno});
            if (rowsUpdated > 0) {
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Read all student details and display in a table
    public void onRead(View view) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        tableLayout.removeAllViews();  // Clear existing rows

        // Query the database to get all student records
        Cursor cursor = db.rawQuery("SELECT * FROM student", null);
        if (cursor.moveToFirst()) {
            // Add table headers
            TableRow headerRow = new TableRow(this);
            TextView headerRollno = new TextView(this);
            headerRollno.setText("Rollno");
            TextView headerName = new TextView(this);
            headerName.setText("Name");
            TextView headerDept = new TextView(this);
            headerDept.setText("Dept");

            headerRow.addView(headerRollno);
            headerRow.addView(headerName);
            headerRow.addView(headerDept);
            tableLayout.addView(headerRow);

            do {
                TableRow row = new TableRow(this);
                TextView rollno = new TextView(this);
                rollno.setText(cursor.getString(0));
                TextView name = new TextView(this);
                name.setText(cursor.getString(1));
                TextView dept = new TextView(this);
                dept.setText(cursor.getString(2));

                row.addView(rollno);
                row.addView(name);
                row.addView(dept);
                tableLayout.addView(row);
            } while (cursor.moveToNext());
        }

        cursor.close();
        findViewById(R.id.dataScrollView).setVisibility(View.VISIBLE);
    }

    public void onDelete(View view) {
        String rollno = etRollno.getText().toString();

        if (rollno.isEmpty()) {
            Toast.makeText(this, "Please enter a roll number", Toast.LENGTH_SHORT).show();
        } else {
            int rowsDeleted = db.delete("student", "rollno = ?", new String[]{rollno});
            if (rowsDeleted > 0) {
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Deletion Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}