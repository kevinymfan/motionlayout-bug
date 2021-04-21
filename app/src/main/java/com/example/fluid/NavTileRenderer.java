package com.example.fluid;

import android.content.Context;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class NavTileRenderer {
    boolean firstRender = true;
    boolean isHasSuggestion = true;
    public void render(ViewGroup container, Context context, boolean hasSuggestion) {
        if (firstRender || isHasSuggestion != hasSuggestion) {
            ViewGroup newLayout = (ViewGroup) LayoutInflater.from(context).inflate(hasSuggestion ? R.layout.nav_suggestion_tile : R.layout.nav_search_tile, null);
            Scene newScene = new Scene(container, newLayout);
            Transition transition = TransitionInflater.from(context).inflateTransition(R.transition.exit);
            TransitionManager.go(newScene, transition);
            firstRender = false;
            isHasSuggestion = hasSuggestion;
        }
    }
}
