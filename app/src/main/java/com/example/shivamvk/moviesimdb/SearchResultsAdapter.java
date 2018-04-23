package com.example.shivamvk.moviesimdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    List<MovieClass> LIST_SEARCH_RESULTS;
    Context context;

    public SearchResultsAdapter(List<MovieClass> LIST_SEARCH_RESULTS, Context context) {
        this.LIST_SEARCH_RESULTS = LIST_SEARCH_RESULTS;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_results_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieClass movieClass = LIST_SEARCH_RESULTS.get(position);
        Picasso.get()
                .load(Constants.IMAGES_URL + movieClass.getStMoviePosterPath())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.ivMovieImage);
        holder.tvMovieTitle.setText(movieClass.getStMovieTitle());
        Float rating = Float.parseFloat(movieClass.getStMovieRating());
        int movieRating = Math.round(rating);
        movieRating = movieRating/2;
        switch (movieRating){
            case 1:
                holder.ivRatingOne.setVisibility(View.VISIBLE);
                holder.ivRatingTwo.setVisibility(View.GONE);
                holder.ivRatingThree.setVisibility(View.GONE);
                holder.ivRatingFour.setVisibility(View.GONE);
                holder.ivRatingFive.setVisibility(View.GONE);
                break;
            case 2:
                holder.ivRatingOne.setVisibility(View.VISIBLE);
                holder.ivRatingTwo.setVisibility(View.VISIBLE);
                holder.ivRatingThree.setVisibility(View.GONE);
                holder.ivRatingFour.setVisibility(View.GONE);
                holder.ivRatingFive.setVisibility(View.GONE);
                break;
            case 3:
                holder.ivRatingOne.setVisibility(View.VISIBLE);
                holder.ivRatingTwo.setVisibility(View.VISIBLE);
                holder.ivRatingThree.setVisibility(View.VISIBLE);
                holder.ivRatingFour.setVisibility(View.GONE);
                holder.ivRatingFive.setVisibility(View.GONE);
                break;
            case 4:
                holder.ivRatingOne.setVisibility(View.VISIBLE);
                holder.ivRatingTwo.setVisibility(View.VISIBLE);
                holder.ivRatingThree.setVisibility(View.VISIBLE);
                holder.ivRatingFour.setVisibility(View.VISIBLE);
                holder.ivRatingFive.setVisibility(View.GONE);
                break;
            case 5:
                holder.ivRatingOne.setVisibility(View.VISIBLE);
                holder.ivRatingTwo.setVisibility(View.VISIBLE);
                holder.ivRatingThree.setVisibility(View.VISIBLE);
                holder.ivRatingFour.setVisibility(View.VISIBLE);
                holder.ivRatingFive.setVisibility(View.VISIBLE);
                break;
        }

        holder.cvSearchREsults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("MovieTitle", movieClass.getStMovieTitle());
                intent.putExtra("MovieRating", movieClass.getStMovieRating());
                intent.putExtra("PosterPath", movieClass.getStMoviePosterPath());
                intent.putExtra("BackdropPath", movieClass.getStMovieBackdropPath());
                intent.putExtra("MovieOverview", movieClass.getStMovieOverview());
                intent.putExtra("ReleaseDate", movieClass.getStMovieReleaseDate());
                context.startActivity(intent);
            }
        });

        holder.btAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Adding to Favorites...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                if (movieClass.getStMovieReleaseDate().equals("Not Provided")){
                    databaseReference.child("users").child(HomeActivity.user.getStUserId()).child("favorites").child("TVShows").child(movieClass.getStMovieTitle()).setValue(movieClass);
                } else {
                    databaseReference.child("users").child(HomeActivity.user.getStUserId()).child("favorites").child("Movies").child(movieClass.getStMovieTitle()).setValue(movieClass);
                }
                progressDialog.dismiss();
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return LIST_SEARCH_RESULTS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivMovieImage;
        private TextView tvMovieTitle;
        private ImageView ivRatingOne;
        private ImageView ivRatingTwo;
        private ImageView ivRatingThree;
        private ImageView ivRatingFour;
        private ImageView ivRatingFive;
        private Button btAddToFav;
        private Button btWatchTrailer;
        private CardView cvSearchREsults;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMovieImage = itemView.findViewById(R.id.iv_search_results_image);
            ivRatingOne = itemView.findViewById(R.id.iv_search_results_rating_one);
            ivRatingTwo = itemView.findViewById(R.id.iv_search_results_rating_two);
            ivRatingThree = itemView.findViewById(R.id.iv_search_results_rating_three);
            ivRatingFour = itemView.findViewById(R.id.iv_search_results_rating_four);
            ivRatingFive = itemView.findViewById(R.id.iv_search_results_rating_five);
            tvMovieTitle = itemView.findViewById(R.id.tv_search_results_movie_title);
            btAddToFav = itemView.findViewById(R.id.bt_search_results_add_to_fav);
            btWatchTrailer = itemView.findViewById(R.id.bt_search_results_watch_trailer);
            cvSearchREsults = itemView.findViewById(R.id.cv_search_results);
        }
    }

}
