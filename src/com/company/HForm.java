package com.company;

import jm.music.data.Score;
import jm.util.Read;
import jm.util.Write;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

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

    // private JPopupMenu popup;
    private int voice0;
    private int voice1;
    private int voice2;
    private int voice3;

    public HForm() {

        voice0 = 0;
        voice1 = 0;
        voice2 = 0;
        voice3 = 0;

        HashMap<String, Integer> instruments = new HashMap<>();
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

                JOptionPane.showMessageDialog(null, "Calming has been selected.");
            }
        });
        buttonCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voice0 = VIOLIN;
                voice1 = BANJO;
                voice2 = ACOUSTIC_GUITAR;
                voice3 = ACCORDION;
                JOptionPane.showMessageDialog(null, "Country has been selected.");

            }

        });




        comboBox0.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // JOptionPane.showMessageDialog(null, "the selected item changed");
                String instrument0 = (String) comboBox0.getSelectedItem();
                // JOptionPane.showMessageDialog(null, instrument0 + " SELECTED");
                voice0 = instruments.get(instrument0);
                // JOptionPane.showMessageDialog(null, "int version = " + voice0);
            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               // JOptionPane.showMessageDialog(null, "the selected item changed");
                String instrument1 = (String) comboBox1.getSelectedItem();
                // JOptionPane.showMessageDialog(null, instrument1 + " SELECTED");
                voice1 = instruments.get(instrument1);
                // JOptionPane.showMessageDialog(null, "int version = " + voice1);
            }
        });

        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // JOptionPane.showMessageDialog(null, "the selected item changed");
                String instrument2 = (String) comboBox2.getSelectedItem();
                // JOptionPane.showMessageDialog(null, instrument2 + " SELECTED");
                voice2 = instruments.get(instrument2);
                // JOptionPane.showMessageDialog(null, "int version = " + voice2);
            }
        });

        comboBox3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // JOptionPane.showMessageDialog(null, "the selected item changed");
                String instrument3 = (String) comboBox3.getSelectedItem();
                // JOptionPane.showMessageDialog(null, instrument3 + " SELECTED");
                voice3 = instruments.get(instrument3);
                // JOptionPane.showMessageDialog(null, "int version = " + voice3);
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
    }





         public static void main (String[]args){
            JFrame frame = new JFrame("HForm");
            frame.setContentPane(new HForm().panelMain);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        }


}