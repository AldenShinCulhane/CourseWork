/**
 * 
 * @Gerred G ICS4U
 * @version Jan 2020
 */

import javax.sound.sampled.*;
import java.io.File;

class Sound {
    private Clip sound;
    private String soundFile;
    
    public void loadSound(String soundFile){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(soundFile));
            this.sound = AudioSystem.getClip();
            this.sound.open(audioStream);
            FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-100.0f); // Reduce volume by 10 decibels.
        }
        catch(Exception e) {System.out.println("error loading sound");};        
    }
    public void playSound(){
        if (this.sound.isRunning()){
            this.sound.stop();              // stop the sound effect if still running
//            this.sound.flush();             // clear the buffer with audio data
//            this.sound.setFramePosition(0); // prepare to start from the beginning
        }
        this.sound.start();
    }
    public void stopSound(){
        if (this.sound.isRunning()){
            this.sound.stop();              // stop the sound effect if still running
            this.sound.flush();             // clear the buffer with audio data
            this.sound.setFramePosition(0); // prepare to start from the beginning
        }
    }
    public void pausedSound(){
        if (this.sound.isRunning()){
            this.sound.stop();              // stop the sound effect if still running
        }
    }
}

