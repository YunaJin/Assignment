import java.math.RoundingMode;
import java.math.BigDecimal;
class Root {
    public static void main(String[] args) {

        Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);

        Controller controller = new Controller(device,heat,pressure);

        controller.start();
        heat.start();
        pressure.start();
    }
}
class Controller extends Thread {
    Device device;
    Sensor heat;
    Sensor pressure;
    public Controller(Device d, Sensor h, Sensor p) {
        device = d;
        heat = h;
        pressure = p;
    }
    public void run() { device.startup(); synchronized (device) {
        while (heat.getValue() <= 70 && pressure.getValue() <= 100) {
            try { device.wait();

                double heatValue=heat.getValue();
                double pressureValue=pressure.getValue();
                BigDecimal bdheatValue = new BigDecimal(heatValue);
                bdheatValue = bdheatValue.setScale(2, RoundingMode.HALF_UP);

                BigDecimal bdpressureValue = new BigDecimal(pressureValue);
                bdpressureValue = bdpressureValue.setScale(2, RoundingMode.HALF_UP);


                System.out.println("heat-->"+bdheatValue+", pressure-->"+bdpressureValue);
            } catch (InterruptedException e) {

            }
        } }
        device.shutdown(); }
}
class Sensor extends Thread {
    private final Device device;
    private double value;
    public Sensor(Device device) {
        this.device = device;
    }
    public double getValue() {
        return value;
    }
    public void updateValue() {
        this.value += 0.005;
    }
    public void run() { while (true) {
        synchronized (device) {
            double oldValue = value;
            updateValue();
            if (value != oldValue) {
                device.notify(); }
        } }
    } }
class Device {
    public void startup() {
        System.out.println("Device started");
    } public void shutdown() {
        System.out.println("Device shutting down due to maintenance ");
    }
}