package ch.bbcag.meteobbc.meteobbc;

import java.util.Date;

/**
 * Created by zbuchs on 11.06.2015.
 */
public class Temperature {

    private int beckenId;

    private double temperature;

    private Date measurementTime;


    public int getBeckenId() {
        return beckenId;
    }

    public void setBeckenId(int beckenId) {
        this.beckenId = beckenId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();

        // Becken
        resultBuilder.append("Becken: ");
        resultBuilder.append(beckenId);
        resultBuilder.append("\n");

        // Temperature
        resultBuilder.append("Temperature: ");
        resultBuilder.append(temperature);
        resultBuilder.append("\n");

        // Measurement time
        resultBuilder.append("Measurement time: ");
        resultBuilder.append(measurementTime);
        resultBuilder.append("\n");

        return resultBuilder.toString();
    }
}