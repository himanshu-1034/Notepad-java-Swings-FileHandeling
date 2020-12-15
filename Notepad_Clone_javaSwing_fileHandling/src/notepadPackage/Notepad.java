package notepadPackage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;

public class Notepad extends JFrame implements ActionListener{

	JScrollPane pane;
	JTextArea area;
	String copiedtext="";
	
	
	public Notepad() {

		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		menubar.add(file);

		JMenuItem newfile = new JMenuItem("New File");
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newfile.addActionListener(this);

		JMenuItem open = new JMenuItem("Open File");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		
		
		JMenuItem savefile = new JMenuItem("Save File");
		savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		savefile.addActionListener(this);
		
		
		JMenuItem printfile = new JMenuItem("Print File");
		printfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		printfile.addActionListener(this);
		
		
		JMenuItem exit = new JMenuItem("Exit.");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		exit.addActionListener(this);
		file.add(newfile);
		file.add(open);
		file.add(savefile);
		file.add(printfile);
		file.add(exit);

		menubar.add(edit);

		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		JMenuItem paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		JMenuItem all = new JMenuItem("Select All");
		all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		all.addActionListener(this);
		

		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(all);

		menubar.add(help);
		JMenuItem about = new JMenuItem("About Me");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		about.addActionListener(this);
		help.add(about);
		
		area = new JTextArea();
		area.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		
		
		pane = new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder());
		this.add(pane,BorderLayout.CENTER);
		
		
		

		setJMenuBar(menubar);
		//setLayout(null);
		// setResizable(false);
		setBounds(0, 0, 1920, 1080);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Notepad().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("New File")) {
			area.setText("");
		}
		else if(e.getActionCommand().equals("Open File")) {
			
			JFileChooser filechooser=new JFileChooser();
			filechooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("All .txt Files", "txt");
			filechooser.addChoosableFileFilter(restrict);
			
			int action = filechooser.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File file = filechooser.getSelectedFile();
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				area.read(reader, null);
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		else if(e.getActionCommand().equals("Save File")) {
			
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			int action = saveas.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File filename = new File(saveas.getSelectedFile()+".txt");
			BufferedWriter outfile= null;
			try {
				
				outfile = new BufferedWriter(new FileWriter(filename));
				area.write(outfile);
				
			}catch(Exception eq) {
				eq.printStackTrace();
			}
			
			
		}
		else if(e.getActionCommand().equals("Print File")) {
			try {
			area.print();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Exit.")) {
			
			System.exit(0);
			
		}
		else if(e.getActionCommand().equals("Copy")) {
			copiedtext = area.getSelectedText();
			
		}
		else if(e.getActionCommand().equals("Paste")) {
			area.insert(copiedtext, area.getCaretPosition());
		}
		else if(e.getActionCommand().equals("Cut")) {
			copiedtext = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
		}
		else if(e.getActionCommand().equals("Select All")) {
			area.selectAll();
		}
		else if(e.getActionCommand().equals("About Me")) {
			
			
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					final URI uri = new URI("https://github.com/himanshu-1034");
					desktop.browse(uri);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}

}
