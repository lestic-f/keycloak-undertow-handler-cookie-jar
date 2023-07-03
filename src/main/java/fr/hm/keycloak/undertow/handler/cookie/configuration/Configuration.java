package fr.hm.keycloak.undertow.handler.cookie.configuration;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Configuration {

    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    private Configuration() { }

    public static Properties getProperties() {
        final Properties properties = new Properties();
        try {
            properties.load(Configuration.class.getResourceAsStream("/application.properties"));
            return properties;
        } catch (IOException e) {
            LOG.error("Impossible de charger le fichier de properties");
            throw new IllegalStateException(e);
        }
    }

}
