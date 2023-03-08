package com.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.model.LocationStates;

import jakarta.annotation.PostConstruct;

@Service
public class CovidTrackerService {
	
	private List<LocationStates> allStates = new ArrayList<>();
	
	public static final String VIRUS_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	@PostConstruct
	public void fetchData() throws IOException, InterruptedException{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_URL)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader reader = new StringReader(httpResponse.body());
		List<LocationStates> newStates = new ArrayList<>();
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for(CSVRecord rec:records) {
			LocationStates states = new LocationStates();
			states.setState(rec.get("Province/State"));
			states.setCountry(rec.get("Country/Region"));
			long totalCases = Long.parseLong(rec.get(rec.size()-1));
			states.setLatestTotalCases(totalCases);
			states.setDiffFromPrevDay(totalCases - Long.parseLong(rec.get(rec.size()-2)));
			newStates.add(states);
		}
		
	   this.allStates=newStates;
		
	}

	public List<LocationStates> getAllStates() {
		return allStates;
	}
	
}
