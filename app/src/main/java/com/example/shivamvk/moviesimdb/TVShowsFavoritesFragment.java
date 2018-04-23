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

public class TVShowsFavoritesFragment extends Fragment {

    RecyclerView recyclerViewTvShowsFavoritesFragment;
    List<MovieClass> LIST_TVSHOWS_FAVORITES;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshows_favorites, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewTvShowsFavoritesFragment = view.findViewById(R.id.recycler_view_tvshows_favorites_fragment);
        recyclerViewTvShowsFavoritesFragment.setHasFixedSize(true);
        recyclerViewTvShowsFavoritesFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressBar = view.findViewById(R.id.progress_bar_tv_shows_favorites);

        LIST_TVSHOWS_FAVORITES = new ArrayList<>();
        loadTvShowsFavorites();
    }

    private void loadTvShowsFavorites(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("favorites")
                .child("TVShows")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            MovieClass movieClass = snapshot.getValue(MovieClass.class);
                            LIST_TVSHOWS_FAVORITES.add(movieClass);
                        }
                        SearchResultsAdapter searchResultsAdapter = new SearchResultsAdapter(LIST_TVSHOWS_FAVORITES, getActivity());
                        recyclerViewTvShowsFavoritesFragment.setAdapter(searchResultsAdapter);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
