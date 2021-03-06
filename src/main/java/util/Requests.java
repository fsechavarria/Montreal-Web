package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class Requests {
    
    private static final String baseUrl = "http://localhost:3000/";
    
    /**
     * Método genérico para instanciar los resultados de las distintas peticiones HTTP
     * @param method Metodo de la petición HTTP - GET POST PUT DELETE
     * @param action Acción de la petición HTTP - Ej; "usuario/1"
     * @param object Nombre del objeto que retornará la petición. Ej "usuario/1" retorna objetos con la llave "usuario".
     * @param body JSONObject con el cuerpo de la petición. Solo se usa para POST y PUT, null para los demás
     * @param cls Class que se usará para instanciar los resultados. Ej: "usuario" retorna array de usuarios por lo que class será Usuario.Class
     * @param token Token de autorización JWT
     * @return ArrayList con los objetos instanciados. Vacío en caso de errores.
     */
    public ArrayList requestController (String method, String action, String object, JSONObject body, Class cls, String token) {
        try {
            
            /**
             * GET
             */
            if (method.toUpperCase().equals("GET")) {
                JSONArray res = null;
                res = this.GET(action, object, token);
                if (res != null) {
                    ArrayList<Object> lstResult = new ArrayList();
                    
                    for (int i = 0; i < res.length(); i++) {
                        JSONObject j = res.getJSONObject(i);
                        Iterator<String> keys = j.keys();
                        Object obj = cls.newInstance();
                        Method[] methods = cls.getDeclaredMethods();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            Object val = j.get(key);
                            for (Method m : methods) {
                                if (m.getName().toLowerCase().equals("set" + key.toLowerCase())) {
                                    if (val.getClass().equals(String.class)) {
                                        byte[] encode = val.toString().getBytes();
                                        val = new String(encode, "UTF-8");
                                    }
                                    if (val == JSONObject.NULL) {
                                        m.invoke(obj, new Object[]{null});
                                        break;
                                    } else {
                                        m.invoke(obj, val);
                                        break;
                                    }
                                }
                            }
                        }
                        lstResult.add(obj);
                    }
                    return lstResult;
                }
                return new ArrayList();
            } /** FIN GET **/
            
            /**
             * POST
             */
            if (method.toUpperCase().equals("POST")) {
                JSONObject res = null;
                String bd = body == null ? "{}" : body.toString();
                res = this.POST(action, bd, object, token);
                if (res != null) {
                    ArrayList<Object> lstResult = new ArrayList();
                    Iterator<String> keys = res.keys();
                    
                    Object obj = cls.newInstance();
                    Method[] methods = cls.getDeclaredMethods();
                    while(keys.hasNext()){
                        String key = keys.next();
                        Object val = res.get(key);
                        for(Method m : methods) {
                            if (m.getName().toLowerCase().equals("set" + key.toLowerCase())) {
                                if (val.getClass().equals(String.class)) {
                                    byte[] encode = val.toString().getBytes();
                                    val = new String(encode, "UTF-8");
                                }
                                if (val == JSONObject.NULL) {
                                    m.invoke(obj, new Object[]{null});
                                    break;
                                } else {
                                    m.invoke(obj, val);
                                    break;
                                }
                            }
                        }
                    }
                    lstResult.add(obj);
                    return lstResult;
                }
                return new ArrayList();
            } /** FIN POST **/
            
            /**
             * PUT
             */
            if (method.toUpperCase().equals("PUT")) {
                JSONObject res = null;
                String bd = body == null ? "{}" : body.toString();
                res = this.PUT(action, bd, object, token);
                if (res != null) {
                    ArrayList<Object> lstResult = new ArrayList();
                    Iterator<String> keys = res.keys();
                    
                    Object obj = cls.newInstance();
                    Method[] methods = cls.getDeclaredMethods();
                    while(keys.hasNext()){
                        String key = keys.next();
                        Object val = res.get(key);
                        for(Method m : methods) {
                            if (m.getName().toLowerCase().equals("set" + key.toLowerCase())) {
                                if (val.getClass().equals(String.class)) {
                                    byte[] encode = val.toString().getBytes();
                                    val = new String(encode, "UTF-8");
                                }
                                if (val == JSONObject.NULL) {
                                    m.invoke(obj, new Object[]{null});
                                    break;
                                } else {
                                    m.invoke(obj, val);
                                    break;
                                }
                            }
                        }
                    }
                    lstResult.add(obj);
                    return lstResult;
                }
                return new ArrayList();
            } /** FIN PUT **/
            
            /** 
             * DELETE
             */
            if (method.toUpperCase().equals("DELETE")) {
                JSONObject res = null;
                res = this.DELETE(action, object, token);
                if (res != null) {
                    ArrayList<Object> lstResult = new ArrayList();
                    Iterator<String> keys = res.keys();
                    
                    Object obj = cls.newInstance();
                    Method[] methods = cls.getDeclaredMethods();
                    while(keys.hasNext()){
                        String key = keys.next();
                        Object val = res.get(key);
                        for(Method m : methods) {
                            if (m.getName().toLowerCase().equals("set" + key.toLowerCase())) {
                                if (val.getClass().equals(String.class)) {
                                    byte[] encode = val.toString().getBytes();
                                    val = new String(encode, "UTF-8");
                                }
                                if (val == JSONObject.NULL) {
                                    m.invoke(obj, new Object[]{null});
                                    break;
                                } else {
                                    m.invoke(obj, val);
                                    break;
                                }
                            }
                        }
                    }
                    lstResult.add(obj);
                    return lstResult;
                }
                return new ArrayList();
            } /** FIN DELETE **/
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    /**
     * Método para autenticarse y conseguir token de autenticación.
     * @param body
     * @return JWT Token para la autenticación.
     */
    public String auth (JSONObject body) {
        try {
            // Configuración del request
            String url = baseUrl + "auth";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Opcional, GET es por defecto.
            con.setRequestMethod("POST");
            // Request Headers
            con.setRequestProperty("Content-type" , "application/json; charset=UTF-8");
            con.setDoOutput(true);
            
            // Set request body
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body.toString());
            wr.flush();
            wr.close();
            
            // Codigo del response. 200 = Ok.
            int responseCode = con.getResponseCode();
            
            // Preparación para obtener datos del response.
            BufferedReader in;
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    JSONObject jObj = new JSONObject(response.toString());
                    boolean err = jObj.getBoolean("error");
                    jObj = jObj.getJSONObject("data");
                    
                    if (!err) {
                        return jObj.get("token").toString();
                    } else {
                        System.out.println(jObj.getJSONObject("message"));
                        return null;
                    } // Error check
                } else {
                    System.out.println("No response received");
                    return null;
                } // Response check
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    JSONObject jObj = new JSONObject(response.toString());
                    jObj = jObj.getJSONObject("data");
                    System.out.println(jObj.getString("message"));
                    return null;
                } else {
                    System.out.println("Error");
                    return null;
                }
            } // Response code check
            
        } catch (MalformedURLException | ProtocolException me){
            System.out.println(me.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    /**
     * Método genérico para hacer peticiones HTTP GET
     * @param action - URL a la que se hará el request. Ej; "usuario/1".
     * @param object - Llave del objeto que se obtendrá. Ej; GET a /usuario/ devuelve un JSON de la siguiente forma: { error: false, data: { usuario: [...]} }. En este caso el objeto sería "usuario".
     * @param token Token de autorización JWT
     * @return JSONArray con los resultados. Null en caso de error.
     */
    private JSONArray GET (String action, String object, String token) {
        try {
            // Configuración del request
            String url = baseUrl + action;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Opcional, GET es por defecto.
            con.setRequestMethod("GET");
            
            // Request Headers
            con.setRequestProperty("Content-type" , "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Bearer " + token);
            
            // Codigo del response. 200 = Ok.
            int responseCode = con.getResponseCode();
            
            // Preparación para obtener datos del response.
            BufferedReader in;
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            // Obtener datos del response, sea esta exitosa o erronea.
            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                if (!response.toString().equals("")) {
                    JSONObject jObj = new JSONObject(response.toString());
                    boolean err = jObj.getBoolean("error");
                    jObj = jObj.getJSONObject("data");
                    
                    if (!err) {
                        JSONArray jArray = jObj.getJSONArray(object);
                        return jArray;
                    } else {
                        try {
                            System.out.println(jObj.getString("message"));
                        } catch (Exception e){
                            System.out.println(jObj.toString());
                        }
                        return null;
                    } // Error check
                } else {
                    System.out.println("No response received");
                    return null;
                }// Response check
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                if (!response.toString().equals("")) {
                    try {
                        JSONObject jObj = new JSONObject(response.toString());
                        jObj = jObj.getJSONObject("data");
                        System.out.println(jObj.getString("message"));
                    } catch (Exception ex) {
                        System.out.println(response.toString());
                    }
                    return null;
                } else {
                    System.out.println("Error");
                    return null;
                }
            } // Response code check
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Método genérico para hacer peticiones HTTP POST
     * @param action - URL a la que se hará el request. Ej; "usuario/1".
     * @param body - Objecto JSON para el cuerpo de la petición.
     * @param object - Llave del objeto que se obtendrá. Ej; GET a /usuario/ devuelve un JSON de la siguiente forma: { error: false, data: { usuario: [...]} }. En este caso el objeto sería "usuario".
     * @param token Token de autorización JWT
     * @return JSONObject con el resultado. Null en caso de error.
     */
    private JSONObject POST(String action, String body, String object, String token){
        try {
            // Configuración del request
            String url = baseUrl + action;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Opcional, GET es por defecto.
            con.setRequestMethod("POST");
            // Request Headers
            con.setRequestProperty("Content-type" , "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setDoOutput(true);
            // Set request body
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();
            // Codigo del response. 200 = Ok.
            int responseCode = con.getResponseCode();
            
            // Preparación para obtener datos del response.
            BufferedReader in;
            String inputLine;
            StringBuilder response = new StringBuilder();
            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    JSONObject jObj = new JSONObject(response.toString());
                    boolean err = jObj.getBoolean("error");
                    jObj = jObj.getJSONObject("data");
                    
                    if (!err) {
                        return jObj.getJSONObject(object);
                    } else {
                        try {
                            System.out.println(jObj.getString("message"));
                        } catch (Exception e){
                            System.out.println(jObj.toString());
                        }
                        return null;
                    } // Error check
                } else {
                    System.out.println("No response received");
                    return null;
                } // Response check
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    try {
                        JSONObject jObj = new JSONObject(response.toString());
                        jObj = jObj.getJSONObject("data");
                        System.out.println(jObj.getString("message"));
                    } catch (Exception ex) {
                        System.out.println(response.toString());
                    }
                    return null;
                } else {
                    System.out.println("Error");
                    return null;
                }
            } // Response code check
            
        } catch (MalformedURLException | ProtocolException me){
            System.out.println(me.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Método genérico para hacer peticiones HTTP PUT
     * @param action - URL a la que se hará el request. Ej; "usuario/1". Debe poseer si o si una id(numerica) dentro de action, tal como en el ejemplo.
     * @param body - Objecto JSON para el cuerpo de la petición.
     * @param object - Llave del objeto que se obtendrá. Ej; GET a /usuario/ devuelve un JSON de la siguiente forma: { error: false, data: { usuario: [...]} }. En este caso el objeto sería "usuario".
     * @param token Token de autorización JWT
     * @return JSONObject con el resultado. Null en caso de error.
     */
    private JSONObject PUT(String action, String body, String object, String token) {
        try {
            // Configuración del request
            String url = baseUrl + action;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Opcional, GET es por defecto.
            con.setRequestMethod("PUT");
            // Request Headers
            con.setRequestProperty("Content-type" , "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setDoOutput(true);
            
            // Set request body
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();
            
            // Codigo del response. 200 = Ok.
            int responseCode = con.getResponseCode();
            
            // Preparación para obtener datos del response.
            BufferedReader in;
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    JSONObject jObj = new JSONObject(response.toString());
                    boolean err = jObj.getBoolean("error");
                    jObj = jObj.getJSONObject("data");
                    
                    if (!err) {
                        return jObj.getJSONObject(object);
                    } else {
                        try {
                            System.out.println(jObj.getString("message"));
                        } catch (Exception e){
                            System.out.println(jObj.toString());
                        }
                        return null;
                    } // Error check
                } else {
                    System.out.println("No response received");
                    return null;
                } // Response check
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    try {
                        JSONObject jObj = new JSONObject(response.toString());
                        jObj = jObj.getJSONObject("data");
                        System.out.println(jObj.getString("message"));
                    } catch (Exception ex) {
                        System.out.println(response.toString());
                    }
                    return null;
                } else {
                    System.out.println("Error");
                    return null;
                }
            } // Response code check
            
        } catch (MalformedURLException | ProtocolException me){
            System.out.println(me.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    /**
     * Método genérico para hacer peticiones HTTP DELETE
     * @param action - URL a la que se hará el request. Ej; "usuario/1". Debe poseer si o si una id(numerica) dentro de action, tal como en el ejemplo.
     * @param object - Llave del objeto que se obtendrá. Ej; GET a /usuario/ devuelve un JSON de la siguiente forma: { error: false, data: { usuario: [...]} }. En este caso el objeto sería "usuario".
     * @param token Token de autorización JWT
     * @return JSONObject con el resultado. Null en caso de error.
     */
    private JSONObject DELETE(String action, String object, String token) {
        try {
            // Configuración del request
            String url = baseUrl + action;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Opcional, GET es por defecto.
            con.setRequestMethod("DELETE");
            // Request Headers
            con.setRequestProperty("Content-type" , "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setDoOutput(true);
            
            // Codigo del response. 200 = Ok.
            int responseCode = con.getResponseCode();
            
            // Preparación para obtener datos del response.
            BufferedReader in;
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    JSONObject jObj = new JSONObject(response.toString());
                    boolean err = jObj.getBoolean("error");
                    jObj = jObj.getJSONObject("data");
                    
                    if (!err) {
                        return jObj.getJSONObject(object);
                    } else {
                        try {
                            System.out.println(jObj.getString("message"));
                        } catch (Exception e){
                            System.out.println(jObj.toString());
                        }
                        return null;
                    } // Error check
                } else {
                    System.out.println("No response received");
                    return null;
                } // Response check
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                if (!response.toString().equals("")) {
                    try {
                        JSONObject jObj = new JSONObject(response.toString());
                        jObj = jObj.getJSONObject("data");
                        System.out.println(jObj.getString("message"));
                    } catch (Exception ex) {
                        System.out.println(response.toString());
                    }
                    return null;
                } else {
                    System.out.println("Error");
                    return null;
                }
            } // Response code check
        } catch (MalformedURLException | ProtocolException me){
            System.out.println(me.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    public void sendFile(String action, String object, MultipartFile file, JSONObject body, String token){
        String charset = "UTF-8";
        
        String boundary = "------------------------" + Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.
        int responseCode = 0;

        try 
        {
            File binaryFile = new File(file.getOriginalFilename());
            binaryFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(binaryFile); 
            fos.write(file.getBytes());
            fos.close();
            
            //Set POST general headers along with the boundary string (the seperator string of each part)
            String url = baseUrl + action;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Opcional, GET es por defecto.
            con.setRequestMethod("POST");
            // Request Headers
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setDoOutput(true);

            
            OutputStream output = con.getOutputStream();
            PrintWriter writer  = new PrintWriter(new OutputStreamWriter(output, charset), true);
            
            // Send binary file - part
            // Part header
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"ant\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: application/octet-stream").append(CRLF);
            writer.append(CRLF).flush();
            
            // File data
            Files.copy(binaryFile.toPath(), output);
            output.flush(); 

            // Other data
            Integer id = body.getInt("ID_FAMILIA");
            writer.append(CRLF).append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"ID_FAMILIA\"").append(CRLF);
            writer.append(CRLF).append(id.toString());
            
            writer.append(CRLF).append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"DESC_ANTECEDENTE\"").append(CRLF);
            writer.append(CRLF).append(body.getString("DESC_ANTECEDENTE"));
            // End of multipart/form-data.
            writer.append(CRLF).append("--" + boundary + "--").flush();
            
            
            responseCode = ((HttpURLConnection) con).getResponseCode();

            
            if(responseCode !=200) //We operate only on HTTP code 200
                return;
            
            System.out.println(responseCode);
            BufferedReader in;
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            if (responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
            }

            System.out.println(response.toString());
            

            InputStream Instream = ((HttpURLConnection) con).getInputStream();

            // Write PDF file 
            BufferedInputStream BISin = new BufferedInputStream(Instream);
            FileOutputStream FOSfile  = new FileOutputStream("asdf");
            BufferedOutputStream out  = new BufferedOutputStream(FOSfile);

            int i;
            while ((i = BISin.read()) != -1) {
                out.write(i);
            }

            // Cleanup
            out.flush();
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
