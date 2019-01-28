package com.macroyau.thingspeakandroid.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;

import java.util.Calendar;
import java.util.Date;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DemoActivity extends AppCompatActivity {

    private ThingSpeakChannel tsChannel;
    private final int CHANNEL_ID = 682515;
    private final int ILOSC_WEJSC = 100;

    private ThingSpeakLineChart tsChartTemperature;
    private ThingSpeakLineChart tsChartHumidity;
    private ThingSpeakLineChart tsChartMove;
    private ThingSpeakLineChart tsChartWater;

    private LineChartView chartViewTemperature;
    private LineChartView chartViewHumidity;
    private LineChartView chartViewMove;
    private LineChartView chartViewWater
            ;
    Calendar godziny = Calendar.getInstance();
    Calendar minuty = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        godziny.add(Calendar.HOUR, -24);
        minuty.add(Calendar.MINUTE, -60);

        tsChannel = new ThingSpeakChannel(CHANNEL_ID);
        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                getSupportActionBar().setTitle("Aplikacja monitorująca Kostański");

                Date lastUpdate = channelFeed.getChannel().getUpdatedAt();
                Toast.makeText(DemoActivity.this, lastUpdate.toString(), Toast.LENGTH_LONG).show();
            }
        });
        tsChannel.loadChannelFeed();


        //TEMPERATURA
        chartViewTemperature = findViewById(R.id.chartTemperature);
        chartViewTemperature.setZoomEnabled(true);
        chartViewTemperature.setValueSelectionEnabled(true);
        chartViewTemperature.setScrollEnabled(true);

        tsChartTemperature = new ThingSpeakLineChart(CHANNEL_ID, 1);

        tsChartTemperature.setNumberOfEntries(ILOSC_WEJSC);
        tsChartTemperature.setValueAxisLabelInterval(1);
        tsChartTemperature.setXAxisName("");
        tsChartTemperature.setDateAxisLabelInterval(10);
        tsChartTemperature.useSpline(false);
        tsChartTemperature.setLineColor(Color.parseColor("#ff2222"));
        tsChartTemperature.setAxisColor(Color.parseColor("#455a64"));
        tsChartTemperature.setChartStartDate(godziny.getTime());
        tsChartTemperature.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartViewTemperature.setLineChartData(lineChartData);
                chartViewTemperature.setMaximumViewport(maxViewport);
                chartViewTemperature.setCurrentViewport(initialViewport);
            }
        });
        tsChartTemperature.loadChartData();


        //WILGOTNOSC
        chartViewHumidity = findViewById(R.id.chartHumidity);
        chartViewHumidity.setZoomEnabled(true);
        chartViewHumidity.setValueSelectionEnabled(true);
        chartViewHumidity.setScrollEnabled(true);

        tsChartHumidity = new ThingSpeakLineChart(CHANNEL_ID, 2);

        tsChartHumidity.setNumberOfEntries(ILOSC_WEJSC);
        tsChartHumidity.setValueAxisLabelInterval(1);
        tsChartHumidity.setXAxisName("");
        tsChartHumidity.setDateAxisLabelInterval(10);
        tsChartHumidity.useSpline(false);
        tsChartHumidity.setLineColor(Color.parseColor("#2222ff"));
        tsChartHumidity.setAxisColor(Color.parseColor("#455a64"));
        tsChartHumidity.setChartStartDate(godziny.getTime());
        tsChartHumidity.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartViewHumidity.setLineChartData(lineChartData);
                chartViewHumidity.setMaximumViewport(maxViewport);
                chartViewHumidity.setCurrentViewport(initialViewport);
            }
        });
        tsChartHumidity.loadChartData();


        //RUCH
        chartViewMove = findViewById(R.id.chartMove);
        chartViewMove.setZoomEnabled(true);
        chartViewMove.setValueSelectionEnabled(true);
        chartViewMove.setScrollEnabled(true);

        tsChartMove = new ThingSpeakLineChart(CHANNEL_ID, 5);

        tsChartMove.setNumberOfEntries(ILOSC_WEJSC);
        tsChartMove.setValueAxisLabelInterval(1);
        tsChartMove.setXAxisName("");
        tsChartMove.setDateAxisLabelInterval(10);
        tsChartMove.useSpline(false);
        tsChartMove.setLineColor(Color.parseColor("#22ff22"));
        tsChartMove.setAxisColor(Color.parseColor("#455a64"));
        tsChartMove.setChartStartDate(godziny.getTime());
        tsChartMove.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartViewMove.setLineChartData(lineChartData);
                chartViewMove.setMaximumViewport(maxViewport);
                chartViewMove.setCurrentViewport(initialViewport);
            }
        });
        tsChartMove.loadChartData();


        //OPADY
        chartViewWater = findViewById(R.id.chartWater);
        chartViewWater.setZoomEnabled(true);
        chartViewWater.setValueSelectionEnabled(true);
        chartViewWater.setScrollEnabled(true);

        tsChartWater = new ThingSpeakLineChart(CHANNEL_ID, 3);

        tsChartWater.setNumberOfEntries(ILOSC_WEJSC);
        tsChartWater.setValueAxisLabelInterval(1);
        tsChartWater.setXAxisName("");
        tsChartWater.setDateAxisLabelInterval(10);
        tsChartWater.useSpline(false);
        tsChartWater.setLineColor(Color.parseColor("#22ffff"));
        tsChartWater.setAxisColor(Color.parseColor("#455a64"));
        tsChartWater.setChartStartDate(godziny.getTime());
        tsChartWater.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartViewWater.setLineChartData(lineChartData);
                chartViewWater.setMaximumViewport(maxViewport);
                chartViewWater.setCurrentViewport(initialViewport);
            }
        });
        tsChartWater.loadChartData();


       /* //WILGOTNOSC
        // Configure LineChartView
        chartViewHumidity = findViewById(R.id.chartHumidity);
        chartViewHumidity.setZoomEnabled(true);
        chartViewHumidity.setValueSelectionEnabled(true);
        chartViewHumidity.setScrollEnabled(true);
        chartViewHumidity.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                chartViewHumidity.getX();
                Toast.makeText(DemoActivity.this, "asd", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });

        tsChartHumidity = new ThingSpeakLineChart(682515, 2);

        tsChartHumidity.setNumberOfEntries(100);

        tsChartHumidity.setValueAxisLabelInterval(1);

        tsChartHumidity.setDateAxisLabelInterval(10);
        tsChartHumidity.setXAxisName("");
        tsChartHumidity.useSpline(false);
        tsChartHumidity.setLineColor(Color.parseColor("#2222ff"));
        tsChartHumidity.setAxisColor(Color.parseColor("#455a64"));
        tsChartHumidity.setChartStartDate(godziny.getTime());
        tsChartHumidity.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartViewHumidity.setLineChartData(lineChartData);
                chartViewHumidity.setMaximumViewport(maxViewport);
                chartViewHumidity.setCurrentViewport(initialViewport);
            }
        });
        tsChartHumidity.loadChartData();*/
    }

}
