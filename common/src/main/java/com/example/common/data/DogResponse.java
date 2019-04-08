package com.example.common.data;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DogResponse{

	@SerializedName("message")
	private List<String> message;

	@SerializedName("status")
	private String status;

	public void setMessage(List<String> message){
		this.message = message;
	}

	public List<String> getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DogResponse{" + 
			"message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}