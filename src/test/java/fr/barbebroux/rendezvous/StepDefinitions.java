package fr.barbebroux.rendezvous;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    @Given("Lucy is {int} mètres from Sean")
    public void lucyIsInRangeFromSean(int arg0) {
        throw new PendingException();
    }

    @When("Sean shout {string}")
    public void seanShout(String arg0) {
        throw new PendingException();
    }

    @Then("Lucy hear the message")
    public void lucyHearTheMessage() {
        throw new PendingException();
    }
}
