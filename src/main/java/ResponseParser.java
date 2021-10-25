import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ResponseParser {

    public JSONArray getFacesArray(String json) {
        JSONObject o = new JSONObject(json);
        return o.getJSONArray("results")
                .getJSONObject(0)
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("faceDetection")
                .getJSONArray("faces");
    }

    public List<Rectangle> getFacesRects(JSONArray faces) {
        List<Rectangle> result = new LinkedList<>();
        for(int i = 0; i < faces.length(); i++) {
            JSONObject point1 = faces.getJSONObject(i)
                    .getJSONObject("boundingBox")
                    .getJSONArray("vertices")
                    .getJSONObject(0);
            JSONObject point2 = faces.getJSONObject(i)
                    .getJSONObject("boundingBox")
                    .getJSONArray("vertices")
                    .getJSONObject(2);

            Rectangle r = new Rectangle(new Point(point1.getInt("x"), point1.getInt("y")));
            r.add(new Point(point2.getInt("x"), point2.getInt("y")));
            result.add(r);
        }
        return result;
    }

    public List<Rectangle> getFacesRects(String json) {
        return this.getFacesRects(this.getFacesArray(json));
    }
}
