package ph.hostev.paul.punk_ipa.beans;

public class Beer {
    private Integer id;
    private String name;
    private String tagline;
    private String firstBrewed;
    private String description;
    private String imageUrl;
    private double abv;
    private double ibu;
    private double targetFg;
    private double targetOg;
    private double ebc;
    private double srm;
    private double ph;
    private double attenuationLevel;
    private String brewersTips;
    private String contributedBy;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public double getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(double targetFg) {
        this.targetFg = targetFg;
    }

    public double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(double targetOg) {
        this.targetOg = targetOg;
    }

    public double getEbc() {
        return ebc;
    }

    public void setEbc(double ebc) {
        this.ebc = ebc;
    }

    public double getSrm() {
        return srm;
    }

    public void setSrm(double srm) {
        this.srm = srm;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public Double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(Double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }
}
