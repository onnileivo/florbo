package xyz.yourboykyle.secretroutes.utils;

import com.google.gson.*;
import io.github.quantizr.dungeonrooms.dungeons.catacombs.RoomDetection;
import io.github.quantizr.dungeonrooms.utils.MapUtils;
import net.minecraft.util.BlockPos;
import xyz.yourboykyle.secretroutes.Main;
import xyz.yourboykyle.secretroutes.utils.Room.SECRET_TYPES;
import xyz.yourboykyle.secretroutes.utils.Room.WAYPOINT_TYPES;

import java.io.*;
import java.util.Map;

public class RouteRecording {
    public boolean recording = false;
    public JsonObject allSecretRoutes = new JsonObject();
    public JsonArray currentSecretRoute = new JsonArray();
    public JsonObject currentSecretWaypoints = new JsonObject();

    // Each waypoint on the locations will be added based on how far the player is from the previous waypoint, this will be used to keep track of said previous waypoint
    public BlockPos previousLocation;

    public RouteRecording() {
        // Create placeholders for the waypoints, as the coordinates are 2D arrays
        currentSecretWaypoints.add("locations", new JsonArray());
        currentSecretWaypoints.add("etherwarps", new JsonArray());
        currentSecretWaypoints.add("mines", new JsonArray());
        currentSecretWaypoints.add("interacts", new JsonArray());
        currentSecretWaypoints.add("tnts", new JsonArray());

        // Import all the current secret routes into the allSecretRoutes JsonObject
        importRoutes("routes.json");

        // Put stuff for testing in here
        /*System.out.println("- Start RouteRecording Testing Data -");

        System.out.println("allSecretRoutes before adding: " + allSecretRoutes);

        addWaypoint(WAYPOINT_TYPES.LOCATIONS, new BlockPos(1, 2, 3));
        addWaypoint(WAYPOINT_TYPES.ETHERWARPS, new BlockPos(1, 2, 3));
        addWaypoint(WAYPOINT_TYPES.MINES, new BlockPos(1, 2, 3));
        addWaypoint(WAYPOINT_TYPES.INTERACTS, new BlockPos(1, 2, 3));
        addWaypoint(WAYPOINT_TYPES.TNTS, new BlockPos(1, 2, 3));
        addWaypoint(SECRET_TYPES.INTERACT, new BlockPos(1, 2, 3));
        System.out.println("Waypoints #1: " + currentSecretWaypoints);

        newSecret();

        addWaypoint(WAYPOINT_TYPES.LOCATIONS, new BlockPos(2, 3, 4));
        addWaypoint(WAYPOINT_TYPES.ETHERWARPS, new BlockPos(2, 3, 4));
        addWaypoint(WAYPOINT_TYPES.MINES, new BlockPos(2, 3, 4));
        addWaypoint(WAYPOINT_TYPES.INTERACTS, new BlockPos(2, 3, 4));
        addWaypoint(WAYPOINT_TYPES.TNTS, new BlockPos(2, 3, 4));
        addWaypoint(SECRET_TYPES.ITEM, new BlockPos(2, 3, 4));
        System.out.println("Waypoints #2: " + currentSecretWaypoints);

        newSecret();

        addWaypoint(WAYPOINT_TYPES.LOCATIONS, new BlockPos(3, 4, 5));
        addWaypoint(WAYPOINT_TYPES.ETHERWARPS, new BlockPos(3, 4, 5));
        addWaypoint(WAYPOINT_TYPES.MINES, new BlockPos(3, 4, 5));
        addWaypoint(WAYPOINT_TYPES.INTERACTS, new BlockPos(3, 4, 5));
        addWaypoint(WAYPOINT_TYPES.TNTS, new BlockPos(3, 4, 5));
        addWaypoint(SECRET_TYPES.BAT, new BlockPos(3, 4, 5));
        System.out.println("Waypoints #3: " + currentSecretWaypoints);

        newSecret();

        System.out.println(currentSecretRoute);

        newRoute();

        System.out.println("allSecretRoutes after adding: " + allSecretRoutes);

        System.out.println("Exporting all routes...");
        exportAllRoutes();
        System.out.println("Exported all routes!");

        allSecretRoutes = new JsonObject();
        String filePath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "routes.json";
        importRoutes(filePath);
        System.out.println("Imported routes: " + allSecretRoutes);

        System.out.println("- End RouteRecording Testing Data -");*/
    }

