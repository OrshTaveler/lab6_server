package utilities;

import initials.HumanBeing;
import initials.WeaponType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HumanBeingDAO implements Serializable {
    ArrayList<HumanBeing> humanBeings;
    Lock lock;
    public int idPointer;
    public HumanBeingDAO(){
        humanBeings = new ArrayList<>();
        idPointer = 0;
        lock = new ReentrantLock();
    }
    public void add(HumanBeing humanBeing){
        lock.lock();
        try{
         humanBeings.add(humanBeing);
         idPointer++;
        }
        finally {
            lock.unlock();
        }
    }
    public void add(int index,HumanBeing humanBeing) throws IndexOutOfBoundsException{
        lock.lock();
        try{
         humanBeings.add(index,humanBeing);
         idPointer++;
        }
        finally {
            lock.unlock();
        }
    }

    public HumanBeing getById(int id) throws NoSuchElementException{
        lock.lock();
        try {
            for (HumanBeing humanBeing : humanBeings) {
                if (humanBeing.getId() == id) {
                    return humanBeing;
                }
            }
            throw new NoSuchElementException("С таким id людей нет");
        }
        finally {
            lock.unlock();
        }
    }
    public HumanBeing getByWeaponType(WeaponType weaponType){
        lock.lock();
        try {
            for (HumanBeing humanBeing : humanBeings) {
                if (humanBeing.getWeaponType() == weaponType) {
                    return humanBeing;
                }
            }
            throw new NoSuchElementException("С таким оружием людей нет");
        }
        finally {
            lock.unlock();
        }
    }
    public int getIndexById(int id){
        lock.lock();
        try{
         for (int i = 0; i <= humanBeings.size(); i++){
             if(humanBeings.get(i).getId() == id){
                 return i;
             }
         }
         throw new NoSuchElementException("С таким id людей нет");
        }
        finally {
            lock.unlock();
        }
    }
    public HumanBeing getByIndex(int index) throws IndexOutOfBoundsException{
        lock.lock();
        try{
         return humanBeings.get(index);
        }
        finally {
            lock.unlock();
        }
    }
    public void remove(HumanBeing humanBeing){
        lock.lock();
        try{
         this.humanBeings.remove(humanBeing);
        }
        finally {
            lock.unlock();
        }
    }


    public void clear(){
        lock.lock();
        try{
         this.humanBeings.clear();
        }
        finally {
            lock.unlock();
        }
    }
    public List<HumanBeing> startsWithName(String start){
        lock.lock();
        try{
         List<HumanBeing> res = new ArrayList<>();
         for(HumanBeing humanBeing: humanBeings){
             if(humanBeing.getName().startsWith(start)){
                 res.add(humanBeing);
             }
         }
         return res;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
         String res = "";
         for(HumanBeing humanBeing: humanBeings){
             res += humanBeing.toString()+'\n';
         }
         return res;
        }
        finally {
            lock.unlock();
        }
    }

    public ArrayList<HumanBeing> getHumanBeings() {
        lock.lock();
        try{
         return humanBeings;
        }
        finally {
            lock.unlock();
        }
    }
}
