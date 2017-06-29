package in.tosc.doandroidlib.models;

import java.util.ArrayList;

import in.tosc.doandroidlib.R;
import in.tosc.doandroidlib.objects.Region;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class Datacenter {
    public static class center{

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        int id;
        String city;
        Region region;

        public center(int id, String city, Region region) {
            this.id = id;
            this.city = city;
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Region getRegion() {
            return region;
        }

        public void setRegion(Region region) {
            this.region = region;
        }
    }

    public static ArrayList<center> getCenter(){
        ArrayList<center> centers = new ArrayList<>(7);

        centers.add(new center(R.drawable.murrica , "New York 1",new Region("nyc1")));
        centers.add(new center(R.drawable.murrica , "New York 2",new Region("nyc2")));
        centers.add(new center(R.drawable.murrica , "New York 3",new Region("nyc3")));
        centers.add(new center(R.drawable.murrica , "San Francisco 2",new Region("sfo1")));
        centers.add(new center(R.drawable.murrica , "San Francisco 2",new Region("sfo2")));
        centers.add(new center(R.drawable.amsterdam , "Amsterdam 2",new Region("ams2")));
        centers.add(new center(R.drawable.amsterdam , "Amsterdam 3",new Region("ams3")));
        centers.add(new center(R.drawable.singapore , "Singapore",new Region("spo1")));
        centers.add(new center(R.drawable.london , "London",new Region("lon1")));
        centers.add(new center(R.drawable.frankfurt , "Frankfurt",new Region("fra1")));
        centers.add(new center(R.drawable.canada , "Toronto",new Region("tor1")));
        centers.add(new center(R.drawable.india , "Bangalore",new Region("blr1")));

        return centers;

    }
}
