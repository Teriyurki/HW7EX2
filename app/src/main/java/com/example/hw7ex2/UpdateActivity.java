package com.example.hw7ex2;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView( );
    }

    // Build a View dynamically with all the candies
    public void updateView( ) {
        ArrayList<Friends> friendArray = dbManager.selectAll( );

        // create ScrollView and GridLayout
        if( friendArray.size( ) > 0 ) {

            //ScrollView scrollView = new ScrollView( this );
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( friendArray.size( ) );
            grid.setColumnCount( 5 );

            // create arrays of components
            TextView [] ids = new TextView[friendArray.size( )];
            EditText [][] namesAndEmails = new EditText[friendArray.size( )][3];
            Button [] buttons = new Button[friendArray.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getDisplay().getRealSize(size);
            int width = size.x;

            // create the TextView for the candy's id
            int i = 0;

            for ( Friends friends : friendArray ) {

                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + friends.getId( ) );

                // create the two EditTexts for the candy's name and price
                namesAndEmails[i][0] = new EditText( this );
                namesAndEmails[i][1] = new EditText( this );
                namesAndEmails[i][2] = new EditText( this );
                namesAndEmails[i][0].setText( friends.getFname( ) );
                namesAndEmails[i][1].setText( "" + friends.getLname( ) );
                namesAndEmails[i][2].setText( "" + friends.getEmail( ) );
                namesAndEmails[i][1]
                        .setInputType( InputType.TYPE_CLASS_NUMBER );
                namesAndEmails[i][0].setId( 10 * friends.getId( ) );
                namesAndEmails[i][1].setId( 10 * friends.getId( ) + 1 );
                namesAndEmails[i][2].setId( 10 * friends.getId( ) + 2 );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( friends.getId());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add the elements to grid
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmails[i][0], ( int ) ( width * .3 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmails[i][1], ( int ) ( width * .3 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmails[i][2], ( int ) ( width * .5 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .2 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            //scrollView.addView( grid );
            //setContentView( scrollView );
            horizontalScrollView.addView(grid);
            setContentView(horizontalScrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve name and price of the candy
            int friendID = v.getId( );
            EditText fnameET = findViewById( 10 * friendID );
            EditText lnameET = findViewById( 10 * friendID + 1 );
            EditText emailET = findViewById( 10 * friendID + 2 );
            String fname = fnameET.getText( ).toString( );
            String lname = lnameET.getText( ).toString( );
            String email = emailET.getText( ).toString( );

            // update friends in database


                dbManager.updateById( friendID, fname, lname, email );
                Toast.makeText( UpdateActivity.this, "Friend updated",
                        Toast.LENGTH_SHORT ).show( );

                // update screen
                updateView( );

        }
    }
}