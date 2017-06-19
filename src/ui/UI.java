package ui;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
/**
 *
 * @author Joohong Ahn, Willie, Gurkiran
 */

class ImageLabel extends javax.swing.JLabel{
    private Image _myimage;
    private int _imageW, _imageH;

    public ImageLabel(String text){
        super(text);
    }

	public void setIcon(BufferedImage img)
	{
		_imageW = img.getWidth();
		_imageH = img.getHeight();
		setIcon(new ImageIcon(img));
	}

	public Dimension getImageSize()
	{
		return new Dimension(_imageW, _imageH);
	}

	public Dimension getAdjustedImageSize()
	{
		int w, h;
	    if (this.getWidth() >= this.getHeight()) 
		{
			h = this.getHeight();
			w = (int) (((1.0 * _imageW) / _imageH) * h);
		}
		else
		{
			w = this.getWidth();
			h = (int) (((1.0 * _imageH) / _imageW) * w);
		}
		return new Dimension(w, h);
	}

    public void setIcon(javax.swing.Icon icon) {
        super.setIcon(icon);
        if (icon instanceof ImageIcon)
        {
            _myimage = ((ImageIcon) icon).getImage();
        }
    }

    @Override
    public void paint(Graphics g){
		Dimension s = getAdjustedImageSize();
		int w = s.width,
		    h = s.height;

        g.drawImage(_myimage, 0, 0, w, h, null);
    }
}

class MarkerLabel extends javax.swing.JLabel{
	public Point source;
	public ArrayList<Point> sinks;

    public MarkerLabel(String text){
        super(text);
        sinks = new ArrayList<>();
    }

	public void setSource(int x, int y)
	{
		source = new Point(x, y);
		repaint();
	}

	public void addSink(int x, int y)
	{
		sinks.add(new Point(x, y));
		repaint();
	}

	public void removeSource()
	{
		source = null;
		repaint();
	}

	public void removeSink(int x, int y)
	{
		sinks.remove(new Point(x, y));
		repaint();
	}

	public Point removeClosestSink(int x, int y)
	{
		if (sinks.isEmpty())
		{
			return null;
		}
		double dist = 1000,
		       _dist;
		Point sink = null;
		for (Point p: sinks)
		{
			_dist = p.distance(x, y);
			if (_dist < dist)
			{
				dist = _dist;
				sink = p;
			}
		}
		sinks.remove(sink);
		repaint();
		return sink;
	}

    @Override
    public void paint(Graphics g){
	    if (source != null)
		{
			g.setColor(Color.red);
			g.fillOval(source.x - 5, source.y - 5, 10, 10);
		}
        g.setColor(Color.blue);
		for (Point p: sinks)
		{
			g.fillOval(p.x - 5, p.y - 5, 10, 10);
		}
    }
}

public class UI extends javax.swing.JFrame  {
	boolean editSource = true;
	boolean image_segmentation = false;
	private Point source;
	private ArrayList<Point> sinks = new ArrayList<>();
	UIListener listener;


	public UI() {

		initComponents();
	}

