package Utils;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateAPI {
    /**
     * Translates text using the unofficial Google Translate API.
     *
     * @param text       The text to translate.
     * @param sourceLang The source language code (e.g., "en" for English).
     * @param targetLang The target language code (e.g., "es" for Spanish).
     * @return The translated text or an error message if the translation fails.
     */
    public static String translateText(String text, String sourceLang, String targetLang) {
        try {
            // Construct the URL for the Google Translate API
            String urlStr = String.format(
                    Constants.TRANSLATEAPIURL,
                    sourceLang, targetLang, URLEncoder.encode(text, "UTF-8")
            );
            URL url = new URL(urlStr);

            // Set up HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Read the response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Parse JSON response to extract the translated text
            JSONArray jsonResponse = new JSONArray(response.toString());
            JSONArray translationsArray = jsonResponse.getJSONArray(0);
            JSONArray firstTranslation = translationsArray.getJSONArray(0);
            return firstTranslation.getString(0);

        } catch (Exception e) {
            System.err.println("Translation failed: " + e.getMessage());
            return "Translation failed.";
        }
    }
}

