package com.example.dogs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.common.base.BaseFragment;
import com.example.common.base.BaseViewHolder;
import com.example.common.di.CommonApplication;
import com.example.dogs.R;
import com.example.dogs.R2;
import com.example.dogs.di.DaggerDogComponent;
import com.example.dogs.di.DogComponent;
import com.example.dogs.fragments.pagging.DogViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DogListFragment extends BaseFragment {

    @BindView(R2.id.progress_bar_toolbar)
    ProgressBar progress_bar_toolbar;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R2.id.progress_bar)
    ProgressBar progress_bar;
    public static DogComponent dogComponent;
    @Inject
    protected DogViewModel dogViewModel;

    private DogAdapter dogAdapter;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.dogs_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        getRoot().setSupportActionBar(toolbar);

        dogAdapter = new DogAdapter();

        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setAdapter(dogAdapter);

        dogViewModel.getDogs().observe(this, strings -> dogAdapter.submitList(strings));
        dogViewModel.getIsLoading().observe(this, DogListFragment.this::setLoadingState);
        dogViewModel.onViewCreated();
    }

    @Override
    protected void inject() {
        CommonApplication commonApplication = (CommonApplication) getRoot().getApplication();

        dogComponent = DaggerDogComponent
                .builder()
                .commonComponent(commonApplication.component())
                .root(this)
                .build();

        dogComponent.inject(this);
    }

    public static class DogViewHolder extends BaseViewHolder<String> {
        private TextView dogUrl;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);

            dogUrl = (TextView) itemView;
        }

        @Override
        public void bind(String item) {
        }
    }

    public static class DogAdapter extends PagedListAdapter<String, DogViewHolder> {
        private DogAdapter() {
            super(DIFF_CALLBACK);
        }

        @NonNull
        @Override
        public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DogViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
            String item = getItem(position);
            holder.dogUrl.setText(item.isEmpty() ? "Загрузка... " : item);
        }
    }

    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem == newItem;
        }
    };

    private void setLoadingState(boolean isLoading) {
        if (isLoading) {
            recycler_view.setVisibility(dogAdapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
            progress_bar.setVisibility(dogAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
            progress_bar_toolbar.setVisibility(dogAdapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
        } else {
            recycler_view.setVisibility(View.VISIBLE);
            progress_bar.setVisibility(View.GONE);
            progress_bar_toolbar.setVisibility(View.GONE);
        }
    }
}
