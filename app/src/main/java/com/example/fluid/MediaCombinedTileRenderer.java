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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.constraintlayout.motion.widget.MotionLayout;

public class MediaCombinedTileRenderer {
    public void render(ViewGroup container, Context context) {
        container.removeAllViews();
        LayoutInflater.from(context).inflate(R.layout.media_player_motionlayout, container, true);
        MotionLayout ml = container.findViewById(R.id.body);
        ml.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                motionLayout.findViewById(R.id.player_album).setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if (i == R.id.minimized) {
                    motionLayout.findViewById(R.id.player_album).setVisibility(View.GONE);
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
    }
}
