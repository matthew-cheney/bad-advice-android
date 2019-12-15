package master.cheney.badadvice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerProxy {

    String urlBase = "http://";
    String serverHost = "10.0.2.2";
    String serverPort = "4000";

    public String getAdvice(String question) {

        try {
            String underscore_question = question.replaceAll(" ", "_");
            URL url = new URL(urlBase + serverHost + ":" + serverPort + "/get_advice/" + underscore_question);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // connection.setRequestProperty("Authorization", authToken);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "No. Next question.";

    }

}
