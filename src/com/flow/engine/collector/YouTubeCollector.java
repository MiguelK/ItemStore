package com.flow.engine.collector;

import java.util.List;

//FIXME
public class YouTubeCollector {

//    Result result = JParser.parse("sjdlksjdlsjdlks https://www.youtube.com/watch?v=RgKAFK5djSk DDDDhagsas" +
    //          " https://www.youtube.com/watch?v=klXyt13nFOk#action=share AAAAAdjsdkjsd");


    public String getVideoId() {

        String videoId = null;
        List<String> parts = null; //Arrays.asList(url.split("v="));

        for (int i = 0; i < parts.size(); i++) {
            String part = parts.get(i);
            if (part.contains("watch?")) {
                videoId = parts.get(i + 1);
//                videoId = string;
                int xxx = videoId.indexOf("#");

                if (xxx != -1) {
                    videoId = videoId.substring(0, xxx);
                }
            }
        }

      /*  for (String string : parts) {
            if (string.contains("watch?")) {
                videoId = string;
                int xxx = string.indexOf("#");

                if (xxx != -1) {
                    videoId = string.substring(0, xxx);
                }
            }
        }*/

        //FIXME delegate to firt item in List<YouTubeVideoContent>
        return videoId;
    }


}
