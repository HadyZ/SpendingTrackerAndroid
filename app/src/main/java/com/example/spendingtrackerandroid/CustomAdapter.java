package com.example.spendingtrackerandroid;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spendingtrackerandroid.R;
import com.example.spendingtrackerandroid.ui.transactions.TransactionFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {
    Context con;
    JSONArray data;
    LayoutInflater inflater;
    // constructor
    public CustomAdapter(Context c, JSONArray data){
        this.con=c;
        this.data = data;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class Holder {
        TextView nametxtv,typetxtv,amounttxtv,datetxtv;
        ImageView deleteimage;
    }
    @Override
    public int getCount() {
        return data.length();
    }
    @Override
    public Object getItem(int i) {
    // i is the clicked position and is filled by Android
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.row,null);
        //initialize the fields in the listview row
        holder.nametxtv = rowView.findViewById(R.id.t_name);
        holder.amounttxtv = rowView.findViewById(R.id.t_amount);
        holder.typetxtv = rowView.findViewById(R.id.t_type);
        holder.datetxtv=rowView.findViewById(R.id.t_date);
        holder.deleteimage = rowView.findViewById(R.id.delete);

        // fill them with data:
        // Data is in the json object that was passed to the constructor
        // and we named it "data".
        // extract the object from the json array
        //optJSONObject() is the same as getJSONObject() but better in case the object is null
        // i is the current position, it increments itself
        JSONObject obj = data.optJSONObject(i);
        try {
            holder.nametxtv.setText(obj.getString("summary"));
            holder.typetxtv.setText(obj.getString("category"));
            holder.amounttxtv.setText(obj.getString("amount"));
            holder.datetxtv.setText(obj.getString("trans_date"));
            //int id= obj.getInt("id");
            holder.deleteimage.setTag(obj.getInt("cat_id"));
            holder.deleteimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url ="http://192.168.1.71/SpendingMoney/deletetransaction.php?id="+holder.deleteimage.getTag();
                    RequestQueue queue = Volley.newRequestQueue(con);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // check whether delete was successful or not // remember that php returns "success" or "fail"
                            if(response.equals("success")){
                                // force the listview to refresh
                                // call onresume in the activity again
//                                ((TransactionFragment)con).onResume();
                                }
                            else {
                                Toast.makeText(con, "Delete failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(con,"Error:"+error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        queue.add(request);
                }});
            }
        catch (JSONException e){
        }
        return rowView;
    }
}
