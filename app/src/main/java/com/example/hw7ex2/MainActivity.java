package com.example.hw7ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_main);
        showView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.action_add:
                Log.w("MainActivity", "Add Selected");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete Selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "Update Selected");
                Intent updateIntent = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }
    private void showView() {
        ArrayList<Friends> friendArray = dbManager.selectAll();

        // create ScrollView and GridLayout
        if (friendArray.size() > 0) {

            //ScrollView scrollView = new ScrollView( this );
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friendArray.size());
            grid.setColumnCount(4);

            // create arrays of components
            TextView[] ids = new TextView[friendArray.size()];
            TextView[][] namesAndEmails = new TextView[friendArray.size()][3];

            // retrieve width of screen
            Point size = new Point();
            getDisplay().getRealSize(size);
            int width = size.x;

            // create the TextView for the candy's id
            int i = 0;

            for (Friends friends : friendArray) {

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friends.getId());

                // create the two EditTexts for the candy's name and price
                namesAndEmails[i][0] = new TextView(this);
                namesAndEmails[i][1] = new TextView(this);
                namesAndEmails[i][2] = new TextView(this);
                namesAndEmails[i][0].setText(friends.getFname());
                namesAndEmails[i][1].setText("" + friends.getLname());
                namesAndEmails[i][2].setText("" + friends.getEmail());
                namesAndEmails[i][1]
                        .setInputType(InputType.TYPE_CLASS_NUMBER);
                namesAndEmails[i][0].setId(10 * friends.getId());
                namesAndEmails[i][1].setId(10 * friends.getId() + 1);
                namesAndEmails[i][2].setId(10 * friends.getId() + 2);
                grid.addView(ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmails[i][0], (int) (width * .3),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmails[i][1], (int) (width * .3),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmails[i][2], (int) (width * .5),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            horizontalScrollView.addView(grid);
            setContentView(horizontalScrollView);

        }

    }





}


