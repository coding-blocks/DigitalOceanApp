package in.tosc.digitaloceanapp.models;

import java.util.ArrayList;

import in.tosc.digitaloceanapp.R;

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

        public center(int id, String city) {
            this.id = id;
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

    }

    public static ArrayList<center> getCenter(){
        ArrayList<center> centers = new ArrayList<>(7);

        centers.add(new center(R.drawable.murrica , "New York"));
        centers.add(new center(R.drawable.murrica , "San Francisco"));
        centers.add(new center(R.drawable.amsterdam , "Amsterdam"));
        centers.add(new center(R.drawable.singapore , "Singapore"));
        centers.add(new center(R.drawable.london , "London"));
        centers.add(new center(R.drawable.frankfurt , "Frankfurt"));
        centers.add(new center(R.drawable.canada , "Toronto"));
        centers.add(new center(R.drawable.india , "Bangalore"));

        return centers;

    }
}
