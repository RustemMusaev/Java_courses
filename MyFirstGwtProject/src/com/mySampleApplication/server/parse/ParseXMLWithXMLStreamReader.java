package com.mySampleApplication.server.parse;


import com.mySampleApplication.server.model.Point;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ParseXMLWithXMLStreamReader {

    public static final String XML_FILE_TO_READ = "D:\\1.xml";

    public static List<Point> ParseXMLWithXMLStreamReader() {
        CreateXMLfileWithXMLStreamWriter createXMLfileWithXMLStreamWriter = new CreateXMLfileWithXMLStreamWriter();
        try {
            createXMLfileWithXMLStreamWriter.createXMLfileWithXMLStreamWriter(generatePoints());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader parser = null;
        try {
            parser = xmlInputFactory.createXMLStreamReader(new FileInputStream(XML_FILE_TO_READ));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Point> pointList = new ArrayList<>();
        Point point = null;
        try {
            int event = parser.getEventType();
            while (true) {
                switch (event) {
                    case XMLStreamConstants.START_DOCUMENT:
                        System.out.println("Start Document.");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        System.out.println("Start Element: " + parser.getName() + "");
                        if (String.valueOf(parser.getName()).equals("point")) {
                            point = new Point();
                            point.setId(Integer.parseInt(parser.getAttributeValue(0)));
                        }
                        if (point != null) {
                            createPoint(point, parser);
                        }

                        // for(int i = 0, n = parser.getAttributeCount(); i < n; ++i)
                        //      System.out.println("Attribute i="+i+ ": " + parser.getAttributeName(i)
                        //                + "=" + parser.getAttributeValue(i));
                        //   break;
                    case XMLStreamConstants.CHARACTERS:
                        if (parser.isWhiteSpace())
                            break;
                        //      System.out.println("Text: " + parser.getText());
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        System.out.println("End Element:" + parser.getName());
                        if (String.valueOf(parser.getName()).equals("point")) {
                            pointList.add(point);
                            System.out.println(pointList);
                            point = null;
                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        System.out.println("End Document.");
                        break;
                }
                if (!parser.hasNext())
                    break;
                event = parser.next();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                parser.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return pointList;
    }
    public static void createPoint(Point point, XMLStreamReader parser) throws XMLStreamException {
        String name = String.valueOf(parser.getName());
        if (name.equals("name")) {
            point.setName(parser.getElementText());
        }
        if (name.equals("city")) {
            point.setCity(parser.getElementText());
        }
        if (name.equals("country")) {
            point.setCountry(parser.getElementText());
        }
        if (name.equals("address")) {
            point.setAddress(parser.getElementText());
        }
        if (name.equals("phone")) {
            point.setPhone(parser.getElementText());
        }
        if (name.equals("services")) {
            point.setServices(Integer.parseInt(parser.getElementText()));
        }
    }
    public static List<Point> generatePoints() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Point point = new Point();
            point.setId(i+1);
            point.setCountry(generateCountry());
            point.setAddress(point.getCountry() + " street");
            point.setName(point.getCountry() + " name");
            point.setCity(point.getCountry()+ " city");
            point.setPhone(generatePhone());
            point.setServices(generateService());
            points.add(point);
        }
        return points;
    }
    public static String generatePhone() {
        StringBuilder phone = new StringBuilder();
        phone.append((int) (Math.random() * 10)).append((int) (Math.random() * 10)).append((int) (Math.random() * 10))
                .append((int) (Math.random() * 10)).append((int) (Math.random() * 10)).append((int) (Math.random() * 10))
                .append((int) (Math.random() * 10)).append((int) (Math.random() * 10)).append((int) (Math.random() * 10))
                .append((int) (Math.random() * 10));
        return String.valueOf(phone);
    }
    public static int generateService() {
        int count = (int) (Math.random() * 10);
        if (count == 0 || count == 1 || count == 2 || count == 3) {
            return 0;
        } else if (count == 4 || count == 5 || count == 6) {
            return 1;
        } else if (count == 7 || count == 8 || count == 9 || count == 10) {
            return 2;
        }
        return count;
    }
    public static String generateCountry(){
        List<String> countries = new ArrayList<>();
        countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("American Samoa");
        countries.add("Andorra");
        countries.add("Angola");
        countries.add("Anguilla");
        countries.add("Antarctica");
        countries.add("Antigua And Barbuda");
        countries.add("Argentina");
        countries.add("Armenia");
        countries.add("Aruba");
        countries.add("Australia");
        countries.add("Austria");
        countries.add("Azerbaijan");
        countries.add("Bahamas");
        countries.add("Bahrain");
        countries.add("Bangladesh");
        countries.add("Barbados");
        countries.add("Belarus");
        countries.add("Belgium");
        countries.add("Belize");
        countries.add("Benin");
        countries.add("Bermuda");
        countries.add("Bhutan");
        countries.add("Bolivia");
        countries.add("Bosnia And Herzegovina");
        countries.add("Botswana");
        countries.add("Bouvet Island");
        countries.add("Brazil");
        countries.add("British Indian Ocean Territory");
        countries.add("Brunei Darussalam");
        countries.add("Bulgaria");
        countries.add("Burkina Faso");
        countries.add("Burundi");
        countries.add("Cambodia");
        countries.add("Cameroon");
        countries.add("Canada");
        countries.add("Cape Verde");
        countries.add("Cayman Islands");
        countries.add("Central African Republic");
        countries.add("Chad");
        countries.add("Chile");
        countries.add("China");
        countries.add("Christmas Island");
        countries.add("Cocos Islands");
        countries.add("Colombia");
        countries.add("Comoros");
        countries.add("Congo");
        countries.add("Congo");
        countries.add("Cook Islands");
        countries.add("Costa Rica");
        countries.add("Cote D''ivoire");
        countries.add("Croatia");
        countries.add("Cuba");
        countries.add("Cyprus");
        countries.add("Czech Republic");
       /* countries.add("Denmark");
        countries.add("Djibouti");
        countries.add("Dominica");
        countries.add("Dominican Republic");
        countries.add("East Timor");
        countries.add("Ecuador");
        countries.add("Egypt");
        countries.add("El Salvador");
        countries.add("Equatorial Guinea");
        countries.add("Eritrea");
        countries.add("Estonia");
        countries.add("Ethiopia");
        countries.add("Falkland Islands");
        countries.add("Faroe Islands");
        countries.add("Fiji");
        countries.add("Finland");
        countries.add("France");
        countries.add("French Guiana");
        countries.add("French Polynesia");
        countries.add("French Southern Territories");
        countries.add("Gabon");
        countries.add("Gambia");
        countries.add("Georgia");
        countries.add("Germany");
        countries.add("Ghana");
        countries.add("Gibraltar");
        countries.add("Greece");
        countries.add("Greenland");
        countries.add("Grenada");
        countries.add("Guadeloupe");
        countries.add("Guam");
        countries.add("Guatemala");
        countries.add("Guinea-Bissau");
        countries.add("Guinea");
        countries.add("Guyana");
        countries.add("Haiti");
        countries.add("Heard Island And Mcdonald Islands");
        countries.add("Holy See");
        countries.add("Honduras");
        countries.add("Hong Kong");
        countries.add("Hungary");
        countries.add("Iceland");
        countries.add("India");
        countries.add("Indonesia");
        countries.add("Iran");
        countries.add("Iraq");
        countries.add("Ireland");
        countries.add("Israel");
        countries.add("Italy");
        countries.add("Jamaica");
        countries.add("Japan");
        countries.add("Jordan");
        countries.add("Kazakstan");
        countries.add("Kenya");
        countries.add("Kiribati");
        countries.add("South Korea");
        countries.add("North Korea");
        countries.add("Kuwait");
        countries.add("Kyrgyzstan");
        countries.add("Laos");
        countries.add("Latvia");
        countries.add("Lebanon");
        countries.add("Lesotho");
        countries.add("Liberia");
        countries.add("Libyan Arab Jamahiriya");
        countries.add("Liechtenstein");
        countries.add("Lithuania");
        countries.add("Luxembourg");
        countries.add("Macau");
        countries.add("Madagascar");
        countries.add("Malawi");
        countries.add("Malaysia");
        countries.add("Maldives");
        countries.add("Mali");
        countries.add("Malta");
        countries.add("Marshall Islands");
        countries.add("Martinique");
        countries.add("Mauritania");
        countries.add("Mauritius");
        countries.add("Mayotte");
        countries.add("Mexico");
        countries.add("Micronesia");
        countries.add("Moldova");
        countries.add("Monaco");
        countries.add("Mongolia");
        countries.add("Montserrat");
        countries.add("Morocco");
        countries.add("Mozambique");
        countries.add("Myanmar");
        countries.add("Namibia");
        countries.add("Nauru");
        countries.add("Nepal");
        countries.add("Netherlands Antilles");
        countries.add("Netherlands");
        countries.add("New Caledonia");
        countries.add("New Zealand");
        countries.add("Nicaragua");
        countries.add("Niger");
        countries.add("Nigeria");
        countries.add("Niue");
        countries.add("Norfolk Island");
        countries.add("Northern Mariana Islands");
        countries.add("Norway");
        countries.add("Oman");
        countries.add("Pakistan");
        countries.add("Palau");
        countries.add("Palestine");
        countries.add("Panama");
        countries.add("Papua New Guinea");
        countries.add("Paraguay");
        countries.add("Peru");
        countries.add("Philippines");
        countries.add("Pitcairn");
        countries.add("Poland");
        countries.add("Portugal");
        countries.add("Puerto Rico");
        countries.add("Qatar");
        countries.add("Reunion");
        countries.add("Romania");
        countries.add("Russian Federation");
        countries.add("Rwanda");
        countries.add("Saint Helena");
        countries.add("Saint Kitts And Nevis");
        countries.add("Saint Lucia");
        countries.add("Saint Pierre And Miquelon");
        countries.add("Saint Vincent And The Grenadines");
        countries.add("Samoa");
        countries.add("San Marino");
        countries.add("Sao Tome And Principe");
        countries.add("Saudi Arabia");
        countries.add("Senegal");
        countries.add("Seychelles");
        countries.add("Sierra Leone");
        countries.add("Singapore");
        countries.add("Slovakia");
        countries.add("Slovenia");
        countries.add("Solomon Islands");
        countries.add("Somalia");
        countries.add("South Africa");
        countries.add("South Georgia And The South Sandwich Islands");
        countries.add("Spain");
        countries.add("Sri Lanka");
        countries.add("Sudan");
        countries.add("Suriname");
        countries.add("Svalbard And Jan Mayen");
        countries.add("Swaziland");
        countries.add("Sweden");
        countries.add("Switzerland");
        countries.add("Syrian Arab Republic");
        countries.add("Taiwan, Province Of China");
        countries.add("Tajikistan");
        countries.add("Tanzania");
        countries.add("Thailand");
        countries.add("Togo");
        countries.add("Tokelau");
        countries.add("Tonga");
        countries.add("Trinidad And Tobago");
        countries.add("Tunisia");
        countries.add("Turkey");
        countries.add("Turkmenistan");
        countries.add("Turks And Caicos Islands");
        countries.add("Tuvalu");
        countries.add("Uganda");
        countries.add("Ukraine");
        countries.add("United Arab Emirates");
        countries.add("United Kingdom");
        countries.add("United States Minor Outlying Islands");
        countries.add("United States");
        countries.add("Uruguay");
        countries.add("Uzbekistan");
        countries.add("Vanuatu");
        countries.add("Venezuela");
        countries.add("Viet Nam");
        countries.add("Virgin Islands, British");
        countries.add("Virgin Islands, U.S.");
        countries.add("Wallis And Futuna");
        countries.add("Western Sahara");
        countries.add("Yemen");
        countries.add("Yugoslavia");
        countries.add("Zambia");
        countries.add("Zimbabwe");*/
        int index = (int) (Math.random() * countries.size());
        String country = countries.get(index);
        return country;
    }
}