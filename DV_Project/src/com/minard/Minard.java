package com.minard;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.data.TableRow;
import controlP5.*;

//To read the city data
class City{
	public float longc = 0;
	public float latc = 0;  
	public String city = "";
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return longc+" "+latc+" "+city;
	}
}


//To read temperature,days,month and day
class Temperature{
	public float longt = 0;
	public int temp = 0;
	public int days = 0;
	public String mon = "";
	public int day = 0;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return longt+" "+temp+" "+days+" "+mon+" "+day;
	}
}


//To read survivors,headed direction and army division
class Survivors{
	public float longp = 0;
	public float latp = 0;
	public int surv = 0;
	public String dir = "";
	public int div = 0;	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return longp+" "+latp+" "+surv+" "+dir+" "+div;
	}
}


//Main Class-Minard
public class Minard extends PApplet {
	
	//Global Variables
	
	ArrayList<City> city = new ArrayList<City>();
	ArrayList<Temperature> temperature = new ArrayList<Temperature>();
	ArrayList<Survivors> survivors = new ArrayList<Survivors>();
	ControlP5 cp5;
	boolean toggleValue,toggleValueDiv1,toggleValueDiv2,toggleValueDiv3;
	Table table;
	String [] pallets = {"0xf1935c","0xb21f66","0xfd5e53","0x2c7873","0xdb3056","0xff896b"};
	
	
	
