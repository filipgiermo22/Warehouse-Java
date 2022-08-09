/*
 * Java script written by Filip Giermek
 * Course Java Programming 
 * FESB UNIST 2022
 * 
 * Program simulates software used to manage warehouse with various merchandise.
 * Main components are:
 * - GUI
 * - Constructor and getters
 * - Action handling method
 * - Correspondence with API
 */

import java.awt.*;  
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList; 

public class Warehouse extends WindowAdapter implements ActionListener {

Frame f;
Label input, remove, info, kn, kn_2, list_l;
TextField input_in, amount_in, price, info_num, remove_num, amount_info, price_info_1, price_info_2, unit, currency_2;
Choice currency;
Checkbox type_1, type_2;
List list;
Button add_button, remove_button, info_button, clear_button;

String input_str;
float am, price_fl, remove_num_fl, input_num;
boolean u;


public Warehouse() {
	f = new Frame("Warehouse");
	
//----------------add product------------------
	input = new Label(" Add Product");
	input.setBackground(new Color(215, 215, 219));
	input.setBounds(20,50,80,20);
	
	input_in = new TextField("name");
	input_in.setBounds(110,50,170,20);
	input_in.addActionListener(this);
	
	amount_in = new TextField(" amount");
	amount_in.setBounds(110,80,50,20);
	amount_in.addActionListener(this);
	
	 type_1 = new Checkbox("kg");
	 type_1.setBounds(110, 100, 30,30);
	 
	 type_2 = new Checkbox("l");
	 type_2.setBounds(140, 100, 30,30);
	 
	 price = new TextField("value");
	 price.setBounds(110,130,50,20);
	 price.addActionListener(this);
	 
	 kn = new Label("kn");
	 kn.setBounds(160,130,30,20);
	 
	 add_button = new Button("Add");
	 add_button.setBackground(new Color(170, 213, 118));
	 add_button.setBounds(195,95,70,40);
	 add_button.addActionListener(this);
//---------------------------------------------	 
	 
	 
//---------------remove product------------------
	
	remove = new Label(" Remove Product");
	remove.setBackground(new Color(215, 215, 219));
	remove.setBounds(20,170,107,20);
	
	remove_num = new TextField(" position");
	remove_num.setBounds(135,170,55,20);
	remove_num.addActionListener(this);
	
	remove_button = new Button("Remove");
	remove_button.setBackground(new Color(254, 74, 73));
	remove_button.setBounds(195,170,70,20);
	remove_button.addActionListener(this);
//------------------------------------------------
	
	
//--------------info product----------------------
	
	info = new Label(" Info about Product");
	info.setBackground(new Color(215, 215, 219));
	info.setBounds(20,210,107,20);
	
	info_num = new TextField(" position");
	info_num.setBounds(135,210,55,20);
	info_num.addActionListener(this);
	
	currency = new Choice();
	currency.setBounds(100,240,55,20);
	currency.add("GBP");
	currency.add("EUR");
	currency.add("JPY");
	currency.add("CHF");
	currency.add("USD");
	currency.add("PLN");
	
	info_button = new Button("Info");
	info_button.setBackground(new Color(254, 215, 102));
	info_button.setBounds(195,210,70,20);
	info_button.addActionListener(this);
	
	amount_info = new TextField(" amount");
	amount_info.setBounds(20,240,50,20);
	amount_info.addActionListener(this);

	unit = new TextField("unit");
	unit.setBounds(70,240,25,20);
	unit.addActionListener(this);
	
	price_info_1 = new TextField();
	price_info_1.setBounds(160,240,50,20);
	price_info_1.addActionListener(this);
	
	price_info_2 = new TextField();
	price_info_2.setBounds(160,265,50,20);
	price_info_2.addActionListener(this);
	
	kn_2 = new Label("kn");
	kn_2.setBounds(210,240,30,20);
	
	currency_2 = new TextField();
	currency_2.setBounds(210,265,40,20);
	currency_2.addActionListener(this);
//---------------------------------------------
	
	
//---------------List--------------------------
	list_l = new Label(" List of products");
	list_l.setBackground(new Color(215, 215, 219));
	list_l.setBounds(100,305,90,20);
	
	list = new List();
	list.setBounds(20,330,260,120);
//---------------------------------------------
	
	
//--------------Clear--------------------------
	clear_button = new Button("Clear");
	clear_button.setBackground(new Color(163, 206, 241));
	clear_button.setBounds(20,455,260,25);
	clear_button.addActionListener(this);
//---------------------------------------------
	f.addWindowListener(this);
	
	f.add(input);
	f.add(input_in);
	f.add(amount_in);
	f.add(type_1);
	f.add(type_2);
	f.add(price);
	f.add(kn);
	f.add(add_button);
	f.add(remove);
	f.add(remove_num);
	f.add(remove_button);
	f.add(info);
	f.add(info_num);
	f.add(currency);
	f.add(info_button);
	f.add(amount_info);
	f.add(unit);
	f.add(price_info_1);
	f.add(price_info_2);
	f.add(kn_2);
	f.add(currency_2);
	f.add(list_l);
	f.add(list);
	f.add(clear_button);
	
	f.setSize(300, 500);
	f.setBackground(new Color(240, 242, 243));
	f.setLayout(null);
	f.setVisible(true);	
	f.setLocation(470,150);
}

public void windowClosing(WindowEvent e) {
	  f.dispose();
	}

public class Merch {
	private String name, uni;
	private float amount, value;
	
	
//------------CONSTRUCTOR-------------------
	
