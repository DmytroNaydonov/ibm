package rest;

import context.ContextHolder;
import context.GlobalContext;
import context.ScenarioContext;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseRestService {

    protected final ContextHolder contextHolder;
    private final GlobalContext globalContext;
    protected final ScenarioContext scenarioContext;

    public BaseRestService(ContextHolder contextHolder) {
        this.contextHolder = contextHolder;
        this.globalContext = contextHolder.getGlobalContext();
        this.scenarioContext = contextHolder.getScenarioContext();
    }

    public Response request(RequestSpecification specification, Method method, String endpoint) {
        return this.scenarioContext.saveResponse(
                specification
                        .when()
                        .log()
                        .all()
                        .request(method, globalContext.getUrl() + endpoint)
                        .then()
                        .log()
                        .all()
                        .extract()
                        .response()
        );
    }
}
