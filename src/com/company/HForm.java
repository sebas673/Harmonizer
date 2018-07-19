package com.company;

import jm.music.data.Score;
import jm.util.Read;
import jm.util.Write;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static jm.constants.ProgramChanges.*;

public class HForm {
    private JButton HarmonizeButton;
    private JPanel panelMain;
    private JButton buttonSaxQuartet;
    private JList InstrumentList4;
    private JButton buttonCalming;
    private JButton buttonClassical;
    private JButton buttonCountry;
    private JPanel panelGenres;
    private JComboBox comboBox0;
    private JComboBox comboBox3;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JList list1;
    private JList list2;
    private JButton makeMinorButton;
    private JComboBox lastClickedCBox;

    // private JPopupMenu popup;
    private int voice0;
    private int voice1;
    private int voice2;
    private int voice3;

    private HashMap<String, Integer> instruments = new HashMap<>();
    {
        instruments.put("ACOUSTIC BASS", ACOUSTIC_BASS);
        instruments.put("ALTO SAXOPHONE", ALTO_SAXOPHONE);
        instruments.put("BANJO", BANJO);
        instruments.put("BARI SAXOPHONE", BARI_SAX);
        instruments.put("BASSOON", BASSOON);
        instruments.put("BELLS", BELLS);
        instruments.put("CELLO", CELLO);
        instruments.put("CLARINET", CLARINET);
        instruments.put("ELECTRIC BASS", ELECTRIC_BASS);
        instruments.put("ELECTRIC GUITAR", ELECTRIC_GUITAR);
        instruments.put("ELECTRIC PIANO", ELECTRIC_PIANO);
        instruments.put("FIDDLE", FIDDLE);
        instruments.put("FLUTE", FLUTE);
        instruments.put("FRENCH HORN", FRENCH_HORN);
        instruments.put("GUITAR", GUITAR);
        instruments.put("HARP", HARP);
        instruments.put("MARIMBA", MARIMBA);
        instruments.put("OBOE", OBOE);
        instruments.put("ORGAN", ORGAN);
        instruments.put("PIANO", ACOUSTIC_GRAND);
        instruments.put("PICCOLO", PICCOLO);
        instruments.put("SOPRANO SAXOPHONE", SOPRANO_SAXOPHONE);
        instruments.put("STEEL DRUM", STEEL_DRUM);
        instruments.put("SYNTH BASS", SYNTH_BASS);
        instruments.put("TENOR SAXOPHONE", TENOR_SAXOPHONE);
        instruments.put("TIMPANI", TIMPANI);
        instruments.put("TROMBONE", TROMBONE);
        instruments.put("TRUMPET", TRUMPET);
        instruments.put("TUBA", TUBA);
        instruments.put("VIOLA", VIOLA);
        instruments.put("VIOLIN", VIOLIN);
        instruments.put("XYLOPHONE", XYLOPHONE);
    }

    private HashSet<Integer> twoPartInstruments = new HashSet<>();

    {
        twoPartInstruments.add(ELECTRIC_PIANO);
        twoPartInstruments.add(HARP);
        twoPartInstruments.add(MARIMBA);
        twoPartInstruments.add(PIANO);
        twoPartInstruments.add(XYLOPHONE);
    }

    private HashMap<JComboBox,JComboBox> comboBoxNeighbors = new HashMap<>();

    {
        comboBoxNeighbors.put(comboBox0, comboBox1);
        comboBoxNeighbors.put(comboBox1, comboBox2);
        comboBoxNeighbors.put(comboBox2, comboBox3);
        comboBoxNeighbors.put(comboBox3, comboBox3);
    }

    public HForm() {

        voice0 = 0;
        voice1 = 0;
        voice2 = 0;
        voice3 = 0;






        HarmonizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Score score = new Score();
                Read.midi(score);
                Harmonizer harmony = new Harmonizer(score);
                Score hScore = harmony.harmonize(voice0, voice1, voice2, voice3);
                Write.midi(hScore);
            }
        });

        buttonClassical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voice0 = VIOLIN;
                voice1 = VIOLA;
                voice2 = CELLO;
                voice3 = DOUBLE_BASS;
                comboBox0.setSelectedItem("VIOLIN");
                comboBox1.setSelectedItem("VIOLA");
                comboBox2.setSelectedItem("CELLO");
                comboBox3.setSelectedItem("ACOUSTIC BASS");
                JOptionPane.showMessageDialog(null, "Classical has been selected.");
            }
        });
        buttonSaxQuartet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voice0 = SOPRANO_SAXOPHONE;
                voice1 = ALTO_SAXOPHONE;
                voice2 = TENOR_SAXOPHONE;
                voice3 = BARITONE_SAXOPHONE;
                comboBox0.setSelectedItem("SOPRANO SAXOPHONE");
                comboBox1.setSelectedItem("ALTO SAXOPHONE");
                comboBox2.setSelectedItem("TENOR SAXOPHONE");
                comboBox3.setSelectedItem("BARI SAXOPHONE");
                JOptionPane.showMessageDialog(null, "Sax Quartet has been selected.");
            }
        });
        buttonCalming.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voice0 = FLUTE;
                voice1 = BELLS;
                voice2 = HARP;
                voice3 = ACOUSTIC_BASS;

                comboBox0.setSelectedItem("FLUTE");
                comboBox1.setSelectedItem("BELLS");
                comboBox2.setSelectedItem("HARP");
                comboBox3.setSelectedItem("ACOUSTIC BASS");
                JOptionPane.showMessageDialog(null, "Calming has been selected.");
            }
        });
        buttonCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voice0 = VIOLIN;
                voice1 = BANJO;
                voice2 = ACOUSTIC_GUITAR;
                voice3 = ACOUSTIC_BASS;
                comboBox0.setSelectedItem("VIOLIN");
                comboBox1.setSelectedItem("BANJO");
                comboBox2.setSelectedItem("GUITAR");
                comboBox3.setSelectedItem("ACOUSTIC BASS");
                JOptionPane.showMessageDialog(null, "Country has been selected.");

            }

        });








        makeMinorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Score scoreToTranspose = new Score();
                Read.midi(scoreToTranspose);
                transposition minorScore = new transposition(scoreToTranspose);
                Score newScore = minorScore.transpose();
                Write.midi(newScore);
            }
        });






        comboBox0.addMouseListener(new myMouseListener());
        comboBox1.addMouseListener(new myMouseListener());
        comboBox2.addMouseListener(new myMouseListener());
        comboBox3.addMouseListener(new myMouseListener());
        comboBox0.addItemListener(new myItemListener());
        comboBox1.addItemListener(new myItemListener());
        comboBox2.addItemListener(new myItemListener());
        comboBox3.addItemListener(new myItemListener());



        comboBox0.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
    }

    class myItemListener implements ItemListener {
        public myItemListener() {

        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox source = (JComboBox) e.getSource();
            if (lastClickedCBox == source) {
                Object instrument = source.getSelectedItem();
                String stringInstrument = (String) instrument;

                int voice = instruments.get(stringInstrument);

                if (twoPartInstruments.contains(voice)) {

                    (comboBoxNeighbors.get(source)).setSelectedItem(instrument);
                }
            }
        }
    }



    class myMouseListener extends MouseAdapter {

        public myMouseListener() {

        }

        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            lastClickedCBox = (JComboBox) e.getSource();
        }
    }




         public static void main (String[]args){
            JFrame frame = new JFrame("HForm");
            frame.setContentPane(new HForm().panelMain);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        }


}