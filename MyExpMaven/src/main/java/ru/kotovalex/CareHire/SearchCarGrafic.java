package ru.kotovalex.CareHire;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kotovalex.CareHire.DatePicker.DatePickerPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

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
    private String apiKey = "co806067104850515641548345723445";
//    private String apiKey = "prtl6749387986743898559646983194";

    ;

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
        JComboBox pickUpPlace = new JComboBox();
        pickUpPlace.setPreferredSize(defaultSise);
        pickUpPlace.setBorder(new TitledBorder("PickUp Place"));
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        searchPanel.add(pickUpPlace, c);

        //добавление dropOffPlace combobox
        JComboBox dropOffPlace = new JComboBox();
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
        marketComboBox.setBorder(new TitledBorder("Market"));
        marketComboBox.setPreferredSize(halfSize);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 2;
        searchPanel.add(marketComboBox, c);

        //добавление locale
        localeComboBox = new JComboBox();
        localeComboBox.setBorder(new TitledBorder("Locale"));
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
        String ipAddress;
        try {
            InetAddress address = InetAddress.getLocalHost();
            ipAddress = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        add(searchPanel);

        //добавление панели вывода результатов
        JPanel resultPanel = new JPanel();
        JList resultList = new JList();
        resultList.setPreferredSize(new Dimension(getWidth() - 40, getHeight() - 150));
        resultPanel.add(resultList);

        add(resultPanel);


        setVisible(true);
    }

    private void setValueCurrencyComboBox() {
        try {
            URL url = new URL("http://partners.api.skyscanner.net/apiservices/reference/v1.0/countries/en-GB?apiKey=prtl6749387986743898559646983194");
//            URL url = new URL("http://partners.api.skyscanner.net/apiservices/reference/v1.0/currencies?apiKey="+apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            //connection.setRequestProperty("application/json", "HTTP/1.1");
            connection.getResponseMessage();

            //Scanner sc = new Scanner(connection.getInputStream());
            String jsonSting = connection.getResponseMessage();

            //парсинг
//            Gson gson = new Gson();
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(jsonSting);
//            JsonObject object = element.getAsJsonObject();
//            String currencies = object.get("Currencies").toString();
            System.out.println(jsonSting);


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