    public void startRecording() {
        // Start recording the secret route
        recording = true;
    }

    public void stopRecording() {
        // Stop recording the secret route
        recording = false;
        newRoute();
    }

    public void importRoutes(String fileName) {
        String filePath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + fileName;

        // Import all the current secret routes into the allSecretRoutes JsonObject from a file
        allSecretRoutes = new JsonObject();

        try {
            Gson gson = new GsonBuilder().create();
            FileReader reader = new FileReader(filePath);

            allSecretRoutes = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWaypoint(WAYPOINT_TYPES type, BlockPos pos) {
        // Add a non-secret waypoint to the current secret waypoints
        Main.checkRoomData();
        BlockPos relPos = MapUtils.actualToRelative(pos, RoomDetection.roomDirection, RoomDetection.roomCorner);

        JsonArray posArray = new JsonArray();

        posArray.add(new JsonPrimitive(relPos.getX()));
        posArray.add(new JsonPrimitive(relPos.getY()));
        posArray.add(new JsonPrimitive(relPos.getZ()));


        if(type == WAYPOINT_TYPES.LOCATIONS) {
            boolean shouldAddWaypoint = true;
            int count = 0;

            JsonArray array = currentSecretWaypoints.get("locations").getAsJsonArray();
            for(JsonElement element : array) {
                JsonArray location = element.getAsJsonArray();
                if(count < array.size() && location.equals(posArray)) {
                    shouldAddWaypoint = false;
                    break;
                }

                count++;
            }

            if(shouldAddWaypoint) {
                currentSecretWaypoints.get("locations").getAsJsonArray().add(posArray);
            }
        } else if(type == WAYPOINT_TYPES.ETHERWARPS) {
            boolean shouldAddWaypoint = true;
            int count = 0;

            JsonArray array = currentSecretWaypoints.get("etherwarps").getAsJsonArray();
            for(JsonElement element : array) {
                JsonArray location = element.getAsJsonArray();
                if(count < array.size() && location.equals(posArray)) {
                    shouldAddWaypoint = false;
                    break;
                }

                count++;
            }

            if(shouldAddWaypoint) {
                currentSecretWaypoints.get("etherwarps").getAsJsonArray().add(posArray);
            }
        } else if(type == WAYPOINT_TYPES.MINES) {
            boolean shouldAddWaypoint = true;
            int count = 0;

            JsonArray array = currentSecretWaypoints.get("mines").getAsJsonArray();
            for(JsonElement element : array) {
                JsonArray location = element.getAsJsonArray();
                if(count < array.size() && location.equals(posArray)) {
                    shouldAddWaypoint = false;
                    break;
                }

                count++;
            }

            if(shouldAddWaypoint) {
                currentSecretWaypoints.get("mines").getAsJsonArray().add(posArray);
            }
        } else if(type == WAYPOINT_TYPES.INTERACTS) {
            boolean shouldAddWaypoint = true;
            int count = 0;

            JsonArray array = currentSecretWaypoints.get("interacts").getAsJsonArray();
            for(JsonElement element : array) {
                JsonArray location = element.getAsJsonArray();
                if(count < array.size() && location.equals(posArray)) {
                    shouldAddWaypoint = false;
                    break;
                }

                count++;
            }

            if(shouldAddWaypoint) {
                currentSecretWaypoints.get("interacts").getAsJsonArray().add(posArray);
            }
        } else if(type == WAYPOINT_TYPES.TNTS) {
            boolean shouldAddWaypoint = true;
            int count = 0;

            JsonArray array = currentSecretWaypoints.get("tnts").getAsJsonArray();
            for(JsonElement element : array) {
                JsonArray location = element.getAsJsonArray();
                if(count < array.size() && location.equals(posArray)) {
                    shouldAddWaypoint = false;
                    break;
                }

                count++;
            }

            if(shouldAddWaypoint) {
                currentSecretWaypoints.get("tnts").getAsJsonArray().add(posArray);
            }
        }
    }

    public boolean addWaypoint(SECRET_TYPES type, BlockPos pos) {
        // Add a secret waypoint to the current secret waypoints
        Main.checkRoomData();
        BlockPos relPos = MapUtils.actualToRelative(pos, RoomDetection.roomDirection, RoomDetection.roomCorner);

        JsonArray posArray = new JsonArray();

        posArray.add(new JsonPrimitive(relPos.getX()));
        posArray.add(new JsonPrimitive(relPos.getY()));
        posArray.add(new JsonPrimitive(relPos.getZ()));

        JsonObject secret = new JsonObject();

        if(type == SECRET_TYPES.INTERACT) {
            secret.add("type", new JsonPrimitive("interact"));
        } else if(type == SECRET_TYPES.ITEM) {
            secret.add("type", new JsonPrimitive("item"));
        } else if(type == SECRET_TYPES.BAT) {
            secret.add("type", new JsonPrimitive("bat"));
        }

        // Make sure the secret hasn't already been recorded
        boolean shouldAddWaypoint = true;

        int count = 0;
        for(JsonElement element : currentSecretRoute) {
            JsonObject waypoints = element.getAsJsonObject();
            if(count < currentSecretRoute.size() && waypoints.get("secret") != null && waypoints.get("secret").getAsJsonObject().get("location") != null) {
                JsonObject secretWaypoints = waypoints.get("secret").getAsJsonObject();
                JsonArray location = secretWaypoints.get("location").getAsJsonArray();

                if(location.equals(posArray)) {
                    shouldAddWaypoint = false;
                }
            }

            count++;
        }

        secret.add("location", posArray);
        if(shouldAddWaypoint) {
            currentSecretWaypoints.add("secret", secret);
            return true;
        } else {
            return false;
        }
    }

    public void newSecret() {
        // Add current secret waypoints to the route and start a new secret waypoints list
        currentSecretRoute.add(currentSecretWaypoints);

        currentSecretWaypoints = new JsonObject();

        currentSecretWaypoints.add("locations", new JsonArray());
        currentSecretWaypoints.add("etherwarps", new JsonArray());
        currentSecretWaypoints.add("mines", new JsonArray());
        currentSecretWaypoints.add("interacts", new JsonArray());
        currentSecretWaypoints.add("tnts", new JsonArray());
    }

    public void newRoute() {
        // Start a new secret route recording
        allSecretRoutes.add(RoomDetection.roomName, currentSecretRoute);
        currentSecretRoute = new JsonArray();

        currentSecretWaypoints = new JsonObject();

        currentSecretWaypoints.add("locations", new JsonArray());
        currentSecretWaypoints.add("etherwarps", new JsonArray());
        currentSecretWaypoints.add("mines", new JsonArray());
        currentSecretWaypoints.add("interacts", new JsonArray());
        currentSecretWaypoints.add("tnts", new JsonArray());
    }

    public void exportAllRoutes() {
        // Export the secret route to a file
        String filePath = System.getProperty("user.home") + File.separator + "Downloads";
        String fileName = "routes.json";

        File file = new File(filePath, fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(prettyPrint(allSecretRoutes));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String prettyPrint(JsonObject jsonObject) {
        // Pretty print the secret routes
        Gson gson = new GsonBuilder().create();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            stringBuilder.append("\t\"").append(entry.getKey()).append("\": ").append(gson.toJson(entry.getValue())).append(",\n");
        }

        // Remove the last comma and add a new line
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }
}