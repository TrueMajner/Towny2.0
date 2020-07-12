package com.palmergames.bukkit.towny.database.dbHandlers;

import com.palmergames.bukkit.towny.database.handler.LoadHandler;
import com.palmergames.bukkit.towny.database.handler.SaveContext;
import com.palmergames.bukkit.towny.database.handler.SerializationHandler;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.database.handler.LoadContext;

import java.util.ArrayList;
import java.util.List;

public class ResidentListHandler implements SerializationHandler<List<Resident>> {
	
	@Override
	public List<Resident> loadString(LoadContext context, String str) {
		List<Resident> residents = new ArrayList<>();
		String strCopy;
		strCopy = str.replace("[", "").replace("]", "");
		String[] residentNames = strCopy.split(",");
		
		for (String residentName : residentNames) {
			Resident loadedResident = context.fromStoredString(residentName, Resident.class);
			
			if (loadedResident == null) {
				continue;
			}
			
			residents.add(loadedResident);
		}
		
		return residents;
	}

	@Override
	public String toStoredString(SaveContext context, List<Resident> obj) {
		StringBuilder output = new StringBuilder("[");
		for (Resident r : obj) {
			output.append(r.getUniqueIdentifier()).append(",");
		}
		
		output.append(']');
		
		return output.toString();
	}
}