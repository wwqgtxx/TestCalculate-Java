package xyz.wwqgtxx.testcalculate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/7.
 */
public class Sets {
    public  static <T> Set<T> intersection(Set<T> s1, Set<T> s2) {
        Set<T> result = new HashSet<T>(s1);
        result.retainAll(s2);
        return result;
    }

    public  static <T> Set<T> union(Set<T> s1, Set<T> s2) {
        Set<T> result = new HashSet<T>(s1);
        result.addAll(s2);
        return result;
    }

    //Subtract subset from superset
    public  static <T> Set<T> difference (Set<T> superset, Set<T> subset) {
        Set<T> result = new HashSet<T>(superset);
        result.removeAll(subset);
        return result;
    }

    //Reflexive --everything not in their intersection
    public static <T> Set<T> complement(Set<T>s1,Set<T> s2){
        return difference(union(s1,s2),intersection(s1,s2));
    }
}