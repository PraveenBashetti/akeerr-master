package com.example.aker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PhoneContactRevised extends AppCompatActivity {


    private static int cntind = 1000;
    private static int[] sltcnt = new int[1000];
    public String[] s = new String[cntind];
    public String[] sn = new String[cntind];
    public static String[] snm;
    public static String[] sno;
      

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_contact_revised);



        listView = findViewById(R.id.listViewPhnContactRevised);


        for (int i = 0; i < cntind; i++) {
            s[i] = new String();
            sn[i] = new String();
        }

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        int ino = 0;

        while (cursor.moveToNext()) {

            s[ino] = new String(
                    cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));// name
            // of
            // contact

            sn[ino] = new String(
                    cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));// mobile
            // no.
            ino++;
        }

        snm = new String[ino];
        sno = new String[ino];

        String[] cntct = new String[ino];
        for (int i = 0; i < ino; i++) {
            // snm[i] = s[i];
            // sno[i] = sn[i];
            cntct[i] = s[i] + "\n" + sn[i];
        }

        ArrayAdapter<String> arr = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, cntct);
        listView.setAdapter(arr);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setBackgroundResource(R.color.SkyBlue);

   /*  buttonYes.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             SparseBooleanArray selectedPos = listView.getCheckedItemPositions();
             ListAdapter lAdapter = listView.getAdapter();
             for (int i = 0; i < lAdapter.getCount(); i++) {
                 if (selectedPos.get(i)) {
                     sltcnt[i] = Integer.parseInt(String.valueOf(lAdapter
                             .getItemId(i)));
                     sno[i] = sn[sltcnt[i]].toString();
                     cntlen++;
                 }
             }

             Intent inttbl = getIntent();
             String stblnam = inttbl.getStringExtra("tablesname");
             String stblid=inttbl.getStringExtra("update");

             DataBaseConnectivityClass dbc = new DataBaseConnectivityClass(
                     getBaseContext());
             dbc.sqlInsertContacts(sno, stblnam, stblid);
             dbc.close();
             PhoneContactRevised.this.finish();


         }
     });
*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // TODO Auto-generated method stub

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.phoneoptionmenu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        int cntlen = 0;
        switch (item.getItemId()) {

            case R.id.submit:
               SparseBooleanArray selectedPos =listView.getCheckedItemPositions();
                ListAdapter lAdapter =listView.getAdapter();
                for (int i = 0; i < lAdapter.getCount(); i++) {
                    if (selectedPos.get(i)) {
                        sltcnt[i] = Integer.parseInt(String.valueOf(lAdapter
                                .getItemId(i)));
                        sno[i] = sn[sltcnt[i]].toString();
                        cntlen++;
                    }
                }

                Intent inttbl = getIntent();
                String stblnam = inttbl.getStringExtra("tablesname");
                String stblid=inttbl.getStringExtra("update");

                DataBaseConnectivityClass dbc = new DataBaseConnectivityClass(
                        getBaseContext());
                dbc.sqlInsertContacts(sno, stblnam, stblid);
                dbc.close();
                PhoneContactRevised.this.finish();
                return true;

            case R.id.cancel:

                Toast.makeText(this,
                        item.getTitle() + " THERE IS NO CONTACT SELECTED",
                        Toast.LENGTH_LONG).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
