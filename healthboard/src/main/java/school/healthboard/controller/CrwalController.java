package school.healthboard.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.json.Json;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.healthboard.entity.dto.CrwalResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CrwalController {


    //부위별 운동 크롤링
    @RequestMapping(value = "/crwal", method = RequestMethod.GET)
    public String crwal(@RequestParam(value = "search", defaultValue = "등 운동") String search, Model model) throws UnsupportedEncodingException, ParseException {

        search = URLEncoder.encode(search, "UTF-8");
        String url = "http://127.0.0.1:5000/crwal/"+search;
//        url = URLEncoder.encode(url, "UTF-8");
        String sb = "";
        System.out.println(url);
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb = sb + line + "\n";
            }
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("=====================");
        System.out.println(sb);
        System.out.println("=====================");
        //크롤링 결과인 String -> json 으로 변경
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(sb);
        JSONObject jsonObj = (JSONObject) obj;

        ArrayList<String> title_list = (ArrayList<String>) jsonObj.get("title");
        ArrayList<String> url_list = (ArrayList<String>) jsonObj.get("url");
        ArrayList<String> views_list = (ArrayList<String>) jsonObj.get("views");
        ArrayList<String> thumnail_list = (ArrayList<String>) jsonObj.get("thumnail");
//        ArrayList<String> channel_list = (ArrayList<String>) jsonObj.get("channel");


        ArrayList<CrwalResponseDto> result = new ArrayList<>();
        for (int i = 0; i < title_list.size(); i++) {
            String title = title_list.get(i);
            String uri = url_list.get(i);
            String view = views_list.get(i);
            String thumnail = thumnail_list.get(i);
//            String channel = channel_list.get(i);
            result.add(new CrwalResponseDto(title, uri, view, thumnail));
        }

        model.addAttribute("results", result);

        return "/front-end/crwal";
    }

}
