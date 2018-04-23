package com.example.shivamvk.moviesimdb;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MovieClass> LIST_SEARCH_RESULTS;
    private String SEARCH_QUERY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        SEARCH_QUERY = getIntent().getStringExtra("SearchQuery");
        setTitle("Search results for '" + SEARCH_QUERY +"'" );

        recyclerView = findViewById(R.id.rv_search_results);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_from_bottom);
        recyclerView.setLayoutAnimation(layoutAnimationController);

        LIST_SEARCH_RESULTS = new ArrayList<>();
        loadSearchResults();
    }

    private void loadSearchResults(){
        SEARCH_QUERY = SEARCH_QUERY.replace(" ","%20");
        String SEARCH_REQUEST_URL = Constants.SEARCH_QUERY_REQUEST_URL + SEARCH_QUERY;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                SEARCH_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                MovieClass movieClass = new MovieClass(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_title"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        currentMovie.getString("release_date"),
                                        "df"
                                );
                                progressDialog.dismiss();
                                LIST_SEARCH_RESULTS.add(movieClass);
                                SearchResultsAdapter searchResultsAdapter
                                        = new SearchResultsAdapter(LIST_SEARCH_RESULTS, SearchResultsActivity.this);
                                recyclerView.setAdapter(searchResultsAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SearchResultsActivity.this);
        requestQueue.add(stringRequest);
    }
}
