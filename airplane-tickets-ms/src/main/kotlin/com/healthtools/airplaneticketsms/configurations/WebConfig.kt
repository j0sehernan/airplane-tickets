package com.healthtools.airplaneticketsms.configurations

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Permite CORS a cualquier ruta (/** significa cualquier ruta)
            .allowedOrigins("http://localhost:5173") // Permite estos orígenes. Puedes añadir más llamando múltiples veces a este método o separando los orígenes con comas
            .allowedMethods(
                "GET",
                "POST",
                "PUT",
                "DELETE"
            ) // Permite estos métodos. Añade cualquier método que necesites
            .allowedHeaders("*") // Permite todos los encabezados
            .allowCredentials(true) // Permite credenciales
    }
}
