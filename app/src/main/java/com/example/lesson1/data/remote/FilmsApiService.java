package com.example.lesson1.data.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lesson1.App;
import com.example.lesson1.data.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsApiService {

    public void getFilms(OnFilmsReadyCallBack myCallBack){
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null){
                    myCallBack.success(response.body());
                }else if (response.code() > 500 ){
                    myCallBack.onServerError();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                myCallBack.failure(t.getLocalizedMessage());
            }
        });
    }

    public LiveData<Film> getFilm(String id){
        MutableLiveData<Film> data = new MutableLiveData<>();
        App.api.getFilm(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                if (response.isSuccessful() && response.body() != null){
                    data.setValue(response.body());
                }else if (response.code() > 500 ){
//                    data.onServerError();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                data.setValue(null);
//                myCall.failure(t.getLocalizedMessage());
            }
        });
        return data;
    }
}
