package com.company;

public class Coffee {
    private String company;
    private CoffeeType type;
    private Integer acidity;
    private String flavour;
    private RoastedBeans roasted;
    private String units;



    public Coffee() {

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public Integer getAcidity() {
        return acidity;
    }

    public void setAcidity(Integer acidity) {
        this.acidity = acidity;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public RoastedBeans getRoasted() {
        return roasted;
    }

    public void setRoasted(RoastedBeans roasted) {
        this.roasted = roasted;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "company='" + company + '\'' +
                ", type=" + type +
                ", acidity=" + acidity +
                ", flavour='" + flavour + '\'' +
                ", roasted=" + roasted +
                ", units=" + units +
                '}';
    }


}