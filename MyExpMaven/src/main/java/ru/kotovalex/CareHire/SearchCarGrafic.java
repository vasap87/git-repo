package ru.kotovalex.CareHire;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import ru.kotovalex.CareHire.DatePicker.DatePickerPanel;
import ru.kotovalex.CareHire.tools.CodeNameObject;
import ru.kotovalex.CareHire.tools.Currency;
import ru.kotovalex.skyscannerExample.client.Place;
import ru.kotovalex.skyscannerExample.client.Result;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 06.07.2016.
 */
public class SearchCarGrafic extends JFrame {

    private Dimension defaultSise = new Dimension(160, 50);
    private Dimension halfSize = new Dimension(80, 50);


    //элементы управления
    private JComboBox ageComboBox;
    private JComboBox currencyComboBox;
    private JComboBox localeComboBox;
    private JComboBox marketComboBox;
    private JComboBox pickUpPlace;
    private JComboBox dropOffPlace;


    //ссылки загрузки данных
    private final static String apiKey = "co806067104850515641548345723445";
    private final static String CURRENCIES_URL = "http://partners.api.skyscanner.net/apiservices/reference/v1.0/currencies?apiKey=" + apiKey;
    private final static String LOCALES_URL = "http://partners.api.skyscanner.net/apiservices/reference/v1.0/locales?apiKey=" + apiKey;
    private final static String MARKETS_URL = "http://partners.api.skyscanner.net/apiservices/reference/v1.0/countries/ru-RU?apiKey=" + apiKey;
    private final static String CREATE_SESSION_URL = "http://partners.api.skyscanner.net/apiservices/carhire/liveprices/v2/";
    private final static String PLACESAUTO_URL = "http://partners.api.skyscanner.net/apiservices/hotels/autosuggest/v2/";


    public SearchCarGrafic() {
        setTitle("CarSearch");
        setBounds(200, 200, 350, 500);
        setMinimumSize(new Dimension(350, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FlowLayout layout = new FlowLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);


        //добавление панели для поиска
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());

        //добавление pickupPlase combobox
        pickUpPlace = new JComboBox();
        pickUpPlace.setPreferredSize(defaultSise);
        pickUpPlace.setBorder(new TitledBorder("PickUp Place"));
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        searchPanel.add(pickUpPlace, c);

        //добавление dropOffPlace combobox
        dropOffPlace = new JComboBox();
        dropOffPlace.setPreferredSize(defaultSise);
        dropOffPlace.setBorder(new TitledBorder("DropOff Place"));
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 0;
        searchPanel.add(dropOffPlace, c);

        //добавдение pickupDate
        DatePickerPanel pickupDate = new DatePickerPanel();
        pickupDate.setPreferredSize(defaultSise);
        pickupDate.setBorder(new TitledBorder("PickUp DateTime"));
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        searchPanel.add(pickupDate, c);

        //добавдение dropOffDate
        DatePickerPanel dropOffDate = new DatePickerPanel();
        dropOffDate.setPreferredSize(defaultSise);
        dropOffDate.setBorder(new TitledBorder("DropOff DateTime"));
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 1;
        searchPanel.add(dropOffDate, c);

        //добавление market
        marketComboBox = new JComboBox();
        setValueMarketComboBox();
        marketComboBox.setBorder(new TitledBorder("Market"));
        marketComboBox.setPreferredSize(halfSize);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 2;
        searchPanel.add(marketComboBox, c);

        //добавление locale
        localeComboBox = new JComboBox();
        localeComboBox.setBorder(new TitledBorder("Locale"));
        setValueLocaleComboBox();
        localeComboBox.setPreferredSize(halfSize);
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 2;
        searchPanel.add(localeComboBox, c);

        //добавление currency
        currencyComboBox = new JComboBox();
        setValueCurrencyComboBox();
        currencyComboBox.setBorder(new TitledBorder("Currency"));
        currencyComboBox.setPreferredSize(halfSize);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridy = 2;
        searchPanel.add(currencyComboBox, c);

        //добавление age
        ageComboBox = new JComboBox();
        setValueAgeComboBox();
        ageComboBox.setBorder(new TitledBorder("Age"));
        ageComboBox.setPreferredSize(halfSize);

        c.gridx = 3;
        c.gridwidth = 1;
        c.gridy = 2;
        searchPanel.add(ageComboBox, c);

        //вычисляем текущий IP

        //кнопка поиска
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchCars());
        c.gridx = 0;
        c.gridwidth = 4;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(searchButton, c);

        add(searchPanel);

        setPickUpAndDropOffPlaces();


        //добавление панели вывода результатов
        JPanel resultPanel = new JPanel();
        JList resultList = new JList();
        resultList.setPreferredSize(new Dimension(getWidth() - 40, getHeight() - 150));
        resultPanel.add(resultList);

        add(resultPanel);


