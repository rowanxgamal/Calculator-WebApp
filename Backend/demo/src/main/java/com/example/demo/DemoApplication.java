package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:8082")
@RestController

public class DemoApplication {
	float num1,num2;
	Boolean isfloat=false, isNeg1=false, isNeg2=false;
	String s="";
	Character op='0';
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@GetMapping
//	public List <String> hello(){
//		return List.of("helloWorld");
//	}
	@PostMapping("/add")
	public String add(@RequestBody int x){
		if (s=="E")
			s=Integer.toString(x);
		else
			s=s+Integer.toString(x);
		return s;
	}
	@PostMapping("/operator")
	public String addoperator(@RequestBody Character x){
		if (x=='.'){
			s=s+Character.toString(x);
			return s;
		}
		if (s=="E"){
			s="E";
			return s;
		}
		if (s.charAt(s.length()-1)==op){
			s="E";
			return s;
		}
		if (x=='='&& op=='0')
			return s;
		if ( num1==0 &&num2==0 && op=='0' && x!='='){
			if (s.contains(".")){
				num1=Float.valueOf(this.s);
				isfloat=true;
			}
			else
				num1 = Integer.valueOf(this.s);
			this.s=s+Character.toString(x);
			op=x;
			return s;
		}
		int index = s.lastIndexOf(op);
		String s2 = s.substring(index+1);
		if (s2.contains(".")){
			num2 = Float.valueOf(s2);
			isfloat=true;
		}
		else
			num2 = Integer.valueOf(s2);
		if (x=='%'){
			num2= (float) ((num2*num1)/100.0);
			isfloat=true;
		}
		switch (op){
			case '+':
				num1+=num2;
				num2=0;
				break;
			case '-':
				num1-=num2;
				num2=0;
				break;
			case 'x':
				num1*=num2;
				num2=0;
				break;
			case '/':
				if ((int)num2==0){
					this.s="E";
					num1=0;
					num2=0;
					op='0';
					isfloat=false;
					return s;
				}else{
					num1/=(float) num2;
					isfloat=true;
					num2=0;
				}
				break;
//			default:
//				s="E";
//				return s;
		}
		if (x=='%' || x=='=')
			op='0';
		else
			op=x;

		if (op == '0'){
			if (isfloat)
				s = Float.toString(num1);
			else
				s = Integer.toString((int)num1);
		} else{
			if (isfloat)
				s = Float.toString(num1)+Character.toString(op);
			else
				s = Integer.toString((int)num1)+Character.toString(op);
		}

		return s;
	}
	@PostMapping("/unary")
	public String unary(@RequestBody Character x){
		if (s=="" || s=="E"){
			s="E";
			return s;
		}
		if (op!='0'){
			s= addoperator('=');
		}
		if (s.contains(".")){
			num1=Float.valueOf(this.s);
			isfloat=true;
		}
		else
			num1 = Integer.valueOf(this.s);

		switch (x){
			case '#':
				num1 =(float) 1/num1;
				isfloat = true;
				break;
			case '^':
				num1=num1*num1;
				break;
			case '$':
				if (num1<0){
					s="E";
					return s;
				}
				else {
					num1 = (float) Math.sqrt(num1);
					isfloat = true;
				}
				break;
		}
		if (isfloat)
			s = Float.toString(num1);
		else
			s = Integer.toString((int)num1);
		return s;
	}
	@PostMapping("/reset")
	public String reset(){
		this.s="";
		num1=0;
		num2=0;
		isfloat=false;
//		isNeg1=false;
//		isNeg2=false;
		op='0';
		return s;
	}
	@PostMapping("/delete")
	public String del(){
		if (s.length()>0){
			Character ch=s.charAt(s.length()-1);
			if (op=='0')
				num1=0;
			if (!Character.isDigit(ch)){
				op ='0';
				num1=0;
				num2=0;
			}
			s = s.substring(0,s.length()-1);
		}
		return s;
	}
	@PostMapping("/toggle")
	public String toggle(){
		if (s=="" || s=="E"){
			s="E";
			return s;
		}
		if (op=='0'){
			if (s.charAt(0)!='-')
				s=Character.toString('-')+s;
			else
				s=s.substring(1);
		}
		else{
			int index = s.lastIndexOf(op);
			if (s.charAt(index+1)!='-')
				s=s.substring(0,index+1)+"-"+s.substring(index+1);
			else
				s=s.substring(0,index+1)+s.substring(index+2);
		}
		return s;
	}
	@PostMapping("/error")
	public String error(@RequestBody String s){
		this.s=s;
		return s;
	}

	@PostMapping("/change")
	// object containing  the arraylist and no return
	public Object takeChange(@RequestBody Object s){
		System.out.println(s);
		return s;
	}
}
