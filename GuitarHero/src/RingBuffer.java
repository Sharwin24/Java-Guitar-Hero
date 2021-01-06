public class RingBuffer {

  private double[] rb; // items in the buffer
  private int first; // index for the next dequeue or peek
  private int last; // index for the next enqueue
  private int size; // number of items in the buffer

  // creates an empty ring buffer with the specified capacity
  public RingBuffer(int capacity) {
    this.rb = new double[capacity];
    this.first = 0;
    this.last = 0;
    this.size = 0;
  }

  // returns the capacity of this ring buffer
  public int capacity() {
    return this.rb.length;
  }

  // returns the number of items currently in this ring buffer
  public int size() {
    return this.size;
  }

  // is this ring buffer empty (size equals zero)?
  public boolean isEmpty() {
    return this.size == 0;
  }

  // is this ring buffer full (size equals capacity)?
  public boolean isFull() {
    return this.size == this.capacity();
  }

  // adds item x to the end of this ring buffer
  public void enqueue(double x) {
    if (this.isFull()) {
      throw new RuntimeException("No Space Available");
    }
    else {
      this.rb[this.last] = x;
      this.last++;

      if (this.last == this.capacity()) {
        this.last = 0;
      }
      this.size++;
    }
  }

  // deletes and returns the item at the front of this ring buffer
  public double dequeue() {
    if (this.isEmpty()) {
      throw new RuntimeException("Buffer is Empty");
    }
    else {
      double toReturn = this.rb[this.first];
      this.first++;
      
      if (this.first == this.capacity()) {
        first = 0;
      }
      
      size--;
      return toReturn;
    }
  }

  // returns the item at the front of this ring buffer
  public double peek() {
    if (this.isEmpty()) {
      throw new RuntimeException("Buffer is Empty");
    }
    else {
      return rb[first];
    }
  }

  // tests this class by directly calling all instance method
  public static void main(String[] args) {
//    int n = Integer.parseInt(args[0]);
    int n = 10;
    RingBuffer buffer = new RingBuffer(n);
    for (int i = 1; i <= n; i++) {
      buffer.enqueue(i);
    }
    double t = buffer.dequeue();
    buffer.enqueue(t);
    System.out.println("Size after wrap-around is " + buffer.size());
    while (buffer.size() >= 2) {
       double x = buffer.dequeue();
       double y = buffer.dequeue();
       buffer.enqueue(x + y);
    }
    System.out.println(buffer.peek());

  }
}