	public UI(UIListener l) {
		this();
		listener = l;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {
		int h = 600,
		    w = 600;
		setSize(new Dimension(w, h));
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jPanel2 = new javax.swing.JPanel();
		jPanel2.setPreferredSize(new Dimension(w, h));

		io = new javax.swing.JLayeredPane();
		io.setSize(new Dimension(w, h));
		io.setPreferredSize(new Dimension(w, h));
		input = new ImageLabel("");
		input.setSize(new Dimension(w, h));
		input.setPreferredSize(new Dimension(w, h));
		inputMarker = new MarkerLabel("");
		inputMarker.setSize(new Dimension(w, h));
		inputMarker.setPreferredSize(new Dimension(w, h));
		output = new ImageLabel("");
		output.setSize(new Dimension(w, h));
		output.setPreferredSize(new Dimension(w, h));
		io.add(output, 0);
		io.add(inputMarker, 1);
		io.add(input, 2);
		
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		jMenu1.setText("jMenu1");

		jMenu2.setText("jMenu2");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		input.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				imageMouseClicked(evt);
			}
		});

		jButton1.setText("select image");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("edit source");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("edit sink");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setText("run segmentation");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		// jLabel1.setText("Tap on the image to select source and sink. First tap will select the source and second tap will select sink.");
		jLabel1.setText("Editing source. Click to add / change source.");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(io, io.getPreferredSize().width, io.getPreferredSize().width, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(jButton1)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jButton2)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(jButton3)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(jButton4)
							.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
					.addContainerGap())
				);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(10, 10, 10)
					.addComponent(jLabel1)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(io, io.getPreferredSize().height, io.getPreferredSize().height, Short.MAX_VALUE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jButton1)
						.addComponent(jButton2)
						.addComponent(jButton3)
						.addComponent(jButton4))
					.addContainerGap(18, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		pack();
	}// </editor-fold>                        

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				BufferedImage i = ImageIO.read(file);
				input.setIcon(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}                                        

	private void imageMouseClicked(java.awt.event.MouseEvent evt) {                                   
		int x=evt.getX();
		int y=evt.getY();
		Dimension s = input.getAdjustedImageSize();
		if (x > s.width || y > s.height)
		{
			return;
		}
		if(editSource){
			source = new Point(x, y);
			inputMarker.setSource(x, y);
			// JOptionPane.showOptionDialog(null, "source selected "+x+","+y,"Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);   
		}
		else
		{
			if (SwingUtilities.isLeftMouseButton(evt))
			{
				sinks.add(new Point(x, y));
				inputMarker.addSink(x, y);
				// JOptionPane.showOptionDialog(null, "sink selected "+x+","+y,"Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);   
			}
			else
			{
				Point sink = inputMarker.removeClosestSink(x, y);
				sinks.remove(sink);
				// JOptionPane.showOptionDialog(null, "sink removed "+x+","+y,"Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);   
			}
		}

	}                                  

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		// source=0;
		editSource = true;
		jLabel1.setText("Editing source. Click to add / change source.");
		// JOptionPane.showOptionDialog(null, "source removed "+xsrc+","+ysrc,"Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);   

	}                                        

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		editSource = false;
		jLabel1.setText("Editing sinks. Left click to add a source, and right click to remove one.");
		// JOptionPane.showOptionDialog(null, "sink removed "+xsnk+","+ysnk,"Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);   

	}                                        

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		JOptionPane.showOptionDialog(null, "image segmentation started","Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);   
		image_segmentation = true;
		if (listener != null)
		{
			listener.UIEvent();
		}
		System.out.println(source);
		System.out.println(getSource());
		for (Point p: getSinks())
		{
			System.out.println(p);
		}
	}                                        

	public Point getSource()
	{
		Dimension trueSize = input.getImageSize();
		Dimension adjSize = input.getAdjustedImageSize();
		double rw = trueSize.width / (1.0 * adjSize.width);
		double rh = trueSize.height / (1.0 * adjSize.height);
		return new Point((int) (source.x * rw), (int) (source.y * rh));
	}
	
	public Point[] getSinks()
	{
		Point[] sinksCopy = new Point[sinks.size()];
		Dimension trueSize = input.getImageSize();
		Dimension adjSize = input.getAdjustedImageSize();
		double rw = trueSize.width / (1.0 * adjSize.width);
		double rh = trueSize.height / (1.0 * adjSize.height);
		Point p;
		for (int i = 0; i < sinksCopy.length; i++)
		{
			p = sinks.get(i);
			sinksCopy[i] = new Point((int) (p.x * rw), (int) (p.y * rh));
		}
		return sinksCopy;
	}

	/**
	 * @param args the command line arguments
	 */
	//RENAME RUN? 
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify                     
	private ImageLabel input;
	private MarkerLabel inputMarker;
	private ImageLabel output;
	private javax.swing.JLayeredPane io;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JPanel jPanel2;
	// End of variables declaration                   
}



