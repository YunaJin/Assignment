import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yzwang on 2017/11/20.
 */
public class MyJson {


    public static void main(String[] args) throws IOException {
        File file = new File("/Users/yzwang/IdeaProjects/wyzimplementfunction/src/main/java/Question3_camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        writeToFile(s, file.getParent());

//        String[] arr = "2903315183~gmps-camino~new~2016~Chevrolet~4500 Gas~2WD Reg Cab 150&quot;~~56001.0~http://inventory-dmg.assets-cdk.com/chrome_jpgs/2016/24174x90.jpg".split("~");
//        Vehicle ve = new Vehicle(arr);
//        System.out.println(ve);
    }

    private static ArrayList<Vehicle> readAndGetVehicles(File file){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            Scanner sc = new Scanner(reader);
            //the header
            sc.nextLine();
            while (sc.hasNext()){
                String[] lines = sc.nextLine().split("~");
                vehicles.add(new Vehicle(lines));
            }
        }catch (Exception e){

        }
        return vehicles;
    }

    public static String getJsonString(ArrayList<Vehicle> vehicles){

        StringBuilder result = new StringBuilder("{ \"gmps-camino\":[");
        for (int i = 0; i < vehicles.size(); i++) {
            if(i < vehicles.size() -1 )
                result = result.append(new StringBuilder("{" + vehicles.get(i).toString() + "},"));
            else
                result = result.append(new StringBuilder("{" + vehicles.get(i).toString() + "}"));

        }
        result = result.append("]}");

        return result.toString();
    }

    public static void writeToFile(String inputToWrite, String filePath){

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(filePath+"/result2.txt"));
            out.write(inputToWrite.getBytes());
            out.close();
        }catch (Exception e){
            System.out.println("error in write file...");
            System.out.println(e);
        }finally {

        }
    }

    static class Vehicle{

        String id;
        String webId;
        Category category;
        Year year;
        String make;
        String model;
        String trim;
        String type;
        double price;
        URL photo;

        Vehicle(String[] arr){
            this.id = arr[0];
            this.webId = arr[1];
            this.category = Category.getCategory(arr[2].toLowerCase());
            this.year = Year.parse(arr[3]);
            this.make = arr[4];
            this.model = arr[5];
            this.trim = arr[6];
            this.type = arr[7];
            this.price = Double.parseDouble(arr[8]);
            try {
                this.photo = new URL(arr[9]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return
                    "\"id\":\"" + id + '\"' +
//                    ", \"webId\":\"" + webId + '\"' +
                    ", \"category\":\"" + category + '\"' +
                    ", \"year\":\"" + year + '\"' +
                    ", \"make\":\"" + make + '\"' +
                    ", \"model\":\"" + model + '\"' +
                    ", \"trim\":\"" + trim + '\"' +
                    ", \"type\":\"" + type + '\"' +
                    ", \"price\":" + price +
                    ", \"photo\":\"" + photo + '\"';
        }
    }

    enum Category{
        NEW , USED, CERTIFIED;

        public static Category getCategory(String cat){
            switch (cat){
                case "used": return USED;
                case "new": return NEW;
                case "certified": return CERTIFIED;
                default: throw new IllegalArgumentException();
            }
        }

        @Override
        public String toString() {
            switch (this){
                case NEW: return "NEW";
                case USED: return "USED";
                case CERTIFIED: return "CERTIFIED";
                default: throw new IllegalArgumentException();
            }
        }
    }

}
