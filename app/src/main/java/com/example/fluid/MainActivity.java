package com.example.fluid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private MediaPlayerTileRenderer mediaPlayerTileRenderer;
    private MediaSuggestionsTileRenderer mediaSuggestionsTileRenderer;
    private MediaCombinedTileRenderer mediaCombinedTileRenderer;
    private NavTileRenderer navTileRenderer;

    boolean isNavSuggestion = true;
    boolean isMediaPlayerPresent = false;
    boolean isMediaSuggestionsActive = true;

    private ViewGroup newRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayerTileRenderer = new MediaPlayerTileRenderer();
        mediaSuggestionsTileRenderer = new MediaSuggestionsTileRenderer();
        mediaCombinedTileRenderer = new MediaCombinedTileRenderer();
        navTileRenderer = new NavTileRenderer();

//        setContentView(R.layout.main_media_suggestions_active);
        setContentView(R.layout.main_motionlayout_mediaplayer);
        newRoot = findViewById(R.id.root);

        render2();
    }

    private void render2() {
        navTileRenderer.render(newRoot.findViewById(R.id.navigation_container), this, true);
        mediaCombinedTileRenderer.render(newRoot.findViewById(R.id.media_container), this);
    }

    private void render() {
        boolean isExpanded = isNavSuggestion ? false : true;
        navTileRenderer.render(newRoot.findViewById(R.id.navigation_container), this, isNavSuggestion);
        if (isMediaPlayerPresent) {
            newRoot.findViewById(R.id.media_player_container).setVisibility(View.VISIBLE);
            mediaPlayerTileRenderer.render(newRoot.findViewById(R.id.media_player_container), this, isMediaSuggestionsActive, isExpanded);
        }
        mediaSuggestionsTileRenderer.render(newRoot.findViewById(R.id.media_suggestions_container), this, !isMediaSuggestionsActive, isExpanded);

        if (isNavSuggestion) newRoot.findViewById(R.id.dismiss_button).setOnClickListener(v -> dismissNavSuggestion());

        if (!isMediaSuggestionsActive) {
            newRoot.findViewById(R.id.media_suggestions_container).getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            newRoot.findViewById(R.id.media_player_container).getLayoutParams().width = 0;
            newRoot.findViewById(R.id.media_suggestions_container).setOnClickListener(v -> switchActiveMediaTile());
        } else {
            newRoot.findViewById(R.id.media_player_container).getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            newRoot.findViewById(R.id.media_suggestions_container).getLayoutParams().width = 0;
            if (isMediaPlayerPresent) {
                newRoot.findViewById(R.id.media_player_container).setOnClickListener(v -> switchActiveMediaTile());
            } else {
                newRoot.findViewById(R.id.media_suggestions_container).setOnClickListener(v -> activateMediaPlayer());
            }
        }
    }

    private void dismissNavSuggestion() {
        isNavSuggestion = false;
        render();
    }

    private void switchActiveMediaTile() {
        isMediaSuggestionsActive = !isMediaSuggestionsActive;
        render();
    }

    private void activateMediaPlayer() {
        isMediaPlayerPresent = true;
        isMediaSuggestionsActive = false;
        render();
    }
}
