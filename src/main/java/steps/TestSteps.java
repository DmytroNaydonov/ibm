package steps;

import context.ContextHolder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Joke;
import rest.JokeService;

import static org.assertj.core.api.Assertions.*;

@Slf4j
//@RequiredArgsConstructor
public class TestSteps {

    private final ContextHolder contextHolder;
    private final JokeService jokeService;

    public TestSteps(ContextHolder contextHolder, JokeService jokeService) {
        this.contextHolder = contextHolder;
        this.jokeService = jokeService;
    }

    @When("I send the rest request")
    public void iSendTheRestRequest() {
        jokeService.getRandomJoke();
    }

    @And("response is valid")
    public void jokeIsValid() {
        var response = contextHolder.getScenarioContext().getLastResponse();
        var apiResponse =  response.as(Joke.class);
        assertThat(apiResponse.getId()).as("Joke is valid").isNotNull();
    }

    @Then("I get the response and code is {int}")
    public void iGetTheResponseAndCodeIs(int code) {
        var response = contextHolder.getScenarioContext().getLastResponse();
        assertThat(response.getStatusCode()).as("Status code is not %s".formatted(code)).isEqualTo(code);
    }
}
