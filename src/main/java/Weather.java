import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

//fb3381fae60669ea4ae81918bb01859c
public class Weather {
    public static String getWeather(String message, Models model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=fb3381fae60669ea4ae81918bb01859c");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i =0;i<getArray.length();i++){
            JSONObject odj = getArray.getJSONObject(i);
            model.setIcon((String) odj.get("icon"));
            model.setMain((String) odj.get("main"));

        }return "City: " + model.getName()+"\n"+
                "temperature: "+model.getTemp()+"C"+"\n"+
                "Humidity: "+model.getHumidity()+"%"+"\n"+
                "https://openweathermap.org/img/w/"+model.getIcon()+".png";


    }
}
