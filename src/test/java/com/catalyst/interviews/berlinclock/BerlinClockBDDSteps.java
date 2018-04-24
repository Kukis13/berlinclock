package com.catalyst.interviews.berlinclock;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.catalyst.interviews.berlinclock.BerlinTimeClock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BerlinClockBDDSteps {

    private TimeConverter berlinClock = new BerlinTimeClock();
    private String time;

    @Given("^the time is (.*)$")
    public void theTimeIs(String time) {
        this.time = time;
    }

    @Then("^the clock should look like:$")
    public void theClockShouldLookLike(String expectedBerlinClockOutput) {
        assertThat(berlinClock.convertTime(time), is(expectedBerlinClockOutput));
    }
}
