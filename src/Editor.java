import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.AttributedCharacterIterator.Attribute;

public class Editor extends JFrame {
	private JLabel label1, label2,lbcor;
	private JButton btGravar, btAbrir, btLimpar,btcor;
	private JTextField tfTexto;
	private TextArea taTexto;
	private FileDialog fdAbrir, fdSalvar;
	private String nome_do_arquivo;

	public static void main(String args[]) {
		JFrame frame = new Editor();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,600,600);
		frame.setVisible(true);
	}

	public Editor() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {
		
		setTitle("Salvando Arquivos");
		setLayout(null);
		setBounds(250, 50, 500, 300);
		setResizable(false);
		btcor = new JButton ("Mudar Cor");
		btcor.setBounds(10,210,100,25);
		label1 = new JLabel("Digite o texto aqui: ");
		label1.setBounds(5, 95, 200, 20);
		label2 = new JLabel("Status: ");
		label2.setBounds(5, 500, 200, 20);
		btGravar = new JButton("Gravar");
		btGravar.setBounds(200, 450, 100, 25);
		btAbrir = new JButton("Abrir");
		btAbrir.setBounds(80, 450, 100, 25);
		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(320, 450, 100, 25);
		tfTexto = new JTextField();
		tfTexto.setBounds(50, 240, 430, 20);
		tfTexto.setEditable(false);
		taTexto = new TextArea();
		taTexto.setBounds(45, 125, 480, 300);
		fdAbrir = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSalvar = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		add(label1);
		add(label2);
		add(tfTexto);
		add(taTexto);
		add(btGravar);
		add(btAbrir);
		add(btLimpar);
		//add(btcor);
	}

	public void definirEventos() {
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setText("");
				tfTexto.setText("");
			}
		});
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdSalvar.setVisible(true);
					if (fdSalvar.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdSalvar.getDirectory()
							+ fdSalvar.getFile();
					FileWriter out = new FileWriter(nome_do_arquivo);
					out.write(taTexto.getText());
					out.close();
					tfTexto.setText("Arquivo gravado com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao gravar no arquivo"
							+ erro.toString());
				}

			}
		});
		btcor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				taTexto.setForeground(Color.RED);
				taTexto.setFont(new Font("Serif",1, 19));
		     	
			
			}
			
		});
		btAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdAbrir.setVisible(true);
					if (fdAbrir.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdAbrir.getDirectory()
							+ fdAbrir.getFile();
					FileReader in = new FileReader(nome_do_arquivo);
					String s = "";
					int i = in.read();
					while (i != -1) {
						s = s + (char) i;
						i = in.read();
					}
					taTexto.setText(s);
					in.close();
					tfTexto.setText("Arquivo aberto com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao abrir no arquivo"
							+ erro.toString());
				}

			}
		});
	}

}