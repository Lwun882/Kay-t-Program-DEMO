import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Driver {
    private static JFrame mainFrame;
    private static JFrame userFrame;
    private static JTextField kullaniciAdiField;
    private static JTextField emailField;
    private static JTextField dogumGünüField;
    private static JTextField phoneField;
    private static JTextField isimField;
    private static JTextField soyadField;
    public static void main(String[] args) {
        anaPanel();
    }

    private static void anaPanel() {
        mainFrame = new JFrame("Kayýt Programý");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new FlowLayout(500,70,100));

        JButton giris = new JButton("Giriþ Yap");
        JButton kayit = new JButton("Kayýt Ol");
        
        giris.addActionListener(e -> bilgiGöster(false));
        kayit.addActionListener(e -> bilgiGöster(true));

        mainFrame.add(giris);
        mainFrame.add(kayit);
       
        mainFrame.setVisible(true);
    }

    private static void bilgiGöster(boolean isRegister) {
        userFrame = new JFrame(isRegister ? "Register User" : "Login User");
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setSize(400, 300);
        userFrame.setLayout(new GridLayout(7, 2));

        JLabel kullaniciAdiLabel = new JLabel("Kullanýcý adý:");
        kullaniciAdiField = new JTextField();
        userFrame.add(kullaniciAdiLabel);
        userFrame.add(kullaniciAdiField);

        if (isRegister) {
            JLabel emailLabel = new JLabel("Email:");
            emailField = new JTextField();
            JLabel dogumGünüLabel = new JLabel("Doðum Günü:");
            dogumGünüField = new JTextField();
            JLabel phoneLabel = new JLabel("Telefon:");
            phoneField = new JTextField();
            JLabel isimLabel = new JLabel("Ýsim:");
            isimField = new JTextField();
            JLabel soyadLabel = new JLabel("Soyad:");
            soyadField = new JTextField();

            userFrame.add(emailLabel);
            userFrame.add(emailField);
            userFrame.add(dogumGünüLabel);
            userFrame.add(dogumGünüField);
            userFrame.add(phoneLabel);
            userFrame.add(phoneField);
            userFrame.add(isimLabel);
            userFrame.add(isimField);
            userFrame.add(soyadLabel);
            userFrame.add(soyadField);
        }

        JButton gecisbtn = new JButton("Tamamlandý");
        gecisbtn.addActionListener(e -> {
            if (isRegister) {
                kayit();
            } else {
                kullangir();
            }
        });

        userFrame.add(gecisbtn);
        userFrame.setVisible(true);
    }

    private static void kayit() {
        String kullaniciAdi = kullaniciAdiField.getText();
        String email = emailField.getText();
        String dogumgunu = dogumGünüField.getText();
        String phone = phoneField.getText();
        String isim = isimField.getText();
        String soyad = soyadField.getText();

        try {
            File file = new File(System.getProperty("user.home") + "/Desktop/" + kullaniciAdi + ".txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Kullanýcý Adý: " + kullaniciAdi + "\n");
            writer.write("Ýsim: " + isim + "\n");
            writer.write("Soyad: " + soyad + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Doðum günü: " + dogumgunu + "\n");
            writer.write("Telefon numarasý: " + phone + "\n");
            writer.close();
            JOptionPane.showMessageDialog(userFrame, "Kullanýcý bilgileri kayýt edildi.");
            userFrame.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(userFrame, "Hatalý Giriþ");
        }
    }

    private static void kullangir() {
        String kullaniciAdi = kullaniciAdiField.getText();
        try {
            File file = new File(System.getProperty("user.home") + "/Desktop/" + kullaniciAdi + ".txt");
            Scanner scanner = new Scanner(file);
            StringBuilder kullaniciBilgileri = new StringBuilder();
            while (scanner.hasNextLine()) {
                kullaniciBilgileri.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            JOptionPane.showMessageDialog(userFrame, kullaniciBilgileri.toString(), "Kullanýcý Bilgileri", JOptionPane.INFORMATION_MESSAGE);
            userFrame.dispose();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(userFrame, "Kullanýcý Bulunamadý.");
        }
    }
}