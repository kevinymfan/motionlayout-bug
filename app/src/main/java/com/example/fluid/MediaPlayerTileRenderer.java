package com.example.fluid;

import android.content.Context;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public class MediaPlayerTileRenderer {
    public void render(ViewGroup container, Context context, boolean isMinimized, boolean isExpanded) {
        ViewGroup newLayout;
        TransitionSet transition = new TransitionSet();//(TransitionSet) TransitionInflater.from(context).inflateTransition(R.transition.enter);
        Fade fade = new Fade();
        fade.setDuration(500);
        fade.setMode(Fade.MODE_IN);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(1000);
        changeBounds.setInterpolator(new LinearInterpolator());
        if (isMinimized) {
            newLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_player_minimized, null);
            fade.setMode(Fade.MODE_OUT);
            if (isExpanded) {
                newLayout.findViewById(R.id.space).getLayoutParams().height = context.getResources().getDimensionPixelSize(R.dimen.media_expanded_height);
            }
        } else if (isExpanded) {
            newLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_player_expanded, null);
        } else {
            newLayout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.media_player_tile, null);
        }
        transition.addTransition(fade);
        transition.addTransition(changeBounds);

        Scene scene = new Scene(container, newLayout);
        TransitionManager.go(scene, transition);
    }
}
