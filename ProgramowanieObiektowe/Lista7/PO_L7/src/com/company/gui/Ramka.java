package com.company.gui;

import com.company.model.Pojazd;
import com.company.model.Samochod;
import com.company.model.Tramwaj;
import com.company.utils.FileUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Ramka extends JFrame {

    JLabel lTyp, lMiejsca, lKategoria, lKlasa1, lKlasa2, lKlasa3;
    JTextField fieldTyp, fieldMiejsca, fieldKategoria;
    private Object pojazd;
    public Ramka(Object pojazd, String a, String filepath)
    {
        super("Edycja pojazdu");
        this.pojazd = pojazd;

        zainicjujPolaPojazdu();
        setSize(600,600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



        String samochodName = Samochod.class.getSimpleName();
        String tramwajName = Tramwaj.class.getSimpleName();
        String pojazdName = Pojazd.class.getSimpleName();
        if (a.equals(samochodName)) {

            Samochod samochod;
            if(pojazd == null)
            {
                samochod = new Samochod();
            }
            else
            {

                samochod = (Samochod) pojazd;
            }

            JTextArea objectDescription = new JTextArea(samochod.toString());
            objectDescription.setBounds(50, 400, 300, 110);

            fieldTyp.setText(samochod.getTyp());
            fieldTyp.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    samochod.setTyp(fieldTyp.getText());
                    objectDescription.setText(samochod.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldTyp.getText().equals(""))
                    {
                        samochod.setTyp("-");
                    }
                    else
                    {
                        samochod.setTyp(fieldTyp.getText());
                    }
                    objectDescription.setText(samochod.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    samochod.setTyp(fieldTyp.getText());
                    objectDescription.setText(samochod.toString());
                }
            });

            fieldMiejsca.setText(String.valueOf(samochod.getLiczbaMiejsc()));
            fieldMiejsca.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    samochod.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    objectDescription.setText(samochod.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldMiejsca.getText().equals(""))
                    {
                        samochod.setLiczbaMiejsc(0);
                    }
                    else
                    {
                        samochod.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    }
                    objectDescription.setText(samochod.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    samochod.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    objectDescription.setText(samochod.toString());
                }
            });

            fieldKategoria.setText(samochod.getKategoriaPrawaJazdy());
            fieldKategoria.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    samochod.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    objectDescription.setText(samochod.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldKategoria.getText().equals(""))
                    {
                        samochod.setKategoriaPrawaJazdy("-");
                    }
                    else
                    {
                        samochod.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    }
                    objectDescription.setText(samochod.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    samochod.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    objectDescription.setText(samochod.toString());
                }
            });

            lKlasa1 = new JLabel("Rodzaj paliwa");
            lKlasa1.setBounds(50,200, 100,30);
            JTextField fieldPaliwo = new JTextField(samochod.getRodzajPaliwa());
            fieldPaliwo.setBounds(250,200, 100,30);
            fieldPaliwo.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    samochod.setRodzajPaliwa(fieldPaliwo.getText());
                    objectDescription.setText(samochod.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldPaliwo.getText().equals(""))
                    {
                        samochod.setRodzajPaliwa("-");
                    }
                    else
                    {
                        samochod.setRodzajPaliwa(fieldPaliwo.getText());
                    }
                    objectDescription.setText(samochod.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    samochod.setRodzajPaliwa(fieldPaliwo.getText());
                    objectDescription.setText(samochod.toString());
                }
            });

            lKlasa2 = new JLabel("Ilosc koni mechanicznych");
            lKlasa2.setBounds(50,250, 100,30);
            JTextField fieldIloscKoni = new JTextField(String.valueOf(samochod.getIloscKoniMechanicznych()));
            fieldIloscKoni.setBounds(250,250, 100,30);
            fieldIloscKoni.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    samochod.setIloscKoniMechanicznych(Integer.parseInt(fieldIloscKoni.getText()));
                    objectDescription.setText(samochod.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldIloscKoni.getText().equals(""))
                    {
                        samochod.setIloscKoniMechanicznych(0);
                    }
                    else
                    {
                        samochod.setIloscKoniMechanicznych(Integer.parseInt(fieldIloscKoni.getText()));
                    }
                    objectDescription.setText(samochod.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    samochod.setIloscKoniMechanicznych(Integer.parseInt(fieldIloscKoni.getText()));
                    objectDescription.setText(samochod.toString());
                }
            });


            lKlasa3 = new JLabel("Pojemnosc bagaznika");
            lKlasa3.setBounds(50,300, 100,30);
            JTextField fieldPojemnoscBagaznika = new JTextField(String.valueOf(samochod.getPojemnoscBagaznika()));
            fieldPojemnoscBagaznika.setBounds(250,300, 100,30);
            fieldPojemnoscBagaznika.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    samochod.setIloscKoniMechanicznych(Integer.parseInt(fieldPojemnoscBagaznika.getText()));
                    objectDescription.setText(samochod.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldPojemnoscBagaznika.getText().equals(""))
                    {
                        samochod.setPojemnoscBagaznika(0);
                    }
                    else
                    {
                        samochod.setPojemnoscBagaznika(Integer.parseInt(fieldPojemnoscBagaznika.getText()));
                    }
                    objectDescription.setText(samochod.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    samochod.setPojemnoscBagaznika(Integer.parseInt(fieldPojemnoscBagaznika.getText()));
                    objectDescription.setText(samochod.toString());
                }
            });

            add(lKlasa1);add(lKlasa2);add(lKlasa3);add(fieldPaliwo);add(fieldIloscKoni);add(fieldPojemnoscBagaznika);
            JButton saveBtn = new JButton("Zapisz samochod");
            saveBtn.setBounds(250,350, 120,30);
            saveBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    samochod.setTyp(fieldTyp.getText());
                    samochod.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    samochod.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    samochod.setRodzajPaliwa(fieldPaliwo.getText());
                    samochod.setIloscKoniMechanicznych(Integer.parseInt(fieldIloscKoni.getText()));
                    samochod.setPojemnoscBagaznika(Integer.parseInt(fieldPojemnoscBagaznika.getText()));
                    FileUtils.WriteObjectToFile(samochod, filepath);
                }
            });
            add(saveBtn);


            add(objectDescription);
        } else if(a.equals(tramwajName))
        {
            Tramwaj tramwaj;
            if(pojazd == null)
            {
                tramwaj = new Tramwaj();
            }
            else
            {
                tramwaj = (Tramwaj) pojazd;
            }

            JTextArea objectDescription = new JTextArea(tramwaj.toString());
            objectDescription.setBounds(50, 400, 300, 110);

            fieldTyp.setText(tramwaj.getTyp());
            fieldTyp.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    tramwaj.setTyp(fieldTyp.getText());
                    objectDescription.setText(tramwaj.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldTyp.getText().equals(""))
                    {
                        tramwaj.setTyp("-");
                    }
                    else
                    {
                        tramwaj.setTyp(fieldTyp.getText());
                    }
                    objectDescription.setText(tramwaj.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    tramwaj.setTyp(fieldTyp.getText());
                    objectDescription.setText(tramwaj.toString());
                }
            });

            fieldMiejsca.setText(String.valueOf(tramwaj.getLiczbaMiejsc()));
            fieldMiejsca.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    tramwaj.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    objectDescription.setText(tramwaj.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldMiejsca.getText().equals(""))
                    {
                        tramwaj.setLiczbaMiejsc(0);
                    }
                    else
                    {
                        tramwaj.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    }
                    objectDescription.setText(tramwaj.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    tramwaj.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    objectDescription.setText(tramwaj.toString());
                }
            });

            fieldKategoria.setText(tramwaj.getKategoriaPrawaJazdy());
            fieldKategoria.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    tramwaj.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    objectDescription.setText(tramwaj.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldKategoria.getText().equals(""))
                    {
                        tramwaj.setKategoriaPrawaJazdy("-");
                    }
                    else
                    {
                        tramwaj.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    }
                    objectDescription.setText(tramwaj.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    tramwaj.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    objectDescription.setText(tramwaj.toString());
                }
            });

            lKlasa1 = new JLabel("Czy niskopodlogowy");
            lKlasa1.setBounds(50,200, 150,30);
            JCheckBox fieldCzyNiskoPodlogowy = new JCheckBox();
            fieldCzyNiskoPodlogowy.setSelected(tramwaj.isCzyNiskopodlogowy());
            fieldCzyNiskoPodlogowy.setBounds(250,200, 100,30);
            fieldCzyNiskoPodlogowy.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    tramwaj.setCzyNiskopodlogowy(fieldCzyNiskoPodlogowy.isSelected());
                    objectDescription.setText(tramwaj.toString());
                }
            });


            lKlasa2 = new JLabel("Czy miejsca dla niepelnosprawnych");
            lKlasa2.setBounds(50,250, 200,30);
            JCheckBox fieldMiejscaDlaNiepelnosprawnych = new JCheckBox();
            fieldMiejscaDlaNiepelnosprawnych.setSelected((tramwaj.isCzyMiejscaDlaNiepelnosprawnych()));
            fieldMiejscaDlaNiepelnosprawnych.setBounds(250,250, 100,30);
            fieldMiejscaDlaNiepelnosprawnych.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    tramwaj.setCzyMiejscaDlaNiepelnosprawnych(fieldMiejscaDlaNiepelnosprawnych.isSelected());
                    objectDescription.setText(tramwaj.toString());
                }
            });

            lKlasa3 = new JLabel("Szerokosc osi");
            lKlasa3.setBounds(50,300, 100,30);
            JTextField fieldSzerokoscOsi = new JTextField(String.valueOf(tramwaj.getSzerokoscOsi()));
            fieldSzerokoscOsi.setBounds(250,300, 100,30);
            fieldSzerokoscOsi.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    tramwaj.setSzerokoscOsi(Integer.parseInt(fieldSzerokoscOsi.getText()));
                    objectDescription.setText(tramwaj.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldSzerokoscOsi.getText().equals(""))
                    {
                        tramwaj.setSzerokoscOsi(0);
                    }
                    else
                    {
                        tramwaj.setSzerokoscOsi(Integer.parseInt(fieldSzerokoscOsi.getText()));
                    }
                    objectDescription.setText(tramwaj.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    tramwaj.setSzerokoscOsi(Integer.parseInt(fieldSzerokoscOsi.getText()));
                    objectDescription.setText(tramwaj.toString());
                }
            });

            add(lKlasa1);add(lKlasa2);add(lKlasa3);add(fieldCzyNiskoPodlogowy);add(fieldMiejscaDlaNiepelnosprawnych);add(fieldSzerokoscOsi);
            JButton saveBtn = new JButton("Zapisz tramwaj");
            saveBtn.setBounds(250,350, 120,30);
            saveBtn.addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    tramwaj.setTyp(fieldTyp.getText());
                    tramwaj.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    tramwaj.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    tramwaj.setCzyNiskopodlogowy(fieldCzyNiskoPodlogowy.isSelected());
                    tramwaj.setCzyMiejscaDlaNiepelnosprawnych(fieldMiejscaDlaNiepelnosprawnych.isSelected());
                    tramwaj.setSzerokoscOsi(Integer.parseInt(fieldSzerokoscOsi.getText()));
                    FileUtils.WriteObjectToFile(tramwaj, filepath);
                }
            }));
            add(saveBtn);

            add(objectDescription);
        } else if(a.equals(pojazdName))
        {
            Pojazd vehicle;
            if(pojazd == null)
            {
                vehicle = new Pojazd();
            }
            else
            {
                vehicle = (Pojazd) pojazd;
            }

            JTextArea objectDescription = new JTextArea(vehicle.toString());
            objectDescription.setBounds(50, 400, 300, 110);

            fieldTyp.setText(vehicle.getTyp());
            fieldTyp.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    vehicle.setTyp(fieldTyp.getText());
                    objectDescription.setText(vehicle.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldTyp.getText().equals(""))
                    {
                        vehicle.setTyp("-");
                    }
                    else
                    {
                        vehicle.setTyp(fieldTyp.getText());
                    }
                    objectDescription.setText(vehicle.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    vehicle.setTyp(fieldTyp.getText());
                    objectDescription.setText(vehicle.toString());
                }
            });

            fieldMiejsca.setText(String.valueOf(vehicle.getLiczbaMiejsc()));
            fieldMiejsca.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    vehicle.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    objectDescription.setText(vehicle.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldMiejsca.getText().equals(""))
                    {
                        vehicle.setLiczbaMiejsc(0);
                    }
                    else
                    {
                        vehicle.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    }
                    objectDescription.setText(vehicle.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    vehicle.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    objectDescription.setText(vehicle.toString());
                }
            });

            fieldKategoria.setText(vehicle.getKategoriaPrawaJazdy());
            fieldKategoria.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    vehicle.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    objectDescription.setText(vehicle.toString());
                }
                public void removeUpdate(DocumentEvent e) {
                    if(fieldKategoria.getText().equals(""))
                    {
                        vehicle.setKategoriaPrawaJazdy("-");
                    }
                    else
                    {
                        vehicle.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    }
                    objectDescription.setText(vehicle.toString());
                }
                public void insertUpdate(DocumentEvent e) {
                    vehicle.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    objectDescription.setText(vehicle.toString());
                }
            });

            JButton saveBtn = new JButton("Zapisz pojazd");
            saveBtn.setBounds(250,350, 120,30);
            saveBtn.addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    vehicle.setTyp(fieldTyp.getText());
                    vehicle.setLiczbaMiejsc(Integer.parseInt(fieldMiejsca.getText()));
                    vehicle.setKategoriaPrawaJazdy(fieldKategoria.getText());
                    FileUtils.WriteObjectToFile(vehicle, filepath);
                }
            }));
            add(saveBtn);
            add(objectDescription);
        }
    }

    private void zainicjujPolaPojazdu() {
        lTyp = new JLabel("Typ pojazdu");
        lTyp.setBounds(50,50, 200,30);
        fieldTyp = new JTextField();
        fieldTyp.setBounds(250,50, 100,30);

        lMiejsca = new JLabel("Liczba miejsc w pojezdzie");
        lMiejsca.setBounds(50,100, 200,30);
        fieldMiejsca = new JTextField();
        fieldMiejsca.setBounds(250,100, 100,30);

        lKategoria = new JLabel("Kategoria prawa jazdy");
        lKategoria.setBounds(50,150, 200,30);
        fieldKategoria = new JTextField();
        fieldKategoria.setBounds(250,150, 100,30);

        add(lTyp);
        add(lMiejsca);
        add(lKategoria);
        add(fieldTyp);
        add(fieldMiejsca);
        add(fieldKategoria);
    }
}
