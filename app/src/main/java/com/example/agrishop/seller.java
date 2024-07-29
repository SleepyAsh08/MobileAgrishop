package com.example.agrishop;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.product.adapter.ProductDetailsAdapter;
import com.android.product.seller.ProductDetails;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class seller extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private RequestQueue requestQueue;
    private List<ProductDetails> productDetailsList = new ArrayList<>();
    private StringRequest stringRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller, container, false);
        init(view);
        requestJsonData();
        return view;
    }

    public void init(View view){
        recyclerView = view.findViewById(R.id.seller);
        context = getContext();
        requestQueue = Volley.newRequestQueue(context);
    }

    public void requestJsonData(){
        Log.d("request", "called");
        requestQueue = Volley.newRequestQueue(context);

        stringRequest = new StringRequest(Request.Method.GET, "http://192.168.131.163:8080/products/all", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.d("response", response.toString());
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    fetchTheData(jsonArray);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Jan", String.valueOf(error));
                showToast("API call error");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        requestQueue.add(stringRequest);
    }

    public void fetchTheData(JSONArray jsonArray){

        String baseUrl = "http://192.168.131.163:8080/storage/";
        for(int i = 0; i<jsonArray.length(); i++){
            try {

                JSONObject product = jsonArray.getJSONObject(i);

                String photos = product.optString("photos", "");

                JSONArray photoArray = new JSONArray(photos);
                String photoPath = photoArray.length() > 0 ? photoArray.getString(0) : ""; // Get the first photo path
                String photoUrl = baseUrl + photoPath.replace("\\", "/");

                productDetailsList.add(new ProductDetails(
                        product.getString("Product_Name"),
                        product.getString("idCategory"),
                        product.getString("price"),
                        product.getString("Quantity"),
                        product.getString("Description"),
                        photoUrl
                ));
            } catch (Exception e){
                e.printStackTrace();
                showToast("Product Details Error");
            }
        }

        ProductDetailsAdapter adapter = new ProductDetailsAdapter(productDetailsList, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    public void showToast(String mgs){
        Toast.makeText(context, mgs, Toast.LENGTH_SHORT).show();
    }
}