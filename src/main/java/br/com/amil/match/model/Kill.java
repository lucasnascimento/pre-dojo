package br.com.amil.match.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Kill implements Comparable<Kill> {

	final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	private Date killDate;
	private String killer;
	private String killed;
	private String gun;
	
	public Kill (String[] values){
		try {
			this.setKillDate( sdf.parse( values[0] ));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setKiller(values[1]);
		this.setKilled(values[2]);
		this.setGun(values[3]);
	}
	
	@Override
	public int compareTo(Kill other) {
		return this.killDate.compareTo(other.killDate);
	}

}
