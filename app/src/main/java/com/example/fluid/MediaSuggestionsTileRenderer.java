package com.example.fluid;

import android.content.Context;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public class MediaSuggestionsTileRenderer {
    public void render(ViewGroup container, Context context, boolean isMinimized, boolean isExpanded) {
        ViewGroup newLayout;
        Transition transition = TransitionInflater.from(context).inflateTransition(R.transition.enter);
        if (isMinimized) {
            newLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_suggestion_minimized, null);
            transition = TransitionInflater.from(context).inflateTransition(R.transition.exit);
            if (isExpanded) {
                newLayout.findViewById(R.id.space).getLayoutParams().height = context.getResources().getDimensionPixelSize(R.dimen.media_expanded_height);
            }
        } else if (isExpanded) {
            newLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_suggestion_tile_expanded, null);
        } else {
            newLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_suggestion_tile, null);
        }

        Scene scene = new Scene(container, newLayout);
        TransitionManager.go(scene, transition);
    }
}
