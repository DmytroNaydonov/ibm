package context;

import lombok.Data;
import org.apache.commons.configuration2.Configuration;

@Data
public class ContextHolder {

    private GlobalContext globalContext = new GlobalContext();
    private ScenarioContext scenarioContext = new ScenarioContext();
    private Configuration properties;

    public void resetScenarioContext() {
        this.scenarioContext = new ScenarioContext();
    }


}
