import java.io.Serializable;

/**
 * This class named Storm.
 * each data field must have getters and setters.
 * Must implement the Serializable interface
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Storm implements Serializable {
    private String name;
    private double precipitation;
    private double windSpeed;
    String Date;

    /**
     * Constructor
     * @param name name
     * @param precipitation precipitation
     * @param windSpeed windSpeed
     * @param Date Date
     */
    public Storm(String name, double precipitation, double windSpeed, String Date ) {
        this.name = name;
        this.Date=  Date;
        this.precipitation=precipitation;
        this.windSpeed=windSpeed;
    }

    /**
     * getter date
     * @return date
     */
    public String getDate() {
        return Date;
    }

    /**
     * setter for date
     * @param date date
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * getter for wind speed
     * @return wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * setter for wind speed
     * @param windSpeed wind speed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for precipitation
     * @return precipitation
     */
    public double getPrecipitation() {
        return precipitation;
    }

    /**
     * setter for precipitation
     * @param precipitation precipitation
     */
    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

}
