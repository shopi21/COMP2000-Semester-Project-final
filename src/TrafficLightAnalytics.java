
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrafficLightAnalytics {

    private final TrafficLightController controller;

    public TrafficLightAnalytics(TrafficLightController controller) {
        this.controller = controller;
    }

    // Count traffic lights with a particular state
    public long countLights(TrafficLightController.LightState state) {
        return controller.getAllLightStates()
                .values()
                .stream()
                .filter(light -> light == state)
                .count();
    }

    // Find directions that currently have green lights
    public List<String> getGreenDirections() {
        return controller.getAllLightStates()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue()
                        == TrafficLightController.LightState.GREEN)
                .map(entry -> formatDirection(entry.getKey()))
                .collect(Collectors.toList());
    }

    // Generate a report of all traffic lights
    public String getStatusReport() {
        return controller.getAllLightStates()
                .entrySet()
                .stream()
                .map(entry -> formatDirection(entry.getKey())
                        + ": " + entry.getValue())
                .collect(Collectors.joining(" | "));
    }

    // Format direction names, for example NORTH becomes North
    private String formatDirection(Enum<?> direction) {
        String name = direction.name().toLowerCase();

        return Character.toUpperCase(name.charAt(0))
                + name.substring(1);
    }
}
