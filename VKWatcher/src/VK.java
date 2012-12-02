/*
 *   Copyright 2012 Aleksey Litvinov litvinov.aleks@gmail.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
*/

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class VK {

    public static String getResponse(String id) throws IOException {
        String response = "";
        int c;
        URL hp = new URL("https://api.vkontakte.ru/method/getProfiles?uid=" + id + "&fields=online");
        URLConnection hpCon = hp.openConnection();
        int len = hpCon.getContentLength();
        if (len > 0){
            InputStream input = hpCon.getInputStream();
            int i = len;
            while (((c = input.read()) != -1) && (--i > 0)){
                response = response + (char) c;
            }
            input.close();
            System.out.println(response);
        } else {
            System.out.println("No content");
        }

        return response;

    }


    public static boolean isOnline(String response){
        int onlinePosition = response.indexOf("online") + 8;
        int onlineStatus = Integer.parseInt(response.charAt(onlinePosition) +"");
        if (onlineStatus == 1){
            return true;
        } else {
            return false;
        }
    }

    public static String getOnlineStatus(boolean isOnline){
        String onlineStatus;
        if (isOnline){
            onlineStatus = "online";
        } else {
            onlineStatus = "offline";
        }

        return onlineStatus;
    }

}
