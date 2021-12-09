package com.example.lesson1.data.remote;

import com.example.lesson1.data.models.Film;

import java.util.List;

public interface OnFilmsReadyCallBack {
    void success(List<Film> films);
    void onServerError();
    void failure(String msg);

}
