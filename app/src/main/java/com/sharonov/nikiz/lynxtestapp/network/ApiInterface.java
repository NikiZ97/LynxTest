package com.sharonov.nikiz.lynxtestapp.network;

import com.sharonov.nikiz.lynxtestapp.model.Article;
import com.sharonov.nikiz.lynxtestapp.model.NewsResponse;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("list.php")
    Observable<NewsResponse> getNewsByCategory(@Query("category") String category);

    @GET("post.php")
    Observable<Article> getArticle(@Query("article") String article);
}
