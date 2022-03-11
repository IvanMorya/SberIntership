package SberIntership;

import java.text.MessageFormat;
import java.util.*;

import static SberIntership.CityUtils.parse;

public class Main {

    public static void main(String[] args) {
        List<City> cities = parse();
        findCountCityByRegionV1(cities);
    }

    /**
     * Поиск количества городов в каждом из регионов (old school)
     *
     * @param cities массив городов
     */
    private static void findCountCityByRegionV1(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        for (City city : cities) {
            if (!regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), 1);
            } else {
                regions.put(city.getRegion(), regions.get(city.getRegion()) + 1);
            }
        }
        for (String key : regions.keySet()) {
            System.out.println(MessageFormat.format(" {0} = {1}", key, regions.get(key)));
        }
    }

    /**
     * Поиск количества городов в каждом из регионов (lambda-выражения)
     *
     * @param cities массив городов
     */
    private static void findCountCityByRegionV2(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegion(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" {0} = {1}", k, v)));
    }

    /**
     * Поиск города с наибольшим количеством жителей путем простого перебора
     *
     * @param cities массив городов
     */
    private static void findBySimpleBruteForce(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        City current = array[0];
        int index = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i].getPopulation() > current.getPopulation()) {
                current = array[i];
                index = i;
            }
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", index, array[index]));
    }

    /**
     * Поиск города с наибольшим количеством жителей путем сортировки вставками
     *
     * @param cities массив городов
     */
    private static void findByInsertionSort(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        for (int i = 1; i < array.length; i++) {
            City current = array[i];
            int j = i - 1;
            while (j >= 0 && current.getPopulation() < array[j].getPopulation()) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", array.length - 1, array[array.length - 1]));
    }

    /**
     * Поиск города с наибольшим количеством жителей с использованием lambda-выражений
     *
     * @param cities массив городов
     */
    private static void findMaxPopulation(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }
}
