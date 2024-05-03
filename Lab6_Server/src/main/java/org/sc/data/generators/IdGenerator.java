package org.sc.data.generators;

import org.sc.data.Flat;
import org.sc.manager.CollectionManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
/**
 * Данный класс отвечает за генерацию id для обхектов коллекции
 *
 * @author Kemansu
 * @since 1.0
 */
public class IdGenerator {
    public static Queue<Long> IdArray = new ArrayDeque<>();

    public static void Fuel(){
        IdArray.clear();
        ArrayList<Long> list = new ArrayList<>();
        for (long i = 1; i < Math.pow(2, 10); i++){
            list.add(i);
        }

        // проверка на уникальность
        ArrayDeque<Flat> arrayDeque = CollectionManager.getArrayDeque();
        for (Flat flat : arrayDeque) {
            if (list.contains(flat.getId())) {
                list.remove(flat.getId());
            }
        }

        for (Long id : list) {
            IdArray.add(id);
        }

    }



    public static Long getId() {
        return IdArray.poll();
    }



}
