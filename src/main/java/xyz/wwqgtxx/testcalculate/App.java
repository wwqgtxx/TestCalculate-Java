package xyz.wwqgtxx.testcalculate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
    private static Logger logger = LogManager.getLogger(App.class);

    public static boolean calculate_second_step(List<HashSet<Integer>> list) {
//        logger.info(list); // like: [[1], [1, 2], [1, 2, 4], [3, 4], [2, 3, 4], [1, 3, 4, 5]]
        for (List<HashSet<Integer>> item : Generator.combination(list).simple(2)) {
            HashSet<Integer> set_1 = item.get(0);
            HashSet<Integer> set_2 = item.get(1);
            Set<Integer> union_set = Sets.union(set_1, set_2);
            boolean is_union = list.contains(union_set);
            Set<Integer> intersection_set = Sets.intersection(set_1, set_2);
            boolean is_intersection = list.contains(intersection_set);
            if (!is_union || !is_intersection) {
                return false;
            }
        }
        return true;
    }

    public static long calculate(int length) {
        List<Integer> init_data_list = new ArrayList<>(length);
        for (int i = 1; i <= length; i++) {
            init_data_list.add(i);
        }
        HashSet<Integer> empty_set = new HashSet<>();
        HashSet<Integer> init_data_set = new HashSet<>(init_data_list);
        List<HashSet<Integer>> first_step_data_list = Generator
                .subset(init_data_list)
                .simple()
                .stream()
                .filter((item) -> !item.isEmpty() && !item.equals(init_data_list))
                .map(HashSet<Integer>::new)
                .collect(Collectors.toList());
//        first_step_data_list.forEach(System.out::println);
        logger.info("first_step_data_list.size=" + first_step_data_list.size());
        long total_count;
        total_count = IntStream.rangeClosed(1, first_step_data_list.size())
                .parallel()
                .mapToObj((i) -> {
                            long count;
                            count = Generator.combination(first_step_data_list)
                                    .simple(i)
                                    .stream()
                                    .filter((item) -> !item.isEmpty())
                                    .parallel()
                                    .peek((item) -> {
                                        item.add(empty_set);
                                        item.add(init_data_set);
                                    })
                                    .map(App::calculate_second_step)
                                    .filter((item) -> item)
                                    .count();
                            logger.info("when i=" + i + " count=" + count);
                            return count;
                        }
                )
                .mapToLong((value) -> value)
                .sum();
        total_count++;
        logger.info("result=" + total_count);
        return total_count;
    }

    public static void main(String[] args) {
        int length = 5;
        calculate(length);

    }
}
