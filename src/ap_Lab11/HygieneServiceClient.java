package ap_Lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HygieneServiceClient {

	public static void main(String[] args) {
		
		GenerateHygieneRatingUI();
		
	}
	
	public static ArrayList<Restaurant> retrieveRatings(String postcode) {
	
		String urlPostcode = postcode.replace(" ", "+");
		String request_url = "http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_postcode&postcode=" + urlPostcode;
		ArrayList<Restaurant> listData = new ArrayList<>();
		
		try {
			URLConnection connection = new URL(request_url).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String data = "", line = "";
			while ((line = reader.readLine()) != null) {
				data = data + line;
			}
			JSONArray ja = new JSONArray(data);
			for (int i=0; i<ja.length(); i++) {
				Restaurant restaurant = new Restaurant();
				JSONObject businessObject = ja.getJSONObject(i);
				int id = businessObject.getInt("id");
				String BusinessName = businessObject.getString("BusinessName");
				String AddressLine1 = businessObject.getString("AddressLine1");
				String AddressLine2 = businessObject.getString("AddressLine2");
				String AddressLine3 = businessObject.getString("AddressLine3");
				String PostCode = businessObject.getString("PostCode");
				int RatingValue = businessObject.getInt("RatingValue");
				LocalDate RatingDate = LocalDate.parse(businessObject.getString("RatingDate"));
				JSONObject Location = businessObject.getJSONObject("Location");
				String Latitude = Location.getString("Latitude");
				String Longitude = Location.getString("Longitude");
				restaurant.setId(id);
				restaurant.setBusinessName(BusinessName);
				restaurant.setAddressLine1(AddressLine1);
				restaurant.setAddressLine2(AddressLine2);
				restaurant.setAddressLine3(AddressLine3);
				restaurant.setPostCode(PostCode);
				restaurant.setRatingValue(RatingValue);
				restaurant.setRatingDate(RatingDate);
				restaurant.setLatitude(Latitude);
				restaurant.setLongitude(Longitude);
				listData.add(restaurant);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return listData;
	}
	
	public static void GenerateHygieneRatingUI() {
		
		JFrame window = new JFrame("Find Local Restaurant's Hygiene Rating!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel JPnorth = new JPanel();
		JTextField postcodeField = new JTextField("", 10);
		JButton searchBtn = new JButton("Search");
		JButton clearBtn = new JButton("Clear");
		
		JPanel JPcenter = new JPanel();
		JTextArea resultsBox = new JTextArea(43, 32);
		resultsBox.append("Enter a postcode to see your local restaurant's hygiene rating!");
	
		Container c = window.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(JPnorth, BorderLayout.NORTH);	
		c.add(JPcenter, BorderLayout.CENTER);
		JPnorth.setLayout(new FlowLayout());
		JPcenter.setLayout(new FlowLayout());
		JPnorth.add(postcodeField);
		JPnorth.add(searchBtn);
		JPnorth.add(clearBtn);

		JPanel JPcenterRes = new JPanel();
		JPcenter.add(JPcenterRes);
		
		JScrollPane scroll = new JScrollPane(resultsBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPcenterRes.add(scroll);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Thread(new Runnable() {
						public void run() {
							resultsBox.setText("");
							String userInput = postcodeField.getText();
							if (userInput.length() < 3) {
								resultsBox.append("Searches require at least three characters as a search term");
							} else {
								ArrayList<Restaurant> results = retrieveRatings(userInput);
								if (results.size() == 0) {
									resultsBox.append("No results");
								} else {
									for (int j=0; j<results.size(); j++) {
										resultsBox.append(results.get(j).toString());
									}
								}
							}
							JPcenterRes.repaint();
							c.revalidate();
						}
					}).start();
				}
				catch (JSONException jse) {
					jse.printStackTrace();
				}
			}
		});

		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultsBox.setText("Enter a postcode to see your local restaurant's hygiene rating!");
				JPcenterRes.repaint();
				c.invalidate();
			}
		});
		
		window.setSize(400,800);
		window.setVisible(true);
	}
}
