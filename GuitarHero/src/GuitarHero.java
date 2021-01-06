public class GuitarHero {

  public static void main(String[] args) {
    String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    GuitarString[] guitarStrings = new GuitarString[37];

    for (int i = 0; i < keyboardString.length(); i++) {
      double letterFreq = 440 * Math.pow(2, (i - 24) / 12);
      guitarStrings[i] = new GuitarString(letterFreq);
    }

    // the main input loop
    Keyboard keyboard = new Keyboard();
    while (true) {

      // check if the user has played a key; if so, process it
      if (keyboard.hasNextKeyPlayed()) {

        // the key the user played
        char key = keyboard.nextKeyPlayed();
        // pluck the corresponding string
        if (keyboardString.indexOf(key) != -1) {
          guitarStrings[keyboardString.indexOf(key)].pluck();
        }
      }

      // compute the superposition of the samples
      double sample = 0;
      for (int i = 0; i < keyboardString.length(); i++) {
        sample += guitarStrings[i].sample();
      }

      // play the sample on standard audio
      StdAudio.play(sample);

      // advance the simulation of each guitar string by one step
      for (int i = 0; i < keyboardString.length(); i++) {
        guitarStrings[i].tic();
      }
    }
  }
}
