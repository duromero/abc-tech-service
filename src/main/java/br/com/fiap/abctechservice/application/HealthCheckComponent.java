package br.com.fiap.abctechservice.application;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class HealthCheckComponent {

    public String getVersion(){
        Properties properties = this.getArquivoProperties();
        return  properties.getProperty("build.version");
    }

    public String getName(){
        Properties properties = this.getArquivoProperties();
        return properties.getProperty("build.name") ;
    }

    public String getNamePlusVersion(){
        return this.getName() + " - " + this.getVersion();
    }

    private Properties getArquivoProperties() {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.yml");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
