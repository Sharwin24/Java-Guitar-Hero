import java.util.Random;

public class GuitarString {
  RingBuffer rb;
  Random rand = new Random();

  // creates a guitar string of the specified frequency,
  // using a sampling rate of 44,100
  public GuitarString(double frequency) {
    int rbSize = (int) Math.ceil(44100 / frequency);
    this.rb = new RingBuffer(rbSize);
    for (int i = 0; i < rbSize; i++) {
      this.rb.enqueue(0);
    }
  }

  // creates a guitar string whose length and initial values
  // are given by the specified array
  public GuitarString(double[] init) {
    this.rb = new RingBuffer(init.length);

    for (double i : init) {
      rb.enqueue(i);
    }

  }

  // returns the number of samples in the ring buffer
  public int length() {
    return this.rb.size();
  }

  // plucks this guitar string (by replacing the ring buffer
  // with white noise)
  public void pluck() {

    for (int i = 0; i < this.rb.size(); i++) {
      this.rb.dequeue();
      this.rb.enqueue(getRandDouble(-0.5, 0.5));
    }
  }

  // advances the Karplus-Strong simulation one time step
  public void tic() {
    double firstVal = this.rb.dequeue();
    double value = 0.996 * this.getAvg(firstVal, this.rb.peek());
    this.rb.enqueue(value);
  }

  // returns the current sample
  public double sample() {
    return this.rb.peek();
  }

  // Gets a random double between -0.5 and 0.5
  public double getRandDouble(double rangeMin, double rangeMax) {
    double randomValue = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();

    return randomValue;
  }

  public double getAvg(double num, double num2) {
    return (num + num2) / 2;
  }

  // tests this class by directly calling both constructors
  // and all instance methods
  public static void main(String[] args) {
    double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
    GuitarString testString = new GuitarString(samples);
    int m = 25; // 25 tics
    for (int i = 0; i < m; i++) {
      double sample = testString.sample();
      System.out.printf("%6d %8.4f\n", i, sample);
      testString.tic();
    }

  }
}
