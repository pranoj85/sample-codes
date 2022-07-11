// Java program to move a mouse from the initial
// location to a specified location
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;
import java.sql.Timestamp;

class robomouse extends Frame implements ActionListener {
	// Frame
	JFrame f;

	// textField
	TextField x, y;
	JLabel l, h;

	// #modifications
	TextField xl, yl;  
	TextField ctime, timediff;
	
	LocalDateTime stime;

	// default constructor
	robomouse()
	{

		// create a frame
		f = new JFrame("robomouse");

		// set the frame to close on exit
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create textfield
		x = new TextField(7);
		x.setFont(new Font("Serif",Font.BOLD,30));
		
		y = new TextField(7);
		y.setFont(new Font("Serif",Font.BOLD,30));

		// create a button
		Button b = new Button("Start");
		b.setFont(new Font("Serif",Font.BOLD,30));
		b.setBackground(Color.DARK_GRAY);
		b.setForeground(Color.WHITE);

		// add actionListener
		b.addActionListener(this);

		// create a panel
		Panel p = new Panel();

		l = new JLabel("x");
		h = new JLabel("y");


		// add items to panel
		p.add(l);
		p.add(x);
		p.add(h);
		p.add(y);
		p.add(b);

		//f.add(p);

		// #modifications
		
		xl=new TextField(3);  
        xl.setBounds(50,100, 100,30);  
        yl=new TextField(3);  
		yl.setBounds(50,150, 100,30);

		// create a panel
		
		ctime=new TextField(40);
		//ctime.setBounds(50,200,100,30);
		ctime.setFont(new Font("Serif",Font.BOLD,25));
		ctime.setBackground(Color.BLUE);
		ctime.setForeground(Color.WHITE);
		ctime.setText("Let's");

		timediff=new TextField(40);
		timediff.setFont(new Font("Serif",Font.BOLD,25));
		timediff.setBackground(Color.MAGENTA);
		timediff.setForeground(Color.WHITE);
		timediff.setText("Move.");

		xl.setText("0");
		xl.setBackground(Color.GREEN);
		xl.setFont(new Font("Serif",Font.BOLD,30));
		
		yl.setText("0");
		yl.setBackground(Color.GREEN);
		yl.setFont(new Font("Serif",Font.BOLD,30));
		
		p.add(xl);
		p.add(yl);
		p.add(ctime);
		p.add(timediff);
		
		f.add(p);
		// setsize of frame
		f.setSize(600, 200);
		f.show();

	}

	// main function
	public static void main(String args[])
	{
		// object of class
		robomouse rm = new robomouse();
		System.out.println("Press Ctrl+C to quit");
	}

	// if button is pressed
	public void actionPerformed(ActionEvent e)
	{
		try {

			stime=LocalDateTime.now();

			Robot r = new Robot();
			int xi1, yi1, xi, yi;
			LocalDateTime now=LocalDateTime.now();
		
			// get initial location
			Point p = MouseInfo.getPointerInfo().getLocation();
			xi = p.x;
			yi = p.y;

			// get x and y points
			xi1 = Integer.parseInt(x.getText());
			yi1 = Integer.parseInt(y.getText());
			int i = xi, j = yi;

			// slowly move the mouse to defined location
			while (i != xi1 || j != yi1 ) {
				
				now = LocalDateTime.now();
				String timeStr = "";
				DateTimeFormatter formatTimeNow = DateTimeFormatter.ofPattern("HH:mm:ss a");
				DateTimeFormatter formatDateToday=DateTimeFormatter.ofPattern("EEEE MMM dd ");
		
				timeStr = now.format(formatTimeNow);
				timeStr = "CurrentTime: " + timeStr + " " + now.format(formatDateToday);
				ctime.setText(timeStr);

				long diff = Timestamp.valueOf(now).getTime() - Timestamp.valueOf(stime).getTime();
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				String awayTime = "Away Time: " + diffDays + " Days " + diffHours + " Hours " + diffMinutes + " Minutes " + diffSeconds + " Seconds";
				timediff.setText(awayTime);

				while (i != xi1 || j != yi1) {
					// move the mouse to the other point
					r.mouseMove(i, j);

					if (i < xi1)
						i++;
					if (j < yi1)
						j++;

					if (i > xi1)
						i--;
					if (j > yi1)
						j--;

					xl.setText(""+i);
					xl.setBackground(Color.GREEN);
		
					yl.setText(""+j);
					yl.setBackground(Color.GREEN);				
					
					// wait
					Thread.sleep(30);

				}
				
				// reset location
				i = xi;
				j = yi;
				xl.setText(""+i);
				xl.setBackground(Color.BLUE);
				yl.setText(""+j);
				yl.setBackground(Color.BLUE);
				// wait
				Thread.sleep(1000);
				
			}
			
		}
		catch (Exception evt) {
			System.err.println(evt.getMessage());
		}
	}
}
