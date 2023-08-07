package com.example.postsapi.data.source.remote;

import com.example.postsapi.data.model.PostResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {
    @GET("posts")
    Call<List<PostResponseItem>> getPosts();

    @GET("posts/{id}")
    Call<PostResponseItem> getpost(@Path("id") int id);
}
