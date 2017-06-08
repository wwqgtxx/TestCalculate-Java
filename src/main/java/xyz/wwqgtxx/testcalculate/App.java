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

    public static boolean calculate_second_step(List<BitSet> list) {
//        logger.info(list); // like: [[1], [1, 2], [1, 2, 4], [3, 4], [2, 3, 4], [1, 3, 4, 5]]
        boolean is_union;
        boolean is_intersection;
        BitSet set_1;
        BitSet set_2;
        BitSet union_set;
        BitSet intersection_set;
        for (List<BitSet> item : Generator.combination(list).simple(2)) {
            set_1 = item.get(0);
            set_2 = item.get(1);
            union_set = (BitSet)set_1.clone();
            union_set.or(set_2);
            intersection_set  = (BitSet)set_1.clone();
            intersection_set.and(set_2);
            is_union = list.contains(union_set);
            is_intersection = list.contains(intersection_set);
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
        BitSet empty_set = new BitSet();
        BitSet init_data_set = new BitSet();
        init_data_list.forEach(init_data_set::set);
        List<BitSet> first_step_data_list = Generator
                .subset(init_data_list)
                .simple()
                .stream()
                .filter((item) -> !item.isEmpty() && !item.equals(init_data_list))
                .map((value)->{
                    BitSet bitSet = new BitSet();
                    value.forEach(bitSet::set);
                    return bitSet;
                })
//                .map(HashSet<Integer>::new)
                .collect(Collectors.toList());
//        first_step_data_list.forEach(System.out::println);
        logger.info("first_step_data_list.size=" + first_step_data_list.size());
        long total_count;
        total_count = IntStream.rangeClosed(1, first_step_data_list.size())
//                .parallel()
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
        int length;
        if (args.length > 0) {
            length = Integer.parseInt(args[0]);
        } else {
            System.out.println("Please input the length:");
            Scanner input = new Scanner(System.in);
            length = input.nextInt();
        }
        System.out.println("Start Calculate length=" + length);
        calculate(length);

    }
}
