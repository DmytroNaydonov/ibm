package steps;

import context.ContextHolder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static helpers.ConfigHelper.getConfig;

public class Hooks {

    private final ContextHolder contextHolder;

    public Hooks(ContextHolder contextHolder) {
        System.setProperty("allure.results.directory", "allure-results");
        this.contextHolder = contextHolder;
        var properties = getConfig();
        this.contextHolder.setProperties(properties);
        var globalContext = contextHolder.getGlobalContext();
        globalContext.setUrl(properties.getString("baseUrl"));
    }

    @Before
    public void initScenario(Scenario scenario) {
        contextHolder.getScenarioContext().setScenarioName(scenario.getName());
    }

    @After
    public void tearDownScenario() {
        contextHolder.resetScenarioContext();
    }
}