	public Merch(String n, float a, float v, String u){
		this.name = n;
		this.amount = a;
		this.value = v;
		this.uni = u;
	}

//------------GETTERS------------------------

	public float getAmount(float amount) {
		return amount;
	}
	
	public boolean getUnit(boolean unit) {
		return unit;
	}
	
	public float getValue(float value) {
		return value;
	}
	
//-----------ToString override--------------
	@Override
	public String toString() {
        return this.name + " | " + this.amount + " " + this.uni + " | " + this.value + " kn";
    }
}

	//------------MAIN---------------//
public static void main(String[] args) {
    new Warehouse();
}

//--------List of objects-------------------------------
ArrayList<Merch> merch_list = new ArrayList<>();

//-----ACTION LISTENERS----------------------------------
@SuppressWarnings("unlikely-arg-type")
public void actionPerformed(ActionEvent e) {
	String name = input_in.getText();
	float amo = Float.parseFloat(amount_in.getText());
	float value = Float.parseFloat(price.getText());
	String uni = null;
	
	if(type_1.getState() == true){ uni = "kg"; }
	else { uni = "l"; }
	
	
	if (e.getSource() == add_button) {
		Merch object = new Merch(name, amo, value, uni);
		merch_list.add(object);
		list.add(String.valueOf(object));
	}
	
	else if (e.getSource() == remove_button) {
		merch_list.remove(Float.parseFloat(remove_num.getText())-1);
		list.remove(Integer.parseInt(remove_num.getText())-1);
	}
	
	else if (e.getSource() == info_button) {
		int i_n = Integer.parseInt(info_num.getText());
		amount_info.setText(String.valueOf(merch_list.get(i_n-1).amount));
		unit.setText(uni);						
		price_info_1.setText(String.valueOf(merch_list.get(i_n-1).value));
		
		//-----API handling-----------------------------------------------
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from=HRK&to=" + currency.getItem(currency.getSelectedIndex()) + "&amount=" + price_info_1))
				.header("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com")
				.header("X-RapidAPI-Key", "ff5fa60a8emsh7f157eedb47ba07p1715cbjsn07b9f162a61d")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(response.body());
				
		float rate = Float.parseFloat(response.body().substring(response.body().lastIndexOf("amount")+9,response.body().lastIndexOf("amount")+14));
		
	price_info_2.setText(String.valueOf(rate));
	currency_2.setText(currency.getItem(currency.getSelectedIndex()));
	}
	
	else if (e.getSource() == clear_button) {
		input_in.setText(null); 
		amount_in.setText(null);
		price.setText(null);
		info_num.setText(null);
		remove_num.setText(null);
		amount_info.setText(null);
		price_info_1.setText(null);
		price_info_2.setText(null);
		unit.setText(null);
		currency_2.setText(null);
		}
	}
}