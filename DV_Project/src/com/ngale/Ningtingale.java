package com.ngale;

//import java.awt.Color;
import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.data.TableRow;
//import processing.data.*;
//import processing.data.*;
//import processing.event.*;
//import processing.opengl.*;
//import processing.event.KeyEvent;

class Data {
	public String month = "";
	public int army_size = 0;
	public float dead_zymotic = 0;
	public float dead_wound = 0;
	public float dead_other = 0;
	public float mort_zymotic = 0;
	public float mort_wound = 0;
	public float mort_other = 0;
	
	@Override
	public String toString() {
        return month+" "+army_size+" "+dead_zymotic+" "+dead_wound+" "+dead_other+" "+mort_zymotic+
        		" "+mort_wound+" "+mort_other;
    }
}

public class Ningtingale extends PApplet{
	//Global Variables
	Table df;
	ArrayList<Data> ar_data = new ArrayList<Data>(); 
	float rot = radians(180);
	float zoom_flag = 7;
	ControlP5 cp5;
	boolean toggleValueD,toggleValueW,toggleValueO;

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("com.ngale.Ningtingale");
	}
	
	public void settings() {
		//fullScreen();
		size(1200,600);
	}
	public void setup() {
		//background(0xdfddc7);
		//df = loadTable("D:\\DataVisualization\\nightingale-data.csv","header");
		smooth();
		getData();
		loadData();
		textAlign(CENTER);
		//textSize(12);
		//textFont(loadFont("andalemo.ttf"));
		//System.out.println(PFont.list());
		//textFont(loadFont("Myanmar Text"));
		//printArray(PFont.list());
		PFont font;
		font = createFont("Serif.bolditalic", 12);
		textFont(font);
//		btn();
	}
	
	
	public void draw() {
		background(0xdfddc7);
		//text(mouseX+" "+mouseY,100,100);
		Data d1;
		float x_cord = width/2;
		float y_cord = height/2;
		//float rot = 135;
		//float ang = 30;
		//System.out.println(d1);
		
		//Display text
		{
			displayHeader();
			displayNotes();
			label();
		}
		
		
		//Construct Right Chart
		pushMatrix();
		translate(x_cord+300,y_cord-10);
		rot = rot_update();
		rotate(rot);
		
		//Zoom flag update
//		zoom_flag = zoom_update();
//		constrain(zoom_flag, 3, 9);
		zoom_update();
		for(int i=0;i<12;i++) {
			d1 = ar_data.get(i);
			float rad_disease = sqrt(12*d1.mort_zymotic/PI) * zoom_flag;
			float rad_other = sqrt(12*d1.mort_other/PI) * zoom_flag;
			float rad_wound = sqrt(12*d1.mort_wound/PI) * zoom_flag;

			//Align Text to right rose chart
			float high = max(rad_disease,rad_other,rad_wound);
			alignRtext(d1,i,high);
			//Draw right rose chart---------------------------------------------------------------------------------------
			//pushMatrix();
			//translate(x_cord,y_cord);
			//rotate(radians(45));
			fill(227, 176, 75,250);
			stroke(1);
			strokeWeight(1);
			arc(0,0,rad_disease,rad_disease,radians(i*30),radians(i*30+30),PIE);
			//popMatrix();
			
			//stroke(0x434e52);
			fill(51, 51, 51,250);
			stroke(1);
			strokeWeight(1);
			arc(0,0,rad_other,rad_other,radians(i*30),radians(i*30+30),PIE);
			
			fill(206, 15, 61,200);
			stroke(1);
			strokeWeight(1);
			arc(0,0,rad_wound,rad_wound,radians(i*30),radians(i*30+30),PIE);
			
		}
		popMatrix();
		//Left rose cart----------------------------------------
		pushMatrix();
		translate(x_cord-400,y_cord-150);
			
			
		for(int k=12;k<ar_data.size();k++) {
			d1 = ar_data.get(k);
			float rad_disease = sqrt(12*d1.mort_zymotic/PI) * 8;
			float rad_other = sqrt(12*d1.mort_other/PI) * 8;
			float rad_wound = sqrt(12*d1.mort_wound/PI) * 8;;

			//Align Text to right rose chart
			float high = max(rad_disease,rad_other,rad_wound);
			alignLtext(d1,k,high);
			//pushMatrix();
			//translate(x_cord,y_cord);
			//rotate(radians(45));
			fill(227, 176, 75,250);
			stroke(0);
			strokeWeight(1);
			arc(0,0,rad_disease,rad_disease,radians((k-12)*30),radians((k-12)*30+30),PIE);
			//popMatrix();
				
			//stroke(0x434e52);
			fill(51, 51, 51,250);
			stroke(0);
			strokeWeight(1);
			arc(0,0,rad_other,rad_other,radians((k-12)*30),radians((k-12)*30+30),PIE);
				
			fill(206, 15, 61,200);
			stroke(0);
			strokeWeight(1);
			arc(0,0,rad_wound,rad_wound,radians((k-12)*30),radians((k-12)*30+30),PIE);		
		}
		popMatrix();
			
		
	}
	
	public float rot_update() {
		// TODO Auto-generated method stub
		if(keyPressed && key == CODED) {
			//System.out.println(key);
			if(keyCode == RIGHT) {
				rot += radians(5);
			}else if(keyCode == LEFT) {
				rot -= radians(5);
			}
		}
		return rot;
	}
	
	
	public void zoom_update() {
		// TODO Auto-generated method stub
		if(keyPressed && key == CODED) {
			//System.out.println(key);
			if(keyCode == UP && zoom_flag<9) {
				zoom_flag += 0.1;
			}else if(keyCode == DOWN && zoom_flag>2) {
				zoom_flag -= 0.1;
			}
		}
		//constrain(zoom_flag, 3, 9);
		//return zoom_flag;
	}
	
	
	public void getData() {
//		df = loadTable("D:\\DataVisualization\\nightingale-data.csv","header");
		df = loadTable("\\data\\nightingale-data.csv","header");
//		System.out.println(df);
	}
	
	public void loadData() {
		for(TableRow tr : df.rows()) {
			Data data = new Data();
//			System.out.println(df.army_size);
			data.month = tr.getString(0);
			data.army_size = tr.getInt(1);
			data.dead_zymotic = tr.getFloat(2);
			data.dead_wound = tr.getFloat(3);
			data.dead_other = tr.getFloat(4);
			data.mort_zymotic = tr.getFloat(5);
			data.mort_wound = tr.getFloat(6);
			data.mort_other = tr.getFloat(7);
			ar_data.add(data);
		}
		
		/*
		for(Data d: ar_data) {
		System.out.println(d);}
		*/
	}
	
	public void alignRtext(Data d1,int i,float high) {

		//float srt_ang = i*30;
		//float end_ang = i*30+30;
		//Right Text Allignment----------------------------------------------------------------------------------------
		
		String[] my = d1.month.split(" ");
		//System.out.println(trim(my[0]));
		float arclength = 0;
		//textSize(15);
		
		//take the maximum distance to align text
		high = max(150,high);
		for(int j=0;j<trim(my[0]).length();j++) {
			// Instead of a constant width, we check the width of each character.
			float theta;
		    char currentChar = trim(my[0]).charAt(j);
		    float w = textWidth(currentChar);
		    // Each box is centered so we move half the width
		    arclength += w;
		    // Angle in radians is the arclength divided by the radius
		    // Starting on the left side of the circle by adding PI
		    theta = radians(i*30) + arclength / high;
		    
		        

		    pushMatrix();
		    // Polar to cartesian coordinate conversion
		    translate(high*cos(theta) * 0.5f, high*sin(theta) * 0.5f);
		    // Rotate the box
		    rotate(theta+PI/2); // rotation is offset by 90 degrees
		    // Display the character
		    fill(0);
		    text(currentChar,0,0);
		    popMatrix();
		    // Move halfway again
		    arclength += w;
		}
		
	}
	
	
	public void alignLtext(Data d1,int i,float high) {

		//float srt_ang = i*30;
		//float end_ang = i*30+30;
		//Right Text Allignment----------------------------------------------------------------------------------------
		
		String[] my = d1.month.split(" ");
		//System.out.println(trim(my[0]));
		float arclength = 0;
		//textSize(15);
		
		//take the maximum distance to align text
		high = max(150,high);
		for(int j=0;j<trim(my[0]).length();j++) {
			// Instead of a constant width, we check the width of each character.
			float theta;
		    char currentChar = trim(my[0]).charAt(j);
		    float w = textWidth(currentChar);
		    // Each box is centered so we move half the width
		    arclength += w;
		    // Angle in radians is the arclength divided by the radius
		    // Starting on the left side of the circle by adding PI
		    theta = radians((i-12)*30) + arclength / high;
		    
		        

		    pushMatrix();
		    // Polar to cartesian coordinate conversion
		    translate(high*cos(theta) * 0.5f, high*sin(theta) * 0.5f);
		    // Rotate the box
		    rotate(theta+PI/2); // rotation is offset by 90 degrees
		    // Display the character
		    fill(0);
		    text(currentChar,0,0);
		    popMatrix();
		    // Move halfway again
		    arclength += w;
		}
	}
	
	
	public void displayNotes() {
		noFill();
		rect(10,350,600,230);
		fill(0);
		textSize(13);
		String s1 = "The two chart shows the matters that affected the health of the British army in 1855 & 1856.";
		text(s1,270,380);
		s1 = "Each of the wedges are measured from the centre and represents the causes of death of the army.";
		text(s1,285,400);
		s1 = "The Yellow wedges represents the deaths from the Zymotic disease, Magenta wedges represents the deaths";
		text(s1,305,420);
		s1 = "by wounds and the Black wedges represents deaths by other reasons.";
		text(s1,205,440);
		String s2= "Press ↑ and ↓ Arrow Key to ZOOM IN and ZOOM OUT     \n"
				+ "Hold → and ← Arrow key to ROTATE the chart in the right";
		String s3 = "Instructions:-";
		textSize(16);
		text(s3,70,520);
		textSize(14);
		text(s2,200,540);
		textSize(12);
	}
	
	public void displayHeader() {
		textSize(30);
		fill(0);
		text("Nightingale\'s RoseChart",width/2-20,30);
		textSize(15);
		text("DIAGRAM OF THE CAUSES OF MORTALITY\r\n" + "IN THE ARMY OF THE EAST",width/2-20,60);
		text("APRIL 1855 TO MARCH 1856",150,50);
		text("APRIL 1854 TO MARCH 1855",1000,50);
		textSize(12);
	}

	public void label() {
		int[] clr = {color(227, 176, 75),color(206, 15, 61),color(51, 51, 51)};
		String [] txt = {"Zymotic Diseases","wounds","others"};
		for(int i =0;i<3;i++) {
			fill(clr[i]);
			rect(400,200+i*20,15,15);
		}
		fill(0);
		text("DEATH BY".toUpperCase(),430,190);
		text(txt[0].toUpperCase(),460+txt[0].length(),200+10);
		text(txt[1].toUpperCase(),440+txt[1].length(),200+30);
		text(txt[2].toUpperCase(),440+txt[2].length(),200+50);
	
	}

	
//	public void mousePressed() {
//		toggleValueD = true;
//		if(toggleValueD) {
//			text("true",430,200);
//		}else {
//			text("false",430,200);
//		}
//	}
	
}