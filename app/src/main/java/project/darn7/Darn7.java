package project.darn7;

import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class Darn7 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}