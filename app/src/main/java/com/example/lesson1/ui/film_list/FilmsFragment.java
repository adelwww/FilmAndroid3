package com.example.lesson1.ui.film_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson1.App;
import com.example.lesson1.R;
import com.example.lesson1.data.models.Film;
import com.example.lesson1.data.remote.OnFilmsReadyCallBack;
import com.example.lesson1.databinding.FragmentFilmsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmsFragment extends Fragment {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;

    public FilmsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater,container,false);
        adapter = new FilmsAdapter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initClickers();
        binding.recyclerFilms.setAdapter(adapter);

       App.apiService.getFilms(new OnFilmsReadyCallBack() {
           @Override
           public void success(List<Film> films) {
               adapter.setFilms(films);
           }

           @Override
           public void onServerError() {
               Log.e("TAG", "onServiceError");
           }

           @Override
           public void failure(String msg) {
               Log.e("TAG", "failure: " + msg);
           }
       });
    }

    private void initClickers() {
        adapter.setOnClick(id -> {
            Navigation.findNavController(requireView()).navigate(FilmsFragmentDirections.actionFilmsFragmentToFilmDetailFragment(id));
        });
    }
}