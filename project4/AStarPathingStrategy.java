import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy {
    /**
     * Return a list containing a single point representing the next step toward a goal
     * If the start is within reach of the goal, the returned list is empty.
     *
     * @param start the point to begin the search from
     * @param end the point to search for a point within reach of
     * @param canPassThrough a function that returns true if the given point is traversable
     * @param withinReach a function that returns true if both points are within reach of each other
     * @param potentialNeighbors a function that returns the neighbors of a given point, as a stream
     */
    public List<Point> computePath(
            Point start,
            Point end,
            Predicate<Point> canPassThrough,
            BiPredicate<Point, Point> withinReach,
            Function<Point, Stream<Point>> potentialNeighbors
    ) {

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getTotalCost));
        Set<Point> closedSet = new HashSet<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        openSet.add(new Node(start, 0, CalculateHeuristic(start, end)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (withinReach.test(current.point, end)) {
                return reconstruct(cameFrom, current.point);
            }
            closedSet.add(current.point);
            potentialNeighbors.apply(current.point)
                    .filter(canPassThrough)
                    .filter(p -> !closedSet.contains(p))
                    .forEach(neighbor -> {
                        int GScore = gScore.get(current.point) + 1;

                        if (!gScore.containsKey(neighbor)){
                            cameFrom.put(neighbor, current.point);
                            gScore.put(neighbor, GScore);
                            int totalCost = GScore + CalculateHeuristic(neighbor, end);
                            openSet.add(new Node(neighbor, GScore, totalCost));
                        }
                        else if (GScore < gScore.get(neighbor)){
                            openSet.remove(new Node(neighbor, gScore.get(neighbor), 0));
                            cameFrom.put(neighbor, current.point);
                            gScore.put(neighbor, GScore);
                            int totalCost = GScore + CalculateHeuristic(neighbor, end);
                            openSet.add(new Node(neighbor, GScore, totalCost));
                        }
                    });

        }
        return new ArrayList<>();
    }
    private List<Point> reconstruct(Map<Point, Point> cameFrom, Point current) {
        List<Point> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private int CalculateHeuristic(Point start, Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }
    private static class Node {
        private final Point point;
        private final int gScore;
        private final int totalCost;

        public Node(Point point, int gScore, int totalCost) {
            this.point = point;
            this.gScore = gScore;
            this.totalCost = totalCost;
        }

        public int getTotalCost() {
            return totalCost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return gScore == node.gScore && Objects.equals(point, node.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(point, gScore);
        }
    }
}
