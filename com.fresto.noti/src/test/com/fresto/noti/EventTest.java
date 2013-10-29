package com.fresto.noti;

import org.zeromq.ZMQ;

import fresto.data.DataUnit;
import fresto.data.DataUnit._Fields;
import fresto.data.FrestoData;
import fresto.data.Pedigree;

public class EventTest {

	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket socket = context.socket(ZMQ.PUB);
		socket.bind("tcp://127.0.0.1/5000");
		
		
	}
	
	public FrestoData getFrestoData() {
		FrestoData frestoData = new FrestoData();
		
		DataUnit dataUnit = new DataUnit();
		dataUnit.setFieldValue(_Fields.REQUEST_EDGE, null);
		frestoData.setDataUnit(dataUnit);
		
		Pedigree pedigree = new Pedigree(44);
		frestoData.setPedigree(pedigree);
		
		return frestoData;
	}
}