	//-----------------------Main Method --------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("com.minard.Minard");
	}
	
	@Override
	public void settings() {
		size(1200,600);
	}
	
	@Override
	public void setup() {
		smooth();
		textAlign(RIGHT,CENTER);
		PFont font;
		font = createFont("Serif.bolditalic", 12);
		textFont(font);
		//System.out.println(System.getProperty("user.dir"));
		getData();
		
		btn();
		
	}
	
	
	@Override
	public void draw() {
		background(0xe5e4cc);//(0xdfddc7);//(0xfaebcd);
		//text(mouseX+" "+mouseY,100,100);
		// TODO Auto-generated method stub
		/*
		for(int i =0; i<15;i++) {
			ellipse(normalizeX(23+i), 200+i*10, 10+i, 10+i);
			line(normalizeX(23+i),200+i*10,normalizeX(23+i+1),200+i*10);
		}
		*/

		//Heading of the chart
		{	
			pushMatrix();
			translate(width/2,20);
			//PFont font;
			//font = createFont("Calibri", 26);
			//textFont(font);
			textSize(25);
			fill(21,25,101);
			stroke(0);
			String header = "CHARLES JOSEPH MINARD\'S MAP OF NAPOLEON\'S RUSSIA CAMPAIGN ";
			text(header,width/3+30,10);
			popMatrix();
			//Default style
			textSize(12);
			//font = createFont("Serif.bolditalic", 12);
			//textFont(font);
		}
		
		
		//Button Labels
		{
			fill(0xe5e4cc);
			noStroke();
			strokeWeight(0);
			rect(20,150,100 ,300);
			lblText();
			
		}
		
		//Labels
		{
			fill(0xe5e4cc);
			strokeWeight(0);
			rect(20,480,1160 ,100);
			displayNotes();
		}
		
		
		
		//Draw temperature chart
		  for (int i = 0; i<4; i++) 
		  {
		    fill(0); 
		    strokeWeight(2);
		    stroke(255);
		    line(width-1000, i * 30 + height/2+60, width-50,i * 30 + height/2+60);
		    strokeWeight(2);
		    stroke(255);
		    textAlign(RIGHT);
		    text(i*10, width-50+20, i * 30 + height/2+60);
		  }
		  
		  //Text for temperature
		  pushMatrix();
		  translate(width-1020,(float) 2.5 * 30 + height/2-10);
		  rotate(-HALF_PI);
		  text("TEMPERATURE",0,0 );
		  popMatrix();
		  text("LONGITUDE", (float) (width/2)+50, 5 * 30 + height/2+20);
		  text("Size of the Bubbles = Days Spent", (float) (width/2)+400, 5 * 30 + height/2+20);
		  
		  
		  //Draw Graph points
		  int j=0;
		  for(int i=0;i<temperature.size()-1;i++) {
			  if(j==0) {
				  {
					  fill(200,25,18);  
					  strokeWeight(3);    
					  stroke(200,25,18);
					  float tempx = temperature.get(8).longt;
					  float tempy = -temperature.get(8).temp*3+height/2 + 60;
					  ellipse(normalizeX(tempx),tempy,temperature.get(8).days*2,temperature.get(8).days*2);
					  fill(94,6,6);
					  text(temperature.get(8).temp+"° C",normalizeX(tempx),tempy-10);
				  }j++;
			  } //Graph second part
			  {
				  Temperature temp1 = temperature.get(i);
				  Temperature temp2 = temperature.get(i+1);
				  //System.out.println(temperature.size());
				  float x1 = temp1.longt;
				  //System.out.println(temp1.longt);
				  float x2 = temp2.longt;
				  float y1 = (float) (-temp1.temp*3+height/2+60);
				  float y2 = (float) (-temp2.temp*3+height/2+60);
				  // Draw Point
				  fill(200,25,18,200); 
				  strokeWeight(3);    
				  stroke(200,25,18,1);
				  ellipse(normalizeX(x1),y1, temp1.days*2, temp1.days*2);
				  
				  fill(94,6,6);
				  //stroke(0,47,53);
				  text(temperature.get(i).temp+"° C",normalizeX(x1),y1-10);
				  //Draw line
				  fill(91,86,86); 
				  strokeWeight(2);
				  stroke(91,86,86);
				  line(normalizeX(x1),y1,normalizeX(x2), y2);
				  
			  }
			       
		  }

		  //Display Survivor chart
		
		  j=0;
		  for(int i= survivors.size()-2;i>=1;i--) {
			  
			  if(j==0) {
				  {
					  
					  fill(0);
					  stroke(0);
					  stroke(216,201,98);
					  Survivors stemp = survivors.get(0);
					  Survivors stemp1 = survivors.get(1);
					  if(stemp.dir.contains("R")) stroke(235,130,66);
					  //ellipse(normalizeX(stemp1.longp), normalizeY(stemp1.latp),5,5);
					  //fill(216,201,98);
					  
					  //Toggle button
						if(toggleValue) {
							btnaction(stemp.div, stemp.dir);
						}
					  
					  float strokeW = norm(stemp1.surv, 4000, 340000) * 60;
					  strokeWeight(max(1, strokeW));
					  line(normalizeX(stemp.longp), normalizeY(stemp.latp), normalizeX(stemp1.longp), normalizeY(stemp1.latp));
					  //fill(0);
					  //stroke(0);
					  //strokeWeight(3);
					  //text(stemp.surv,normalizeX(stemp.longp)+20,normalizeY(stemp.latp)+30);
				  }
				  j++;
			  }//Survior block
			  
			  {
			    float x1,x2,y1,y2;
			    stroke(216,201,98);
				Survivors sur1 = survivors.get(i);
				Survivors sur2 = survivors.get(i+1); 
				if (sur1.div != sur2.div) continue;
				if(sur1.dir.contains("R")) stroke(235,130,66);
				//fill(strokecolor);
				//Implement button action
				
				if(toggleValue) {
					btnaction(sur1.div, sur1.dir);
				}
				
				float stWt = norm(sur1.surv, 4000, 340000) * 60;
			    strokeWeight(max(1, stWt));
				x1 = sur1.longp;
			    y1 = sur1.latp;  
			    x2 = sur2.longp; 
			    y2 = sur2.latp;
			    //System.out.println(normalizeX(x1)+" "+normalizeY(y1)+" "+normalizeX(x2)+" "+normalizeY(y2));
			    line(normalizeX(x1), normalizeY(y1), normalizeX(x2), normalizeY(y2));
			    
			    
			  } 
			    
		  }
		  
		  //display survivors
		  {
			  displaySurvivor();
		  }
		  
		  
		  for(int i=0;i<city.size();i++) {
			  fill(0);
			  stroke(0);
			  strokeWeight(2);
			  float x = city.get(i).longc;
			  float y = city.get(i).latc;
			  ellipse(normalizeX(x), normalizeY(y),5,5);
			  text(city.get(i).city,normalizeX(x)+20,normalizeY(y)+20);
		  }
		  
		  
		  		  
	}
	
	
	
	public void getData() {
		table = loadTable("\\data\\minard-data.csv","header");
		//int i=0;
		for(TableRow tr : table.rows()) {
			//System.out.println(tr.getFloat(0));
			if (!Double.isNaN(tr.getFloat(0))) {
				City cty = new City();
				cty.longc = tr.getFloat(0);
				cty.latc = tr.getFloat(1);
				cty.city = tr.getString(2);
				city.add(cty);
				//System.out.println(cty);
			}

			
			if(!Double.isNaN(tr.getFloat(3))) {
				Temperature tmp = new Temperature();
				tmp.longt = tr.getFloat(3);
			    tmp.longt = tr.getFloat(3);
			    tmp.temp = tr.getInt(4);
			    tmp.days = tr.getInt(5);
			    tmp.mon = tr.getString(6);
			    tmp.day = tr.getInt(7);
			    temperature.add(tmp);
			}
			
		    
		    Survivors srv = new Survivors();
		    srv.longp = tr.getFloat(8);
		    srv.latp = tr.getFloat(9);
		    srv.surv = tr.getInt(10);
		    srv.dir = tr.getString(11);
		    srv.div = tr.getInt(12);
		    survivors.add(srv);
		    
		   // i++;
		}
	}
	
	
	//Normalize the X and Y coordinates
	public float normalizeX(float x_val){
		//System.out.println((float) (norm(x_val,24f,36.5f) * width/1.5));
		//return (float) (norm(x_val,24f,36.5f) * width/1.5 + width/10);
		
		//return map((float) x_val,24f,36.5f,width/10,4*width/5);
		return map((float) x_val,24f,37.7f,width-1000,width-50);
	}
	
	public float normalizeY(float y_val){
		//return (float)(norm(y_val,24f,36.5f) * height/2 + height/1.5);
		//return map((float) y_val,53.9f,55.8f,height/6,height/3+200);
		return map((float) y_val,54.1f,55.8f,height/2,height/4-100);
	} 	
	
	
	public void btn() {
		cp5 = new ControlP5(this);
		cp5.addToggle("toggleValue").setPosition(30,160).setSize(20,30)
	     .setCaptionLabel("")
	     .setColorBackground(color(91,140,133))
	     .setColorForeground(color(0,0,255))
	     .setColorActive(color(105,124,55))
	     .setColorCaptionLabel(0);
		textAlign(CENTER, CENTER);
		textSize(200);
	}

	
	public void btnaction(int div,String dir) {
		//text(str(toggleValue)+" "+(div), width/2, height/2);
		if(div == 1 && dir.equals("A")) {
			//fill(91,140,90);
			stroke(91,140,90);
//			rect(50,180,0,0);
			//ellipse(50,180,1,1);
		} else if(div == 1 && dir.equals("R")) {
			stroke(82,115,24);
		} else if(div == 2 && dir.equals("A")) {
			stroke(199,44,65);
		} else if(div == 2 && dir.equals("R")) {
			stroke(255,99,99);
		}else if(div == 3 && dir.equals("A")) {
			stroke(66,41,28);
		} else if(div == 3 && dir.equals("R")) {
			stroke(181,84,0);
		}
		
		//textSize(8);
//		text("DIV 1 ATTACK",30,180);
		//lblText(div,dir);
	}
	
	public void lblText() {
		
		//textSize(8);
		if(toggleValue) {
			String[] divName = {"DIV-1 ADVANCE","DIV-1 RETREAT ","DIV-2 ADVANCE","DIV-2 RETREAT  ","DIV-3 ADVANCE  ","DIV-2 RETREAT   "};
			int [] divclr = {color(91,140,90),color(82,115,24),color(199,44,65),color(255,99,99),color(66,41,28),color(181,84,0)};
			PFont Font1;
			Font1 = createFont("Arial Bold", 10);
			textFont(Font1);
			fill(0);
			text("ARMY",90,175);
			for(int i = 0;i<6;i++) {
				pushMatrix();
				translate(30,225+(i+1)*10);
				fill(divclr[i]);
				stroke(0);
				rect(0,i*15,20,20);
				//ellipse(0,i*10,10,10);
				fill(0);
				text(divName[i],102+i*2,i*15+12);			
				popMatrix();
			}
		}else {
			String[] divName = {"ADVANCE","RETREAT   "};
			int [] divclr = {color(216,201,98),color(235,130,66)};
			fill(0);
			text("ARMY",90,175);
			for(int i = 0;i<2;i++) {
				pushMatrix();
				translate(30,225+(i+1)*10);
				fill(divclr[i]);
				stroke(0);
				rect(0,i*15,20,20);
				//ellipse(0,i*10,10,10);
				fill(0);
				text(divName[i],85+i*10,i*15+15);			
				popMatrix();
			}
			
		}
		textSize(11);
		
	}
	
	public void displayNotes() {
		String s = "The graph illustrates casualities of Nepoleon\'s army against Russian border. The thick yellow band illustrates "
				+ "the size of his army at different Geographic Location towards Moscow.";// 
		String s1 = "The thin orange band represents army size during the retreat. The bottom graph revelas the army suffered casulaties "
				+ "due to cold temperature";
		String s2 = "Toggle the Army button to display the March of each Division.";
		fill(0);
		PFont Font1;
		Font1 = createFont("Arial Bold", 12);
		textFont(Font1);
//		textSize(14);
		text(s,1070,500);
		text(s1,840,520);
		textSize(14);
		fill(30,30,40);
		text("Instructions:",width/2-480,550);
		textSize(13);
		text(s2,width/2-185,570);
		fill(0);
		Font1 = createFont("Serif.bolditalic", 12);
		textFont(Font1);
		textSize(11);
		
	}
	
	public void displaySurvivor() {
		for(int i=0;i<survivors.size()-1;i++) {
			float x1 = survivors.get(i).longp; 
		    float y1 = survivors.get(i).latp;  
		    //System.out.println(normalizeX(x1)+" "+normalizeY(y1)+" "+normalizeX(x2)+" "+normalizeY(y2));
		    fill(34,40,49);
		    textSize(9);
		    if(survivors.get(i).surv == survivors.get(i+1).surv || survivors.get(i).surv == 97000) continue;
		    text(survivors.get(i).surv,normalizeX(x1)-20,normalizeY(y1)+30);
		    
		}
		text(survivors.get(0).surv,normalizeX(survivors.get(0).longp)-20,normalizeY(survivors.get(0).latp));
		
		fill(34,40,49);
		textSize(12);
		stroke(34,40,49);
		strokeWeight(1);
		rect(900,250,15,15);
		text("ARMY SIZE",990,240);
		text("340000-6000",990,260);
	}
	

}
