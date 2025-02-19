package Octo.Servicios.ApiServices;

import Octo.Exceptions.OctoServiceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CotizacionesRequest {
    private static final String URL_API =
            "https://api.coingecko.com/api/v3/simple/price?ids=*&vs_currencies=usd";

    public static Map<String, Map<String, Double>> RequestSync(String ids) throws OctoServiceException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_API.replace("*", ids)))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            if (respuesta.statusCode() == 200) {
                return parsearYMostrarPrecios(respuesta.body());
            } else {
                throw new OctoServiceException("Error al obtener los precios. Código de estado: " + respuesta.statusCode());
            }
        } catch (Exception e) {
            throw new OctoServiceException("Error de conexión con el mercado actual de criptomonedas. Intente nuevamente más tarde.", e);
        }
    }

    private static Map<String, Map<String, Double>> parsearYMostrarPrecios(String cuerpoRespuesta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(cuerpoRespuesta, new TypeReference<Map<String, Map<String, Double>>>() {});
        } catch (Exception e) {
            System.out.println("Error al parsear el JSON: " + e.getMessage());
            return Collections.emptyMap();
        }
    }
}