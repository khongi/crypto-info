package com.thiosin.cryptoinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;

import java.io.File;
import java.util.Objects;

public abstract class EspressoTest<T extends Activity> {

    @Rule
    public ActivityTestRule<T> activityRule;

    public EspressoTest(Class<T> activityClass) {
        setupActivityRule(activityClass);
        final MockApplication application = (MockApplication) InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        injectDependencies((AppTestComponent) application.injector);
    }

    @Before
    @CallSuper
    public void setup() {
        clearAppState();
        activityRule.launchActivity(new Intent());
    }

    protected void clearAppState() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        File root = targetContext.getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : Objects.requireNonNull(sharedPreferencesFileNames)) {
            targetContext
                    .getSharedPreferences(
                            fileName.replace(".xml", ""),
                            Context.MODE_PRIVATE
                    )
                    .edit().clear().commit();
        }
    }

    protected void setupActivityRule(Class<T> activityClass) {
        activityRule = new ActivityTestRule<>(activityClass, false, false);
    }

    public abstract void injectDependencies(@NonNull final AppTestComponent appAndroidTestComponent);

}
