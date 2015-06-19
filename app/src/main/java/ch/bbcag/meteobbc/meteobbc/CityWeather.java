package ch.bbcag.meteobbc.meteobbc;


public class CityWeather {

    public CityWeather(String ort, String temp) {
        this.setOrt(ort);
        this.setTemp(temp);
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }



    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    private String ort;
    private String temp;
}
