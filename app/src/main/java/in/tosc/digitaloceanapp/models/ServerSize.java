package in.tosc.digitaloceanapp.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 27/11/16.
 */

public class ServerSize {
    public static class SizeModel{
        String perMonth;
        String perHour;
        String ram;
        String cpu;
        String ssd;

        public SizeModel(String perMonth, String perHour, String ram, String cpu, String ssd) {
            this.perMonth = perMonth;
            this.perHour = perHour;
            this.ram = ram;
            this.cpu = cpu;
            this.ssd = ssd;
        }

        public String getPerMonth() {
            return perMonth;
        }

        public void setPerMonth(String perMonth) {
            this.perMonth = perMonth;
        }

        public String getPerHour() {
            return perHour;
        }

        public void setPerHour(String perHour) {
            this.perHour = perHour;
        }

        public String getRam() {
            return ram;
        }

        public void setRam(String ram) {
            this.ram = ram;
        }

        public String getCpu() {
            return cpu;
        }

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public String getSsd() {
            return ssd;
        }

        public void setSsd(String ssd) {
            this.ssd = ssd;
        }
    }

    public static ArrayList<SizeModel> getSizes() {
         ArrayList<SizeModel> sizes = new ArrayList<>(8);

        sizes.add(new SizeModel("5", "0.007", "512", "1", "20"));
        sizes.add(new SizeModel("10", "0.015", "1", "1", "30"));
        sizes.add(new SizeModel("20", "0.030", "2", "2", "40"));
        sizes.add(new SizeModel("40", "0.060", "4", "2", "60"));
        sizes.add(new SizeModel("80", "0.119", "8", "4", "80"));
        sizes.add(new SizeModel("160", "0.238", "16", "8", "160"));
        sizes.add(new SizeModel("320", "0.476", "32", "12", "320"));
        sizes.add(new SizeModel("480", "0.714", "48", "16", "480"));
        sizes.add(new SizeModel("640", "0.952", "48", "20", "640"));

        Log.d("Size log :" , String.valueOf(sizes.get(0)));
        return sizes;
    }

}
