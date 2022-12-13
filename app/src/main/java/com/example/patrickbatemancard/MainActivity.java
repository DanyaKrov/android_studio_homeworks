package com.example.patrickbatemancard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<NewsItem> adapter;

    private final NewsItem[] news = new NewsItem[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news[0] = new NewsItem("Германия требует судетскую область. " +
                "Чемберлен продолжает политику умиротворения", 20, R.drawable.germany,
                new String[]{"кринж кринж",
                        "база база база"});
        news[1] = new NewsItem("Лига наций выражает крайнюю обеспокоенность " +
                "растущим итальянским милитаризмом", 20, R.drawable.italy,
                new String[]{"разработчики! добавьте возможность ловить покемонов в игру",
                        "какие покемоны?! меня эти покемоны уже задолбали"});
        news[2] = new NewsItem("Франсиско Франко тянет с" +
                " подписанием антикоминтерновского пакта", 20, R.drawable.spain,
                new String[]{"*скидывает аниме версию картинки с обложки",
                        "*спор левых и правых на 100000 комментариев", "не база"});
        news[3] = new NewsItem("Президент США Рузвельт высказался о великой депрессии и её последствиях на всё американское общество"
                , 20, R.drawable.usa,
                new String[]{"*скидывает аниме версию картинки с обложки",
                        "*спор левых и правых на 100000 комментариев", "прочитал - здоровья маме", "кринжобаза",
                "*гифка с патриком бейтманом"});
        news[4] = new NewsItem("СССР: Ежов продолжает свою работу по устранению троцкистов из всех государственных структур"
                , 20, R.drawable.ussr,
                new String[]{"*скидывает аниме версию картинки с обложки",
                        "*спор левых и правых на 100000 комментариев"});
        news[5] = new NewsItem("Чан Кайши вновь отметил важность борьбы с японской империей"
                , 20, R.drawable.china,
                new String[]{"*скидывает аниме версию картинки с обложки",
                        "*спор левых и правых на 100000 комментариев", "*гифка с хоумлендером"});
        listView = findViewById(R.id.list_view);
        adapter = new NewsAdapter(this, news);
        listView.setAdapter(adapter);
    }

    class NewsAdapter extends ArrayAdapter<NewsItem> {

        final NewsItem[] newsItems;
        boolean liked;

        public NewsAdapter(@NonNull Context context, NewsItem[] newsItems) {
            super(context, R.layout.news);
            this.newsItems = newsItems;
        }

        @Override
        public int getCount() {
            return newsItems.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            NewsItem newsItem = news[position];
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.news, null);
            }
            TextView textNews = convertView.findViewById(R.id.textView);
            textNews.setText(newsItem.aricle);

            TextView likeCount = convertView.findViewById(R.id.textLike);
            likeCount.setText(String.valueOf(newsItem.likes));

            TextView commentCount = convertView.findViewById(R.id.textComment);
            commentCount.setText(String.valueOf(newsItem.commentArray.length));

            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setImageResource(newsItem.picture);

            ImageButton likeButton = convertView.findViewById(R.id.imagelike);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!newsItem.liked) {
                        likeButton.setImageResource(R.drawable.like_activated);
                        newsItem.likes = newsItem.likes + 1;
                        newsItem.liked = true;
                    } else {
                        newsItem.likes = newsItem.likes - 1;
                        likeButton.setImageResource(R.drawable.like);
                        newsItem.liked = false;

                    }
                    likeCount.setText(String.valueOf(newsItem.likes));
                }
            });
            convertView.findViewById(R.id.imageComment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                    intent.putExtra("comments", newsItem.commentArray);
                    startActivity(intent);
                }
            });
            convertView.findViewById(R.id.imageShare).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Давайте не спамить друзьям новостями :)", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return convertView;
        }
    }
}