package com.macroyau.thingspeakandroid.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;

import java.util.Calendar;
import java.util.Date;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DemoActivity extends AppCompatActivity {

    private ThingSpeakChannel tsChannel;
    private ThingSpeakLineChart tsChartTemperature;
    private ThingSpeakLineChart tsChartHumidity;
    private LineChartView chartViewTemperature;
    private LineChartView chartViewHumidity;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                tsChartTemperature.loadChartData();
                tsChartHumidity.loadChartData();

                Toast.makeText(DemoActivity.this, "refresh", Toast.LENGTH_LONG).show();

            }
        });

        //łączenie z kanałem/ ustawianie głównych danych + toast
        tsChannel = new ThingSpeakChannel(572256);
        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                getSupportActionBar().setTitle("Temperatura/Wilgotność Piotr Kostański");

                Date lastUpdate = channelFeed.getChannel().getUpdatedAt();
                Toast.makeText(DemoActivity.this, lastUpdate.toString(), Toast.LENGTH_LONG).show();
            }
        });
        tsChannel.loadChannelFeed();

        // Create a Calendar object dated 5 minutes ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);


        //TEMPERATURA
        // Configure LineChartView
        chartViewTemperature = findViewById(R.id.chartTemperature);
        chartViewTemperature.setZoomEnabled(false);
        chartViewTemperature.setValueSelectionEnabled(true);

        tsChartTemperature = new ThingSpeakLineChart(572256, 1);

        tsChartTemperature.setNumberOfEntries(200);

        tsChartTemperature.setValueAxisLabelInterval(10);

        tsChartTemperature.setDateAxisLabelInterval(3);
        // Show the line as a cubic spline
        tsChartTemperature.useSpline(false);
        // Set the line color
        tsChartTemperature.setLineColor(Color.parseColor("#ff2222"));
        // Set the axis color
        tsChartTemperature.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChartTemperature.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        tsChartTemperature.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartViewTemperature.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartViewTemperature.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartViewTemperature.setCurrentViewport(initialViewport);
            }
        });
        // Load chart data asynchronously
        tsChartTemperature.loadChartData();



        //WILGOTNOSC
        // Configure LineChartView
        chartViewHumidity = findViewById(R.id.chartHumidity);
        chartViewHumidity.setZoomEnabled(false);
        chartViewHumidity.setValueSelectionEnabled(true);

        tsChartHumidity = new ThingSpeakLineChart(572256, 2);

        tsChartHumidity.setNumberOfEntries(200);

        tsChartHumidity.setValueAxisLabelInterval(10);

        tsChartHumidity.setDateAxisLabelInterval(2);
        // Show the line as a cubic spline
        tsChartHumidity.useSpline(false);
        // Set the line color
        tsChartHumidity.setLineColor(Color.parseColor("#2222ff"));
        // Set the axis color
        tsChartHumidity.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChartHumidity.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        tsChartHumidity.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartViewHumidity.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartViewHumidity.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartViewHumidity.setCurrentViewport(initialViewport);
            }
        });
        // Load chart data asynchronously
        tsChartHumidity.loadChartData();
    }

}
