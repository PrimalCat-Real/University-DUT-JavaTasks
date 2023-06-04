import java.util.Objects;

public class LinkedList {
    private Link firthLink = null;
    private Link lastLink = null;
    private int size = 0;
    public Link getFirthLink() {
        return firthLink;
    }

    public Link getLastLink() {
        return lastLink;
    }

    public int getSize() {
        return size;
    }


    public LinkedList(){

    }
    public Boolean isEmpty(){
        return this.firthLink == null;
    }

    public void add(){
        Link newLink = new Link(1);
        if (firthLink == null){
            firthLink = lastLink = newLink;
        }else{
            this.lastLink.nextLink = newLink;
        }
        size++;
    }

    public void add(int index){
        // check if index in range of LinkedList
        Objects.checkIndex(index, size+1);

        Link newLink = new Link(1);
        if (firthLink == null){
            firthLink = lastLink = newLink;
        }else if (index == 0){
            // \|/------
            newLink.nextLink = firthLink;
            firthLink = newLink;
        }else if(index == size){
            // ------\|/
            lastLink.nextLink = newLink;
            lastLink = newLink;
        }else{
            // ----\|/--
            Link current = getLinkByIndex(index);
            // swap next link between newLink and current
            newLink.nextLink = current.nextLink;
            current.nextLink = newLink;
            this.lastLink.nextLink = newLink;
        }
        size++;
    }

    public Link getLinkByIndex(int index){
        Link current = firthLink;
        for (int i = 0; i < index; i++) {
            current = current.nextLink;
        }
        return current;
    }

    public static void main(String[] args) {

    }


}
