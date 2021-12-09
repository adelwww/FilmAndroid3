package com.example.lesson1.ui.film_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1.data.models.Film;
import com.example.lesson1.databinding.ItemFilmsBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {

    private List<Film> films = new ArrayList<>();
    private onClick click;

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    public void setOnClick(onClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmsBinding binding = ItemFilmsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new FilmsViewHolder(binding, click);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, int position) {
        holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmsViewHolder extends RecyclerView.ViewHolder {
        private ItemFilmsBinding binding;
        private onClick click;

        public FilmsViewHolder(@NonNull ItemFilmsBinding binding, onClick setOnClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.click = setOnClick;
        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvDescription.setText(film.getDescription());

            binding.getRoot().setOnClickListener(view -> {
                        click.setOnClickListener(film.getId());
                    }
            );
        }
    }

    public interface onClick {
        void setOnClickListener(String id);
    }
}
