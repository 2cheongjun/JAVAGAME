import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class ThreadMainSound extends Thread{ // 메인 배경음악 쓰레드 게임시작시 실행됨



    @Override
    public void run() {

        try {

            File soundFile = new File("/Users/doit/Desktop/Class_first/src/music/Ponte de Abril - Steve Adams.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioIn.getFormat();


            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);


            clip.open(audioIn);
            clip.start();
            clip.loop(3); // 배경음악 반복횟수


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }
}


