package login;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;


public class Register extends JFrame {

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
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public Register() {
		setSize(300, 180);
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

		JLabel passwordConfirmLabel = new JLabel("Password");
		passwordConfirmLabel.setBounds(10, 70, 80, 25);
		contentPane.add(passwordConfirmLabel);

		JPasswordField passwordConfirmText = new JPasswordField(20);
		passwordConfirmText.setBounds(100, 70, 160, 25);
		contentPane.add(passwordConfirmText);

		JButton registerButton = new JButton("register");
		registerButton.setBounds(100, 110, 80, 25);
		contentPane.add(registerButton);

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String un = userText.getText();
				char[] cpw = passwordText.getPassword();
				String pw = String.valueOf(cpw);
				char[] cpwc = passwordConfirmText.getPassword();
				String pwc = String.valueOf(cpwc);
				if (pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Password is blank!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (pwc.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Password Confirmation is blank!", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (un.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Username is blank!", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							if (new String(pw).equals(pwc)) {
								File files = new File("C:\\addressbook");
								files.mkdir();
								Path path = Paths.get("C:\\addressbook\\" + un + "File.txt");
								final String u = un;
								final String p = pw;
								if (Files.exists(path)) {
									JOptionPane.showMessageDialog(null, "Username Taken!", "Error",
											JOptionPane.ERROR_MESSAGE);
								} else {
									try {
										UserInfo user = new UserInfo();
										user.setUsername(u);
										user.setPassword(p);
										File file = new File("C:\\addressbook\\" + u + "File.txt");
										OutputStream fileOutputStream = new FileOutputStream(file);
										ObjectOutput outputStream = new ObjectOutputStream(fileOutputStream);
										outputStream.writeObject(user);
										
										JOptionPane.showMessageDialog(null,
												"Account Name:  " + u + " Has Been Created.", "Welcome!!",
												JOptionPane.PLAIN_MESSAGE);
										dispose();
										JFrame main = new Main();
										main.setVisible(true);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							} else {
								JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
	}

}
