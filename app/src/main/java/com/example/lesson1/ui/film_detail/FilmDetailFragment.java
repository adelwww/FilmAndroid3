package com.example.lesson1.ui.film_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson1.App;
import com.example.lesson1.R;
import com.example.lesson1.data.models.Film;
import com.example.lesson1.data.remote.FilmsApi;
import com.example.lesson1.data.remote.FilmsApiService;
import com.example.lesson1.data.remote.OnFilmsReadyCallBack;
import com.example.lesson1.databinding.FragmentFilmDetailBinding;

import java.util.List;


public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.apiService.getFilm(FilmDetailFragmentArgs.fromBundle(getArguments()).getId()).observe(getViewLifecycleOwner(), film -> {
            binding.nameTv.setText(String.valueOf(film.getTitle()));
            binding.originalTv.setText(String.valueOf(film.getOriginal_title()));
            binding.directorTv.setText(String.valueOf(film.getDirector()));
            binding.producerTv.setText(String.valueOf(film.getProducer()));
            binding.description.setText(String.valueOf(film.getDescription()));
        });

    }
}