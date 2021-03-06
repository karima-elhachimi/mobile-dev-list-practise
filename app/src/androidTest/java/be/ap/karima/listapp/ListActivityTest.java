package be.ap.karima.listapp;


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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    public DataManager dm = DataManager.getInstance();
    @Rule
    public ActivityTestRule<ListActivity> mActivityTestRule = new ActivityTestRule<>(ListActivity.class);

    @Test
    public void listActivityTest() {
        /* //auto generated
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_add_item),
                        childAtPosition(
                                allOf(withId(R.id.include),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.text_main_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("New title"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.text_main_description),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_main_add), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

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
        // einde auto generated */



        String newTitle = "This is a title";
        String newDescription = "This is a new description";

        //eerst behaviour testen
        //fab om naar add new item te gaan
        onView(withId(R.id.fab_add_item)).perform(click());
        //title intypen en checken of de text correct ingetypt is
        onView(withId(R.id.text_main_title)).perform(typeText(newTitle)).check(matches(withText(containsString(newTitle))));
        //same als met title, maar ook de keyboard wegklikken
        onView(withId(R.id.text_main_description)).perform(typeText(newDescription), closeSoftKeyboard()).check(matches(withText(containsString(newDescription))));
        //button aanklikken om item toe te voegen
        onView(withId(R.id.button_main_add)).perform(click());

        //nu logica testen
        int laatstetoegevoegdeIndex = dm.list.size() - 1;
        MyItem toegevoegdeItem = dm.list.get(laatstetoegevoegdeIndex);

        assertEquals(newTitle, toegevoegdeItem.getmTitle());
        assertEquals(newDescription, toegevoegdeItem.getMyDescription());





    }


}
