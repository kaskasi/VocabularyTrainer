package de.fluchtwege.vocabulary.lessons;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fluchtwege.vocabulary.R;
import tools.fastlane.screengrab.Screengrab;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LessonsActivityTest {

    @Rule
    public ActivityTestRule<LessonsActivity> mActivityTestRule = new ActivityTestRule<>(LessonsActivity.class);

    @Test
    public void lessonsActivityTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.add_lesson), withContentDescription("Add A Lesson"),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        Screengrab.screenshot("please_now");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.lesson_name),

                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.lesson_name),

                        isDisplayed()));
        appCompatEditText2.perform(replaceText("lesson1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.lesson_description),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("st"), closeSoftKeyboard());

        Screengrab.screenshot("please");

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.save_lesson), withText("Save"),
                        isDisplayed()));
        appCompatTextView.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
