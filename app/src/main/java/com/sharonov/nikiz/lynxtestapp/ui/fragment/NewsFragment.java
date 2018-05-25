package com.sharonov.nikiz.lynxtestapp.ui.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sharonov.nikiz.lynxtestapp.R;
import com.sharonov.nikiz.lynxtestapp.adapter.NewsAdapter;
import com.sharonov.nikiz.lynxtestapp.model.NewsItem;
import com.sharonov.nikiz.lynxtestapp.network.ApiClient;
import com.sharonov.nikiz.lynxtestapp.network.ApiInterface;
import com.sharonov.nikiz.lynxtestapp.ui.activity.DetailNewsActivity;
import com.sharonov.nikiz.lynxtestapp.util.RecyclerItemClickListener;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsFragment extends Fragment {

    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    public NewsFragment() {
    }

    public static NewsFragment newInstance(String sportKind) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("item", sportKind);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String sportKInd = getArguments().getString("item");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final ArrayList[] news = new ArrayList[]{new ArrayList<>()};
        compositeDisposable.add(apiInterface.getNewsByCategory(sportKInd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(newsResponse -> {
                            news[0] = newsResponse.getEvents();
                            NewsAdapter newsAdapter = new NewsAdapter(newsResponse.getEvents());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(newsAdapter);
                        },
                        this::handleError));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ArrayList<NewsItem> items = (ArrayList<NewsItem>) news[0];
                        compositeDisposable.add(apiInterface.getArticle(items.get(position).getArticle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(article -> {
                            Intent intent = new Intent(getContext(), DetailNewsActivity.class);
                            intent.putExtra("article", article);
                            intent.putExtra("article_item_one_text", article.getArticleItems().get(0).getText());
                            intent.putExtra("article_item_two_text", article.getArticleItems().get(1).getText());
                            intent.putExtra("article_item_three_header", article.getArticleItems().get(2).getHeader());
                            intent.putExtra("article_item_three_text", article.getArticleItems().get(2).getText());
                            startActivity(intent);
                        }, t -> handleError(t)));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {}
                })
        );
        return view;
    }

    /**
     * As server errors are undefined, the common toast appears
     */
    private void handleError(Throwable t) {
        String jsonError = "java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $";
        if (t.getMessage().equals(jsonError)) {
            Toast.makeText(getContext(), "Невозможно открыть страницу: неверный формат данных",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
