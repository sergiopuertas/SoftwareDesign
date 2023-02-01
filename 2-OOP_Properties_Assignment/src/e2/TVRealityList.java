package e2;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TVRealityList<T> implements Iterable<T>{
    List<T> candidates;
    boolean rebound;
    /*
    Our implementation of the TVreality list transforms a normal list of string to an iterable list with the iterators we designed.
    We believe it is not the most simple implementation, as we use 2 lists, but we feel that it is the most common sense application,
    as when we imagine that in a real reality show, only a list of strings is stored as the main goal is to have the names of the contestants.
    That way, real life programs could transform their list only to use it to choose the final winner of the slot or any prize.
     */
    public TVRealityList(boolean rebound,List<T>candidates) {
        this.setRebound(rebound);
        this.candidates = candidates;
    }
    public boolean getRebound(){
        return this.rebound;
    }
    public void setRebound(boolean rebound){
        this.rebound = rebound;
    }

    @Override
    public Iterator<T> iterator() {
        if (getRebound()) {
            return new Rebound<>(candidates);
        }
        else {
            return new Circular<>(candidates);
        }
    }


    public static class Rebound<E> implements Iterator<E>{
        int index = -1;
        boolean canRemove = false;
        boolean back = false;
        List<E> TVRealityList;
        public Rebound(List<E> list) {
            this.TVRealityList = list;
        }
        @Override
        public boolean hasNext() {
            return (TVRealityList.size() !=1);
        }
        @Override
        public E next() {
            if(TVRealityList.isEmpty())  throw new NoSuchElementException("First enter the contestants from which to choose a winner...");
            if (back) {
                index--;
                if(index == 0)  {
                    back = false;
                }
                else if (index == -1){
                    index+=2;
                    back = false;
                }
            }
            else {
                index++;
                if (index == TVRealityList.size()){
                    index-=2;
                    back = true;
                }
            }
            canRemove = true;
            return TVRealityList.get(index);
        }
        @Override
        public void remove()
        {
            if (!canRemove) throw new IllegalStateException();
            System.out.println(TVRealityList.get(index) +" has been disqualified...\n");
            if(!back){
                TVRealityList.remove(index);
                index--;
            }
            else {
                TVRealityList.remove(index);
            }
            canRemove = false;
        }
        @Override
        public String toString() {
            return "Contestants=" +TVRealityList;
        }
    }
    public static class Circular<E> implements Iterator<E>{
        int index = -1;
        boolean canRemove = false;
        List<E> TVRealityList;
        public Circular(List<E> list) {
            this.TVRealityList = list;
        }
        @Override
        public boolean hasNext() {
            return !(TVRealityList.size()==1);
        }
        @Override
        public E next()
        {
            if (hasNext())
            {
                index = (index+1)%TVRealityList.size();
                canRemove = true;
                return TVRealityList.get(index);
            }
            throw new NoSuchElementException();
        }
        @Override
        public void remove()
        {
            if (!canRemove)
            {
                throw new IllegalStateException();
            }
            System.out.println(TVRealityList.get(index) +" has been disqualified...\n");
            TVRealityList.remove(index);
            index--;
            canRemove = false;
        }
        @Override
        public String toString() {
            return "Contestants=" + TVRealityList;
        }
    }
    public int size(){
        return candidates.size();
    }
    public boolean isEmpty(){
        return candidates.isEmpty();
    }
}
