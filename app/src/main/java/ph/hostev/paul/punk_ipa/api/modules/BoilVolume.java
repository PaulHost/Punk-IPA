
package ph.hostev.paul.punk_ipa.api.modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoilVolume {

    @SerializedName("value")
    @Expose
    private Long value;
    @SerializedName("unit")
    @Expose
    private String unit;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
