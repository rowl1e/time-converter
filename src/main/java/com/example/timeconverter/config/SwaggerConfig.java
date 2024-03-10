package com.example.timeconverter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        title = "time-converter", version = "1.0", license = @License(name = "MIT License", url = "https://github.com/rowl1e/time-converter/blob/master/LICENSE.md"), contact = @Contact(name = "Zakhar", url = "", email = "pstogg1898@gmail.com")), servers = {
        @Server(url = "http://localhost:8080", description = "Local Server")
})

public class SwaggerConfig {

}