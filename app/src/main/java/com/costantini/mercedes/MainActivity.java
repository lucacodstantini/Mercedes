package com.costantini.mercedes;

import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.app.AlertDialog.*;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
                Button button=(Button) findViewById(R.id.button);


    }

    public void stats(View v)
    {
        Intent intent=new Intent(MainActivity.this,Statistics.class);
        startActivity(intent);
    }
    public void playVideo(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1w-4PO8Fb-Y")));
    }
    public void showAD(View v){
        Builder builder = new Builder(MainActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle("Grazie per averci chiamato")
                .setMessage("Abbiamo inoltrato una richiesta di assistenza al collaboratore più vicino a te");

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        switch(position)
        {
            case 1:
            {
                Intent intent =new Intent(MainActivity.this,Current.class);
                startActivity(intent);
                break;}
            case 2:{
                Intent intent =new Intent(MainActivity.this,Lista.class);
                startActivity(intent);
            break;}
            case 3:{
                Intent intent =new Intent(MainActivity.this,Map.class);
                startActivity(intent);
                break;}

            case 4:{
                Intent intent =new Intent(MainActivity.this,Assistance.class);
                startActivity(intent);
                break;}

            case 5:{
                Intent intent =new Intent(MainActivity.this,Sign.class);
                startActivity(intent);
                break;}

            case 6:{
                Intent intent =new Intent(MainActivity.this,Gear.class);
                startActivity(intent);
                break;}
            default:break;
        }
    }

    public void current(View v)
    {
        Intent intent =new Intent(MainActivity.this,Current.class);
        startActivity(intent);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            default:break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            // get the listview
            expListView = (ExpandableListView) findViewById(R.id.list);

            // preparing list data
            prepareListData();

            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(listAdapter);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(MainActivity.this,Statistics.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Consumi");
        listDataHeader.add("Statistiche");
        listDataHeader.add("Versioni");
        listDataHeader.add("Descrizione");

        // Adding child data
        List<String> Consumi = new ArrayList<String>();
        Consumi.add("Quanto consuma in media?");
        Consumi.add("Che cosa ne pensano gli utilizzatori del rapporto km/l?");
        Consumi.add("Fai un confronto con altri prodotti Mercedes.");


        List<String> Statistiche = new ArrayList<String>();
        Statistiche.add("Qual'è la velocità massima?");
        Statistiche.add("Quanti cilindri possiede?");
        Statistiche.add("Qual è la cilindrata?");
        Statistiche.add("Ottieni lista completa di informazioni.");


        List<String> Versioni = new ArrayList<String>();
        Versioni.add("Lista dei colori disponibili.");
        Versioni.add("Lista optional.");

        List<String> Descrizione = new ArrayList<String>();
        Descrizione.add("Come è nata l'idea di Modello 1?");
        Descrizione.add("Cos'è che fa di Modello 1 un'ottima macchina?");

        listDataChild.put(listDataHeader.get(0), Consumi); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Statistiche);
        listDataChild.put(listDataHeader.get(2), Versioni);
        listDataChild.put(listDataHeader.get(3), Descrizione);
    }

}
