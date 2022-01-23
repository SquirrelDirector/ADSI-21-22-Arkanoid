package eus.ehu.adsi.arkanoid.vista; 
 
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.EventQueue; 
 
import javax.swing.BorderFactory; 
import javax.swing.ImageIcon; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.border.EmptyBorder; 
 
import eus.ehu.adsi.arkanoid.controlador.Arkanoid; 
import eus.ehu.adsi.arkanoid.vista.claseObjetos.EtiquetaTitulo; 
 
import java.awt.GridLayout; 
import java.awt.Image; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.util.Observable; 
import java.util.Observer; 
 
import javax.swing.JLabel; 
import javax.swing.SwingConstants; 
 
public class IU_Inicial extends JFrame implements Observer{ 
 
	private InterfazBase contentPane; 
	private JPanel pnlNiveles; 
	private JPanel pnlLogros; 
	private JPanel pnlRanking; 
	private JLabel lblImagenNiveles; 
	private JPanel pnlTextoNiveles; 
	private JLabel lblJugar; 
	private JLabel lblImagenLogros; 
	private JPanel pnlTextoLogros; 
	private JLabel lblLogro; 
	private JLabel lblImagenRanking; 
	private JPanel pnlTextoRanking; 
	private JLabel lblRanking; 
	private ImageIcon imagenJugar; 
	private ImageIcon imagenLogros; 
	private ImageIcon imagenRanking; 
	 
	 
 
