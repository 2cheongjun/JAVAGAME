import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class successSound extends Thread{ // 주문성공시 출력되는 쓰레드


    @Override
    public void run() {

        try {
            // Open an audio input stream.
            File soundFile = new File("/Users/doit/Desktop/Class_first/src/music/111.wav"); //you could also get the sound file with an URL
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioIn.getFormat();
            // Get a sound clip resource.

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            // Open audio clip and load samples from the audio input stream.

            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}


//    successSound soundOrder = new successSound();// 주문성공시 효과음 쓰레드 사운드
//    Thread orderOk = new Thread(soundOrder);
//            orderOk.start();
