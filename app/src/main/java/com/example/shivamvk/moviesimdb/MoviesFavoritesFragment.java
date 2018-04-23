package com.example.shivamvk.moviesimdb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesFavoritesFragment extends Fragment {

    RecyclerView recyclerViewMoviesFavoritesFragment;
    List<MovieClass> LIST_MOVIES_FAVORITES;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies_favorites, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewMoviesFavoritesFragment = view.findViewById(R.id.recycler_view_movies_favorites_fragment);
        recyclerViewMoviesFavoritesFragment.setHasFixedSize(true);
        recyclerViewMoviesFavoritesFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressBar = view.findViewById(R.id.progress_bar_movies_favorites);

        LIST_MOVIES_FAVORITES = new ArrayList<>();
        loadFavoriteMoview();
    }

    private void loadFavoriteMoview(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("favorites")
                .child("Movies")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            MovieClass movieClass = snapshot.getValue(MovieClass.class);
                            LIST_MOVIES_FAVORITES.add(movieClass);
                        }
                        SearchResultsAdapter searchResultsAdapter = new SearchResultsAdapter(LIST_MOVIES_FAVORITES, getActivity());
                        recyclerViewMoviesFavoritesFragment.setAdapter(searchResultsAdapter);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
