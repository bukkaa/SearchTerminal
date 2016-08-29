package test.gwt.hibernate.client;

import test.gwt.hibernate.shared.PointsEntity;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;


public class SearchInterface implements EntryPoint {
    private VerticalPanel leftPanel = new VerticalPanel();
    private HorizontalPanel mainPanel = new HorizontalPanel();
    private FlexTable rightPanel = new FlexTable();

    private HorizontalPanel inputCityPanel = new HorizontalPanel();
    private HorizontalPanel inputBankPanel = new HorizontalPanel();

    private Label labelCity = new Label("Input city");
    private Label labelBank = new Label("Input bank");

    private TextBox inputCity = new TextBox();
    private TextBox inputBank = new TextBox();

    private Button searchButton = new Button("Search");
    private SearchTerminalServiceAsync rpcService = GWT.create(SearchTerminalService.class);

    /**
     * Interface rendering
     * Search fields, Search Button and Results
     */
    public void onModuleLoad() {

        // Assemble bank search panel
        inputBankPanel.add(labelBank);
        inputBankPanel.add(inputBank);
        inputBankPanel.addStyleName("searchPanel");

        // Assemble city search panel
        inputCityPanel.add(labelCity);
        inputCityPanel.add(inputCity);
        inputCityPanel.addStyleName("searchPanel");

        // Assemble left panel with Search button
        leftPanel.add(inputBankPanel);
        leftPanel.add(inputCityPanel);
        leftPanel.add(searchButton);
        leftPanel.addStyleName("searchPanel");
        leftPanel.setSpacing(5);

        // Assemble right panel (table)
        rightPanel.setText(0, 0, "Bankname");
        rightPanel.setText(0, 1, "Address");

        // Add styles to elements in points list table
        rightPanel.getRowFormatter().addStyleName(0, "pointsListHeader");
        rightPanel.addStyleName("pointsList");
        rightPanel.setCellPadding(6);
        rightPanel.setVisible(false);

        // Assemble main panel
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        // Associate the Main panel with the HTML host page
        RootPanel.get("searchInterface").add(mainPanel);

        // Rendering full table
        getPointsFromServer("onModuleLoad");

        // Move cursor focus to the Bank input box
        inputBank.setFocus(true);

        // Listen for mouse events on the Search button
        searchButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                makeSearchQuery();
            }
        });

        // Creating an enter button click handler
        KeyDownHandler enterListen = new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
                    makeSearchQuery();
            }
        };

        // Listen for keyboard events on the Search button and text boxes
        searchButton.addKeyDownHandler(enterListen);
        inputCity.addKeyDownHandler(enterListen);
        inputBank.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    // Move cursor focus to the City input box
                    inputBank.setFocus(false);
                    inputCity.setFocus(true);
                }
            }
        });
    }

    /**
     * Validate and assemble the inputs for searching
     */
    private void makeSearchQuery() {
        final String search;
        final String bank = inputBank.getText().toLowerCase().trim();
        final String city = inputCity.getText().toLowerCase().trim();

        // Bank name must be shorter than 20 chars and consist of numbers and lowercase letters only
        if ( !bank.matches("^[0-9a-z]{1,20}$") ) {
            Window.alert("'" + bank + "' is not a valid bank name");
            inputBank.selectAll();
            return;
        }
        // Create a temporary search String
        String searchTemp = bank;

        // City name must be shorter than 20 chars and consist of numbers and lowercase letters only
        if ( (!city.equals("")) && (!city.matches("^[0-9a-z]{1,20}$")) ) {
            Window.alert("'" + city + "' is not a valid city name");
            inputCity.selectAll();
            return;
        }

        // Add the City name to the temporary search
        if (!city.isEmpty()) searchTemp += " " + city;

//        // Initialize the RPC service proxy
//        if (rpcService == null) rpcService = GWT.create(SearchTerminalService.class);
//
//        // Set up the callback object
//        AsyncCallback<List<PointsEntity>> callback = new AsyncCallback<List<PointsEntity>>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                Window.alert("Something wrong with AsyncCallback!");
//            }
//
//            @Override
//            public void onSuccess(List<PointsEntity> result) {
//                renderResults(result);
//            }
//        };

        // Init a final search String
        search = searchTemp;

        // Get the specified Points from server
        //rpcService.getPoints(search, callback);
        getPointsFromServer(search);
    }

    /**
     * Initialize the RPC service proxy, create the callback object, call the method getPoints on Server
     * @param search the validated search String
     */

    public void getPointsFromServer(String search) {

        // Initialize the RPC service proxy
        if (rpcService == null) rpcService = GWT.create(SearchTerminalService.class);

        // Set up the callback object
        AsyncCallback<List<PointsEntity>> callback = new AsyncCallback<List<PointsEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Something wrong with AsyncCallback!");
            }

            @Override
            public void onSuccess(List<PointsEntity> result) {
                renderResults(result);
            }
        };

        // Get the specified Points from server
        rpcService.getPoints(search, callback);
    }

    /**
     * Add found points to the Right Panel
     * @param points
     */
    private void renderResults(List<PointsEntity> points) {
        int row = 1;
        String fullAddress;

        rightPanel.setVisible(true);

        for (PointsEntity point : points) {
            rightPanel.setText(row, 0, point.getBankname());
            fullAddress = point.getCountry() + ", " + point.getCity() + ", " + point.getAddress();
            rightPanel.setText(row, 1, fullAddress);
            row++;
        }
    }
}
