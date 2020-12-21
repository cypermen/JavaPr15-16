import java.util.Collection;

public class CircularDoublyLinkedList<T> {
    private static class Node<T> {

        Node<T> previous;
        T elem;
        Node<T> next;


        Node(Node<T> previous, T elem, Node<T> next) {
            this.previous = previous;
            this.elem = elem;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<T> last;
    private Node<T> first;


    public CircularDoublyLinkedList() {}

    public CircularDoublyLinkedList(Collection<? extends T> collection) {
        addAll(collection);
    }

    public boolean add(T elem) {
        linkLast(elem);
        return true;
    }

    public boolean addAll(Collection<? extends T> collection) {
        return addAll(size, collection);
    }

    @SuppressWarnings("Unchecked")
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndex(index);
        Object[] objects = collection.toArray();
        int numNew = objects.length;
        if (numNew == 0)
            return false;
        Node<T> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.previous;
        }
        for (Object obj : objects) {
            T e = (T) obj;
            Node<T> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }
        if (succ == null)
            last = pred;
        else {
            pred.next = succ;
            succ.previous = pred;
        }
        size += numNew;
        return true;
    }

    public boolean remove(Object object) {
        if (object == null) {
            for (Node<T> f = first; f != null; f = f.next) {
                if (f.elem == null) {
                    unlink(f);
                    return true;
                }
            }
        } else {
            for (Node<T> f = first; f != null; f = f.next) {
                if (object.equals(f.elem)) {
                    unlink(f);
                    return true;
                }
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    private void checkIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException();
    }

    private void linkFirst(T element) {
        Node<T> first = this.first;
        Node<T> newNode = new Node<>(null, element, first);
        this.first = newNode;
        if (first == null)
            last = newNode;
        else
            first.previous = newNode;
        size++;
    }

    private void linkLast(T element) {
        Node<T> last = this.last;
        Node<T> newNode = new Node<>(last, element, null);
        this.last = newNode;
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
        size++;
    }

    void linkBefore(T element, Node<T> node) {
        Node<T> pred = node.previous;
        Node<T> newNode = new Node<>(pred, element, node);
        node.previous = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    private void unlink(Node<T> elem) {

        Node<T> previous = elem.previous;
        Node<T> next = elem.next;
        if (previous == null)
            first = next;
        else {
            previous.next = next;
            elem.previous = null;
        }
        if (next == null)
            last = previous;
        else {
            next.previous = previous;
            elem.next = null;
        }
        elem.elem = null;
        size--;
    }

    private Node<T> node(int index) {
        Node<T> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++)
                node = node.next;
        } else {
            node = last;
            for (int i = size - 1; i > index; i--)
                node = node.previous;
        }
        return node;
    }

    @SuppressWarnings("Unchecked")
    public T[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> x = first; x != null; x = x.next)
            result[i++] = x.elem;
        return (T[]) result;
    }
}
