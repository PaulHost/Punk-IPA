
package ph.hostev.paul.punk_ipa.api.modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ingredients {

    @SerializedName("malt")
    @Expose
    private List<Malt> malt = new ArrayList<Malt>();
    @SerializedName("hops")
    @Expose
    private List<Hop> hops = new ArrayList<Hop>();
    @SerializedName("yeast")
    @Expose
    private String yeast;

    public List<Malt> getMalt() {
        return malt;
    }

    public void setMalt(List<Malt> malt) {
        this.malt = malt;
    }

    public List<Hop> getHops() {
        return hops;
    }

    public void setHops(List<Hop> hops) {
        this.hops = hops;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }

}
