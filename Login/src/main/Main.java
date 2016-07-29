package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.Register;
import login.UserInfo;

public class Main extends JFrame {

	private JPanel contentPane;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	public Main() {
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel userLabel = new JLabel("User Name");
		userLabel.setBounds(10, 10, 80, 25);
		contentPane.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		contentPane.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		contentPane.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		contentPane.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		contentPane.add(loginButton);

		JButton registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 80, 25);
		contentPane.add(registerButton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String un = userText.getText();
				char[] pwc = passwordText.getPassword();
				String pw = String.valueOf(pwc);
				if (un.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Username is blank!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (pw.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Password is blank!", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						Path path = Paths.get("C:\\addressbook\\" + un + "File.txt");
						if (Files.exists(path)) {
							UserInfo ui = new UserInfo();
							try {
								FileInputStream fileIn = new FileInputStream("C:\\addressbook\\" + un + "File.txt");
								ObjectInputStream in = new ObjectInputStream(fileIn);
								ui = (UserInfo) in.readObject();
								in.close();
								fileIn.close();
							} catch (IOException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String username = ui.getUsername();
							String password = ui.getPassword();
							if (new String(username).equals(un)) {
								if (new String(password).equals(pw)) {
									JOptionPane.showMessageDialog(null, "User: " + un + " has logged In!", "Error",
											JOptionPane.PLAIN_MESSAGE);

								} else {
									JOptionPane.showMessageDialog(null, "Invalid Password!", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Invalid User Name!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null, "Invalid User Name!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}

			}
		});

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JFrame reg = new Register();
				reg.setVisible(true);

			}
		});
	}

}

