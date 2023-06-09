package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/birbd.wav");
        soundURL[1] = getClass().getResource("/sound/track1.wav");
        soundURL[2] = getClass().getResource("/sound/track2.wav");
        soundURL[3] = getClass().getResource("/sound/track3.wav");
        soundURL[4] = getClass().getResource("/sound/pikup.wav");
        soundURL[5] = getClass().getResource("/sound/brbvill2.wav");
        soundURL[6] = getClass().getResource("/sound/hit.wav");
        soundURL[7] = getClass().getResource("/sound/levelup.wav");
        soundURL[8] = getClass().getResource("/sound/cursor.wav");
    }
    public void setFile(int i){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }


}
