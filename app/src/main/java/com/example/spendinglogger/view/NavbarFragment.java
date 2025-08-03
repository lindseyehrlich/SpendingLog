package com.example.spendinglogger.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.spendinglogger.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavbarFragment extends Fragment {
    private static final String TAG = "NavbarFragment";

    private BottomNavigationView bottomNavigationView;
    private int currentItemId = R.id.nav_dashboard; // Default to dashboard
    private boolean isNavigating = false; // Prevent multiple navigation attempts

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navbar, container, false);

        bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        // Update currentItemId to match the actual current activity
        currentItemId = getCurrentItemId();

        // Set the current selected item
        bottomNavigationView.setSelectedItemId(currentItemId);

        // Set up navigation listener with improved handling
        bottomNavigationView.setOnItemSelectedListener(
                item -> {
                    Log.d(
                            TAG,
                            "Navigation item selected: "
                                    + item.getItemId()
                                    + ", current: "
                                    + currentItemId
                                    + ", isNavigating: "
                                    + isNavigating);

                    // Prevent navigation if already navigating or already on this page
                    if (isNavigating || item.getItemId() == currentItemId) {
                        Log.d(TAG, "Navigation blocked - already navigating or on current page");
                        return true;
                    }

                    navigateToActivity(item.getItemId());
                    return true;
                });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");

        // Reset navigation state
        isNavigating = false;

        // Ensure the correct item is highlighted when the fragment becomes visible
        int correctItemId = getCurrentItemId();
        if (correctItemId != currentItemId) {
            Log.d(TAG, "Correcting navbar selection from " + currentItemId + " to " + correctItemId);
            currentItemId = correctItemId;
            if (bottomNavigationView != null) {
                bottomNavigationView.setSelectedItemId(currentItemId);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
        // Don't reset isNavigating here to prevent conflicts during transitions
    }

    /** Get the current item ID based on the current activity */
    private int getCurrentItemId() {
        if (getActivity() == null) return R.id.nav_dashboard;

        String activityName = getActivity().getClass().getSimpleName();
        Log.d(TAG, "Current activity: " + activityName);

        if ("MainActivity".equals(activityName)) {
            return R.id.nav_dashboard;
        } else if ("GoalsListActivity".equals(activityName)) {
            return R.id.nav_goals;
        } else if ("SpendingLogActivity".equals(activityName)) {
            return R.id.nav_spending_log;
        } else if ("SummaryActivity".equals(activityName)) {
            return R.id.nav_summary;
        } else if ("CategoriesActivity".equals(activityName)) {
            return R.id.nav_categories;
        } else {
            Log.d(TAG, "Unknown activity, defaulting to home: " + activityName);
            return R.id.nav_dashboard;
        }
    }

    /** Navigate to the appropriate activity with improved error handling */
    private void navigateToActivity(int itemId) {
        if (getActivity() == null) {
            Log.w(TAG, "Cannot navigate - activity is null");
            return;
        }

        if (isNavigating) {
            Log.w(TAG, "Navigation already in progress, ignoring request");
            return;
        }

        isNavigating = true;
        Log.d(TAG, "Starting navigation to item: " + itemId);

        Intent intent = null;
        String targetActivity = "";

        if (itemId == R.id.nav_dashboard) {
            intent = new Intent(getActivity(), MainActivity.class);
            targetActivity = "MainActivity";
        } else if (itemId == R.id.nav_goals) {
            intent = new Intent(getActivity(), GoalsListActivity.class);
            targetActivity = "GoalsListActivity";
        } else if (itemId == R.id.nav_spending_log) {
            intent = new Intent(getActivity(), SpendingLogActivity.class);
            targetActivity = "StudyLogActivity";
        } else if (itemId == R.id.nav_summary) {
            intent = new Intent(getActivity(), SummaryActivity.class);
            targetActivity = "ProgressActivity";
        } else if (itemId == R.id.nav_categories) {
            intent = new Intent(getActivity(), CategoriesActivity.class);
            targetActivity = "GroupStudyActivity";
        } else {
            Log.w(TAG, "Unknown navigation item: " + itemId);
            isNavigating = false;
            return;
        }

        if (intent != null) {
            Log.d(TAG, "Navigating to: " + targetActivity);

            // Update currentItemId before navigation
            currentItemId = itemId;

            // Use flags to ensure proper navigation behavior
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            try {
                startActivity(intent);

                // Determine animation direction
                int currentIndex = getItemIndex(getCurrentItemId());
                int targetIndex = getItemIndex(itemId);
                boolean moveToLeft = targetIndex < currentIndex;

                // Apply smooth slide animations
                if (moveToLeft) {
                    getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                } else {
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

                Log.d(TAG, "Navigation completed successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error during navigation", e);
                isNavigating = false;
            }
        } else {
            isNavigating = false;
        }
    }

    /** Get the index of a menu item for animation direction calculation */
    private int getItemIndex(int itemId) {
        if (itemId == R.id.nav_dashboard) {
            return 0;
        } else if (itemId == R.id.nav_goals) {
            return 1;
        } else if (itemId == R.id.nav_spending_log) {
            return 2;
        } else if (itemId == R.id.nav_summary) {
            return 3;
        } else if (itemId == R.id.nav_categories) {
            return 4;
        } else {
            return 0;
        }
    }

    /** Public method to set the selected item (useful when navigating from other places) */
    public void setSelectedItem(int itemId) {
        if (bottomNavigationView != null && !isNavigating) {
            Log.d(TAG, "Manually setting selected item: " + itemId);
            bottomNavigationView.setSelectedItemId(itemId);
            currentItemId = itemId;
        }
    }
}
