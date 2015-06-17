package ch.bbcag.meteobbc.meteobbc;

/**
 * Created by zbuchs on 11.06.2015.
 */
public class Temperature {

    private double temperature;
    private double sunGoesUp;
    private double sunGoesDown;
    private double hoechstTemperatur;
    private double mindestTemperatur;

    private String wetterArt;
    private String stadtName;

    public String getStadtName() {
        return stadtName;
    }

    public void setStadtName(String stadtName) {
        this.stadtName = stadtName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getSunGoesUp() {
        return sunGoesUp;
    }

    public void setSunGoesUp(double sunGoesUp) {
        this.sunGoesUp = sunGoesUp;
    }

    public double getSunGoesDown() {
        return sunGoesDown;
    }

    public void setSunGoesDown(double sunGoesDown) {
        this.sunGoesDown = sunGoesDown;
    }

    public String getWetterArt() {
        return wetterArt;
    }

    public void setWetterArt(String wetterArt) {
        this.wetterArt = wetterArt;
    }

    public double getHoechstTemperatur() {
        return hoechstTemperatur;
    }

    public void setHoechstTemperatur(double hoechstTemperatur) {
        this.hoechstTemperatur = hoechstTemperatur;
    }

    public double getMindestTemperatur() {
        return mindestTemperatur;
    }

    public void setMindestTemperatur(double mindestTemperatur) {
        this.mindestTemperatur = mindestTemperatur;
    }

    @Override
    public String toString() {

        StringBuilder resultBuilder = new StringBuilder();

        // Temperature
        resultBuilder.append("Temperature: ");
        resultBuilder.append(temperature);
        resultBuilder.append("\n");

        //Sun goes up
        //resultBuilder.append("Sonnenaufgang: ");
        //resultBuilder.append(sunGoesUp);
        //resultBuilder.append("\n");

        //Sun goes down
//        resultBuilder.append("Sonnenuntergant: ");
        //      resultBuilder.append(sunGoesDown);
        // resultBuilder.append("\n");

        //HoechstTemperatur
        // resultBuilder.append("Hoechstemperatur: ");
        //resultBuilder.append(hoechstTemperatur);
        //resultBuilder.append("\n");

        //MindestTemperatur
        //resultBuilder.append("Mindesttemperatur: ");
        //resultBuilder.append(mindestTemperatur);
        //resultBuilder.append("\n");

        //Stadtname
        //resultBuilder.append("Name: ");
        //resultBuilder.append(stadtName);
        // resultBuilder.append("\n");

        //Wetterart
        //resultBuilder.append("Wetterart: ");
        // resultBuilder.append(wetterArt);
        //resultBuilder.append("\n");

        return resultBuilder.toString();
    }
}