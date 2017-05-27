package com.xiarh.purenews.util;

import android.widget.ImageView;

import com.xiarh.purenews.R;

/**
 * Created by xiarh on 2017/5/27.
 */

public class WeatherUtil {

    /**
     * 设置天气图标
     *
     * @param code
     * @param img
     */
    public static void setWeatherIcon(String code, ImageView img) {
        switch (code) {
            case "100":
                img.setBackgroundResource(R.drawable.ic_sunny);
                break;
            case "101":
            case "102":
            case "103":
                img.setBackgroundResource(R.drawable.ic_cloudy);
                break;
            case "104":
                img.setBackgroundResource(R.drawable.ic_overcast);
                break;
            case "200":
            case "201":
            case "202":
            case "203":
            case "204":
                img.setBackgroundResource(R.drawable.ic_wind);
                break;
            case "205":
            case "206":
            case "207":
            case "208":
                img.setBackgroundResource(R.drawable.ic_heavy_wind);
                break;
            case "209":
            case "210":
            case "211":
                img.setBackgroundResource(R.drawable.ic_hurricane);
                break;
            case "212":
            case "213":
                img.setBackgroundResource(R.drawable.ic_tornado);
                break;
            case "300":
            case "301":
            case "310":
                img.setBackgroundResource(R.drawable.ic_rainstorm);
                break;
            case "302":
            case "303":
                img.setBackgroundResource(R.drawable.ic_thunder_shower);
                break;
            case "304":
            case "313":
                img.setBackgroundResource(R.drawable.ic_rain_and_hail);
                break;
            case "305":
            case "309":
                img.setBackgroundResource(R.drawable.ic_light_rain);
                break;
            case "306":
                img.setBackgroundResource(R.drawable.ic_oderate_rain);
                break;
            case "307":
            case "308":
                img.setBackgroundResource(R.drawable.ic_heavy_rain);
                break;
            case "311":
            case "312":
                img.setBackgroundResource(R.drawable.ic_heavy_rainstorm);
                break;
            case "400":
                img.setBackgroundResource(R.drawable.ic_light_snow);
                break;
            case "401":
                img.setBackgroundResource(R.drawable.ic_moderate_snow);
                break;
            case "402":
                img.setBackgroundResource(R.drawable.ic_heavy_snow);
                break;
            case "403":
            case "407":
                img.setBackgroundResource(R.drawable.ic_blizzard);
                break;
            case "404":
            case "405":
            case "406":
                img.setBackgroundResource(R.drawable.ic_sleet);
                break;
            case "500":
            case "501":
                img.setBackgroundResource(R.drawable.ic_fog);
                break;
            case "502":
                img.setBackgroundResource(R.drawable.ic_haze);
                break;
            case "503":
            case "507":
            case "508":
                img.setBackgroundResource(R.drawable.ic_sanddust);
                break;
            case "504":
                img.setBackgroundResource(R.drawable.ic_dust);
                break;
            case "900":
            case "901":
            case "999":
                img.setBackgroundResource(R.drawable.ic_unknown);
                break;
        }
    }
}
