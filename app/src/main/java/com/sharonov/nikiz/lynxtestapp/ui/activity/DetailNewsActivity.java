package com.sharonov.nikiz.lynxtestapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sharonov.nikiz.lynxtestapp.R;
import com.sharonov.nikiz.lynxtestapp.model.Article;

public class DetailNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Article article = intent.getParcelableExtra("article");
        String prediction = article.getPrediction();
        String itemOneText = intent.getStringExtra("article_item_one_text");
        String itemTwoText = intent.getStringExtra("article_item_two_text");
        String itemThreeHeader = intent.getStringExtra("article_item_three_header");
        String itemThreeText = intent.getStringExtra("article_item_three_text");
        toolbar.setTitle(article.getTeam1() + " - " + article.getTeam2());

        TextView teamOneTextView = findViewById(R.id.team_one);
        TextView teamTwoTextView = findViewById(R.id.team_two);
        TextView tournament = findViewById(R.id.tournament);
        TextView place = findViewById(R.id.place);
        TextView firstTeamName = findViewById(R.id.first_team_name);
        TextView secondTeamName = findViewById(R.id.second_team_name);
        TextView secondTeamInfo = findViewById(R.id.second_team_info);
        TextView firstTeamInfo = findViewById(R.id.first_team_info);
        TextView predictionText = findViewById(R.id.prediction_text);
        TextView predictionTextView = findViewById(R.id.prediction_title);
        TextView predictionCoefficients = findViewById(R.id.prediction_coefficients);
        teamOneTextView.setText(article.getTeam1());
        teamTwoTextView.setText(article.getTeam2());
        place.setText(article.getPlace());
        tournament.setText(article.getTournament());
        firstTeamName.setText(article.getTeam1());
        firstTeamInfo.setText(itemOneText);
        secondTeamName.setText(article.getTeam2());
        secondTeamInfo.setText(itemTwoText);
        predictionText.setText(itemThreeHeader);
        predictionTextView.setText(itemThreeText);
        predictionCoefficients.setText(prediction);
    }
}
