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
        return result;
        
    }
}
