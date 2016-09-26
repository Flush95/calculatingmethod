package org.flush.calculatingmethod.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.flush.calculatingmethod.model.ChartData;
import org.flush.calculatingmethod.service.CalculatingService;

@Path("/calculator")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {

	private List<Double> xArray = new ArrayList<>();
	private List<Double> yArray = new ArrayList<>();
	
	@POST
	@Path("/calculate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List <ChartData> retrieveChartData(@FormParam("obj") String obj) {
		String[] splited = obj.split(", ");

		for (String s: splited) {
			String[] temp = s.split(";");
			xArray.add(Double.valueOf(temp[0]));
			yArray.add(Double.valueOf(temp[1]));
		}
		
		CalculatingService service = new CalculatingService(xArray, yArray);
		
		return service.finalB();
		
	}
	
}