	/** 
	 * Launch the application. 
	 */ 
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try { 
					IU_Inicial frame = new IU_Inicial(); 
					frame.setVisible(true); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
		}); 
	} 
 
	/** 
	 * Create the frame. 
	 */ 
	public IU_Inicial() { 
		imagenJugar = new ImageIcon(getClass().getResource("/general/img_jugar.png")); 
		imagenLogros = new ImageIcon(getClass().getResource("/general/img_logros.png")); 
		imagenRanking = new ImageIcon(getClass().getResource("/general/img_ranking.png")); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 940, 570); 
		setLocationRelativeTo(null); 
		contentPane = new InterfazBase("Arkanoid"); 
		setContentPane(contentPane); 
		contentPane.ocultarBotonRegreso(); 
		contentPane.panelIdentidades.add(new PnlIdentidad()); 
		contentPane.panelPrincipal.setLayout(new GridLayout(0, 3, 24, 24)); 
		contentPane.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24)); 
		contentPane.panelPrincipal.add(getPnlNiveles()); 
		contentPane.panelPrincipal.add(getPnlLogros()); 
		contentPane.panelPrincipal.add(getPnlRanking()); 
		contentPane.setIdentificado(Arkanoid.getArkanoid().isIdentificado()); 
		Arkanoid.getArkanoid().addObserver(this); 
		 
	} 
 
	private JPanel getPnlNiveles() { 
		if (pnlNiveles == null) { 
			pnlNiveles = new JPanel(); 
			pnlNiveles.setLayout(new BorderLayout(0, 0)); 
			pnlNiveles.setBackground(new Color(0,0,0)); 
			pnlNiveles.add(getLblImagenNiveles(), BorderLayout.CENTER); 
			pnlNiveles.add(getPnlTextoNiveles(), BorderLayout.SOUTH); 
			pnlNiveles.addMouseListener(new MouseAdapter() { 
				@Override 
				public void mouseClicked(MouseEvent e) { 
					super.mouseClicked(e); 
					new IU_Niveles().setVisible(true); 
					dispose(); 
				} 
			}); 
		} 
		return pnlNiveles; 
	} 
	private JPanel getPnlLogros() { 
		if (pnlLogros == null) { 
			pnlLogros = new JPanel(); 
			pnlLogros.setLayout(new BorderLayout(0, 0)); 
			pnlLogros.setBackground(new Color(0,0,0)); 
			pnlLogros.add(getLblImagenLogros(), BorderLayout.CENTER); 
			pnlLogros.add(getPnlTextoLogros(), BorderLayout.SOUTH); 
			pnlLogros.addMouseListener(new MouseAdapter() { 
				@Override 
				public void mouseClicked(MouseEvent e) { 
					super.mouseClicked(e); 
					Arkanoid ark = Arkanoid.getArkanoid(); 
					if(!ark.isIdentificado()) { 
						IU_Identificarse.getMiIU_Identificarse().mostrarVentana(); 
					}else { 
						new IU_Logros(); 
						dispose(); 
					} 
					 
					 
					 
				} 
			}); 
		} 
		return pnlLogros; 
	} 
	private JPanel getPnlRanking() { 
		if (pnlRanking == null) { 
			pnlRanking = new JPanel(); 
			pnlRanking.setLayout(new BorderLayout(0, 0)); 
			pnlRanking.setBackground(new Color(0,0,0)); 
			pnlRanking.add(getLblImagenRanking(), BorderLayout.CENTER); 
			pnlRanking.add(getPnlTextoRanking(), BorderLayout.SOUTH); 
			pnlRanking.addMouseListener(new MouseAdapter() { 
				@Override 
				public void mouseClicked(MouseEvent e) { 
					super.mouseClicked(e); 
					new Ranking(); 
					Arkanoid.getArkanoid().deleteObserver(IU_Inicial.this); 
					dispose(); 
					 
				} 
			}); 
		} 
		return pnlRanking; 
	} 
	private JLabel getLblImagenNiveles() { 
		if (lblImagenNiveles == null) { 
			lblImagenNiveles = new JLabel(imagenJugar); 
			lblImagenNiveles.setHorizontalAlignment(SwingConstants.CENTER); 
		} 
		return lblImagenNiveles; 
	} 
	private JPanel getPnlTextoNiveles() { 
		if (pnlTextoNiveles == null) { 
			pnlTextoNiveles = new JPanel(); 
			pnlTextoNiveles.setBackground(new Color(0,0,0)); 
			pnlTextoNiveles.add(getLblJugar()); 
		} 
		return pnlTextoNiveles; 
	} 
	private JLabel getLblJugar() { 
		if (lblJugar == null) { 
			lblJugar = new EtiquetaTitulo("Jugar"); 
		} 
		return lblJugar; 
	} 
	private JLabel getLblImagenLogros() { 
		if (lblImagenLogros == null) { 
			lblImagenLogros = new JLabel(imagenLogros); 
			lblImagenLogros.setHorizontalAlignment(SwingConstants.CENTER); 
		} 
		return lblImagenLogros; 
	} 
	private JPanel getPnlTextoLogros() { 
		if (pnlTextoLogros == null) { 
			pnlTextoLogros = new JPanel(); 
			pnlTextoLogros.add(getLblLogro()); 
			pnlTextoLogros.setBackground(new Color(0,0,0)); 
		} 
		return pnlTextoLogros; 
	} 
	private JLabel getLblLogro() { 
		if (lblLogro == null) { 
			lblLogro = new EtiquetaTitulo("Logros"); 
		} 
		return lblLogro; 
	} 
	private JLabel getLblImagenRanking() { 
		if (lblImagenRanking == null) { 
			lblImagenRanking = new JLabel(imagenRanking); 
			lblImagenRanking.setHorizontalAlignment(SwingConstants.CENTER); 
		} 
		return lblImagenRanking; 
	} 
	private JPanel getPnlTextoRanking() { 
		if (pnlTextoRanking == null) { 
			pnlTextoRanking = new JPanel(); 
			pnlTextoRanking.setBackground(new Color(0,0,0)); 
			pnlTextoRanking.add(getLblRanking()); 
		} 
		return pnlTextoRanking; 
	} 
	private JLabel getLblRanking() { 
		if (lblRanking == null) { 
			lblRanking = new EtiquetaTitulo("Rankings"); 
		} 
		return lblRanking; 
	} 
 
	@Override 
	public void update(Observable arg0, Object arg1) { 
		if (arg1 instanceof Boolean) 
			contentPane.setIdentificado((boolean) arg1); 
	} 
} 