        setVisible(true);
    }

    private void setPickUpAndDropOffPlaces() {
        try {
            String urlString = PLACESAUTO_URL+marketComboBox.getSelectedItem()+"/"+currencyComboBox.getSelectedItem()+"/"+localeComboBox.getSelectedItem()+"/pari"+"?apikey="+apiKey;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonSting = bufferedReader.readLine();
            //парсинг
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jsonSting);
            JsonObject rootObject = jsonElement.getAsJsonObject();
            //получить массив plases
            String placesString = rootObject.get("places").toString();
            java.lang.reflect.Type type1 = new TypeToken<List<Place>>() {
            }.getType();
            List<Place> plases = gson.fromJson(placesString, type1);
            //Получить массив результатов
            String resultString = rootObject.get("results").toString();
            java.lang.reflect.Type type2 = new TypeToken<List<Result>>() {
            }.getType();
            List<Result> results = gson.fromJson(resultString, type2);
            for (Result result : results) {
                for (Place place : plases) {
                    if(result.getParent_place_id() == place.getPlace_id()) result.setPlace(place);
                }
            }
            String placesStr[] = new String[results.size()];
            for (int i = 0; i < results.size(); i++) {
                placesStr[i] = results.get(i).getIndividual_id();
            }

            pickUpPlace.setModel(new DefaultComboBoxModel(placesStr));
            pickUpPlace.updateUI();
            dropOffPlace.setModel(new DefaultComboBoxModel(placesStr));
            dropOffPlace.updateUI();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchCars() {
        String ipAddress = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            ipAddress = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Date pickUpdate = new Date();
        Date dropOffDate = new Date(System.currentTimeMillis()+864000000);
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String urlStr = CREATE_SESSION_URL + marketComboBox.getSelectedItem() + "/" + currencyComboBox.getSelectedItem() + "/" +
                localeComboBox.getSelectedItem() + "/" + pickUpPlace.getSelectedItem() + "/" + dropOffPlace.getSelectedItem() + "/" +
                currentDate.format(pickUpdate) + "/" + currentDate.format(dropOffDate) + "/" + ageComboBox.getSelectedItem() +
                "?apiKey=" + apiKey + "&userip=" + ipAddress;
        System.out.println(urlStr);

    }

    private void setValueMarketComboBox() {
        try {
            URL url = new URL(MARKETS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonSting = bufferedReader.readLine();
            //парсинг
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(jsonSting);
            JsonObject object = element.getAsJsonObject();
            String markets = object.get("Countries").toString();
            java.lang.reflect.Type type1 = new TypeToken<List<CodeNameObject>>() {
            }.getType();
            List<CodeNameObject> localesList = gson.fromJson(markets, type1);
            String marketsStr[] = new String[localesList.size()];
            for (int i = 0; i < localesList.size(); i++) {
                marketsStr[i] = localesList.get(i).getCode();
            }
            Arrays.sort(marketsStr);
            marketComboBox.setModel(new DefaultComboBoxModel(marketsStr));
            marketComboBox.setSelectedItem("RU");


        } catch (MalformedURLException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValueLocaleComboBox() {
        try {
            URL url = new URL(LOCALES_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonSting = bufferedReader.readLine();
            //парсинг
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(jsonSting);
            JsonObject object = element.getAsJsonObject();
            String locales = object.get("Locales").toString();
            java.lang.reflect.Type type1 = new TypeToken<List<CodeNameObject>>() {
            }.getType();
            List<CodeNameObject> localesList = gson.fromJson(locales, type1);
            String localesStr[] = new String[localesList.size()];
            for (int i = 0; i < localesList.size(); i++) {
                localesStr[i] = localesList.get(i).getCode();
            }
            Arrays.sort(localesStr);
            localeComboBox.setModel(new DefaultComboBoxModel(localesStr));
            localeComboBox.setSelectedItem("ru-RU");


        } catch (MalformedURLException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValueCurrencyComboBox() {
        try {
            URL url = new URL(CURRENCIES_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonSting = bufferedReader.readLine();
            //парсинг
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(jsonSting);
            JsonObject object = element.getAsJsonObject();
            String currencies = object.get("Currencies").toString();
            java.lang.reflect.Type type1 = new TypeToken<List<Currency>>() {
            }.getType();
            List<Currency> currencyList = gson.fromJson(currencies, type1);
            String currenciesStr[] = new String[currencyList.size()];
            for (int i = 0; i < currencyList.size(); i++) {
                currenciesStr[i] = currencyList.get(i).toString();
            }
            currencyComboBox.setModel(new DefaultComboBoxModel(currenciesStr));
            currencyComboBox.setSelectedItem("RUB");


        } catch (MalformedURLException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValueAgeComboBox() {
        String ages[] = new String[55];
        for (int i = 21, j = 0; i < 76; i++, j++) {
            ages[j] = "" + i;
        }
        ageComboBox.setModel(new DefaultComboBoxModel(ages));
    }

}
