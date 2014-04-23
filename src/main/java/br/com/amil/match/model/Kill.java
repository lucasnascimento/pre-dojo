package br.com.amil.match.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

/**
 * Domain class to represent lines from LogFile
 * 
 * Sample:
 * 
 * 23/04/2013 15:34:22 - New match 11348965 has started
 * 23/04/2013 15:36:04 - Roman killed Nick using M16
 * 23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN
 * 23/04/2013 15:39:22 - Match 11348965 has ended
 * 
 * @author Lucas
 */
@Data
public class Kill implements Comparable<Kill> {

	final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	private Date killDate;
	private String killer;
	private String killed;
	private String gun;

	/**
	 * Bridge constructor
	 * 
	 * @param values
	 */
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
	
	/**
	 * Utility method to get Hour plus Minute of eventlog
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getKillMinute(){
		return killDate.getHours()+""+killDate.getMinutes();
	}
	
	@Override
	public int compareTo(Kill other) {
		return this.killDate.compareTo(other.killDate);
	}

}
