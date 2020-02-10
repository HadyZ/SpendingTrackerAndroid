package com.example.spendingtrackerandroid.ui.transactions;
import android.content.Context;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.spendingtrackerandroid.CustomAdapter;
import com.example.spendingtrackerandroid.R;
import android.view.View;
import android.widget.ListView;
import org.json.JSONArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.Toast;
public class TransactionFragment extends Fragment {
    Context con;
    ListView list;
    JSONArray data;
    CustomAdapter cust_adapater;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transactions, container, false);
        list = root.findViewById(R.id.listview);
        getDataFromDB();

        return root;
    }

    public void onResume() {
        super.onResume();
        getDataFromDB();
    }

    public void getDataFromDB() {
        String url ="http://192.168.1.69/SpendingMoney/getAllTransactions.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                data = response;

                cust_adapater = new CustomAdapter(getActivity().getApplicationContext(), data);
                list.setAdapter(cust_adapater);
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Toast.makeText(getActivity().getApplicationContext(), "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);

}

}

