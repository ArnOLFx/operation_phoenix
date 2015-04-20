package com.example.arnolf.test_app_projectphoenix;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    private String url1 = "http://129.16.79.149:25555";
    private EditText truckSpeed, avgFuelconsumption, engineRpm, gameBrake, fuelWarning,
            engineDmg, transmissionDmg, cabinDmg, chassiDmg, wheelDmg, trailerDmg;
    //private EditText inputUrl;
    private JSONHandler jHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inputUrl = (EditText) findViewById(R.id.editText);

        truckSpeed = (EditText) findViewById(R.id.truckSpeed);
        avgFuelconsumption = (EditText) findViewById(R.id.avgFuel);
        engineRpm = (EditText) findViewById(R.id.engineRpm);
        gameBrake = (EditText) findViewById(R.id.gameBrake);
        fuelWarning = (EditText) findViewById(R.id.fuelWarning);
        engineDmg = (EditText) findViewById(R.id.engineDmg);
        transmissionDmg = (EditText) findViewById(R.id.transmissionDmg);
        cabinDmg = (EditText) findViewById(R.id.cabinDmg);
        chassiDmg = (EditText) findViewById(R.id.chassiDmg);
        wheelDmg = (EditText) findViewById(R.id.wheelDmg);
        trailerDmg = (EditText) findViewById(R.id.trailerDmg);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void open(View v) {

        String url = "/api/ets2/telemetry";

        String finalUrl = url1 + url;

        truckSpeed.setText(finalUrl);

        jHandler = new JSONHandler(finalUrl);
        jHandler.fetchJSON();

        while(jHandler.parsingFinished);

        truckSpeed.setText(jHandler.getTruckSpeed());
        avgFuelconsumption.setText(jHandler.getAvgFuelconsumption());
        engineRpm.setText(jHandler.getEngineRpm());
        gameBrake.setText(jHandler.getGameBrake());
        fuelWarning.setText(jHandler.getFuelWarning());
        engineDmg.setText(jHandler.getEngineDmg());
        transmissionDmg.setText(jHandler.getTransmissionDmg());
        cabinDmg.setText(jHandler.getCabinDmg());
        chassiDmg.setText(jHandler.getChassiDmg());
        wheelDmg.setText(jHandler.getWheelDmg());
        trailerDmg.setText(jHandler.getTrailerDmg());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
