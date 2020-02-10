package com.example.spendingtrackerandroid;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.StringRequest;
import androidx.appcompat.widget.Toolbar;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;





public class AddTransaction extends AppCompatActivity {

    EditText txt_amount, txt_summary;
    DatePicker datePicker;
    Spinner s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        txt_amount = findViewById(R.id.amount);
        txt_summary = findViewById(R.id.summary);
        datePicker = findViewById(R.id.datePicker);
        s = findViewById(R.id.spinner);

    }

//    @Override
//    public boolean onCreateOptionsMenu() {
//        return onCreateOptionsMenu();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//// Inflate the menu; this adds items to the action bar if it is present
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            saveData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction_bar, menu);
        return true;
    }

    private void saveData() {

        Float amount = Float.parseFloat(txt_amount.getText().toString());
        String summary = txt_summary.getText().toString();

        String date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();

        String category = s.getSelectedItem().toString();


        String url ="http://192.168.1.71/SpendingMoney/addtransaction.php?category=" + category + "&summary=" + summary + "&trans_date=" + date + "&amount=" + amount;
        RequestQueue queue =
                Volley.newRequestQueue(this);
        StringRequest request = new
                StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Saved successfully",
                                Toast.LENGTH_SHORT).show();

                        onBackPressed();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void
                    onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error:" +
                                error.toString(), Toast.LENGTH_SHORT).show();
//                        pb.setVisibility(View.INVISIBLE);
                    }
                }
        );
        queue.add(request);
    }



}
