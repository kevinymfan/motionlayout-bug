# motionlayout-bug

Demonstrating MotionLayout bug in 2.1.0-beta01

Context: I want to animate an element fading out, and then set its visibility to GONE at the end state (since it will have click behaviors). I set visibilityMode to ignore in the ConstraintSet and then add a TransitionListener to the MotionLayout that sets the view to GONE in onTransitionCompleted for the start->end direction, and sets it back to VISIBLE in onTransitionStarted in either direction.

Expected behavior: In start->end direction, view should fade out and then be GONE when completed. In end->start direction, view should become VISIBLE when transition is started and fade into view.

Actual behavior: In start->end direction, the behavior is as expected. In the end->start direction, the view is obstensibly set to VISIBLE (I log the visibility of the view after setting it in onTransitionStarted), but the view does not appear on screen until the transition completes, whereupon it blinks into view.

Demo: https://photos.app.goo.gl/J1xCDtzXzcM1QSfBA

See MediaCombinedTileRenderer.java for TransitionListener, media_player_motionlayout.xml for MotionLayout, and media_player.xml for MotionScene.
