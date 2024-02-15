package context;

import io.restassured.response.Response;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScenarioContext {

    private String scenarioName;
    private List<Response> responses = new ArrayList<>();
    private Response lastResponse;

    public Response saveResponse(Response response) {
        this.responses.add(response);
        this.lastResponse = response;
        return response;
    }
}
