import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class App {
    public static void main(String[] args) throws Exception {
        new MainFrame();
    }
}

class MainFrame extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Container c;
    private JTable MainTable;
    private JLabel title;

    DefaultTableModel model = new DefaultTableModel();

    // Nama
    private JLabel label_name;
    private JTextField filed_name;

    // Panjang
    private JLabel label_length;
    private JTextField filed_length;

    // Lebar
    private JLabel label_width;
    private JTextField filed_width;

    // Tinggi
    private JLabel label_height;
    private JTextField filed_height;

    // Button
    private JButton sub;
    private JButton reset;

    Object[] DefaultData = { "Sample", "3.5", "3", "4", "8.8" };
    String[] columnNames = { "Ruangan", "Panjang", "Lebar", "Tinggi", "Kebutuhan Cat (Liter)" };

    public MainFrame() {
        setTitle("Hitung Kebutuhan Cat");
        setBounds(300, 90, 700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        JLabel kelompok = new JLabel(
                "<html>Kevin Adam - 41818310014, Ervan Adi Wijaya - 41818310026,<br> Nina Marlina - 41819310017, Tri Sutrisna NW - 41819310006 </html>",
                SwingConstants.CENTER);
        kelompok.setFont(new Font("Arial", Font.PLAIN, 13));
        kelompok.setSize(500, 100);
        kelompok.setLocation(100, 480);
        c.add(kelompok);

        title = new JLabel("Hitung kebutuhan cat");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(800, 30);
        title.setLocation(100, 30);
        c.add(title);

        label_name = new JLabel("Nama Ruangan");
        label_name.setFont(new Font("Arial", Font.PLAIN, 15));
        label_name.setSize(200, 20);
        label_name.setLocation(100, 100);
        c.add(label_name);

        filed_name = new JTextField();
        filed_name.setFont(new Font("Arial", Font.PLAIN, 15));
        filed_name.setSize(190, 20);
        filed_name.setLocation(250, 100);
        c.add(filed_name);

        label_length = new JLabel("Panjang Ruangan (m)");
        label_length.setFont(new Font("Arial", Font.PLAIN, 15));
        label_length.setSize(200, 20);
        label_length.setLocation(100, 130);
        c.add(label_length);

        filed_length = new JTextField();
        filed_length.setFont(new Font("Arial", Font.PLAIN, 15));
        filed_length.setSize(190, 20);
        filed_length.setLocation(250, 130);
        c.add(filed_length);

        label_width = new JLabel("Lebar Ruangan (m)");
        label_width.setFont(new Font("Arial", Font.PLAIN, 15));
        label_width.setSize(200, 20);
        label_width.setLocation(100, 160);
        c.add(label_width);

        filed_width = new JTextField();
        filed_width.setFont(new Font("Arial", Font.PLAIN, 15));
        filed_width.setSize(190, 20);
        filed_width.setLocation(250, 160);
        c.add(filed_width);

        label_height = new JLabel("Tinggi Ruangan (m)");
        label_height.setFont(new Font("Arial", Font.PLAIN, 15));
        label_height.setSize(200, 20);
        label_height.setLocation(100, 190);
        c.add(label_height);

        filed_height = new JTextField();
        filed_height.setFont(new Font("Arial", Font.PLAIN, 15));
        filed_height.setSize(190, 20);
        filed_height.setLocation(250, 190);
        c.add(filed_height);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 13));
        sub.setSize(150, 30);
        sub.setLocation(95, 220);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 13));
        reset.setSize(150, 30);
        reset.setLocation(250, 220);
        reset.addActionListener(this);
        c.add(reset);

        MainTable = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        model.addRow(DefaultData);
        MainTable.setRowHeight(20);

        MainTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        MainTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        MainTable.getColumnModel().getColumn(2).setPreferredWidth(10);
        MainTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        MainTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        MainTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scrollPane = new JScrollPane(MainTable);
        scrollPane.setLocation(0, 260);
        scrollPane.setSize(700, 200);
        c.add(scrollPane);

        // JScrollPane sp = new JScrollPane(MainTable);
        // c.add(sp);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String val_name, val_width, val_length, val_height;
            String error = null;
            val_name = filed_name.getText();
            val_length = filed_length.getText();
            val_width = filed_width.getText();
            val_height = filed_height.getText();

            // validate filed
            if (val_name.isEmpty()) {
                error = "Nama ruangan tidak boleh koosng.";
            } else if (val_length.isEmpty()) {
                error = "Panjang ruangan tidak boleh kosong.";
            } else if (val_width.isEmpty()) {
                error = "Lebar ruangan tidak boleh kosong.";
            } else if (val_height.isEmpty()) {
                error = "Tinggi ruangan tidak boleh kosong.";
            }

            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Input Error", JOptionPane.WARNING_MESSAGE);
            } else {
                double total = paint_calculate(Double.parseDouble(val_length), Double.parseDouble(val_width),
                        Double.parseDouble(val_height));

                JOptionPane.showMessageDialog(
                        this, "Cat yang dibutuhkan untuk " + val_name + " adalah sebanyak "
                                + String.format("%.1f", total) + " liter.",
                        "Hasil kebutuhan cat", JOptionPane.PLAIN_MESSAGE);

                model.addRow(
                        new Object[] { val_name, val_length, val_width, val_height, String.format("%.1f", total) });
                emptyFiled();
            }

        } else {
            emptyFiled();
        }
    }

    public void emptyFiled() {
        String def = "";
        filed_name.setText(def);
        filed_height.setText(def);
        filed_width.setText(def);
        filed_length.setText(def);
    }

    public double paint_calculate(double length, double width, double height) {
        double result, wall_num, wall_calc, plafon_calc, surface_area, scattering, layers;

        // formula
        // https://www.pratamabangunan.com/id/tips-and-trick/panduan-penghitungan-kebutuhan-cat

        // Hitung luas permukaan bidang dinding atau tembok yang akan dicat.
        // Tinggi x Lebar x Jumlah dinding | contoh Dinding: 4m x 5m x 4 dinding = 80m2
        wall_num = 4;
        wall_calc = length * width * wall_num;

        // Hitung luas bidang permukaan plafon.
        // Panjang x Lebar | contoh Plafon: 3m x 4m = 12m2
        plafon_calc = length * width;

        // jumlahkan total luas bidang tersebut.
        // Total luas dinding + Total luas plafon | contoh 80m2 + 12m2 = 92m2
        surface_area = wall_calc + plafon_calc;

        // Satu galon memiliki daya sebar teoritis 12m2 dengan 2 lapis pengecatan,
        // maka jumlah yang dibutuhkan secara teoritis sebagai berikut.
        // (Total luas bidang : Daya sebar teoritis) x Jumlah lapisan pengecatan |
        // contoh (91m2 : 12) x 2 = 15,2 liter
        scattering = 12;
        layers = 2;
        result = (surface_area / scattering) * layers; // Liter

        return result;
    }
}
