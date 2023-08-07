package com.example.postsapi.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postsapi.R;
import com.example.postsapi.data.model.PostResponseItem;
import com.example.postsapi.data.source.remote.RetrofitClient;
import com.example.postsapi.databinding.FragmentPostsBinding;
import com.example.postsapi.ui.adpter.PostsAdpater;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment implements PostsAdpater.postAction {

    private FragmentPostsBinding binding;
    private PostsAdpater postsAdpater;

    private void initRecyclerviwe(){
        postsAdpater =new PostsAdpater(this);
        binding.postsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.postsRecycler.setAdapter(postsAdpater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding =FragmentPostsBinding.bind(view);
        initRecyclerviwe();
        fatchPosts();

    }

    private void fatchPosts(){
        RetrofitClient.getWebService()
                .getPosts().enqueue(new Callback<List<PostResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<PostResponseItem>> call, Response<List<PostResponseItem>> response) {
                        Log.d("ttttt", "onResponse: "+response.body());
                        if (response.isSuccessful())
                            postsAdpater.addposts(response.body());


                    }

                    @Override
                    public void onFailure(Call<List<PostResponseItem>> call, Throwable t) {
                        Log.d("ttttt", "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

    @Override
    public void postClick(PostResponseItem post) {
        Navigation.findNavController(getView())
                .navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment2(post.getId()));
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}