package application.beta.geoassist.Trip;


import application.beta.geoassist.Location.Location;
import application.beta.geoassist.pojo.Road;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class TripMetaData {
    public Map<Integer, Location> nodeCoordinates = new HashMap<>();

    public Map<Integer, List<Road>> routes = new HashMap<>();

    public Map<Integer, Character> colors = new HashMap<>();
    public int DriverLoc = 1345;
    public int RiderLoc = 3;

    public void loadCoordinates() {
        try {
            Scanner myReader;
            File myObj = new File("/Users/lohithseeram/Downloads/USA-road-d.NY.co");
            Scanner my = new Scanner(myObj);
            while (my.hasNextLine()) {
                String data = my.nextLine();
                String[] d = data.split(" ");
                nodeCoordinates.put(Integer.valueOf(d[1]), new Location(Double.valueOf(d[1]) / Math.pow(10, 6), Double.valueOf(d[2]) / Math.pow(10, 6)));
            }
            my.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void createMap() {
        int i = 0;
        try {
            Scanner myReader;
            File distances = new File("/Users/lohithseeram/Downloads/USA-road-d.NY.gr");
            File time = new File("/Users/lohithseeram/Downloads/USA-road-t.NY.gr");

            Scanner d = new Scanner(distances);
            Scanner t = new Scanner(time);
            //System.out.print(i++);
            while (d.hasNextLine() && t.hasNextLine()) {
                String dist = d.nextLine();
                String tim = t.nextLine();
                String[] sd = dist.split(" ");
                String[] st = tim.split(" ");
                Road r = new Road(Integer.valueOf(sd[2]), Integer.valueOf(sd[3]), Integer.valueOf(st[3]));
                if (routes.get(Integer.valueOf(sd[1])) == null) {
                    ArrayList<Road> roads = new ArrayList<>();
                    routes.put(Integer.valueOf(sd[1]), roads);
                }
                routes.get(Integer.valueOf(sd[1])).add(r);
            }
            d.close();
            t.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assignColor(routes);
    }

    public void createColors() {

        try {
            Scanner myReader;
            File myObj = new File("/Users/lohithseeram/Downloads/USA-road-colors.NY.gr");
            Scanner my = new Scanner(myObj);
            while (my.hasNextLine()) {
                String data = my.nextLine();
                String[] d = data.split(" ");
                colors.put(Integer.valueOf(d[0]), d[1].charAt(0));
            }
            my.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Deprecated
    public void assignColor(Map<Integer, List<Road>> routes) {

        File myObj = new File("/Users/lohithseeram/Downloads/USA-road-colors.NY.gr");
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Random random = new Random();
        for (Map.Entry<Integer, List<Road>> entries : routes.entrySet()) {
            colors.put(entries.getKey(), (char) ('a' + random.nextInt(4)));
        }
        try {
            FileWriter myWriter = new FileWriter(myObj);
            for (Map.Entry<Integer, Character> entries : colors.entrySet()) {
                myWriter.write(entries.getKey() + " " + entries.getValue());
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
