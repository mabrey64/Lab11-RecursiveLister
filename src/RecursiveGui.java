import javax.swing.*;
import java.awt.*;

public class RecursiveGui extends JFrame
{
    JPanel mainPanel;
    JLabel titleLabel;
    JTextArea textArea;
    JFileChooser fileChooser;
    JScrollPane scrollPane;
    JButton selectFileButton;
    JButton startButton;
    JButton quitButton;

    public RecursiveGui ()
    {
        setTitle("Recursive GUI: A Simple Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titleLabel = new JLabel("Select a directory to display its files and subdirectories:");
        titleLabel.setBounds(175, 10, 500, 30);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        mainPanel.add(titleLabel);

        textArea = new JTextArea();
        textArea.setBounds(10, 50, 760, 400);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 50, 760, 400);
        mainPanel.add(scrollPane);

        selectFileButton = new JButton("Select File");
        selectFileButton.setBounds(100, 470, 150, 30);
//        selectFileButton.addActionListener(e -> {
//            fileChooser = new JFileChooser();
//            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            int returnValue = fileChooser.showOpenDialog(this);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
//                textArea.setText("Selected Directory: " + selectedDirectory);
//                // Here you can add code to display the files and subdirectories
//            }
//        });
        mainPanel.add(selectFileButton);

        startButton = new JButton("Start");
        startButton.setBounds(325, 470, 150, 30);
        startButton.addActionListener(e -> {
            textArea.setText("Starting the recursive process...");
            // Here you can add code to start the recursive process
        });
        mainPanel.add(startButton);

        quitButton = new JButton("Quit");
        quitButton.setBounds(550, 470, 150, 30);
        quitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        mainPanel.add(quitButton);

        add(mainPanel);
    }

    public static void main(String[] args) {
        RecursiveGui gui = new RecursiveGui();
        gui.setVisible(true);
    }
}
