package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {

            URL url = new URL("https://api.mercadopublico.cl/servicios/v1/publico/licitaciones.json?fecha=02022014&ticket=F8537A18-6766-4DEF-9E59-426B4FEE2844");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while( (line = reader.readLine()) != null){
                    response.append(line);
                }

                reader.close();

                System.out.println("Respuesta de la API: " + response.toString());

                connection.disconnect();


            } else {
                throw new RuntimeException("Error al conectarnos a la API code: " + responseCode);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
