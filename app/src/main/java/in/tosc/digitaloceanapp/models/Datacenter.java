package in.tosc.digitaloceanapp.models;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class Datacenter {
    public static class center{
        String url;
        String city;

        public center(String url, String city) {
            this.url = url;
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static ArrayList<center> getCenter(){
        ArrayList<center> centers = new ArrayList<>(7);

        centers.add(new center("http://i.imgur.com/wclBYL4.png" , "New York"));
        centers.add(new center("http://i.imgur.com/wclBYL4.png" , "San Francisco"));
        centers.add(new center("http://36u65w3dg54i1kju4b3cdnjs.wpengine.netdna-cdn.com/files/2010/11/nethflag1.jpg" , "Amsterdam"));
        centers.add(new center("http://www.nhb.gov.sg/~/media/nhb/images/resources/national%20symbols/resources_singaporeflaghighres.jpg" , "Singapore"));
        centers.add(new center("https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1280px-Flag_of_the_United_Kingdom.svg.png" , "London"));
        centers.add(new center("https://upload.wikimedia.org/wikipedia/en/thumb/b/ba/Flag_of_Germany.svg/1280px-Flag_of_Germany.svg.png" , "Franfurt"));
        centers.add(new center("https://upload.wikimedia.org/wikipedia/en/thumb/c/cf/Flag_of_Canada.svg/1280px-Flag_of_Canada.svg.png" , "Toronto"));
        centers.add(new center("http://www.flagsinformation.com/indian-flag.png" , "Bangalore"));

        return centers;

    }
}
