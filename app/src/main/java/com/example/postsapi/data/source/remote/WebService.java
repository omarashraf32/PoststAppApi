package com.example.postsapi.data.source.remote;

import com.example.postsapi.data.model.PostResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET("posts")
    Call<PostResponse> getPosts();
}
