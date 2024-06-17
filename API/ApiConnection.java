package API;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiConnection {

    private static final Logger LOGGER = Logger.getLogger(ApiConnection.class.getName());

    public JSONObject apiCEP(String cep) {
        String urlAPI = "https://viacep.com.br/ws/" + cep + "/json/";
        JSONObject responseJson = null;
        JSONObject filtroAPI = new JSONObject();
        HttpURLConnection conn = null;
        BufferedReader inputCEP = null;

        try {
            URL url = new URL(urlAPI);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("Accept", "application/json");

            int retornoAPIcod = conn.getResponseCode();

            if (retornoAPIcod == 400) {
                LOGGER.log(Level.WARNING, "CEP inválido, verifique se não há:\n- Espaços\n- Letras\n- Dígitos a mais");
                return null;
            } else if (retornoAPIcod != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Falha: Erro HTTP: " + retornoAPIcod);
            }

            inputCEP = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder outputCEP = new StringBuilder();
            String output;

            while ((output = inputCEP.readLine()) != null) {
                outputCEP.append(output);
            }

            responseJson = new JSONObject(outputCEP.toString());

            if (responseJson.has("cep") && responseJson.has("logradouro") &&
                    responseJson.has("bairro") && responseJson.has("localidade") &&
                    responseJson.has("uf")) {

                filtroAPI.put("cep", responseJson.getString("cep"));
                filtroAPI.put("logradouro", responseJson.getString("logradouro"));
                filtroAPI.put("bairro", responseJson.getString("bairro"));
                filtroAPI.put("localidade", responseJson.getString("localidade"));
                filtroAPI.put("uf", responseJson.getString("uf"));

            } else {
                LOGGER.log(Level.WARNING, "Resposta da API não contém todos os campos necessários.");
                return null;
            }

        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "URL inválida! Verifique o CEP e tente novamente.", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro de I/O ocorreu ao tentar conectar com a API.", e);
            throw new RuntimeException(e);
        } finally {
            if (inputCEP != null) {
                try {
                    inputCEP.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Erro ao fechar BufferedReader.", e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return filtroAPI;
    }

    public void printCepData(String cep) {
        JSONObject resultado = apiCEP(cep);
        if (resultado != null) {
            System.out.println("Dados do CEP:");
            System.out.println("CEP: " + resultado.optString("cep"));
            System.out.println("Logradouro: " + resultado.optString("logradouro"));
            System.out.println("Bairro: " + resultado.optString("bairro"));
            System.out.println("Localidade: " + resultado.optString("localidade"));
            System.out.println("UF: " + resultado.optString("uf"));
        } else {
            System.out.println("Não foi possível obter os dados do CEP ou os dados retornados são incompletos.");
        }
    }
}