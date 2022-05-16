package utility;

import com.google.gson.JsonObject;

import java.io.DataOutputStream;
import java.io.IOException;

public class RequestJsonBuilder {
    private static DataOutputStream outputStream;
    private JsonObject requestJson;

    public RequestJsonBuilder()
    {
        requestJson=new JsonObject();
    }
    public static void setOutputStream(DataOutputStream outputStream)
    {
        RequestJsonBuilder.outputStream=outputStream;
    }


    public RequestJsonBuilder setType(String type)
    {
        requestJson.addProperty("type",type);
        return this;
    }
    public RequestJsonBuilder addProperty(String key,String value)
    {
        requestJson.addProperty(key,value);
        return this;
    }
    public void send()
    {
        try {
            RequestJsonBuilder.outputStream.writeUTF(requestJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(JsonObject object)
    {
       requestJson=object;
       send();
    }
}
