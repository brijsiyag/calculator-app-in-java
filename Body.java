import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Body implements ActionListener {
    JLabel label = new JLabel("<html><span style='font-size:20px'>" + "Calculator" + "</span></html>",
            SwingConstants.CENTER);
    JFrame frame = new JFrame();
    JTextField screen = new JTextField("Welcome!!");
    JButton[] numberButtons = new JButton[10];
    JButton addBtn = new JButton("+");
    JButton subBtn = new JButton("-");
    JButton mulBtn = new JButton("*");
    JButton divBtn = new JButton("/");
    JButton eqlBtn = new JButton("=");
    JButton dotBtn = new JButton(".");
    JButton clearBtn = new JButton("C");
    JButton plusMinusBtn = new JButton("+/-");
    JButton bracketOpen = new JButton("(");
    JButton bracketClose = new JButton(")");
    JPanel panel = new JPanel();
    JLabel copyrightLabel = new JLabel("©birju", SwingConstants.CENTER);

    Body() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setMaximumSize(new Dimension(400, 350));
        frame.setMinimumSize(new Dimension(400, 350));
        frame.setLayout(null);
        label.setBounds(0, 10, 400, 30);
        copyrightLabel.setBounds(0, 290, 400, 30);
        // label.setFont(new Font("Serif", Font.PLAIN, 14));
        panel.setBounds(10, 110, 380, 180);
        screen.setBounds(30, 50, 340, 50);
        screen.setEditable(false);
        screen.setBorder(
                BorderFactory.createCompoundBorder(screen.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setSize(100, 100);
        }
        panel.add(clearBtn);
        panel.add(bracketOpen);
        panel.add(bracketClose);
        panel.add(divBtn);

        panel.add(numberButtons[9]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[7]);
        panel.add(mulBtn);

        panel.add(numberButtons[6]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[4]);
        panel.add(subBtn);

        panel.add(numberButtons[3]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[1]);
        panel.add(addBtn);

        panel.add(numberButtons[0]);
        panel.add(dotBtn);
        panel.add(plusMinusBtn);
        panel.add(eqlBtn);
        frame.add(label);
        frame.add(screen);
        frame.add(panel);
        frame.add(copyrightLabel);

        divBtn.setFocusable(false);
        mulBtn.setFocusable(false);
        subBtn.setFocusable(false);
        addBtn.setFocusable(false);
        dotBtn.setFocusable(false);
        plusMinusBtn.setFocusable(false);
        clearBtn.setFocusable(false);
        eqlBtn.setFocusable(false);
        bracketClose.setFocusable(false);
        bracketOpen.setFocusable(false);
        clearBtn.setFocusable(false);
        divBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        subBtn.addActionListener(this);
        dotBtn.addActionListener(this);
        plusMinusBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        eqlBtn.addActionListener(this);
        addBtn.addActionListener(this);
        bracketOpen.addActionListener(this);
        bracketClose.addActionListener(this);

        frame.setVisible(true);
        setTimeout(() -> screen.setText(""), 2000);
    }

    public void calc() throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        screen.setText(String.valueOf(engine.eval(screen.getText())));
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                screen.setText(screen.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == addBtn) {
            screen.setText(screen.getText().concat("+"));
        }
        if (e.getSource() == subBtn) {
            screen.setText(screen.getText().concat("-"));
        }
        if (e.getSource() == mulBtn) {
            screen.setText(screen.getText().concat("*"));
        }
        if (e.getSource() == divBtn) {
            screen.setText(screen.getText().concat("/"));
        }
        if (e.getSource() == dotBtn) {
            screen.setText(screen.getText().concat("."));
        }
        if (e.getSource() == clearBtn) {
            screen.setText("");
        }
        if (e.getSource() == bracketOpen) {
            screen.setText(screen.getText().concat("("));
        }
        if (e.getSource() == bracketClose) {
            screen.setText(screen.getText().concat(")"));
        }
        if (e.getSource() == plusMinusBtn) {
            String s = screen.getText();
            char val = '-';
            if (s.charAt(0) == '-') {
                val = '+';
                s = Character.toString(val).concat(s.substring(1, s.length()));
            } else if (s.charAt(0) == '+') {
                val = '-';
                s = Character.toString(val).concat(s.substring(1, s.length()));
            } else {
                s = Character.toString(val).concat(s);
            }
            screen.setText(s);
        }
        if (e.getSource() == eqlBtn) {
            try {
                calc();
            } catch (Exception a) {
                screen.setText("Enter a valid expression!!");
                setTimeout(() -> screen.setText(""), 2000);
            }
        }
    }
}
