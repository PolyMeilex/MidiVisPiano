package pac;

/* http://blog.taragana.com/index.php/archive/how-to-play-a-midi-file-from-a-java-application/ */
import javax.sound.midi.*;
import java.io.*;

//import java.util.ArrayList;

public class Player extends Thread {
    String File = "Music03.mid";
    Start RootParrent;

    public class myListener implements Receiver {
        public void send(MidiMessage message, long timeStamp) {
            // System.out.println(message.getStatus());
            if (message instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) message;
                int channel = sm.getChannel();
                int command = sm.getCommand();

                if (command == ShortMessage.NOTE_ON) {
                    // String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
                    // int note = sm.getData1() % 12;
                    // String name = NOTE_NAMES[note];


                    int rawKey = sm.getData1();

                    Boolean State = true;

                    if (sm.getData2() == 0)
                        State = false;
                    else
                        State = true;

                    RootParrent.noteTrigger(rawKey, channel, State);
                } else if (command == ShortMessage.NOTE_OFF) {
                    int rawKey = sm.getData1();

                    RootParrent.noteTrigger(rawKey, channel, false);
                }
            }
        }

        public void close() {
            System.out.println("O");
        }
    }

    Player(String file, Start Parrent) {
        this.RootParrent = Parrent;
    }

    public void run() {
        File midiFile = new File(this.File);

        // Play once
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            sequencer.open();

            Receiver receiver = new myListener();

            Transmitter transmitter = sequencer.getTransmitter();

            transmitter.setReceiver(receiver);

            sequencer.start();

            while (true) {
                if (sequencer.isRunning()) {
                    try {
                        Thread.sleep(1000); // Check every second
                    } catch (InterruptedException ignore) {
                        break;
                    }
                } else
                    break;
            }
            // Close the MidiDevice & free resources
            sequencer.stop();
            sequencer.close();
        } catch (MidiUnavailableException mue) {
            System.out.println("Midi device unavailable!");
        } catch (InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        } catch (IOException ioe) {
            System.out.println("I/O Error!");
        }
    }
}
