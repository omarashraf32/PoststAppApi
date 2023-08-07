package com.example.postsapi.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.postsapi.R;
import com.example.postsapi.data.model.PostResponseItem;
import com.example.postsapi.data.source.remote.RetrofitClient;
import com.example.postsapi.databinding.FragmentPostDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailsFragment extends Fragment {


    private FragmentPostDetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       int postId = PostDetailsFragmentArgs.fromBundle(getArguments())
                .getPostid();

        RetrofitClient.getWebService()
                .getpost(postId)
                .enqueue(new Callback<PostResponseItem>() {
                    @Override
                    public void onResponse(Call<PostResponseItem> call, Response<PostResponseItem> response) {
                        Log.d("ddddddddd", "onResponse: "+response.body());

                        if (response.isSuccessful())
                            initUI(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostResponseItem> call, Throwable t) {
                        Toast.makeText(requireContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding =FragmentPostDetailsBinding.bind(view);
    }

    private void initUI(PostResponseItem post){
        binding.txtPostId.setText(String.valueOf(post.getId()));
        binding.txtUserId.setText(String.valueOf(post.getUserId()));
        binding.txtPostDeac.setText(post.getBody());
        binding.txtPostTitile.setText(post.getTitle());




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding =null;
    }
}