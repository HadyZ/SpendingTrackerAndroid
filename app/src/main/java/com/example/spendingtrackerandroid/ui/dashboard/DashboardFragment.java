package com.example.spendingtrackerandroid.ui.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.spendingtrackerandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {
    JSONArray data;
    TextView amt,category,categoryValue;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard,container, false);
        amt = root.findViewById(R.id.txtcategories);
        category = root.findViewById(R.id.lbl_category1_name);
        categoryValue = root.findViewById(R.id.txt_category1_value);


        getDashboardData();


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getDashboardData(){
        String url = "http://192.168.1.71/SpendingMoney/getdashboarddata.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                data = response;

                                JSONObject obj = data.optJSONObject(0);
                                try{


                                    amt.setText("$" + obj.getString("summary"));
                                    category.setText(obj.getString("category").toUpperCase());
                                    categoryValue.setText( "$" + obj.getString("amount"));

                                }catch (JSONException e){
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//
                        Toast.makeText(getActivity(),"Error! " + error,Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);


    }
}



