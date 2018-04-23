package com.example.shivamvk.moviesimdb;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView tvMovieDetailLargeText;
    private TextView tvMovieDetailMovieTitle;
    private ImageView ivMovieDetailBackdropPath;
    private ImageView ivMovieDetailPosterPath;

    private ImageView ivMovieRatingOne;
    private ImageView ivMovieRatingTwo;
    private ImageView ivMovieRatingThree;
    private ImageView ivMovieRatingFour;
    private ImageView ivMovieRatingFive;

    private Button btMovieDetailAddToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final String stMovieName = getIntent().getStringExtra("MovieTitle");
        final String stMovieRating = getIntent().getStringExtra("MovieRating");
        final String stPosterPath = getIntent().getStringExtra("PosterPath");
        final String stBackdropPath = getIntent().getStringExtra("BackdropPath");
        final String stMovieOverview = getIntent().getStringExtra("MovieOverview");
        final String stReleaseDate = getIntent().getStringExtra("ReleaseDate");

        setTitle(stMovieName);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tvMovieDetailLargeText = findViewById(R.id.tv_movie_detail_large_text);
        tvMovieDetailLargeText.setText(stMovieOverview);

        ivMovieDetailBackdropPath = findViewById(R.id.iv_movie_detail_backdrop_image);
        Picasso.get()
                .load(Constants.IMAGES_URL + stBackdropPath)
                .resize(540,180)
                .centerCrop()
                .noPlaceholder()
                .error(R.drawable.placeholder)
                .into(ivMovieDetailBackdropPath);

        btMovieDetailAddToFav = findViewById(R.id.bt_movie_detail_add_to_fav);

        tvMovieDetailMovieTitle = findViewById(R.id.tv_movie_detail_title);
        tvMovieDetailMovieTitle.setText(stMovieName);

        ivMovieDetailPosterPath = findViewById(R.id.iv_movie_detail_poster_path);
        Picasso.get()
                .load(Constants.IMAGES_URL + stPosterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivMovieDetailPosterPath);

        ivMovieRatingOne = findViewById(R.id.iv_movie_detail_rating_one);
        ivMovieRatingTwo = findViewById(R.id.iv_movie_detail_rating_two);
        ivMovieRatingThree = findViewById(R.id.iv_movie_detail_rating_three);
        ivMovieRatingFour = findViewById(R.id.iv_movie_detail_rating_four);
        ivMovieRatingFive = findViewById(R.id.iv_movie_detail_rating_five);

        Float rating = Float.parseFloat(stMovieRating);
        int movieRating = Math.round(rating);
        movieRating = movieRating/2;
        switch (movieRating){
            case 1:
                ivMovieRatingOne.setVisibility(View.VISIBLE);
                ivMovieRatingTwo.setVisibility(View.GONE);
                ivMovieRatingThree.setVisibility(View.GONE);
                ivMovieRatingFour.setVisibility(View.GONE);
                ivMovieRatingFive.setVisibility(View.GONE);
                break;
            case 2:
                ivMovieRatingOne.setVisibility(View.VISIBLE);
                ivMovieRatingTwo.setVisibility(View.VISIBLE);
                ivMovieRatingThree.setVisibility(View.GONE);
                ivMovieRatingFour.setVisibility(View.GONE);
                ivMovieRatingFive.setVisibility(View.GONE);
                break;
            case 3:
                ivMovieRatingOne.setVisibility(View.VISIBLE);
                ivMovieRatingTwo.setVisibility(View.VISIBLE);
                ivMovieRatingThree.setVisibility(View.VISIBLE);
                ivMovieRatingFour.setVisibility(View.GONE);
                ivMovieRatingFive.setVisibility(View.GONE);
                break;
            case 4:
                ivMovieRatingOne.setVisibility(View.VISIBLE);
                ivMovieRatingTwo.setVisibility(View.VISIBLE);
                ivMovieRatingThree.setVisibility(View.VISIBLE);
                ivMovieRatingFour.setVisibility(View.VISIBLE);
                ivMovieRatingFive.setVisibility(View.GONE);
                break;
            case 5:
                ivMovieRatingOne.setVisibility(View.VISIBLE);
                ivMovieRatingTwo.setVisibility(View.VISIBLE);
                ivMovieRatingThree.setVisibility(View.VISIBLE);
                ivMovieRatingFour.setVisibility(View.VISIBLE);
                ivMovieRatingFive.setVisibility(View.VISIBLE);
                break;
        }

        btMovieDetailAddToFav = findViewById(R.id.bt_movie_detail_add_to_fav);
        btMovieDetailAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(MovieDetailsActivity.this);
                progressDialog.setMessage("Adding to Favorites...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                MovieClass movieClass = new MovieClass(stMovieRating, stMovieName, stPosterPath, stBackdropPath, stMovieOverview, stReleaseDate, " ");

                if (stReleaseDate.equals("Not Provided")) {
                    databaseReference.child("users").child(HomeActivity.user.getStUserId()).child("favorites").child("TVShows").child(stMovieName).setValue(movieClass);
                } else {
                    databaseReference.child("users").child(HomeActivity.user.getStUserId()).child("favorites").child("Movies").child(stMovieName).setValue(movieClass);
                }
                progressDialog.dismiss();
                Snackbar.make(findViewById(R.id.cl_movie_details_activity), "Added to favorites", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
