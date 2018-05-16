package ericstephens.candleinventory;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainMenu extends FragmentActivity
implements CandleListFragment.OnListFragmentInteractionListener{

    private FragmentManager fm = getSupportFragmentManager();
    private EditText search;
    private Button searchBtn;
    private FloatingActionButton fab;
    private Button refresh;
    DatabaseHelper db;
    CandleListFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        db = new DatabaseHelper(getApplicationContext());

        search = findViewById(R.id.candleNameSearch);
        searchBtn = findViewById(R.id.searchBtn);
        fab = findViewById(R.id.addCandle);
        refresh = findViewById(R.id.refresh);

        fragment = new CandleListFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(fragment, null);
        transaction.commit();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewItemFragment newItem = new NewItemFragment();
                newItem.newInstance(getApplicationContext());
                newItem.show(fm, null);
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement searching list
                String text = search.getText().toString();
                fragment.getSearchData(text);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getAllData();
            }
        });


    }

    @Override
    public void onListFragmentInteraction(EditText invetoryAmount) {

    }
}
