package com.example.navigationdraweractivity.ui.weather;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {

    private String mTemperature,micon,mcity,mWeatherType,mhumidity;

    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject)
    {
        try
        {
            weatherData weatherD=new weatherData();
            weatherD.mcity=jsonObject.getString( "name");
            weatherD.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mWeatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

            
            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue=(int)Math.rint(tempResult);
            weatherD.mTemperature=Integer.toString(roundedValue);

            weatherD.micon=updateWeatherIcon((int) tempResult);

            double humidityResult=jsonObject.getJSONObject("main").getDouble("humidity");
            int roundedValue1=(int)Math.rint(humidityResult);
            weatherD.mhumidity=Integer.toString(roundedValue1);
            return weatherD;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static String updateWeatherIcon(int condition)
    {
        if (condition>=0 && condition<=20)
        {
            return "thunderstrom1";
        }
        else if (condition>=20 && condition<=50)
        {
            return "sunny";
        }
        else if (condition>=50 && condition<=60)
        {
            return "shower";
        }
        else if (condition>=60 && condition<=70)
        {
            return "snow2";
        }
        else if (condition>=701 && condition<=771)
        {
            return "fog";
        }
        else if (condition>=772 && condition<=800)
        {
            return "overcast";
        }
        else if (condition>801 && condition<=804)
        {
            return "cloudy";
        }
        else if (condition>900 && condition<=902)
        {
            return "thunderstrom1";
        }
        else if (condition==903)
        {
            return "snow1";
        }
        else if (condition==904)
        {
            return "sunny";
        }
        else if (condition>905 && condition<=1000)
        {
            return "thunderstrom2";
        }
        else{
            return "sunny1";
        }


    }

    public String getmTemperature() {
        return mTemperature+"°C";
    }

    public String getMicon() {
        return micon;
    }

    public String getMcity() {
        return mcity;
    }

    public String getmWeatherType() {
        return mWeatherType;
    }

    public String getMhumidity(){
        return mhumidity;
    }


}
