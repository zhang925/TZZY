package com.zzy.model;

 import java.sql.Clob;
import java.util.Date;

public class tempModel  extends BaseDomain{
	private String name;
	private Date date;
	private int age;
	private java.lang.Integer age2;
	private double price;
	private java.lang.Double price2;
	private Clob text;
	
	private float float1;
	private java.lang.Float float2;
	
	private java.lang.Boolean b;
	private java.lang.Boolean c;
	
	public tempModel(){
		
	}
	public tempModel(String name,int age,double price){
		this.name = name;
		this.age = age;
		this.price =price;
	}
	
	public tempModel(String name,int age,double price,Date date){
		this.name = name;
		this.date = date;
		this.age = age;
		this.price =price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public java.lang.Integer getAge2() {
		return age2;
	}
	public void setAge2(java.lang.Integer age2) {
		this.age2 = age2;
	}
	public java.lang.Double getPrice2() {
		return price2;
	}
	public void setPrice2(java.lang.Double price2) {
		this.price2 = price2;
	}
	public Clob getText() {
		return text;
	}
	public void setText(Clob text) {
		this.text = text;
	}
	public float getFloat1() {
		return float1;
	}
	public void setFloat1(float float1) {
		this.float1 = float1;
	}
	public java.lang.Float getFloat2() {
		return float2;
	}
	public void setFloat2(java.lang.Float float2) {
		this.float2 = float2;
	}
	public java.lang.Boolean getB() {
		return b;
	}
	public void setB(java.lang.Boolean b) {
		this.b = b;
	}
	public java.lang.Boolean getC() {
		return c;
	}
	public void setC(java.lang.Boolean c) {
		this.c = c;
	}
	
	
	
	
}
