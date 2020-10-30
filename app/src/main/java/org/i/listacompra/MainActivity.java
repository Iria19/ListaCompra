package org.i.listacompra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */

    private ArrayAdapter<String> itemsAdapter;
    private ArrayList<String> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        this.items = new ArrayList<String>();

        Button btAdd = (Button) this.findViewById( R.id.lnButton );
        ListView lvItems = (ListView) this.findViewById( R.id.lnListView );

        // lvItems.setLongClickable( true );
        this.itemsAdapter = new ArrayAdapter<String>(
                this.getApplicationContext(),
                android.R.layout.simple_selectable_list_item,
                this.items
        );

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onAdd();
            }
        });
        lvItems.setAdapter( this.itemsAdapter );

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if ( pos >= 0 ) {
                    MainActivity.this.items.remove( pos );
                    MainActivity.this.itemsAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });

    }

    private void onAdd() {
        final EditText edText = new EditText( this );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("A comprar...");
        builder.setMessage( "Nombre" );
        builder.setView( edText );
        builder.setPositiveButton( "+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String text = edText.getText().toString();

                MainActivity.this.itemsAdapter.add( text );
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }



}