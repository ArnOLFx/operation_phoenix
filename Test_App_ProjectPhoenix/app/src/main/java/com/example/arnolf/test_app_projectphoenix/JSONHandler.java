package com.example.arnolf.test_app_projectphoenix;

import android.annotation.SuppressLint;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ArnOLF on 2015-04-13.
 */
public class JSONHandler {

    /**
     * Data to be read (11 variables)
     */
    private String truckSpeed = "truckSpeed";
    private String avgFuelconsumption = "fuelAverageConsumption";
    private String engineRpm = "engineRpm";
    private String gameBrake = "gameBrake";
    private String fuelWarning = "fuelWarning";
    private String engineDmg = "wearEngine";
    private String transmissionDmg = "wearTransmission";
    private String cabinDmg = "wearCabin";
    private String chassiDmg = "wearChassis";
    private String wheelDmg = "wearWheels";
    private String trailerDmg = "wearTrailer";

    private String urlString = null;

    public volatile boolean parsingFinished = true;

    /**
     *
     * @param url
     * Constructor
     */
    public JSONHandler(String url) {
        this.urlString = url;
    }

    /**
     *
     * @return
     * get methods for all variables
     */
    public String getTruckSpeed() {
        return truckSpeed;
    }
    public String getAvgFuelconsumption() {
        return avgFuelconsumption;
    }
    public String getEngineRpm() {
        return engineRpm;
    }
    public String getGameBrake() {
        return gameBrake;
    }
    public String getFuelWarning() {
        return fuelWarning;
    }
    public String getEngineDmg() {
        return engineDmg;
    }
    public String getTransmissionDmg() {
        return transmissionDmg;
    }
    public String getCabinDmg() {
        return cabinDmg;
    }
    public String getChassiDmg() {
        return chassiDmg;
    }
    public String getWheelDmg() {
        return wheelDmg;
    }
    public String getTrailerDmg() {
        return trailerDmg;
    }

    /**
     * For reading and parsing JSON data
     */
    @SuppressLint("NewApi")
    public void readAndParse(String in) {

        try {
            JSONObject reader = new JSONObject(in);

            String ts = reader.getString("truckSpeed");
            truckSpeed = ts;

            String afc = reader.getString("fuelAverageConsumption");
            avgFuelconsumption = afc;

            String erpm = reader.getString("engineRpm");
            engineRpm = erpm;

            String gb = reader.getString("gameBrake");
            gameBrake = gb;

            String fw = reader.getString("fuelWarning");
            fuelWarning = fw;

            String edmg = reader.getString("wearEngine");
            engineDmg = edmg;

            String tdmg = reader.getString("wearTransmission");
            transmissionDmg = tdmg;

            String cdmg = reader.getString("wearCabin");
            cabinDmg = cdmg;

            String chdmg = reader.getString("wearChassis");
            chassiDmg = chdmg;

            String wdmg = reader.getString("wearWheels");
            wheelDmg = wdmg;

            String trdmg = reader.getString("wearTrailer");
            trailerDmg = tdmg;

            parsingFinished = false;

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchJSON() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Create connection
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);     //in millis
                    conn.setConnectTimeout(15000);  //in millis
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    //Start query
                    conn.connect();

                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParse(data);
                    stream.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    static String convertStreamToString(InputStream is) {
        Scanner sc = new Scanner(is).useDelimiter("\\A");

        return sc.hasNext() ? sc.next() : "";
    }

}
