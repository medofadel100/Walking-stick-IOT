package com.example.bengalaiotmob;

import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import android.util.Log;

/*
    This class abstracts the use of the gpio pins. This class can be utilized on any linux operating
    system that has gpio pins defined in the /sys/class/gpio directory. It is required that the gpio
    pins themselves are available for access by the user of this application, and may require a
    change of permissions.
 */
public class GpioProcessor {
    public static final String TAG = "GpioProcessor";

    public class Gpio {
        public static final int HIGH=1;
        public static final int LOW=0;
        private int pin;
        private String PATH = "/sys/class/gpio";

        /* gets the pin defined by the integer. This number does not always correspond with the pin
        number: For example, on the IFC6410, GPIO pin 21 corresponds to the operating system pin
        number 6.
         */
        public Gpio(int pin) {
            Log.v(TAG, "Initializing pin " + pin);
            this.pin = pin;
        }

        private void setDirection(String direction) {
            Log.v(TAG,"Setting Direction");
            BufferedWriter out = null;
            try {
                FileWriter fstream = new FileWriter(PATH + "/gpio" + pin + "/direction", false); //t
                out = new BufferedWriter(fstream);
                out.write(direction);
                out.close();
            } catch (IOException e) {
                Log.e(TAG,"Error: " + e.getMessage());
            }
        }

        private void setValue(int value) {
            Log.v(TAG,"Setting Value");
            BufferedWriter out = null;
            try {
                FileWriter fstream = new FileWriter(PATH + "/gpio" + pin + "/value", false); //t
                out = new BufferedWriter(fstream);
                out.write(Integer.toString(value));
                out.close();
            } catch (IOException e) {
                Log.e(TAG,"Error: " + e.getMessage());
            }
        }

        /* gets the direction of the pin */
        public String getDirection() {
            Log.v(TAG,"Getting Direction");
            BufferedReader br;
            String line = "";
            try {
                br = new BufferedReader(new FileReader(PATH + "/gpio" + pin + "/direction"));
                line = br.readLine();
                br.close();


            } catch (Exception e) {
                Log.e(TAG,"Error: " + e.getMessage());

            }

            return line;
        }

        /* gets the value of the pin */
        public int getValue() {
            Log.v(TAG,"Getting Value");
            BufferedReader br;
            String line = "";
            try {
                br = new BufferedReader(new FileReader(PATH + "/gpio" + pin + "/value"));
                line = br.readLine();
                br.close();


            } catch (Exception e) {
                Log.e(TAG,"Error: " + e.getMessage());

            }

            return Integer.parseInt(line);
        }

        /* sets pin high */
        public void high() {
            setValue(Gpio.HIGH);
        }

        /* sets pin low */
        public void low() {
            setValue(Gpio.LOW);
        }

        /* sets pin to output */
        public void out() {
            setDirection("out");
        }

        /* sets pin to input */
        public void in() {
            setDirection("in");
        }
    }

    public GpioProcessor() {}

    public Gpio getPin(int pin) {
        return new Gpio(pin);
    }

    /* These methods are specific to the IFC6410. This is to easily get access to the pins without
       knowing their operating system value.
     */
    public Gpio getPin23() {
        return new Gpio(938);
    }

    public Gpio getPin24() {
        return new Gpio(914);
    }

    public Gpio getPin25() {
        return new Gpio(915);
    }

    public Gpio getPin26() {
        return new Gpio(971);
    }

    public Gpio getPin27() {
        return new Gpio(1017);
    }

    public Gpio getPin28() {
        return new Gpio(901);
    }

    public Gpio getPin29() {
        return new Gpio(926);
    }

    public Gpio getPin30() {
        return new Gpio(927);
    }

    public Gpio getPin31(){
        return new Gpio(937);
    }

    public Gpio getPin32(){
        return new Gpio(936);
    }

    public Gpio getPin33(){
        return new Gpio(930);
    }

    public Gpio getPin34(){
        return new Gpio(935);
    }

    public Gpio[] getAllPins() {
        Gpio[] allPins = new Gpio[12];

        allPins[0]  = getPin23();
        allPins[1]  = getPin24();
        allPins[2]  = getPin25();
        allPins[3]  = getPin26();
        allPins[4]  = getPin27();
        allPins[5]  = getPin28();
        allPins[6]  = getPin29();
        allPins[7]  = getPin30();
        allPins[8]  = getPin31();
        allPins[9]  = getPin32();
        allPins[10] = getPin33();
        allPins[11] = getPin34();

        return allPins;
    }
}