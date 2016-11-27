package in.tosc.doandroidlib.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by championswimmer on 27/11/16.
 */

public enum StatisticsType {

    @SerializedName("bandwidth")
    BANDWIDTH("bandwidth"),

    @SerializedName("cpu")
    CPU("cpu"),

    @SerializedName("disk")
    DISK("disk");

    private String value;

    StatisticsType(String val) {
        this.value = val;
    }



    public static StatisticsType fromValue(String value) {
        if (null == value || "".equals(value)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }

        for (StatisticsType rt : StatisticsType.values()) {
            if (value.equalsIgnoreCase(rt.value)) {
                return rt;
            }
        }

        throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
    }

    @Override
    public String toString() {
        return this.value;
    }
}
