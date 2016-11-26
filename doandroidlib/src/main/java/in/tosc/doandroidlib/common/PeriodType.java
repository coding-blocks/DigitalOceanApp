package in.tosc.doandroidlib.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by championswimmer on 27/11/16.
 */

public enum PeriodType {

    @SerializedName("hour")
    HOUR("hour"),

    @SerializedName("day")
    DAY("day"),

    @SerializedName("week")
    WEEK("week"),

    @SerializedName("month")
    MONTH("month");

    private String value;

    PeriodType(String val) {
        this.value = val;
    }



    public static PeriodType fromValue(String value) {
        if (null == value || "".equals(value)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }

        for (PeriodType rt : PeriodType.values()) {
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
