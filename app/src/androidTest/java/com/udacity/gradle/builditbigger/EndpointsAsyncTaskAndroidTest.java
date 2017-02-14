package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.v4.util.Pair;

import com.example.TellJokes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Simon on 15-Feb-17.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTaskPostExecute() throws ExecutionException, InterruptedException {
        TellJokes jokes = new TellJokes();
        String result = new EndpointsAsyncTask(null).execute(
                new Pair<Context, String>(mActivityRule.getActivity(), jokes.getTheJoke())).get();
        assertEquals(result.length(), 47);
    }
}
