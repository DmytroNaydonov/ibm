package helpers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

@Slf4j
public class ConfigHelper {

    private static Configuration config;

    private static void loadConfiguration() {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName("src/main/config.properties"));
        try
        {
            config = builder.getConfiguration();
        }
        catch(ConfigurationException cex)
        {
             log.info("loading of the configuration file failed");
        }
    }

    public static Configuration getConfig() {
        if (config == null) {
            loadConfiguration();
        }
        return config;
    }
}
