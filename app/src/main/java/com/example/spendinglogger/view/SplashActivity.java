package com.example.spendinglogger.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.spendinglogger.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 seconds
    private static final int ANIMATION_DELAY = 500; // 0.5 seconds

    private ImageView logoIcon;
    private TextView appTitle;
    private TextView appSubtitle;
    private TextView versionText;
    private Handler animationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide system UI for immersive experience
        hideSystemUI();

        initializeViews();
        startEntryAnimations();
        navigateToLoginAfterDelay();
    }

    private void hideSystemUI() {
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void initializeViews() {
        logoIcon = findViewById(R.id.logo_icon);
        appTitle = findViewById(R.id.app_title);
        appSubtitle = findViewById(R.id.app_subtitle);
        versionText = findViewById(R.id.version_text);

        // Initially hide all views for animation
        logoIcon.setAlpha(0f);
        logoIcon.setScaleX(0.5f);
        logoIcon.setScaleY(0.5f);
        appTitle.setAlpha(0f);
        appTitle.setTranslationY(50f);
        appSubtitle.setAlpha(0f);
        appSubtitle.setTranslationY(30f);
        versionText.setAlpha(0f);
    }

    private void startEntryAnimations() {
        new Handler(Looper.getMainLooper())
                .postDelayed(
                        () -> {
                            animateLogoEntry();
                        },
                        ANIMATION_DELAY);
    }

    private void animateLogoEntry() {
        // Logo animation with scale and fade
        ObjectAnimator logoAlpha = ObjectAnimator.ofFloat(logoIcon, "alpha", 0f, 1f);
        ObjectAnimator logoScaleX = ObjectAnimator.ofFloat(logoIcon, "scaleX", 0.5f, 1f);
        ObjectAnimator logoScaleY = ObjectAnimator.ofFloat(logoIcon, "scaleY", 0.5f, 1f);

        AnimatorSet logoSet = new AnimatorSet();
        logoSet.playTogether(logoAlpha, logoScaleX, logoScaleY);
        logoSet.setDuration(800);
        logoSet.setInterpolator(new DecelerateInterpolator());

        logoSet.addListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateTitleEntry();
                    }
                });

        logoSet.start();
    }

    private void animateTitleEntry() {
        // Title animation with slide up and fade
        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(appTitle, "alpha", 0f, 1f);
        ObjectAnimator titleTranslation = ObjectAnimator.ofFloat(appTitle, "translationY", 50f, 0f);

        AnimatorSet titleSet = new AnimatorSet();
        titleSet.playTogether(titleAlpha, titleTranslation);
        titleSet.setDuration(600);
        titleSet.setInterpolator(new DecelerateInterpolator());

        titleSet.addListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateSubtitleEntry();
                    }
                });

        titleSet.start();
    }

    private void animateSubtitleEntry() {
        // Subtitle animation with slide up and fade
        ObjectAnimator subtitleAlpha = ObjectAnimator.ofFloat(appSubtitle, "alpha", 0f, 1f);
        ObjectAnimator subtitleTranslation =
                ObjectAnimator.ofFloat(appSubtitle, "translationY", 30f, 0f);

        AnimatorSet subtitleSet = new AnimatorSet();
        subtitleSet.playTogether(subtitleAlpha, subtitleTranslation);
        subtitleSet.setDuration(500);
        subtitleSet.setInterpolator(new DecelerateInterpolator());

        subtitleSet.addListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateVersionEntry();
                    }
                });

        subtitleSet.start();
    }

    private void animateVersionEntry() {
        // Version text fade in
        ObjectAnimator versionAlpha = ObjectAnimator.ofFloat(versionText, "alpha", 0f, 1f);
        versionAlpha.setDuration(400);
        versionAlpha.setInterpolator(new DecelerateInterpolator());
        versionAlpha.start();
    }

    private void navigateToLoginAfterDelay() {
        new Handler(Looper.getMainLooper())
                .postDelayed(
                        () -> {
                            startExitAnimations();
                        },
                        SPLASH_DURATION);
    }

    private void startExitAnimations() {
        // Fade out the entire splash container
        ObjectAnimator fadeOut =
                ObjectAnimator.ofFloat(findViewById(R.id.splash_container), "alpha", 1f, 0f);
        fadeOut.setDuration(500);
        fadeOut.addListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                });
        fadeOut.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (animationHandler != null) {
            animationHandler.removeCallbacks(dotAnimationRunnable);
        }*/
    }

    @Override
    public void onBackPressed() {
        // Disable back button during splash screen
        // Do nothing to prevent users from going back
        super.onBackPressed();
    }
}